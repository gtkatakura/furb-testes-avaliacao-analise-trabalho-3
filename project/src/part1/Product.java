package part1;

import java.math.BigDecimal;

public class Product {
	private String name;
	private BigDecimal value;
	
	public Product(String name, BigDecimal value) {
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return this.name;
	}
	
	public BigDecimal getValue() {
		return this.value;
	}
}
