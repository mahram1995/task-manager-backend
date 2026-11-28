
package com.mislbd.sprint.controller;

import com.mislbd.sprint.service.TaskNoteService;
import com.mislbd.sprint.serviceImpl.TaskNoteServiceImpl;
import com.mislbd.sprint.dto.ApiDtos;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks/{taskId}/notes")
public class TaskNoteController {

    private final TaskNoteService s;

    public TaskNoteController(TaskNoteServiceImpl s) {
        this.s = s;
    }

    @GetMapping
    public List<ApiDtos.NoteResponse> list(
            @PathVariable(name = "taskId") Long taskId) {

        return s.list(taskId);
    }

    @PostMapping
    public ApiDtos.NoteResponse add(
            @PathVariable(name = "taskId") Long taskId,
            @Valid @RequestBody ApiDtos.NoteRequest request) {

        return s.add(taskId, request);
    }

    @DeleteMapping("/{noteId}")
    public void delete(
            @PathVariable(name = "taskId") Long taskId,
            @PathVariable(name = "noteId") Long noteId) {

        s.delete(taskId, noteId);
    }
}
