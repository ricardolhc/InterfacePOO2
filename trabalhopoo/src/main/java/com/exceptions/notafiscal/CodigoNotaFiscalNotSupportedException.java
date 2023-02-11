package com.exceptions.notafiscal;

/**
 * A classe CodigoNotaFiscalNotSupportedException é responsável por exibir uma mensagem de erro caso o código de uma nota fiscal não seja suportado.
 * @author Mateus, Maurício, Ricardo, Tales
 * @since jan 2023
 * @version 1.0
 */
public class CodigoNotaFiscalNotSupportedException extends IllegalArgumentException {

    /**
     * Construtor da classe CodigoNotaFiscalNotSupportedException
     * @param message Mensagem de erro
     */
    public CodigoNotaFiscalNotSupportedException(String message) {
        super(message);
    }
}
