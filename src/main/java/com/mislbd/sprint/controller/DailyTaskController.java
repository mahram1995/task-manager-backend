
package com.mislbd.sprint.controller;

import com.mislbd.sprint.dto.ApiDtos.*;
import com.mislbd.sprint.dto.TaskDomain;
import com.mislbd.sprint.service.TaskService;
import com.mislbd.sprint.serviceImpl.TaskServiceImpl;
import jakarta.validation.Valid;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class DailyTaskController {

    private final TaskService service;

    public DailyTaskController(TaskServiceImpl s) {
        this.service = s;
    }
    @GetMapping("get")
    public String all(){
      return "Hello Mahram"  ;
    }

    @GetMapping("get-all")
    public Page<TaskDomain>  all(
            @RequestParam(name = "taskName", required = false) String taskName,
            @RequestParam(name = "client", required = false) String client,
            @RequestParam(name = "priority", required = false) String priority,
            @RequestParam(name = "internalMits", required = false) String internalMits,
            @RequestParam(name = "developer", required = false) String developer,
            @RequestParam(name = "supportMits", required = false) String supportMits,
            @RequestParam(name = "devStatus", required = false) String devStatus,
            @RequestParam(name = "deliveryStatus", required = false) String deliveryStatus,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sort", defaultValue = "createDate,desc") String sort) {

        String[] sortParams = sort.split(",");

        Sort.Direction direction =
                sortParams.length > 1 &&
                        sortParams[1].trim().equalsIgnoreCase("asc")
                        ? Sort.Direction.ASC
                        : Sort.Direction.DESC;

        String sortField = sortParams[0].trim();

        Sort sorting = Sort.by(direction, sortField);

        Pageable pageable = PageRequest.of(page, size, sorting);

        return service.search(
                taskName,
                client,
                internalMits,
                supportMits,
                developer,
                priority,
                devStatus,
                deliveryStatus,
                pageable
        );
    }

    @GetMapping("get/{taskId}")
    public ResponseEntity<TaskDomain> get(
            @PathVariable(name = "taskId") Long taskId) {

        return service.get(taskId);
    }

    @PostMapping("create")
    public ResponseEntity<TaskDomain> create(
            @Valid @RequestBody TaskDomain request,
            Authentication authentication) {

        return service.save(request, authentication.getName());
    }

    @PutMapping("update")
    public ResponseEntity<TaskDomain> update(
            @Valid @RequestBody TaskDomain request,
            Authentication authentication) {

        return service.save(request, authentication.getName());
    }

    @DeleteMapping("delete/{taskId}")
    public void delete(
            @PathVariable(name = "taskId") Long taskId) {

        service.delete(taskId);
    }
}
