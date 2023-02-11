package com.listas;

import java.util.ArrayList;

import com.baseclasse.Produto;
import com.baseclasse.ProdutoUnidade;

import com.exceptions.produto.AddProdutoException;
import com.exceptions.produto.AddQuantidadeNotSupportedException;
import com.exceptions.produto.CodigoProdutoNotSupportedException;
import com.exceptions.produto.PrecoNotSupportedException;
import com.exceptions.produto.ProdutoNotFoundException;
import com.exceptions.produto.ProdutoNotSupportedException;
import com.exceptions.produto.QuantidadeNotSupportedException;
import com.exceptions.produto.SubQuantidadeNotSupportedException;

/**
* Classe responsável por controlar a lista de produtos.
* @author Ricardo, Tales, Mateus, Mauricio
* @since jan 2023
*/
public class ListaProdutos implements IProdutos {
    
    /**
     * Lista de produtos
     */
    private ArrayList<Produto> produtos;

    /**
    * Construtor default da classe ListaProdutosl<br>
    * Inicializa a lista de produtos
    */
    public ListaProdutos() {
        this.produtos = new ArrayList<Produto>();
    }

    /**
    * Construtor da classe ListaProdutos<br>
    * Inicializa a lista de produtos com a lista passada por parâmetro
    * @param produtos Lista de produtos
    */
    public ListaProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

    /**
    * Método responsável por adicionar um produto na lista de produtos
    * @param p produto a ser adicionado
    */
    @Override
    public void addProduto(Produto p) throws Exception {
        if (p == null) {
            throw new AddProdutoException("Não foi possível adicionar o produto.");
        }
        produtos.add(p);
    }
    
    /**
    * Método responsável por remover um produto da lista de produtos
    * @param codigo código do produto a ser removido
    */
    @Override
    public void removeProduto(int codigo) throws Exception {
        
        if(codigo < 0) {
            throw new CodigoProdutoNotSupportedException("Para remover o produto o código deve ser maior que zero.");
        }

        try {
            Produto produto = getProduto(codigo);
            produtos.remove(produto);
        } catch (Exception e) {
            throw new ProdutoNotFoundException("Não foi possível remover o produto com o código " + codigo + ".");
        }
    }
    
    /**
    * Método responsável por retornar um produto da lista de produtos
    * @param codigo código do produto a ser retornado
    * @return produto
    */
    @Override
    public Produto getProduto(int codigo) throws Exception {
        
        if(codigo < 0) {
            throw new CodigoProdutoNotSupportedException("Para obter o produto o código deve ser maior que zero.");
        }

        for (Produto p : produtos){
            if (p.getCodigo() == codigo){
                return p;
            }
        }
        throw new ProdutoNotFoundException("Não foi possível obter o produto com o código " + codigo + ".");
    }
    
    /**
    * Método responsável por atualizar a quantidade de um produto da lista de produtos
    * @param codigo código do produto a ser atualizado
    * @param nova nova quantidade do produto
    * @return lista de produtos
    */
    @Override
    public void updateQuantidade(int codigo, double nova) throws Exception {

        if(codigo < 0) {
            throw new CodigoProdutoNotSupportedException("Para atualizar a quantidade o código deve ser maior que zero.");
        }

        if(nova <= 0) {
            throw new QuantidadeNotSupportedException("Para atualizar a quantidade a quantidade deve ser maior que zero.");
        }

        try {
            Produto produto = getProduto(codigo);

            if(produto instanceof ProdutoUnidade) {
                int quantidadeInt = (int) nova;

                if(quantidadeInt != nova) {
                    throw new QuantidadeNotSupportedException("Para atualizar a quantidade a quantidade deve ser um número inteiro.");
                }
            }

            produto.setQuantidade(nova);
        } catch (Exception e) {
            throw new ProdutoNotFoundException("Não foi possível atualizar a quantidade do produto com o código " + codigo + ".");
        }
    }       
    
