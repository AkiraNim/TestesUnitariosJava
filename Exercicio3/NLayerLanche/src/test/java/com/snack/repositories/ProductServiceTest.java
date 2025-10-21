package com.snack.repositories;

import com.snack.entities.Product;
import com.snack.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceTest {

    private ProductService productService;
    private Product product1;

    @BeforeEach
    public void setup() {
        productService = new ProductService();
        product1 = new Product(1, "Hot Dog", 10.4f, "C:\\Users\\Akira\\Desktop\\Atividades\\TestesUnitariosJava\\Exercicio3\\NLayerLanche\\imagensOriginais\\grassFloor1.png");
    }

    @Test
    public void testarSalvarProdutoComImagemValida(){
        product1.setImage("C:\\Users\\Akira\\Desktop\\Atividades\\TestesUnitariosJava\\Exercicio3\\NLayerLanche\\imagensOriginais\\grassFloor1.png");

        boolean result = productService.save(product1);

        assertTrue(result);
    }

    @Test
    public void testarSalvarProdutoComImagemInvalida(){
        product1.setImage("C:\\Users\\Akira\\Desktop\\Atividades\\TestesUnitariosJava\\Exercicio3\\NLayerLanche\\imagensOriginais\\grassFloor2.png");

        boolean result = productService.save(product1);

        assertFalse(result);
    }


    @Test
    public void obterCaminhoDaImagemPorId(){
        product1.setImage("C:\\Users\\Akira\\Desktop\\Atividades\\TestesUnitariosJava\\Exercicio3\\NLayerLanche\\imagensOriginais\\grassFloor1.png");
        productService.save(product1);

        String imagePath = productService.getImagePathById(product1.getId());

        assertEquals("C:\\Users\\Akira\\Desktop\\Atividades\\TestesUnitariosJava\\Exercicio3\\NLayerLanche\\imagensOriginais\\grassFloor1.png", imagePath);
    }
}
