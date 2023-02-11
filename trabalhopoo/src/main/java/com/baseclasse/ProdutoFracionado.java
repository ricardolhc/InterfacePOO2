package com.baseclasse;

/**
 * A classe ProdutoFraionado modela um produto fracionado do sistema.
 * @author Ricardo, Tales, Mateus, Mauricio
 * @since jan 2023
 * @version 1.0
 */
public class ProdutoFracionado extends Produto {
    
    /**
     * Construtor da classe ProdutoFracionado.
     * @param nome Nome do produto.
     * @param preco Preço do produto.
     * @param quantidade Quantidade do produto.
     * @param descricao Descrição do produto.
     */
    public ProdutoFracionado(String nome, double preco, double quantidade, String descricao) {
        super(nome, preco, quantidade, descricao);
    }
    
    /**
    @param quantidade double que identifica a quantidade do item
    */
    public void setQuantidade(double quantidade) { super.setQuantidade(quantidade); }
    
    /** 
     * @return String que identifica as informações do produto
     */
    @Override
    public String toString() { return super.toString(); }
    
}
