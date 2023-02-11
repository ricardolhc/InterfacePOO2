package com.controller.notafiscal;

import java.text.SimpleDateFormat;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.baseclasse.Item;
import com.baseclasse.ListaItem;
import com.baseclasse.NotaFiscal;

import com.controller.ControllerMenuPrincipal;

import com.listas.ListaNotaFiscal;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import javafx.util.Callback;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Classe responsável por controlar a tela de visualização de notas fiscais
 * 
 * @author Ricardo, Tales, Mateus, Mauricio
 * @since jan 2023
 */
public class ControllerVisualizaVenda {

    /**
     * btnInformacaoNotaFiscal usado para mostrar as informações de uma nota fiscal
     */
    @FXML
    private Button btnInformacaoNotaFiscal;

    /**
     * btnInformacaoTodosProdutos usado para mostrar as informações de todas as
     * notas fiscais
     */
    @FXML
    private Button btnInformacaoTodasNotas;

    /**
     * btnInformacaoDiaEspecifico usado para mostrar as informações de um dia
     * específico
     */
    @FXML
    private Button btnInformacaoDiaEspecifico;

    @FXML
    private Button btnProcurarNotaFiscalDia;

    /**
     * btnVoltar usado para voltar para a tela de menu principal
     */
    @FXML
    private ImageView btnVoltar;

    /**
     * datePickerVenda usado para selecionar uma data
     */
    @FXML
    private DatePicker datePickerVenda;

    /**
     * datePickerVendaDia usado para selecionar um dia específico
     */
    @FXML
    private DatePicker datePickerVendaDia;

    /**
     * paneInformacoesUmaNota usado para mostrar as informações de uma nota fiscal
     */
    @FXML
    private Pane paneInformacoesUmaNota;

    /**
     * rootPane usado para carregar a tela
     */
    @FXML
    private AnchorPane rootPane;

    /**
     * tableColumnCodigo usado para mostrar o código do item
     */
    @FXML
    private TableColumn<Item, Integer> tableColumnCodigo;

    /**
     * tableColumnNome usado para mostrar o nome do item
     */
    @FXML
    private TableColumn<Item, String> tableColumnNome;

    /**
     * tableColumnPreco usado para mostrar o preço do item
     */
    @FXML
    private TableColumn<Item, Double> tableColumnPreco;

    /**
     * tableColumnQuantidade usado para mostrar a quantidade do item
     */
    @FXML
    private TableColumn<Item, Double> tableColumnQuantidade;

    /**
     * tableColumnDescricao usado para mostrar a descrição do item
     */
    @FXML
    private TableColumn<Item, String> tableColumnDescricao;

    /**
     * tableProdutos usado para mostrar os itens da nota fiscal
     */
    @FXML
    private TableView<Item> tableProdutos;

    /**
     * tableTodasNotas usado para mostrar todas as notas fiscais
     */
    @FXML
    private TableView<NotaFiscal> tableTodasNotas;

    /**
     * tableColumnCodigoNotaFiscalTodas usado para mostrar o código de todas as
     * notas fiscais
     */
    @FXML
    private TableColumn<NotaFiscal, Integer> tableColumnCodigoNotaFiscalTodas;

    /**
     * tableColumnDataNotaFiscalTodas usado para mostrar a data da nota fiscal
     */
    @FXML
    private TableColumn<NotaFiscal, Calendar> tableColumnDataNotaFiscalTodas;

    /**
     * tableNotasDia usado para mostrar as notas fiscais de um dia específico
     */
    @FXML
    private TableView<NotaFiscal> tableNotasDia;

    /**
     * tableColumnCodigoNotaFiscalDia usado para mostrar o código das notas fiscais
     * de um dia específico
     */
    @FXML
    private TableColumn<NotaFiscal, Integer> tableColumnCodigoNotaFiscalDia;

    /**
     * tableColumnDataNotaFiscalDia usado para mostrar a data das notas fiscais de
     * um dia específico
     */
    @FXML
    private TableColumn<NotaFiscal, Calendar> tableColumnDataNotaFiscalDia;

    /**
     * tableColumnTotalVendidoNotaFiscalDia usado para mostrar o total vendido das
     * notas fiscais de um dia específico
     */
    @FXML
    private TextField textFieldTotalVendidoNotaDia;

    /**
     * tableColumnTotalVendidoNotaFiscalTodas usado para mostrar o total vendido de
     * todas as notas fiscais
     */
    @FXML
    private TextField textFieldQuantidadeNotas;

    /**
     * textFieldCodigo usado para receber o código do produto
     */
    @FXML
    private TextField textFieldCodigo;

    /**
     * textFieldTotalVendidoNota usado para mostrar o total vendido de uma nota
     * fiscal
     */
    @FXML
    private TextField textFieldTotalVendidoNota;

