package br.com.wirth.melhoriademodelo.dtos;

import br.com.wirth.melhoriademodelo.entities.Requisicao;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;


@Getter
@Setter
public class ConsultaRequisicaoDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long id;
    private String modelo;
    private Integer solicitante;
    private String tipo;
    private Instant data;
    private String descricao;
    private Integer avaliador;
    private Boolean processado;
    private Boolean possivel;
    private Instant dataValidacao;
    private String atualizacao;


    public ConsultaRequisicaoDTO(Requisicao requisicaoDTO){
        this.id = requisicaoDTO.getId();
        this.modelo = requisicaoDTO.getModelo();
        this.solicitante = requisicaoDTO.getSolicitante().getCUsuario();
        this.tipo = requisicaoDTO.getTipo();
        this.data = requisicaoDTO.getData();
        this.processado = requisicaoDTO.getProcessado();
        this.descricao = requisicaoDTO.getDescricao();
        if (processado) {
            this.avaliador = requisicaoDTO.getAvaliador().getCUsuario();
            this.possivel = requisicaoDTO.getPossivel();
            this.dataValidacao = requisicaoDTO.getDataValidacao();
            this.atualizacao = requisicaoDTO.getAtualizacao();
        }
    }


}
