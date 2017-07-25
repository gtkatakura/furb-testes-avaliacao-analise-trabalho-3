package part1;

import java.math.BigDecimal;
import java.util.List;

public class AccountPayable {
	private List<ProductConsumed> consumeds;
	
	public AccountPayable(List<ProductConsumed> consumeds) {
		this.consumeds = consumeds;
	}
	
	public BigDecimal calculate() {
		return consumeds.stream()
			.map(consumed -> consumed.getValue())
			.reduce(BigDecimal.ZERO, (acc, current) -> acc.add(current));
	}
}
