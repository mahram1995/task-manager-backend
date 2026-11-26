
package com.example.dailytask.controller;

import com.example.dailytask.dto.ApiDtos.*;
import com.example.dailytask.service.DailyTaskService;
import jakarta.validation.Valid;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class DailyTaskController {

    private final DailyTaskService s;

    public DailyTaskController(DailyTaskService s) {
        this.s = s;
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Task Controller Working");
    }

    @GetMapping
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

        return s.search(
                q,
                client,
                priority,
                devStatus,
                deliveryStatus,
                pageable
        );
    }

    @GetMapping("/{id}")
    public TaskResponse get(
            @PathVariable(name = "id") Long id) {

        return s.get(id);
    }

    @PostMapping
    public TaskResponse create(
            @Valid @RequestBody TaskRequest request,
            Authentication authentication) {

        return s.save(request, authentication.getName(), null);
    }

    @PutMapping("/{id}")
    public TaskResponse update(
            @PathVariable(name = "id") Long id,
            @Valid @RequestBody TaskRequest request,
            Authentication authentication) {

        return s.save(request, authentication.getName(), id);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable(name = "id") Long id) {

        s.delete(id);
    }
}
