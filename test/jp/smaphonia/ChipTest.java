package jp.smaphonia;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ChipTest {

	Chip chip;
	
	@Before
	public void setUp() throws Exception {
		chip = new Chip();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		try {
			// 初期枚数になっていること
			int count0 = chip.getCount();
			assertEquals(Chip.INIT_COUNT, count0);
			chip.lose(2);
			int count1 = chip.getCount();
			assertEquals((count0 - 2), count1);
			chip.win(10);
			int count2 = chip.getCount();
			assertEquals((count1 + 10), count2);
		} catch (Exception e) {
			fail();
			
		}
	}
	// 負けても0以下の枚数にはならない
	@Test
	public void testLose() {
		try {
			chip.lose(Chip.INIT_COUNT + 1);
			int count = chip.getCount();
			assertEquals(0, count);
			
		} catch (Exception e) {
			fail();
		}
	}

}
