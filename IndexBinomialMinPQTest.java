package edu.princeton.cs.algs4;

import org.assertj.core.api.Assertions;

import edu.princeton.cs.algs4.beyond.IndexBinomialMinPQ;
import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;

import java.util.Comparator;
import java.util.Iterator;
import static org.assertj.core.api.Assertions.*;
import java.util.Random;

public class IndexBinomialMinPQTest {
	
	@Property
	boolean isEmpty() {
		
		Random value = new Random();
		
		int randomDim = value.nextInt(1000) + 100; //create a random integer between 100 and 1000
		
		int randomIndex = value.nextInt(99); //create a random integer between 0 and 99
		
		IndexBinomialMinPQ<Integer> emptyPQ = new IndexBinomialMinPQ<Integer>(0); //create an empty PQ
		
		assertThat(emptyPQ).isEmpty();
		
        if(!(emptyPQ.size() == 0)) {
        	return false;
        }
        
        IndexBinomialMinPQ<String> fullPQ = new IndexBinomialMinPQ<>(randomDim); //create a PQ of size between 100 and 1000
        
        fullPQ.insert(randomIndex, "test"); //insert the string "test" at index between 0 and 99
        
        assertThat(fullPQ).isNotEmpty();
        
        if(fullPQ.size() == 0) {
        	return false;
        }
        
        return true;
	}
	
	
	@Property
	boolean contains(@ForAll @IntRange(min=1, max=1000) int i) { //if we use positive integers (see below), we get the "OutOfMemoryError"
		
		
			IndexBinomialMinPQ<String> testPQ = new IndexBinomialMinPQ<>(i+1); //create an empty queue that contains the index i
			
			testPQ.insert(i, "some key");
			
			
			try {
			
			    if(testPQ.contains(i) && !testPQ.contains(i-1)) { 
				
				    return true;
			    }
			    
			    
			    else {
			    	return false;
			    }
			    
			} catch(IllegalArgumentException e) {
				
				assertThat(e).hasMessage("Illegal argument provided!");
				
			}
			
		return false;
	}
	
	@Provide("Non-negative integers")
	Arbitrary<Integer> numbers(){
		return Arbitraries.integers().greaterOrEqual(0);
	}
	
	@Provide("Positive integers")
	Arbitrary<Integer> numbers2(){
		return Arbitraries.integers().greaterOrEqual(1);
	}
	
	
	@Property
	int size() {
		Random value = new Random();
		
		int randomDim = value.nextInt(5000); //create a random integer between 0 and 5000
		
		try {
		IndexBinomialMinPQ<Integer> testPQ = new IndexBinomialMinPQ<>(randomDim); //create a queue with a random dimension
		
		} catch(ArithmeticException e) {
			
			assertThat(e).hasMessage("The number of elements cannot be evaluated, but the priority queue is still valid.");
		}
		
		return randomDim; //the size() function must return the random dimension		
		
	}
	
	@Property
	void insert(@ForAll @IntRange(min=0, max=1000) int i, @ForAll @IntRange(min=0, max=1000) Integer key) {
		
		IndexBinomialMinPQ<Integer> testPQ = new IndexBinomialMinPQ<>(i+1);
	
		testPQ.insert(i, key);
		
		assertThat(testPQ.keyOf(i)).isEqualTo(key);   
		
	}
	
	@Property
	int minIndex() {
		
		Random value = new Random();
		
		int randomDim = value.nextInt(1000); //create a random integer between 0 and 1000
		
		int result = 0;
		
		int minKey = Integer.MAX_VALUE;
		
		IndexBinomialMinPQ<Integer> testPQ = new IndexBinomialMinPQ<>(randomDim); //create a queue
		
		for(int i=0; i<testPQ.size(); i++) { //fill the queue with random (integer) keys
			
		    int randomKey = value.nextInt();
		    
		    if(randomKey <= minKey) { //if the current key is the minimum (so far), set the value of minimum key to the current key
		    	minKey = randomKey;
		        result = i; //remember the index associated with the minimum key
		    }
			
			testPQ.insert(i, randomKey);
		}
		
		return result;
		
	}
	
	/*@Property
	Key minKey() {
		
	}*/
	
	/*@Property
	int delMin() 
	{
	    return 0;
	}*/
	
    /*@Property
    Key keyOf(int i) {
    
    }*/
	
	/*@Property
	void changeKey(int i, Key key) {
		
	}*/
	
	/*@Property
	void decreaseKey(int i, Key key) {
		
	}*/
	
	/*@Property
	void increaseKey(int i, Key key) {
		
	}*/
	
	@Property
	void delete(@ForAll @IntRange(min=1, max=1000) int i) { 
		
	     IndexBinomialMinPQ<String> testPQ = new IndexBinomialMinPQ<>(i+10);
	     
	     testPQ.insert(i-1, "another Key");
		
		 testPQ.insert(i, "some Key"); 
		
		 testPQ.insert(i+1, "key");
		
		 testPQ.delete(i);
		 
		 testPQ.insert(i, "yet another key");
		 
		 assertThat(testPQ.keyOf(i)).doesNotContain("some Key");
	   
	}
	
}
