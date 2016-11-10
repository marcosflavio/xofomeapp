package br.com.xofome.xofome.services;

import java.util.List;

import br.com.xofome.xofome.dao.PedidoDAOMemory;
import br.com.xofome.xofome.model.ItemPedido;
import br.com.xofome.xofome.model.Pedido;
/**
 * Created by marcosf on 09/11/2016.
 */

public class PedidoServiceMemory {

    private static PedidoDAOMemory dao = new PedidoDAOMemory();

    public static Pedido getPedidoById(int id) {
        return dao.find(id);
    }

    public static List<Pedido> getPedidos() {
        return dao.findAll();
    }

    public static void save(Pedido pedido) {
        dao.save(pedido);
    }

    public static void delete(Pedido pedido) {
        dao.delete(pedido);
    }

    public static Pedido formarPedido(int idPedido, List<ItemPedido> itensPedido, String status,
                                      double valorTotalPedido) {
        Pedido pedido = new Pedido();
        pedido.setIdPedido(idPedido);
        pedido.setItensPedido(itensPedido);
        pedido.setStatus(status);
        pedido.setValorTotalPedido(valorTotalPedido);
        return pedido;
    }

}
