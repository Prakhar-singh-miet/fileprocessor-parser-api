package com.example.parserapi.model;

public class ParseResponse {

    private String status;
    private String message;
    private String fileType;
    private String processedAt;
    private String fileprocessorResponse;
    public ParseResponse()
    {

    }
      public String getStatus()
      {
          return status;

      }
       public void setStatus(String status)
       {
           this.status=status;
       }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getProcessedAt() {
        return processedAt;
    }

    public void setProcessedAt(String processedAt) {
        this.processedAt = processedAt;
    }

    public String getFileprocessorResponse() {
        return fileprocessorResponse;
    }

    public void setFileprocessorResponse(String fileprocessorResponse) {
        this.fileprocessorResponse = fileprocessorResponse;
    }
}
