package br.com.bgrbarbosa.sos_service_order.controller.exception.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResponse {

    private int status;
    private String message;
    private List<Errors> erros;

    public static ErrorResponse responseDefault(String menssage){
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), menssage, List.of());
    }
}
