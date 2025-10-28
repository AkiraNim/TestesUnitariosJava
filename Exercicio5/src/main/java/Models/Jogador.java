package Models;

public class Jogador {
    private String spell;

    public Jogador(String spell) {
        this.spell = spell;
    }

    public String getSpell() {
        return spell;
    }

    public void realizarAcao(){
        System.out.println("O jogador realizou uma ação legal");
    }
}
