package com.mislbd.sprint.service;

import com.mislbd.sprint.dto.ApiDtos;
import com.mislbd.sprint.entity.TaskNoteEntity;

import java.util.List;

public interface TaskNoteService {
    public ApiDtos.NoteResponse dto(TaskNoteEntity n);
    public List<ApiDtos.NoteResponse> list(Long id);
    public ApiDtos.NoteResponse add(Long id, ApiDtos.NoteRequest r);
    public void delete(Long taskId, Long noteId);
}
