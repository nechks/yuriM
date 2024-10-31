package br.com.trabalho.romulo.api.yuriM.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name ="Pessoa")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Pessoas {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String idade;


}


