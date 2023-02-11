package com.exceptions.produto;

/** A classe RemoveProdutoException é responsável por exibir uma mensagem de erro caso não seja possível remover um produto. 
 * @author Mateus, Maurício, Ricardo, Tales
 * @since jan 2023
 * @version 1.0
 */
public class ProdutoNotFoundException extends NullPointerException {

    /**
     * Construtor da classe ProdutoNotFoundException
     * @param message Mensagem de erro
     */
    public ProdutoNotFoundException(String message) {
        super(message);
    }
}
