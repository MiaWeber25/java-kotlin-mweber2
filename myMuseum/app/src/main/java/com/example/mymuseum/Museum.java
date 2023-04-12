package com.example.mymuseum;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

@JsonDeserialize(using = Museum.Deserializer.class)
@JsonSerialize(using = Museum.Serializer.class)
public class Museum {
    public static class Deserializer extends JsonDeserializer<Museum> {
        @Override
        public Museum deserialize(JsonParser jp, DeserializationContext context) throws IOException {
            JsonNode node = jp.getCodec().readTree(jp);
            return new Museum(node);
        }
    }

    public static class Serializer extends StdSerializer<Museum> {
        public Serializer() { this(null); }
        public Serializer(Class<Museum> t) { super(t); }

        @Override
        public void serialize(Museum museum, JsonGenerator jsonGenerator, SerializerProvider serializer) throws IOException {
            museum.toJson(jsonGenerator);
        }
    }

    // Enum to determine if there is a school visiting the Museum
    public static final schoolVisit DEFAULT_VISIT = schoolVisit.FALSE;
    private static schoolVisit visit = DEFAULT_VISIT;

    public schoolVisit getVisit() {
        return visit;
    }
    public void setVisit(schoolVisit visit) {
        if (visit == null) {
            throw new IllegalArgumentException("visit cannot be null");
        }
        this.visit = visit;
    }

    // Double to store the price of a ticket in dollars
    public static final double DEFAULT_PRICE = (double) 2.5;
    private double ticketPrice = DEFAULT_PRICE;
    public double getPrice() {
        return ticketPrice;
    }
    public void setPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    // String to store the name of the special exhibit
    public static final String DEFAULT_NAME = "";
    private String exhibitName = DEFAULT_NAME;

    public String getName() {
        return exhibitName;
    }
    public void setName(String exhibitName) {
        if (exhibitName == null) {
            throw new IllegalArgumentException("exhibit name cannot be null");
        }
        this.exhibitName = exhibitName;
    }

    // Constructors:
    public Museum(JsonNode node) {
        this.visit = schoolVisit.valueOf(node.get("school visit").asText());
        this.ticketPrice = (double) (node.get("ticket price")).numberValue(); // ????
        this.exhibitName = node.get("exhibit name").asText();
    }

    public void toJson(JsonGenerator out) throws IOException {
        out.writeStartObject();
        out.writeStringField("schoolVisit", getVisit().toString());
        out.writeNumberField("ticket price", getPrice());
        out.writeStringField("exhibit name", getName());
        out.writeEndObject();
    }
    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
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
