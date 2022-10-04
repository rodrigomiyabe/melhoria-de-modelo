package br.com.wirth.melhoriademodelo.services;

import br.com.wirth.melhoriademodelo.dtos.AvaliacaoDTO;
import br.com.wirth.melhoriademodelo.dtos.ConsultaRequisicaoDTO;
import br.com.wirth.melhoriademodelo.dtos.RequisicaoDTO;
import br.com.wirth.melhoriademodelo.entities.Requisicao;
import br.com.wirth.melhoriademodelo.entities.Usuario;
import br.com.wirth.melhoriademodelo.repositories.RequisicaoRepository;
import br.com.wirth.melhoriademodelo.repositories.UsuarioRepository;
import br.com.wirth.melhoriademodelo.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class RequisicaoService {
    private final RequisicaoRepository repository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public RequisicaoService(RequisicaoRepository repository, UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    public Page<ConsultaRequisicaoDTO> findAllPaged(Pageable pageable) {
        Page<Requisicao> requisicaoPage = repository.findAll(pageable);
        return requisicaoPage.map(ConsultaRequisicaoDTO::new);
    }

    @Transactional(readOnly = true)
    public Page<ConsultaRequisicaoDTO> findAllAvaliado(Pageable pageable){
        Page<Requisicao> requisicaoPage = repository.findRequisicaoByProcessadoIsTrue(pageable);
        return requisicaoPage.map(ConsultaRequisicaoDTO::new);
    }

    @Transactional(readOnly = true)
    public Page<ConsultaRequisicaoDTO> findAllUnvalued(Pageable pageable) {
        Page<Requisicao> requisicaoPage = repository.findRequisicaoByProcessadoIsFalse(pageable);
        return requisicaoPage.map(ConsultaRequisicaoDTO::new);
    }

    @Transactional
    public RequisicaoDTO insert(RequisicaoDTO dto) {

        Usuario solicitante = usuarioRepository.findById(dto.getSolicitante()).orElseThrow(() -> new ResourceNotFoundException("Entity not found"));

        Requisicao requisicao = new Requisicao();
        requisicao.setSolicitante(solicitante);
        requisicao.setModelo(dto.getModelo());
        requisicao.setData(Instant.now());
        requisicao.setTipo(dto.getTipo());
        requisicao.setDescricao(dto.getDescricao());
        repository.save(requisicao);
        return new RequisicaoDTO(requisicao);
    }

    @Transactional
    public void insertAvaliacao(AvaliacaoDTO avaliacaoDTO) {
        Requisicao requisicao = repository.findById(avaliacaoDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Requisição com id" + avaliacaoDTO.getId() + "não encontrada"));
        Usuario usuario = usuarioRepository.findById(avaliacaoDTO.getAvaliador()).orElseThrow(() -> new ResourceNotFoundException("Entity not found"));

        requisicao.setPossivel(avaliacaoDTO.getPossivel());
        requisicao.setAvaliador(usuario);
        requisicao.setDataValidacao(avaliacaoDTO.getDataValidacao());
        requisicao.setAtualizacao(avaliacaoDTO.getAtualizacao());
        requisicao.setProcessado(true);
        repository.save(requisicao);
    }

}
