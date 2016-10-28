package br.com.xofome.xofome.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import br.com.xofome.xofome.activities.ProdutoFragment;
import br.com.xofome.xofome.R;

/**
 * Created by marcosf on 27/10/2016.
 */

public class TabsAdapter extends FragmentPagerAdapter {
    private Context context;
    public TabsAdapter(Context context,FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        if(position == 0){
            return context.getString(R.string.comidas);
        }
        return context.getString(R.string.bebidas);
    }

    @Override
    public Fragment getItem(int position) {
        Bundle args = new Bundle();
        if(position == 0){
            args.putString("tipo", "comidas");
        }else{
            args.putString("tipo", "bebidas");
        }
        Fragment f = new ProdutoFragment();
        f.setArguments(args);
        return f;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
