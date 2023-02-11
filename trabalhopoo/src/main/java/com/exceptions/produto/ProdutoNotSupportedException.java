package com.exceptions.produto;

/** A classe ProdutoNotSupportedException é responsável por exibir uma mensagem de erro caso o produto não seja suportado.
 * @author Mateus, Maurício, Ricardo, Tales
 * @since jan 2023
 * @version 1.0
 */
public class ProdutoNotSupportedException extends IllegalArgumentException {

    /**
     * Construtor da classe ProdutoNotSupportedException
     * @param message Mensagem de erro
     */
    public ProdutoNotSupportedException(String message) {
        super(message);
    }
}
