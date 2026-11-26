package com.example.dailytask.repository;

import com.example.dailytask.entity.TaskNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskNoteRepository extends JpaRepository<TaskNote, Long> {
    List<TaskNote> findByTaskIdOrderByNoteDateDesc(Long taskId);
}