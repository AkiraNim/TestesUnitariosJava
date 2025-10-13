package org.example;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppTest{
    private Produto produto;
    private Venda venda;

    @BeforeEach
    public void setUp(){
        produto = new Produto();
        venda = new Venda();
    }

    @Test
    public void deveCriarProdutoComValoresValidos(){
        String nome = "Arroz";
        double preco = 20.0;
        int estoque = 50;

        produto = new Produto(nome, preco, estoque);

        Assertions.assertNotNull(produto);
        Assertions.assertEquals(nome, produto.getNome());
        Assertions.assertTrue(preco >= produto.getPreco());
        Assertions.assertEquals(estoque, produto.getEstoque());
    }

    @Test
    public void naoDeveCriarProdutoComPrecoNegativo(){
        String nome = "Arroz";
        double preco = -20.0;
        int estoque = 50;

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
            produto = new Produto(nome, preco, estoque)
        );
        Assertions.assertEquals("O preço não pode ser negativo.", exception.getMessage());
    }
    @Test
    public void naoDeveCriarProdutoComEstoqueNegativo(){
        String nome = "Arroz";
        double preco = 20.0;
        int estoque = -50;

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
            produto = new Produto(nome, preco, estoque)
        );
        Assertions.assertEquals("O estoque não pode ser negativo.", exception.getMessage());
    }

    @Test
    public void deveAlterarNomeValido(){
        String novoNome = "Arroz";
        produto.setNome(novoNome);
        Assertions.assertEquals(novoNome, produto.getNome());
    }
    @Test
    public void deveAlterarPrecoValido(){
        double novoPreco = 20.30;
        produto.setPreco(novoPreco);
        Assertions.assertEquals(novoPreco, produto.getPreco());
    }
    @Test
    public void deveAlterarEstoqueValido(){
        int novoEstoque = 15;
        produto.setEstoque(novoEstoque);
        Assertions.assertEquals(novoEstoque, produto.getEstoque());
    }
    @Test
    public void naoDeveAlterarPrecoParaValorNegativo(){
        produto = new Produto("Arroize", 20, 10);
        double precoNovo = -20.0;

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            produto.setPreco(precoNovo);
        });
        Assertions.assertEquals("O preço não pode ser negativo.", exception.getMessage());
    }
    @Test
    public void naoDeveVenderMenosQueEstoqueDisponivel(){
        produto = new Produto("Arroz", 20, 10);
        int quantidadeVendida = 5;
        venda = new Venda(produto, quantidadeVendida);
        Assertions.assertTrue(venda.realizarVenda());
        Assertions.assertEquals(5, produto.getEstoque());
    }
    @Test
    public void deveVenderIgualEstoqueDisponivel(){
        produto = new Produto("Arroz", 20, 10);
        int quantidadeVendida = 10;
        venda = new Venda(produto, quantidadeVendida);

        Assertions.assertTrue(venda.realizarVenda());
        Assertions.assertEquals(0, produto.getEstoque());
    }
    @Test
    public void naoDeveVenderMaisQueEstoqueDisponivel(){
        produto = new Produto("Arroz", 20, 10);
        int quantidadeVendida = 15;
        venda = new Venda(produto, quantidadeVendida);

        Assertions.assertFalse(venda.realizarVenda());
        Assertions.assertEquals(10, produto.getEstoque());
    }
    @Test
    public void deveTestarCalculoTotalVenda(){
        produto = new Produto("Arroz", 20, 10);
        int quantidadeVendida = 5;
        venda = new Venda(produto, quantidadeVendida);

        double totalEsperado = 100;

        Assertions.assertEquals(totalEsperado, venda.calcularTotalVenda());
    }
    @Test
    public void deveTestarAumentarEstoqueAposVenda(){
        produto = new Produto("Arroz", 20, 10);
        int quantidadeVendida = 5;
        venda = new Venda(produto, quantidadeVendida);
        Assertions.assertTrue(venda.realizarVenda());

        int estoqueAposVenda = produto.getEstoque();
        int aumentoEstoque = 3;
        produto.aumentarEstoque(aumentoEstoque);

        Assertions.assertEquals(estoqueAposVenda + 3, produto.getEstoque());
    }
    @Test
    public void deveVerificarDiminuicaoEstoqueAposVenda(){
        produto = new Produto("Arroz", 20, 10);
        int estoqueInicial = produto.getEstoque();
        int quantidadeVendida = 5;
        venda = new Venda(produto, quantidadeVendida);
        Assertions.assertTrue(venda.realizarVenda());

        Assertions.assertEquals(estoqueInicial - 5, produto.getEstoque());
    }
    @Test
    public void naoDeveRealizarVendaDeProdutoNulo(){
        venda.setProduto(null);
        venda.setQuantidadeVendida(5);

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
            venda.realizarVenda()
        );
        Assertions.assertEquals("O produto não pode ser nulo.", exception.getMessage());
    }
    @Test
    public void naoDeveCriarVendaComQuantidadeNegativa(){
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
            venda = new Venda(produto, -5)
        );
        Assertions.assertEquals("A quantidade vendida não pode ser negativa.", exception.getMessage());
    }
    @Test
    public void naoDeveAlterarEstoqueVendaQuantidadeMaiorEstoque(){
        produto = new Produto("Arroz", 20, 10);
        int estoqueInicial = produto.getEstoque();
        int quantidadeVendida = 15;
        venda = new Venda(produto, quantidadeVendida);

        Assertions.assertFalse(venda.realizarVenda());
        Assertions.assertEquals(estoqueInicial, produto.getEstoque());
    }
    @Test
    public void deveCriarVariosProdutosEVenderComEstoqueIndependente() {
        Produto arroz = new Produto("Arroz", 10.0, 20);
        Produto feijao = new Produto("Feijão", 8.0, 15);

        Venda vendaArroz = new Venda(arroz, 5);
        Venda vendaFeijao = new Venda(feijao, 10);

        assertTrue(vendaArroz.realizarVenda());
        assertTrue(vendaFeijao.realizarVenda());

        Assertions.assertEquals(15, arroz.getEstoque());
        Assertions.assertEquals(5, feijao.getEstoque());
    }
    @Test
    public void deveTestarCalularTotalVendaAposAjustarPrecoProduto(){
        produto = new Produto("Arroz", 20, 10);
        int quantidadeVendida = 5;
        venda = new Venda(produto, quantidadeVendida);
        double novoPreco = 25.0;
        produto.setPreco(novoPreco);

        Assertions.assertEquals(125.0, venda.calcularTotalVenda());
    }
    @Test
    public void naoDeveVenderQuandoEstoqueZero() {
        Produto produto = new Produto("Arroz", 10.0, 0);
        Venda venda = new Venda(produto, 1);

        Assertions.assertFalse(venda.realizarVenda());
        Assertions.assertEquals(0, produto.getEstoque());
    }
    @Test
    public void devePermitirVendaAposReposicaoDeEstoque() {
        Produto produto = new Produto("Arroz", 10.0, 0);
        Venda venda = new Venda(produto, 2);


        Assertions.assertFalse(venda.realizarVenda());
        Assertions.assertEquals(0, produto.getEstoque());


        produto.aumentarEstoque(5);


        Assertions.assertTrue(venda.realizarVenda());
        Assertions.assertEquals(3, produto.getEstoque());
    }
    @Test
    void deveExecutarMainSemErros() {
        Assertions.assertDoesNotThrow(() -> App.main(new String[]{}));
    }
}
