package br.com.sicredi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SessaoDeVotacaoDto {

    private Integer numeroDeVotos;
    private boolean encerrada;
    private String nomePauta;
    private Integer sim;
    private Integer nao;
    private Instant startTime;

    public SessaoDeVotacaoDto(String name, Instant instant) {
        startTime = instant;
        sim = 0;
        nao = 0;
        encerrada = false;
        numeroDeVotos = 0;
        nomePauta = name;
    }

    public void votar(Integer voto) {
        if (voto == 1)
            this.sim++;
        else
            this.nao++;
    }

    public Integer getTotalDeVotos() {
        return this.sim + this.nao;
    }

    public String resultado() {
        String resultado;
        if (sim > nao)
            return resultado = "Pauta Aprovada";
        else if (nao > sim)
            return resultado = "Pauta Não foi aprovada";
        else return resultado = "Pauta Empatada";

    }

}
