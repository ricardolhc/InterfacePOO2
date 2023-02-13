package com.controller.notafiscal;

import java.util.Optional;

import com.controller.ControllerMenuPrincipal;
import com.exceptions.notafiscal.CodigoNotaFiscalNotSupportedException;
import com.exceptions.notafiscal.NotaFiscalNotFoundException;
import com.listas.ListaNotaFiscal;

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
 * Classe responsável por controlar a tela de remmover de nota fiscal
 * 
 * @author Ricardo, Tales, Mateus, Mauricio
 * @since jan 2023
 */
public class ControllerRemoverNotaFiscal {

    /**
     * btnLimpar usado para limpar os campos
     */
    @FXML
    private Button btnLimpar;

    /**
     * btnRemover usado para remover a nota fiscal
     */
    @FXML
    private Button btnRemover;

    /**
     * btnVoltar usado para voltar para o menu principal
     */
    @FXML
    private ImageView btnVoltar;

    /**
     * rootPane usado para controlar a tela
     */
    @FXML
    private AnchorPane rootPane;

    /**
     * textFieldCodigo usado para receber o código da nota fiscal
     */
    @FXML
    private TextField textFieldCodigo;

    /**
     * listaProdutos usado para receber a lista de notas fiscais
     */
    private ListaNotaFiscal listaNotaFiscal;

    /**
     * Método usado para inicializar a lista de notas fiscais
     */
    @FXML
    void initialize() {
        listaNotaFiscal = ControllerMenuPrincipal.getListaNotaFiscal();
    }

    /**
     * Método usado para remover a nota fiscal usando o codigo como parâmetro
     * 
     * @param event evento de clicar no botão
     */
    @FXML
    void removerNotaFiscal(ActionEvent event) {

        String codigo = textFieldCodigo.getText();

        try {

            int codigoInt = 0;
            Optional<ButtonType> result = null;

            if (codigo.trim().isEmpty() || codigo == null) {
                throw new Exception("Preencha o campo de código!");
            }

            try {
                codigoInt = Integer.parseInt(codigo);
            } catch (Exception e) {
                throw new Exception("O código deve ser um número inteiro!");
            }

            if (codigoInt <= 0) {
                throw new Exception("O código deve ser um número inteiro positivo!");
            }

            result = alertInterfaceConfirmacao();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    listaNotaFiscal.removeNotaFiscal(codigoInt);
                } catch (Exception e) {
                    throw e;
                }
                limparCampos(null);
                alertInterface("SUCESSO", "Nota fiscal com código " + codigoInt + " removido com sucesso!",
                        AlertType.INFORMATION);
            }

        } catch (CodigoNotaFiscalNotSupportedException | NotaFiscalNotFoundException e) {
            alertInterface("ERRO", e.getMessage(), AlertType.ERROR);
        } catch (Exception e) {
            alertInterface("ERRO", "Ocorreu um erro inesperado", AlertType.ERROR);
            System.out.println(e.getMessage());
        }
    }

    /**
     * Método usado para voltar para a tela principal
     * 
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
     * 
     * @param titulo   título da mensagem
     * @param mensagem mensagem a ser mostrada
     * @param tipo     tipo de alerta
     */
    void alertInterface(String titulo, String mensagem, AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    /**
     * Método usado para mostrar uma mensagem de confirmação
     * 
     * @return retorna o resultado da confirmação
     */
    Optional<ButtonType> alertInterfaceConfirmacao() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText(null);
        alert.setContentText("Você deseja remover o produto?");
        return alert.showAndWait();
    }

    /**
     * Método usado para limpar os campos de texto
     * 
     * @param event evento de clicar no botão
     */
    @FXML
    void limparCampos(ActionEvent event) {
        textFieldCodigo.clear();
    }

    /**
     * Efeito de hover ao passar o mouse do botão de limpar
     * 
     * @param event efeito de hover ao passar o mouse do botão
     */
    @FXML
    void hoverBtnLimpar(MouseEvent event) {
        btnLimpar.setStyle("-fx-background-color: white;-fx-cursor: hand; -fx-background-radius: 5;-fx-text-fill: #686868;");
    }

    /**
     * Efeito de hover ao passar o mouse do botão de remover
     * 
     * @param event efeito de hover ao tirar o mouse do botão
     */
    @FXML
    void hoverBtnRemover(MouseEvent event) {
        btnRemover.setStyle("-fx-background-color: white;-fx-cursor: hand; -fx-background-radius: 5; -fx-text-fill: #682121;");
    }

    /**
     * Efeito de hover ao tirar o mouse do botão de limpar
     * 
     * @param event efeito de hover ao tirar o mouse do botão
     */
    @FXML
    void notHoverBtnLimpar(MouseEvent event) {
        btnLimpar.setStyle("-fx-background-color: #747474;-fx-cursor: hand; -fx-background-radius: 5;");
    }

    /**
     * Efeito de hover ao tirar o mouse do botão de limpar
     * 
     * @param event efeito de hover ao tirar o mouse do botão
     */
    @FXML
    void notHoverBtnRemover(MouseEvent event) {
        btnRemover.setStyle("-fx-background-color: #7d2727;-fx-cursor: hand; -fx-background-radius: 5;");
    }

    /**
     * Efeito de hover ao tirar o mouse no botão de voltar
     * 
     * @param event evento de hover ao passar o mouse no botão
     */
    @FXML
    void notHoverBtnVoltar(MouseEvent event) {
        btnVoltar.setImage(new Image("com/views/images/pngVoltar.png"));
        btnVoltar.setStyle("-fx-cursor: hand;");
    }

    /**
     * Efeito de hover ao passar o mouse no botão de voltar
     * 
     * @param event evento hover ao passar o mouse no botão de voltar
     */
    @FXML
    void hoverBtnVoltar(MouseEvent event) {
        btnVoltar.setImage(new Image("com/views/images/pngVoltarHover.png"));
        btnVoltar.setStyle("-fx-cursor: hand;");
    }

}