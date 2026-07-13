package com.example.parserapi.controller;


import com.example.parserapi.model.ParseRequest;
import com.example.parserapi.model.ParseResponse;
import com.example.parserapi.service.ParseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/parser")
@Tag(name="Parser API",description = "Fixed Contract Parser Api for UAT Testing")
public class ParseController {

    @Autowired
    ParseService parseService;

     public ResponseEntity<ParseResponse> processFile(@RequestParam("file")
                                                      MultipartFile file) {
         if (file.isEmpty()) {
             ParseResponse response = new ParseResponse();
             response.setStatus("FAILURE");
             response.setMessage("FILE is empty");
             return ResponseEntity.badRequest().body(response);
         }
         try {
             ParseResponse response = parseService.forwardFileToProcessor(file);
             return ResponseEntity.ok(response);
         } catch (IOException e)
         {
         ParseResponse response=new ParseResponse();
            response.setStatus("FAILURE");
            response.setMessage("Failed to process file: "+e.getMessage());
            return ResponseEntity.status(500).body(response);
         }
     }





}
