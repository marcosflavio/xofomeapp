package br.com.xofome.xofome.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import br.com.xofome.xofome.db.DBHelper;
import br.com.xofome.xofome.model.Produto;
import br.com.xofome.xofome.model.Usuario;

/**
 * Created by marcosf on 27/11/2016.
 */

public class UsuarioDAO {

    private Context context;
    private String table_name = "usuario";
    private static final String TAG = "sql";

    public UsuarioDAO (Context context){
        this.context = context;
    }

    public void save(Usuario usuario){

        SQLiteDatabase db = new DBHelper(context).getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("email", usuario.getEmail());
            //insiro o usuario
            db.insert(table_name, "", values);
        } finally {
            Log.d(TAG, "Usuario " + usuario.getEmail() + " adicionado ao banco!");
            db.close();
        }

    }

    public long getTaskCount() {
        SQLiteDatabase db = new DBHelper(context).getWritableDatabase();
        return DatabaseUtils.queryNumEntries(db,table_name);
    }

    public String find() {
        SQLiteDatabase db = new DBHelper(context).getWritableDatabase();

        try {
            Cursor c = db.query(table_name, null, null, null, null, null, null);
            return toEmail(c);
        } finally {
            db.close();
        }
    }

    private String toEmail(Cursor c) {
        Usuario user = new Usuario();

        if (c.moveToFirst()) {
            Log.w("moveToFirst", "true");
            user.setEmail(c.getString(c.getColumnIndex("email")));
            return user.getEmail();
        } else {
            Log.w("moveToFirst", "false");
            return null;
        }
    }


}
