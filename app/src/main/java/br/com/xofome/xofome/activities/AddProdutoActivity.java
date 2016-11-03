package br.com.xofome.xofome.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import br.com.xofome.xofome.R;
import br.com.xofome.xofome.constantes.Codes;
import br.com.xofome.xofome.constantes.Keys;
import br.com.xofome.xofome.model.Produto;
import br.com.xofome.xofome.services.ProdutoService;

public class AddProdutoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarAdd);
        setSupportActionBar(toolbar);
    }


    public void salvar(View view) {
        EditText nomeProduto = (EditText) findViewById(R.id.editTextNome);
        EditText tipoProduto = (EditText) findViewById(R.id.editTextTipo);
        EditText precoProduto = (EditText) findViewById(R.id.editTextPreco);
        EditText descProduto = (EditText) findViewById(R.id.editTextDescricao);

        int tipo = Integer.valueOf(tipoProduto.getText().toString());
        float preco = Float.valueOf(precoProduto.getText().toString());
        String nome = nomeProduto.getText().toString();
        String desc = descProduto.getText().toString();

        Produto produto = ProdutoService.formarProduto(tipo, preco, nome, desc);
        ProdutoService.save(getApplicationContext(), produto);

        Intent sav = new Intent();
        sav.putExtra(Keys.RESPONSE_SAVE_NOME, nome);
        setResult(Codes.RESPONSE_ADD_OK);

        finish();
    }
}