    /**
    * Método responsável por atualizar o preço de um produto da lista de produtos
    * @param codigo código do produto a ser atualizado
    * @param novo novo preço do produto
    * @return lista de produtos
    */
    @Override
    public void updatePreco(int codigo, double novo) throws Exception {

        if(codigo < 0) {
            throw new CodigoProdutoNotSupportedException("Para atualizar o preço o código deve ser maior que zero.");
        }

        if(novo < 0) {
            throw new PrecoNotSupportedException("Para atualizar o preço o preço deve ser maior que zero.");
        }
        
        try {
            Produto produto = getProduto(codigo);
            produto.setPreco(novo);
        } catch (Exception e) {
            throw new ProdutoNotFoundException("Não foi possível atualizar o preço do produto com o código " + codigo + ".");
        }
    }
    
    /**
    * Método responsável por adicionar a quantidade de um produto da lista de produtos
    * @param codigo código do produto a ser atualizado
    * @param quantidade quantidade a ser adicionada
    * @return lista de produtos
    */
    @Override
    public void addQuantidade(int codigo, double quantidade) throws Exception {

        if(codigo < 0) {
            throw new CodigoProdutoNotSupportedException("Para adicionar a quantidade o código deve ser maior que zero.");
        }

        if(quantidade <= 0) {
            throw new AddQuantidadeNotSupportedException("Para adicionar a quantidade a quantidade deve ser maior que zero.");
        }

        try {
            Produto produto = getProduto(codigo);

            if(produto instanceof ProdutoUnidade) {
                int quantidadeInt = (int) quantidade;

                if(quantidadeInt != quantidade) {
                    throw new AddQuantidadeNotSupportedException("Para adicionar a quantidade a quantidade deve ser um número inteiro.");
                }
            }

            produto.setQuantidade(produto.getQuantidade() + quantidade);
        } catch (Exception e) {
            throw new ProdutoNotFoundException("Não foi possível adicionar a quantidade do produto com o código " + codigo + ".");
        }
    }

    /**
    * Método responsável por subtrair a quantidade de um produto da lista de produtos
    * @param codigo código do produto a ser atualizado
    * @param quantidade quantidade a ser subtraída
    * @return lista de produtos
    */
    @Override
    public void subQuantidade(int codigo, double quantidade) throws Exception {

        if(codigo < 0) {
            throw new CodigoProdutoNotSupportedException("Para subtrair a quantidade o código deve ser maior que zero.");
        }

        if(quantidade <= 0) {
            throw new SubQuantidadeNotSupportedException("Para subtrair a quantidade a quantidade deve ser maior que zero.");
        }

        try {
            Produto produto = getProduto(codigo);

            if(produto instanceof ProdutoUnidade) {
                int quantidadeInt = (int) quantidade;

                if(quantidadeInt != quantidade) {
                    throw new SubQuantidadeNotSupportedException("Para subtrair a quantidade a quantidade deve ser um número inteiro.");
                }
            }

            produto.setQuantidade(produto.getQuantidade() - quantidade);
        } catch (Exception e) {
            throw new ProdutoNotFoundException("Não foi possível subtrair a quantidade do produto com o código " + codigo + ".");
        }
    }


    /**
    * Método responsável por substituir um produto da lista de produtos
    * @param produtoASerSubstituido produto a ser substituido
    * @param produtoSubstituto produto substituto
    * @return lista de produtos
    */
    public void substituirProduto(Produto produtoSubstituido, Produto produtoSubstituto) {

        if(produtoSubstituido == null) {
            throw new ProdutoNotSupportedException("Produto a ser substituido não pode ser nulo.");
        }

        if(produtoSubstituto == null) {
            throw new ProdutoNotSupportedException("Produto substituto não pode ser nulo.");
        }

        int index = produtos.indexOf(produtoSubstituido);
        produtos.set(index, produtoSubstituto);
        produtoSubstituido.diminuirCodigoUnico();
        
    }

    /**
    * Método responsável por verificar se a lista de produtos está vazia
    * @return lista de produtos
    */
    public boolean isEmpty() {
        return produtos.isEmpty();
    }

    public ArrayList<Produto> getArray() {
        return produtos;
    }

    /**
    * Método responsável por verificar o tamanho da lista de produtos
    * @return lista de produtos
    */
    public int size() {
        return produtos.size();
    }

    /** 
     * @return String que identifica as informações da lista de produtos
     */
    @Override
    public String toString() {
        String conteudo = "";
        if(produtos.size() != 0) {
            for (Produto p : produtos){
                conteudo += p.toString() + "\n";
            }
            return conteudo;
        }
        return "Lista vazia";
    }

}
