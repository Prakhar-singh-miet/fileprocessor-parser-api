package com.example.parserapi.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class ParseRequest {

    @NotBlank(message="name cannot be empty")
    private String name;
@NotBlank(message = "Age cannot be null")
   @Min(value=18,message="age should be atleast 18")
    private Integer age;
     @NotBlank(message="email cannot be empty")
    private String email;
      @NotBlank(message="department cannot be empty")
    private String department;


       public String getName()
       {
            return name;
       }
        public void setName(String name)
        {
             this.name=name;
        }
         public Integer getAge()
         {
             return age;
         }
          public void setAge()
          {
              this.age=age;
          }
           public String getEmail()
           {
               return email;

           }
            public void setEmail(String email)
            {
                this.email=email;
            }
             public String getDepartment()
             {
                  return department;
             }
              public void setDepartment(String department)
              {
                  this.department=department;
              }

}

