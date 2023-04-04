package com.example.capco;
// https://stackoverflow.com/questions/51615047/jackson-jsondeserializer-stackoverflow
//
import android.content.ClipData;

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
@JsonDeserialize(using = Cap.Deserializer.class)
@JsonSerialize(using = Cap.Serializer.class)
// Jackson serializer: https://www.baeldung.com/jackson-object-mapper-tutorial
public class Cap {
    public static class Deserializer extends JsonDeserializer<Cap> {
// {"size":"MEDIUM", "label":"Ok"}

        @Override
        public Cap deserialize(JsonParser jp, DeserializationContext context) throws IOException {
            JsonNode node = jp.getCodec().readTree(jp);
            return new Cap(node);
        }
    }
    public static class Serializer extends StdSerializer<Cap> {
        public Serializer() {
            this(null);
        }

        public Serializer(Class<Cap> t) {
            super(t);
        }
        @Override
        public void serialize(Cap cap, JsonGenerator jsonGenerator, SerializerProvider serializer) throws IOException {
            cap.toJson(jsonGenerator);
        }
    }

    // private Size size = Size.MEDIUM; // If you don't initialize an object to something it is a null pointer. Try to do something with the object = null pointer exception
    public static final Size DEFAULT_SIZE = Size.MEDIUM; // final is CONST in Java

    private static Size size = DEFAULT_SIZE;


    //Getters and Setters
    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        if (size == null) {
            throw new IllegalArgumentException("size cannot be null");
        }
        this.size = size;
    }

    public static final String DEFAULT_LABEL = "";
    private String label = "";

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        if (label == null) {
            throw new IllegalArgumentException("label cannot be null");
        }
        if (Utils.codepoints(label) > 20) { // Set a max character length for the label of 20
            throw new IllegalArgumentException("label is to long");
        }
        this.label = label;
    }

    // constructor when you have a JsonNode
    public Cap(JsonNode node) {
        this.size = Size.valueOf(node.get("size").asText());
        this.label = node.get("label").asText();
    }

    public void toJson(JsonGenerator out) throws IOException {
        out.writeStartObject();
        out.writeStringField("label",getLabel());
        out.writeStringField("size",getSize().toString());
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
    // constructor when I have the arguments
    public Cap(String label, Size size) {
        this.label = label;
        this.size = size;
    }

    // constructor when I have nothing
    public Cap() {
        this(DEFAULT_LABEL, DEFAULT_SIZE);
    }

    // toJson
}
