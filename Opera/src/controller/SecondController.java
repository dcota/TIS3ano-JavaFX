package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Dados;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class SecondController {

    @FXML
    private Label labelRes;

    @FXML
    private Button btnFechar;

    private Dados dadosOpera;

    @FXML
    void fechar(ActionEvent event) {
        Stage stage = (Stage) this.btnFechar.getScene().getWindow();
        stage.close();
    }

    public void getDados(Dados dados){
        this.dadosOpera = dados;
        if(this.dadosOpera.getOpera().equals("somar")){
            BigDecimal bd = new BigDecimal(dados.soma()).setScale(dados.getCasasDec(), RoundingMode.HALF_UP);
            this.labelRes.setText(String.valueOf(bd.doubleValue()));
        }
        if(this.dadosOpera.getOpera().equals("subtrair")){
            BigDecimal bd = new BigDecimal(dados.sub()).setScale(dados.getCasasDec(), RoundingMode.HALF_UP);
            this.labelRes.setText(String.valueOf(bd.doubleValue()));
        }

        if(this.dadosOpera.getOpera().equals("multiplicar")){
            BigDecimal bd = new BigDecimal(dados.mult()).setScale(dados.getCasasDec(), RoundingMode.HALF_UP);
            this.labelRes.setText(String.valueOf(bd.doubleValue()));
        }

        if(this.dadosOpera.getOpera().equals("dividir")){
            BigDecimal bd = new BigDecimal(dados.div()).setScale(dados.getCasasDec(), RoundingMode.HALF_UP);
            this.labelRes.setText(String.valueOf(bd.doubleValue()));
        }

    }

}
