package com.example.classwork;

import static org.junit.Assert.*;

import org.junit.Test;

public class MuseumTest {

    @Test
    public void isSchoolTripVisit() {
        Museum M = new Museum();
        assertTrue(M.isSchoolTripVisit()); // It is true that there is a school trip visit at the Museum
    }

    @Test
    public void setSchoolTripVisit() {
        Museum M = new Museum();
        M.setSchoolTripVisit(true);
        assertTrue(M.isSchoolTripVisit());
    }

    @Test
    public void getTicketPrice() {
        Museum M = new Museum();
        double epsilon = 0.01;
        assertEquals(2.5,epsilon, M.getTicketPrice());
    }

    @Test
    public void setTicketPrice() {
        Museum M = new Museum();
        double epsilon = 0.01;
        M.setTicketPrice((double) 2.5);
        assertEquals(2.5,epsilon, M.getTicketPrice());
    }

    @Test
    public void getExhibitName() {
    }

    @Test
    public void setExhibitName() {
    }
}