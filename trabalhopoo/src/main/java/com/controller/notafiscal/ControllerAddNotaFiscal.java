package com.controller.notafiscal;

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
import com.exceptions.lista.ListaVaziaException;
import com.exceptions.notafiscal.AddNotaFiscalException;
import com.exceptions.notafiscal.DataNotSupportedException;
import com.exceptions.produto.CodigoProdutoNotSupportedException;
import com.exceptions.produto.ProdutoNotFoundException;
import com.exceptions.produto.QuantidadeNotSupportedException;

import com.listas.ListaNotaFiscal;
import com.listas.ListaProdutos;

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
 * Classe responsável por controlar a tela de adicionar nota fiscal
 * 
 * @author Ricardo, Tales, Mateus, Mauricio
 * @since jan 2023
 */
public class ControllerAddNotaFiscal {

    /**
     * btnAdicionar usado para adicionar um produto
     */
    @FXML
    private Button btnAdicionar;

    /**
     * btnConcluir usado para concluir a nota fiscal
     */
    @FXML
    private Button btnConcluir;

    /**
     * btnLimpar usado para limpar os campos
     */
    @FXML
    private Button btnLimpar;

    /**
     * btnAlterarProduto usado para alterar um produto
     */
    @FXML
    private Button btnAlterarProduto;

    /**
     * btnRemover usado para remover um produto
     */
    @FXML
    private Button btnRemover;

    /**
     * btnVoltar usado para voltar para a tela anterior
     */
    @FXML
    private ImageView btnVoltar;

    /**
     * tableProdutos usado para mostrar os produtos
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
    private TableColumn<Item, Double> tableColumnQuantidade;

    /**
     * tableColumnDescricao usado para mostrar a descrição do produto
     */
    @FXML
    private TableColumn<Item, String> tableColumnDescricao;

    /**
     * rootPane usado para mostrar a tela
     */
    @FXML
    private AnchorPane rootPane;

    /**
     * textFieldCodigo usado para receber o código do produto
     */
    @FXML
    private TextField textFieldCodigo;

    /**
     * textFieldQuantidade usado para receber a quantidade do produto
     */
    @FXML
    private TextField textFieldQuantidade;

    /**
     * datePickerVenda usado para receber a data da venda
     */
    @FXML
    private DatePicker datePickerVenda;

    /**
     * listaProdutos usado para receber a lista de produtos
     */
    private ListaProdutos listaProdutos;

    /**
     * listaNotaFiscal usado para receber a lista de notas fiscais
     */
    private ListaNotaFiscal listaNotaFiscal;

    /**
     * listaItem usado para receber a lista de itens
     */
    private ListaItem listaItem;

    /**
     * Método usado para inicializar a coluna codigo, nome, preço, quantidade e
     * descrição e a lista de produtos e notas fiscais
     *
     */
    @FXML
    void initialize() {
        tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<Item, Integer>("codigo"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<Item, String>("nome"));
        tableColumnPreco.setCellValueFactory(new PropertyValueFactory<Item, Double>("preco"));
        tableColumnQuantidade.setCellValueFactory(new PropertyValueFactory<Item, Double>("quantidade"));
        tableColumnDescricao.setCellValueFactory(new PropertyValueFactory<Item, String>("descricao"));

        listaProdutos = ControllerMenuPrincipal.getListaProdutos();
        listaNotaFiscal = ControllerMenuPrincipal.getListaNotaFiscal();
    }

