package com.escolatecnica.projetotemplate.controller;
import com.escolatecnica.projetotemplate.dto.PerfilDTO;
import com.escolatecnica.projetotemplate.entity.Perfil;
import com.escolatecnica.projetotemplate.service.PerfilService;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/perfis")
public class PerfilController {
    private final PerfilService service;

    // Injeção de dependência recomendada via Construtor
    public PerfilController(PerfilService service) {
        this.service = service;
    }

    @GetMapping("/novo")
    public String cadastrarPerfil(Model model) {
        model.addAttribute("titulo", "Cadastro de Perfil");
        model.addAttribute("perfilDTO", new PerfilDTO(null, ""));
        return "cadastrarPerfil";
    }

    @PostMapping("/salvar")
    public String salvarPerfil(@ModelAttribute("perfilDTO") PerfilDTO dto, 
		RedirectAttributes redAtributos) {
        if (dto.id() != null) {
            service.update(dto.id(), dto);
            redAtributos.addFlashAttribute("mensagem", 
				"Perfil atualizado com sucesso!");
        } else {
            service.saveByDTO(dto);
            redAtributos.addFlashAttribute("mensagem", 
				"Perfil cadastrado com sucesso!");
        }
        return "redirect:/perfis";
    }

    @GetMapping
    public String listarPerfis(Model model) {
        List<Perfil> perfis = service.listAll();
        model.addAttribute("perfis", perfis);
        return "listarPerfis";
    }

    // Alterado de GET para POST por questões de segurança
    @Transactional
    @PostMapping("/excluir/{id}")
    public String excluirPerfil(@PathVariable("id") UUID id, 
		RedirectAttributes redAtributos) {
        service.deleteById(id);
        redAtributos.addFlashAttribute("mensagem", "Perfil excluído com sucesso!");
        return "redirect:/perfis";
    }

    @GetMapping("/editar/{id}")
    public String editarPerfil(@PathVariable("id") UUID id, Model model) {
        Perfil perfil = service.listById(id);
        PerfilDTO dto = new PerfilDTO(perfil.getId(), perfil.getNome());
        
        model.addAttribute("titulo", "Editar Perfil");
        model.addAttribute("perfilDTO", dto);
        return "cadastrarPerfil";
    }
}

  
