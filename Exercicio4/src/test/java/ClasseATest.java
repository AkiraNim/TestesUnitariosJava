import Models.ClasseA;
import Models.ClasseB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ClasseATest {
    @InjectMocks
    ClasseA classeA;

    @Mock
    ClasseB classeB;

    @BeforeEach
    public void setUp(){

    }

    @Test
    public void testarSomaDosValores(){
        Mockito.when(classeB.getX()).thenReturn(20);
        assertEquals(25, classeA.somarValores());
    }
}
