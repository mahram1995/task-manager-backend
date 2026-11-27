
package com.example.dailytask.controller;

import com.example.dailytask.dto.ApiDtos.*;
import com.example.dailytask.dto.TaskDomain;
import com.example.dailytask.service.DailyTaskService;
import jakarta.validation.Valid;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class DailyTaskController {

    private final DailyTaskService service;

    public DailyTaskController(DailyTaskService s) {
        this.service = s;
    }


    @GetMapping("get-all")
    public Page<TaskResponse> all(
            @RequestParam(name = "q", required = false) String q,
            @RequestParam(name = "client", required = false) String client,
            @RequestParam(name = "priority", required = false) String priority,
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
                q,
                client,
                priority,
                devStatus,
                deliveryStatus,
                pageable
        );
    }

    @GetMapping("get/{taskIdd}")
    public TaskResponse get(
            @PathVariable(name = "taskIdd") Long taskIdd) {

        return service.get(taskIdd);
    }

    @PostMapping("create")
    public TaskResponse create(
            @Valid @RequestBody TaskDomain request,
            Authentication authentication) {

        return service.save(request, authentication.getName(), null);
    }

    @PutMapping("update")
    public TaskResponse update(
            @Valid @RequestBody TaskDomain request,
            Authentication authentication) {

        return service.save(request, authentication.getName(), request.getId());
    }

    @DeleteMapping("delete/{taskId}")
    public void delete(
            @PathVariable(name = "taskId") Long taskId) {

        service.delete(taskId);
    }
}
