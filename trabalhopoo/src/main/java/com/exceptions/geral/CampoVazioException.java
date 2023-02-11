package com.exceptions.geral;

/**
 * A classe CampoVazioException é responsável por exibir uma mensagem de erro caso um campo esteja vazio.
 * @author Mateus, Maurício, Ricardo, Tales
 * @since jan 2023
 * @version 1.0
 */
public class CampoVazioException extends NullPointerException {

    /**
     * Construtor da classe CampoVazioException
     * @param message Mensagem de erro
     */
    public CampoVazioException(String message) {
        super(message);
    }
}

