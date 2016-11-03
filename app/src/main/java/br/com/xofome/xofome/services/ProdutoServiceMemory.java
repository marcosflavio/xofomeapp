package br.com.xofome.xofome.services;

import java.util.List;

import br.com.xofome.xofome.dao.ProdutoDAOMemory;
import br.com.xofome.xofome.model.Produto;

/**
 * Created by Caio on 02/11/2016.
 */

public class ProdutoServiceMemory {

    private static ProdutoDAOMemory dao = new ProdutoDAOMemory();

    public static Produto getProdutoById(int id) {
        return dao.find(id);
    }

    public static List<Produto> getProdutos(int tipo) {
        return dao.findAllTipo(tipo);
    }

    public static void save(Produto produto) {
        dao.save(produto);
    }

    public static void delete(Produto produto) {
        dao.delete(produto);
    }

    public static Produto formarProduto(int tipo, float preco, String nome, String desc) {
        Produto produto = new Produto();

        produto.setTipo(tipo);
        produto.setPreco(preco);
        produto.setNomeProduto(nome);
        produto.setDescricao(desc);

        return produto;
    }
}
