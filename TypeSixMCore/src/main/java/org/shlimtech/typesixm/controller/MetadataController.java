package org.shlimtech.typesixm.controller;

import lombok.RequiredArgsConstructor;
import org.shlimtech.typesixdatabasecommon.dto.UserDTO;
import org.shlimtech.typesixdatabasecommon.metadata.Metadata;
import org.shlimtech.typesixm.service.MetadataUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/metadata")
@RequiredArgsConstructor
public class MetadataController {

    private final MetadataUserService metadataUserService;

    @GetMapping("/user-info")
    public ResponseEntity<UserDTO> userInfo(JwtAuthenticationToken token) {
        UserDTO userDTO = metadataUserService.loadUser(Math.toIntExact((Long) token.getTokenAttributes().get("id")));
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/get")
    public ResponseEntity<Metadata> getUserMetadata(JwtAuthenticationToken token) {
        int userId = Integer.parseInt(token.getTokenAttributes().get("id").toString());
        return ResponseEntity.ok(metadataUserService.getMetadata(userId));
    }

    @PostMapping("/set")
    public ResponseEntity<?> setUserMetadata(JwtAuthenticationToken token, @RequestBody Metadata metadata) {
        int userId = Integer.parseInt(token.getTokenAttributes().get("id").toString());
        metadataUserService.setMetadata(userId, metadata);
        return ResponseEntity.ok().build();
    }

}