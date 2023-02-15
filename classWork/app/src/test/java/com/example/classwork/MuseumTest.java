package com.example.classwork;

import static org.junit.Assert.*;

import org.junit.Test;

public class MuseumTest {

    Museum m;
    @Test
    public void getSchoolVisit() {
        assertEquals(m.DEFAULT_VISIT, m.getSchoolVisit());
    }

    @Test
    public void setSchoolVisit() {
        m.setSchoolTripVisit(schoolVisit.FALSE);
        assertEquals(schoolVisit.FALSE, m.getSchoolVisit());

    }
    @Test
    public void isSchoolVisit() {
        Museum M = new Museum();
        assertTrue(M.isSchoolVisit()); // It is true that there is a school trip visit at the Museum
    }

    @Test
    public void setSchoolVisit() {
        Museum M = new Museum();
        M.setSchoolVisit(true);
        assertTrue(M.isSchoolVisit());
    }

    @Test
    public void getTicketPrice() {
        Museum M = new Museum();
        double epsilon = 0.01;
        M.setTicketPrice((double)2.5);
        // Actual, Expected, Delta -> assertEquals with doubles/floats
        assertEquals(2.5,M.getTicketPrice(), (double) 0.01);
    }

    @Test
    public void setTicketPrice() {
    }

    @Test
    public void getExhibitName() {
        Museum M = new Museum();
        M.setExhibitName("Ancient Egypt");
        assertEquals("Ancient Egypt", M.getExhibitName());
    }

    @Test
    public void setExhibitName() {
    }

    @Test
    public void fromJSON() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String json = "{\n" +
                "    \"exhibitName\": \"Ancient Egypt\",\n" +
                "    \"ticketPrice\": 2.5\n" +
                "    \"schoolTripVisit\": \"true\"\n" +
                "}";
    }

    @Test
    public void fromJSON() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String json = "{\n" +
                "    \"size\": \"LARGE\",\n" +
                "    \"label\": \"My Label\"\n" +
                "}";
        cap = mapper.readValue(json, Cap.class);

        assertEquals(Size.LARGE, cap.getSize());
        assertEquals("My Label", cap.getLabel());

    }
}