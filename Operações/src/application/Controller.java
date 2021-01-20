package application;

import Model.Opera;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
    
    private ToggleGroup tg;
    
    @FXML
    void dividir(ActionEvent event) {
    	try {
    		if(this.op1.getText()=="") {
    			this.op1.setText("0");
    		}
    		if(this.op2.getText()=="") {
    			this.op2.setText("0");
    		}
    		double op1 = Double.parseDouble(this.op1.getText());
        	double op2 = Double.parseDouble(this.op2.getText());
        	Opera opera = new Opera(op1,op2);
        	this.res.setText(String.valueOf(opera.dividir()));
    	} catch (NumberFormatException e) {
    		Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.setHeaderText(null);
			alerta.setTitle("Ocorreu um erro!");
			alerta.setContentText("Formato incorreto...");
			alerta.showAndWait();
			reset();
    	}
    }

    @FXML
    void multiplicar(ActionEvent event) {
    	try {
    		if(this.op1.getText()=="") {
    			this.op1.setText("0");
    		}
    		if(this.op2.getText()=="") {
    			this.op2.setText("0");
    		}
    		double op1 = Double.parseDouble(this.op1.getText());
        	double op2 = Double.parseDouble(this.op2.getText());
        	Opera opera = new Opera(op1,op2);
        	this.res.setText(String.valueOf(opera.multiplicar()));
    	} catch (NumberFormatException e) {
    		Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.setHeaderText(null);
			alerta.setTitle("Ocorreu um erro!");
			alerta.setContentText("Formato incorreto...");
			alerta.showAndWait();
			reset();
    	}
    }

    @FXML
    void somar(ActionEvent event) {
    	try {
    		if(this.op1.getText()=="") {
    			this.op1.setText("0");
    		}
    		if(this.op2.getText()=="") {
    			this.op2.setText("0");
    		}
    		double op1 = Double.parseDouble(this.op1.getText());
        	double op2 = Double.parseDouble(this.op2.getText());
        	Opera opera = new Opera(op1,op2);
        	this.res.setText(String.valueOf(opera.somar()));
    	} catch (NumberFormatException e) {
    		Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.setHeaderText(null);
			alerta.setTitle("Ocorreu um erro!");
			alerta.setContentText("Formato incorreto...");
			alerta.showAndWait();
			reset();
    	}
    		
    }

    @FXML
    void subtrair(ActionEvent event) {
    	try {
    		if(this.op1.getText()=="") {
    			this.op1.setText("0");
    		}
    		if(this.op2.getText()=="") {
    			this.op2.setText("0");
    		}
    		double op1 = Double.parseDouble(this.op1.getText());
        	double op2 = Double.parseDouble(this.op2.getText());
        	Opera opera = new Opera(op1,op2);
        	this.res.setText(String.valueOf(opera.subtrair()));
    	} catch (NumberFormatException e) {
    		Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.setHeaderText(null);
			alerta.setTitle("Ocorreu um erro!");
			alerta.setContentText("Formato incorreto...");
			alerta.showAndWait();
			reset();
    	}
    }
    
    @FXML
    void limpar(ActionEvent event) {
    	this.op1.setText("");
    	this.op2.setText("");
    	this.res.setText("");
    	if(this.soma.isSelected()) {
    		this.soma.setSelected(false);
    	}
    	else if(this.sub.isSelected()) {
    		this.sub.setSelected(false);
    	}
    	else if(this.div.isSelected()) {
    		this.div.setSelected(false);
    	}
    	else if(this.mult.isSelected()) {
    		this.mult.setSelected(false);
    	}
    }
    
    public void reset() {
    	this.op1.setText("");
    	this.op2.setText("");
    	this.res.setText("");
    	if(this.soma.isSelected()) {
    		this.soma.setSelected(false);
    	}
    	else if(this.sub.isSelected()) {
    		this.sub.setSelected(false);
    	}
    	else if(this.div.isSelected()) {
    		this.div.setSelected(false);
    	}
    	else if(this.mult.isSelected()) {
    		this.mult.setSelected(false);
    	}
	}
    
    public void initialize() {
    	//instanciar um togglegroup
    	this.tg = new ToggleGroup();    	
    	this.soma.setToggleGroup(tg);
    	this.sub.setToggleGroup(tg);
    	this.mult.setToggleGroup(tg);
    	this.div.setToggleGroup(tg);
    }

}
