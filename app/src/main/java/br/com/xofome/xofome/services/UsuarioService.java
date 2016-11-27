package br.com.xofome.xofome.services;

import android.content.ContentValues;
import android.content.Context;

import br.com.xofome.xofome.dao.UsuarioDAO;
import br.com.xofome.xofome.model.Usuario;

/**
 * Created by marcosf on 27/11/2016.
 */

public class UsuarioService {

    public void save(Context context, Usuario usuario){
        UsuarioDAO dao = new UsuarioDAO(context);
        dao.save(usuario);
    }
}
