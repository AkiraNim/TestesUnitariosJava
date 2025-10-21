package com.snack.repositories;

import com.snack.entities.Product;
import com.snack.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import java.io.File;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceTest {

    private ProductService productService;
    private Product product1;

    @BeforeEach
    public void setup() {
        productService = new ProductService();
        product1 = new Product(1, "Hot Dog", 10.4f, "C:\\Users\\aluno.fsa\\Desktop\\Ayanami Programas\\TestesUnitariosJava\\Exercicio3\\NLayerLanche\\imagensOriginais\\grassFloor1.png");
    }

    @Test
    public void testarSalvarProdutoComImagemValida(){
        product1.setImage("C:\\Users\\aluno.fsa\\Desktop\\Ayanami Programas\\TestesUnitariosJava\\Exercicio3\\NLayerLanche\\imagensOriginais\\grassFloor1.png");

        assertTrue(productService.save(product1));
    }

    @Test
    public void testarSalvarProdutoComImagemInvalida(){
        product1.setImage("C:\\Users\\aluno.fsa\\Desktop\\Ayanami Programas\\TestesUnitariosJava\\Exercicio3\\NLayerLanche\\imagensOriginais\\grassFloor2.png");

        assertFalse(productService.save(product1));
    }


    @Test
    public void verificarAtualizarImagemDoProduto(){
        productService.save(product1);

        Product productAtualizado = new Product(1, "Hot Doggers", 10.4f, "C:\\Users\\aluno.fsa\\Desktop\\Ayanami Programas\\TestesUnitariosJava\\Exercicio3\\NLayerLanche\\imagensOriginais\\grassFloor1.png");
        productService.update(productAtualizado);

        File arquivo = new File(productService.getImagePathById(product1.getId()));
        assertTrue(arquivo.exists());
    }

    @Test
    public void verificarRemoverImagemDoProduto(){
        productService.save(product1);

        productService.remove(product1.getId());

        assertThrows(NoSuchElementException.class,() -> {
            File arquivo = new File(
                    productService.getImagePathById(product1.getId()));
        });

    }

    @Test
    public void obterCaminhoDaImagemPorId(){
        productService.save(product1);

        String imagePath = productService.getImagePathById(product1.getId());

        assertEquals("C:\\Users\\aluno.fsa\\Desktop\\Ayanami Programas\\TestesUnitariosJava\\Exercicio3\\NLayerLanche\\imagens\\1.png", imagePath);

    }
}
