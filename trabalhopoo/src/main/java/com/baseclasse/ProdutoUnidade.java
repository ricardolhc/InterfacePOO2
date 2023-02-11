package com.baseclasse;

/**
* A classe ProdutoUnidade modela um produto unitario do sistema.
* @author Ricardo, Tales, Mateus, Mauricio
* @since jan 2023
* @version 1.0
*/
public class ProdutoUnidade extends Produto {
    
    /**
    * Construtor da classe ProdutoUnidade.
    * @param nome Nome do produto.
    * @param preco Preco do produto.
    * @param quantidade Quantidade do produto.
    * @param descricao Descricao do produto.
    */
    public ProdutoUnidade(String nome, double preco, int quantidade, String descricao) {
        super(nome, preco, quantidade, descricao);
    } 

    /**
    * Segundo construtor da classe ProdutoUnidade.
    * @param nome Nome do produto.
    * @param preco Preco do produto.
    * @param quantidade Quantidade do produto.
    * @param descricao Descricao do produto.
    */
    public ProdutoUnidade(String nome, double preco, double quantidade, String descricao) {
        super(nome, preco, quantidade, descricao);

        try {
            int quantidadeInt = (int) quantidade;

            if (quantidadeInt != quantidade) {
                throw new Exception("Quantidade inválida");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
    @param quantidade double que identifica a quantidade do item
    */
    public void setQuantidade(int quantidade) { super.setQuantidade(quantidade); }

    /** 
     * @return String que identifica as informações do produto
     */
    @Override
    public String toString() { return super.toString(); }

}