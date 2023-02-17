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
import com.exceptions.geral.CampoVazioException;
import com.exceptions.lista.ListaVaziaException;
import com.exceptions.notafiscal.CodigoNotaFiscalNotSupportedException;
import com.exceptions.notafiscal.DataNotSupportedException;
import com.exceptions.notafiscal.NotaFiscalNotFoundException;
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
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
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

    @FXML
    private TableColumn<NotaFiscal, Double> tableColumnTotalNotaFiscalTodas;

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

              
                tableColumnTotalNotaFiscalTodas.setCellValueFactory(new Callback<CellDataFeatures<NotaFiscal, Double>, ObservableValue<Double>>() {
                    @Override
                    public ObservableValue<Double> call(TableColumn.CellDataFeatures<NotaFiscal, Double> features) {
                        NotaFiscal notaFiscal = features.getValue();
                        double total = 0.0;
                        try {
                            total = notaFiscal.getTotal();
                        } catch (Exception e) {
                            total = 0;
                            System.out.println("Erro ao calcular o total da nota fiscal");
                        }
                        DoubleProperty totalProperty = new SimpleDoubleProperty(total);
                        return totalProperty.asObject();
                    }
                });

                tableColumnTotalNotaFiscalTodas.setCellFactory(new Callback<TableColumn<NotaFiscal, Double>, TableCell<NotaFiscal, Double>>() {
                    @Override
                    public TableCell<NotaFiscal, Double> call(TableColumn<NotaFiscal, Double> column) {
                        return new TableCell<NotaFiscal, Double>() {
                            @Override
                            protected void updateItem(Double item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty || item == null) {
                                    setText(null);
                                } else {
                                    setText(String.format("R$ %.2f", item));
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

        if (paneTodasNotas.isVisible()) {
            

            // NAO FAZER O LAÇO FOR EACH ABAIXO, BASTA COLOCAR A ARRAYLIST DENTOR DOS PARENTESES DA OBSERVABLE LIST

            try {

                ObservableList<NotaFiscal> observableList = null;
                
                if (listaNotaFiscal.isEmpty()) {
                    throw new ListaVaziaException("Não há notas fiscais cadastradas");
                }

                observableList = FXCollections.observableArrayList(listaNotaFiscal.getArray());

                tableTodasNotas.setItems(observableList);
            } catch (ListaVaziaException e) {
                alertInterface("ERRO", e.getMessage(), AlertType.ERROR);
            } catch (Exception e) {
                alertInterface("ERRO", "Ocorreu um erro inesperado", AlertType.ERROR);
                System.out.println(e.getMessage());
            }
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

            LocalDate localDate = null;
            Date date = null;
            Calendar dataCalendar = null;
            ArrayList<NotaFiscal> notasFicais = null;
            ObservableList<NotaFiscal> observableList = null;

            double totalNotas = 0;

            if (datePickerVendaDia.getValue() == null) {
                throw new CampoVazioException("O campo de data não pode estar vazio");
            }

            localDate = datePickerVendaDia.getValue();

            date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            dataCalendar = Calendar.getInstance();
            dataCalendar.setTime(date);

            try {
                notasFicais = listaNotaFiscal.getNotasFiscaisPorData(dataCalendar);
            } catch (Exception e) {
                throw e;
            }

            // NAO FAZER O LAÇO FOR EACH ABAIXO, BASTA COLOCAR A ARRAYLIST DENTOR DOS PARENTESES DA OBSERVABLE LIST


            try {
                for (NotaFiscal notaFiscal : notasFicais) {
                    totalNotas += notaFiscal.getTotal();
                }
            } catch (Exception e) {
                throw e;
            }

            observableList = FXCollections.observableArrayList(notasFicais);
            tableNotasDia.setItems(observableList);
            textFieldTotalVendidoNotaDia.setText("R$" + String.format("%.2f", totalNotas));
            textFieldQuantidadeNotas.setText(notasFicais.size() + "");

        } catch (CampoVazioException | NotaFiscalNotFoundException | DataNotSupportedException | ListaVaziaException e) {
            alertInterface("ERRO", e.getMessage(), AlertType.ERROR);
        } catch (Exception e) {
            alertInterface("ERRO", "Ocorreu um erro inesperado", AlertType.ERROR);
            System.out.println(e.getMessage());
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

            int codigoNFInt = 0;

            NotaFiscal notaFiscal = null;
            ListaItem listaItem = null;
            double totalVendaNotaFiscal = 0;

            Calendar data = null;
            Instant instant = null;
            LocalDate dataLocalDate = null;

            ObservableList<Item> observableList = null;

            if (codigoNF.trim().isEmpty() || codigoNF == null) {
                throw new CampoVazioException("O campo de código da nota fiscal não pode estar vazio");
            }

            try {
                codigoNFInt = Integer.parseInt(codigoNF);
            } catch (Exception e) {
                throw new CodigoNotaFiscalNotSupportedException("O campo de código da nota fiscal deve ser um número inteiro");
            }

            if (codigoNFInt <= 0) {
                throw new CodigoNotaFiscalNotSupportedException("O campo de código da nota fiscal deve ser um número inteiro maior que zero");
            }

            try {
                notaFiscal = listaNotaFiscal.getNotaFiscal(codigoNFInt);
            } catch (Exception e) {
                throw e;
            }

            try {
                totalVendaNotaFiscal = notaFiscal.getTotal();
            } catch (Exception e) {
                throw e;
            }
            
            data = notaFiscal.getData();
            instant = data.toInstant();
            dataLocalDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();

            listaItem = notaFiscal.getItens();

            observableList = FXCollections.observableArrayList(listaItem.getArray());

            datePickerVenda.setValue(dataLocalDate);
            textFieldTotalVendidoNota.setText(String.format("%.2f", totalVendaNotaFiscal));
            tableProdutos.setItems(observableList);

        } catch (CodigoNotaFiscalNotSupportedException | CampoVazioException | NotaFiscalNotFoundException | ListaVaziaException e) {
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