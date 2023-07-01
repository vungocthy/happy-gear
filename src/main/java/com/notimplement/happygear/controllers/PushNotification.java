package com.notimplement.happygear.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.notimplement.happygear.model.dto.Note;
import com.notimplement.happygear.service.FirebaseMessagingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class PushNotification {

    private final FirebaseMessagingService firebaseService;

    @PostMapping("")
    public String sendNotification(@RequestBody Note note)
            throws FirebaseMessagingException {
        return firebaseService.sendNotification(note, note.getToken());
    }
}
