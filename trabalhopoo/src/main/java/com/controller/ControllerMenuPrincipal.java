package com.controller;

import com.listas.ListaNotaFiscal;
import com.listas.ListaProdutos;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.input.MouseEvent;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * Classe responsável por controlar a tela de menu principal
* @author Ricardo, Tales, Mateus, Mauricio
* @since jan 2023
 */
public class ControllerMenuPrincipal {

    /**
     * btnAdicionaNotaFiscal usado para acessar o menu de adicionar nota fiscal
     */
    @FXML
    private Button btnAdicionaNotaFiscal;

    /**
     * btnAdicionaProduto usado para acessar o menu de adicionar produto
     */
    @FXML
    private Button btnAdicionaProduto;

    /**
     * btnAlteraInformacoesNotaFiscal usado para acessar o menu de alterar informações da nota fiscal
     */
    @FXML
    private Button btnAlteraInformacoesNotaFiscal;

    /**
     * btnAlterarInformacoesProduto usado para acessar o menu de alterar informações do produto
     */
    @FXML
    private Button btnAlterarInformacoesProduto;

    /**
     * btnNotaFiscalExpandir usado para expandir o menu de nota fiscal
     */
    @FXML
    private Button btnNotaFiscalExpandir;

    /**
     * btnProdutoExpandir usado para expandir o menu de produto
     */
    @FXML
    private Button btnProdutoExpandir;

    /**
     * btnRemoveNotaFiscal usado para acessar o menu de remover nota fiscal
     */
    @FXML
    private Button btnRemoveNotaFiscal;

    /**
     * btnRemoveProduto usado para acessar o menu de remover produto
     */
    @FXML
    private Button btnRemoveProduto;

    /**
     * btnVisualizaInformacoesNotaFiscal usado para acessar o menu de visualizar informações da nota fiscal
     */
    @FXML
    private Button btnVisualizaInformacoesNotaFiscal;

    /**
     * btnVisualizaInformacoesProduto usado para acessar o menu de visualizar informações do produto
     */
    @FXML
    private Button btnVisualizaInformacoesProduto;

    /**
     * paneMenuNotaFiscalExpandido usado para expandir o menu de nota fiscal
     */
    @FXML
    private Pane paneMenuNotaFiscalExpandido;

    /**
     * paneMenuProdutoExpandido usado para expandir o menu de produto
     */
    @FXML
    private Pane paneMenuProdutoExpandido;

    /**
     * rootPane usado para acessar a tela de menu principal
     */
    @FXML
    private AnchorPane rootPane;

    /**
     * listaProdutos usado para receber a lista de produtos
     */
    private static ListaProdutos listaProdutos;

    /**
     * listaNotaFiscal usado para receber a lista de nota fiscal
     */
    private static ListaNotaFiscal listaNotaFiscal;

