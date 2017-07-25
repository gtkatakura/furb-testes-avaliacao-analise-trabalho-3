package part1;

import java.math.BigDecimal;

public class ProductConsumed {
	private Product product;
	private int quantity;
	
	public ProductConsumed(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}
	
	public BigDecimal getValue() {
		return product.getValue().multiply(new BigDecimal(quantity));
	}
}
