package com.example.classwork;

// Museum class containing properties about a museum.
public class Museum {

    private boolean schoolTripVisit; // Boolean to identify whether a school trip is visiting the Museum

    private double ticketPrice; // Double to identify the price of a ticket to the Museum

    private String exhibitName; // String to identify the name of an exhibit

    public boolean isSchoolTripVisit() {
        return schoolTripVisit;
    }

    public void setSchoolTripVisit(boolean schoolTripVisit) {
        this.schoolTripVisit = schoolTripVisit;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getExhibitName() {
        return exhibitName;
    }

    public void setExhibitName(String exhibitName) {
        this.exhibitName = exhibitName;
    }
}
