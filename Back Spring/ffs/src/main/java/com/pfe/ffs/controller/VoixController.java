package com.pfe.ffs.controller;

import com.pfe.ffs.entity.Voix;
import com.pfe.ffs.services.voix.VoixService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class VoixController {
    private final VoixService voixService;

    @GetMapping("/voix")
    public ResponseEntity<List<Voix>> getAllVoix(){
        List<Voix> voix = voixService.getAllVoix();
        return ResponseEntity.ok(voix);
    }
}
