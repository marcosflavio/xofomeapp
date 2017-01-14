package br.com.xofome.xofome.services;

import android.content.ContentValues;
import android.content.Context;

import br.com.xofome.xofome.dao.UsuarioDAO;
import br.com.xofome.xofome.model.Usuario;

/**
 * Created by marcosf on 27/11/2016.
 */

public class UsuarioService {

    private Context context;
    public UsuarioService(Context context){
        this.context = context;
    }

    public void save(Usuario usuario){
        UsuarioDAO dao = new UsuarioDAO(context);
        dao.save(usuario);
    }

    public Usuario getUsuario(){
        UsuarioDAO dao = new UsuarioDAO(context);
        return dao.find();
    }

    public Long getUser (){
        UsuarioDAO dao = new UsuarioDAO(context);
        return dao.getTaskCount();
    }
}
