package br.com.wirth.melhoriademodelo.entities;

import br.com.wirth.melhoriademodelo.entities.converter.BooleanStringConverter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "TB_REQUISICAO")
@Getter
@Setter
public class Requisicao implements Persistable<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "solicitante_c_usuario")
    private Usuario solicitante;

    private String modelo;

    private String tipo;

    private Instant data;

    private String descricao;

    @Convert(converter = BooleanStringConverter.class)
    private Boolean processado = false;

    @ManyToOne
    @JoinColumn(name = "avaliador_c_usuario")
    private Usuario avaliador;

    @Convert(converter = BooleanStringConverter.class)
    private Boolean possivel;

    private Instant dataValidacao;

    private String atualizacao;

    public Requisicao(Requisicao requisicao) {
    }
    public Requisicao() {
    }


    @Override
    public boolean isNew() {
        return false;
    }
}
