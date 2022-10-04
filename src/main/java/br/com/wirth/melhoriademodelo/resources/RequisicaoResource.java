package br.com.wirth.melhoriademodelo.resources;

import br.com.wirth.melhoriademodelo.dtos.AvaliacaoDTO;
import br.com.wirth.melhoriademodelo.dtos.ConsultaRequisicaoDTO;
import br.com.wirth.melhoriademodelo.dtos.RequisicaoDTO;
import br.com.wirth.melhoriademodelo.services.RequisicaoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Controller
@RequestMapping("/requisicao")
public class RequisicaoResource {

    private final RequisicaoService service;

    public RequisicaoResource(RequisicaoService service) {
        this.service = service;
    }

    @PostMapping("/insert")
    public ResponseEntity<RequisicaoDTO> createNewRequisicao(@RequestBody RequisicaoDTO requisicaoDTO) {
        requisicaoDTO = service.insert(requisicaoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(requisicaoDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(requisicaoDTO);
    }

    @GetMapping("/listaTodos")
    public ResponseEntity<Page<ConsultaRequisicaoDTO>> findRequisicao(Pageable pageable) {
        return ResponseEntity.ok().body(service.findAllPaged(pageable));
    }

    @GetMapping("/listaNaoAvaliados")
    public ResponseEntity<Page<ConsultaRequisicaoDTO>> findAllUnvalued(Pageable pageable) {
        return ResponseEntity.ok().body(service.findAllUnvalued(pageable));
    }

    @PostMapping("/insertValidacao")
    public ResponseEntity<Void> insertValidacao(@RequestBody AvaliacaoDTO avaliacaoDTO) {
        service.insertAvaliacao(avaliacaoDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/listaAvaliado")
    public ResponseEntity<Page<ConsultaRequisicaoDTO>> findAllAvaliado(Pageable pageable){
        return ResponseEntity.ok().body(service.findAllAvaliado(pageable));
    }



}
