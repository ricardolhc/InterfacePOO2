package com.controller.produto;

import com.baseclasse.Produto;
import com.baseclasse.ProdutoFracionado;
import com.baseclasse.ProdutoUnidade;

import com.controller.ControllerMenuPrincipal;
import com.exceptions.geral.CampoVazioException;
import com.exceptions.lista.ListaVaziaException;
import com.exceptions.produto.CodigoProdutoNotSupportedException;
import com.exceptions.produto.ProdutoNotFoundException;
import com.listas.ListaProdutos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * Classe responsável por controlar a tela de visualização de produtos
* @author Ricardo, Tales, Mateus, Mauricio
* @since jan 2023
 */
public class ControllerVisualizaProduto {

    /**
     * btnInformacaoProduto usado para mostrar as informações de um produto
     */
    @FXML
    private Button btnInformacaoProduto;

    /**
     * btnInformacaoTodosProdutos usado para mostrar as informações todos os produtos
     */
    @FXML
    private Button btnInformacaoTodosProdutos;

    /**
     * btnLimpar usado para limpar os campos
     */
    @FXML
    private Button btnLimpar;

    /**
     * btnProcurar usado para  pesquisar um produto
     */
    @FXML
    private Button btnPesquisar;

    /**
     * btnVoltar usado para voltar para a tela de menu principal
     */
    @FXML
    private ImageView btnVoltar;

    /**
     * radioButtonFracionado usado para selecionar se o produto é fracionado
     */
    @FXML
    private RadioButton radioButtonFracionado;

    /**
     * radioButtonUnidade usado para selecionar se o produto é por unidade
     */
    @FXML
    private RadioButton radioButtonUnidade;

    /**
     * rootPane usado para carregar a tela de alterar produto
     */
    @FXML
    private AnchorPane rootPane;

    /**
     * tableColumnCodigo usado para mostrar o código do produto
     */
    @FXML
    private TableColumn<Produto, Integer> tableColumnCodigo;

    /**
     * tableColumnNome usado para mostrar o nome do produto
     */
    @FXML
    private TableColumn<Produto, String> tableColumnNome;

    /**
     * tableColumnPreco usado para mostrar o preço do produto
     */
    @FXML
    private TableColumn<Produto, Double> tableColumnPreco;

    /**
     * tableColumnQuantidade usado para mostrar a quantidade do produto
     */
    @FXML
    private TableColumn<Produto, Double> tableColumnQuantidade;

    /**
     * tableColumnDescricao usado para mostrar a descrição do produto
     */
    @FXML
    private TableColumn<Produto, String> tableColumnDescricao;

    /**
     * tableViewInfoCompleta usado para mostrar a tabela com todos os produtos
     */
    @FXML
    private TableView<Produto> tableViewInfoCompleta;

    /**
     * textFieldCodigo usado para receber o código do produto
     */
    @FXML
    private TextField textFieldCodigo;

    /**
     * textFieldDescricao usado para receber a descrição do produto
     */
    @FXML
    private TextField textFieldDescricao;

    /**
     * textFieldNome usado para receber o nome do produto
     */
    @FXML
    private TextField textFieldNome;

    /**
     * textFieldPreco usado para receber o preço do produto
     */
    @FXML
    private TextField textFieldPreco;

    /**
     * textFieldQuantidade usado para receber a quantidade do produto
     */
    @FXML
    private TextField textFieldQuantidade;

    /**
     * paneCamposProduto usado para mostrar ou esconder os campos de produto
     */
    @FXML
    private Pane paneCamposProduto;

    /**
     * paneTabelaProduto usado para mostrar ou esconder a tabela de produto
     */
    private boolean mostrarEsconderCampos = true;

    /**
     * paneTabelaProduto usado para mostrar ou esconder a tabela de produto
     */
    private boolean mostrarEsconderTabela = true;

    /**
     * listaProdutos usado para receber a lista de produtos
     */
    private ListaProdutos listaProdutos;

