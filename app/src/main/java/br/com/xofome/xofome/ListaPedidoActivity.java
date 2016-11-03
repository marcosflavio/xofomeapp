package br.com.xofome.xofome;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.xofome.xofome.adapters.ItemPedidoListAdapter;
import br.com.xofome.xofome.constantes.Keys;
import br.com.xofome.xofome.model.ItemPedido;
import br.com.xofome.xofome.model.Produto;
import br.com.xofome.xofome.services.ProdutoServiceMemory;

public class ListaPedidoActivity extends AppCompatActivity {

    private ListView mListView;
    private List<ItemPedido> itensPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pedido);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ProdutoServiceMemory.save(new Produto("Pastel de flango", 3.50f, "Descrição do pastel de flango", 0));
        ProdutoServiceMemory.save(new Produto("Pastel de peixe", 5f, "Descrição do pastel de peixe", 0));
        ProdutoServiceMemory.save(new Produto("Pastel de arroz", 4.30f, "Descrição do pastel de arroz", 0));

        itensPedido = getItems();

        ListAdapter adapter = new ItemPedidoListAdapter(this, itensPedido);

        mListView = (ListView) findViewById(R.id.listaItemsPedido);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mostrarDetalhes(position);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    public void mostrarDetalhes(int position){
        Intent intent = new Intent(this, DescricaoProdutoActivity.class);
        intent.putExtra(Keys.REQUEST_DETALHES, itensPedido.get(position).getIdProduto());
        startActivity(intent);
    }

    public List<ItemPedido> getItems(){
        List<ItemPedido> itens  = new ArrayList<>();

        List<Produto> prods = ProdutoServiceMemory.getProdutos(0);

        for (int i = 0; i < prods.size(); i++) {
            itens.add(new ItemPedido(i, 0, prods.get(i).getIdProduto(), prods.get(i).getNomeProduto(), 1));
            Log.w("quant_produtos", String.valueOf(prods.get(i).getIdProduto()));
        }

        return itens;
    }

}
