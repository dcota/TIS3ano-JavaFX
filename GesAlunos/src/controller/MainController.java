package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MainController {

    private Pane view;

    private Connection connection;

    @FXML
    private BorderPane bpLabel;

    @FXML
    private Button btnAdicionarTurma;

    @FXML
    private Button btnAdicionarCurso;

    @FXML
    private Button btnAdicionarSistema;


    @FXML
    void adicionarCurso(ActionEvent event) throws IOException {
        Pane view = getView("/view/adicionarCursoView.fxml");
        bpLabel.setCenter(view);
    }

    @FXML
    void adicionarSistema(ActionEvent event) throws IOException {
        Pane view = getView("/view/adicionarSistemaView.fxml");
        bpLabel.setCenter(view);
    }

    @FXML
    void adicionarTurma(ActionEvent event) throws IOException {
        //FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/adicionarTurmaView.fxml"));
        //Pane pane = (Pane) loader.load();
        Pane view = getView("/view/adicionarTurmaView.fxml");
        bpLabel.setCenter(view);
    }

    //método para colocar a nova view na MainView
    public Pane getView(String fileName) throws IOException {
        URL fileURL = Main.class.getResource(fileName);
        view = new FXMLLoader().load(fileURL);
        return view;
    }

    public void connectDB() throws IOException {
        Properties p = new Properties();
        InputStream is = new FileInputStream("dbConfig.properties");
        p.load(is);
        try {
            connection = DriverManager.getConnection(p.getProperty("url"),p.getProperty("username"),p.getProperty("password"));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("INFO");
            alert.setContentText("Ligado à BD!");
            alert.showAndWait();
        } catch (SQLException e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERRO");
            alert.setContentText("Não ligado à BD!");
            alert.showAndWait();
        }
    }
}
