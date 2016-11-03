package br.com.xofome.xofome.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.xofome.xofome.R;
import br.com.xofome.xofome.constantes.Codes;

public class CadastrarEnderecoActivity extends AppCompatActivity {

    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cadastrar_endereco);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarCadastrarEndereco);
        setSupportActionBar(toolbar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cadastrar_endereco, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.item_menu_confirm) {

            EditText rua = (EditText) this.findViewById(R.id.editTextRua);
            EditText bairro = (EditText) this.findViewById(R.id.editTextBairro);
            EditText numero = (EditText) this.findViewById(R.id.editTextNumero);
            EditText complemento = (EditText) this.findViewById(R.id.editTextMultAreaComplemento);
            EditText pnt_ref = (EditText) this.findViewById(R.id.multiAreaPontoRef);

            String str_rua = rua.getText().toString();
            String str_bairro = bairro.getText().toString();
            String str_numero = numero.getText().toString();
            String str_complemento = complemento.getText().toString();
            String str_pontoRef = pnt_ref.getText().toString();

            Toast.makeText(getApplicationContext(), "Endereço :" + str_rua + " " + str_bairro + " " +
                            str_numero + " " + str_complemento + " " + str_pontoRef + " adicionado com sucesso!",
                    Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, ListarProdutosActivity.class);
            startActivityForResult(intent, Codes.REQUEST_LIST);
            return true;

        } else if (id == R.id.item_menu_cancel) {

            Intent intent = new Intent(this, MainActivity.class);
            startActivityForResult(intent, Codes.REQUEST_BACK);
            return true;

        }

        return super.onOptionsItemSelected(item);
    }
}