    /**
     * paneDiaEspecifico usado para mostrar as informações de um dia específico
     */
    @FXML
    private Pane paneDiaEspecifico;

    /**
     * paneInformacoesTodasNotas usado para mostrar as informações de todas as notas
     * fiscais
     */
    @FXML
    private Pane paneTodasNotas;

    /**
     * paneInformacoesTodasNotas usado para mostrar as informações de todas as notas
     * fiscais
     */
    @FXML
    private Pane paneInformacoesTodasNotas;

    @FXML
    private Button btnProcurarNotaFiscalUnica;

    /**
     * informacoesUmaNotaFiscal usado para mostrar as informações de uma nota fiscal
     */
    private boolean informacoesUmaNotaFiscal = true;

    /**
     * informacoesTodasNotasFiscais usado para mostrar as informações de todas as
     * notas fiscais
     */
    private boolean informacoesTodasNotasFiscais = true;

    /**
     * informacoesDiaEspecifico usado para mostrar as informações de um dia
     * específico
     */
    private boolean informacoesDiaEspecifico = true;

    /**
     * listaNotaFiscal usado para receber a lista de notas fiscais
     */
    private ListaNotaFiscal listaNotaFiscal;

    /**
     * initialize usado para inicializar a coluna nome, preço, quantidade e
     * descrição da tabela de produtos
     */
    @FXML
    void initialize() {
        tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<Item, Integer>("codigo"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<Item, String>("nome"));
        tableColumnPreco.setCellValueFactory(new PropertyValueFactory<Item, Double>("preco"));
        tableColumnQuantidade.setCellValueFactory(new PropertyValueFactory<Item, Double>("quantidade"));
        tableColumnDescricao.setCellValueFactory(new PropertyValueFactory<Item, String>("descricao"));

        tableColumnCodigoNotaFiscalTodas.setCellValueFactory(new PropertyValueFactory<NotaFiscal, Integer>("codigo"));

        tableColumnDataNotaFiscalTodas
                .setCellValueFactory(new Callback<CellDataFeatures<NotaFiscal, Calendar>, ObservableValue<Calendar>>() {
                    @Override
                    public ObservableValue<Calendar> call(CellDataFeatures<NotaFiscal, Calendar> dadosTabela) {
                        Calendar calendar = dadosTabela.getValue().getData();
                        return new SimpleObjectProperty<Calendar>(calendar);
                    }
                });
        tableColumnDataNotaFiscalTodas
                .setCellFactory(new Callback<TableColumn<NotaFiscal, Calendar>, TableCell<NotaFiscal, Calendar>>() {
                    @Override
                    public TableCell<NotaFiscal, Calendar> call(TableColumn<NotaFiscal, Calendar> column) {
                        return new TableCell<NotaFiscal, Calendar>() {
                            private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                            @Override
                            protected void updateItem(Calendar item, boolean empty) {
                                super.updateItem(item, empty);
                                if (item == null || empty) {
                                    setText(null);
                                } else {
                                    setText(sdf.format(item.getTime()));
                                }
                            }
                        };
                    }
                });

        tableColumnCodigoNotaFiscalDia.setCellValueFactory(new PropertyValueFactory<NotaFiscal, Integer>("codigo"));

        tableColumnDataNotaFiscalDia
                .setCellValueFactory(new Callback<CellDataFeatures<NotaFiscal, Calendar>, ObservableValue<Calendar>>() {
                    @Override
                    public ObservableValue<Calendar> call(CellDataFeatures<NotaFiscal, Calendar> dadosTabela) {
                        Calendar calendar = dadosTabela.getValue().getData();
                        return new SimpleObjectProperty<Calendar>(calendar);
                    }
                });

        tableColumnDataNotaFiscalDia
                .setCellFactory(new Callback<TableColumn<NotaFiscal, Calendar>, TableCell<NotaFiscal, Calendar>>() {
                    @Override
                    public TableCell<NotaFiscal, Calendar> call(TableColumn<NotaFiscal, Calendar> column) {
                        return new TableCell<NotaFiscal, Calendar>() {
                            private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                            @Override
                            protected void updateItem(Calendar item, boolean empty) {
                                super.updateItem(item, empty);
                                if (item == null || empty) {
                                    setText(null);
                                } else {
                                    setText(sdf.format(item.getTime()));
                                }
                            }
                        };
                    }
                });

        listaNotaFiscal = ControllerMenuPrincipal.getListaNotaFiscal();
    }

    /**
     * informacaoUmaNota usado para mostrar as informações de uma nota fiscal
     * 
     * @param event evento de clicar no botão
     */
    @FXML
    void informacaoUmaNota(ActionEvent event) {
        if (paneDiaEspecifico.isVisible() || paneTodasNotas.isVisible()) {
            paneDiaEspecifico.setVisible(false);
            paneTodasNotas.setVisible(false);

            informacoesDiaEspecifico = true;
            informacoesTodasNotasFiscais = true;
        }

        paneInformacoesUmaNota.setVisible(informacoesUmaNotaFiscal);
        informacoesUmaNotaFiscal = !informacoesUmaNotaFiscal;
    }

