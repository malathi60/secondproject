package mypack;

import org.testng.annotations.Test;

public class Calculation {

	@Test
	public void addition() 
	{
		int x=100;
		int y=500;
		int z=x+y;
		System.out.println("sum of numbers is :" + z);

	}

}
