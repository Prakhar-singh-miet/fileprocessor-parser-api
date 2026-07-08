package com.example.parserapi.service;

import com.example.parserapi.entity.ParserEntity;
import com.example.parserapi.exception.ResourceNotFoundException;
import com.example.parserapi.model.ParseRequest;
import com.example.parserapi.model.ParseResponse;
import com.example.parserapi.parser.ParseParser;
import com.example.parserapi.repository.ParseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParseService {

    @Autowired
    ParseRepository parseRepository;
    @Autowired
    ParseParser parseParser;
    public ParseResponse parseAndSave(ParseRequest request)
    {
        ParserEntity entity=parseParser.ParseRequestToEntity(request);
        ParserEntity saved=parseRepository.save(entity);
        return parseParser.parseEntityToResponse(saved);
    }

    public List<ParseResponse> getAll()
    {
        List<ParserEntity> entities=parseRepository.findAll();
         return entities.stream()
                 .map(entity->parseParser.parseEntityToResponse(entity))
                 .collect(Collectors.toList());
    }
     public ParseResponse getById(Long id)
     {
         ParserEntity entity=parseRepository.findById(id)
                 .orElseThrow(()->new ResourceNotFoundException("" +
                         "Record Not Found with id:"+id));
           return parseParser.parseEntityToResponse(entity);
     }
      public ParseResponse deleteById(Long id)
      {
          ParserEntity entity=parseRepository.findById(id)
                  .orElseThrow(()->new ResourceNotFoundException("" +
                          "Resource Not Found with id:"+id));
             parseRepository.deleteById(id);
             ParseResponse response=parseParser.parseEntityToResponse(entity);
                response.setStatus("SUCCESS");
                response.setMessage("Record Deleted Successfully");
                return response;

      }

}