    /**
    * Método usado para inicializar a coluna codigo, nome, preço, quantidade e descrição e também a lista de produtos
    */
    @FXML
    void initialize() {
        tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<Produto, Integer>("codigo"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<Produto, String>("nome"));
        tableColumnPreco.setCellValueFactory(new PropertyValueFactory<Produto, Double>("preco"));
        tableColumnQuantidade.setCellValueFactory(new PropertyValueFactory<Produto, Double>("quantidade"));
        tableColumnDescricao.setCellValueFactory(new PropertyValueFactory<Produto, String>("descricao"));

        listaProdutos = ControllerMenuPrincipal.getListaProdutos();
    }

    /**
    * Método usado para visualizar as informações de um produto
    * @param event evento de clique no botão
    */
    @FXML
    void infoProduto(ActionEvent event) {
        String codigo = textFieldCodigo.getText();

        try {

            int codigoInt = 0;

            Produto produto = null;

            String nomeProduto = null;
            String descricaoProduto = null;
            double precoProduto = 0;
            double quantidadeProduto = 0;

            if(codigo.trim().isEmpty()) {
                throw new CampoVazioException("Preencha o campo Código");
            }

            try {
                codigoInt = Integer.parseInt(codigo);
            } catch (Exception e) {
                throw new CodigoProdutoNotSupportedException("Código deve ser um inteiro");
            }

            if(codigoInt < 0) {
                throw new CodigoProdutoNotSupportedException("Código deve ser um inteiro positivo");
            }

            try {
                produto = listaProdutos.getProduto(codigoInt);
            } catch (Exception e) {
                throw e;
            }

            nomeProduto = produto.getNome();
            descricaoProduto = produto.getDescricao();
            precoProduto = produto.getPreco();
            quantidadeProduto = produto.getQuantidade();

            textFieldNome.setText(nomeProduto);
            textFieldDescricao.setText(descricaoProduto);
            textFieldPreco.setText(precoProduto + "");
            
            if(produto instanceof ProdutoUnidade) {
                radioButtonFracionado.setSelected(false);
                radioButtonUnidade.setSelected(true);
                textFieldQuantidade.setText((int) quantidadeProduto + "");
            } else if(produto instanceof ProdutoFracionado) {
                radioButtonFracionado.setSelected(true);
                radioButtonUnidade.setSelected(false);
                textFieldQuantidade.setText(quantidadeProduto + "");
            }
        } catch (CampoVazioException | CodigoProdutoNotSupportedException | ProdutoNotFoundException e) {
            alertInterface("ERRO", e.getMessage(), AlertType.ERROR);
        } catch (Exception e) {
            alertInterface("ERRO", "Ocorreu um erro inesperado", AlertType.ERROR);
            System.out.println(e.getMessage());
        }
    }

    /**
    * Método usado para mostrar ou esconder os campos de texto
    * @param event evento de clicar no botão
    */
    @FXML
    void mostrarInformacaoProduto(ActionEvent event) {
        if(tableViewInfoCompleta.isVisible()) {
            tableViewInfoCompleta.setVisible(false);
            mostrarEsconderTabela = true;
        } 

        paneCamposProduto.setVisible(mostrarEsconderCampos);
        mostrarEsconderCampos = !mostrarEsconderCampos;
    }

