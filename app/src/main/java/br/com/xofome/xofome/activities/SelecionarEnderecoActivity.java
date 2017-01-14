package br.com.xofome.xofome.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.Toast;

import br.com.xofome.xofome.R;
import br.com.xofome.xofome.constantes.Codes;

public class SelecionarEnderecoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionar_endereco);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarSelecionarEndereco);
        setSupportActionBar(toolbar);

        final RadioGroup group = (RadioGroup) findViewById(R.id.group1);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                boolean atual = R.id.radioButtonEnderecoAtual == i;
                boolean cadatrado = R.id.radioButtonEnderecoCadastrado == i;

                if (atual) {
                    Toast.makeText(getApplicationContext(), "Optou por utilizar o endereço atual",
                            Toast.LENGTH_SHORT).show();
                } else if (cadatrado) {
                    Toast.makeText(getApplicationContext(), "Optou por utilizar o endereço cadastrado",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_selecionar_endereco, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.item_menu_confirm_selecionar_end) {
            Toast.makeText(getApplicationContext(), "Pedido realizado com sucesso!!",
                    Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, ListarProdutosActivity.class);
            startActivityForResult(intent, Codes.REQUEST_LIST);

        } else if (id == R.id.item_menu_cancel__selecionar_end) {

            Intent intent = new Intent(this, ListarProdutosActivity.class);
            startActivityForResult(intent, Codes.REQUEST_LIST);
        }

        return true;
    }

}
