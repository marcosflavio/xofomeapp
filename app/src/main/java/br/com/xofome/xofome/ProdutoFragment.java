package br.com.xofome.xofome;

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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.xofome.xofome.adapters.ProdutoAdapter;
import br.com.xofome.xofome.model.Produto;
import br.com.xofome.xofome.services.ProdutoService;

/**
 * Created by marcosf on 27/10/2016.
 */

public class ProdutoFragment extends Fragment {
    private ProdutoFragment.OnFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    private static List<Produto> produtos;
    private String tipo;
    public ProdutoFragment() {
    }

    // TODO: Rename and change types and number of parameters
    public static ProdutoFragment newInstance(String param1, String param2) {
        ProdutoFragment fragment = new ProdutoFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);

//        fragment.setArguments(args);
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
        recyclerView = (RecyclerView) vi.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        List<Produto> produtoStub = new ArrayList<Produto>();
        Produto p1 = new Produto("Baião", 50.0f, "Baião saboroso", 0);
        Produto p2 = new Produto("Galeto", 50.0f, "Galeto saboroso", 0);
        Produto p3 = new Produto("Farofa", 50.0f, "Farofa saborosa", 0);

        Produto p4 = new Produto("Baião2", 50.0f, "Baião saboroso2", 1);
        Produto p5 = new Produto("Galeto2", 50.0f, "Galeto saboroso2", 1);
        Produto p6 = new Produto("Farofa2", 50.0f, "Farofa saborosa2", 1);

        produtoStub.add(p1);
        produtoStub.add(p2);
        produtoStub.add(p3);
        produtoStub.add(p4);
        produtoStub.add(p5);
        produtoStub.add(p6);

        //try {
            if(this.tipo.equals("comidas")){

                produtos = produtoStub;
                //produtos = ProdutoService.getProdutos(getContext(),0);
            }else if(this.tipo.equals("bebidas")){
               //produtos = ProdutoService.getProdutos(getContext(),1);
                produtos = produtoStub;
            }

            recyclerView.setAdapter(new ProdutoAdapter(getContext().getApplicationContext(), produtos, onClickProduto()));
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}

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
