package part1;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Exercise1Test {	
	public void assertEqualsBigDecimal(BigDecimal result, BigDecimal expected) {
		assertEquals(result.stripTrailingZeros(), expected.stripTrailingZeros());
	}

	@Test
	public void scenario1() {
		Product vodka = new Product("vodka", new BigDecimal("10"));
		ProductConsumed vodkaConsumed = new ProductConsumed(vodka, 10);
		
		Product doritos = new Product("doritos", new BigDecimal("3.5"));
		ProductConsumed doritosConsumed = new ProductConsumed(doritos, 1);
		
		List<ProductConsumed> consumeds = new ArrayList<>();
		
		consumeds.add(vodkaConsumed);
		consumeds.add(doritosConsumed);
		
		AccountPayable accountWithConsumeds = new AccountPayable(consumeds);
		AccountPayable accountWithoutConsumeds = new AccountPayable(new ArrayList<>());
		
		assertEqualsBigDecimal(accountWithConsumeds.calculate(), new BigDecimal("103.5"));
		assertEqualsBigDecimal(accountWithoutConsumeds.calculate(), new BigDecimal("0"));
	}
	
	@Test
	public void scenario2_whenValueIsLowerThen1000() {
		TaxCalculated calculated = new TaxCalculated(new BigDecimal("500"));
		assertEqualsBigDecimal(calculated.getValue(), new BigDecimal("0"));
	}
	
	@Test
	public void scenario2_whenValueIsBetween1000And10000() {
		TaxCalculated calculated = new TaxCalculated(new BigDecimal("1000"));
		assertEqualsBigDecimal(calculated.getValue(), new BigDecimal("30"));
	}
	
	@Test
	public void scenario2_whenValueIsGreaterThen10000() {
		TaxCalculated calculated = new TaxCalculated(new BigDecimal("20000"));
		assertEqualsBigDecimal(calculated.getValue(), new BigDecimal("850"));
	}
	
	@Test
	public void scenario3() {
		// Caminho feliz
		PeopleCollection peoples = new PeopleCollection();
		
		People maria = new People("Maria", LocalDate.of(2000, 7, 10));
		peoples.add(maria);

		People joao = new People("João", LocalDate.of(1994, 12, 21));
		peoples.add(joao);

		People removed = peoples.remove();
		assertEquals(maria, removed);

		// Lista vazia
		peoples = new PeopleCollection();
		removed = peoples.remove();
		assertNull(removed);
	}
}
