package com.smartcareer.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

/*
   This class  make sure that all the responses have the same standards
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

    private  String message;
    private  T data;
    private  int statusCode;
    private LocalDateTime timestamp;

     /*
       Generic method for a success response
     */
    public  static <T> Response<T> success(T data, String message)
    {
        return  Response.<T>builder()
                .statusCode(200)
                .message(message)
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();

    }
    /*
     *Generic method for error response
     */
    public static  <T> Response<T>   error(int code, String message)
    {
        return  Response.<T>builder()
                .statusCode(code)
                .message(message)
                .data(null)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
