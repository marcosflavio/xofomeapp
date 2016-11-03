package br.com.xofome.xofome.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.List;

import br.com.xofome.xofome.R;
import br.com.xofome.xofome.model.ItemPedido;

/**
 * Created by Caio on 30/10/2016.
 */

public class ItemPedidoListAdapter extends BaseAdapter {

    private List<ItemPedido> itens;
    private Context context;

    public ItemPedidoListAdapter(Context context, List<ItemPedido> objects) {
        itens = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater li = LayoutInflater.from(context);
        convertView = li.inflate(R.layout.product_list_item_layout, null);

        ItemPedido itemPedido = getItem(position);

        if (itemPedido != null) {
            TextView nomeItem = (TextView) convertView.findViewById(R.id.nomeItemPedido);
            nomeItem.setText(itemPedido.getNomeProduto());

            NumberPicker quantidadeItemPedido = (NumberPicker) convertView.findViewById(R.id.quantidadeItemPedido);
            quantidadeItemPedido.setMinValue(1);
            quantidadeItemPedido.setMaxValue(10);

            quantidadeItemPedido.setValue(itemPedido.getQuantidade());
        }

        return convertView;
    }

    public void addItem(ItemPedido item) {
        itens.add(item);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return itens.size();
    }

    @Nullable
    @Override
    public ItemPedido getItem(int position) {
        return itens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