    /**
    * Método usado para visualizar as informções de todos os produtos
    * @param event evento de clique no botão
    */
    @FXML
    void informacaoTodosProdutos(ActionEvent event) {
        if(paneCamposProduto.isVisible()) {
            paneCamposProduto.setVisible(false);
            mostrarEsconderCampos = true;
        } 

        tableViewInfoCompleta.setVisible(mostrarEsconderTabela);
        mostrarEsconderTabela = !mostrarEsconderTabela;

        if(tableViewInfoCompleta.isVisible()) {
            ObservableList<Produto> observableList = FXCollections.observableArrayList();

            try {

                if(listaProdutos.isEmpty()) {
                    throw new ListaVaziaException("Não há produtos cadastrados");
                }
        
                for(Produto produto : listaProdutos.getArray()) {
                    observableList.add(produto);
                }

            } catch (ListaVaziaException e) {
                alertInterface("ERRO", e.getMessage(), AlertType.ERROR);	
            } catch (Exception e) {
                alertInterface("ERRO", "Ocorreu um erro inesperado", AlertType.ERROR);
                System.out.println(e.getMessage());
            }

            tableViewInfoCompleta.setItems(observableList);
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
            alertInterface("ERRO", "Não foi possível voltar para o menu principal", AlertType.ERROR);
        }
    }

    /**
    * Método usado para limpar os campos de texto
    * @param event evento de clicar no botão
    */
    @FXML
    void limparCampos(ActionEvent event) {
        textFieldCodigo.clear();
        textFieldNome.clear();
        textFieldDescricao.clear();
        textFieldPreco.clear();
        textFieldQuantidade.clear();
        radioButtonUnidade.setSelected(false);
        radioButtonFracionado.setSelected(false);
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
     * Efeito de hover ao tirar o mouse do botão de mostrar informação de um produto
     * @param event efeito de hover ao tirar o mouse do botão
     */
    @FXML
    void notHoverBtnInformacaoProduto(MouseEvent event) {
        btnInformacaoProduto.setStyle("-fx-background-color: #2b6b2a;-fx-cursor: hand; -fx-background-radius: 5;");
    }

    /**
     * Efeito de hover ao tirar o mouse do botão de mostrar informção de todos os produtos
     * @param event efeito de hover ao tirar o mouse do botão
     */
    @FXML
    void notHoverBtnInformacaoTodosProdutos(MouseEvent event) {
        btnInformacaoTodosProdutos.setStyle("-fx-background-color: #2b6b2a;-fx-cursor: hand; -fx-background-radius: 5;");
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
     * Efeito de hover ao tirar o mouse no botão de procurar
     * @param event evento de hover ao tirar o mouse no botão
     */
    @FXML
    void notHoverBtnPesquisar(MouseEvent event) {
        btnPesquisar.setStyle("-fx-background-color: #2b6b2a;-fx-cursor: hand; -fx-background-radius: 5;");
    }

    /**
     * Efeito de hover ao passar o mouse do botão de limpar
     * @param event efeito de hover ao passar o mouse do botão
     */
    @FXML
    void hoverBtnInformacaoProduto(MouseEvent event) {
        btnInformacaoProduto.setStyle("-fx-background-color: white;-fx-cursor: hand; -fx-background-radius: 5; -fx-text-fill: #2b6b2a;");
    }

    /**
     * Efeito de hover ao passar o mouse do botão de mostrar informações de todos os produtos
     * @param event efeito de hover ao passar o mouse do botão
     */
    @FXML
    void hoverBtnInformacaoTodosProdutos(MouseEvent event) {
        btnInformacaoTodosProdutos.setStyle("-fx-background-color: white;-fx-cursor: hand; -fx-background-radius: 5; -fx-text-fill: #2b6b2a;");
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
     * Efeito de hover ao passar o mouse do botão de procurar
     * @param event efeito de hover ao passar o mouse do botão
     */
    @FXML
    void hoverBtnPesquisar(MouseEvent event) {
        btnPesquisar.setStyle("-fx-background-color: white;-fx-cursor: hand; -fx-background-radius: 5; -fx-text-fill: #245823");
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

     /**
     * Efeito de hover ao tirar o mouse no botão de voltar
     * @param event evento de hover ao tirar o mouse no botão
     */
    @FXML
    void notHoverBtnVoltar(MouseEvent event) {
        btnVoltar.setImage(new Image("com/views/images/pngVoltar.png"));
        btnVoltar.setStyle("-fx-cursor: hand;");
    }

}

