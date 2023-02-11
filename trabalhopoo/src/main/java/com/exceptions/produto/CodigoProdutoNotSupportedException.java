package com.exceptions.produto;

/**
 * A classe CodigoProdutoNotSupportedException é responsável por exibir uma mensagem de erro caso o código de um produto não seja suportado.
 * @author Mateus, Maurício, Ricardo, Tales
 * @since jan 2023
 * @version 1.0
 */
public class CodigoProdutoNotSupportedException extends IllegalArgumentException {

    /**
     * Construtor da classe CodigoProdutoNotSupportedException
     * @param message Mensagem de erro
     */
    public CodigoProdutoNotSupportedException(String message) {
        super(message);
    }
}
