package Model;

public class Opera {
	
	//atributos
	private double op1;
	private double op2;
	
	//construtor
	public Opera(double op1, double op2) {
		this.op1 = op1;
		this.op2 = op2;
	}

	//getters e setters
	public double getOp1() {
		return op1;
	}

	public void setOp1(int op1) {
		this.op1 = op1;
	}

	public double getOp2() {
		return op2;
	}

	public void setOp2(int op2) {
		this.op2 = op2;
	}
	
	//métodos para manipular os atributos
	public double somar() {
		return this.op1 + this.op2;
	}
	
	public double subtrair() {
		return this.op1 - this.op2;
	}
	
	public double multiplicar() {
		return this.op1 * this.op2;
	}
	
	public double dividir() {
		return (double) this.op1 / this.op2;
	}

}
