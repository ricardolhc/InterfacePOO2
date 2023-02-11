package com.exceptions.notafiscal;

/**
 * A classe AddNotaFiscalException é responsável por exibir uma mensagem de erro caso não seja possível adicionar uma Nota Fiscal. 
 * @author Mateus, Maurício, Ricardo, Tales
 * @since jan 2023
 * @version 1.0
 */
public class AddNotaFiscalException extends IllegalArgumentException {

    /**
     * Construtor da classe AddNotaFiscalException
     * @param message Mensagem de erro
     */
    public AddNotaFiscalException(String message) {
        super(message);
    }
}
