
package com.escolatecnica.projetotemplate.service;

import com.escolatecnica.projetotemplate.dto.PerfilDTO;
import com.escolatecnica.projetotemplate.entity.Perfil;
import com.escolatecnica.projetotemplate.repository.PerfilRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PerfilService {
  
    private final PerfilRepository rep;
    
    public PerfilService(PerfilRepository rep){
        this.rep = rep;
    }
    
    @Transactional
    public Perfil saveByDTO(PerfilDTO dto){
        // converte o DTO para Entidade
        Perfil perfil = rep.save(dto.toPerfil());
        // executa o INSERT no banco
        return perfil;
    }
    
    //Listar todos os perfis
    public List<Perfil> listAll(){
        return rep.findAll();
    }
    
    //Buscar por id
    /* findById(id): Retorna um objeto do tipo Optional<Perfil>. 
    Usamos .orElseThrow() para lançar uma exceção amigável caso o ID 
    buscado não exista no banco de dados.
    
    */
    
    public Perfil listById(UUID id){
        return rep.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Perfil não encontrado para o ID: " + id));
    }
    
  
    /*
    Para operações de escrita/atualização no banco de dados, é boa prática 
    marcar o método como transactional para garantir que todas as alterações 
    ocorram dentro de uma mesma transação.
    */
    @Transactional
    public Perfil update(UUID id, PerfilDTO dto){
        //Busca o perfil existente no banco de dados
        Perfil perfil = listById(id);
               
        // Atualiza os dados da entidade com as informações recebidas no DTO
        perfil.setNome(dto.nome());
        
        // Salva a entidade atualizada (faz UPDATE por conta do ID presente)
        return rep.save(perfil);
    }
    
    @Transactional
    public void deleteById(UUID id){
        Perfil perfil = listById(id);
        rep.delete(perfil);
    }
 }
