import Models.ClasseA;
import Models.ClasseB;

public class Main {
    public static void main(String[] args) {
        ClasseB classeB = new ClasseB();
        ClasseA classeA = new ClasseA(classeB);
        System.out.println("Resultado: " + classeA.somarValores());
    }
}