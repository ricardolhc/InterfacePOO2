package com.exceptions.geral;

/**
 * A classe RadioButtonVazioException é responsável por exibir uma mensagem de erro caso o RadioButton esteja vazio.
 * @author Mateus, Maurício, Ricardo, Tales
 * @since jan 2023
 * @version 1.0
 */
public class RadioButtonVazioException extends NullPointerException {

    /**
     * Construtor da classe RadioButtonVazioException
     * @param message Mensagem de erro
     */
    public RadioButtonVazioException(String message) {
        super(message);
    }
}

