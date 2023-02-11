package com.controller.notafiscal;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import java.util.Calendar;
import java.util.Date;

import com.baseclasse.Item;
import com.baseclasse.ListaItem;
import com.baseclasse.NotaFiscal;
import com.baseclasse.Produto;
import com.baseclasse.ProdutoUnidade;

import com.controller.ControllerMenuPrincipal;
import com.exceptions.geral.CampoVazioException;
import com.exceptions.notafiscal.CodigoNotaFiscalNotSupportedException;
import com.exceptions.notafiscal.NotaFiscalNotFoundException;
import com.exceptions.produto.CodigoProdutoNotSupportedException;
import com.exceptions.produto.ProdutoNotFoundException;
import com.exceptions.produto.QuantidadeNotSupportedException;
import com.listas.ListaNotaFiscal;
import com.listas.ListaProdutos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
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
 * Classe responsável por controlar a tela de alterar nota fiscal
 * 
 * @author Ricardo, Tales, Mateus, Mauricio
 * @since jan 2023
 */
public class ControllerAlterarNotaFiscal {

    @FXML
    private Button btnAlterarProduto;

    /**
     * btnLimpar usado para limpar os campos
     */
    @FXML
    private Button btnLimpar;

    /**
     * btnProcurar usado para procurar uma nota fiscal
     */
    @FXML
    private Button btnProcurar;

    /**
     * btnRemover usado para remover um produto da nota fiscal
     */
    @FXML
    private Button btnRemover;

    /**
     * btnSalvar usado para salvar a nota fiscal
     */
    @FXML
    private Button btnSalvar;

    /**
     * btnAdicionarProduto usado para adicionar um produto na nota fiscal
     */
    @FXML
    private Button btnAdicionarProduto;

    /**
     * datePickerVenda usado para selecionar a data da venda
     */
    @FXML
    private DatePicker datePickerVenda;

    /**
     * btnVoltar usado para voltar para a tela anterior
     */
    @FXML
    private ImageView btnVoltar;

    /**
     * rootPane usado para carregar a tela
     */
    @FXML
    private AnchorPane rootPane;

    /**
     * salvarAltera usado para salvar a alteração da nota fiscal
     */
    @FXML
    private Pane salvarAltera;

    /**
     * textFieldCodigo usado para receber o código da nota fiscal
     */
    @FXML
    private TextField textFieldCodigo;

    /**
     * textFieldCodigoProduto usado para receber o código do produto
     */
    @FXML
    private TextField textFieldCodigoProduto;

    /**
     * textFieldQuantidade usado para receber a quantidade do produto
     */
    @FXML
    private TextField textFieldQuantidade;

    /**
     * tableProdutos usado para mostrar os produtos da nota fiscal
     */
    @FXML
    private TableView<Item> tableProdutos;

    /**
     * tableColumnCodigo usado para mostrar o código do produto
     */
    @FXML
    private TableColumn<Item, Integer> tableColumnCodigo;

    /**
     * tableColumnNome usado para mostrar o nome do produto
     */
    @FXML
    private TableColumn<Item, String> tableColumnNome;

    /**
     * tableColumnPreco usado para mostrar o preço do produto
     */
    @FXML
    private TableColumn<Item, Double> tableColumnPreco;

    /**
     * tableColumnQuantidade usado para mostrar a quantidade do produto
     */
    @FXML
    private TableColumn<Item, Integer> tableColumnQuantidade;

    @FXML
    private TableColumn<Item, String> tableColumnDescricao;

    /**
     * listaProdutos usado para receber a lista de notas fiscais
     */
    private ListaNotaFiscal listaNotaFiscal;

    /**
     * listaProdutos usado para receber a lista de produtos
     */
    private ListaProdutos listaProdutos;

    /**
     * listaItemAntes usado para receber a lista de itens antes da alteração
     */
    private ListaItem listaItemAntes;

