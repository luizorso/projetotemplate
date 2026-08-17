
package com.escolatecnica.projetotemplate.service;

import com.escolatecnica.projetotemplate.dto.PerfilDTO;
import com.escolatecnica.projetotemplate.entity.Perfil;
import com.escolatecnica.projetotemplate.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerfilService {
    @Autowired
    PerfilRepository rep;
    
    public Perfil saveByDTO(PerfilDTO dto){
        Perfil perfil = rep.save(dto.toPerfil());
        return perfil;
        
    }
    
}
