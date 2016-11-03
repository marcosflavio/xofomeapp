package br.com.xofome.xofome.model;

/**
 * Created by marcosf on 27/10/2016.
 */

public class Produto {

    private static int idProduto = 0;
    private String nomeProduto;
    private Float preco;
    private String descricao;
    private int tipo;

    public Produto (String nome, Float preco, String descricao, int tipo){
        this.nomeProduto = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.tipo = tipo;
    }

    public Produto(){

    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
