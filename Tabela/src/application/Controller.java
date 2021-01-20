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

public class Controller {

    @FXML
    private TableView<Aviao> tabelaAvioes;

    @FXML
    private TableColumn<Aviao, String> colunaFabricante;

    @FXML
    private TableColumn<Aviao, String> colunaModelo;

    @FXML
    private TableColumn<Aviao, Integer> colunaCapacidade;

    @FXML
    private TableColumn<Aviao, Boolean> colunaAtivo;

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
    		
    		//instancia objeto Aviao
    		Aviao av = new Aviao(fabricante,modelo,capacidade,ativo);
    		
    		//adicionar o registo à lista e mostrar a lista na tabela
    		if(!this.listaAviao.contains(av)) {
    			this.listaAviao.add(av); //adiciona o novo registo à lista
        		this.tabelaAvioes.setItems(listaAviao); //mostra a lista na tabela
    		}
    		else {
    			Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setHeaderText(null);
				alert.setTitle("ATENÇÃO");
				alert.setContentText("Registo existente");
				alert.showAndWait();
    		}
    		
    	} catch(NumberFormatException e) {
    		
    	}
    }

    @FXML
    void alterar(ActionEvent event) {

    }

    @FXML
    void apagar(ActionEvent event) {

    }
    
    @FXML 
    void atualizaAtivo(ActionEvent event) {
		this.textFieldAtivo.setText(this.comboBoxAtivo.getValue());
    }
    
    public void initialize() {
    	
    	//criar valores iniciais da comboBox
    	this.comboBoxAtivo.getItems().addAll("Sim","Não");
    	
    	//ligação entre a classe Aviao e a tableView
    	listaAviao = FXCollections.observableArrayList();
    	this.colunaFabricante.setCellValueFactory(new PropertyValueFactory<Aviao,String>("fabricante"));
    	this.colunaModelo.setCellValueFactory(new PropertyValueFactory<Aviao,String>("modelo"));
    	this.colunaCapacidade.setCellValueFactory(new PropertyValueFactory<Aviao,Integer>("capacidade"));
    	this.colunaAtivo.setCellValueFactory(new PropertyValueFactory<Aviao,Boolean>("ativo"));
	
    }

}

