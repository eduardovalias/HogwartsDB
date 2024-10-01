package br.inatel.Model;

public class Varinha {
    private int idVarinha;
    private String nucleo;
    private String madeira;

    public Varinha(int idVarinha, String nucleo, String madeira) {
        this.idVarinha = idVarinha;
        this.nucleo = nucleo;
        this.madeira = madeira;
    }

    public int getIdVarinha() {
        return idVarinha;
    }

    public String getNucleo() {
        return nucleo;
    }

    public String getMadeira() {
        return madeira;
    }
}