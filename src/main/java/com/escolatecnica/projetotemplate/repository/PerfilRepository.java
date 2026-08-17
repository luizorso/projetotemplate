
package com.escolatecnica.projetotemplate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.escolatecnica.projetotemplate.entity.Perfil;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil,UUID>{
    
}
