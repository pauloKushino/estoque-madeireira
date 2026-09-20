package com.pi.estoquemadeireira.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Valida CPF (11 digitos) ou CNPJ (14 digitos) pelos digitos verificadores.
 * Aceita valores com ou sem mascara; campo vazio/nulo e considerado valido
 * (a obrigatoriedade, quando necessaria, fica a cargo de @NotBlank).
 */
public class CpfCnpjValidator implements ConstraintValidator<CpfCnpj, String> {

    @Override
    public boolean isValid(String valor, ConstraintValidatorContext context) {
        if (valor == null || valor.isBlank()) {
            return true;
        }
        String digitos = valor.replaceAll("\\D", "");
        if (todosDigitosIguais(digitos)) {
            return false;
        }
        return switch (digitos.length()) {
            case 11 -> validarCpf(digitos);
            case 14 -> validarCnpj(digitos);
            default -> false;
        };
    }

    private boolean todosDigitosIguais(String digitos) {
        return digitos.chars().allMatch(c -> c == digitos.charAt(0));
    }

    // CPF: pesos crescem de 2 ate 10 (1o DV) e 2 ate 11 (2o DV), sem reiniciar.
    private boolean validarCpf(String digitos) {
        return confereDigito(digitos, 9, false) && confereDigito(digitos, 10, false);
    }

    // CNPJ: pesos ciclam de 2 a 9.
    private boolean validarCnpj(String digitos) {
        return confereDigito(digitos, 12, true) && confereDigito(digitos, 13, true);
    }

    /**
     * Confere o digito verificador na posicao informada: soma os digitos
     * anteriores da direita para a esquerda com pesos iniciando em 2
     * (reiniciando em 2 apos o 9 quando ciclaPesos), e aplica modulo 11.
     */
    private boolean confereDigito(String digitos, int posicao, boolean ciclaPesos) {
        int soma = 0;
        int peso = 2;
        for (int i = posicao - 1; i >= 0; i--) {
            soma += Character.getNumericValue(digitos.charAt(i)) * peso;
            peso = ciclaPesos && peso == 9 ? 2 : peso + 1;
        }
        int digitoEsperado = soma % 11 < 2 ? 0 : 11 - (soma % 11);
        return Character.getNumericValue(digitos.charAt(posicao)) == digitoEsperado;
    }
}
