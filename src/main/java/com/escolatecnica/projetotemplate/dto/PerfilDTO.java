
package com.escolatecnica.projetotemplate.dto;

import com.escolatecnica.projetotemplate.entity.Perfil;

public record PerfilDTO(String nome){
    public Perfil toPerfil(){
        Perfil perfil = new Perfil();
        perfil.setNome(nome);
        return perfil;
    }
    
}
