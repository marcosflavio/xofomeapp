package br.com.xofome.xofome.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import br.com.xofome.xofome.db.DBHelper;
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
}
