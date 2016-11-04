package br.com.xofome.xofome.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import br.com.xofome.xofome.R;
import br.com.xofome.xofome.constantes.Codes;

public class ConfirmarPedidoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_pedido);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarConfimarPedido);
        setSupportActionBar(toolbar);

    }

    public void confirmar(View view){

        Intent intent = new Intent(this, SelecionarEnderecoActivity.class);
        startActivityForResult(intent, Codes.REQUEST_SELECT_END);
    }

}
