package com;

import com.listas.ListaProdutos;
import com.listas.ListaNotaFiscal;

import com.baseclasse.Produto;
import com.baseclasse.ProdutoFracionado;
import com.baseclasse.ProdutoUnidade;

import com.controller.ControllerMenuPrincipal;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

/**
 * A classe App é responsável por iniciar a aplicação e carregar a tela inicial.
 * @author Mateus, Maurício, Ricardo, Tales
 * @since jan 2023
 * @version 1.0
 */
public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Método responsável por iniciar a aplicação e carregar a tela inicial.
     * @param primaryStage paramentro que recebe a tela inicial.
     */
    @Override
    public void start(Stage primaryStage) {
        System.out.println("Iniciando aplicação");

        final String viewInicial = "views/viewIndex.fxml";
        final String tituloApp = "Mercado Central";
        final boolean telaResizable = false;

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(viewInicial));
            Parent root = fxmlLoader.load();
            Scene tela = new Scene(root);

            ListaProdutos listaProdutos = new ListaProdutos();
            ListaNotaFiscal listaNotaFiscal = new ListaNotaFiscal();

            Produto produto1 = new ProdutoUnidade("Caneta Bic Azul (Unidade)", 10, 5, "Caneta esferográfica");
            Produto produto2 = new ProdutoUnidade("Caneta Bic Azul (Caixa)", 10, 5, "Caneta esferográfica");
            Produto produto3 = new ProdutoFracionado("Tecido azul (MT)", 10, 7.5, "Tecido de algodão");
            Produto produto4 = new ProdutoFracionado("Tecido vermelho (MT)", 15, 10, "Tecido de algodão");

            listaProdutos.addProduto(produto1);
            listaProdutos.addProduto(produto2);
            listaProdutos.addProduto(produto3);
            listaProdutos.addProduto(produto4);

            ControllerMenuPrincipal controller = fxmlLoader.getController();
            controller.setListaNotaFiscal(listaNotaFiscal);
            controller.setListaProdutos(listaProdutos);

            primaryStage.setTitle(tituloApp);
            primaryStage.setScene(tela);
            primaryStage.setResizable(telaResizable);
            primaryStage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}