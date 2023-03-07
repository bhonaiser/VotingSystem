package br.com.sicredi.service;

import br.com.sicredi.mapper.PautaMapper;
import br.com.sicredi.model.dto.PautaDto;
import br.com.sicredi.model.dto.SessaoDeVotacaoDto;
import br.com.sicredi.repository.PautaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Timer;
import java.util.TimerTask;


@Service
public class PautaService {
    private final PautaMapper pautaMapper;
    private final PautaRepository pautaRepository;

    public PautaService(PautaMapper pautaMapper, PautaRepository pautaRepository) {
        this.pautaMapper = pautaMapper;
        this.pautaRepository = pautaRepository;
    }

    public PautaDto create(PautaDto pautaDto) {
        return pautaMapper.pautaToDto(pautaRepository.save(pautaMapper.dtoToPauta(pautaDto)));
    }

    public PautaDto buscaPauta(String name) {
        return pautaMapper.pautaToDto(pautaRepository.findByName(name));

    }

    public void save(PautaDto pautaDto) {
        pautaRepository.save(pautaMapper.dtoToPauta(pautaDto));
    }

    public Timer iniciaSessao(PautaDto pautaDto, String name, Long tempo){
        pautaDto.setSessaoDeVotacao(new SessaoDeVotacaoDto(pautaDto.getName(), Instant.now().minusSeconds(60*60*3)));
        Timer timer = startaTimerSessao(pautaDto, name, tempo);
        pautaRepository.save(pautaMapper.dtoToPauta(pautaDto));
        return timer;
    }

    private Timer startaTimerSessao(PautaDto pautaDto, String name, Long tempo) {
        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("Time is over ! " + name + " encerrada");
                pautaDto.getSessaoDeVotacao().setEncerrada(true);
                pautaRepository.save(pautaMapper.dtoToPauta(pautaDto));
            }
        };
        Timer timer = new Timer(pautaDto.getName());
        timer.schedule(task, tempo*1000);
        return timer;
    }

    public ResponseEntity<String> votar(PautaDto pautaDto, Integer voto){
        if (pautaDto != null) {
            if(pautaDto.getSessaoDeVotacao() != null) {
                if (pautaDto.getSessaoDeVotacao().isEncerrada() != true){
                    pautaDto.getSessaoDeVotacao().votar(voto);
                    pautaRepository.save(pautaMapper.dtoToPauta(pautaDto));
                    return ResponseEntity.ok("Voto computado");
                }
                else return ResponseEntity.ok("Sessão de votação encerrada");

            } else return ResponseEntity.ok("Sessão de votação ainda não foi iniciada !");

        } else return ResponseEntity.ok("Pauta não foi criada ainda !");
    }

}
