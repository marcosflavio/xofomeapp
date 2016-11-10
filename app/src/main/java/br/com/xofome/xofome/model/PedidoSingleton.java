package br.com.xofome.xofome.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcosf on 09/11/2016.
 */

public class PedidoSingleton {

    private static PedidoSingleton instancia;

    private int idPedido;
    private List<ItemPedido> itensPedido = new ArrayList<ItemPedido>();
    private String status = "Inativo";
    private double valorTotalPedido;



    private PedidoSingleton(){

    }

    public static PedidoSingleton getInstancia(){
        if(instancia == null){
            instancia = new PedidoSingleton();
        }

        return instancia;
    }

    public void iniciarLista(){

        if(instancia.getItensPedido().size() == 0) {

            List<ItemPedido> itens = new ArrayList<ItemPedido>();

            for (int i = 0; i < 5; i++) {
                ItemPedido itemPedido = new ItemPedido(i, 1, 5, "Pastel de Flango " + i, 2, 5.00);
                itens.add(itemPedido);
            }

            instancia.setItensPedido(itens);
        }
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

        double valorTotal =0;

        for(ItemPedido i: itensPedido){
            valorTotal += i.getValor();
        }
        valorTotalPedido = valorTotal;
        return valorTotalPedido;
    }

    public void setValorTotalPedido(double valorTotalPedido) {
        this.valorTotalPedido = valorTotalPedido;
    }

}
