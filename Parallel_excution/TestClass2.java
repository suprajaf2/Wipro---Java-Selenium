package Parallel_excution;

import org.testng.annotations.Test;

public class TestClass2 {

	@Test
	public void testMethod5() {
		System.out.println("TestClass2 >> testMethod4 >> " +Thread.currentThread().getId());
	}
	
	@Test
	public void testMethod6() {
		System.out.println("TestClass2 >> testMethod6 >> " +Thread.currentThread().getId());
	}
	
	@Test
	public void testMethod7() {
		System.out.println("TestClass2 >> testMethod7 >> " +Thread.currentThread().getId());
	}
	
}