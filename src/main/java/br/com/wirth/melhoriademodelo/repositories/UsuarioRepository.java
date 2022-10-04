package br.com.wirth.melhoriademodelo.repositories;

import br.com.wirth.melhoriademodelo.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}