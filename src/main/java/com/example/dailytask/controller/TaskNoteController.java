
package com.example.dailytask.controller;

import com.example.dailytask.dto.ApiDtos.*;
import com.example.dailytask.service.TaskNoteService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks/{taskId}/notes")
public class TaskNoteController {

    private final TaskNoteService s;

    public TaskNoteController(TaskNoteService s) {
        this.s = s;
    }

    @GetMapping
    public List<NoteResponse> list(
            @PathVariable(name = "taskId") Long taskId) {

        return s.list(taskId);
    }

    @PostMapping
    public NoteResponse add(
            @PathVariable(name = "taskId") Long taskId,
            @Valid @RequestBody NoteRequest request) {

        return s.add(taskId, request);
    }

    @DeleteMapping("/{noteId}")
    public void delete(
            @PathVariable(name = "taskId") Long taskId,
            @PathVariable(name = "noteId") Long noteId) {

        s.delete(taskId, noteId);
    }
}
