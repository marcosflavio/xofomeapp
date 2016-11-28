package br.com.xofome.xofome.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.xofome.xofome.R;
import br.com.xofome.xofome.adapters.ItemPedidoListAdapter;
import br.com.xofome.xofome.constantes.Codes;
import br.com.xofome.xofome.constantes.Keys;
import br.com.xofome.xofome.model.ItemPedido;
import br.com.xofome.xofome.model.ItemPedidoSingleton;
import br.com.xofome.xofome.model.Produto;
import br.com.xofome.xofome.services.ItemPedidoService;
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

        //pegar os itens de um determinado pedido somente
        //criar esse service
        ItemPedidoSingleton itemPedidoSingleton = ItemPedidoSingleton.getInstancia();
        itensPedido = itemPedidoSingleton.getListItens();

        ListAdapter adapter = new ItemPedidoListAdapter(this, itensPedido);

        mListView = (ListView) findViewById(R.id.listaItemsPedido);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mostrarDetalhes(position);
            }
        });
    }

    public void mostrarDetalhes(int position) {
        Intent intent = new Intent(this, DescricaoProdutoActivity.class);
        intent.putExtra(Keys.REQUEST_DETALHES, itensPedido.get(position).getIdProduto());
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_listar_item_pedido, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.item_menu_back_listar_itens_pedido) {
            Intent intent = new Intent(this, ListarProdutosActivity.class);
            startActivityForResult(intent, Codes.REQUEST_LIST);
            return true;
        }else if(id == R.id.item_menu_confirm_item_pedido){
            Intent intent = new Intent(this, ConfirmarPedidoActivity.class);
            startActivityForResult(intent, Codes.REQUEST_CONFIRM_PEDIDO);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
