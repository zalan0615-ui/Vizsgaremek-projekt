package com.gymforge.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class ContactStatusConverter implements AttributeConverter<ContactStatus, String> {

    @Override
    public String convertToDatabaseColumn(ContactStatus status) {
        return status == null ? null : status.name().toLowerCase();
    }

    @Override
    public ContactStatus convertToEntityAttribute(String value) {
        return value == null ? null : ContactStatus.valueOf(value.toUpperCase());
    }
}