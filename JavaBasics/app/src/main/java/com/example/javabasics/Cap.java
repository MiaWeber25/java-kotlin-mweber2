package com.example.javabasics;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Cap implements Comparable<Cap> {
    public Cap(int threadCount, Size size, String label) {
        this.threadCount = threadCount;
        this.size = size;
        this.label = label;
    }
    private int threadCount;
    void doUselessThingsWithThreadCount() {
        threadCount++;

        // std::list < int > ints;
        List< Integer > ints = new LinkedList< Integer >();

        // for (std::list<int>::iterator i = ints.begin();  i!=ints.end(); ++i) {
        //     cout << *i;
        // }
        // for (auto &x : ints) { ... }
        // i = begin() - 1;
        Iterator<Integer> i = ints.iterator();
        while (i.hasNext()) { // (i+1) != end())
            // ++i; x = *i;
            Integer x = i.next();
        }

        for (Integer x : ints) {

        }
    }
    public static final Size DEFAULT_SIZE = Size.MEDIUM;

    private Size size = DEFAULT_SIZE;

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        if (size == null) {
            throw new IllegalArgumentException("size cannot be null");
        }
        this.size = size;
    }

    private String label = "";

    public String getLabel() {
        return label;
    }

    public void shout(int loudness) {
        // StringBuffer - thread safe / slower version
        // StringBuilder - not thread safe / faster
        StringBuilder sb = new StringBuilder(label.length() + loudness);
        sb.append(label);
        for (int i = 0; i<loudness; ++i) {
            sb.append("!");
        }
        label = sb.toString();
    }

    public void setLabel(String label) {
        if (label == null) {
            throw new IllegalArgumentException("label cannot be null");
        }
        if (Utils.codepoints(label) > 20) {
            throw new IllegalArgumentException("label is too long");
        }
        this.label = label;
    }

    @Override
    public boolean equals(Object o) { // Two objects (Caps) are equal if each element if equal
        return o instanceof Cap ? compareTo((Cap) o) == 0: false; // This would work in most instances, unless you start comparing Caps to other objects
        //if (this == o) return true;
        //if (!(o instanceof Cap)) return false;
        //Cap cap = (Cap) o; // Java does a runtime type check (will throw an error if this doesn't match up)
        //return threadCount == cap.threadCount && getSize().equals(cap.getSize()) && getLabel().equals(cap.getLabel());
        // remember: .equals() compares the content of Objects! == compares the content of primatives
    }

    @Override
    public int hashCode() { return Objects.hash(threadCount, getSize(), getLabel()); }
    // Objects.hash will construct a hash based on the things that you want to be included
    @Override
    public int compareTo(Cap cap) { // this is a transitive compare
        if (threadCount != cap.threadCount) return Integer.compare(threadCount,cap.threadCount);
        if (getSize() != cap.getSize()) return getSize().compareTo(cap.getSize());
        return getLabel().compareTo(cap.getLabel()); // If I get this far, everything else is equal so this would make the caps equal
    }


    public int getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(int threadCount) {
        this.threadCount = threadCount;
    }
}
