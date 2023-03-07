package br.com.sicredi.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@JsonIgnoreProperties(ignoreUnknown = true)
public class PautaDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;

    @NotNull
    private String name;

    @NotNull
    private SessaoDeVotacaoDto sessaoDeVotacao;

}
