package com.exceptions.produto;

/**
 * A classe AddQuantidadeNotSupportedException é responsável por exibir uma mensagem de erro caso não seja possível adicionar uma quantidade de um produto.
 * @author Mateus, Maurício, Ricardo, Tales
 * @since jan 2023
 * @version 1.0
 */
public class AddQuantidadeNotSupportedException extends IllegalArgumentException {

    /**
     * Construtor da classe AddQuantidadeNotSupportedException
     * @param message Mensagem de erro
     */
    public AddQuantidadeNotSupportedException(String message) {
        super(message);
    }
}
