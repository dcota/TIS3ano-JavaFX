package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller1 {

    @FXML private Button btnPreencherTexto;
    @FXML private TextArea taTextoView1;
    @FXML private TextField tfTexto;
    @FXML private Button btnEnviarTexto;

    @FXML
    void preencherTextoView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/view2.fxml"));
        Parent root = loader.load();
        Controller2 controller = loader.getController(); //a variável controller fica associada ao Controller2
        //lançar a segunda view
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.showAndWait();

        //chama o método getTexto() da classe Controller2 através da variável controller
        String texto = controller.getTexto();
        this.taTextoView1.setText(texto);
    }

    @FXML
    void enviarTexto(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/view2.fxml"));
        Parent root = loader.load();

        Controller2 controller = loader.getController(); //a variável controller fica associada ao Controller2
        //envia o conteúdo da caixa de texto para o controller2 antes de lançar a view2
        controller.preencheLabel(this.tfTexto.getText());

        //lançar a segunda view
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.showAndWait();

    }
}