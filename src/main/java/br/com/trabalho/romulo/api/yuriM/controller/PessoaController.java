package br.com.trabalho.romulo.api.yuriM.controller;


import br.com.trabalho.romulo.api.yuriM.model.EnderecoPessoas;
import br.com.trabalho.romulo.api.yuriM.model.EnderecoRepository;
import br.com.trabalho.romulo.api.yuriM.model.PessoaRepositery;
import br.com.trabalho.romulo.api.yuriM.model.Pessoas;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class PessoaController {
    @Autowired
    private PessoaRepositery pessoaRepositery;
    @Autowired
    private EnderecoRepository enderecoRepository;

    @GetMapping("/pessoas")
    public String mostrarForm(Model model) {
        Pessoas pessoa = new Pessoas();
        pessoa.setEndereco(new EnderecoPessoas());
        EnderecoPessoas endereco = new EnderecoPessoas();
        model.addAttribute(pessoa);
        model.addAttribute(endereco);
        return "formPessoa";
    }

    @PostMapping(value = "/pessoas", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String adicionarPessoa(Pessoas pessoa, RedirectAttributes redirectAttributes) {
        EnderecoPessoas enderecoSalvo = enderecoRepository.save(pessoa.getEndereco());
        pessoa.setEndereco(enderecoSalvo);
        pessoaRepositery.save(pessoa);

        redirectAttributes.addFlashAttribute("mensagem", "Pessoa e endereço adicionados com sucesso!");
        return "redirect:/pessoas/listarPessoas";
    }


    @GetMapping("pessoas/listarPessoas")
    public String listarPessoas(Model model) {
        List<Pessoas> pessoas = pessoaRepositery.findAll();
        List<EnderecoPessoas> enderecos = enderecoRepository.findAll();
        model.addAttribute("pessoas", pessoas);
        return "pessoas/listarPessoas";
    }

    @PostMapping("/limparDados")
    public String limparDados(RedirectAttributes redirectAttributes) {
        pessoaRepositery.deleteAll();
        enderecoRepository.deleteAll();

        // Adiciona uma mensagem de confirmação para exibição após o redirecionamento
        redirectAttributes.addFlashAttribute("mensagem", "Todos os dados foram apagados com sucesso!");


        return "redirect:/pessoas";
    }






}
