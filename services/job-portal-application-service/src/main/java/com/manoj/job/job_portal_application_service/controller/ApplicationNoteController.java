package com.manoj.job.job_portal_application_service.controller;

import com.manoj.job.dto.response.ApiResponse;
import com.manoj.job.dto.response.ApplicationNoteResponse;
import com.manoj.job.job_portal_application_service.payload.AddApplicationNoteRequest;
import com.manoj.job.job_portal_application_service.service.ApplicationNoteService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications/{application-id}/notes")
public class ApplicationNoteController {

    @Autowired
    private ApplicationNoteService applicationNoteService;

    @PostMapping("/add-note")
    public ResponseEntity<ApplicationNoteResponse> addNote(@PathVariable("application-id") Long applicationId,
                                                           @RequestHeader("X-User-Id") Long employerId,
                                                           @RequestBody @Valid AddApplicationNoteRequest request) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(applicationNoteService.addNote(applicationId,employerId,request));
    }
    @GetMapping("/get-note")
    public ResponseEntity<List<ApplicationNoteResponse>> getNotes(@PathVariable("application-id") Long applicationId,
                                                                  @RequestHeader("X-User-Id") Long employerId){
        return ResponseEntity.ok(applicationNoteService.getNotesByApplication(applicationId,employerId));

    }
    @DeleteMapping("/{note-id}/delete")
    public ResponseEntity<ApiResponse> deleteNote(@PathVariable("application-id") Long applicationId,
                                                  @PathVariable("note-id") Long noteId,
                                                  @RequestHeader("X-User-Id") Long employerId) throws Exception {
        applicationNoteService.deleteNote(applicationId,noteId,employerId);
        return ResponseEntity.ok(new ApiResponse("Note Deleted Successfully",true));
    }


}
