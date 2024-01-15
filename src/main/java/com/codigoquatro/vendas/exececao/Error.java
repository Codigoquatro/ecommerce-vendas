package com.codigoquatro.vendas.exececao;

public class Error {
    private String msgUsuario;
    
    private String msgDesenvolvedor;

    public Error(String msgUsuario, String msgDesenvolvedor) {
        this.msgUsuario = msgUsuario;
        this.msgDesenvolvedor = msgDesenvolvedor;
    }

    public String getMsgUsuario() {
        return msgUsuario;
    }

    public void setMsgUsuario(String msgUsuario) {
        this.msgUsuario = msgUsuario;
    }

    public String getMsgDesenvolvedor() {
        return msgDesenvolvedor;
    }

    public void setMsgDesenvolvedor(String msgDesenvolvedor) {
        this.msgDesenvolvedor = msgDesenvolvedor;
    }   
    
}
