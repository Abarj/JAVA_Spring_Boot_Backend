package com.example.block6pathvariableheaders;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping
    public User createUser(@RequestBody User user) {
        return user;
    }

    @GetMapping("/{id}")
    public long getUserId(@PathVariable("id") long id) {
        return id;
    }
}
