package br.com.xofome.xofome;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.xofome.xofome.adapters.ItemPedidoListAdapter;
import br.com.xofome.xofome.model.ItemPedido;

public class ListaPedidoActivity extends AppCompatActivity {

    private ListView mListView;
    private List<ItemPedido> itensPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pedido);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        itensPedido = getItems();

        ListAdapter adapter = new ItemPedidoListAdapter(this, itensPedido);

        mListView = (ListView) findViewById(R.id.listaItemsPedido);
        mListView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarDetalhes();
            }
        });
    }

    public void mostrarDetalhes(){
        Intent intent = new Intent(this, DescricaoProdutoActivity.class);
        startActivity(intent);
    }

    public List<ItemPedido> getItems(){
        List<ItemPedido> itens  = new ArrayList<>();

        itens.add(new ItemPedido(0, 0, 0, "Pastel de flango", 3));
        itens.add(new ItemPedido(1, 1, 1, "Pastel de flango", 5));
        itens.add(new ItemPedido(3, 2, 1, "Pastel de flango", 1));

        return itens;
    }

}
