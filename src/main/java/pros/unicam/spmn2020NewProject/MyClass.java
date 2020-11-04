package pros.unicam.spmn2020NewProject;

public class MyClass {

	
	public int multiply(int x, int y) {

		if (x > 1000) {
			throw new IllegalArgumentException("X should be less than 1000");
		}
		return x * y;
	}
	
	public boolean isPalindrome(String str) {
	    return str.equals(new StringBuilder(str).reverse().toString());
	}
}
