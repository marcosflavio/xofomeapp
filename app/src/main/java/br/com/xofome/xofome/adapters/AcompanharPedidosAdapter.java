package br.com.xofome.xofome.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.xofome.xofome.R;
import br.com.xofome.xofome.model.Pedido;

/**
 * Created by marcosf on 02/11/2016.
 */

public class AcompanharPedidosAdapter extends RecyclerView.Adapter<AcompanharPedidosAdapter.MyViewHolder> {

    private final List<Pedido> pedidos;
    private final Context context;
    private final AcompanharPedidosAdapter.AcompanharPedidoOnClickListener onClickListener;

    public interface AcompanharPedidoOnClickListener {
        void onClickPedido(View view, int idx);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewIdPedido;
        public TextView textViewNumeroPedido;
        public TextView textViewStatusPedido;
        public TextView textViewNomeStatusPedido;

        private View view;

        public MyViewHolder(View v) {
            super(v);
            this.view = v;

            this.textViewIdPedido = (TextView) view.findViewById(R.id.textViewIdPedido);
            this.textViewNumeroPedido = (TextView) view.findViewById(R.id.textViewNumeroPedido);
            this.textViewStatusPedido = (TextView) view.findViewById(R.id.textViewStatusPedido);
            this.textViewNomeStatusPedido = (TextView) view.findViewById(R.id.textViewNomeStatusPedido);

        }

        public View getView() {
            return view;
        }
    }


    public AcompanharPedidosAdapter(Context context, List<Pedido> pedidos,
                                    AcompanharPedidosAdapter.AcompanharPedidoOnClickListener onClickListener) {
        this.context = context;
        this.pedidos = pedidos;
        this.onClickListener = onClickListener;
    }


    @Override
    public AcompanharPedidosAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pedido_layout, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final AcompanharPedidosAdapter.MyViewHolder holder, final int position) {

        Pedido pedido = pedidos.get(position);

        holder.textViewNumeroPedido.setText(String.valueOf(pedido.getIdPedido()));
        holder.textViewNomeStatusPedido.setText(pedido.getStatus());

        //Click
        if (onClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    onClickListener.onClickPedido(holder.getView(), position);

                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return pedidos.size();
    }
}
