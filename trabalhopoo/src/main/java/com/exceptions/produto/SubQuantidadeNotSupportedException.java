package com.exceptions.produto;

/**
 * A classe SubQuantidadeNotSupportedException é responsável por exibir uma mensagem de erro caso não seja possível subtrair a quantidade de um produto.
 * @author Mateus, Maurício, Ricardo, Tales
 * @since jan 2023
 * @version 1.0
 */
public class SubQuantidadeNotSupportedException extends IllegalArgumentException {

    /**
     * Construtor da classe SubQuantidadeNotSupportedException
     * @param message Mensagem de erro
     */
    public SubQuantidadeNotSupportedException(String message) {
        super(message);
    }
}
