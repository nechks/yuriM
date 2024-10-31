package br.com.trabalho.romulo.api.yuriM.controller;


import br.com.trabalho.romulo.api.yuriM.model.PessoaRepositery;
import br.com.trabalho.romulo.api.yuriM.model.Pessoas;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class PessoaController {
    @Autowired
    private PessoaRepositery pessoaRepositery;


    @GetMapping("/pessoas")
    public String mostrarForm(Model model) {;
        model.addAttribute("pessoa", new Pessoas());
        return "formPessoa";
    }

    @PostMapping(value = "/pessoas", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String adicionarPessoa(Pessoas pessoa, RedirectAttributes redirectAttributes) {
        pessoaRepositery.save(pessoa);
        redirectAttributes.addFlashAttribute("mensagem", "Pessoa adicionada com sucesso!");
        return "redirect:pessoas/listarPessoas";
    }


    @GetMapping("pessoas/listarPessoas")
    public String listarPessoas(Model model) {
        List<Pessoas> pessoas = pessoaRepositery.findAll();
        model.addAttribute("pessoas", pessoas);
        return "pessoas/listarPessoas";
    }







}
