package com.mislbd.sprint.repository;

import com.mislbd.sprint.entity.TaskNoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskNoteRepository extends JpaRepository<TaskNoteEntity, Long> {
    List<TaskNoteEntity> findByTaskIdOrderByNoteDateDesc(Long taskId);
}