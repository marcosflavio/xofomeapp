package br.com.xofome.xofome;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import br.com.xofome.xofome.constantes.Codes;

public class MainActivity extends AppCompatActivity implements ProdutoFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        replaceFragment(new ProdutoTabFragment());
    }


    // Adiciona o fragment no centro da tela
    private void replaceFragment(Fragment frag) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frag, frag, "TAG").commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.search) {
            Intent intent = new Intent(this,ActivityAdd.class);
            startActivityForResult(intent, Codes.REQUEST_ADD);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}