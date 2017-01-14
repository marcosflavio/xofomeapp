package br.com.xofome.xofome.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import br.com.xofome.xofome.R;
import br.com.xofome.xofome.dao.UsuarioDAO;
import br.com.xofome.xofome.model.Usuario;
import br.com.xofome.xofome.services.UsuarioService;
import br.com.xofome.xofome.tasks.SaveUsuario;

public class CadastrarEmailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_email);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarEmail);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.add_email) {
            EditText email = (EditText) findViewById(R.id.editTextCadastrarEmail);
            Usuario u = new Usuario();
            u.setEmail(email.getText().toString());
            UsuarioService service = new UsuarioService(getApplicationContext());
            service.save(u);
            SaveUsuario taskSaveUsuario = new SaveUsuario(getApplicationContext());
            taskSaveUsuario.execute(u);

            Intent i = new Intent(getApplicationContext(), ListarProdutosActivity.class);
            startActivity(i);

            return true;
        }else if (id == R.id.cancel_email){
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cadastrar_email, menu);
        return true;
    }

}
