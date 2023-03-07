package br.com.sicredi.v1;

import br.com.sicredi.model.dto.PautaDto;
import br.com.sicredi.repository.PautaRepository;
import br.com.sicredi.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Timer;


@RestController
@RequestMapping(value = "/sicredi/votingsystem")
public class PautaApiController {
    @Autowired
    PautaService pautaService;

    private final PautaRepository pautaRepository;

    public PautaApiController(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    @PostMapping("/novapauta")
    public ResponseEntity<PautaDto> novaPauta(@RequestBody PautaDto pautaDto) {
        return ResponseEntity.ok(pautaService.create(pautaDto));
    }

    @GetMapping("/{name}")
    public ResponseEntity<PautaDto> getPauta(@PathVariable String name) {
        PautaDto pautaDto = pautaService.buscaPauta(name);
        if (pautaDto != null) {
            return ResponseEntity.ok(pautaDto);
        } else return ResponseEntity.notFound().build();
    }

    @PostMapping("/iniciasessao/{name}/{tempo}")
    public ResponseEntity<String> iniciaSessao(@PathVariable String name, @PathVariable Long tempo) {
        Timer timer;
        PautaDto pautaDto = pautaService.buscaPauta(name);
        if (pautaDto != null) {
            if (pautaDto.getSessaoDeVotacao() == null) {
                timer = pautaService.iniciaSessao(pautaDto, name, tempo);

                return ResponseEntity.ok("Sessão iniciada as : " + pautaDto.getSessaoDeVotacao().getStartTime());
                        } else {
                        if (pautaDto.getSessaoDeVotacao().isEncerrada())
                        return ResponseEntity.ok("Pauta " + name + " encerrada");
                        else return ResponseEntity.ok("Sessão ainda em votação ...restam ");
                        }
                        }
                        return ResponseEntity.notFound().build();
                        }

@PostMapping("/{pautaname}/{voto}")
public ResponseEntity<String> vote(@PathVariable String pautaname, @PathVariable Integer voto) {
        PautaDto pautaDto = pautaService.buscaPauta(pautaname);
        return pautaService.votar(pautaDto, voto);
        }

@GetMapping("/{pautaname}/resultado")
public ResponseEntity<String> resultado(@PathVariable String pautaname){
        PautaDto pautaDto = pautaService.buscaPauta(pautaname);
        if (pautaDto != null) {
        if(pautaDto.getSessaoDeVotacao() != null) {
        return ResponseEntity.ok(pautaDto.getSessaoDeVotacao().resultado());
        } else return ResponseEntity.ok("Sessão de votação ainda não foi iniciada !");

        } else return ResponseEntity.notFound().build();
        }
        }