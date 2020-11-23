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
}
