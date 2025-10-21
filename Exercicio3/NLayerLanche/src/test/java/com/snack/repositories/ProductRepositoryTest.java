package com.snack.repositories;

import com.snack.entities.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ProductRepositoryTest {
    private ProductRepository productRepository;
    private Product product1;

    @BeforeEach
    public void setup() {
        productRepository = new ProductRepository();
        product1 = new Product(1, "Hot Dog", 10.4f, "");
    }

    @Test
    public void verificarSeOProdutoEstaNoArray() {
        // Arrange
        productRepository.append(product1);

        // Act
        int id = product1.getId();

        // Assert
        assertTrue(productRepository.exists(id));
    }
    @Test
    public void verificarProdutoPorId(){
        productRepository.append(product1);

        Product productId1 = productRepository.getById(1);

        assertNotNull(productId1);
    }

    @Test
    public void verificarProdutoPorList(){
        productRepository.append(product1);

        List<Product> productList = productRepository.getAll();

        assertTrue(productList.contains(product1));
    }

    @Test
    public void testarSeProdutoFoiRemovido(){
        productRepository.append(product1);

        productRepository.remove(1);
        List<Product> productList = productRepository.getAll();

        assertFalse(productList.contains(product1));
    }

    @Test
    public void verificarSeProdutoFoiAtualizado(){
        productRepository.append(product1);

        Product productAtt = new Product(1, "Hot Dogger", 10.4f, "");
        productRepository.update(product1.getId(), productAtt);


        assertEquals(productAtt.getDescription(), product1.getDescription());
    }

    @Test
    public void testarSeTodosProdutosSaoRecuperados(){
        Product product2 = new Product(2, "Pepsi", 5.00f, "");
        productRepository.append(product1);
        productRepository.append(product2);

        List<Product> productList = productRepository.getAll();

        assertNotNull(productList);
        assertEquals(2, productList.size());
        assertTrue(productList.contains(product1));
        assertTrue(productList.contains(product2));
    }

    @Test
    public void verificarComportamentoAoTentarRemoverNaoExistente(){
        productRepository.append(product1);

        productRepository.remove(product1.getId());

        assertFalse(productRepository.remove(product1.getId()));
    }

    @Test
    public void verificarComportamentoAoTentarAtualizarNaoExistente(){
        int idNaoExiste = 1;

        Product productAtt = new Product(idNaoExiste, "Hot Dogger", 10.4f, "");

        assertThrows(NoSuchElementException.class, () -> productRepository.update(product1.getId(), productAtt));
    }

    @Test
    public void verificarComportamentoAoTentarAdicaoIdDuplicado(){
        productRepository.append(product1);

        Product product2 = new Product(1, "Pepsi", 5.00f, "");
        productRepository.append(product2);

        List<Product> productList = productRepository.getAll();

        assertEquals(2, productList.size(), "O repositório não deveria ter adicionado o segundo produto.");
    }

    @Test
    public void confirmarQueRepositorioRetornaListaVaziaAoIniciar(){
        List<Product> productList = productRepository.getAll();

        assertTrue(productList.isEmpty());
    }
}
