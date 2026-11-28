package com.mislbd.sprint.mapper;


import com.mislbd.sprint.dto.TaskDomain;
import com.mislbd.sprint.entity.TaskEntity;
import com.mislbd.sprint.entity.Enums;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TaskMapper {

    private TaskMapper() {
        // Utility class
    }

    /**
     * Entity to Domain
     */
    public static TaskDomain entityToDomain(TaskEntity entity) {

        if (entity == null) {
            return null;
        }

        TaskDomain domain = new TaskDomain();

        domain.setId(entity.getId());
        domain.setTaskName(entity.getTaskName());
        domain.setDescription(entity.getDescription());
        domain.setClient(entity.getClient());

        domain.setDevStatus(entity.getDevStatus());
        domain.setQaStatus(entity.getQaStatus());

        domain.setSupportMits(entity.getSupportMits());
        domain.setInterMits(entity.getInterMits());

        domain.setCreateBy(entity.getCreateBy());
        domain.setUpdateBy(entity.getUpdateBy());

        domain.setCreatedDate(entity.getCreateDate());
        domain.setUpdateDate(entity.getUpdateDate());

        domain.setExpectedDeliveryDate(entity.getExpectedDeliveryDate());
        domain.setDeliveryDate(entity.getDeliveryDate());

        domain.setExpectedStartDate(entity.getExpectedStartDate());
        domain.setActualStartDate(entity.getActualStartDate());

        domain.setExpectedEndDate(entity.getExpectedEndDate());
        domain.setActualEndDate(entity.getActualEndDate());

        domain.setPriority(entity.getPriority());
        domain.setDeliveryStatus(entity.getDeliveryStatus());
        domain.setVersion(entity.getVersion());

        return domain;
    }

    /**
     * Domain to Entity
     */
    public static TaskEntity domainToEntity(TaskDomain domain) {

        if (domain == null) {
            return null;
        }

        TaskEntity entity = new TaskEntity();

        entity.setId(domain.getId());
        entity.setTaskName(domain.getTaskName());
        entity.setDescription(domain.getDescription());
        entity.setClient(domain.getClient());

        entity.setDevStatus(domain.getDevStatus() == null ? Enums.DevelopmentStatus.PENDING : domain.getDevStatus());
        entity.setQaStatus(domain.getQaStatus() == null ? Enums.QAStatus.PENDING : domain.getQaStatus());

        entity.setSupportMits(domain.getSupportMits());
        entity.setInterMits(domain.getInterMits());

        entity.setExpectedDeliveryDate(domain.getExpectedDeliveryDate());
        entity.setDeliveryDate(domain.getDeliveryDate());

        entity.setExpectedStartDate(domain.getExpectedStartDate());
        entity.setActualStartDate(domain.getActualStartDate());

        entity.setExpectedEndDate(domain.getExpectedEndDate());
        entity.setActualEndDate(domain.getActualEndDate());

        entity.setPriority(domain.getPriority() == null ? Enums.Priority.NORMAL : domain.getPriority());
        entity.setDeliveryStatus(domain.getDevStatus() == null ? Enums.DeliveryStatus.PENDING :domain.getDeliveryStatus());
        entity.setVersion(domain.getVersion());

        return entity;
    }

    // List of Entity to List of Domain
    public static List<TaskDomain> listEntityToDomain(List<TaskEntity> entities) {

        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }

        return entities.stream()
                .map(TaskMapper::entityToDomain)
                .collect(Collectors.toList());
    }
}