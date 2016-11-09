package jp.smaphonia;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;

import static org.assertj.core.api.Assertions.*;

public class BigOrSmallTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGetChoiceBig() {
		try {
			
			try (InputStream in = new ByteArrayInputStream("0".getBytes()); Scanner scanner = new Scanner(in)) {
				BigOrSmall game = spy(new BigOrSmall());
				doReturn(in).when(game).getInputStream();
				Card currentCard = Card.createCard(4);
				int choice = game.getChoice(scanner, currentCard, 1);
				
//				assertEquals(BigOrSmall.CHOICE_BIG, choice);
				assertThat(choice).isEqualTo(BigOrSmall.CHOICE_BIG);
			}
		} catch (Exception e) {
			fail();
		}
	}
	@Test
	public void testGetChoiceSmall() {
		try {
			
			try (InputStream in = new ByteArrayInputStream("1".getBytes()); Scanner scanner = new Scanner(in)) {
				BigOrSmall game = spy(new BigOrSmall());
				doReturn(in).when(game).getInputStream();
				Card currentCard = Card.createCard(4);
				int choice = game.getChoice(scanner, currentCard, 1);
				
//				assertEquals(BigOrSmall.CHOICE_SMALL, choice);
				assertThat(choice).isEqualTo(BigOrSmall.CHOICE_SMALL);
			}
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testPrint() {
		try {
			try (ByteArrayOutputStream bas = new ByteArrayOutputStream(); PrintStream ps = new PrintStream(bas)) {
				BigOrSmall game = spy(new BigOrSmall());
				doReturn(ps).when(game).getPrintStream();
				String message = "test";
				game.print(message);
				
//				assertEquals(message, new String(bas.toByteArray()));
				assertThat(new String(bas.toByteArray())).isEqualTo(message);
			}

		} catch (Exception e) {
			fail();
		}
	}
	@Test
	public void testPrintln() {
		try {
			try (ByteArrayOutputStream bas = new ByteArrayOutputStream(); PrintStream ps = new PrintStream(bas)) {
				BigOrSmall game = spy(new BigOrSmall());
				doReturn(ps).when(game).getPrintStream();
				String message = "test";
				game.println(message);
				byte[] bytes = bas.toByteArray();
				assertEquals(message, new String(bytes, 0, bytes.length - 1));
				assertEquals(bytes[bytes.length - 1], '\n');
			}

		} catch (Exception e) {
			fail();
		}
	}

}