    /**
     * initialize usado para inicializar a coluna codigo, nome, preço e quantidade e
     * a lista de notas fiscais e produtos
     */
    @FXML
    void initialize() {
        tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<Item, Integer>("codigo"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<Item, String>("nome"));
        tableColumnPreco.setCellValueFactory(new PropertyValueFactory<Item, Double>("preco"));
        tableColumnQuantidade.setCellValueFactory(new PropertyValueFactory<Item, Integer>("quantidade"));
        tableColumnDescricao.setCellValueFactory(new PropertyValueFactory<Item, String>("descricao"));

        listaNotaFiscal = ControllerMenuPrincipal.getListaNotaFiscal();
        listaProdutos = ControllerMenuPrincipal.getListaProdutos();

        listaItemAntes = new ListaItem();
    }

    /**
     * Método usado para alterar um produto da nota fiscal
     * 
     * @param event evento de clicar no botão
     */
    @FXML
    void alterarProduto(ActionEvent event) {
        try {
            int selectedIndex = tableProdutos.getSelectionModel().getSelectedIndex();
            Item item = tableProdutos.getItems().get(selectedIndex);

            textFieldCodigoProduto.setText(item.getCodigo() + "");

            if (item.getProduto() instanceof ProdutoUnidade) {
                textFieldQuantidade.setText((int) item.getQuantidade() + "");
            } else {
                textFieldQuantidade.setText(item.getQuantidade() + "");
            }

            tableProdutos.getItems().remove(selectedIndex);

        } catch (IndexOutOfBoundsException e) {
            alertInterface("ERRO", "Selecione um item para alterar", AlertType.ERROR);
        } catch (Exception e) {
            alertInterface("ERRO", "Ocorreu um erro inesperado", AlertType.ERROR);
            System.out.println(e.getMessage());
        }
    }

