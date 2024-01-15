package com.codigoquatro.vendas.exececao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GestaoDeVendasTratamentoExcecao extends ResponseEntityExceptionHandler {

    private static final String CONSTANT_VALIDATION_NOT_BLANK = "NotBlank";
    private static final String CONSTANT_VALIDATION_LENGTH = "Length";

    @Override   
    protected ResponseEntity<Object> handleMethodArgumentNotValid(@NonNull MethodArgumentNotValidException ex,
            @NonNull HttpHeaders headers, @NonNull HttpStatusCode status, @NonNull WebRequest request) {

        gerarListaDeErros(ex.getBindingResult());
        
        return super.handleMethodArgumentNotValid(ex, headers, status, request);
    }

    private List<Error> gerarListaDeErros(BindingResult bindingResult) {
        List<Error> erros = new ArrayList<>();
        bindingResult.getFieldErrors().forEach(fieldError -> {
            String msgUsuario = tratarMensagemDeErroParaUsuario(fieldError);
            String msgDesenvolvedor = fieldError.toString();
            erros.add(new Error(msgUsuario, msgDesenvolvedor));
        });
        return erros;        
    }

    private String tratarMensagemDeErroParaUsuario(FieldError fieldError) {
if (CONSTANT_VALIDATION_NOT_BLANK.equals(fieldError.getCode())) {
    String defaultMessage = fieldError.getDefaultMessage();
    return defaultMessage != null ? defaultMessage.concat(" é obrigatório") : "Campo é obrigatório";
}

        if (CONSTANT_VALIDATION_LENGTH.equals(fieldError.getCode())) {
            String defaultMessage = fieldError.getDefaultMessage();
            Object[] arguments = fieldError.getArguments();
            String formattedMessage = String.format("deve ter entre %s e %s caracteres",
                    arguments != null && arguments.length > 2 ? arguments[2] : "",
                    arguments != null && arguments.length > 1 ? arguments[1] : "");
            return defaultMessage != null ? defaultMessage.concat(formattedMessage) : formattedMessage;
        }
        return fieldError.toString();
    }

}

