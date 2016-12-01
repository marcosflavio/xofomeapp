package br.com.xofome.xofome.constantes;

/**
 * Created by marcosf on 27/11/2016.
 */

public class HTTP {
    public static final String URL = "http://192.168.0.174:8060";
    public static final String REQUEST_COMPARE_BD = URL + "/produtos/count";
    public static final String REQUEST_UPDATE_BD = URL + "/produtos/update/";
    public static final String REQUEST_SAVE_USER = URL + "/usuario/";
    public static final String REQUEST_SAVE_PEDIDO = URL + "/pedidos/";
    public static final String REQUEST_FIND_PEDIDO_BY_USER_AND_STATUS = URL + "/pedidos/";
    public static final String REQUEST_SAVE_ITEM_PEDIDO = URL + "/item/";
    public static final String REQUEST_FIND_ITENS_BY_PEDIDO = URL + "/pedidos/itensp/";
    public static final String REQUEST_UPDATE_STATUS_PEDIDO = URL + "/pedidos/findPedido/";
}
