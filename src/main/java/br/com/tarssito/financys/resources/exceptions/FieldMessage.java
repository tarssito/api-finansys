package br.com.tarssito.financys.resources.exceptions;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class FieldMessage  {

    private String fieldName;
    private String message;

    public FieldMessage(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public FieldMessage() {
    }
}
