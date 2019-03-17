package br.com.tarssito.financys.resources.exceptions;

import lombok.Data;

/**
 * Classe que retorna dados das excpetions capturadas
 */
@SuppressWarnings("serial")
@Data
public class StandardError {

    private Long timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    public StandardError(Long timestamp, Integer status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

}
