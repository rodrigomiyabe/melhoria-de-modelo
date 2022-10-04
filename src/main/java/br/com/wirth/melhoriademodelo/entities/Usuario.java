package br.com.wirth.melhoriademodelo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TB_SOLICITANTE")
@Getter
@Setter
public class Usuario implements Serializable {
    private final static Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cUsuario;
    private String dUsuario;
}
