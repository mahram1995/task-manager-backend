package com.mislbd.sprint.serviceImpl;
import com.mislbd.sprint.dto.ApiDtos;
import com.mislbd.sprint.entity.TaskEntity;
import com.mislbd.sprint.entity.TaskNoteEntity;
import com.mislbd.sprint.repository.DailyTaskRepository;
import com.mislbd.sprint.repository.TaskNoteRepository;
import com.mislbd.sprint.service.TaskNoteService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Component
public class TaskNoteServiceImpl implements TaskNoteService {
    private final DailyTaskRepository tasks;
    private final TaskNoteRepository notes;

    public TaskNoteServiceImpl(DailyTaskRepository t, TaskNoteRepository n) {
        tasks = t;
        notes = n;
    }
    @Override
    public ApiDtos.NoteResponse dto(TaskNoteEntity n) {
        return new ApiDtos.NoteResponse(n.getId(), n.getNoteDescription(), n.getNoteDate());
    }

    @Transactional(readOnly = true)
    @Override
    public List<ApiDtos.NoteResponse> list(Long id) {
        if (!tasks.existsById(id)) throw new IllegalArgumentException("Task not found");
        return notes.findByTaskIdOrderByNoteDateDesc(id).stream().map(this::dto).toList();
    }

    @Transactional
    @Override
    public ApiDtos.NoteResponse add(Long id, ApiDtos.NoteRequest r) {
        TaskEntity t = tasks.findById(id).orElseThrow(() -> new IllegalArgumentException("Task not found"));
        TaskNoteEntity n = new TaskNoteEntity();
        n.setTask(t);
        n.setNoteDate(LocalDateTime.now());
        n.setNoteDescription(r.noteDescription());
        return dto(notes.save(n));
    }

    @Transactional
    @Override
    public void delete(Long taskId, Long noteId) {
        TaskNoteEntity n = notes.findById(noteId).orElseThrow(() -> new IllegalArgumentException("Note not found"));
        if (!n.getTask().getId().equals(taskId)) throw new IllegalArgumentException("Note does not belong to task");
        notes.delete(n);
    }
}
