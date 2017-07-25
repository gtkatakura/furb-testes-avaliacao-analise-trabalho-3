package case2;

import static org.junit.Assert.*;

import org.junit.Test;

public class Part2Test {

	@Test
	public void part2() {
		Carro c1 = new Carro("ABC-0101", "Gol", 2009);
		Carro c2 = new Carro("XXX-0909", "Palio", 2011);
		Carro c3 = new Carro("VML-1223", "Celta", 2020);
		
		CarrosControle cControle = new CarrosControle();
		
		assertTrue(cControle.inserir(c1));
		assertTrue(cControle.inserir(c2));
		assertTrue(cControle.inserir(c3));
		assertTrue(cControle.inserir(new Carro("ABC-0200", "K", 2015)));
		assertFalse(cControle.inserir(new Carro("ABC-0101", "Gol", 2009)));
		
		assertTrue(cControle.buscarModelo("ABC-0101").equals("Gol"));
		assertEquals(4, cControle.total(),0);
		assertTrue(c1.validaPlaca("ABC-0101"));
		assertFalse(c1.validaPlaca("ABCD0101"));
		
		Carro cAlterar = new Carro();
		cAlterar.setAno(2020);
		cControle.alterar("XXX-0909", cAlterar);
		assertEquals(cControle.get("XXX-0909").getAno(), 2020, 0);
		assertTrue(cControle.get("XXX-0909").getModelo().equals("Palio"));
	}
}
