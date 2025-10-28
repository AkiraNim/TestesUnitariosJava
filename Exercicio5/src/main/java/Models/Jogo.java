package Models;

public class Jogo {
    private final Jogador jogador;

    public Jogo(Jogador jogador) {
        this.jogador = jogador;
    }

    public String castSpell(){
        return "O jogador X castou a spell: " + jogador.getSpell();
    }

    public void iniciarJogo(){
        System.out.println("O jogo comecou!");
        jogador.realizarAcao();
    }
}
