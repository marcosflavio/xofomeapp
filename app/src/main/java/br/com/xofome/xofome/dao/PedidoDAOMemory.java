package br.com.xofome.xofome.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.xofome.xofome.model.Pedido;
import br.com.xofome.xofome.model.Produto;

/**
 * Created by marcosf on 09/11/2016.
 */

public class PedidoDAOMemory {


    private static int ID_GENERATOR = -1;

    private static List<Pedido> pedidos = new ArrayList<>();

    public void save(Pedido pedido) {
        if (pedido != null) {
            pedido.setIdPedido(++ID_GENERATOR);
            PedidoDAOMemory.pedidos.add(pedido);
        }
    }

    public void delete(Pedido pedido) {
        if (pedido != null) {
            PedidoDAOMemory.pedidos.remove(pedido);
        }
    }

    public Pedido find(int id) {
        for (Pedido pedido : PedidoDAOMemory.pedidos) {
            if (pedido.getIdPedido() == id)
                return pedido;
        }

        return null;
    }

    public List<Pedido> findAll() {
        List<Pedido> pedidos = new ArrayList<>();

        for (Pedido pedido : PedidoDAOMemory.pedidos) {
            Pedido p = this.find(pedido.getIdPedido());

            if (p != null)
                pedidos.add(pedido);
        }
        return pedidos;
    }
}