    /**
     * informacaoTodasNotas usado para mostrar as informações de todas as notas
     * fiscais
     * 
     * @param event evento de clicar no botão
     */
    @FXML
    void informacaoTodasNotas(ActionEvent event) {

        if (paneInformacoesUmaNota.isVisible() || paneDiaEspecifico.isVisible()) {
            paneInformacoesUmaNota.setVisible(false);
            paneDiaEspecifico.setVisible(false);

            informacoesUmaNotaFiscal = true;
            informacoesDiaEspecifico = true;
        }

        paneTodasNotas.setVisible(informacoesTodasNotasFiscais);
        informacoesTodasNotasFiscais = !informacoesTodasNotasFiscais;

        try {
            if (paneTodasNotas.isVisible()) {
                ObservableList<NotaFiscal> observableList = FXCollections.observableArrayList();

                if (listaNotaFiscal.getArray().size() == 0) {
                    throw new Exception("Não há notas fiscais cadastradas");
                }

                for (NotaFiscal notaFiscal : listaNotaFiscal.getArray()) {
                    observableList.add(notaFiscal);
                }

                tableTodasNotas.setItems(observableList);
            }
        } catch (Exception e) {
            alertInterface("ERRO", e.getMessage(), AlertType.ERROR);
        }

    }

    /**
     * informacaoDiaEspecifico usado para mostrar as informações de uma nota fiscal
     * de um dia específico
     * 
     * @param event evento de clicar no botão
     */
    @FXML
    void informacaoDiaEspecifico(ActionEvent event) {
        if (paneInformacoesUmaNota.isVisible() || paneTodasNotas.isVisible()) {
            paneInformacoesUmaNota.setVisible(false);
            paneTodasNotas.setVisible(false);

            informacoesUmaNotaFiscal = true;
            informacoesTodasNotasFiscais = true;
        }

        paneDiaEspecifico.setVisible(informacoesDiaEspecifico);
        informacoesDiaEspecifico = !informacoesDiaEspecifico;
    }

    /**
     * procurarNotaFiscalDia usado para procurar uma nota fiscal de um dia
     * específico
     * 
     * @param event evento de clicar no botão
     */
    @FXML
    void procurarNotaFiscalDia(ActionEvent event) {
        try {

            if (datePickerVendaDia.getValue() == null) {
                throw new Exception("O campo de data não pode estar vazio");
            }

            LocalDate localDate = datePickerVendaDia.getValue();

            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            Calendar dataCalendar = Calendar.getInstance();
            dataCalendar.setTime(date);

            ArrayList<NotaFiscal> notasFicais = listaNotaFiscal.getNotasFiscaisPorData(dataCalendar);

            ObservableList<NotaFiscal> observableList = FXCollections.observableArrayList();

            double totalNotas = 0;

            for (NotaFiscal notaFiscal : notasFicais) {
                totalNotas += notaFiscal.getTotal();
                observableList.add(notaFiscal);
            }

            tableNotasDia.setItems(observableList);
            textFieldTotalVendidoNotaDia.setText(totalNotas + "");
            textFieldQuantidadeNotas.setText(notasFicais.size() + "");
        } catch (Exception e) {
            alertInterface("ERRO", e.getMessage(), AlertType.ERROR);
        }
    }

