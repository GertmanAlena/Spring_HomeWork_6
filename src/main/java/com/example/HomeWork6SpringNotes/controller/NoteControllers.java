package com.example.HomeWork6SpringNotes.controller;

import com.example.HomeWork6SpringNotes.model.Note;
import com.example.HomeWork6SpringNotes.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/note")
public class NoteControllers {

    private final NoteRepository noteRepository;

    /**
     * Добавление заметки
     * @param note
     * @return
     */
    @PostMapping
    public ResponseEntity<Note> addNone(@RequestBody Note note){
        note.setDate(LocalDate.now());
        return new ResponseEntity<>(noteRepository.save(note), HttpStatus.CREATED);
    }

    /**
     * Просмотреть все заметки
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        return new ResponseEntity<>(noteRepository.findAll(), HttpStatus.OK);
    }

    /**
     * Получить заметку по id
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable("id") Long id){
        return new ResponseEntity<>(noteRepository.findById(id).orElse(null), HttpStatus.OK);
    }

    /**
     * Метод обновления заметки
     * @param id
     * @param note
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @RequestBody Note note){
        Optional<Note> noteOptional = noteRepository.findById(id);
        Note n = noteOptional.get();

        if (noteOptional.isPresent()) {
            n.setTitle(note.getTitle());
            n.setContent(note.getContent());
            n.setDate(LocalDate.now());
            noteRepository.save(n);
            return ResponseEntity.ok(note);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Метод удаление заметки
     * @param id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNoteById(@PathVariable("id") Long id){
        noteRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
