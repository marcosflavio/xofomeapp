package br.com.xofome.xofome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import br.com.xofome.xofome.constantes.Codes;
import br.com.xofome.xofome.constantes.Keys;
import br.com.xofome.xofome.model.Produto;
import br.com.xofome.xofome.services.ProdutoService;
import br.com.xofome.xofome.services.ProdutoServiceMemory;

public class ActivityAdd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }


    public void salvar(View view){
            EditText nomeProduto = (EditText) findViewById(R.id.editTextNome);
            EditText tipoProduto = (EditText) findViewById(R.id.editTextTipo);
            EditText precoProduto = (EditText) findViewById(R.id.editTextPreco);
            EditText descProduto = (EditText) findViewById(R.id.editTextDescricao);

            int tipo = Integer.valueOf(tipoProduto.getText().toString());
            float preco = Float.valueOf(precoProduto.getText().toString());
            String nome = nomeProduto.getText().toString();
            String desc = descProduto.getText().toString();

//            Produto produto = ProdutoServiceMemory.formarProduto(tipo, preco, nome, desc);
            Produto produto = ProdutoServiceMemory.formarProduto(tipo, preco, nome, desc);
//            ProdutoService.save(this, produto);
            ProdutoServiceMemory.save(produto);

            Intent sav = new Intent();
            sav.putExtra(Keys.RESPONSE_SAVE_NOME, nome);
            setResult(Codes.RESPONSE_ADD_OK);

            finish();
        }
    }

