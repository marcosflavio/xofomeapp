package br.com.xofome.xofome.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.xofome.xofome.model.Produto;

/**
 * Created by Caio on 02/11/2016.
 */

public class ProdutoDAOMemory {
    private static int ID_GENERATOR = 0;

    private static List<Produto> produtos = new ArrayList<>();

    public void save(Produto produto){
        if (produto != null){
            produto.setIdProduto(ID_GENERATOR++);
            ProdutoDAOMemory.produtos.add(produto);
        }
    }

    public void delete(Produto produto){
        if (produto != null){
            ProdutoDAOMemory.produtos.remove(produto);
        }
    }

    public Produto find(int id){
        for (Produto produto : ProdutoDAOMemory.produtos){
            if (produto.getIdProduto() == id)
                return produto;
        }

        return null;
    }

    public List<Produto> findAllTipo(int tipo){
        List<Produto> produtos = new ArrayList<>();

        for (Produto produto : ProdutoDAOMemory.produtos){
            Produto p = this.find(produto.getIdProduto());

            if (p != null && p.getTipo() == tipo)
                produtos.add(produto);
        }

        return produtos;
    }
}