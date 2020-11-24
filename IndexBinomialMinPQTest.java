package edu.princeton.cs.algs4;

import org.assertj.core.api.Assertions;

import edu.princeton.cs.algs4.beyond.IndexBinomialMinPQ;
import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

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
			
		testPQ.insert(i, "some key"); //insert a test key at index i
			
			
		try {
			
	        if(testPQ.contains(i) && !testPQ.contains(i-1)) { //the index with the initialised key should return true; but contains with the decremented index should return false
				
			    assertThat(testPQ.contains(i)).isEqualTo(true);
			    	
				return true;
			 }
			    
			else {
			    	
			    assertThat(testPQ.contains(i)).isEqualTo(false);
			    	
			    return false;
			}
			    
		} catch(IllegalArgumentException e) {
				
			assertThat(e).hasMessage("Illegal argument provided!");
				
		}
			
	return false;
	
    }
	
	
	@Property
	int size() {
		
		Random value = new Random();
		
		int randomDim = value.nextInt(5000); //create a random integer between 0 and 5000
		
		int result = 0;
		
		try {
			
		IndexBinomialMinPQ<Integer> testPQ = new IndexBinomialMinPQ<>(randomDim); //create a queue with a random dimension
		
		for(int i=0; i<randomDim; i++) { //fill the queue with random (integer) keys
			
			int randomKey = value.nextInt(); //create a random integer
			
			testPQ.insert(i, randomKey); //insert the randomKey into the queue at index i
			
			result++;
			
		}
		
		assertThat(testPQ.size()).isEqualTo(result);
		
		
		} catch(ArithmeticException e) {
			
			assertThat(e).hasMessage("The number of elements cannot be evaluated, but the priority queue is still valid.");
		}
		
		return result;	
		
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
		
		int randomDim = value.nextInt(1000) + 1; //create a random integer between 0 and 1000
		
		int result = 0;
		
		int minKey = Integer.MAX_VALUE;
		
		IndexBinomialMinPQ<Integer> testPQ = new IndexBinomialMinPQ<>(randomDim); //create a queue of size randomDim
		
		for(int i=0; i<randomDim; i++) { //fill the queue with random (integer) keys
			
		    int randomKey = value.nextInt();
		    
		    if(randomKey <= minKey) { //if the current key is the minimum (so far), set the value of minimum key to the current key
		    	
		    	minKey = randomKey;
		    	
		        result = i; //remember the index associated with the minimum key
		        
		    }
			
			testPQ.insert(i, randomKey); //insert the randomKey into the queue at index i
			
		}
		
		assertThat(testPQ.minIndex()).isEqualTo(result);

		
		return result; //returns the minimum key
		
	}
	
	
	@Property
	Integer minKey() {
		
		Random value = new Random();
		
		int randomDim = value.nextInt(1000) + 1; //create a random integer between 1 and 1000
		
		int result = Integer.MAX_VALUE;;
		
		IndexBinomialMinPQ<Integer> testPQ = new IndexBinomialMinPQ<>(randomDim); //create a queue of size randomDim
		
		for(int i=0; i<randomDim; i++) { //fill the queue with random (integer) keys
			
		    int randomKey = value.nextInt();
		    
		    if(randomKey <= result) { //if the current key is the minimum (so far), set the value of minimum key to the current key
		    	
		        result = randomKey;
		        
		    }
			
			testPQ.insert(i, randomKey); //insert the randomKey into the queue at index i
			
		}
		
		assertThat(testPQ.minKey()).isEqualTo(result);
		
		return result;
		
	}
	
	
	@Property
	int delMin() {
		
		Random value = new Random();
		
		int randomDim = value.nextInt(1000) + 1; //create a random integer between 1 and 1000
		
		int result = 0;
		
		IndexBinomialMinPQ<Integer> testPQ = new IndexBinomialMinPQ<>(randomDim+3); //create a queue of size randomDim+3
		
		testPQ.insert(0, 1);
		
		testPQ.insert(1, 0); //minimum key
		
		testPQ.insert(2, 3);
		
		assertThat(testPQ.delMin()).isEqualTo(1); //delete the minimum key and check if it returns 1
		
		testPQ.insert(1, 10);
		
		assertThat(testPQ.keyOf(1)).isNotEqualTo(0);
		
		return result;
	}
	
	
    @Property
    String keyOf(@ForAll @IntRange(min=1, max=1000) int i) {
    	
		IndexBinomialMinPQ<String> testPQ = new IndexBinomialMinPQ<>(i+1); //create a queue of size i+1
		
		testPQ.insert(i, "test key"); //insert a test key at index i
		
		assertThat(testPQ.keyOf(i)).isEqualTo("test key"); //the key at index i should be equal to the String "test key"
    	
    	return "test key";
    
    }
	
    
	@Property
	void changeKey(@ForAll @IntRange(min=1, max=1000) int i, @ForAll String key) {
		
		IndexBinomialMinPQ<String> testPQ = new IndexBinomialMinPQ<>(i+10); //create a queue of size i+10
		
		testPQ.insert(i-1, "another key"); //insert a key at index i-1 (to prevent the NullPointerException in the last line)
		
		testPQ.insert(i, key); //insert a (random) key at index i
		
		testPQ.insert(i+1, "yet another key"); //insert a key at index i+1 (to prevent the NullPointerException in the last line)
		
		testPQ.changeKey(i, "test"); //change the key at index i
		
		assertThat(testPQ.keyOf(i)).isEqualTo("test"); //the key at i should be "test 2"
		
	}
	
	
	@Property
	void decreaseKey(@ForAll @IntRange(min=1, max=1000) int i, @ForAll Integer key) {
		
		IndexBinomialMinPQ<Integer> testPQ = new IndexBinomialMinPQ<>(i+10); //create a queue of size i+10
		
		Random value = new Random();
		
		int randomKey = value.nextInt(1000); //create a random integer between 0 and 1000
		
		testPQ.insert(i-1, 5); //insert a key at index i-1 (to prevent the NullPointerException in the last line)
		
		testPQ.insert(i, randomKey); //insert a (random) key at index i
		
		testPQ.insert(i+1, 2); //insert a key at index i+1 (to prevent the NullPointerException in the last line)
		
		try {
			
		    if(key < testPQ.keyOf(i)) {
		
		        testPQ.decreaseKey(i, key); //decrease the key at i
		        
		        assertThat(testPQ.keyOf(i)).isLessThan(randomKey);  
		    }
		
		} catch(IllegalArgumentException e) {
			
			assertThat(e).hasMessage("Calling with this argument would not decrease the key");
			
		}
		
	}
	
	
	@Property
	void increaseKey(@ForAll @IntRange(min=1, max=1000) int i, @ForAll Integer key) {
		
		IndexBinomialMinPQ<Integer> testPQ = new IndexBinomialMinPQ<>(i+10); //create a queue of size i+10
		
		Random value = new Random();
		
		int randomKey = value.nextInt(1000); //create a random integer between 0 and 1000
		
		testPQ.insert(i-1, 5); //insert a key at index i-1 (to prevent the NullPointerException in the last line)
		
		testPQ.insert(i, randomKey); //insert a (random) key at index i
		
		testPQ.insert(i+1, 2); //insert a key at index i+1 (to prevent the NullPointerException in the last line)
		
		try {
			
		    if(key > testPQ.keyOf(i)) {
		
		        testPQ.increaseKey(i, key); //increase the key at i
		        
		        assertThat(testPQ.keyOf(i)).isGreaterThan(randomKey);  
		    }
		
		} catch(IllegalArgumentException e) {
			
			assertThat(e).hasMessage("Calling with this argument would not increase the key");
			
		}
			
	}
	
	
	@Property
	void delete(@ForAll @IntRange(min=1, max=1000) int i) { 
		
	     IndexBinomialMinPQ<String> testPQ = new IndexBinomialMinPQ<>(i+10); //create a queue of size i+10
	     
	     testPQ.insert(i-1, "another Key"); //insert a key at index i-1 (to prevent the NullPointerException in the last line)
		
		 testPQ.insert(i, "some Key"); //insert a key at index i
		
		 testPQ.insert(i+1, "key"); //insert a key at index i+1 (to prevent the NullPointerException in the last line)
		
		 try {
		 
		 testPQ.delete(i); //try to delete the key associated with index i
		 
		 } catch(NoSuchElementException e) {
			 
			 assertThat(e).hasMessage("Specified index is not in the queue");
			 
		 }
		 
		 testPQ.insert(i, "yet another key"); //insert another test String as key of index i
		 
		 assertThat(testPQ.keyOf(i)).doesNotContain("some Key");
	   
	}
	
	
	@Provide("Positive integers") //for exhaustive testing; sometimes using 'Positive integers' prevents the termination of the test and that's why we use the int range

	Arbitrary<Integer> numbers2(){
		
		return Arbitraries.integers().greaterOrEqual(1);	
	}
	
}


