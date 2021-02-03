package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Dados;

import java.io.IOException;

public class MainController {

    @FXML
    public Button btnSair;

    @FXML
    private TextField tfVal1;

    @FXML
    private TextField tfVal2;

    @FXML
    private RadioButton rbSoma;

    @FXML
    private RadioButton rbSub;

    @FXML
    private RadioButton rbMult;

    @FXML
    private RadioButton rbDiv;

    @FXML
    private ComboBox<Integer> comboBoxDec;

    @FXML
    private Button btnCalcular;

    private ToggleGroup tg;

    public void initialize(){
        //preencher a combobox
        this.comboBoxDec.getItems().addAll(1,2,3);

        //criar um ToggleGroup
        this.tg = new ToggleGroup();
        this.rbSoma.setToggleGroup(tg);
        this.rbSub.setToggleGroup(tg);
        this.rbMult.setToggleGroup(tg);
        this.rbDiv.setToggleGroup(tg);

    }

    public boolean chkDados(){
        boolean val=true;
        if(this.tfVal1.getText()==null || this.tfVal2.getText()==null || this.comboBoxDec.getValue()==null || this.tg.getToggles().isEmpty()){
            val=false;
        }
        return val;
    }


    @FXML
    void calcular(ActionEvent event) throws IOException {
        //verifica se todos os atributos possuem valores. se sim criar um objeto da classe Dados
        if(chkDados()){
            try {
                double val1 = Double.parseDouble(this.tfVal1.getText());
                double val2 = Double.parseDouble(this.tfVal2.getText());
                String op = "";
                if (this.rbSoma.isSelected()) {
                    op = "somar";
                }
                if (this.rbSub.isSelected()) {
                    op = "subtrair";
                }
                if (this.rbMult.isSelected()) {
                    op = "multiplicar";
                }
                if (this.rbDiv.isSelected()) {
                    op = "dividir";
                }
                int casasDec = this.comboBoxDec.getValue();

                //instanciar classe Dados
                Dados opera = new Dados(val1, val2, op, casasDec);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/secondView.fxml"));
                Parent root = loader.load();

                SecondController controller = loader.getController();

                if (opera.getVal2() == 0 && opera.getOpera().equals("dividir")) {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setHeaderText(null);
                    alerta.setTitle("ERRO!");
                    alerta.setContentText("Divisão por zero!");
                    alerta.showAndWait();
                } else {
                    //envia dados para o controller da segunda view (variável dadosOpera através do método getDados())
                    controller.getDados(opera);
                    //lançar a segunda view
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.setScene(scene);
                    stage.showAndWait();
                }
            } catch (NumberFormatException e){
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setHeaderText(null);
                alerta.setTitle("ERRO!");
                alerta.setContentText("Formato de número inválido...");
                alerta.showAndWait();
            }
        }
        else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setHeaderText(null);
            alerta.setTitle("ALERTA!");
            alerta.setContentText("Todos os campos deve estar preenchidos!");
            alerta.showAndWait();
        }

    }

    @FXML
    public void sair(ActionEvent event) {
        Stage stage = (Stage) this.btnSair.getScene().getWindow();
        stage.close();
    }
}
