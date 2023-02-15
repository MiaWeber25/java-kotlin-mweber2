package com.example.classwork;

// Museum class containing properties about a museum.
public class Museum {
    public static class Deserializer extends JsonDeserialerizer<Museum> {

        @Override
        public Museum deserialize(JsonParser jp, DesereializationContext context) throws IOException {
            JsonNode node = jp.getCodec().readTree(jp);
            return new Museum(node);
        }

    }
    //private boolean schoolTripVisit; // Boolean to identify whether a school trip is visiting the Museum
    public static final schoolVisit DEFAULT_VISIT = schoolVisit.FALSE; // final is CONST in Java

    private static schoolVisit visit = DEFAULT_VISIT;

    public schoolVisit getSchoolVisit() {
        return visit;
    }

    public void setSchoolVisit(schoolVisit visit) {
        if (visit == null) {
            throw new IllegalArgumentException("school trip visit cannot be null");
        }
        this.visit = visit;
    }

    private double ticketPrice; // Double to identify the price of a ticket to the Museum

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }


    public static final String DEFAULT_EXHIBITNAME = "";
    private String exhibitName; // String to identify the name of an exhibit

    public String getExhibitName() {
        return exhibitName;
    }

    public void setExhibitName(String exhibitName) {
        if(exhibitName == null) {
            throw new IllegalArgumentException("exhibit name cannot be null");
        }
        this.exhibitName = exhibitName;
    }

    // Constructors:
    public Museum(schoolVisit schoolVisit, double ticketPrice, String exhibitName) {
        this.schoolVisit = schoolVisit;
        this.ticketPrice = ticketPrice;
        this.exhibitName = exhibitName;
    }
}
