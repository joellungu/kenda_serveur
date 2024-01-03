package org.kenda.models.utilisateurs;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class AuthentificationCode {

    public String code;
    public String numero;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    public Timestamp dateteste;
}
