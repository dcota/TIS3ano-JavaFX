package application2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class SampleController {

	@FXML
	private TableView<Aluno> tblAlunos;

	@FXML
	private TableColumn<Aluno, String> colNome;

	@FXML
	private TableColumn<Aluno, String> colApelido;

	@FXML
	private TableColumn<Aluno, String> colIdade;

	@FXML
	private TableColumn<Aluno, String> colTurma;

	@FXML
	private TextField nome;

	@FXML
	private TextField apelido;

	@FXML
	private TextField idade;

	@FXML
	private TextField turma;

	private ObservableList<Aluno> alunos;

	public void initialize() {

		alunos = FXCollections.observableArrayList();

		this.colNome.setCellValueFactory(new PropertyValueFactory<Aluno, String>("nome"));
		this.colApelido.setCellValueFactory(new PropertyValueFactory<Aluno, String>("apelido"));
		this.colIdade.setCellValueFactory(new PropertyValueFactory<Aluno, String>("idade"));
		this.colTurma.setCellValueFactory(new PropertyValueFactory<Aluno, String>("turma"));
	}

	@FXML
	void adicionar(ActionEvent event) {
		try {
			if (!chkEmpty()) {
				String nome = this.nome.getText();
				String apelido = this.apelido.getText();
				int idade = Integer.parseInt(this.idade.getText());
				String turma = this.turma.getText();

				Aluno aluno = new Aluno(nome, apelido, idade, turma);

				if (!this.alunos.contains(aluno)) {
					this.alunos.add(aluno);
					this.tblAlunos.setItems(alunos);
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setTitle(null);
					alert.setContentText("Aluno adicionado...");
					alert.showAndWait();
					reset();
				} else {
					Alert alert = new Alert(Alert.AlertType.WARNING);
					alert.setHeaderText(null);
					alert.setTitle("ATENÇÃO");
					alert.setContentText("Aluno existente");
					alert.showAndWait();
				}
			} else {
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setHeaderText(null);
				alert.setTitle("ATENÇÃO");
				alert.setContentText("Campos vazios...");
				alert.showAndWait();
			}

		} catch (NumberFormatException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("ERRO");
			alert.setContentText("Formato de idade inválido...");
			alert.showAndWait();
		}

	}

	@FXML
	void eliminar(ActionEvent event) {
		Aluno aluno = this.tblAlunos.getSelectionModel().getSelectedItem();
		
		if(aluno == null) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("ERRO");
			alert.setContentText("Deve selecionar um aluno...");
			alert.showAndWait();
		} else {
			this.alunos.remove(aluno);
			this.tblAlunos.refresh();
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle(null);
			alert.setContentText("Aluno eliminado...");
			alert.showAndWait();
			reset();
		}
	}

	@FXML
	void modificar(ActionEvent event) {
		Aluno aluno = this.tblAlunos.getSelectionModel().getSelectedItem();
		
		if(aluno == null) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("ERRO");
			alert.setContentText("Deve selecionar um aluno...");
			alert.showAndWait();
		} else {
			try {
				if (!chkEmpty()) {
									
					String nome = this.nome.getText();
					String apelido = this.apelido.getText();
					int idade = Integer.parseInt(this.idade.getText());
					String turma = this.turma.getText();

					Aluno aux = new Aluno(nome, apelido, idade, turma);

					if (!this.alunos.contains(aux)) {
						aluno.setNome(aux.getNome());
						aluno.setApelido(aux.getApelido());
						aluno.setIdade(aux.getIdade());
						aluno.setTurma(aux.getTurma());
						this.tblAlunos.refresh();
						Alert alert = new Alert(Alert.AlertType.INFORMATION);
						alert.setHeaderText(null);
						alert.setTitle(null);
						alert.setContentText("Aluno modificado...");
						alert.showAndWait();
						reset();
					} else {
						Alert alert = new Alert(Alert.AlertType.WARNING);
						alert.setHeaderText(null);
						alert.setTitle("ATENÇÃO");
						alert.setContentText("Aluno existente");
						alert.showAndWait();
					}
				} else {
					Alert alert = new Alert(Alert.AlertType.WARNING);
					alert.setHeaderText(null);
					alert.setTitle("ATENÇÃO");
					alert.setContentText("Campos vazios...");
					alert.showAndWait();
				}

			} catch (NumberFormatException e) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle("ERRO");
				alert.setContentText("Formato de idade inválido...");
				alert.showAndWait();
			}
		}
	}
	
	@FXML
	public void selecionar(MouseEvent event) {
		Aluno aluno = this.tblAlunos.getSelectionModel().getSelectedItem();
		if(aluno != null) {
			this.nome.setText(aluno.getNome());
			this.apelido.setText(aluno.getApelido());
			this.idade.setText(String.valueOf(aluno.getIdade()));
			this.turma.setText(aluno.getTurma());
		}
	}

	public Boolean chkEmpty() {
		Boolean chk = false;
		if(this.nome.getText().isEmpty()||this.apelido.getText().isEmpty()||this.idade.getText().isEmpty()||this.turma.getText().isEmpty()) {
			chk=true;
		}
		
		return chk;
	}
	
	public void reset() {
		this.nome.setText("");
		this.apelido.setText("");
		this.idade.setText("");
		this.turma.setText("");
		
	}

}