    /**
     * Método usado para adicionar um produto na nota fiscal, usando o código e a
     * quantidade como parâmetros
     *
     * @param event evento de clicar no botao
     */
    @FXML
    void adicionarProduto(ActionEvent event) {

        String codigo = textFieldCodigo.getText();
        String quantidade = textFieldQuantidade.getText();

        try {

            double quantidadeDouble = 0;
            double quantidadeTotal = 0;

            boolean flag = true;

            int codigoInt = 0;

            Produto produto = null;
            ObservableList<Item> observableList = null;

            Item item = null;
            Item itemLista = null;

            if (codigo.trim().isEmpty() || codigo == null) {
                throw new CampoVazioException("O campo código não pode estar vazio");
            }

            if (quantidade.trim().isEmpty() || quantidade == null) {
                throw new CampoVazioException("O campo quantidade não pode estar vazio");
            }

            try {
                codigoInt = Integer.parseInt(codigo);
            } catch (Exception e) {
                throw new CodigoProdutoNotSupportedException("O campo código deve ser um número");
            }

            try {
                quantidadeDouble = Double.parseDouble(quantidade);
            } catch (Exception e) {
                throw new QuantidadeNotSupportedException("O campo quantidade deve ser um número");
            }

            if (quantidadeDouble <= 0) {
                throw new QuantidadeNotSupportedException("O campo quantidade deve ser maior que zero");
            }

            if (codigoInt <= 0) {
                throw new CodigoProdutoNotSupportedException("O campo código deve ser maior que zero");
            }

            if (tableProdutos.getItems().size() == 0) {
                listaItem = new ListaItem();
            }

            try {
                produto = listaProdutos.getProduto(codigoInt);
            } catch (Exception e) {
                throw e;
            }

            observableList = tableProdutos.getItems();

            if (produto instanceof ProdutoUnidade) {
                int quantidadeInt = (int) quantidadeDouble;

                if (quantidadeInt != quantidadeDouble) {
                    throw new QuantidadeNotSupportedException("O campo quantidade deve ser um número inteiro");
                }
            }

            for (int i = 0; i < observableList.size(); i++) {
                itemLista = observableList.get(i);

                if (itemLista.getCodigo() == produto.getCodigo()) {
                    quantidadeTotal += itemLista.getQuantidade();
                    if (quantidadeTotal + quantidadeDouble > produto.getQuantidade()) {
                        throw new QuantidadeNotSupportedException("A quantidade de produtos em estoque é menor que a quantidade solicitada");
                    }
                    observableList.remove(itemLista);
                    itemLista.setQuantidade(itemLista.getQuantidade() + quantidadeDouble);
                    flag = false;
                    break;
                }
            }

            if (flag) {
                if (produto.getQuantidade() < quantidadeDouble) {
                    throw new QuantidadeNotSupportedException("Quantidade de produtos no estoque é menor que a quantidade que deseja vender");
                }
                item = new Item(produto, quantidadeDouble);
                listaItem.addItem(item);
                observableList.add(item);
            } else {
                observableList.add(itemLista);
            }

            limparCampos(null);
            tableProdutos.setItems(observableList);

        } catch (CampoVazioException | CodigoProdutoNotSupportedException | QuantidadeNotSupportedException | ProdutoNotFoundException e) {
            alertInterface("ERRO", e.getMessage(), AlertType.ERROR);
        } catch (Exception e) {
            alertInterface("ERRO", "Ocorreu um erro inesperado", AlertType.ERROR);
            System.out.println(e.getMessage());
        }

    }

