package br.com.xofome.xofome.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.xofome.xofome.R;
import br.com.xofome.xofome.model.Produto;

/**
 * Created by marcosf on 27/10/2016.
 */

public class ProdutoAdapter extends RecyclerView.Adapter <ProdutoAdapter.ProdutosViewHolder>{

    private final List<Produto> produtos;
    private final Context context;
    private final ProdutoOnClickListener onClickListener;


    public interface ProdutoOnClickListener{
        public void onClickProduto(View view, int idx);
    }

    public ProdutoAdapter(Context context, List<Produto> produtos, ProdutoOnClickListener onClickListener){
        this.context = context;
        this.produtos = produtos;
        this.onClickListener = onClickListener;
    }

    @Override
    public ProdutoAdapter.ProdutosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Esse método cria uma subclasse de RecycleView.ViewHolder
        //Infla o view do Layout
        View view = LayoutInflater.from(context).inflate(R.layout.card_item, parent, false);
        //Cria a classe do ViewHolder
        ProdutosViewHolder holder = new ProdutosViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ProdutoAdapter.ProdutosViewHolder holder, final int position) {
        //Este método recebe o índice do elemento, e a atualiza as views que estao dentro do viewHolder

        Produto p = produtos.get(position);
        //Atualiza os valores nas views
        holder.nomeProduto.setText(p.getNomeProduto());
        holder.precoProduto.setText(String.valueOf(p.getPreco()));

        //Click
        if(onClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {

              onClickListener.onClickProduto(holder.view, position);

                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return this.produtos != null ? this.produtos.size() : 0;
    }

    //Subclasse de RecycleView.ViewHolder, contém todas as views

    public static class ProdutosViewHolder extends RecyclerView.ViewHolder{
        public TextView nomeProduto;
        public TextView precoProduto;
        public CardView cardView;
        private View view;
        public ImageButton imgShop;
        public ImageButton imgMenu;
        public ImageView imageView;
        public ProdutosViewHolder(View view){
            super(view);
            this.view = view;
            nomeProduto = (TextView) view.findViewById(R.id.info_text);
            imgShop = (ImageButton) view.findViewById(R.id.imageButton);
            imgMenu = (ImageButton) view.findViewById(R.id.imageButton2);
            precoProduto = (TextView) view.findViewById(R.id.textViewPrice);
            imageView = (ImageView) view.findViewById(R.id.imageProduto);
        }

        public View getView() {
            return view;
        }
    }
}
