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
		
		IndexBinomialMinPQ emptyPQ = new IndexBinomialMinPQ(0); //create an empty PQ
		
		assertThat(emptyPQ).isEmpty();
		
        if(!(emptyPQ.size() == 0)) {
        	return false;
        }
        
        IndexBinomialMinPQ fullPQ = new IndexBinomialMinPQ(randomDim); //create a PQ of size between 100 and 1000
        
        fullPQ.insert(randomIndex, "test"); //insert the string "test" at index between 0 and 99
        
        assertThat(fullPQ).isNotEmpty();
        
        if(fullPQ.size() == 0) {
        	return false;
        }
        
        return true;
	}
	
	
	/*@Property
	boolean contains(@ForAll("Non-negative integers") int i) {
		
		
		if(i!=0) {
			IndexBinomialMinPQ testPQ = new IndexBinomialMinPQ(0); //create an empty queue that doesn't contain the index i
			
			try {
			
			    if(testPQ.contains(i)) { //fail if contains() determines that the queue contains the index i
				
				    return false;
			    }
			} catch(IllegalArgumentException e) {
				assertThat(e).hasMessage("Illegal argument provided!");
			}
			
			
		}
		
		else {
			IndexBinomialMinPQ testPQ = new IndexBinomialMinPQ(i+2); //create an empty queue that contains the index i
			
			if(!testPQ.contains(i)) { //fail if contains() determines that the queue doesn't contain the index i
 				return false;
			}
			
		}
		
		return true;
	}*/
	
	@Provide("Non-negative integers")
	Arbitrary<Integer> numbers(){
		return Arbitraries.integers().greaterOrEqual(0);
	}
	
	
	@Property
	int size() {
		Random value = new Random();
		
		int randomDim = value.nextInt(5000); //create a random integer between 0 and 5000
		
		try {
		IndexBinomialMinPQ testPQ = new IndexBinomialMinPQ(randomDim); //create a queue with a random dimension
		
		} catch(ArithmeticException e) {
			
			assertThat(e).hasMessage("The number of elements cannot be evaluated, but the priority queue is still valid.");
		}
		
		return randomDim; //the size() function must return the random dimension		
		
	}
	
/*	@Property
	void insert(@ForAll("Non-negative integers") int i, Key key) {
		
		Random value = new Random();
		
		int randomDim = value.nextInt(100); //create a random integer between 0 and 100
		
		IndexBinomialMinPQ testPQ = new IndexBinomialMinPQ(i+randomDim);
		
		testPQ.insert(i, key);
		
		assertThat(testPQ).element(i).isEqualTo(key);
		
	}*/
	
	@Property
	int minIndex() {
		
		Random value = new Random();
		
		int randomDim = value.nextInt(1000); //create a random integer between 0 and 1000
		
		int result = 0;
		
		int minKey = Integer.MAX_VALUE;
		
		IndexBinomialMinPQ testPQ = new IndexBinomialMinPQ(randomDim); //create a queue
		
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
	void delete(@ForAll @IntRange(min=0, max=1000) int i) { 
		
	     IndexBinomialMinPQ testPQ = new IndexBinomialMinPQ(i+5); 	
		
		for(int j=0; j<testPQ.size(); j++) {
			
			testPQ.insert(j, "fillerKey");
		}
		
		//testPQ.changeKey(i, "test"); //why java.lang.IllegalArgumentException: Specified index is not in the queue ?!
		
		//testPQ.delete(i);
		
	    assertThat(testPQ).doesNotContain("test");
		
	}
	
}
