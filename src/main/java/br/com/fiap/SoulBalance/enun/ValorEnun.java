package br.com.fiap.SoulBalance.enun;

public enum ValorEnun {

    MUITO_BAIXO(1, "Muito Baixo"),
    BAIXO(2, "Baixo"),
    MEDIO(3, "Médio"),
    ALTO(4, "Alto"),
    MUITO_ALTO(5, "Muito ALto");


    private final int valor;

    private final String descricao;

    ValorEnun(int valor, String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }

    public int getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }
}
