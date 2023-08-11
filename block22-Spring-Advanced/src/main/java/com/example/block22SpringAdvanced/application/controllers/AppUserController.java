package com.example.block22SpringAdvanced.application.controllers;

import com.example.block22SpringAdvanced.infrastructure.abstract_services.ModifyUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping(path = "user")
@AllArgsConstructor
@Tag(name = "User")
public class AppUserController {

    private final ModifyUserService modifyUserService;

    @Operation(summary = "Enabled or disabled user")
    @PatchMapping(path = "enabled-or-disabled")
    public ResponseEntity<Map<String, Boolean>> enabledOrDisabled(@RequestParam String username) {
        return ResponseEntity.ok(modifyUserService.enabled(username));
    }

    @Operation(summary = "Adds a role to a user")
    @PatchMapping(path = "add-role")
    public ResponseEntity<Map<String, Set<String>>> addRole(@RequestParam String username, @RequestParam String role) {
        return ResponseEntity.ok(modifyUserService.addRole(username, role));
    }

    @Operation(summary = "Removes a role to a user")
    @PatchMapping(path = "remove-role")
    public ResponseEntity<Map<String, Set<String>>> removeRole(@RequestParam String username, @RequestParam String role) {
        return ResponseEntity.ok(modifyUserService.removeRole(username, role));
    }
}
