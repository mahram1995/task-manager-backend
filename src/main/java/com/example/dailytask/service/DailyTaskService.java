package com.example.dailytask.service;

import com.example.dailytask.dto.ApiDtos.*;
import com.example.dailytask.dto.TaskDomain;
import com.example.dailytask.entity.*;
import com.example.dailytask.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

@Service
public class DailyTaskService {
    private final DailyTaskRepository repo;

    public DailyTaskService(DailyTaskRepository r) {
        repo = r;
    }

    private TaskResponse dto(DailyTask t) {
        return new TaskResponse(t.getId(), t.getTaskName(), t.getDescription(), t.getClient(), t.getCreateDate(), t.getDevStatus(), t.getCreateBy(), t.getExpectedDeliveryDate(), t.getDeliveryDate(), t.getExpectedStartDate(), t.getActualStartDate(), t.getExpectedEndDate(), t.getActualEndDate(), t.getPriority(), t.getDeliveryStatus());
    }

    @Transactional(readOnly = true)
    public Page<TaskResponse> search(String q, String client, String priority, String dev, String delivery, Pageable p) {
        Specification<DailyTask> s = (root, query, cb) -> cb.conjunction();
        if (q != null && !q.isBlank())
            s = s.and((r, x, c) -> c.like(c.lower(r.get("taskName")), "%" + q.toLowerCase() + "%"));
        if (client != null && !client.isBlank()) s = s.and((r, x, c) -> c.equal(r.get("client"), client));
        if (priority != null && !priority.isBlank())
            s = s.and((r, x, c) -> c.equal(r.get("priority"), TaskEnums.Priority.valueOf(priority)));
        if (dev != null && !dev.isBlank())
            s = s.and((r, x, c) -> c.equal(r.get("devStatus"), TaskEnums.DevelopmentStatus.valueOf(dev)));
        if (delivery != null && !delivery.isBlank())
            s = s.and((r, x, c) -> c.equal(r.get("deliveryStatus"), TaskEnums.DeliveryStatus.valueOf(delivery)));
        return repo.findAll(s, p).map(this::dto);
    }

    @Transactional(readOnly = true)
    public TaskResponse get(Long id) {
        return dto(repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Task not found: " + id)));
    }

    @Transactional
    public TaskResponse save(TaskDomain r, String user, Long id) {
        DailyTask t = id == null ? new DailyTask() : repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Task not found: " + id));
        t.setTaskName(r.getTaskName());
        t.setDescription(r.getDescription());
        t.setClient(r.getClient());
        t.setDevStatus(r.getDevStatus() == null ? TaskEnums.DevelopmentStatus.PENDING : r.getDevStatus());
        t.setExpectedDeliveryDate(r.getExpectedDeliveryDate());
        t.setDeliveryDate(r.getDeliveryDate());
        t.setExpectedStartDate(r.getExpectedStartDate());
        t.setActualStartDate(r.getActualStartDate());
        t.setExpectedEndDate(r.getExpectedEndDate());
        t.setActualEndDate(r.getActualEndDate());
        t.setPriority(r.getPriority() == null ? TaskEnums.Priority.NORMAL : r.getPriority());
        t.setDeliveryStatus(r.getDevStatus() == null ? TaskEnums.DeliveryStatus.PENDING : r.getDeliveryStatus());
        if (id == null) {
            t.setCreateDate(LocalDate.now());
            t.setCreateBy(user);
        }
        return dto(repo.save(t));
    }

    @Transactional
    public void delete(Long id) {
        repo.delete(repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Task not found: " + id)));
    }
}
