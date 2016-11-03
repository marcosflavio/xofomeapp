package br.com.xofome.xofome.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import br.com.xofome.xofome.R;
import br.com.xofome.xofome.constantes.Keys;
import br.com.xofome.xofome.model.Produto;
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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
        Produto p = ProdutoServiceMemory.getProdutoById(id);

        if (p != null) {
            mNomeProduto.setText(p.getNomeProduto());
            mPrecoProduto.setText(String.valueOf(p.getPreco()));
            mDescricaoProduto.setText(p.getDescricao());
        } else {
            Toast.makeText(this, "Erro ao visualizar o produto.", Toast.LENGTH_LONG).show();
            finish();
        }
    }
}
