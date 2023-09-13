package com.example.backendAPI.infrastructure.controller;

import com.example.backendAPI.application.ClientService;
import com.example.backendAPI.application.FileService;
import com.example.backendAPI.entities.Client;
import com.example.backendAPI.entities.Region;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@Slf4j
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    FileService fileService;

    @PostMapping("/clients")
    public ResponseEntity<?> addClient(@Valid @RequestBody Client client, BindingResult result) { // BindingResult -> Objeto que contiene todos los mensajes de error
        Client newClient = null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                            .stream()
                            .map(err -> "Field '" + err.getField() + "' " + err.getDefaultMessage())
                            .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            newClient = clientService.addClient(client);
        } catch (DataAccessException e) {
            response.put("message", "Error when insert in the database");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "Client added to database successfully!");
        response.put("client", newClient);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @GetMapping("/clients")
    public List<Client> getAllClients() {
        return clientService.findAll();
    }

    @GetMapping("/clients/page/{page}")
    public Page<Client> getAllClients(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 5);
        return clientService.findAll(pageable);
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<?> getClientById(@PathVariable Long id) {
        Client client = null;
        Map<String, Object> response = new HashMap<>();

        try{
            client = clientService.findClientById(id);
        } catch(DataAccessException e) {
            response.put("message", "Error when querying the database");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (client == null) {
            response.put("message", "The client with ID ".concat(id.toString().concat(" doesn´t exist in database!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Client>(client, HttpStatus.OK);
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<?> updateClient(@Valid @RequestBody Client client, BindingResult result, @PathVariable Long id) {
        Client currentClient = clientService.findClientById(id);
        Client updatedClient = null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "Field '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if (currentClient == null) {
            response.put("message", "The client with ID ".concat(id.toString().concat(" doesn´t exist in database!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try{
            currentClient.setName(client.getName());
            currentClient.setLastName(client.getLastName());
            currentClient.setCreateAt(client.getCreateAt());
            currentClient.setEmail(client.getEmail());
            currentClient.setRegion(client.getRegion());

            updatedClient = clientService.addClient(currentClient);
        } catch(DataAccessException e) {
            response.put("message", "Error when updating record");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "Client updated successfully!");
        response.put("client", updatedClient);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();


        try {
            Client client = clientService.findClientById(id);
            String oldAvatarName = client.getAvatar();
            fileService.delete(oldAvatarName);
            clientService.delete(id);
        } catch(DataAccessException e) {
            response.put("message", "Error when deleting record");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "Client deleted successfully!");

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @PostMapping("/clients/upload")
    public ResponseEntity<?> upload(@RequestParam("file")MultipartFile file, @RequestParam Long id) {
        Map<String, Object> response = new HashMap<>();
        Client client = clientService.findClientById(id);

        if (!file.isEmpty()) {
            String fileName = null;

            try {
                fileName = fileService.saveImage(file);
            } catch (IOException e) {
                response.put("message", "Error uploading image");
                response.put("error", e.getMessage());
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            String oldAvatarName = client.getAvatar();
            fileService.delete(oldAvatarName);
            client.setAvatar(fileName);
            clientService.addClient(client);

            response.put("client", client);
            response.put("message", "Avatar " + fileName + " uploaded successfully!");
        }

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @GetMapping("/uploads/img/{avatarName:.+}")
    public ResponseEntity<Resource> showAvatar(@PathVariable String avatarName) {
        Resource resource = null;

        try {
            resource = fileService.loadImage(avatarName);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"");

        return new ResponseEntity<Resource>(resource, headers , HttpStatus.OK);
    }

    @GetMapping("/clients/regions")
    public List<Region> listAllRegions() {
        return clientService.findAllRegions();
    }
}