    /**
     * Método usado para adicionar um produto na nota fiscal
     * 
     * @param event evento de clicar no botão
     */
    @FXML
    void adicionarProduto(ActionEvent event) {
        String codigoNF = textFieldCodigo.getText();
        String codigoProduto = textFieldCodigoProduto.getText();
        String quantidade = textFieldQuantidade.getText();

        try {

            int codigoNFInt = 0;
            int codigoProdutoInt = 0; 

            boolean flag = true;

            double quantidadeDouble = 0;
            double quantidadeVendidaNFOriginal = 0;
            double quantidadeNFAntes = 0;

            Produto produto = null;
            NotaFiscal notaFiscal = null;
            ObservableList<Item> listaProdutosNotaFiscal = null;

            if (codigoNF.trim().isEmpty() || codigoNF == null) {
                throw new CampoVazioException("Campo código nota fiscal deve ser preenchido com um número inteiro");
            }

            if (codigoProduto.trim().isEmpty() || codigoProduto == null) {
                throw new CampoVazioException("Campo código deve ser preenchido com um número inteiro");
            }

            if (quantidade.trim().isEmpty() || quantidade == null) {
                throw new CampoVazioException("Campo quantidade deve ser preenchido com um número inteiro");
            }

            try {
                codigoProdutoInt = Integer.parseInt(codigoProduto);
            } catch (Exception e) {
                throw new CodigoProdutoNotSupportedException(
                        "Campo código produto deve ser preenchido com um número inteiro");
            }

            try {
                codigoNFInt = Integer.parseInt(codigoNF);
            } catch (Exception e) {
                throw new CodigoNotaFiscalNotSupportedException(
                        "Campo código nota fiscal deve preenchido com um número inteiro");
            }

            try {
                quantidadeDouble = Double.parseDouble(quantidade);
            } catch (Exception e) {
                throw new QuantidadeNotSupportedException("Campo quantidade deve ser preenchido com um número inteiro");
            }

            if (codigoNFInt <= 0) {
                throw new CodigoNotaFiscalNotSupportedException(
                        "Campo código nota fiscal deve ser preenchido com um número inteiro");
            }

            if (codigoProdutoInt <= 0) {
                throw new CodigoProdutoNotSupportedException("Campo código deve ser preenchido com um número inteiro");
            }

            if (quantidadeDouble <= 0) {
                throw new QuantidadeNotSupportedException("Campo quantidade deve ser preenchido com um número inteiro");
            }

            try {
                produto = listaProdutos.getProduto(codigoProdutoInt);
            } catch (Exception e) {
                throw e;
            }

            if (produto instanceof ProdutoUnidade) {
                int quantidadeInt = (int) quantidadeDouble;

                if (quantidadeDouble != quantidadeInt) {
                    throw new QuantidadeNotSupportedException(
                            "Campo quantidade deve ser preenchido com um número inteiro");
                }
            }

            try {
                notaFiscal = listaNotaFiscal.getNotaFiscal(codigoNFInt);
            } catch (Exception e) {
                throw e;
            }

            for (Item item : notaFiscal.getItens().getArray()) {
                if (item.getCodigo() == produto.getCodigo()) {
                    quantidadeVendidaNFOriginal = (int) item.getQuantidade();
                }
            }

            listaProdutosNotaFiscal = tableProdutos.getItems();

            for (Item item : listaProdutosNotaFiscal) {
                if (item.getProduto().getCodigo() == produto.getCodigo()) {
                    double quantidadeTotalEstoque = produto.getQuantidade() + quantidadeVendidaNFOriginal;

                    double quantidadeAgoraTabela = item.getQuantidade();
                    double quantidadeQueroAdicionar = quantidadeDouble;

                    if (quantidadeTotalEstoque - (quantidadeAgoraTabela + quantidadeQueroAdicionar) < 0) {
                        throw new QuantidadeNotSupportedException(
                                "Quantidade de produtos no estoque é menor que a quantidade que deseja vender");
                    }

                    item.setQuantidade(item.getQuantidade() + quantidadeDouble);
                    tableProdutos.refresh();
                    flag = false;
                }
            }

            try {
                quantidadeNFAntes = listaItemAntes.getItem(codigoProdutoInt).getQuantidade();
            } catch (Exception e) {
                quantidadeNFAntes = 0;
            }

            if (flag) {
                if (listaProdutos.getProduto(codigoProdutoInt).getQuantidade() < quantidadeDouble - quantidadeNFAntes) {
                    throw new QuantidadeNotSupportedException(
                            "Quantidade de produtos no estoque é menor que a quantidade que deseja vender");
                }

                Item itemNotaFiscal = new Item(produto, quantidadeDouble);
                listaProdutosNotaFiscal.add(itemNotaFiscal);
                tableProdutos.refresh();
            }

            textFieldCodigoProduto.clear();
            textFieldQuantidade.clear();

        } catch (CampoVazioException | CodigoNotaFiscalNotSupportedException | CodigoProdutoNotSupportedException
                | QuantidadeNotSupportedException | NotaFiscalNotFoundException | ProdutoNotFoundException e) {
            alertInterface("ERRO", e.getMessage(), AlertType.ERROR);
        } catch (Exception e) {
            alertInterface("ERRO", "Ocorreu um erro inesperado", AlertType.ERROR);
            System.out.println(e.getMessage());
        }
    }

