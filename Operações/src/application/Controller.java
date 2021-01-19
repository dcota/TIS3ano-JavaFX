package application;

import Model.Opera;
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
    void limpar(ActionEvent event) {
    	this.op1.setText("");
    	this.op2.setText("");
    	if(this.soma.isSelected()) {
    		this.soma.setSelected(false);
    	}
    	else if(this.sub.isSelected()) {
    		this.sub.setSelected(false);
    	}
    	else if(this.mult.isSelected()) {
    		this.mult.setSelected(false);
    	}
    	else if(this.div.isSelected()) {
    		this.div.setSelected(false);
    	}
    }

    @FXML
    void dividir(ActionEvent event) {
    	int op1 = Integer.parseInt(this.op1.getText());
    	int op2 = Integer.parseInt(this.op2.getText());
    	
    	Opera oper = new Opera(op1,op2);
    	
    	this.res.setText(oper.dividir() + "");
    }

    @FXML
    void multiplicar(ActionEvent event) {
    	int op1 = Integer.parseInt(this.op1.getText());
    	int op2 = Integer.parseInt(this.op2.getText());
    	
    	Opera oper = new Opera(op1,op2);
    	
    	this.res.setText(oper.multiplicar() + "");
    }

    @FXML
    void somar(ActionEvent event) {
    	int op1 = Integer.parseInt(this.op1.getText());
    	int op2 = Integer.parseInt(this.op2.getText());
    	
    	Opera oper = new Opera(op1,op2);
    	
    	this.res.setText(oper.somar() + "");	
    }

    @FXML
    void subtrair(ActionEvent event) {
    	int op1 = Integer.parseInt(this.op1.getText());
    	int op2 = Integer.parseInt(this.op2.getText());
    	
    	Opera oper = new Opera(op1,op2);
    	
    	this.res.setText(oper.subtrair() + "");
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
