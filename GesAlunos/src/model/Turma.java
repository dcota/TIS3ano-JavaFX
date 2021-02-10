package model;

public class Turma {
	private String nome;
	private String curso;
	private int ano;
	
	public Turma(String nome, String curso, int ano) {
		super();
		this.nome = nome;
		this.curso = curso;
		this.ano = ano;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}
	
	
}
