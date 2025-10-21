package com.snack.repositories;

import com.snack.applications.ProductApplication;
import com.snack.entities.Product;
import com.snack.facade.ProductFacade;
import com.snack.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ProductFacadeTest {
    ProductService productService;
    ProductRepository productRepository;
    ProductApplication productApplication;
    ProductFacade productFacade;
    Product product1;
    Product product2;

    @BeforeEach
    public void setup() {
        productService = new ProductService();
        productRepository = new ProductRepository();
        productApplication = new ProductApplication(productRepository, productService);
        productFacade = new ProductFacade(productApplication);

        product1 = new Product(1, "Hot Dog Simples", 10.4f, "C:\\Users\\aluno\\Produtos\\HotDog.jpg");
        product2 = new Product(2, "Hot Dog Monstro", 15.0f, "C:\\Users\\aluno\\Produtos\\HotDog2.jpg");
    }

    @Test
    public void verificarListagemDeTodosOsProdutos() {
        productFacade.append(product1);
        productFacade.append(product2);

        List<Product> produtos = productFacade.getAll();

        assertEquals(2, produtos.size());
        assertTrue(produtos.contains(product1));
        assertTrue(produtos.contains(product2));
    }

    @Test
    public void verificarObtencaoDeProdutoValidoPorId() {
        productFacade.append(product1);

        Product productId1 = productFacade.getById(product1.getId());

        assertEquals(productId1.getId(), product1.getId());
        assertEquals(productId1.getDescription(), product1.getDescription());
        assertEquals(productId1.getPrice(), product1.getPrice());

        File arquivo = new File(productService.getImagePathById(1));
        assertTrue(arquivo.exists());


    }

    @Test
    public void verificarObtencaoDeProdutoInvalidoPorId() {
        productFacade.append(product1);

        assertThrows(NoSuchElementException.class,() ->{
            productFacade.getById(2);
        });
    }

    @Test
    public void verificarRemoverUmProdutoExistente(){
        productFacade.append(product1);

        productFacade.remove(product1.getId());

        assertThrows(NoSuchElementException.class,() ->{productFacade.getById(product1.getId());});
    }

}
