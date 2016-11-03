package br.com.xofome.xofome;

import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

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

    private void inicializar(int id){
        Produto p = ProdutoServiceMemory.getProdutoById(id);

        if (p != null){
            mNomeProduto.setText(p.getNomeProduto());
            mPrecoProduto.setText(String.valueOf(p.getPreco()));
            mDescricaoProduto.setText(p.getDescricao());
        }
        else{
            Toast.makeText(this, "Erro ao visualizar o produto.", Toast.LENGTH_LONG).show();
        }
    }
}
