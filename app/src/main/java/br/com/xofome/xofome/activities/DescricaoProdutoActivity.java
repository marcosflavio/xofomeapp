package br.com.xofome.xofome.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

import br.com.xofome.xofome.R;
import br.com.xofome.xofome.model.Produto;
import br.com.xofome.xofome.services.ProdutoService;

public class DescricaoProdutoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descricao_produto);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarDescProduto);
        setSupportActionBar(toolbar);

        try{
            Produto p = ProdutoService.getProduto(this,1);

            Toast.makeText(getApplicationContext(), "Produto " + p.getNomeProduto(), Toast.LENGTH_SHORT).show();
        }catch (IOException e){

        }

    }


}