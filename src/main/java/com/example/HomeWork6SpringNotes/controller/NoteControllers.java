package com.example.HomeWork6SpringNotes.controller;

import com.example.HomeWork6SpringNotes.model.Note;
import com.example.HomeWork6SpringNotes.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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
    public Note addNone(@RequestBody Note note){
        note.setDate(LocalDate.now());
        return noteRepository.save(note);
    }

    /**
     * Просмотреть все заметки
     * @return
     */
    @GetMapping
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    /**
     * Получить заметку по id
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public Note getNoteById(@PathVariable("id") Long id){
        return noteRepository.findById(id).orElse(null);
    }

    /**
     * Метод обновления заметки
     * @param id
     * @param note
     * @return
     */
    @PutMapping("/{id}")
    public Note updateNote(@PathVariable Long id, @RequestBody Note note){
        Note noteUpdate = noteRepository.findById(id).orElse(null);
        if(note.getContent() == null){
            noteUpdate.setTitle(note.getTitle());
            noteUpdate.setContent(noteUpdate.getContent());
            noteUpdate.setDate(LocalDate.now());
        }
        if(note.getTitle() == null){
            noteUpdate.setTitle(noteUpdate.getTitle());
            noteUpdate.setContent(note.getContent());
            noteUpdate.setDate(LocalDate.now());
        }
        return noteRepository.save(noteUpdate);

    }

    /**
     * Метод удаление заметки
     * @param id
     */
    @DeleteMapping("/{id}")
    public void deleteNoteById(@PathVariable("id") Long id){
        noteRepository.deleteById(id);
    }

}
