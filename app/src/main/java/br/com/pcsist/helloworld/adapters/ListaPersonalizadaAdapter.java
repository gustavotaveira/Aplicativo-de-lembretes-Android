package br.com.pcsist.helloworld.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.pcsist.helloworld.R;
import br.com.pcsist.helloworld.modelo.Lembrete;

/**
 * Created by Gustavo Vinicius on 24/10/2017.
 */

public class ListaPersonalizadaAdapter extends BaseAdapter {

    private final List<Lembrete> lista;
    private final Activity activity;

    public ListaPersonalizadaAdapter(List<Lembrete> lista, Activity activity){
        this.lista = lista;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int i) {
        return lista.get(i);
    }

    @Override
    public long getItemId(int i) {
        return lista.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       View otherView = activity.getLayoutInflater().inflate(R.layout.lista_personalizada, viewGroup, false);
        Lembrete lembrete = lista.get(i);

        TextView info = (TextView) otherView.findViewById(R.id.listaPersonalizada_info);
        TextView lembreteLista = (TextView) otherView.findViewById(R.id.listaPersonalizada_lembrete);

        String texto = String.format("%d - Em %s as %s:", lembrete.getId(), lembrete.getData(),lembrete.getHora());
        info.setText(texto);
        lembreteLista.setText(lembrete.getLembrete());
        return otherView;
    }
}