    /**
     * Método para procurar uma venda usando o codigo como parâmetro
     * 
     * @param event Evento de clique no botão
     */
    @FXML
    void procurarVenda(ActionEvent event) {
        String codigo = textFieldCodigo.getText();

        try {

            int codigoInt = 0;

            NotaFiscal notaFiscal = null;

            Calendar data = null;
            Instant instant = null;
            LocalDate dataLocalDate = null;

            ObservableList<Item> listaProdutosNotaFiscal = FXCollections.observableArrayList();;

            if (codigo.trim().isEmpty() || codigo == null) {
                throw new Exception("Campo código deve ser preenchido com um número inteiro");
            }

            try {
                codigoInt = Integer.parseInt(codigo);
            } catch (Exception e) {
                throw new Exception("Campo código deve ser preenchido com um número inteiro");
            }

            if (codigoInt <= 0) {
                throw new Exception("Campo código deve ser preenchido com um número inteiro");
            }

            try {
                notaFiscal = listaNotaFiscal.getNotaFiscal(codigoInt);
            } catch (Exception e) {
                throw e;
            }

            textFieldCodigo.setText(notaFiscal.getCodigo() + "");

            data = notaFiscal.getData();
            instant = data.toInstant();
            dataLocalDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();

            datePickerVenda.setValue(dataLocalDate);

            for (Item item : notaFiscal.getItens().getArray()) {
                Item itemNotaFiscal = new Item(item.getProduto(), item.getQuantidade());
                listaProdutosNotaFiscal.add(itemNotaFiscal);
            }

            tableProdutos.setItems(listaProdutosNotaFiscal);
            listaItemAntes = notaFiscal.getItens();
        } catch (Exception e) {
            alertInterface("ERRO", e.getMessage(), AlertType.ERROR);
        }
    }

    /**
     * Método para remover um produto da nota fiscal
     * 
     * @param event Evento de clique no botão
     */
    @FXML
    void removerProduto(ActionEvent event) {
        try {
            int selectedIndex = tableProdutos.getSelectionModel().getSelectedIndex();
            tableProdutos.getItems().remove(selectedIndex);
        } catch (Exception e) {
            alertInterface("ERRO", "Selecione um item para remover", AlertType.ERROR);
        }
    }

