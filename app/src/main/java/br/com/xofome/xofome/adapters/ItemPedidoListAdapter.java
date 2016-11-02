package br.com.xofome.xofome.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import br.com.xofome.xofome.R;
import br.com.xofome.xofome.model.ItemPedido;

/**
 * Created by Caio on 30/10/2016.
 */

public class ItemPedidoListAdapter extends BaseAdapter{

    private List<ItemPedido> itens;
    private Context context;

    public ItemPedidoListAdapter(Context context,  List<ItemPedido> objects) {
        itens = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater li;
        li = LayoutInflater.from(context);
        View v =  li.inflate(R.layout.product_list_item_layout, null);

        ItemPedido itemPedido = getItem(position);

        if (itemPedido != null){
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.quant_itens, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            TextView nomeItem = (TextView) v.findViewById(R.id.nomeItemPedido);
            nomeItem.setText(itemPedido.getNomeProduto());

            Spinner spinner = (Spinner) v.findViewById(R.id.quantidadeItemPedido);
            spinner.setAdapter(adapter);

            spinner.setSelection(itemPedido.getQuantidade() - 1);
        }

        return v;
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
        return itens.get(position).getIdItemPedido();
    }

    public int getIdProduto(int position){
        return itens.get(position).getIdProduto();
    }
}
