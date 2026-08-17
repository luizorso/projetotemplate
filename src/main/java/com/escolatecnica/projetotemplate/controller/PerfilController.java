
package com.escolatecnica.projetotemplate.controller;

import com.escolatecnica.projetotemplate.dto.PerfilDTO;
import com.escolatecnica.projetotemplate.service.PerfilService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.ui.Model;

@Controller
public class PerfilController {

    @Autowired
    private PerfilService service;

    @GetMapping("/cadastrarPerfil")
    public String cadastrarPerfil(Model model) {
        model.addAttribute("titulo", "Cadastro de Perfil");
        model.addAttribute("nome", "");
        return "cadastrarPerfil";
    }

    @PostMapping("/resultado")
    public String salvarPerfil(PerfilDTO dto, Model model) {
        // 1. Salva no banco através do serviço
        service.saveByDTO(dto);

        // 2. Prepara os dados para serem exibidos na tela resultado.html
        model.addAttribute("mensagem", "Perfil cadastrado com sucesso!");
        model.addAttribute("nome", dto.nome());

        // 3. Retorna o template resultado.html
        return "resultado";
    }
}