    /**
     * Método para salvar as alterações feitas na nota fiscal
     * 
     * @param event Evento de clique no botão
     */
    @FXML
    void salvarAlteracaoProduto(ActionEvent event) {

        String codigoNF = textFieldCodigo.getText();

        try {

            int codigoNFInt = 0;
            NotaFiscal notaFiscal = null;

            ListaItem listaItem = new ListaItem();

            LocalDate localDate = null;
            Date date = null;
            Calendar dataCalendar = null;

            if (codigoNF.trim().isEmpty() || codigoNF == null) {
                throw new Exception("Campo código não pode ser vazio");
            }

            try {
                codigoNFInt = Integer.parseInt(codigoNF);
            } catch (Exception e) {
                throw new Exception("Campo código deve ser preenchido com um número inteiro");
            }

            if (codigoNFInt <= 0) {
                throw new Exception("Campo código deve ser preenchido com um número positivo");
            }

            if (tableProdutos.getItems().size() == 0) {
                throw new Exception("Não é possível salvar uma nota fiscal sem produtos");
            }

            if (datePickerVenda.getValue() == null) {
                throw new Exception("Campo data deve ser preenchido");
            }

            try {
                notaFiscal = listaNotaFiscal.getNotaFiscal(codigoNFInt);
            } catch (Exception e) {
                throw e;
            }

            for (Item item : listaItemAntes.getArray()) {
                listaProdutos.addQuantidade(item.getCodigo(), item.getQuantidade());
            }

            for (int i = 0; i < tableProdutos.getItems().size(); i++) {
                Item item = tableProdutos.getItems().get(i);

                listaProdutos.subQuantidade(item.getCodigo(), item.getQuantidade());
                listaItem.addItem(item);
            }

            localDate = datePickerVenda.getValue();

            date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            dataCalendar = Calendar.getInstance();
            dataCalendar.setTime(date);

            notaFiscal.setData(dataCalendar);
            notaFiscal.setItens(listaItem);

            alertInterface("SUCESSO", "Nota fiscal com o código " + codigoNFInt + " alterada com sucesso!",
                    AlertType.INFORMATION);
            textFieldCodigo.clear();
            textFieldCodigoProduto.clear();
            textFieldQuantidade.clear();
            datePickerVenda.setValue(null);
            tableProdutos.getItems().clear();
        } catch (Exception e) {
            alertInterface("ERRO", e.getMessage(), AlertType.ERROR);
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

    @FXML
    void notHoverBtnAdicionar(MouseEvent event) {
        btnAdicionarProduto.setStyle("-fx-background-color: #2b6b2a;-fx-cursor: hand; -fx-background-radius: 5;");
    }

    @FXML
    void hoverBtnAdicionar(MouseEvent event) {
        btnAdicionarProduto.setStyle("-fx-background-color: white;-fx-cursor: hand; -fx-background-radius: 5; -fx-text-fill: #245823;");
    }

    @FXML
    void hoverBtnAlterar(MouseEvent event) {
        btnAlterarProduto.setStyle("-fx-background-color: white;-fx-cursor: hand; -fx-background-radius: 5; -fx-text-fill: #676508;");
    }

    /**
     * Efeito de hover ao passar o mouse do botão de procurar
     * 
     * @param event efeito de hover ao passar o mouse do botão
     */
    @FXML
    void hoverBtnProcurar(MouseEvent event) {
        btnProcurar.setStyle("-fx-background-color: white;-fx-cursor: hand; -fx-background-radius: 5; -fx-text-fill: #676508;");
    }

    /**
     * Efeito de hover ao passar o mouse do botão de remover
     * 
     * @param event efeito de hover ao passar o mouse do botão
     */
    @FXML
    void hoverBtnRemover(MouseEvent event) {
        btnRemover.setStyle("-fx-background-color: white;-fx-cursor: hand; -fx-background-radius: 5; -fx-text-fill: #682121;");
    }

    /**
     * Efeito de hover ao passar o mouse do botão de salvar
     * 
     * @param event efeito de hover ao passar o mouse do botão
     */
    @FXML
    void hoverBtnSalvar(MouseEvent event) {
        btnSalvar.setStyle("-fx-background-color: white;-fx-cursor: hand; -fx-background-radius: 5; -fx-text-fill: #245823;");
    }

    /**
     * Efeito de hover ao tirar o mouse do botão de alterar
     * 
     * @param event efeito de hover ao tirar o mouse do botão
     */
    @FXML
    void notHoverBtnAlterar(MouseEvent event) {
        btnAlterarProduto.setStyle("-fx-background-color: #807d0a;-fx-cursor: hand; -fx-background-radius: 5;");
    }

    /**
     * Efeito de hover ao tirar o mouse do botão de procurar
     * 
     * @param event efeito de hover ao tirar o mouse do botão
     */
    @FXML
    void notHoverBtnProcurar(MouseEvent event) {
        btnProcurar.setStyle("-fx-background-color: #807d0a;-fx-cursor: hand; -fx-background-radius: 5;");
    }

    /**
     * Efeito de hover ao tirar o mouse do botão de remover
     * 
     * @param event efeito de hover ao tirar o mouse do botão
     */
    @FXML
    void notHoverBtnRemover(MouseEvent event) {
        btnRemover.setStyle("-fx-background-color: #7d2727;-fx-cursor: hand; -fx-background-radius: 5;");
    }

    /**
     * Efeito de hover ao tirar o mouse do botão de salvar
     * 
     * @param event efeito de hover ao tirar o mouse do botão
     */
    @FXML
    void notHoverBtnSalvar(MouseEvent event) {
        btnSalvar.setStyle("-fx-background-color: #2b6b2a;-fx-cursor: hand; -fx-background-radius: 5;");
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
     * Efeito de hover ao passar o mouse do botão de voltar
     * 
     * @param event efeito de hover ao passar o mouse do botão
     */
    @FXML
    void hoverBtnVoltar(MouseEvent event) {
        btnVoltar.setImage(new Image("com/views/images/pngVoltarHover.png"));
        btnVoltar.setStyle("-fx-cursor: hand;");
    }

}