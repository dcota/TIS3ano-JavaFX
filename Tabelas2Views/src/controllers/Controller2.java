package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;


public class Controller2 {

    @FXML private Button btnFechar;
    @FXML private Button btnSubmeter;
    @FXML private TextArea taTextoView2;

    private String texto;

    @FXML
    public void submeterTexto(ActionEvent event){
        texto = this.taTextoView2.getText();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("INFO");
        alert.setContentText("Texto submetido com sucesso!");
        alert.showAndWait();
        Stage stage = (Stage) this.btnSubmeter.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void fecharView2(ActionEvent event){
        Stage stage = (Stage) this.btnFechar.getScene().getWindow();
        stage.close();
    }

    public String getTexto(){
        return this.texto;
    }
}
