package case2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CarrosControle {
	private Set<Carro> carros;
	
	public CarrosControle() {
		this.carros = new HashSet<>();
	}
	
	public boolean inserir(Carro carro) {
		return this.carros.add(carro);
	}
	
	public void alterar(String placa, Carro carro) {
		Carro encontrado = this.get(placa);
		encontrado.setAno(carro.getAno());
	}
	
	public Carro get(String placa) {
		return this.carros.stream()
			.filter(current -> current.getPlaca().equals(placa))
			.findFirst()
			.orElse(null);
	}
	
	public String buscarModelo(String placa) {
		return this.get(placa).getModelo();
	}
	
	public int total() {
		return this.carros.size();
	}
}
