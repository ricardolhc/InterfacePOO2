package com.exceptions.item;

/**
 * A classe ItemNotFoundException é responsável por exibir uma mensagem de erro caso o item não seja encontrado.
 * @author Mateus, Maurício, Ricardo, Tales
 * @since jan 2023
 * @version 1.0
 */
public class ItemNotFoundException extends NullPointerException {

    /**
     * Construtor da classe ItemNotFoundException
     * @param message Mensagem de erro
     */
    public ItemNotFoundException(String message) {
        super(message);
    }
}
