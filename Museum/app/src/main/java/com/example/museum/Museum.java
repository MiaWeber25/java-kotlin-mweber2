// Using Json in classes
// Mia Weber
// Feb 9, 2023

package com.example.museum;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.IOException;
@JsonDeserialize(using = Museum.Deserializer.class)

public class Museum {
    public static class Deserializer extends JsonDeserializer<Museum> {
        @Override
        public Museum deserialize(JsonParser jp, DeserializationContext context) throws IOException {
            JsonNode node = jp.getCodec().readTree(jp);
            return new Museum(node);
        }
    }

    // Enum to determine if there is a school visiting the Museum
    public static final schoolVisit DEFAULT_VISIT = schoolVisit.FALSE;
    private static schoolVisit visit = DEFAULT_VISIT;

    // Double to store the price of a ticket in dollars
    public static final double DEFAULT_PRICE = (double) 2.5;
    private double ticketPrice = DEFAULT_PRICE;

    // String to store the name of the special exhibit
    public static final String DEFAULT_NAME = "";
    private String exhibitName = DEFAULT_NAME;

    // Getters and Setters:

    public static schoolVisit getVisit() {
        return visit;
    }

    public static void setVisit(schoolVisit visit) {
        if (visit == null) {
            throw new IllegalArgumentException("school visit cannot be null");
        }
        Museum.visit = visit;
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
        if (exhibitName == null) {
            throw new IllegalArgumentException("exhibit name cannot be null");
        }
        this.exhibitName = exhibitName;
    }

    // Constructors:
    public Museum(JsonNode node) {
        this.visit = schoolVisit.valueOf(node.get("schoolVisit").asText());
        this.ticketPrice = (double) (node.get("ticketPrice")).numberValue(); // ????
        this.exhibitName = node.get("exhibitName").asText();
    }
    public Museum(schoolVisit visit, double ticketPrice, String exhibitName) {
        this.visit = visit;
        this.ticketPrice = ticketPrice;
        this.exhibitName = exhibitName;
    }
    public Museum() {
        this(DEFAULT_VISIT, DEFAULT_PRICE, DEFAULT_NAME);
    }
}