    /**
     * procurarNotaFiscalUnica usado para procurar uma nota fiscal específica
     * 
     * @param event evento de clicar no botão
     */
    @FXML
    void procurarNotaFiscalUnica(ActionEvent event) {
        String codigoNF = textFieldCodigo.getText();

        try {
            if (codigoNF.trim().isEmpty() || codigoNF == null) {
                throw new Exception("O campo de código da nota fiscal não pode estar vazio");
            }

            int codigoNFInt;

            try {
                codigoNFInt = Integer.parseInt(codigoNF);
            } catch (Exception e) {
                throw new Exception("O campo de código da nota fiscal deve ser um número inteiro");
            }

            if (codigoNFInt <= 0) {
                throw new Exception("O campo de código da nota fiscal deve ser um número inteiro maior que zero");
            }

            NotaFiscal notaFiscal = listaNotaFiscal.getNotaFiscal(codigoNFInt);

            double totalNota = notaFiscal.getTotal();

            Calendar data = notaFiscal.getData();
            Instant instant = data.toInstant();
            LocalDate dataLocalDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();

            ObservableList<Item> observableList = FXCollections.observableArrayList();

            ListaItem listaItem = notaFiscal.getItens();

            for (Item item : listaItem.getArray()) {
                observableList.add(item);
            }

            datePickerVenda.setValue(dataLocalDate);
            textFieldTotalVendidoNota.clear();
            textFieldTotalVendidoNota.setText(totalNota + "");
            tableProdutos.setItems(observableList);
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
     * Efeito de hover ao tirar o mouse no botão de mostrar informações de uma nota
     * fiscal
     * 
     * @param event evento de hover ao tirar o mouse no botão
     */
    @FXML
    void notHoverbtnInformacaoNotaFiscal(MouseEvent event) {
        btnInformacaoNotaFiscal.setStyle("-fx-background-color: #2b6b2a;-fx-cursor: hand; -fx-background-radius: 50;");
    }

    /**
     * Efeito de hover ao passar o mouse no botão de mostrar informações de uma nota
     * fiscal
     * 
     * @param event evento de hover ao passar o mouse no botão
     */
    @FXML
    void hoverbtnInformacaoNotaFiscal(MouseEvent event) {
        btnInformacaoNotaFiscal.setStyle("-fx-background-color: #245823;-fx-cursor: hand; -fx-background-radius: 50;");
    }

    /**
     * Efeito de hover ao tirar o mouse no botão de mostrar informações de uma nota
     * fiscal
     * 
     * @param event evento de hover ao tirar o mouse no botão
     */
    @FXML
    void notHoverBtnInformacaoTodasNotas(MouseEvent event) {
        btnInformacaoTodasNotas.setStyle("-fx-background-color: #2b6b2a;-fx-cursor: hand; -fx-background-radius: 50;");
    }

    /**
     * Efeito de hover ao passar o mouse no botão de mostrar informações de uma nota
     * fiscal de um dia específico
     * 
     * @param event evento de hover ao passar o mouse no botão
     */
    @FXML
    void hoverBtnInformacaoDiaEspecifico(MouseEvent event) {
        btnInformacaoDiaEspecifico
                .setStyle("-fx-background-color: #245823;-fx-cursor: hand; -fx-background-radius: 50;");
    }

    /**
     * Efeito de hover ao tirar o mouse no botão de mostrar informações de uma nota
     * fiscal de um dia específico
     * 
     * @param event evento de hover ao tirar o mouse no botão
     */
    @FXML
    void notHoverBtnInformacaoDiaEspecifico(MouseEvent event) {
        btnInformacaoDiaEspecifico
                .setStyle("-fx-background-color: #2b6b2a;-fx-cursor: hand; -fx-background-radius: 50;");
    }

    /**
     * Efeito de hover ao passar o mouse no botão de mostrar informações de todas as
     * notas fiscais
     * 
     * @param event evento de hover ao passar o mouse no botão
     */
    @FXML
    void hoverBtnInformacaoTodasNotas(MouseEvent event) {
        btnInformacaoTodasNotas.setStyle("-fx-background-color: #245823;-fx-cursor: hand; -fx-background-radius: 50;");
    }

    /**
     * Efeito de hover ao tirar o mouse no botão de procurar uma nota fiscal em
     * específico
     * 
     * @param event evento de hover ao tirar o mouse no botão
     */
    @FXML
    void notHoverBtnProcurarNotaFiscalUnica(MouseEvent event) {
        btnProcurarNotaFiscalUnica
                .setStyle("-fx-background-color: #2b6b2a;-fx-cursor: hand; -fx-background-radius: 50;");
    }

    /**
     * Efeito de hover ao passar o mouse no botão de procurar uma nota fiscal em
     * específico
     * 
     * @param event evento de hover ao passar o mouse no botão
     */
    @FXML
    void hoverBtnProcurarNotaFiscalUnica(MouseEvent event) {
        btnProcurarNotaFiscalUnica
                .setStyle("-fx-background-color: #245823;-fx-cursor: hand; -fx-background-radius: 50;");
    }

    /**
     * Efeito de hover ao tirar o mouse no botão de procurar uma nota fiscal em um
     * dia específico
     * 
     * @param event evento de hover ao tirar o mouse no botão
     */
    @FXML
    void notHoverBtnProcurarNotaFiscalDia(MouseEvent event) {
        btnProcurarNotaFiscalDia.setStyle("-fx-background-color: #2b6b2a;-fx-cursor: hand; -fx-background-radius: 50;");
    }

    /**
     * Efeito de hover ao passar o mouse no botão de procurar uma nota fiscal em um
     * dia específico
     * 
     * @param event evento de hover ao passar o mouse no botão
     */
    @FXML
    void hoverBtnProcurarNotaFiscalDia(MouseEvent event) {
        btnProcurarNotaFiscalDia.setStyle("-fx-background-color: #245823;-fx-cursor: hand; -fx-background-radius: 50;");
    }

    /**
     * Efeito de hover ao tirar o mouse no botão de voltar
     * 
     * @param event evento de hover ao tirar o mouse no botão
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