package edu.princeton.cs.algs4;

import java.util.List;

import java.util.Map;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.Arrays; //don't need all those imports; I was just experimenting with various things

import java.util.Iterator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ValueSource;

import edu.princeton.cs.algs4.collections.Vector;
import edu.princeton.cs.algs4.sorting.InsertionX;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.Assume;
import net.jqwik.api.Combinators;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.Report;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Positive;
import net.jqwik.api.lifecycle.BeforeProperty;
import java.util.Random;

public class VectorTests {
	
	@Property
	int dimension(@ForAll @IntRange(min=0, max=1000) int d) { //if we set max to Integer.MAX_VALUE, we get the Java heap error.
		
		double[] testArray = new double[d];
		
		return testArray.length;
	    
	} //obviously, we get an error when the integer is negative

	
	@Property
	double magnitude() { // the array is identical to the vector (logically speaking); don't know how to use the @ForAll in the function's body
		
		
		double result = 0;
		
		Random value = new Random();
		
		int randomDim = value.nextInt(1000); //pick a random dimension, i.e., an integer between 0 and 1000
		
		double[] testArray = new double[randomDim];
		
		for(int i=0; i<randomDim; i++) {
			
			double randomValue = 100000*value.nextDouble(); //pick a random double value between 0 and 100000
			
			testArray[i] = randomValue; //fill the test array with random doubles
			
			result += testArray[i] * testArray[i]; //compute the magnitude
		}
		
		
		return Math.sqrt(result);
	
	}
	
	@Property
	double dot(Vector v) { // won't work until we have the generator
		
		double result = 0.0;
		
		Random value = new Random();
		
		//int randomDim = value.nextInt(1000); //pick a random dimension, i.e., an integer between 0 and 1000
		
		//double[] testArray = new double[randomDim]; uncomment to check that dimension (most likely) don't agree and check if this action throws an error, as required
		
		double[] testArray2 = new double[v.dimension()]; //interpret the parameter vector as an array
		
		double[] testArray = new double[v.dimension()]; //for the 'successful' test, use the same dimension as the parameter vector
		
		
		
		for(int i=0; i<v.dimension(); i++) {
			double randomValue = 100000*value.nextDouble(); //pick a random double value between 0 and 100000
			
			testArray[i] = randomValue; //fill the test array with random doubles
			
			testArray2[i] = v.cartesian(i); //get the ith value from the parameter vector's data
			
			result += testArray[i] * testArray2[i]; //compute the dot product of the two arrays (aka vectors)
		}
		
		
		return result;
	}
	
	@Provide // no idea how to do this correctly
	Arbitrary<Vector> provideVector() {
		  return Arbitraries.defaultFor(List.class, String.class).map(//something);
		}
	

}
