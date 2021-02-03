package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class Controller {

    @FXML
    private TableView<Cidade> tblCidades;

    @FXML
    private TableColumn<Cidade,String> colNomeCidade;

    @FXML
    private Button btnObter;

    @FXML
    private ComboBox<String> comboBoxPaises;


    private ObservableList<Cidade> cidades;

    private Connection connection;


    public void initialize() throws IOException {
        //preparar a tabela de dados
        cidades = FXCollections.observableArrayList();
        this.tblCidades.setItems(cidades);
        this.colNomeCidade.setCellValueFactory(new PropertyValueFactory<Cidade,String>("cidade"));

        //ligar à base de dados
        connectDB();

        //preencher combo Box
        preencheCombo();
    }

    public void preencheCombo(){
        try{
            String sql = "SELECT Name FROM country";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while(result.next()) {
                String pais = result.getString("Name");
                this.comboBoxPaises.getItems().add(pais);
            }
            statement.close();

        } catch (SQLException e) {

        }
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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERRO");
            alert.setContentText("Não ligado à BD!");
            alert.showAndWait();
        }
    }


    public void obterCidades(ActionEvent event) {
        try{
            String sql = "SELECT name FROM city WHERE population > 3000000";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while(result.next()) {
                Cidade cidade = new Cidade(result.getString("name"));
                this.cidades.add(cidade);
                this.tblCidades.refresh();

            }
            statement.close();

        } catch (SQLException e) {

        }
    }
}
