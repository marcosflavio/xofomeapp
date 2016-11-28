package br.com.xofome.xofome.model;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by marcosf on 27/11/2016.
 */
public class ItemPedidoSingleton {
    private static ItemPedidoSingleton instancia;
    private double valorTotalItens;
    private List<ItemPedido> itensPedido = new ArrayList<ItemPedido>();

    private ItemPedidoSingleton(){
    }
    public static ItemPedidoSingleton getInstancia(){
        if(instancia == null){
            instancia = new ItemPedidoSingleton();
        }
        return instancia;
    }

    public void adicionarItem(ItemPedido itemPedido){
        itensPedido.add(itemPedido);
    }

    public List<ItemPedido> getListItens(){
        return this.itensPedido;
    }

    public void clear (){
        itensPedido.clear();
    }
    public double getValorTotalPedido() {
        double valorTotal =0;
        for(ItemPedido i: itensPedido){
            valorTotal += i.getValor();
        }
        valorTotalItens = valorTotal;
        return valorTotalItens;
    }
}
