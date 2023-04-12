package com.example.mymuseum;

import static org.junit.Assert.*;

import com.fasterxml.jackson.databind.ObjectMapper;

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
        assertEquals(2.5, m.getPrice(), 0.001); // Don't forget epsilon
    }

    @Test
    public void setTicketPrice() {
        m.setPrice(33.0);
        //assertEquals(33.0, 0.001, m.getTicketPrice()); // Don't forget epsilon!
        assertEquals(33.0, m.getPrice(), 0.001); // Don't forget epsilon!

    }

    @Test
    public void getExhibitName() {
        assertEquals("",m.getName());
    }

    @Test
    public void setExhibitName() {
        m.setName("Ancient Egypt");
        assertEquals("Ancient Egypt", m.getName());
    }

    // We expect this test to fail -> "exhibitName cannot be null"
    @Test(expected = IllegalArgumentException.class)
    public void setExhibitNameFail() {
        m.setName(null);
    }

    @Test
    public void fromJSON() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String json = "{\"school visit\":\"FALSE\",\"ticket price\":8.0,\"exhibit name\":\"ancient egypt\"}";
        m = mapper.readValue(json,Museum.class);

        assertEquals(schoolVisit.FALSE, m.getVisit());
        assertEquals("ancient egypt", m.getName());
    }
    @Test
    public void toJSON() throws Exception {
        m = new Museum(schoolVisit.FALSE, 8.00, "ancient egypt");
        ObjectMapper mapper = new ObjectMapper();
        String json  = mapper.writeValueAsString(m);
        String expect = "{\"schoolVisit\":\"FALSE\",\"ticket price\":8.0,\"exhibit name\":\"ancient egypt\"}";

        assertEquals(expect, json);

    }
}
