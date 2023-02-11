package com.baseclasse;

import com.exceptions.produto.QuantidadeNotSupportedException;

/**
* A classe Item modela um item do sistema.
* @author Ricardo, Tales, Mateus, Mauricio
* @since jan 2023
*/
public class Item {

    /**
    * O atributo produto, do tipo Produto, identifica o produto do item.
    */
    private Produto produto;
    
    /**
    * O atributo codigo, do tipo int, identifica o código do item.
    */
    private int codigo;

    /**
    * O atributo nome, do tipo String, identifica o nome do item.
    */
    private String nome;

    /**
    * O atributo preco, do tipo double, identifica o preço do item.
    */
    private double preco;

    /**
    * O atributo quantidade, do tipo double, identifica a quantidade do item.
    */
    private double quantidade;

    /**
    * O atributo descricao, do tipo String, identifica a descricao do item.
    */
    private String descricao;

    /**
    * Construtor default da classe Item<br>
    * <b>Uso: </b>
    * Item item = new Item(produto, quantidade);<br><br>
    * @param produto Produto que identifica o produto do item
    * @param quantidade double que identifica a quantidade do item
    */
    public Item(Produto produto, double quantidade) {

        if(produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo.");
        }

        if(quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        }

        if(produto instanceof ProdutoUnidade) {
            int quantidadeInt = (int) quantidade;

            if(quantidadeInt != quantidade) {
                throw new QuantidadeNotSupportedException("Quantidade não suportada.");
            }
        }

        this.produto = produto;
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
        this.codigo = produto.getCodigo();
        this.quantidade = quantidade;
        this.descricao = produto.getDescricao();
    }

    /**
    * @return String que identifica o nome do item
    */
    public String getNome() { return nome; }

    /**
    * @return Produto que identifica o produto do item
    */
    public Produto getProduto() { return produto; }

    /**
    * @return double que identifica o preço do item
    */
    public double getPreco() { return preco; }

    /** 
     * @return double que identifica o preço total do item
     */
    public double getPrecoTotal() { return preco * quantidade; }

    /**
    * @return double que identifica a quantidade do item
    */
    public double getQuantidade() { return quantidade; }

    /**
    * @return int que identifica o código do item
    */
    public int getCodigo() { return codigo; }

    /**
    * @return String que identifica a descrição do item
    */
    public String getDescricao() { return descricao;}

    /**
    @param codigo int que identifica o código do item
    */
    public void setCodigo(int codigo) { this.codigo = codigo; }

    /**
    @param nome String que identifica o nome do item
    */
    public void setNome(String nome) { this.nome = nome; }

    /**
    @param preco double que identifica o preço do item
    */
    public void setPreco(double preco) { this.preco = preco; }

    /**
    @param produto Produto que identifica o produto do item
    */
    public void setProduto(Produto produto) { this.produto = produto; }

    /**
    @param quantidade double que identifica a quantidade do item
    */
    public void setQuantidade(double quantidade) { this.quantidade = quantidade; }

     /**
    @param descricao String que identifica a descricao do item
    */
    public void setDescricao(String descricao) { this.descricao = descricao; }

    /** 
     * @return String que identifica as informações do item
     */
    @Override
    public String toString() {
        return produto.toString() + ";" + 
               " Quantidade: " + quantidade + ";";
    }
}