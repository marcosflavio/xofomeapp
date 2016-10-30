package br.com.xofome.xofome.activities;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.List;

import br.com.xofome.xofome.R;
import br.com.xofome.xofome.adapters.MyAdapter;
import br.com.xofome.xofome.model.Produto;
import br.com.xofome.xofome.services.ProdutoService;

/**
 * Created by marcosf on 29/10/2016.
 */

public class BlankFragment extends Fragment {
    private BlankFragment.OnFragmentInteractionListener mListener;
    private static List<Produto> produtos;
    private String tipo;



    public BlankFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.tipo = getArguments().getString("tipo");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_blank, container, false);

        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.rv_recycler_view);
        rv.setHasFixedSize(true);

        try {
            if(this.tipo.equals("comidas")){

                produtos = ProdutoService.getProdutos(getContext(),0);
            }else if(this.tipo.equals("bebidas")){
                produtos = ProdutoService.getProdutos(getContext(),1);
            }

            MyAdapter adapter = new MyAdapter(this.getContext(),produtos, onClickProduto());

            rv.setAdapter(adapter);

            LinearLayoutManager llm = new LinearLayoutManager(getActivity());
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
            rv.setLayoutManager(gridLayoutManager);


         } catch (IOException e) {
            e.printStackTrace();
        }
        return rootView;
    }

    private MyAdapter.ProdutoOnClickListener onClickProduto(){
        return new MyAdapter.ProdutoOnClickListener(){

            @Override
            public void onClickProduto(View view, int idx){
                Produto p = produtos.get(idx);
            }
        };
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}