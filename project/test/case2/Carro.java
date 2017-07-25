package case2;

import java.util.Objects;

public class Carro {
	private String placa;
	private String modelo;
	private int ano;
	
	public Carro() {
		
	}
	
	public Carro(String placa, String modelo, int ano) {
		this.placa = placa;
		this.modelo = modelo;
		this.ano = ano;
	}
	
	public String getPlaca() {
		return this.placa;
	}
	
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	public String getModelo() {
		return this.modelo;
	}
	
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public int getAno() {
		return this.ano;
	}
	
	public void setAno(int ano) {
		this.ano = ano;
	}
	
	public boolean validaPlaca(String placa) {
		if (placa == null) return false;
		return placa.matches("^[a-zA-Z]{3}-[0-9]{4}$");
	}
	
	@Override
	public boolean equals(Object other) {
		if (this == other) return true;
		if (!(other instanceof Carro)) return false;
		
		Carro otherAsCarro = (Carro) other;
		
		return (
			this.getPlaca() == otherAsCarro.getPlaca() &&
			this.getModelo() == otherAsCarro.getModelo() &&
			this.getAno() == otherAsCarro.getAno()
		);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.getPlaca(), this.getModelo(), this.getAno());
	}
}
