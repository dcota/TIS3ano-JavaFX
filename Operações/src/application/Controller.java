package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class Controller {
	
    @FXML
    private TextField op1;

    @FXML
    private TextField op2;
    
    @FXML
    private TextField res;

    @FXML
    private RadioButton soma;

    @FXML
    private RadioButton sub;

    @FXML
    private RadioButton mult;

    @FXML
    private RadioButton div;
    

    @FXML
    void dividir(ActionEvent event) {
    	
    }

    @FXML
    void multiplicar(ActionEvent event) {
    	
    }

    @FXML
    void somar(ActionEvent event) {
    		
    }

    @FXML
    void subtrair(ActionEvent event) {
    
    }
    
    private ToggleGroup tg;
    
    public void initialize() {
    	this.tg = new ToggleGroup();
    	this.soma.setToggleGroup(tg);
    	this.sub.setToggleGroup(tg);
    	this.mult.setToggleGroup(tg);
    	this.div.setToggleGroup(tg);
    }

}
