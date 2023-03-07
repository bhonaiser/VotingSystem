package br.com.sicredi.model.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SessaoResponseDto {

    private String nomeDaPauta;
    private Integer tempoInicial;
    private Integer numeroDeVotos;
}
