package com.blog.blog.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestErrorMenssage {

    private Integer status;
    private String message;
    private List<String> errorMessages;
    private final LocalDateTime timestamp = LocalDateTime.now();

    public RestErrorMenssage(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public RestErrorMenssage(Integer status, List<String> errorMessages) {
        this.status = status;
        this.errorMessages = errorMessages;
    }
}