    /**
     * Método usado para adicionar uma nota fiscal, usando como parametros a lista
     * de produtos
     *
     * @param event evento de clicar no botao
     */
    @FXML
    void adicionarVenda(ActionEvent event) {
        ObservableList<Item> observableList = tableProdutos.getItems();

        try {

            LocalDate localDate = null;
            Date date = null;
            Calendar dataCalendar = null;

            NotaFiscal notaFiscal = null;

            String codigoNotaFiscal = null;
            String dataString = null;
            int variedadeProdutos = 0;
            double totalVendaNotaFiscal = 0;

            final String mensagemSucesso;

            if (tableProdutos.getItems().size() == 0) {
                throw new ListaVaziaException("Não é possível salvar uma nota fiscal sem produtos");
            }

            if (listaItem.isEmpty()) {
                throw new ListaVaziaException("Não há itens na lista de itens");
            }

            if (datePickerVenda.getValue() == null) {
                throw new DataNotSupportedException("O campo data não pode estar vazio");
            }

            localDate = datePickerVenda.getValue();

            date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            dataCalendar = Calendar.getInstance();
            dataCalendar.setTime(date);

            for (Item item : observableList) {
                double quantidadeSub = item.getQuantidade();
                int codigoProduto = item.getCodigo();

                listaProdutos.subQuantidade(codigoProduto, quantidadeSub);
            }

            notaFiscal = new NotaFiscal(dataCalendar, listaItem);

            try {
                listaNotaFiscal.addNotaFiscal(notaFiscal);
            } catch (Exception e) {
                throw e;
            }

            codigoNotaFiscal = notaFiscal.getCodigo() + "";
            dataString = notaFiscal.getDataFormatada();
            variedadeProdutos = tableProdutos.getItems().size();
            try {
                totalVendaNotaFiscal = notaFiscal.getTotal();
            } catch (Exception e) {
                throw e;
            }
            
            textFieldCodigo.clear();
            textFieldQuantidade.clear();
            tableProdutos.getItems().clear();
            datePickerVenda.setValue(null);

            mensagemSucesso = "Venda adicionada com sucesso.\n\nCódigo da nota fiscal: " + codigoNotaFiscal +
                    "\nData da venda: " + dataString + "\nVariedade de produtos: " + variedadeProdutos +
                    "\nTotal da nota fiscal: R$" + totalVendaNotaFiscal;

            alertInterface("SUCESSO", mensagemSucesso, AlertType.INFORMATION);

        } catch (DataNotSupportedException | ListaVaziaException | AddNotaFiscalException e) {
            alertInterface("ERRO", e.getMessage(), AlertType.ERROR);
        } catch (Exception e) {
            alertInterface("ERRO", "Ocorreu um erro inesperado", AlertType.ERROR);
            System.out.println(e.getMessage());
        }
    }

    /**
     * Método usado para alterar um produto da lista
     * 
     * @param event evento de clicar no botão
     */
    @FXML
    void alterarProduto(ActionEvent event) {

        try {

            int selectedIndex = tableProdutos.getSelectionModel().getSelectedIndex();
            Item item = tableProdutos.getItems().get(selectedIndex);

            textFieldCodigo.setText(item.getCodigo() + "");

            if (item.getProduto() instanceof ProdutoUnidade) {
                textFieldQuantidade.setText((int) item.getQuantidade() + "");
            } else {
                textFieldQuantidade.setText(item.getQuantidade() + "");
            }

            tableProdutos.getItems().remove(selectedIndex);
        } catch (IndexOutOfBoundsException e) {
            alertInterface("ERRO", "Selecione um produto para alterar", AlertType.ERROR);
        } catch (Exception e) {
            alertInterface("ERRO", "Ocorreu um erro inesperado", AlertType.ERROR);
            System.out.println(e.getMessage());
        }
    }

    /**
     * Método usado para remover um produto da lista
     * 
     * @param event evento de clicar no botão
     */
    @FXML
    void removerProduto(ActionEvent event) {
        try {
            int selectedIndex = tableProdutos.getSelectionModel().getSelectedIndex();
            tableProdutos.getItems().remove(selectedIndex);
        } catch (IndexOutOfBoundsException e) {
            alertInterface("ERRO", "Selecione um produto para remover", AlertType.ERROR);
        } catch (Exception e) {
            alertInterface("ERRO", "Ocorreu um erro inesperado", AlertType.ERROR);
            System.out.println(e.getMessage());
        }
    }

