package br.com.wirth.melhoriademodelo.dtos;

import br.com.wirth.melhoriademodelo.entities.Requisicao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;


@Getter
@Setter
@AllArgsConstructor
public class RequisicaoDTO implements Serializable {

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


    public RequisicaoDTO(Requisicao req){
        this.id = req.getId();
        this.modelo = req.getModelo();
        this.tipo = req.getTipo();
        this.data = req.getData();
        this.solicitante = req.getSolicitante().getCUsuario();
        this.descricao = req.getDescricao();
    }


}
