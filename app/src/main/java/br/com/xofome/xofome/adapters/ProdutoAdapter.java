package br.com.xofome.xofome.adapters;

/**
 * Created by marcosf on 30/10/2016.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.xofome.xofome.R;
import br.com.xofome.xofome.activities.DescricaoProdutoActivity;
import br.com.xofome.xofome.constantes.Keys;
import br.com.xofome.xofome.model.Produto;
import br.com.xofome.xofome.util.ImageUtil;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.MyViewHolder> {
    //private String[] mDataset;
    private final List<Produto> produtos;
    private final Context context;
    private final ProdutoAdapter.ProdutoOnClickListener onClickListener;

    public interface ProdutoOnClickListener {
        public void onClickProduto(View view, int idx);
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView mCardView;
        public TextView textViewNomeProduto;
        public ImageView imageView;
        public TextView textViewDesc;
        public TextView textViewPrice;
        public ImageButton imageButtonShop;
        public ImageButton imageButtonMenu;
        private View view;

        public MyViewHolder(View v) {
            super(v);
            this.view = v;
            mCardView = (CardView) v.findViewById(R.id.card_view);
            imageView = (ImageView) v.findViewById(R.id.imageProduto);
            textViewNomeProduto = (TextView) v.findViewById(R.id.info_text);
            textViewDesc = (TextView) v.findViewById(R.id.textDescriptionPrice);
            textViewPrice = (TextView) v.findViewById(R.id.textViewPrice);
            imageButtonShop = (ImageButton) v.findViewById(R.id.imageButton2);
        }

        public View getView() {
            return view;
        }
    }


    public ProdutoAdapter(Context context, List<Produto> produtos, ProdutoAdapter.ProdutoOnClickListener onClickListener) {
        this.context = context;
        this.produtos = produtos;
        this.onClickListener = onClickListener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ProdutoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final Produto p = produtos.get(position);
        //Atualiza os valores nas views
        holder.textViewNomeProduto.setText(p.getNomeProduto());
        holder.textViewPrice.setText(String.valueOf(p.getPreco()));
        holder.imageView.setImageBitmap(ImageUtil.getImage(p.getImagem()));
        holder.imageButtonShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context,"Você adicionou o produto " +  p.getNomeProduto() +
                " ao seu pedido!", Toast.LENGTH_SHORT).show();
            }
        });

        //Click
        if (onClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    onClickListener.onClickProduto(holder.getView(), position);

                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }
}