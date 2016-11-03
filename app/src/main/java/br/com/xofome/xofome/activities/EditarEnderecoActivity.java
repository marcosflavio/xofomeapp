package br.com.xofome.xofome.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import br.com.xofome.xofome.R;
import br.com.xofome.xofome.constantes.Codes;

public class EditarEnderecoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_endereco);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarEditarEndereco);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editar_endereco, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.item_menu_confirm_editar_end){

            EditText rua = (EditText) this.findViewById(R.id.editEditarTextRua);
            EditText bairro = (EditText) this.findViewById(R.id.editTextEditarBairro);
            EditText numero = (EditText) this.findViewById(R.id.editTextEditarNumero);
            EditText complemento = (EditText) this.findViewById(R.id.editTextMultAreaEditarComplemento);
            EditText pnt_ref = (EditText) this.findViewById(R.id.multiAreaEditarPontoRef);

            String str_rua = rua.getText().toString();
            String str_bairro = bairro.getText().toString();
            String str_numero = numero.getText().toString();
            String str_complemento = complemento.getText().toString();
            String str_pontoRef = pnt_ref.getText().toString();

            Toast.makeText(getApplicationContext(), "Endereço :" + str_rua + " " + str_bairro + " "+
                            str_numero + " " + str_complemento + " " + str_pontoRef + " atualizado com sucesso!",
                    Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this,ListarProdutosActivity.class);
            startActivityForResult(intent, Codes.REQUEST_LIST);
            return true;

        }else if(id == R.id.item_menu_cancel__editar_end){

            Intent intent = new Intent(this,ListarProdutosActivity.class);
            startActivityForResult(intent, Codes.REQUEST_BACK);
            return true;

        }

        return super.onOptionsItemSelected(item);
    }
}
