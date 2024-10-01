package br.inatel.Model;

public class Feitico {
    private int idFeitico;
    private String nome;
    private String tipo;
    private String efeito;
    private int dano;

    public Feitico(int idFeitico, String nome, String tipo, String efeito, int dano) {
        this.idFeitico = idFeitico;
        this.nome = nome;
        this.tipo = tipo;
        this.efeito = efeito;
        this.dano = dano;
    }

    public int getDano() {
        return dano;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public String getEfeito() {
        return efeito;
    }

    public int getIdFeitico() {
        return idFeitico;
    }
}
