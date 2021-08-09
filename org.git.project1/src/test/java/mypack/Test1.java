package mypack;

import org.testng.annotations.Test;

public class Test1 
{
	@Test
	public void method1()
	{
		String x="hi";
		String y="how are you?";
		String w=x+y;
		System.out.println("String after concatination:"  + w);
	}
}
