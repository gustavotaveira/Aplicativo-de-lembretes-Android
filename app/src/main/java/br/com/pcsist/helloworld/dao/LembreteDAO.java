package br.com.pcsist.helloworld.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.pcsist.helloworld.FormularioActivity;
import br.com.pcsist.helloworld.modelo.Lembrete;

/**
 * Created by Gustavo Vinicius on 24/10/2017.
 */

public class LembreteDAO extends SQLiteOpenHelper{


    public LembreteDAO(Context context) {
        super(context, "lembretes_database", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table if not exists lembretes (id integer primary key, lembrete text, data text, horario text)";
        sqLiteDatabase.execSQL(sql);
    }

    //chama esse metodo quando a aplicacao esta desatualizada em relacao ao banco de dados
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "drop table if exists lembretes";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    public void insere(String lembrete) {
        SQLiteDatabase db = getWritableDatabase();

        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        long data = System.currentTimeMillis();
        String dataString = formatador.format(data);

        formatador = new SimpleDateFormat("HH:mm:ss");
        java.util.Date hora = Calendar.getInstance().getTime();
        String horario = formatador.format(hora);

        ContentValues values= new ContentValues();
        values.put("lembrete", lembrete);
        values.put("data", dataString);
        values.put("horario", horario);

        db.insert("lembretes", null, values);
    }

    public List<Lembrete> buscaLembretes() {
        String sql = "select * from lembretes";
        List<Lembrete> lembretes = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        while(cursor.moveToNext()){
            Lembrete lembrete = new Lembrete();
            lembrete.setId(cursor.getLong(cursor.getColumnIndex("id")));
            lembrete.setLembrete(cursor.getString(cursor.getColumnIndex("lembrete")));
            lembrete.setData(cursor.getString(cursor.getColumnIndex("data")));
            lembrete.setHora(cursor.getString(cursor.getColumnIndex("horario")));
            lembretes.add(lembrete);
        }
        return lembretes;
    }

    public void remove(Lembrete lembreteExcluir) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {String.valueOf(lembreteExcluir.getId())};
        db.delete("lembretes", "id = ?", params);
    }
}
