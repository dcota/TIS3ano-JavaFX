package Model;

public class Aviao {
	private String fabricante;
	private String modelo;
	private int capacidade;
	private boolean ativo;
	
	public Aviao(String fabricante, String modelo, int capacidade, boolean ativo) {
		this.fabricante = fabricante;
		this.modelo = modelo;
		this.capacidade = capacidade;
		this.ativo = ativo;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	
	
}
