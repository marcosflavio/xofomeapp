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
import br.com.xofome.xofome.model.ItemPedido;
import br.com.xofome.xofome.model.ItemPedidoSingleton;
import br.com.xofome.xofome.model.Produto;
import br.com.xofome.xofome.services.ProdutoService;
import br.com.xofome.xofome.util.ImageUtil;

public class DescricaoProdutoActivity extends AppCompatActivity {

    private TextView mNomeProduto;
    private TextView mPrecoProduto;
    private TextView mDescricaoProduto;
    private ImageView mFotoProduto;
    private Produto p;
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
        int idProduto = (Integer) getIntent().getExtras().get(Keys.REQUEST_DETALHES);
        inicializar(idProduto);
    }

    private void inicializar(int id) {
        try {
             p = ProdutoService.getProduto(getApplicationContext(), id);

            if (p != null) {
                mNomeProduto.setText(p.getNomeProduto());
                mPrecoProduto.setText(String.valueOf(p.getPreco()));
                mDescricaoProduto.setText(p.getDescricao());
                mFotoProduto.setImageBitmap(ImageUtil.decodeBase64(p.getImagem()));
            } else {
                Toast.makeText(this, "Erro ao visualizar o produto.", Toast.LENGTH_LONG).show();
                finish();
            }
        }catch (IOException E){

        }
    }

    public void chamarConfirmar(View view){

        //Crio um novo item e adiciona à lista Singleton
        ItemPedido item = new ItemPedido();
        item.setProduto(p);
        item.setNomeProduto(p.getNomeProduto());
        item.setValor(p.getPreco());

        ItemPedidoSingleton itemPedidoSingleton = ItemPedidoSingleton.getInstancia();
        itemPedidoSingleton.adicionarItem(item);

        Toast.makeText(this,"Você adicionou o produto " +  p.getNomeProduto() +
                " ao seu pedido!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, ListarProdutosActivity.class);
        startActivityForResult(intent, Codes.REQUEST_LIST);
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
