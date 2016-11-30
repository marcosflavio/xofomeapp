package br.com.xofome.xofome.model;

import java.util.List;

/**
 * Created by marcosf on 27/11/2016.
 */
public class Usuario {

    private String email;

   // private List<Pedido> pedidos;

    public Usuario (){
    }

    public Usuario (String email){
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public List<Pedido> getPedidos() {
//        return pedidos;
//    }
//
//    public void setPedidos(List<Pedido> pedidos) {
//        this.pedidos = pedidos;
//    }
}
