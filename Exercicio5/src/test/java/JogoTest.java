import Models.Jogador;
import Models.Jogo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class JogoTest {
    @InjectMocks
    Jogo jogo;

    @Mock
    Jogador jogador;

    @Test
    public void testarSpellCastada(){
        Mockito.when(jogador.getSpell()).thenReturn("Fireball");
        assertEquals("O jogador X castou a spell: Fireball", jogo.castSpell());
    }

    @Test
    public void testarIncializacaoDoJogo(){
        this.jogo.iniciarJogo();

        Mockito.verify(jogador, Mockito.times(1)).realizarAcao();
    }
}
