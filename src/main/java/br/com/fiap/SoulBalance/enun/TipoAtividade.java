package br.com.fiap.SoulBalance.enun;

public enum TipoAtividade {

    TRABALHO_FOCO("Trabalho de Alto Foco"),
    TRABALHO_CRIATIVO("Trabalho Criativo/Soft Skill"),
    ESTUDO_APRENDIZADO("Estudo e Aprendizado Formal"),

    // Atividades relacionadas à Recuperação/Bem-Estar
    PAUSA_ATIVA("Pausa Ativa (ex: alongamento)"),
    DESCANSO_PASSIVO("Descanso Passivo (ex: cochilo, ócio)"),
    LAZER_SOCIAL("Lazer e Interação Social"),
    MEDITACAO_MINDFULNESS("Meditação e Mindfulness"),
    EXERCICIO_FISICO("Exercício Físico Moderado/Intenso");

    private final String descricao;

    TipoAtividade(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
