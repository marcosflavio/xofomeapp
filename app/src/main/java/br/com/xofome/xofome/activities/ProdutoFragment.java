package br.com.xofome.xofome.activities;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.io.IOException;
import java.util.List;

import br.com.xofome.xofome.R;
import br.com.xofome.xofome.adapters.ProdutoAdapter;
import br.com.xofome.xofome.adapters.ProdutoAdapterGrid;
import br.com.xofome.xofome.model.Produto;
import br.com.xofome.xofome.services.ProdutoService;

/**
 * Created by marcosf on 27/10/2016.
 */

public class ProdutoFragment extends Fragment {
    private ProdutoFragment.OnFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    //private GridView gridView;
    private static List<Produto> produtos;
    private String tipo;

    public ProdutoFragment() {
    }

    // TODO: Rename and change types and number of parameters
    public static ProdutoFragment newInstance(String param1, String param2) {
        ProdutoFragment fragment = new ProdutoFragment();
        return fragment;
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
        View vi = (View) inflater.inflate(R.layout.fragment_produtos, container, false);
        //recyclerView = (RecyclerView) vi.findViewById(R.id.gridview);
        //recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.setHasFixedSize(true);
        // gridView = (GridView) vi.findViewById(R.id.gridview);


        try {
            if(this.tipo.equals("comidas")){

                produtos = ProdutoService.getProdutos(getContext(),0);
                //produtos = ProdutoService.getProdutos(getContext(),0);
            }else if(this.tipo.equals("bebidas")){
               produtos = ProdutoService.getProdutos(getContext(),1);
                //produtos = produtoStub;
            }

            //recyclerView.setAdapter(new ProdutoAdapter(getContext().getApplicationContext(), produtos, onClickProduto()));
            //gridView.setAdapter(new ProdutoAdapterGrid(getContext().getApplicationContext(), produtos));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return vi;

    }

    private ProdutoAdapter.ProdutoOnClickListener onClickProduto(){
        return new ProdutoAdapter.ProdutoOnClickListener(){

            @Override
            public void onClickProduto(View view, int idx){
                Produto p = produtos.get(idx);
            }
        };
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ProdutoFragment.OnFragmentInteractionListener) {
            mListener = (ProdutoFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
