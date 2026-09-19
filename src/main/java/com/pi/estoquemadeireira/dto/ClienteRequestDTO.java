package com.pi.estoquemadeireira.dto;

import com.pi.estoquemadeireira.validation.CpfCnpj;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClienteRequestDTO(

        @NotBlank(message = "nome e obrigatorio")
        @Size(max = 120, message = "nome deve ter no maximo 120 caracteres")
        String nome,

        @CpfCnpj
        @Size(max = 18, message = "cpfCnpj deve ter no maximo 18 caracteres")
        String cpfCnpj,

        @Size(max = 20, message = "telefone deve ter no maximo 20 caracteres")
        String telefone,

        @Email(message = "email invalido")
        @Size(max = 120, message = "email deve ter no maximo 120 caracteres")
        String email,

        @Pattern(regexp = "\\d{5}-?\\d{3}", message = "cep invalido: use o formato 00000-000")
        String cep,

        @Size(max = 150, message = "logradouro deve ter no maximo 150 caracteres")
        String logradouro,

        @Size(max = 100, message = "bairro deve ter no maximo 100 caracteres")
        String bairro,

        @Size(max = 100, message = "cidade deve ter no maximo 100 caracteres")
        String cidade,

        @Pattern(regexp = "[A-Za-z]{2}", message = "uf deve ter exatamente 2 letras")
        String uf
) {
}
