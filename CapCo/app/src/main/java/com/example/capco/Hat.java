package com.example.capco;

public class Hat implements AutoCloseable{ // Promise that you are implementing a close method to clean up
    private boolean isOld;

    private byte age; // byte is signed -128..127 - 1 byte
    private short threadCount; // -32768..32767 (16 bits) - 2 bytes
    private int priceInCents; // -2 billion..2 billion - 4 bytes
    private long taxId; // -2^63..(2^63)-1 - 8 bytes

    private float size; // base 2 representation (about 7 decimal digits of mantessa) * 10^(p/m 30)
    private double distToCenterOfUniverseInAngstroms; // like float but 15 digits pm 300

    private char x; // 0..65535 -> only unsigned data type (16 bits long = UTF16). Have to think of codepoints (real length of a string is the number of codepoints

    private String name; // Object composed of the primitive types above

    // Java will always initialize to a "zero-ish thing" as opposed to c++ which won't initialize to anything

    // Java DOES allow constructors (and multiple constructors)
    // Java DOES NOT have default values

    Hat(float _size, String name) {
        size = _size; // Could write this to avoid potential namespace collision (use different names _size and _name)
        this.name = name;
    }

    // "Default" Constructor
    Hat(float size) { // Need both constructors -> this constructor calls the one above
        this(size, "hat"); // Didn't type name- that's the IDE. The code is just -> this(size, "hat);
    }
    Hat() {
        this(10.0f, "hat");
    }

    // Java does NOT have destructors (Garbage Collectors)
    // Java DOES have close methods (have to be public)
    public void close() {
        System.out.println("Closing Hat" + this + " named " + name); // sout *tab*
    }

    // Auto generated getters and setters:

    public boolean isOld() {
        return isOld;
    }

    public void setOld(boolean old) {
        isOld = old;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public short getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(short threadCount) {
        if (threadCount > 0) {
            this.threadCount = threadCount;
        } else {
            throw new IllegalArgumentException("thread count must be positive");
        }
    }

    public int getPriceInCents() {
        return priceInCents;
    }

    public void setPriceInCents(int priceInCents) {
        this.priceInCents = priceInCents;
    }

    public long getTaxId() {
        return taxId;
    }

    public void setTaxId(long taxId) {
        this.taxId = taxId;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public double getDistToCenterOfUniverseInAngstroms() {
        return distToCenterOfUniverseInAngstroms;
    }

    public void setDistToCenterOfUniverseInAngstroms(double distToCenterOfUniverseInAngstroms) {
        this.distToCenterOfUniverseInAngstroms = distToCenterOfUniverseInAngstroms;
    }

    public char getX() {
        return x;
    }

    public void setX(char x) {
        this.x = x;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
