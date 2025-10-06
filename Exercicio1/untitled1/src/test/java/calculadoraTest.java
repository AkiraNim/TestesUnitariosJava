import org.example.Calculadora;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class calculadoraTest {
    private Calculadora calculadora;
    private double valorA= 10, valorB = 5;
    private double resultado;

    @BeforeEach
    public void setUp(){
        calculadora = new Calculadora();
    }

    @Test
    public void deveSomarDoisValores(){
        resultado = calculadora.somar(valorA, valorB);
        Assertions.assertEquals(15, resultado);
    }
    @Test
    public void deveSubtrairDoisValores(){
        resultado = calculadora.subtrair(valorA, valorB);
        Assertions.assertEquals(5, resultado);
    }
    @Test
    public void deveMultiplicarDoisValores(){
        resultado = calculadora.multiplicar(valorA, valorB);
        Assertions.assertEquals(50, resultado);
    }
    @Test
    public void deveDividirDoisValores(){
        resultado = calculadora.dividir(valorA, valorB);
        Assertions.assertEquals(2, resultado);
    }
}
