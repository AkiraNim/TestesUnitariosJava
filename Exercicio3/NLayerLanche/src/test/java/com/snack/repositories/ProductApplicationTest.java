package com.snack.repositories;

import com.snack.applications.ProductApplication;
import com.snack.entities.Product;
import com.snack.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ProductApplicationTest {
    ProductService productService;
    ProductRepository productRepository;
    ProductApplication productApplication;
    Product product1;
    Product product2;

    @BeforeEach
    public void setup() {
        productService = new ProductService();
        productRepository = new ProductRepository();
        productApplication = new ProductApplication(productRepository, productService);

        product1 = new Product(1, "Hot Dog", 10.4f, "C:\\Users\\aluno.fsa\\Desktop\\Ayanami Programas\\TestesUnitariosJava\\Exercicio3\\NLayerLanche\\imagensOriginais\\grassFloor1.png");
        product2 = new Product(2, "Hot Doggerson", 15.0f, "C:\\Users\\aluno.fsa\\Desktop\\Ayanami Programas\\TestesUnitariosJava\\Exercicio3\\NLayerLanche\\imagensOriginais\\grassFloor1.png");
    }

    @Test
    public void deveTestarListarProdutosDoRepositorio(){
        productApplication.append(product1);
        productApplication.append(product2);

        List<Product> productList = productApplication.getAll();

        assertEquals(2, productList.size());
        assertTrue(productList.contains(product1));
        assertTrue(productList.contains(product2));
    }

    @Test
    public void deveObterUmProdutoPorId(){
        productApplication.append(product1);

        assertTrue(productApplication.exists(product1.getId()));
    }

    @Test
    public void deveRetornarNuloAoProdutoIdNulo(){
        productApplication.append(product1);

        productApplication.remove(product1.getId());

        assertThrows(NoSuchElementException.class, () ->
                productApplication.getById(1));
    }

    @Test
    public void deveVerificarSeProdutoExistePorId(){
        productApplication.append(product1);

        assertTrue(productApplication.exists(product1.getId()));
    }

    @Test
    public void deveVerificarSeProdutoNaoExistePorId(){
        productApplication.append(product1);

        assertFalse(productApplication.exists(2));
    }

    @Test
    public void deveAdicionarProdutoESalvarImagem(){
        productApplication.append(product1);

        assertTrue(productService.save(product1));
        assertEquals("C:\\Users\\aluno.fsa\\Desktop\\Ayanami Programas\\TestesUnitariosJava\\Exercicio3\\NLayerLanche\\imagens\\1.png", product1.getImage());
    }

    @Test
    public void verificarRemoverUmProdutoExistente(){
        productApplication.append(product1);

        productApplication.remove(product1.getId());

        assertThrows(NoSuchElementException.class,() ->{productApplication.getById(product1.getId());});
    }

    @Test
    public void verificarRemoverUmProdutoInexistente(){
        productApplication.append(product1);

        productApplication.remove(1);

        assertThrows(NoSuchElementException.class, () -> productApplication.remove(1));

    }

    @Test
    public void verificarAtualizarUmProdutoExistente(){
        productApplication.append(product1);

        Product productAtualizado = new Product(1, "Hot Dognelson", 10.4f, "C:\\Users\\aluno.fsa\\Desktop\\Ayanami Programas\\TestesUnitariosJava\\Exercicio3\\NLayerLanche\\imagensOriginais\\grassFloor1.png");
        productApplication.update(productAtualizado.getId(), productAtualizado);

        File arquivo = new File(productService.getImagePathById(1));
        assertTrue(arquivo.exists());

    }
}
