package com.example.museum;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
public class MuseumTest {

    Museum m;

    @Before
    public void setUp() throws Exception {
        m = new Museum();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getVisit() {
        assertEquals(Museum.DEFAULT_VISIT, m.getVisit());
    }

    @Test
    public void setVisit() {
        m.setVisit(schoolVisit.TRUE);
        assertEquals(schoolVisit.TRUE, m.getVisit());
    }

    @Test
    public void getTicketPrice() {
        assertEquals(2.5, 0.001, m.getTicketPrice()); // Don't forget epsilon!
    }

    @Test
    public void setTicketPrice() {
        m.setTicketPrice(33.0);
        assertEquals(33.0, 0.001, m.getTicketPrice()); // Don't forget epsilon!
    }

    @Test
    public void getExhibitName() {
        assertEquals("",m.getExhibitName());
    }

    @Test
    public void setExhibitName() {
        m.setExhibitName("Ancient Egypt");
        assertEquals("Ancient Egypt", m.getExhibitName());
    }

    // We expect this test to fail -> "exhibitName cannot be null"
    @Test(expected = IllegalArgumentException.class)
    public void setExhibitNameFail() {
        m.setExhibitName(null);
    }
}