    /**
     * Método usado para limpar os campos de texto
     * 
     * @param event evento de clicar no botão
     */
    @FXML
    void limparCampos(ActionEvent event) {
        textFieldCodigo.clear();
        textFieldQuantidade.clear();
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

    /**
     * Efeito de hover ao tirar o mouse no botão de alterar
     * 
     * @param event evento de hover ao passar o mouse no botão
     */
    @FXML
    void notHoverBtnAlterar(MouseEvent event) {
        btnAlterarProduto.setStyle("-fx-background-color: #807d0a;-fx-cursor: hand; -fx-background-radius: 5;");
    }

    /**
     * Efeito de hover ao passar o mouse no botão de alterar
     * 
     * @param event evento de hover ao passar o mouse no botão
     */
    @FXML
    void hoverBtnAlterar(MouseEvent event) {
        btnAlterarProduto.setStyle("-fx-background-color: white;-fx-cursor: hand; -fx-background-radius: 5; -fx-text-fill: #676508;");
    }

    /**
     * Efeito de hover ao tirar o mouse no botão de remover
     * 
     * @param event evento de hover ao passar o mouse no botão
     */
    @FXML
    void notHoverBtnRemover(MouseEvent event) {
        btnRemover.setStyle("-fx-background-color: #7d2727;-fx-cursor: hand; -fx-background-radius: 5;");
    }

    /**
     * Efeito de hover ao passar o mouse no botão de remover
     * 
     * @param event evento de hover ao passar o mouse no botão
     */
    @FXML
    void hoverBtnRemover(MouseEvent event) {
        btnRemover.setStyle("-fx-background-color: white;-fx-cursor: hand; -fx-background-radius: 5; -fx-text-fill: #682121;");
    }

    /**
     * Efeito de hover ao tirar o mouse no botão de adicionar
     * 
     * @param event evento de hover ao tirar o mouse no botão
     */
    @FXML
    void notHoverBtnAdicionar(MouseEvent event) {
        btnAdicionar.setStyle("-fx-background-color: #2b6b2a;-fx-cursor: hand; -fx-background-radius: 5;");
    }

    /**
     * Efeito de hover ao tirar o mouse no botão de limpar
     * 
     * @param event evento de hover ao tirar o mouse no botão
     */
    @FXML
    void notHoverBtnLimpar(MouseEvent event) {
        btnLimpar.setStyle("-fx-background-color: #747474;-fx-cursor: hand; -fx-background-radius: 5;");
    }

    /**
     * Efeito de hover ao tirar o mouse no botão de concluir
     * 
     * @param event evento de hover ao tirar o mouse no botão
     */
    @FXML
    void notHoverBtnConcluir(MouseEvent event) {
        btnConcluir.setStyle("-fx-background-color: #2b6b2a;-fx-cursor: hand; -fx-background-radius: 5;");
    }

    /**
     * Efeito de hover ao passar o mouse no botão de adicionar
     * 
     * @param event evento de hover ao passar o mouse no botão
     */
    @FXML
    void hoverBtnAdicionar(MouseEvent event) {
        btnAdicionar.setStyle("-fx-background-color: white;-fx-cursor: hand; -fx-background-radius: 5; -fx-text-fill: #245823;");
    }

    /**
     * Efeito de hover ao passar o mouse no botão de concluir
     * 
     * @param event evento de hover ao passar o mouse no botão
     */
    @FXML
    void hoverBtnConcluir(MouseEvent event) {
        btnConcluir.setStyle("-fx-background-color: white;-fx-cursor: hand; -fx-background-radius: 5; -fx-text-fill: #245823;");
    }

    /**
     * Efeito de hover ao tirar o mouse do botão de limpar
     * 
     * @param event efeito de hover ao tirar o mouse do botão
     */
    @FXML
    void hoverBtnLimpar(MouseEvent event) {
        btnLimpar.setStyle("-fx-background-color: white;-fx-cursor: hand; -fx-background-radius: 5; -fx-text-fill: #686868;");
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

    /**
     * Efeito de hover ao tirar o mouse no botão de concluir
     * 
     * @param event evento de hover ao tirar o mouse no botão
     */
    @FXML
    void notHoverBtnVoltar(MouseEvent event) {
        btnVoltar.setImage(new Image("com/views/images/pngVoltar.png"));
        btnVoltar.setStyle("-fx-cursor: hand;");
    }

}