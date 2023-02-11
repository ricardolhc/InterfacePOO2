package com.exceptions.notafiscal;

/**
 * A classe DataNotSupportedException é responsável por exibir uma mensagem de erro caso a data não seja suportada.
 * @author Mateus, Maurício, Ricardo, Tales
 * @since jan 2023
 * @version 1.0
 */
public class DataNotSupportedException extends IllegalArgumentException {

    /**
     * Construtor da classe DataNotSupportedException
     * @param message Mensagem de erro
     */
    public DataNotSupportedException(String message) {
        super(message);
    }
}

