package br.com.pcsist.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.pcsist.helloworld.adapters.ListaPersonalizadaAdapter;
import br.com.pcsist.helloworld.dao.LembreteDAO;
import br.com.pcsist.helloworld.modelo.Lembrete;

public class MainActivity extends AppCompatActivity {

    private ListView listaLembretes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaLembretes = (ListView) findViewById(R.id.lista_lembretes);
        carregaLista();

        Button botao_novo_aluno = (Button) findViewById(R.id.botao_novo_lembrete);
        botao_novo_aluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vaiProformulario = new Intent(MainActivity.this, FormularioActivity.class);
                startActivity(vaiProformulario);
            }
        });
        registerForContextMenu(listaLembretes);
    }

    @Override
    public void onCreateContextMenu(final ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem deletar = menu.add("Excluir lembrete");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                AdapterView.AdapterContextMenuInfo info =(AdapterView.AdapterContextMenuInfo) menuInfo;
                Lembrete lembreteExcluir = (Lembrete) listaLembretes.getItemAtPosition(info.position);
                LembreteDAO dao = new LembreteDAO(MainActivity.this);
                dao.remove(lembreteExcluir);
                dao.close();
                carregaLista();
                return false;
            }
        });
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    protected void onResume() {
        carregaLista();
        super.onResume();
    }

    private void carregaLista() {

        LembreteDAO dao = new LembreteDAO(this);
        List<Lembrete> lembretes = dao.buscaLembretes();
        dao.close();
        //converte o array de string pra view
       // ArrayAdapter adapter = new ArrayAdapter<Lembrete>(this, android.R.layout.simple_list_item_1, lembretes);
        ListaPersonalizadaAdapter adapter = new ListaPersonalizadaAdapter(lembretes, MainActivity.this);
        //seta na listaLembretes as views dos alunos
        listaLembretes.setAdapter(adapter);
    }
}
