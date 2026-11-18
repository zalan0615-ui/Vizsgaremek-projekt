package com.gymforge.service;

import com.gymforge.dto.request.CreateClassBookingsRequest;
import com.gymforge.dto.request.UpdateClassBookingsRequest;
import com.gymforge.dto.response.ClassBookingsResponse;
import com.gymforge.exception.ResourceNotFoundException;
import com.gymforge.model.ClassBookings;
import com.gymforge.repository.ClassBookingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

//----------------------------------------------------------------------------------------------------//
//----------------------------------------------------------------------------------------------------//

@Service
@RequiredArgsConstructor
public class ClassBookingsService {

    private final ClassBookingsRepository classBookingsRepository;

    //----------------------------------------------------------------------------
    // Létrehozás (createclass_bookings eljárás)

    @Transactional
    public ClassBookingsResponse createClassBooking(CreateClassBookingsRequest request) {

        classBookingsRepository.createClassBooking(
                request.getUserId(),
                request.getClassScheduleId(),
                request.getBookingDate()
        );

        // Az eljárás nem ad vissza id-t, ezért a legnagyobb id-jú sort keressük vissza
        ClassBookings saved = classBookingsRepository.getAllClassBookings().stream()
                .max(Comparator.comparing(ClassBookings::getId))
                .orElseThrow(() -> new ResourceNotFoundException("A foglalás létrehozása nem sikerült"));

        return toResponse(saved);
    }

    //----------------------------------------------------------------------------

    public List<ClassBookingsResponse> getAllClassBookings() {
        return classBookingsRepository.getAllClassBookings()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ClassBookingsResponse getClassBookingById(Integer id) {
        return toResponse(findClassBooking(id));
    }

    //----------------------------------------------------------------------------
    // Módosítás (updateclass_bookings eljárás)

    @Transactional
    public ClassBookingsResponse updateClassBooking(Integer id, UpdateClassBookingsRequest request) {
        findClassBooking(id);

        classBookingsRepository.updateClassBooking(
                id,
                request.getUserId(),
                request.getClassScheduleId(),
                request.getBookingDate()
        );

        return toResponse(findClassBooking(id));
    }

    //----------------------------------------------------------------------------
    // Segédmetódusok

    private ClassBookings findClassBooking(Integer id) {
        return classBookingsRepository.getClassBookingById(id)
                .orElseThrow(() -> new ResourceNotFoundException("A foglalás nem található"));
    }

    private ClassBookingsResponse toResponse(ClassBookings cb) {
        return ClassBookingsResponse.builder()
                .id(cb.getId())
                .userId(cb.getUserId())
                .classScheduleId(cb.getClassScheduleId())
                .bookingDate(cb.getBookingDate())
                .createdAt(cb.getCreatedAt())
                .build();
    }
}

//----------------------------------------------------------------------------------------------------//