package br.com.pcsist.helloworld.modelo;

/**
 * Created by Gustavo Vinicius on 24/10/2017.
 */

public class Lembrete {

    private Long id;
    private String lembrete;
    private String data;
    private String hora;


    public String getLembrete() {
        return lembrete;
    }

    public void setLembrete(String lembrete) {
        this.lembrete = lembrete;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id+ " " +data + " "+ hora + " - " + lembrete;
    }
}
