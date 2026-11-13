package com.gymforge.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

//----------------------------------------------------------------------------------------------//

@Converter
public class PaymentStatusConverter implements AttributeConverter<PaymentStatus, String> {

    // Java enum (UNPAID) -> adatbázis érték (unpaid)
    @Override
    public String convertToDatabaseColumn(PaymentStatus status) {
        return status == null ? null : status.name().toLowerCase();
    }

    // Adatbázis érték (unpaid) -> Java enum (UNPAID)
    @Override
    public PaymentStatus convertToEntityAttribute(String value) {
        return value == null ? null : PaymentStatus.valueOf(value.toUpperCase());
    }
}

//----------------------------------------------------------------------------------------------//