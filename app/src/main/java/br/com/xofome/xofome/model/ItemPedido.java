package br.com.xofome.xofome.model;

/**
 * Created by Caio on 30/10/2016.
 */

public class ItemPedido {
    private int idItemPedido;
    private int idPedido;
    private int idProduto;
    private String nomeProduto;
    private int quantidade;

    public ItemPedido(int idItemPedido, int idPedido, int idProduto, String nomeProduto, int quantidade) {
        this.idItemPedido = idItemPedido;
        this.idPedido = idPedido;
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
    }

    public int getIdItemPedido() {
        return idItemPedido;
    }

    public void setIdItemPedido(int idItemPedido) {
        this.idItemPedido = idItemPedido;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemPedido that = (ItemPedido) o;

        if (getIdItemPedido() != that.getIdItemPedido()) return false;
        if (getIdPedido() != that.getIdPedido()) return false;
        if (getIdProduto() != that.getIdProduto()) return false;
        if (getQuantidade() != that.getQuantidade()) return false;
        return getNomeProduto().equals(that.getNomeProduto());

    }

    @Override
    public int hashCode() {
        int result = getIdItemPedido();
        result = 31 * result + getIdPedido();
        result = 31 * result + getIdProduto();
        result = 31 * result + getNomeProduto().hashCode();
        result = 31 * result + getQuantidade();
        return result;
    }
}
