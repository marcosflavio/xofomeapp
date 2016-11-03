package br.com.xofome.xofome.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.xofome.xofome.R;
import br.com.xofome.xofome.adapters.AcompanharPedidosAdapter;
import br.com.xofome.xofome.adapters.ProdutoAdapter;
import br.com.xofome.xofome.constantes.Codes;
import br.com.xofome.xofome.model.ItemPedido;
import br.com.xofome.xofome.model.Pedido;
import br.com.xofome.xofome.model.Produto;

public class AcompanharPedidosActivity extends AppCompatActivity {
    private List<Pedido> pedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acompanhar_pedidos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarAcompanharPedido);
        setSupportActionBar(toolbar);

        AcompanharPedidosAdapter adapter = null;
        RecyclerView rv = (RecyclerView) findViewById(R.id.recycler_view_acompanhar_pedido);
        rv.setHasFixedSize(true);
        pedidos = new ArrayList<Pedido>();
        Pedido pedido = new Pedido();
        pedido.setIdPedido(1);
        pedido.setStatus("Recebido");
        List<ItemPedido> itens = new ArrayList<ItemPedido>();
        for(int i = 0; i < 5; i++){
            ItemPedido itemPedido = new ItemPedido(i,1,5,"Pastel de Flango " + i , 2);
            itens.add(itemPedido);
        }

        pedido.setItensPedido(itens);

        pedidos.add(pedido);
        adapter = new AcompanharPedidosAdapter(getApplicationContext(), pedidos, onClickPedido());
        rv.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);

    }

    private AcompanharPedidosAdapter.AcompanharPedidoOnClickListener onClickPedido() {

        return new AcompanharPedidosAdapter.AcompanharPedidoOnClickListener() {

            @Override
            public void onClickPedido(View view, int idx) {
                Pedido pedido = pedidos.get(idx);

                Toast.makeText(getApplicationContext(), "Pedido " + pedido.getIdPedido() + " com status: "
                + pedido.getStatus(),Toast.LENGTH_SHORT).show();
            }
        };
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_acompanhar_pedido, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.item_menu_back){
            Intent intent = new Intent(this,ListarProdutosActivity.class);
            startActivityForResult(intent, Codes.REQUEST_LIST);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
