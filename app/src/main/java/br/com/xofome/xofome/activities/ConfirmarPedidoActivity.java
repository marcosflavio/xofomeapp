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
import br.com.xofome.xofome.tasks.PedidoTask;

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
        pedido = new Pedido();

    }

    public void confirmar(View view){

        Intent intent = new Intent(this, SelecionarEnderecoActivity.class);
        startActivityForResult(intent, Codes.REQUEST_SELECT_END);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.item_menu_confirmar_pedido_ok) {
            EditText valorPago = (EditText) findViewById(R.id.editTextConfPagar);
            Double valor = Double.valueOf(valorPago.getText().toString());
            ItemPedidoSingleton itemPedidoSingleton = ItemPedidoSingleton.getInstancia();

            if( valor >= itemPedidoSingleton.getValorTotalPedido() ) {
                pedido.setStatus("Iniciado");

                pedido.setValorTotalPedido(itemPedidoSingleton.getValorTotalPedido());


                if (valor != null)
                    pedido.setValorASerPago(valor);

                UsuarioService serviceUsuario = new UsuarioService(getApplicationContext());
                pedido.setUsuario(serviceUsuario.getUsuario());

                PedidoTask pedidoTask = new PedidoTask(getApplicationContext());
                pedidoTask.execute(pedido);

                Toast.makeText(getApplicationContext(), "Pedido confirmado!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ListarProdutosActivity.class);
                startActivityForResult(intent, Codes.REQUEST_BACK);
            } else {
                Toast.makeText(getApplicationContext(), "Valor a ser pago menor que o valor total!", Toast.LENGTH_SHORT).show();
            }


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
