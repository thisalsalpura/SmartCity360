package com.thisal.smartcity360.record;

import com.thisal.smartcity360.enums.CityEventType;
import com.thisal.smartcity360.enums.EventField;

import java.time.LocalTime;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public record CityEvent(
        CityEventType type,
        String source,
        Map<EventField, String> data
) {

    public CityEvent {
        Objects.requireNonNull(type, "Event Type must not be Null!");
        if (source == null || source.isBlank()) {
            throw new IllegalArgumentException("Event Source must not be Null or Blank!");
        }
        EnumMap<EventField, String> copy = new EnumMap<>(EventField.class);
        if (data != null) {
            copy.putAll(data);
        }
        data = Collections.unmodifiableMap(copy);
    }

    public static CityEvent of(CityEventType type, String source) {
        return new CityEvent(type, source, Map.of());
    }

    public CityEvent with(EventField field, Object value) {
        EnumMap<EventField, String> copy = new EnumMap<>(EventField.class);
        copy.putAll(data);
        copy.put(Objects.requireNonNull(field, "Field must not be Null!"), String.valueOf(value));
        return new CityEvent(type, source, copy);
    }

    public String text(EventField field) {
        String value = data.get(field);
        if (value == null) {
            throw new IllegalStateException(type + " Event carries no " + field + "!");
        }
        return value;
    }

    public double number(EventField field) {
        return Double.parseDouble(text(field));
    }

    public int integer(EventField field) {
        return Integer.parseInt(text(field));
    }

    public LocalTime time(EventField field) {
        return LocalTime.parse(text(field));
    }

}