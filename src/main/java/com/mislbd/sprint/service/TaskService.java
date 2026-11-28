package com.mislbd.sprint.service;

import com.mislbd.sprint.dto.ApiDtos;
import com.mislbd.sprint.dto.TaskDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface TaskService {
    public Page<TaskDomain>  search(String taskName, String client, String internalMits, String supportMits, String developer, String priority, String dev, String delivery, Pageable p);
    public ResponseEntity<TaskDomain> get(Long id);
    public ResponseEntity<TaskDomain> save(TaskDomain domain, String user);
    public void delete(Long id);
}
