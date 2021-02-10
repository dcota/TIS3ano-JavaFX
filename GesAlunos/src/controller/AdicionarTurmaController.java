package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Turma;

public class AdicionarTurmaController {

    @FXML
    private TextField tfTurma;

    @FXML
    private ComboBox<String> cbCurso;

    @FXML
    private ComboBox<Integer> cbAno;

    @FXML
    private Button btnAdicionar;

    @FXML
    private Button btnCancelar;

    @FXML
    private TableView<Turma> tblTurmas;

    @FXML
    private TableColumn<Turma, String> colNomeTurma;

    @FXML
    private TableColumn<Turma, String> colCursoTurma;

    @FXML
    private TableColumn<Turma, Integer> colAno;

    @FXML
    void adicionarTurma(ActionEvent event) {

    }

    @FXML
    void cancelar(ActionEvent event) {

    }

    private ObservableList<Turma> turmas;

    private Connection connection;

    public void initialize() throws IOException {
        //preparar a tabela
        turmas = FXCollections.observableArrayList();
        this.tblTurmas.setItems(turmas);
        this.colNomeTurma.setCellValueFactory(new PropertyValueFactory<Turma,String>("nome"));
        this.colCursoTurma.setCellValueFactory(new PropertyValueFactory<Turma,String>("curso"));
        this.colAno.setCellValueFactory(new PropertyValueFactory<Turma,Integer>("ano"));
        //criar ligação à bd
        connectDB();
        //query das turmas existentes
        turmasQuery();

    }
    public void turmasQuery(){
        try{
            String sql = "SELECT nomeTurma, nomeCurso, anoTurma " +
                    "FROM turma, curso, ano_curso " +
                    "WHERE turma.curso_idCurso = curso.idCurso " +
                    "AND turma.idAno = ano_curso.idAno";
            Statement statement = this.connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            turmas.clear();
            while(result.next()) {
                //retirar os dois valores de cada linha
                String nome = result.getString(1);
                String curso = result.getString(2);
                int ano = result.getInt(3);
                //criar objeto da classe turma com os dois valores
                Turma t = new Turma(nome,curso,ano);
                //adicionar o novo objeto à lista (ObservableList)
                this.turmas.add(t);
                //refresh da tabela
                this.tblTurmas.refresh();
                //adicionar o curso à combo box de cursos
                cbCurso.getItems().add(curso);

            }
            statement = this.connection.createStatement();
            result = statement.executeQuery("SELECT anoTurma FROM ano_curso");
            while(result.next()) {
                int ano = result.getInt(1);
                cbAno.getItems().add(ano);
            }
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void connectDB() throws IOException {
        Properties p = new Properties();
        InputStream is = new FileInputStream("dbConfig.properties");
        p.load(is);
        try {
            this.connection = DriverManager.getConnection(p.getProperty("url"),p.getProperty("username"),p.getProperty("password"));
        } catch (SQLException e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERRO");
            alert.setContentText("Não ligado à BD!");
            alert.showAndWait();
        }
    }

    public void getConnection(Connection connection){
        this.connection = connection;
    }



}