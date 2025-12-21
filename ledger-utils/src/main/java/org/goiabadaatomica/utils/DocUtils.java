package org.goiabadaatomica.utils;

import org.apache.log4j.Logger;
import org.apache.commons.lang3.StringUtils;

public final class DocUtils {

    private static final Logger logger = Logger.getLogger(DocUtils.class);

    private static final int CPF_SIZE = 11;
    private static final int CNPJ_SIZE = 14;
    
    private static final int[] PESOS_CNPJ_1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    private static final int[] PESOS_CNPJ_2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    public static boolean isCpfValido(String cpf) {
        String cpfLimpo = sanitizar(cpf);

        if (cpfLimpo == null || isSequenciaRepetida(cpfLimpo) || !isTamanhoValido(cpfLimpo, CPF_SIZE)) {
            return false;
        }

        return isDigitoVerificadorValido(cpfLimpo, 9) && isDigitoVerificadorValido(cpfLimpo, 10);
    }

    public static boolean isCnpjValido(String cnpj) {
        String cnpjLimpo = sanitizar(cnpj);

        if (cnpjLimpo == null || isSequenciaRepetida(cnpjLimpo) || !isTamanhoValido(cnpjLimpo, CNPJ_SIZE)) {
            return false;
        }

        if (!validarDigitoCnpj(cnpjLimpo, PESOS_CNPJ_1, 12)) {
            return false;
        }

        return validarDigitoCnpj(cnpjLimpo, PESOS_CNPJ_2, 13);
    }

    private static boolean validarDigitoCnpj(String cnpjLimpo, int[] pesos, int posicaoDoDigito) {
        int soma = 0;
        
        for (int i = 0; i < pesos.length; i++) {
            int digito = cnpjLimpo.charAt(i) - '0'; //conversao de ASCII para Inteiro
            soma += digito * pesos[i];
        }

        int resto = soma % 11;
        int digitoCalculado = (resto < 2) ? 0 : 11 - resto;

        int digitoReal = cnpjLimpo.charAt(posicaoDoDigito) - '0'; //conversao de ASCII para Inteiro

        return digitoCalculado == digitoReal;
    }

    private static String sanitizar(String valor) {
        if (StringUtils.isBlank(valor)) {
            return null;
        }
        return valor.replaceAll("\\D", "");
    }

    private static boolean isSequenciaRepetida(String valor) {
        return valor.matches("(\\d)\\1+");
    }

    private static boolean isTamanhoValido(String cpfCnpjLimpo, int validLength) {
        if (cpfCnpjLimpo.length() == validLength) {
            return true;
        }
        logger.warn(String.format("Tamanho invalido: %d (Esperado: %d)", cpfCnpjLimpo.length(), validLength));
        return false;
    }

    private static boolean isDigitoVerificadorValido(String cpfLimpo, int posicao) {
        int soma = calculaSomaPonderada(cpfLimpo, posicao);
        int resto = soma % CPF_SIZE;

        int digitoCalculado = (resto < 2) ? 0 : CPF_SIZE - resto;
        int digitoReal = Character.getNumericValue(cpfLimpo.charAt(posicao));

        return digitoCalculado == digitoReal;
    }

    private static int calculaSomaPonderada(String cpfLimpo, int posicao) {
        int peso = posicao + 1;
        int soma = 0;

        for (int i = 0; i < posicao; i++) {
            int digito = Character.getNumericValue(cpfLimpo.charAt(i));
            soma += digito * peso;
            peso--;
        }
        return soma;
    }
}