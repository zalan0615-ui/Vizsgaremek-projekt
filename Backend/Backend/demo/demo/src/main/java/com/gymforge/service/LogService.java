package com.gymforge.service;

import com.gymforge.dto.response.LogResponse;
import com.gymforge.exception.ResourceNotFoundException;
import com.gymforge.model.Log;
import com.gymforge.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LogService {

    private final LogRepository logRepository;

    //---------------------------------------------------------------------------
    // Létrehozás (createlogs eljárás)

    @Transactional
    public void createLog(Integer userId, Integer adminId, String action,
                          String entityTable, Integer entityId) {
        logRepository.createLog(userId, adminId, action, entityTable, entityId);
    }

    //---------------------------------------------------------------------------
    // Lekérdezés (getlogs, getlogsById eljárás)

    public List<LogResponse> getAllLogs() {
        return logRepository.getAllLogs()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public LogResponse getLogById(Integer id) {
        return toResponse(findLog(id));
    }

    //---------------------------------------------------------------------------
    // Segédmetódusok

    private Log findLog(Integer id) {
        return logRepository.getLogById(id)
                .orElseThrow(() -> new ResourceNotFoundException("A naplóbejegyzés nem található"));
    }

    private LogResponse toResponse(Log log) {
        return LogResponse.builder()
                .id(log.getId())
                .userId(log.getUserId())
                .adminId(log.getAdminId())
                .action(log.getAction())
                .entityTable(log.getEntityTable())
                .entityId(log.getEntityId())
                .createdAt(log.getCreatedAt())
                .build();
    }
}

//----------------------------------------------------------------------------------------------------//