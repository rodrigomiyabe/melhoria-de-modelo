package br.com.wirth.melhoriademodelo.dtos;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
public class AvaliacaoDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long id;
    private Integer avaliador;
    private Boolean possivel;
    private Instant dataValidacao;
    private String atualizacao;

}
