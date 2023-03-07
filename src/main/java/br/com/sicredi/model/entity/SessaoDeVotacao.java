package br.com.sicredi.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;


//@AllArgsConstructor
//@NoArgsConstructor
@Data
public class SessaoDeVotacao {

    private Integer numeroDeVotos;
    private boolean encerrada;
    private String  nomePauta;
    private Integer sim ;
    private Integer nao ;
    private Instant startTime ;

}
