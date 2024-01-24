package org.shlimtech.typesevenm.controller;

import lombok.RequiredArgsConstructor;
import org.shlimtech.typesevendatabasecommon.dto.MetadataDTO;
import org.shlimtech.typesevendatabasecommon.metadata.Metadata;
import org.shlimtech.typesevendatabasecommon.service.MetadataService;
import org.shlimtech.typesixdatabasecommon.dto.UserDTO;
import org.shlimtech.typesixdatabasecommon.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/metadata")
@RequiredArgsConstructor
public class MetadataController {

    private final MetadataService metadataService;
    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity<?> userInfo(JwtAuthenticationToken token) {
        UserDTO userDTO = userService.loadUser(Math.toIntExact((Long) token.getTokenAttributes().get("id")));
        MetadataDTO metadataDTO = metadataService.loadUserMetadataDTO(userDTO.getId());
        return ResponseEntity.ok(Map.of("user", userDTO, "metadata", metadataDTO));
    }

    @PostMapping("/set")
    public ResponseEntity<?> setUserMetadata(JwtAuthenticationToken token, @RequestBody Metadata metadata) {
        int userId = Integer.parseInt(token.getTokenAttributes().get("id").toString());
        metadataService.saveUserMetadata(userId, metadata);
        return ResponseEntity.ok().build();
    }

}