package com.example.parserapi.parser;

import com.example.parserapi.entity.ParserEntity;
import com.example.parserapi.model.ParseRequest;
import com.example.parserapi.model.ParseResponse;
import jakarta.persistence.Entity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ParseParser {

     public ParserEntity ParseRequestToEntity(ParseRequest request)
     {
       ParserEntity entity=new ParserEntity();
        entity.setName(request.getName());
         entity.setAge(request.getAge());
         entity.setEmail(request.getEmail().trim().toLowerCase());
         entity.setDepartment(request.getDepartment().trim().toUpperCase());
          entity.setProcessedAt(getCurrentTimestamp());
           return entity;
     }
      public ParseResponse parseEntityToResponse(ParserEntity entity)
      {
      ParseResponse response =new ParseResponse();
        response.setStatus("SUCCESS");
        response.setMessage("Data Parse and stored Successfully");
        response.setName(entity.getName());
          response.setAge(entity.getAge());
          response.setEmail(entity.getEmail());
          response.setDepartment(entity.getDepartment());
           response.setProcessedAt(entity.getProcessedAt());
            return response;

      }
       public ParseResponse buildErrorResponse(String errorMessage)
       {
          ParseResponse response=new ParseResponse();
             response.setStatus("FAILURE");
             response.setMessage(errorMessage);
           response.setName(null);
           response.setAge(null);
           response.setEmail(null);
           response.setDepartment(null);
           response.setProcessedAt(getCurrentTimestamp());
            return response;

       }




     private String getCurrentTimestamp()
     {
         return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
     }
}
