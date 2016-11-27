package br.com.xofome.xofome.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.com.xofome.xofome.R;
import br.com.xofome.xofome.constantes.Codes;
import br.com.xofome.xofome.services.UsuarioService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);
        Button b = (Button) findViewById(R.id.button_cadastrar);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UsuarioService service = new UsuarioService(getApplicationContext());
                if(service.getUser() != 0){

                    Intent i = new Intent(getApplicationContext(), ListarProdutosActivity.class);
                    startActivity(i);

                }else{
                    Intent i = new Intent(getApplicationContext(), CadastrarEmailActivity.class);
                    startActivity(i);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.itemSettings) {
            Toast.makeText(getApplicationContext(), "Opções", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void callCadastrarEndereco(View view) {
        Intent intent = new Intent(this, CadastrarEnderecoActivity.class);
        startActivityForResult(intent, Codes.REQUEST_CADASTAR_END);
    }
}
