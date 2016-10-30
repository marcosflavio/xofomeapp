package br.com.xofome.xofome.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.xofome.xofome.R;
import br.com.xofome.xofome.model.Produto;

/**
 * Created by marcosf on 29/10/2016.
 */

public class ProdutoAdapterGrid extends BaseAdapter {
    private Context context;
    private List<Produto> produtos;

    public ProdutoAdapterGrid(Context context, List<Produto> produtos){

        this.context = context;
        this.produtos = produtos;
    }

    @Override
    public int getCount() {
        return produtos.size();
    }

    @Override
    public Object getItem(int i) {
        return produtos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        View vi = LayoutInflater.from(this.context).inflate(R.layout.card_item,parent, false);

        if(vi != null){
            TextView nomeProduto = (TextView) vi.findViewById(R.id.info_text);
            nomeProduto.setText(produtos.get(i).getNomeProduto());
        }

        return vi;
    }
}
