package com.controller.produto;

import java.util.Optional;

import com.baseclasse.Produto;
import com.controller.ControllerMenuPrincipal;

import com.exceptions.geral.CampoVazioException;
import com.exceptions.produto.CodigoProdutoNotSupportedException;
import com.exceptions.produto.ProdutoNotFoundException;

import com.listas.ListaProdutos;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * Classe responsável por controlar a tela de remover produto
* @author Ricardo, Tales, Mateus, Mauricio
* @since jan 2023
 */
public class ControllerRemoverProduto {

    /**
     * btnLimpar usado para limpar os campos
     */
    @FXML
    private Button btnLimpar;

    /**
     * btnRemover usado para remover o produto
     */
    @FXML
    private Button btnRemover;

    /**
     * btnVoltar usado para voltar para o menu principal
     */
    @FXML
    private ImageView btnVoltar;

    /**
     * rootPane usado para carregar a tela de remover produto
     */
    @FXML
    private AnchorPane rootPane;

    /**
     * textFieldCodigo usado para receber o código do produto
     */
    @FXML
    private TextField textFieldCodigo;

    /**
     * listaProdutos usado para receber a lista de produtos
     */
    private ListaProdutos listaProdutos;

    /**
     * Método usado para inicializar a lista de produtos
     */
    @FXML
    void initialize() {
        listaProdutos = ControllerMenuPrincipal.getListaProdutos();
    }

    /**
    * Método usado para limpar os campos de texto
    * @param event evento de clicar no botão
    */
    @FXML
    void limparCampos(ActionEvent event) {
        textFieldCodigo.clear();
    }

    /**
    * Método usado para remover o produto usando o código do produto
    * @param event evento de clicar no botão
    */
    @FXML
    void removerProduto(ActionEvent event) {
        String codigo = textFieldCodigo.getText();

        try {

            int codigoInt = 0;
            Produto produto = null;
            Optional<ButtonType> result = null;

            if (codigo.trim().isEmpty() || codigo == null) {
                throw new CampoVazioException("Preencha o campo de código!");
            }

            try {
                codigoInt = Integer.parseInt(codigo);
            } catch (Exception e) {
                throw new CodigoProdutoNotSupportedException("O código deve ser um número inteiro!");
            }

            try {
                produto = listaProdutos.getProduto(codigoInt);
            } catch (Exception e) {
                throw e;
            }
            
            result = alertInterfaceConfirmacao(produto);
                
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    listaProdutos.removeProduto(codigoInt);
                    limparCampos(null);
                    alertInterface("Sucesso!", "Produto com código " + codigoInt  + " removido com sucesso!", AlertType.INFORMATION);
                } catch (Exception e) {
                    throw e;
                }
            }
        } catch (CampoVazioException | CodigoProdutoNotSupportedException | ProdutoNotFoundException e) {
            alertInterface("ERRO", e.getMessage(), AlertType.ERROR);
        } catch (Exception e) {
            alertInterface("ERRO", "Ocorreu um erro inesperado", AlertType.ERROR);
            System.out.println(e.getMessage());
        }
    }

    /**
    * Método usado para voltar para a tela principal
    * @param event evento de clicar no botão
    */   
    @FXML
    void voltarParaPrincipal(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../views/viewIndex.fxml"));
            Pane cmdPane = (Pane) fxmlLoader.load();

            rootPane.getChildren().clear();
            rootPane.getChildren().add(cmdPane);
        } catch (Exception e) {
            System.out.println(e);
            alertInterface("ERRO", "Não foi possível voltar para o menu principal", AlertType.ERROR);
        }
    }

    /**
    * Método usado para mostrar uma mensagem de alerta
    * @param titulo título da mensagem
    * @param mensagem mensagem a ser mostrada
    * @param tipo tipo de alerta
    */
    void alertInterface(String titulo, String mensagem, AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    /**
    * Método usado para mostrar uma mensagem de confirmação de remoção 
    * @return retorna o resultado da confirmação
    */
    Optional<ButtonType> alertInterfaceConfirmacao(Produto produto) {

        String nomeProduto = produto.getNome();
        String codigoProduto = String.valueOf(produto.getCodigo());
        String precoProduto = String.valueOf(produto.getPreco());
        String quantidadeProduto = String.valueOf(produto.getQuantidade());

        final String mensagem = "Nome: " + nomeProduto + "\nCódigo: " + codigoProduto + "\nPreço: " + precoProduto + "\nQuantidade: " + quantidadeProduto + "\n\nDeseja realmente remover o produto?";

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        return alert.showAndWait();
    }

    /**
     * Efeito de hover ao passar o mouse do botão de limpar
     * @param event efeito de hover ao passar o mouse do botão
     */
    @FXML
    void hoverBtnLimpar(MouseEvent event) {
        btnLimpar.setStyle("-fx-background-color: white;-fx-cursor: hand; -fx-background-radius: 5; -fx-text-fill: #747474;");
    }

    /**
     * Efeito de hover ao passar o mouse do botão de remover
     * @param event efeito de hover ao tirar o mouse do botão
     */
    @FXML
    void hoverBtnRemover(MouseEvent event) {
        btnRemover.setStyle("-fx-background-color: white;-fx-cursor: hand; -fx-background-radius: 5; -fx-text-fill: #7d2727;");
    }

    /**
     * Efeito de hover ao tirar o mouse do botão de limpar
     * @param event efeito de hover ao tirar o mouse do botão
     */
    @FXML
    void notHoverBtnLimpar(MouseEvent event) {
        btnLimpar.setStyle("-fx-background-color: #747474;-fx-cursor: hand; -fx-background-radius: 5;");
    }

    /**
     * Efeito de hover ao tirar o mouse do botão de limpar
     * @param event efeito de hover ao tirar o mouse do botão
     */
    @FXML
    void notHoverBtnRemover(MouseEvent event) {
        btnRemover.setStyle("-fx-background-color: #7d2727;-fx-cursor: hand; -fx-background-radius: 5;");
    }

    /**
     * Efeito de hover ao tirar o mouse no botão de voltar
     * @param event evento de hover ao passar o mouse no botão
     */
    @FXML
    void notHoverBtnVoltar(MouseEvent event) {
        btnVoltar.setImage(new Image("com/views/images/pngVoltar.png"));
        btnVoltar.setStyle("-fx-cursor: hand;");
    }

    /**
     * Efeito de hover ao passar o mouse no botão de voltar
     * @param event evento hover ao passar o mouse no botão de voltar
     */
    @FXML
    void hoverBtnVoltar(MouseEvent event) {
        btnVoltar.setImage(new Image("com/views/images/pngVoltarHover.png"));
        btnVoltar.setStyle("-fx-cursor: hand;");
    }

}
