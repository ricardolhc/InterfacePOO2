package com.exceptions.produto;

/** A classe RemoveProdutoException é responsável por exibir uma mensagem de erro caso a quantidade não seja suportada.
 * @author Mateus, Maurício, Ricardo, Tales
 * @since jan 2023
 * @version 1.0
 */
public class QuantidadeNotSupportedException extends IllegalArgumentException {

    /**
     * Construtor da classe QuantidadeNotSupportedException
     * @param message Mensagem de erro
     */
    public QuantidadeNotSupportedException(String message) {
        super(message);
    }
}
