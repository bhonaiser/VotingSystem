package br.com.sicredi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
//@AllArgsConstructor
//@NoArgsConstructor
@Data
public class Pauta {

    @Id
    private String id;

    @Indexed(unique = true)
    private String name;

    private SessaoDeVotacao sessaoDeVotacao;

}
