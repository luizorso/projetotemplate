
package com.escolatecnica.projetotemplate.dto;

import com.escolatecnica.projetotemplate.entity.Perfil;
import java.util.UUID;

public record PerfilDTO(UUID id,String nome){
    
    public PerfilDTO(String nome){
        this(null,nome);
    }
    public Perfil toPerfil(){
        Perfil perfil = new Perfil();
        perfil.setId(this.id);
        perfil.setNome(this.nome);
        return perfil;
    }
    
}
