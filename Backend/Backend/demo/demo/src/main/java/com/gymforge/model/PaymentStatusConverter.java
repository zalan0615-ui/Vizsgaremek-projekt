package com.gymforge.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

//----------------------------------------------------------------------------------------------//
//UTÁNA NÉZNI!!!!!!!!!!!!!!!!

@Converter
public class PaymentStatusConverter implements AttributeConverter<PaymentStatus, String> {


    @Override
    public String convertToDatabaseColumn(PaymentStatus status) {
        return status == null ? null : status.name().toLowerCase();
    }

    @Override
    public PaymentStatus convertToEntityAttribute(String value) {
        return value == null ? null : PaymentStatus.valueOf(value.toUpperCase());
    }
}

//----------------------------------------------------------------------------------------------//