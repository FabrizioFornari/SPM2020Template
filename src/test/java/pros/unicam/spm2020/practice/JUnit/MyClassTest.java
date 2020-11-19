package pros.unicam.spm2020.practice.JUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumeFalse;

import java.time.LocalDate;
import static java.time.Duration.ofMillis;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import pros.unicam.spmn2020NewProject.MyClass;

public class MyClassTest {

	@Test
	public void testExceptionIsThrown() {

		MyClass tester = new MyClass(); // MyClass is tested
		assertThrows(IllegalArgumentException.class, () -> {
			tester.multiply(1000, 5);
		});

	}

	@Test
	public void exceptionTesting() {
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			throw new IllegalArgumentException("a message");
		});
		assertEquals("a message", exception.getMessage());
	}

	@Test
	public void testMultiplyShouldReturnProperMultiplicationResult() {
		// Assume.assumeFalse(System.getProperty("os.name").contains("Mac OS X"));
		MyClass tester = new MyClass(); // MyClass is tested

		// assert statements
		assertEquals(50, tester.multiply(10, 5), "10 x 5 must be 50");
	}

	@Test
	public void testMultiplyShouldAcceptAnyValue() {
		MyClass tester = new MyClass(); // MyClass is tested

		// assert statements
		assertEquals(50, tester.multiply(10, 5), "10 x 5 must be 50");
		assertEquals(5000, tester.multiply(1000, 5), "1000 x 5 must be 5000");
	}

	@Test
	public void multiplicationOfXByOneShouldReturnX() {
		// Assumption
		assumeFalse(System.getProperty("os.name").contains("Mac OS X"));

		MyClass tester = new MyClass();// MyClass is tested

		// assert statements
		assertEquals(22, tester.multiply(22, 1), "22 x 1 must be 22");
		assertEquals(22, tester.multiply(1, 22), "1 x 22 must be 22");
		assertEquals(1, tester.multiply(1, 1), "1 x 1 must be 1");
	}
	

	@Test
	public void multiplicationOfZeroIntegersShouldReturnZero() {
		MyClass tester = new MyClass(); // MyClass is tested

		// assert statements
		assertEquals(0, tester.multiply(10, 0), "10 x 0 must be 0");
		assertEquals(0, tester.multiply(0, 10), "0 x 10 must be 0");
		assertEquals(0, tester.multiply(0, 0), "0 x 0 must be 0");

	}

	@ParameterizedTest
	@ValueSource(strings = { "Hello", "JUnit" })
	void withValueSource(String word) {
		assertNotNull(word);
	}

	@ParameterizedTest
	@ValueSource(strings = { "pippo", "radar", "able was I ere I saw elba" })
	void palindromes(String candidate) {
		MyClass tester = new MyClass();
		assertTrue(tester.isPalindrome(candidate));
	}

	@Test // Multi-line script, custom engine name and custom reason.
	@EnabledIf(value = { "load('nashorn:mozilla_compat.js')", "importPackage(java.time)", "",
			"var today = LocalDate.now()", "var tomorrow = today.plusDays(1)",
			"tomorrow.isAfter(today)" }, engine = "nashorn", reason = "Self-fulfilling: {result}")
	public void theDayAfterTomorrow() {
		LocalDate today = LocalDate.now();
		LocalDate tomorrow = today.plusDays(1);
		assertTrue(tomorrow.isAfter(today));
	}

	@ParameterizedTest
	@Disabled
	@ValueSource(ints = { 1, 2, 3, -1 })
	void test(int i) {
		assumeTrue(i >= 0, "Wrong Input, Only positive ints please");

		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	void timeoutExceeded() {
		// The following assertion fails with an error message similar to:
		// execution exceeded timeout of 10 ms by 91 ms
		assertTimeout(ofMillis(10), () -> {
			// Simulate task that takes more than 10 ms.
			Thread.sleep(100);
		});
	}
	
	 @Test
	    void timeoutExceededWithPreemptiveTermination() {
	        // The following assertion fails with an error message similar to:
	        // execution timed out after 10 ms
	        assertTimeoutPreemptively(ofMillis(10), () -> {
	            // Simulate task that takes more than 10 ms.
	            Thread.sleep(100);
	        });
	    }

}
