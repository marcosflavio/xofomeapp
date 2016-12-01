package br.com.xofome.xofome.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.xofome.xofome.R;
import br.com.xofome.xofome.adapters.AcompanharPedidosAdapter;
import br.com.xofome.xofome.constantes.Codes;
import br.com.xofome.xofome.model.Pedido;
import br.com.xofome.xofome.services.PedidoService;
import br.com.xofome.xofome.services.UpdateStatusService;

public class AcompanharPedidosActivity extends AppCompatActivity {
    private List<Pedido> pedidos;
    RecyclerView rv;
    protected SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acompanhar_pedidos);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeAcompanhar);
        swipeRefreshLayout.setOnRefreshListener(OnRefreshListener());
        swipeRefreshLayout.setColorSchemeResources(R.color.refresh_progress_1,R.color.refresh_progress_2,
                R.color.refresh_progress_3);


        rv = (RecyclerView) findViewById(R.id.recycler_view_acompanhar_pedido);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarAcompanharPedido);
        setSupportActionBar(toolbar);
        registerReceiver(receiverAcompanhar,new IntentFilter("Update_status_complete"));
        registerReceiver(receiverAcompanharFail, new IntentFilter("Update_status_erro"));

        rv.setHasFixedSize(true);
       // Pedido p = new Pedido();
       // p.setStatus("Entrege");
        //PedidoService.save(p,getApplicationContext());
        pedidos = PedidoService.findAll(getApplicationContext());
        AcompanharPedidosAdapter adapter = new AcompanharPedidosAdapter(getApplicationContext(), pedidos, onClickPedido());
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
                        + pedido.getStatus(), Toast.LENGTH_SHORT).show();
            }
        };
    }


    private SwipeRefreshLayout.OnRefreshListener OnRefreshListener(){
        return  new SwipeRefreshLayout.OnRefreshListener(){

            @Override
            public void onRefresh() {
                startService(new Intent(getApplicationContext(),UpdateStatusService.class));
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

        if (id == R.id.item_menu_back) {
            Intent intent = new Intent(this, ListarProdutosActivity.class);
            startActivityForResult(intent, Codes.REQUEST_LIST);
            return true;
        }else if(id == R.id.item_menu_acompanhar_atualizar){
            startService(new Intent(this,UpdateStatusService.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        swipeRefreshLayout.setRefreshing(false);
        pedidos = PedidoService.findAll(getApplicationContext());
        AcompanharPedidosAdapter adapter = new AcompanharPedidosAdapter(getApplicationContext(), pedidos, onClickPedido());
        rv.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);
        super.onResume();
    }

    private BroadcastReceiver receiverAcompanhar = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            onResume();
        }
    };

    private BroadcastReceiver receiverAcompanharFail = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("service","Entrei no BroadcastReceiver acompanharFail");
            Toast.makeText(getApplicationContext(), "Falha ao verificar atualizações, favor verificar conexão com a internet.",
                    Toast.LENGTH_SHORT).show();
        }
    };

}
