package br.com.pcsist.helloworld;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import br.com.pcsist.helloworld.dao.LembreteDAO;

public class FormularioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.botao_confirmar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.botao_confirmar:
                LembreteDAO dao = new LembreteDAO(FormularioActivity.this);
                EditText campoLembrete = (EditText) findViewById(R.id.formulario_lembrete);
                dao.insere(campoLembrete.getText().toString());
                dao.close();
                Toast.makeText(FormularioActivity.this, "Lembrete salvo com sucesso", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
