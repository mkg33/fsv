package edu.princeton.cs.algs4;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

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
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.Report;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Positive;
import net.jqwik.api.lifecycle.AfterProperty;
import net.jqwik.api.lifecycle.BeforeProperty;
import java.util.Random;

import java.util.Collections.*;

public class InsertionXtests {

    @Property
    void isSorted(@ForAll Comparable[] v) {
        InsertionX.sort(v);
        Assertions.assertThat(a).isSorted();
    }
	
	//@Property //how can we test it AT ALL without invoking sort(a)??? I always get the StackOverflowError :-(
    // P: I think because below you aren't using the InsertionX sort method,
    // but you are calling the same function recursively because it is also
    // called sort. So this is an inifinite recursive call and that's why the
    // stack grows so large and overflows.

	/*void sort(@ForAll Comparable[] a) {
	
		Comparable[] b = new Comparable[a.length]; //create a deep copy of a
		
		for(int i=0; i<a.length; i++) {
			b[i] = a[i];
		}
		
		sort(a);
		Arrays.sort(b);
		
		Assertions.assertThat(a).isEqualTo(b);
		
		//or even easier (if it worked...) : sort(a); Assertions.assertThat(a).isSorted(); produces StackOverflowError
	}*/

	/*@AfterProperty //this doesn't work either...
	void sort(Comparable[] a) {
		Assertions.assertThat(a).isSorted();
	}*/
	
}
