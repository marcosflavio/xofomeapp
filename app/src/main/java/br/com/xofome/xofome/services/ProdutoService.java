package br.com.xofome.xofome.services;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import br.com.xofome.xofome.dao.ProdutoDAO;
import br.com.xofome.xofome.model.Produto;

/**
 * Created by marcosf on 27/10/2016.
 */

public class ProdutoService {

    public static List<Produto> getProdutos(Context context, int tipo) throws IOException{
        ProdutoDAO dao = new ProdutoDAO(context);
        try{
            List<Produto> produtos = dao.findAllTipo(tipo);
            return produtos;
        }finally {
            dao.close();
        }
    }

    public static Produto getProduto(Context context, int id) throws IOException{
        ProdutoDAO dao = new ProdutoDAO(context);
        try{
           Produto produto = dao.findById(id);
            return produto;
        }finally {
            dao.close();
        }
    }

    public static void save(Context context, Produto produto){
        ProdutoDAO dao = new ProdutoDAO(context);
        try{
            dao.save(produto);
        }finally {
            dao.close();
        }
    }

    public static void delete(Context context, Produto produto){
        ProdutoDAO dao = new ProdutoDAO(context);
        try{
            dao.delete(produto);
        }finally {
            dao.close();
        }
    }

    public static Produto formarProduto(int tipo, float preco, String nome, String desc){
        Produto produto = new Produto();
        produto.setTipo(tipo);
        produto.setPreco(preco);
        produto.setNomeProduto(nome);
        produto.setDescricao(desc);

        return produto;
    }
}
