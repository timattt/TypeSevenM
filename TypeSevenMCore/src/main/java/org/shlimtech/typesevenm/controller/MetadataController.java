package org.shlimtech.typesevenm.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.shlimtech.typesevendatabasecommon.dto.MetadataDTO;
import org.shlimtech.typesevendatabasecommon.service.MetadataService;
import org.shlimtech.typesevenm.dto.BioDTO;
import org.shlimtech.typesevenm.service.MatchTasksService;
import org.shlimtech.typesixdatabasecommon.dto.UserDTO;
import org.shlimtech.typesixdatabasecommon.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/metadata")
@RequiredArgsConstructor
@Log
public class MetadataController {

    private final MetadataService metadataService;
    private final UserService userService;
    private final MatchTasksService matchTasksService;

    @GetMapping("/get")
    public ResponseEntity<?> userInfo(JwtAuthenticationToken token) {
        UserDTO userDTO = userService.loadUser(Math.toIntExact((Long) token.getTokenAttributes().get("id")));
        MetadataDTO metadataDTO = metadataService.loadUserMetadataDTO(userDTO.getId());
        return ResponseEntity.ok(Map.of("user", userDTO, "metadata", metadataDTO));
    }

    @PostMapping("/set")
    public ResponseEntity<?> setUserMetadata(JwtAuthenticationToken token, @RequestBody MetadataDTO metadata) {
        int userId = Integer.parseInt(token.getTokenAttributes().get("id").toString());
        metadataService.saveUserMetadataDTO(userId, metadata);
        matchTasksService.createMatchTask(userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/set/bio")
    public ResponseEntity<?> setBio(JwtAuthenticationToken token, @RequestBody BioDTO bioDTO) {
        int userId = Integer.parseInt(token.getTokenAttributes().get("id").toString());
        userService.setBio(userId, bioDTO.getBio());
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler
    public ResponseEntity<?> errorHandler(Exception e) {
        log.severe("Error in controller: [" + e.getMessage() + "]");
        log.severe(Arrays.toString(e.getStackTrace()));
        return ResponseEntity.internalServerError().body(Map.of("error", e.getMessage()));
    }

}