package br.com.xofome.xofome.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import br.com.xofome.xofome.R;
import br.com.xofome.xofome.constantes.Codes;
import br.com.xofome.xofome.constantes.Keys;
import br.com.xofome.xofome.model.Produto;
import br.com.xofome.xofome.services.ProdutoService;
import br.com.xofome.xofome.services.ProdutoServiceMemory;

public class DescricaoProdutoActivity extends AppCompatActivity {

    private TextView mNomeProduto;
    private TextView mPrecoProduto;
    private TextView mDescricaoProduto;
    private ImageView mFotoProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descricao_produto);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarDescProduto);
        setSupportActionBar(toolbar);

        mNomeProduto = (TextView) findViewById(R.id.nomeProdutoTextDetalhes);
        mDescricaoProduto = (TextView) findViewById(R.id.conteudoDescricaoTextDetalhes);
        mPrecoProduto = (TextView) findViewById(R.id.precoProdutoText);
        mFotoProduto = (ImageView) findViewById(R.id.fotoProdutoImage);

        Intent intent = getIntent();
        int idProduto = intent.getIntExtra(Keys.REQUEST_DETALHES, -1);

        inicializar(idProduto);
    }

    private void inicializar(int id) {
       // Produto p = ProdutoServiceMemory.getProdutoById(id);

        try {
            Produto p = ProdutoService.getProduto(getApplicationContext(), id);

            if (p != null) {
                mNomeProduto.setText(p.getNomeProduto());
                mPrecoProduto.setText(String.valueOf(p.getPreco()));
                mDescricaoProduto.setText(p.getDescricao());
            } else {
                Toast.makeText(this, "Erro ao visualizar o produto.", Toast.LENGTH_LONG).show();
                finish();
            }
        }catch (IOException E){

        }
    }

    public void chamarConfirmar(View view){
        Intent intent = new Intent(this, ConfirmarPedidoActivity.class);
        startActivityForResult(intent, Codes.REQUEST_SELECT_END);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_descricao_produto, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.item_menu_back_descricao_produto) {
            Intent intent = new Intent(this, ListarProdutosActivity.class);
            startActivityForResult(intent, Codes.REQUEST_LIST);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
