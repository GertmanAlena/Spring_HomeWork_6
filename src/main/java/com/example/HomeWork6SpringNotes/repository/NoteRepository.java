package com.example.HomeWork6SpringNotes.repository;

import com.example.HomeWork6SpringNotes.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface NoteRepository extends JpaRepository<Note, Long> {

}
