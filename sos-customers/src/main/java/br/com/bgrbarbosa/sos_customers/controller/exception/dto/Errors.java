package br.com.bgrbarbosa.sos_customers.controller.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Errors {
    private String field;
    private String error;
}
