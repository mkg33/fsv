package edu.princeton.cs.algs4;

import static org.assertj.core.api.Assertions.*;
import edu.princeton.cs.algs4.collections.Stack;
import net.jqwik.api.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TestStack {
	
	@Property
	void testEmpty(@ForAll Integer[] v) {
		
		Stack<Integer> s = new Stack<>();
		
		// At the beginning the stack should be empty.
		assertThat(s.isEmpty()).isEqualTo(true);
		
		// Push items onto the stack.
		for (int i = 0; i < v.length; i++) {
			s.push(v[i]);
		}
		
		// Check if the stack is empty after pushing items.
		assertThat(s.isEmpty()).isEqualTo(!(v.length > 0));
		
		try {
			// Pop items from the stack.
			for (int i = 0; i < v.length; i++) {
				s.pop();
			}
			
			// Now it should be empty.
			assertThat(s.isEmpty()).isEqualTo(true);
		} catch (NoSuchElementException e) {
			// The implementation didn't push all elements of v.
			assertThat(e).hasMessage("Stack underflow");
			fail("Stack did not push all elements.");
		}
		
	}
	
	@Property
	void testSize(@ForAll Integer[] v) {
		
		Stack<Integer> s = new Stack<>();
		
		// At the beginning the stack should be empty.
		assertThat(s.size()).isEqualTo(0);
		
		// Push items onto the stack and test size.
		for (int i = 0; i < v.length; i++) {
			s.push(v[i]);
			assertThat(s.size()).isEqualTo(i + 1);
		}
				
		try {
			// Pop items from the stack and test size.
			for (int i = 0; i < v.length; i++) {
				s.pop();
				assertThat(s.size()).isEqualTo(v.length - i - 1);
			}
		} catch (NoSuchElementException e) {
			// The implementation didn't push all elements of v.
			assertThat(e).hasMessage("Stack underflow");
			fail("Stack did not push all elements.");
		}
	}
	
	@Property
	void testPush(@ForAll Integer[] v) {
		// TODO
	}
	
	@Property
	void testPeek(@ForAll Integer[] v) {
		Stack<Integer> s = new Stack<>();
		
		// At the beginning, peek() should throw an Exception.
		assertThatThrownBy(() -> s.peek()).isInstanceOf(NoSuchElementException.class)
											  .hasMessage("Stack underflow");
		
		try {
			// push() items onto the stack and test peek().
			for (int i = 0; i < v.length; i++) {
				s.push(v[i]);
				assertThat(s.peek()).isEqualTo(v[i]);
			}
			
			// Test peek() and then pop() the item.
			for (int i = 0; i < v.length; i++) {
				assertThat(s.peek()).isEqualTo(v[v.length - i - 1]);
				s.pop();
			}
		} catch (NoSuchElementException e) {
			// The implementation didn't push all elements of v.
			assertThat(e).hasMessage("Stack underflow");
			fail("Stack did not push all elements.");
		}
		
		// At the end, peek() should throw an Exception.
		assertThatThrownBy(() -> s.peek()).isInstanceOf(NoSuchElementException.class)
										  .hasMessage("Stack underflow");
	}
	
	@Property
	void testPop(@ForAll Integer[] v) {
		Stack<Integer> s = new Stack<>();
		
		// At the beginning, pop() should throw an Exception.
		assertThatThrownBy(() -> s.pop()).isInstanceOf(NoSuchElementException.class)
										 .hasMessage("Stack underflow");
		
		try {
			// push() items onto the stack.
			for (int i = 0; i < v.length; i++) {
				s.push(v[i]);
			}
			
			// pop() items and check if they are equal to v's items.
			for (int i = 0; i < v.length; i++) {
				assertThat(s.pop()).isEqualTo(v[v.length - i - 1]);
			}
		} catch (NoSuchElementException e) {
			// The implementation didn't push all elements of v.
			assertThat(e).hasMessage("Stack underflow");
			fail("Stack did not push all elements.");
		}
		
		// At the end, pop() should throw an Exception.
		assertThatThrownBy(() -> s.pop()).isInstanceOf(NoSuchElementException.class)
										 .hasMessage("Stack underflow");
	}
	
	@Property
	void testToString(@ForAll Integer[] v) {
		Stack<Integer> s = new Stack<>();
		String tmp = "";
		
		// At the beginning, toString() should return an empty string.
		assertThat(s.toString()).isEqualTo("");
		
		try {
			// push() items onto the stack, add them to tmp and check toString().
			for (int i = 0; i < v.length; i++) {
				s.push(v[i]);
				if (i > 0) {
					tmp = v[i] + " " + tmp;
				} else {
					tmp = "" + v[i];
				}
				assertThat(s.toString()).isEqualTo(tmp);
			}
			
			// pop() items from the stack, remove item from tmp and check toString().
			for (int i = 0; i < v.length; i++) {
				s.pop();
				tmp = tmp.replaceAll(".+ ", "");
				assertThat(s.toString()).isEqualTo(tmp);
			}
		} catch (NoSuchElementException e) {
			// The implementation didn't push all elements of v.
			assertThat(e).hasMessage("Stack underflow");
			fail("Stack did not push all elements.");
		}
		
		// At the end, toString() should return an empty string.
		assertThat(s.toString()).isEqualTo("");
	}
	
}
