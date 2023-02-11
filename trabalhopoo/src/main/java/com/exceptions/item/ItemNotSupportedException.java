package com.exceptions.item;

/**
 * A classe ItemNotSupportedException é responsável por exibir uma mensagem de erro caso o item não seja suportado. 
 * @author Mateus, Maurício, Ricardo, Tales
 * @since jan 2023
 * @version 1.0
 */
public class ItemNotSupportedException extends IllegalArgumentException {

    /**
     * Construtor da classe ItemNotSupportedException
     * @param message Mensagem de erro
     */
    public ItemNotSupportedException(String message) {
        super(message);
    }
}
