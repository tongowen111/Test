package TestString;

public class TestString {
	public static void main(String[] args) {
		String s1 = "abc";
		String s2 = new String("abc");
		String s3 = "abc";
		String s4 = "ab"+"c";
		System.out.println(s1==s2);
		System.out.println(s1==s3);
		System.out.println(s1==s4);
	}
}
