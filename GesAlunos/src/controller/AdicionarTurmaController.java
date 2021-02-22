package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
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

    private Pane view;

    @FXML
    void adicionarTurma(ActionEvent event) {
        if(!tfTurma.getText().equals("")  &&  cbCurso.getValue()!=null  &&  cbAno.getValue()!=null){
            String nome = tfTurma.getText();
            String curso = cbCurso.getValue();
            int ano = cbAno.getValue();
            Turma t = new Turma(nome,curso,ano);
            insertTurma(t);
            queryTurmas();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERRO");
            alert.setContentText("Verifique os dados preenchidos...");
            alert.showAndWait();
        }
    }

    @FXML
    void cancelar(ActionEvent event) throws IOException {
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
        queryTurmas();
        //query para preencher a combo box de cursos
        queryCursos();
        //query para preencher a combo box de anos
        queryAnos();

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
    public void queryTurmas() {
        String sql = "SELECT nomeTurma, nomeCurso, anoTurma "
                + "FROM turma, curso, ano_curso "
                + "WHERE turma.curso_idCurso=curso.idCurso "
                + "AND turma.idAno=ano_curso.idAno";
        try {
            Statement stm = this.connection.createStatement();
            ResultSet result = stm.executeQuery(sql);
            turmas.clear();
            while(result.next()) {
                String nome = result.getString(1);
                String curso = result.getString(2);
                int ano = result.getInt(3);
                Turma t = new Turma(nome,curso,ano);
                turmas.add(t);
                this.tblTurmas.refresh();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void queryCursos() {
        String sql = "SELECT nomeCurso FROM curso";

        try {
            Statement stm = this.connection.createStatement();
            ResultSet result = stm.executeQuery(sql);
            while(result.next()) {
                String nome = result.getString(1);
                this.cbCurso.getItems().add(nome);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void queryAnos() {
        String sql = "SELECT anoTurma FROM ano_curso";
        try {
            Statement stm = this.connection.createStatement();
            ResultSet result = stm.executeQuery(sql);
            while(result.next()) {
                int ano = result.getInt(1);
                this.cbAno.getItems().add(ano);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertTurma(Turma turma){
        try {
            String nome = turma.getNome();
            String curso = turma.getCurso();
            int ano = turma.getAno();
            //ir buscar o id do curso
            String sql = "SELECT idCurso FROM curso WHERE nomeCurso = " + "\"" + curso + "\"";
            Statement stm = this.connection.createStatement();
            ResultSet result = stm.executeQuery(sql);
            int idCurso=0;
            while(result.next()) {
                idCurso = result.getInt(1);
            }
            //ir buscar o id do curso
            sql = "SELECT idAno FROM ano_curso WHERE anoTurma = " + ano;
            stm = this.connection.createStatement();
            result = stm.executeQuery(sql);
            int idAno=0;
            while(result.next()) {
                idAno = result.getInt(1);
            }
            stm.close();
            sql = "INSERT INTO turma (nomeTurma,curso_idCurso,idAno) VALUES (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nome);
            statement.setInt(2, idCurso);
            statement.setInt(3, idAno);
            int rows = statement.executeUpdate();
            if(rows > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("INFO");
                alert.setContentText("Turma criada com sucesso!");
                alert.showAndWait();
            }
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}