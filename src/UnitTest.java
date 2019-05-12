import static org.junit.Assert.*;
import org.junit.Test;

import Model.Player;


public class UnitTest {
	
	@Test
	public void testPay() {

		Player player = new Player(1, 2, 3);
		Integer moneyBefore = 100;
		player.setMoney(moneyBefore);
		player.pay(50);
		Integer moneyAfter = player.getMoney();
		Integer moneyExpected = 50;
		assertEquals(moneyAfter, moneyExpected);

	}

	@Test
	public void testClamp() {
		
		Player player = new Player(1, 2, 3);
		double money = 50.0;
		Double res = player.clamp(money, 1.0, 50.5, 0.0);
		Double resExpected = 50.5;
		assertEquals(res, resExpected);

	}

	
	
	
}


