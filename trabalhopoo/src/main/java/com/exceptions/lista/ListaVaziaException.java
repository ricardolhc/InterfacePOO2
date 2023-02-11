package com.exceptions.lista;

/**
 * A classe ListaVaziaException é responsável por exibir uma mensagem de erro caso a lista esteja vazia.
 * @author Mateus, Maurício, Ricardo, Tales
 * @since jan 2023
 * @version 1.0
 */
public class ListaVaziaException extends NullPointerException {

    /**
     * Construtor da classe ListaVaziaException
     * @param message Mensagem de erro
     */
    public ListaVaziaException(String message) {
        super(message);
    }
}
