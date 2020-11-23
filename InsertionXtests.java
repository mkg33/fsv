package edu.princeton.cs.algs4;

import org.assertj.core.api.Assertions;
import edu.princeton.cs.algs4.sorting.InsertionX;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;

public class InsertionXtests {

    @Property
    void isSorted(@ForAll Comparable[] v) {
        InsertionX.sort(v);
        Assertions.assertThat(v).isSorted();
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
