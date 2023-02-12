package com.baseclasse;

/**
 * A classe Produto modela um produto do sistema.
 * @author Ricardo, Tales, Mateus, Mauricio
 * @since jan 2023
 * @version 1.0
 */
public abstract class Produto {

    private int codigo;
    private String nome;
    private double preco;
    private String descricao;
    private double quantidade;
    private static int codigoUnico = 10000;
    
    /**
     * Construtor default da classe Produto<br>
     * <b>Uso: </b>
     * Produto produto = new Produto("Arroz", 5.0, 10.0, "Arroz branco");<br><br>
     * @param nome String que identifica o nome do produto
     * @param preco double que identifica o preço do produto
     * @param quantidade double que identifica a quantidade do produto
     * @param descricao String que identifica a descrição do produto
     */
    public Produto(String nome, double preco, double quantidade, String descricao) {
        if(nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio.");
        }

        if(preco <= 0) {
            throw new IllegalArgumentException("Preço deve ser maior que zero.");
        }

        if(quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        }

        if(descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição não pode ser nula ou vazia.");
        }

        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.codigo = codigoUnico++;
        this.quantidade = quantidade;
    }

    /**
    * @return int que identifica o código do produto
    */
    public int getCodigo() { return codigo; }

    /**
    * @return double que identifica o preço do produto
    */
    public double getPreco() { return preco; }

    /**
    * @return String que identifica o nome do produto
    */
    public String getNome() { return nome; }

    /**
    * @return double que identifica a quantidade do produto
    */
    public double getQuantidade() { return quantidade; }

    /**
    * @return String que identifica a descrição do produto
    */
    public String getDescricao() { return descricao; }

    /**
    @param codigo int que identifica o código do produto
    */
    public void setCodigo(int codigo) { this.codigo = codigo; }
    
    /**
    @param nome String que identifica o nome do produto
    */
    public void setNome(String nome) { this.nome = nome; }
    
    /**
    @param preco double que identifica o preço do produto
    */
    public void setPreco(double preco) { this.preco = preco; }
    
    /**
    @param quantidade double que identifica a quantidade do produto
    */
    public void setQuantidade(double quantidade) { this.quantidade = quantidade; }

    /**
    @param descricao String que identifica a descrição do produto
    */
    public void setDescricao(String descricao) { this.descricao = descricao; }

    /**
    * @return int que identifica o código único do produto
    */
    public void diminuirCodigoUnico() { codigoUnico--; }
    
    /**
    * @return String que identifica o produto
    */
    @Override
    public String toString() {
        return "Codigo: " + codigo + ";" +
               "Nome: " + nome + ";" +
               "Preco: " + preco + ";" +
               "Quantidade: " + quantidade + ";" +
               "Descricao: " + descricao;
    }
    
}