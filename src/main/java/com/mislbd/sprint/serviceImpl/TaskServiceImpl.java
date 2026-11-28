package com.mislbd.sprint.serviceImpl;

import com.mislbd.sprint.dto.TaskDomain;
import com.mislbd.sprint.entity.TaskEntity;
import com.mislbd.sprint.entity.Enums;
import com.mislbd.sprint.mapper.TaskMapper;
import com.mislbd.sprint.repository.DailyTaskRepository;
import com.mislbd.sprint.service.TaskService;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

@Component
public class TaskServiceImpl implements TaskService {
    private final DailyTaskRepository repo;

    public TaskServiceImpl(DailyTaskRepository r) {
        repo = r;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TaskDomain> search(String taskName, String client, String internalMits,
                                             String supportMits, String developer,
                                             String priority, String dev, String delivery, Pageable pageable) {
        Specification<TaskEntity> s = (root, query, cb) -> cb.conjunction();
        if (taskName != null && !taskName.isBlank())
            s = s.and((r, x, c) -> c.like(c.lower(r.get("taskName")), "%" + taskName.toLowerCase() + "%"));
        if (client != null && !client.isBlank()) s = s.and((r, x, c) -> c.equal(r.get("client"), client));
        if (internalMits != null && !internalMits.isBlank())
            s = s.and((r, x, c) -> c.equal(r.get("internalMits"), internalMits));
        if (supportMits != null && !supportMits.isBlank())
            s = s.and((r, x, c) -> c.equal(r.get("supportMits"), supportMits));
        if (developer != null && !developer.isBlank()) s = s.and((r, x, c) -> c.equal(r.get("developer"), developer));
        if (priority != null && !priority.isBlank())
            s = s.and((r, x, c) -> c.equal(r.get("priority"), Enums.Priority.valueOf(priority)));
        if (dev != null && !dev.isBlank())
            s = s.and((r, x, c) -> c.equal(r.get("devStatus"), Enums.DevelopmentStatus.valueOf(dev)));
        if (delivery != null && !delivery.isBlank())
            s = s.and((r, x, c) -> c.equal(r.get("deliveryStatus"), Enums.DeliveryStatus.valueOf(delivery)));

        Page<TaskEntity> entityPage = repo.findAll(s, pageable);

        return entityPage.map(TaskMapper::entityToDomain);
    }

    @Transactional(readOnly = true)
    @Override
    public ResponseEntity<TaskDomain> get(Long id) {
        TaskEntity entity=repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Task not found: " + id));

        return  ResponseEntity.ok(TaskMapper.entityToDomain(entity));
    }

    @Transactional
    @Override
    public ResponseEntity<TaskDomain> save(TaskDomain domain, String user) {
        Long id=domain.getId();
        TaskEntity entity = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        // Check whether another user has updated this task
        if (!entity.getVersion().equals(domain.getVersion())) {
            throw new OptimisticLockingFailureException(
                    "This task has already been modified by another user. Please refresh and try again."
            );
        }

        entity = TaskMapper.domainToEntity(domain);
        entity.setCreateBy(user);
        if (id == null) {
            entity.setCreateDate(LocalDate.now());
            entity.setCreateBy(user);
        }else{
            entity.setUpdateBy(user);
            entity.setUpdateDate(LocalDate.now());
        }
        repo.save(entity);
        return ResponseEntity.ok(domain);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repo.delete(repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Task not found: " + id)));
    }
}
