// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.model.inheritance.enumdiscriminator.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.json.JsonReader;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * Golden dog model.
 */
@Immutable
public final class Golden extends Dog {
    /**
     * Creates an instance of Golden class.
     * 
     * @param weight the weight value to set.
     */
    @Generated
    public Golden(int weight) {
        super(weight);
        this.kind = DogKind.GOLDEN;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        toJsonShared(jsonWriter);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of Golden from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of Golden if the JsonReader was pointing to an instance of it, or null if it was pointing to
     * JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the Golden.
     */
    @Generated
    public static Golden fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            int weight = 0;
            DogKind kind = DogKind.GOLDEN;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("weight".equals(fieldName)) {
                    weight = reader.getInt();
                } else if ("kind".equals(fieldName)) {
                    kind = DogKind.fromString(reader.getString());
                } else {
                    reader.skipChildren();
                }
            }
            Golden deserializedGolden = new Golden(weight);
            deserializedGolden.kind = kind;

            return deserializedGolden;
        });
    }
}