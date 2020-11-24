package edu.princeton.cs.algs4;

import static org.assertj.core.api.Assertions.*;
import net.jqwik.api.*;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

import java.util.Iterator;

import edu.princeton.cs.algs4.collections.Stack;

public class TestStack {
	
	@Property
	void testEmpty(@ForAll Integer[] v) {
		
		Stack<Integer> s = new Stack<>();
		
		// At the beginning, the stack should be empty.
		assertThat(s.isEmpty()).isEqualTo(true);
		
		// Push the items onto the stack.
		for (int i = 0; i < v.length; i++) {
			s.push(v[i]);
		}
		
		// If v is not empty, the stack should not be empty as well.
		if (v.length > 0) {
			assertThat(s.isEmpty()).isEqualTo(false);
		} else {
			assertThat(s.isEmpty()).isEqualTo(true);
		}
		
		try {
			// Pop the items from the stack.
			for (int i = 0; i < v.length; i++) {
				s.pop();
			}
		} catch (NoSuchElementException e) {
			fail("Custom stack is empty.");
		}
		
		// At the end, the stack should be empty again.
		assertThat(s.isEmpty()).isEqualTo(true);
	}
	
	@Property
	void testSize(@ForAll Integer[] v) {
		
		Stack<Integer> s = new Stack<>();
		
		// At the beginning, the size of the stack should be 0.
		assertThat(s.size()).isEqualTo(0);
		
		// Push the items onto the stack.
		for (int i = 0; i < v.length; i++) {
			s.push(v[i]);
		}
		
		// If the size of v is not 0, the size of the stack should not be 0 as well.
		if (v.length > 0) {
			assertThat(s.size()).isEqualTo(v.length);
		} else {
			assertThat(s.size()).isEqualTo(0);
		}
		
		try {
			// Pop the items from the stack.
			for (int i = 0; i < v.length; i++) {
				s.pop();
			}
		} catch (NoSuchElementException e) {
			fail("Custom stack is empty.");
		}
		
		// At the end, the size of the stack should be 0 again.
		assertThat(s.size()).isEqualTo(0);
	}
	
	@Property
	void testPush(@ForAll Integer[] v) {
		
		Stack<Integer> s		   = new Stack<>();
		java.util.Stack<Integer> t = new java.util.Stack<>();
		
		// At the beginning the size of the stacks should match.
		assertThat(s.size()).isEqualTo(t.size());
		
		try {
			// Push the same items onto the stacks.
			for (int i = 0; i < v.length; i++) {
				s.push(v[i]);
				t.push(v[i]);
				// The size should always match.
				assertThat(s.size()).isEqualTo(t.size());
				// The item at the top of the stacks should always match.
				assertThat(s.peek()).isEqualTo(t.peek());
			}

		} catch (NoSuchElementException e) {
			fail("Custom stack is empty.");
		} catch (EmptyStackException e) {
			fail("Java stack is empty.");
		}
	}

	@Property
	void testPop(@ForAll Integer[] v) {
		
		Stack<Integer> s		   = new Stack<>();
		java.util.Stack<Integer> t = new java.util.Stack<>();
		
		// At the beginning, pop() should throw an exception.
		assertThatThrownBy(() -> s.pop()).isInstanceOf(NoSuchElementException.class)
										 .hasMessage("Stack underflow");
		
		// Push the same items onto the stacks.
		for (int i = 0; i < v.length; i++) {
			s.push(v[i]);
			t.push(v[i]);
		}
		
		try {
			// Pop the items from the stacks and check if the returned items match.
			for (int i = 0; i < v.length; i++) {
				assertThat(s.pop()).isEqualTo(t.pop());
			}
		} catch (NoSuchElementException e) {
			fail("Custom stack is empty.");
		} catch (EmptyStackException e) {
			fail("Java stack is empty.");
		}
		
		// At the end, pop() should throw an exception again.
		assertThatThrownBy(() -> s.pop()).isInstanceOf(NoSuchElementException.class)
										 .hasMessage("Stack underflow");
	}
	
	@Property
	void testPeek(@ForAll Integer[] v) {
		Stack<Integer> s		   = new Stack<>();
		java.util.Stack<Integer> t = new java.util.Stack<>();
		
		// At the beginning, peek() should throw an exception.
		assertThatThrownBy(() -> s.peek()).isInstanceOf(NoSuchElementException.class)
										  .hasMessage("Stack underflow");
		
		try {
			// Push the same items onto the stacks and check if the items returned by peek() match.
			for (int i = 0; i < v.length; i++) {
				s.push(v[i]);
				t.push(v[i]);
				assertThat(s.peek()).isEqualTo(t.peek());
			}
			
			// Check if the items returned by peek() match and then pop those items from the stacks.
			for (int i = 0; i < v.length; i++) {
				assertThat(s.peek()).isEqualTo(t.peek());
				s.pop();
				t.pop();
			}
		} catch (NoSuchElementException e) {
			fail("Custom stack is empty.");
		} catch (EmptyStackException e) {
			fail("Java stack is empty.");
		}
		
		// At the end, peek() should throw an exception again.
		assertThatThrownBy(() -> s.peek()).isInstanceOf(NoSuchElementException.class)
										  .hasMessage("Stack underflow");
	}
	
	@Property
	void testToString(@ForAll Integer[] v) {
		Stack<Integer> s = new Stack<>();
		String tmp       = "";
		
		// At the beginning, the string representation should be empty.
		assertThat(s.toString()).isEqualTo("");
		
		
		// Push items onto the stack, insert them at the beginning of tmp and check if they match.
		for (int i = 0; i < v.length; i++) {
			s.push(v[i]);
			
		    tmp = v[i] + " " + tmp;

			assertThat(s.toString()).isEqualTo(tmp);
		}
		
		try {
			// Pop the items from the stack, remove them from tmp and check if they match.
			for (int i = 0; i < v.length; i++) {
				s.pop();
				tmp = tmp.replaceAll(".+ ", "");
			}
			assertThat(s.toString()).isEqualTo(tmp);
		} catch (NoSuchElementException e) {
			fail("Custom stack is empty.");
		}
		
		
		// At the end, the string representation should be empty again.
		assertThat(s.toString()).isEqualTo("");
	}
	
	
	@Property
	void testIterator(@ForAll Integer[] v) {
		
		Stack<Integer> s = new Stack<>();
		Iterator<Integer> it;
		
		// At the beginning, the returned iterator should not have a next value.
		it = s.iterator();
		assertThat(it.hasNext()).isEqualTo(false);
		
		try {
			// The returned iterator should be the last element inserted.
			for (int i = 0; i < v.length; i++) {
				s.push(v[i]);
				it = s.iterator();
				assertThat(it.hasNext()).isEqualTo(true);
				assertThat(it.next()).isEqualTo(v[i]);
			}
			
			// The items in v and s should match.
			it = s.iterator();
			for (int i = v.length - 1; i >= 0; i--) {
				assertThat(it.hasNext()).isEqualTo(true);
				assertThat(it.next()).isEqualTo(v[i]);
			}
			
			// Popping an item should update the returned iterator.
			for (int i = v.length - 1; i >= 0; i--) {
				it = s.iterator();
				assertThat(it.hasNext()).isEqualTo(true);
				assertThat(it.next()).isEqualTo(v[i]);
				s.pop();
			}
		} catch (NoSuchElementException e) {
			fail("Custom stack is empty.");
		}
		
		// At the end, the returned iterator should not have a next value.
		it = s.iterator();
		assertThat(it.hasNext()).isEqualTo(false);
	}
	
}