    /**
     * Método usado para acessar o menu de adicionar nota fiscal
     * @param event evento de clique
     */
    @FXML
    void adicionaNotaFiscal(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/NotaFiscal/viewAddNotaFiscal.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e);
            alertInterface("ERRO", "Não foi possível entrar na tela de adicionar nova venda", AlertType.ERROR);
        }
    }

    /**
     * Método usado para acessar o menu de adicionar produto
     * @param event evento de clique
     */
    @FXML
    void adicionaProduto(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/produto/viewAddProduto.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e);
            alertInterface("ERRO", "Não foi possível entrar na tela de adicionar produto", AlertType.ERROR);
        }
    }

    /**
     * Método usado para acessar o menu de alterar informações da nota fiscal
     * @param event evento de clique
     */
    @FXML
    void alteraInformacoesNotaFiscal(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/NotaFiscal/viewAlterarNotaFiscal.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e);
            alertInterface("ERRO", "Não foi possível entrar na tela de remover produto", AlertType.ERROR);
        }
    }

    /**
     * Método usado para acessar o menu de alterar informações do produto
     * @param event evento de clique
     */
    @FXML
    void alterarInformacoesProduto(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/produto/viewAlteraProduto.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            alertInterface("ERRO", "Não foi possível entrar na tela de alterar produto", AlertType.ERROR);
        }
    }

    /**
     * Método usado para fechar o menu de nota fiscal
     * @param event evento de clique
     */
    @FXML
    void fecharMenuNotaFiscal(MouseEvent event) {
        paneMenuNotaFiscalExpandido.setVisible(false);
    }

    /**
     * Método usado para fechar o menu de produto
     * @param event evento de clique
     */
    @FXML
    void fecharMenuProduto(MouseEvent event) {
        paneMenuProdutoExpandido.setVisible(false);
    }

    /**
     * Efeito de hover ao passar o mouse do botão de adicionar nota fiscal
     * @param event efeito de hover ao passar o mouse do botão
     */
    @FXML
    void hoverAdicionaNotaFiscal(MouseEvent event) {
        btnAdicionaNotaFiscal.setStyle("-fx-background-color: #3e081a; -fx-cursor: hand;");
    }

    /**
     * Efeito de hover ao passar o mouse do botão de adicionar produto
     * @param event efeito de hover ao passar o mouse do botão
     */
    @FXML
    void hoverAdicionaProduto(MouseEvent event) {
        btnAdicionaProduto.setStyle("-fx-background-color: #5c0a27; -fx-cursor: hand;");
    }

    /**
     * Efeito de hover ao passar o mouse do botão de alterar informações da nota fiscal
     * @param event efeito de hover ao passar o mouse do botão
     */
    @FXML
    void hoverAlteraInformacoesNotaFiscal(MouseEvent event) {
        btnAlteraInformacoesNotaFiscal.setStyle("-fx-background-color: #3e081a; -fx-cursor: hand;");
    }

    /**
     * Efeito de hover ao passar o mouse do botão de alterar informações do produto
     * @param event efeito de hover ao passar o mouse do botão
     */
    @FXML
    void hoverAlteraInformacoesProduto(MouseEvent event) {
        btnAlterarInformacoesProduto.setStyle("-fx-background-color: #5c0a27; -fx-cursor: hand;");
    }

    /**
     * Efeito de hover ao passar o mouse do botão de remover nota fiscal
     * @param event efeito de hover ao passar o mouse do botão
     */
    @FXML
    void hoverRemoveNotaFiscal(MouseEvent event) {
        btnRemoveNotaFiscal.setStyle("-fx-background-color: #3e081a; -fx-cursor: hand;");
    }

    /**
     * Efeito de hover ao passar o mouse do botão de remover produto
     * @param event efeito de hover ao passar o mouse do botão
     */
    @FXML
    void hoverRemoveProduto(MouseEvent event) {
        btnRemoveProduto.setStyle("-fx-background-color: #5c0a27; -fx-cursor: hand;");
    }

    /**
     * Efeito de hover ao passar o mouse do botão de visualizar informações da nota fiscal
     * @param event efeito de hover ao passar o mouse do botão
     */
    @FXML
    void hoverVisualizaInformacoesNotaFiscal(MouseEvent event) {
        btnVisualizaInformacoesNotaFiscal.setStyle("-fx-background-color: #3e081a; -fx-cursor: hand;");
    }

    /**
     * Efeito de hover ao passar o mouse do botão de visualizar informações do produto
     * @param event efeito de hover ao passar o mouse do botão
     */
    @FXML
    void hoverVisualizaInformacoesProduto(MouseEvent event) {
        btnVisualizaInformacoesProduto.setStyle("-fx-background-color: #5c0a27; -fx-cursor: hand;");
    }

    /**
     * Método usado para mostrar o menu de nota fiscal
     * @param event evento de clique
     */
    @FXML
    void mostrarMenuNotaFiscal(MouseEvent event) {
        paneMenuNotaFiscalExpandido.setVisible(true);
    }

    /**
     * Método usado para mostrar o menu de produto
     * @param event evento de clique
     */
    @FXML
    void mostrarMenuProduto(MouseEvent event) {
        paneMenuProdutoExpandido.setVisible(true);
    }

    /**
     * Efeito de hover ao tirar o mouse no botão de adicionar nota fiscal
     * @param event evento de hover ao tirar o mouse no botão
     */
    @FXML
    void notHoverAdicionaNotaFiscal(MouseEvent event) {
        btnAdicionaNotaFiscal.setStyle("-fx-background-color: #5c0a27");
    }

    /**
     * Efeito de hover ao tirar o mouse no botão de adicionar produto
     * @param event evento de hover ao tirar o mouse no botão
     */
    @FXML
    void notHoverAdicionaProduto(MouseEvent event) {
        btnAdicionaProduto.setStyle("-fx-background-color: #370617");
    }

    /**
     * Efeito de hover ao tirar o mouse no botão de alterar informações da nota fiscal
     * @param event evento de hover ao tirar o mouse no botão
     */
    @FXML
    void notHoverAlteraInformacoesNotaFiscal(MouseEvent event) {
        btnAlteraInformacoesNotaFiscal.setStyle("-fx-background-color: #5c0a27");
    }

    /**
     * Efeito de hover ao tirar o mouse no botão de alterar informações do produto
     * @param event evento de hover ao tirar o mouse no botão
     */
    @FXML
    void notHoverAlteraInformacoesProduto(MouseEvent event) {
        btnAlterarInformacoesProduto.setStyle("-fx-background-color: #370617");
    }

    /**
     * Efeito de hover ao tirar o mouse no botão de remover nota fiscal
     * @param event evento de hover ao tirar o mouse no botão
     */
    @FXML
    void notHoverRemoveNotaFiscal(MouseEvent event) {
        btnRemoveNotaFiscal.setStyle("-fx-background-color: #5c0a27");
    }

    /**
     * Efeito de hover ao tirar o mouse no botão de remover produto
     * @param event evento de hover ao tirar o mouse no botão
     */
    @FXML
    void notHoverRemoveProduto(MouseEvent event) {
        btnRemoveProduto.setStyle("-fx-background-color: #370617");
    }

    /**
     * Efeito de hover ao tirar o mouse no botão de visualizar informações da nota fiscal
     * @param event evento de hover ao tirar o mouse no botão
     */
    @FXML
    void notHoverVisualizaInformacoesNotaFiscal(MouseEvent event) {
        btnVisualizaInformacoesNotaFiscal.setStyle("-fx-background-color: #5c0a27");
    }

    /**
     * Efeito de hover ao tirar o mouse no botão de visualizar informações do produto
     * @param event evento de hover ao tirar o mouse no botão
     */
    @FXML
    void notHoverVisualizaInformacoesProduto(MouseEvent event) {
        btnVisualizaInformacoesProduto.setStyle("-fx-background-color: #370617");
    }

    /**
     * Método usado para acessar o menu de remover nota fiscal
     * @param event evento de clique
     */
    @FXML
    void removeNotaFiscal(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/NotaFiscal/viewRemoverNotaFiscal.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e);
            alertInterface("ERRO", "Não foi possível entrar na tela de remover produto", AlertType.ERROR);
        }
    }

    /**
     * Método usado para acessar o menu de remover produto
     * @param event evento de clique
     */
    @FXML
    void removeProduto(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/produto/viewRemoverProduto.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e);
            alertInterface("ERRO", "Não foi possível entrar na tela de remover produto", AlertType.ERROR);
        }
    }

    /**
     * Método usado para acessar o menu de visualizar informações de nota fiscal
     * @param event evento de clique
     */
    @FXML
    void visualizaInformacoesNotaFiscal(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/notafiscal/viewVisualizaVenda.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e);
            alertInterface("ERRO", "Não foi possível entrar na tela de visualizar vendas", AlertType.ERROR);
        }
    }

    /**
     * Método usado para acessar o menu de visualizar informações de produto
     * @param event evento de clique
     */
    @FXML
    void visualizaInformacoesProduto(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../views/produto/viewVisualizaProdutos.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.out.println(e);
            alertInterface("ERRO", "Não foi possível entrar na tela de visualizar informações do produto", AlertType.ERROR);
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

    public void setListaProdutos(ListaProdutos listaProdutosNova) {
        listaProdutos = listaProdutosNova;
    }

    public void setListaNotaFiscal(ListaNotaFiscal ListaNotaFiscalNova) {
        listaNotaFiscal = ListaNotaFiscalNova;
    }

    public static ListaProdutos getListaProdutos() {
        return listaProdutos;
    }

    public static ListaNotaFiscal getListaNotaFiscal() {
        return listaNotaFiscal;
    }

}
