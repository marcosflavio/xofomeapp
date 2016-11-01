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
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import br.com.xofome.xofome.R;
import br.com.xofome.xofome.adapters.ProdutoAdapter;
import br.com.xofome.xofome.model.Produto;
import br.com.xofome.xofome.services.ProdutoService;

/**
 * Created by marcosf on 29/10/2016.
 */

public class ProdutoFragment extends Fragment {
    private ProdutoFragment.OnFragmentInteractionListener mListener;
    private static List<Produto> produtos;
    private static List<Produto> comidas;
    private static List<Produto> bebidas;
    private String tipo;



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
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_blank, container, false);
        ProdutoAdapter adapter = null;
        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.rv_recycler_view);
        rv.setHasFixedSize(true);

        try {
            if(this.tipo.equals("comidas")){

                comidas = ProdutoService.getProdutos(getContext(),0);
//                if(produtos.size() > 0)
//                    produtos.clear();

               // produtos = ProdutoService.getProdutos(getContext(),0);
                adapter = new ProdutoAdapter(this.getContext(),comidas, onClickProduto());
            }else if(this.tipo.equals("bebidas")){
//                if(produtos.size() > 0)
//                    produtos.clear();
                bebidas = ProdutoService.getProdutos(getContext(),1);
                // produtos = ProdutoService.getProdutos(getContext(),1);
                adapter = new ProdutoAdapter(this.getContext(),bebidas, onClickProduto());
            }

            rv.setAdapter(adapter);

            LinearLayoutManager llm = new LinearLayoutManager(getActivity());
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
            rv.setLayoutManager(llm);


         } catch (IOException e) {
            e.printStackTrace();
        }
        return rootView;
    }

    private ProdutoAdapter.ProdutoOnClickListener onClickProduto(){

        return new ProdutoAdapter.ProdutoOnClickListener(){

            @Override
            public void onClickProduto(View view, int idx){
                //Erro aqui
                //Como faço pra pegar a lista de bebidas qnd eu estiver na tab bebidas?
                //Como faço para pegar a lista de comidas qnd eu estiver na tab comidas?

                if(tipo.equals("comidas")){
                    Produto p = comidas.get(idx);
                    Toast.makeText(getContext(),"Produto " + p.getNomeProduto() + "Comidas", Toast.LENGTH_SHORT).show();

                }else if(tipo.equals("bebidas")){
                    Produto p = bebidas.get(idx);
                    Toast.makeText(getContext(),"Produto " + p.getNomeProduto() + "Bebidas", Toast.LENGTH_SHORT).show();
                }

            }
        };
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}