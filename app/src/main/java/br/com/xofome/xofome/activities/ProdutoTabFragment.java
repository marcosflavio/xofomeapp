package br.com.xofome.xofome.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.xofome.xofome.R;
import br.com.xofome.xofome.adapters.TabsAdapter;

/**
 * Created by marcosf on 27/10/2016.
 */

public class ProdutoTabFragment extends Fragment implements TabLayout.OnTabSelectedListener {

    private ViewPager mViewPager;
    private TabLayout tabLayout;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.produto_tab_fragment,container,false);
        //ViewPager
        mViewPager = (ViewPager) view.findViewById(R.id.viewPagerProduto);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(new TabsAdapter(getContext(), getChildFragmentManager()));

        //Tabs
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayoutProdutos);
        tabLayout.setTabTextColors(R.color.colorPrimaryDark, R.color.colorPrimaryDark);

        //Adiciona as tabs
        tabLayout.addTab(tabLayout.newTab().setText(R.string.comidas));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.bebidas));
        tabLayout.setupWithViewPager(mViewPager);
        //listener para tratar eventos de clique na tab
        tabLayout.setOnTabSelectedListener(this);
        //se mudar, o viewpager atualiza a tab selecionada
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        return view;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        //Se alterar a tab, atualiza o ViewPager
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }
}
