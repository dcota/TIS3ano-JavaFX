package application;

import Model.Aviao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class Controller {

    @FXML
    private TableView<Aviao> tabelaAvioes;

    @FXML
    private TableColumn<Aviao, String> colunaFabricante;

    @FXML
    private TableColumn<Aviao, String> colunaModelo;

    @FXML
    private TableColumn<Aviao, String> colunaCapacidade;

    @FXML
    private TableColumn<Aviao, String> colunaAtivo;

    @FXML
    private TextField textFieldFabricante;

    @FXML
    private TextField textFieldModelo;

    @FXML
    private TextField textFieldCapacidade;

    @FXML
    private ComboBox<String> comboBoxAtivo;

    @FXML
    private TextField textFieldAtivo;

    private ObservableList<Aviao> listaAviao;

    public void initialize() {

        //criar valores iniciais da comboBox
        this.comboBoxAtivo.getItems().addAll("Sim","Não");

        //ligação entre a classe Aviao e a tableView
        listaAviao = FXCollections.observableArrayList();
        this.tabelaAvioes.setItems(listaAviao);
        this.colunaFabricante.setCellValueFactory(new PropertyValueFactory<Aviao,String>("fabricante"));
        this.colunaModelo.setCellValueFactory(new PropertyValueFactory<Aviao,String>("modelo"));
        this.colunaCapacidade.setCellValueFactory(new PropertyValueFactory<Aviao,String>("capacidade"));
        this.colunaAtivo.setCellValueFactory(new PropertyValueFactory<Aviao,String>("ativo"));
    }

    public void limpar() {
        this.textFieldAtivo.setText("");
        this.textFieldFabricante.setText("");
        this.textFieldModelo.setText("");
        this.textFieldCapacidade.setText("");
    }


    @FXML
    void adicionar(ActionEvent event) {
        try {
            //recolher informação do formulário
            String fabricante = this.textFieldFabricante.getText();
            String modelo = this.textFieldModelo.getText();
            int capacidade = Integer.parseInt(this.textFieldCapacidade.getText());
            boolean ativo;
            if(this.textFieldAtivo.getText().equals("Sim")) {
                ativo=true;
            }
            else
                ativo=false;

            //instanciar objeto Aviao
            Aviao av = new Aviao(fabricante,modelo,capacidade,ativo);

            //adicionar o registo à lista e mostrar a lista na tabela
            if(!this.listaAviao.contains(av)) {
                this.listaAviao.add(av); //adiciona o novo registo à lista
                this.tabelaAvioes.setItems(listaAviao); //mostra a lista na tabela
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setTitle("ATENÇÃO");
                alert.setContentText("Registo adicionado com sucesso!");
                alert.showAndWait();
                limpar();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setTitle("ATENÇÃO");
                alert.setContentText("Registo existente");
                alert.showAndWait();
            }

        } catch(NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERRO");
            alert.setContentText("Valor da capacidade inválido...");
            alert.showAndWait();
        }
    }

    @FXML
    void alterar(ActionEvent event) {
        Aviao av = this.tabelaAvioes.getSelectionModel().getSelectedItem();
        if( av == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERRO");
            alert.setContentText("Nenhum registo selecionado!");
            alert.showAndWait();
        }
        else {
            try {
                //recolher informação alterada do formulário
                String fabricante = this.textFieldFabricante.getText();
                String modelo = this.textFieldModelo.getText();
                int capacidade = Integer.parseInt(this.textFieldCapacidade.getText());
                boolean ativo;
                if(this.textFieldAtivo.getText().equals("Sim")) {
                    ativo=true;
                }
                else
                    ativo=false;

                Aviao avAux = new Aviao(fabricante,modelo,capacidade,ativo);

                if(!this.listaAviao.contains(avAux)) {
                    av.setFabricante(avAux.getFabricante());
                    av.setModelo(avAux.getModelo());
                    av.setCapacidade(avAux.getCapacidade());
                    av.setAtivo(avAux.getAtivo());
                    this.tabelaAvioes.refresh();
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText(null);
                    alert.setTitle("ATENÇÃO");
                    alert.setContentText("Registo alterado com sucesso!");
                    alert.showAndWait();
                    limpar();
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText(null);
                    alert.setTitle("ATENÇÃO");
                    alert.setContentText("Registo existente");
                    alert.showAndWait();
                }

            } catch(NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("ERRO");
                alert.setContentText("Valor da capacidade inválido...");
                alert.showAndWait();
            }
        }

    }

    @FXML
    void apagar(ActionEvent event) {
        Aviao av = this.tabelaAvioes.getSelectionModel().getSelectedItem();
        if(av==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERRO");
            alert.setContentText("Nenhum registo selecionado!");
            alert.showAndWait();
        } else {
            this.listaAviao.remove(av);
            this.tabelaAvioes.refresh();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("ATENÇÃO");
            alert.setContentText("Registo apagado com sucesso!");
            alert.showAndWait();
            limpar();

        }
    }

    @FXML
    void selecionar(MouseEvent event) {
        //seleciona a linha escolhida pelo utilizador
        Aviao av = this.tabelaAvioes.getSelectionModel().getSelectedItem();
        if(av != null) {
            this.textFieldFabricante.setText(av.getFabricante());
            this.textFieldModelo.setText(av.getModelo());
            this.textFieldCapacidade.setText(String.valueOf(av.getCapacidade()));
            boolean ativo = av.getAtivo();
            if(ativo == true) {
                this.textFieldAtivo.setText("Sim");
            }
            else {
                this.textFieldAtivo.setText("Não");
            }
        }
    }


    @FXML
    void atualizaAtivo(ActionEvent event) {
        this.textFieldAtivo.setText(this.comboBoxAtivo.getValue());
    }


}