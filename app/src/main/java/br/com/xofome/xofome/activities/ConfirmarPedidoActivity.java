package br.com.xofome.xofome.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.com.xofome.xofome.R;
import br.com.xofome.xofome.constantes.Codes;
import br.com.xofome.xofome.model.ItemPedidoSingleton;
import br.com.xofome.xofome.model.Pedido;
import br.com.xofome.xofome.services.UsuarioService;

public class ConfirmarPedidoActivity extends AppCompatActivity {
    private Pedido pedido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_pedido);
        ItemPedidoSingleton itemPedidoSingleton = ItemPedidoSingleton.getInstancia();
        TextView valorPedido = (TextView) findViewById(R.id.textViewValorConfirma);
        valorPedido.setText(String.valueOf(itemPedidoSingleton.getValorTotalPedido()));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarConfimarPedido);
        setSupportActionBar(toolbar);

    }

    public void confirmar(View view){

        Intent intent = new Intent(this, SelecionarEnderecoActivity.class);
        startActivityForResult(intent, Codes.REQUEST_SELECT_END);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.item_menu_confirmar_pedido_ok) {
            ItemPedidoSingleton itemPedidoSingleton = ItemPedidoSingleton.getInstancia();
            EditText valorPago = (EditText) findViewById(R.id.editTextConfPagar);
            pedido = new Pedido();
            pedido.setStatus("Iniciado");

            pedido.setValorTotalPedido(itemPedidoSingleton.getValorTotalPedido());
            pedido.setValorASerPago(Double.valueOf(valorPago.getText().toString()));
            UsuarioService service = new UsuarioService(getApplicationContext());
            //modificar isso aqui, passar o próprio usuario
            //pedido.setEmail(service.getEmail());

            //Envio e salvo na web
            //chamar asynctask
            // depois salva os items de pedido que estão no singleton setando o id do pedido retornado
            
            Toast.makeText(getApplicationContext(), "Pedido confirmado!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ListarProdutosActivity.class);
            startActivityForResult(intent, Codes.REQUEST_BACK);

        }else if(id == R.id.item_menu_confirmar_pedido_cancel){
            Toast.makeText(getApplicationContext(), "Opção cancelada", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ListarProdutosActivity.class);
            startActivityForResult(intent, Codes.REQUEST_BACK);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_confirmar_pedido, menu);
        return true;
    }

}
