package br.com.xofome.xofome.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcosf on 02/11/2016.
 */

public class Pedido {

    private int idPedido;
    private List<ItemPedido> itensPedido;
    private String status = "Inativo";
    private double valorTotalPedido;

    public Pedido() {
        this.itensPedido = new ArrayList<ItemPedido>();
        idPedido++;
    }

    public List<ItemPedido> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<ItemPedido> itensPedido) {
        this.itensPedido = itensPedido;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public double getValorTotalPedido() {
        return valorTotalPedido;
    }

    public void setValorTotalPedido(double valorTotalPedido) {
        this.valorTotalPedido = valorTotalPedido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pedido)) return false;

        Pedido pedido = (Pedido) o;

        return idPedido == pedido.idPedido;

    }

    @Override
    public int hashCode() {
        return idPedido;
    }

}
