package org.example;

public class Venda {
    private Produto produto;
    private int quantidadeVendida;
    private double totalVenda;

    public Venda(Produto produto, int quantidadeVendida) {

        if (quantidadeVendida < 0) {
            throw new IllegalArgumentException("A quantidade vendida não pode ser negativa.");
        }
        this.produto = produto;
        this.quantidadeVendida = quantidadeVendida;
    }

    public Venda() {
    }

    public boolean realizarVenda() {
        if (produto  == null) {
            throw new IllegalArgumentException("O produto não pode ser nulo.");
        }
        if (produto.diminuirEstoque(quantidadeVendida)) {
            totalVenda = calcularTotalVenda();
            return true;
        }
        return false;
    }

    public double calcularTotalVenda() {
        return produto.getPreco() * quantidadeVendida;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setQuantidadeVendida(int quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }
}
