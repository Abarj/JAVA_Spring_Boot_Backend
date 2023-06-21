package com.example.block6pathvariableheaders;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/post")
public class PostController {

    @PutMapping
    public Map<String, String> updatePost(@RequestParam("var1") String var1, @RequestParam("var2") String var2) {
        Map<String, String> data = new HashMap<>();
        data.put("var1", var1);
        data.put("var2", var2);
        return data;
    }
}
