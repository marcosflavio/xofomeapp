package br.com.xofome.xofome.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import br.com.xofome.xofome.R;
import br.com.xofome.xofome.adapters.ProdutoAdapter;
import br.com.xofome.xofome.constantes.Keys;
import br.com.xofome.xofome.model.Produto;
import br.com.xofome.xofome.services.ProdutoService;

/**
 * Created by marcosf on 29/10/2016.
 */

public class ProdutoFragment extends Fragment {
    private ProdutoFragment.OnFragmentInteractionListener mListener;
    private static List<Produto> comidas;
    private static List<Produto> bebidas;
    private String tipo;
    protected SwipeRefreshLayout swipeRefreshLayout;


    public ProdutoFragment() {
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

        View rootView = inflater.inflate(R.layout.fragment_produtos, container, false);
        ProdutoAdapter adapter = null;
        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.rv_recycler_view);
        rv.setHasFixedSize(true);

        try {
            if (this.tipo.equals("comidas")) {
                comidas = ProdutoService.getProdutos(getContext(), 0);
                adapter = new ProdutoAdapter(this.getContext(), comidas, onClickProduto());
            } else if (this.tipo.equals("bebidas")) {
                bebidas = ProdutoService.getProdutos(getContext(), 1);
                adapter = new ProdutoAdapter(this.getContext(), bebidas, onClickProduto());
            }

            rv.setAdapter(adapter);

            LinearLayoutManager llm = new LinearLayoutManager(getActivity());
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
            rv.setLayoutManager(llm);
            swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe);
            swipeRefreshLayout.setOnRefreshListener(OnRefreshListener());
            swipeRefreshLayout.setColorSchemeResources(R.color.refresh_progress_1,R.color.refresh_progress_2,
                    R.color.refresh_progress_3);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return rootView;
    }


    private SwipeRefreshLayout.OnRefreshListener OnRefreshListener(){
        return  new SwipeRefreshLayout.OnRefreshListener(){

            @Override
            public void onRefresh() {
                getActivity().sendBroadcast(new Intent("UPDATE_LIST"));
                swipeRefreshLayout.setRefreshing(false);
            }
        };
    }

    private ProdutoAdapter.ProdutoOnClickListener onClickProduto() {

        return new ProdutoAdapter.ProdutoOnClickListener() {

            @Override
            public void onClickProduto(View view, int idx) {
                if (tipo.equals("comidas")) {
                    Produto p = comidas.get(idx);
                    Toast.makeText(getContext(), "Produto " + p.getNomeProduto() + "Comidas", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(getContext(), DescricaoProdutoActivity.class);
                    i.putExtra(Keys.REQUEST_DETALHES,p.getIdProduto());
                    startActivity(i);

                } else if (tipo.equals("bebidas")) {
                    Produto p = bebidas.get(idx);
                    Toast.makeText(getContext(), "Produto " + p.getNomeProduto() + "Bebidas", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(getContext(), DescricaoProdutoActivity.class);
                    i.putExtra(Keys.REQUEST_DETALHES,p.getIdProduto());
                    startActivity(i);
                }

            }
        };
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}