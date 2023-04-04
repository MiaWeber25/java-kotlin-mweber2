package com.example.javabasics;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class CapTest {

    Cap cap;

    @Test
    public void before() {
        cap = new Cap(100, Size.MEDIUM, "Hi");
    }

    @Test
    public void getSize() {
        assertEquals("", cap.getSize());
    }

    @Test
    public void setSize() {
        cap.setSize(Size.SMALL);
        assertEquals(Size.SMALL, cap.getSize());
    }

    @Test
    public void getLabel() {
        assertEquals("", cap.getLabel());
    }

    @Test
    public void setLabel() {
        cap.setLabel("x");
        assertEquals("x", cap.getLabel());
    }

    // use .equals() for Objects
    // == only checks to see if the pointers are pointing to the same memory location
    // needed for hashes (HashMap and HashSet)
    // Must be consistent with compareTo()
    // Java does not support operator overloading
    @Test
    public void testEquals() {
        Cap other = new Cap(100, Size.MEDIUM, "Hi");
        assertEquals(cap,other);
    }

    // needed for HashSet or HashMap (the key)
    // amortized O(1) for a good hash
    // as you calculate more hashes it also needs to regenerate the collision table (allocates the bigger and bigger collision tables)
    // can be fast but it isn't consistent
    // must be consistent with equals
    // 2 < 3 and 3 < 7 => 2 < 7 must have the transitive property

    @Test
    public void testHashCode() {
        long hc1 = cap.hashCode();
        Cap other = new Cap(100, Size.MEDIUM, "Hi");
        Set<Integer> hashes = new TreeSet<Integer>();
        for (int t=0; t<10_000; ++t) {
            other.setThreadCount(45+25*t);
            hashes.add(other.hashCode());
        }
        // by birthday paradox, expect no collisions until about sqrt(2^32) = 2^16 hashes
        // so 3 is a pretty big number (really shouldn't have any collisions)
        assertTrue(hashes.size() >= 10_000-3); // birthday paradox thing (shouldn't be more than 3 collisions)
    }

    // useful for sorting
    // needed for TreeMap and HashMap
    // add.remove.find -> O(logN) steps

    @Test
    public void compareTo() {
        List<Cap> caps = new ArrayList<Cap>();
        caps.add(new Cap(Integer.MIN_VALUE,Size.SMALL,""));
        caps.add(new Cap(Integer.MIN_VALUE, Size.MEDIUM,""));
        caps.add(new Cap(Integer.MIN_VALUE, Size.MEDIUM,"x"));
        caps.add(new Cap(Integer.MIN_VALUE, Size.LARGE,""));
        caps.add(new Cap(0, Size.SMALL,""));
        caps.add(new Cap(Integer.MAX_VALUE, Size.SMALL,""));

       //int i = 0;
        //int j = 3;
        for (int i=0; i<caps.size(); i++) {
            for (int j=0; j<caps.size(); j++) {
                if (i < j) {
                    assertTrue(caps.get(i).compareTo(caps.get(j)) < 0);
                }
                else if (i == j) {
                    assertTrue(caps.get(i).compareTo(caps.get(j)) = 0);
                }
                assertEquals("i="+i + " j="+j, Integer.compare(i,j), caps.get(i).compareTo(caps.get(j)));
            }
        }

    }
}