package br.com.xofome.xofome;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import br.com.xofome.xofome.activities.ProdutoFragment;

public class MainActivity extends AppCompatActivity implements ProdutoFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

//        if (id == R.id.search) {
//            Intent intent = new Intent(this, AddProdutoActivity.class);
//            startActivityForResult(intent, Codes.REQUEST_ADD);
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }
}