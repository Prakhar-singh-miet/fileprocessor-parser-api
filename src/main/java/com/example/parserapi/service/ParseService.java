package com.example.parserapi.service;

import com.example.parserapi.config.AppConfig;
import com.example.parserapi.entity.ParserEntity;
import com.example.parserapi.exception.ResourceNotFoundException;
import com.example.parserapi.model.ParseRequest;
import com.example.parserapi.model.ParseResponse;
import com.example.parserapi.parser.ParseParser;
import com.example.parserapi.repository.ParseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import org.springframework.http.HttpHeaders;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParseService {

    @Autowired
    ParseRepository parseRepository;
    @Autowired
    RestTemplate restTemplate;
     private static final String FILEPROCESSOR_URL="http://localhost:8080";

     public ParseResponse forwardFileToProcessor(MultipartFile file) throws IOException
     {
         String fileName= file.getOriginalFilename();
          String fileType="";
          String url="";

          if(fileName!=null&&fileName.endsWith(".csv"))
          {
              fileType="CSV";
              url=FILEPROCESSOR_URL+"/students/upload";
          }
          else if(fileName!=null&&fileName.endsWith(".json"))
          {
              fileType="JSON";
              url=FILEPROCESSOR_URL+"/students/upload-json";
          }
          else
          {
              return buildErrorResponse("only CSV and json files are allowed");
          }
         HttpHeaders headers=new HttpHeaders();
         headers.setContentType(MediaType.MULTIPART_FORM_DATA);

         ByteArrayResource fileResource=new ByteArrayResource(file.getBytes())
         {
             @Override
             public String getFilename()
             {
                 return file.getOriginalFilename();
             }
         };
         MultiValueMap<String,Object> body=new LinkedMultiValueMap<>();
         body.add("file",fileResource);

         HttpEntity<MultiValueMap<String,Object>> requestEntity=new HttpEntity<>(body,headers);
         ResponseEntity<String> fileprocessorResponse =restTemplate.postForEntity(url,requestEntity,
                 String.class);
        ParseResponse response=new ParseResponse();
            response.setStatus("SUCESS");
            response.setMessage("File forwarded and processed succeefully");
            response.setFileType(fileType);
            response.setProcessedAt(getCurrentTimestamp());
            response.setFileprocessorResponse(fileprocessorResponse.getBody());
            return response;

     }

  private ParseResponse buildErrorResponse(String message)
  {
      ParseResponse response=new ParseResponse();
       response.setStatus("FAILURE");
       response.setMessage(message);
       response.setProcessedAt(getCurrentTimestamp());
       return response;
  }
  private String getCurrentTimestamp()
  {
      return LocalDateTime.now()
              .format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss"));

  }

}
