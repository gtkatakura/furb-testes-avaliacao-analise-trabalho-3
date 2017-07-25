package part1;

import java.math.BigDecimal;

public class TaxCalculated {
	private BigDecimal money;

	public TaxCalculated(BigDecimal money) {
		this.money = money;
	}
	
	public BigDecimal getValue() {
		BigDecimal value = new BigDecimal("0");

		if (money.compareTo(new BigDecimal(1000)) >= 0) {
			value = money.multiply(new BigDecimal("0.03"));
			
			if (money.compareTo(new BigDecimal(10000)) > 0) {
				value = value.add(new BigDecimal("250"));
			}
		}

		return value;
	}
}
