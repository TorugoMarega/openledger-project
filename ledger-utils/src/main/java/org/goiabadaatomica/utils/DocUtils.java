package org.goiabadaatomica.utils;
import org.apache.log4j.Logger;
import org.apache.commons.lang3.StringUtils;

public final class DocUtils {

    private static final Logger logger = Logger.getLogger(DocUtils.class);

    private static final int CPF_SIZE = 11;
    private static final int CNPJ_SIZE = 14;

    private DocUtils() {
        throw new UnsupportedOperationException("Esta é uma classe utilitária e não pode ser instanciada");
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

    public static boolean isCpfValido(String cpf){
        String cpfLimpo = sanitizar(cpf);
        if (cpfLimpo == null || isSequenciaRepetida(cpfLimpo) || !isTamanhoValido(cpfLimpo, CPF_SIZE)) {
            return false;
        }
        return true;
    }

    public static boolean isCnpjValido(String cnpj){
        String cnpjLimpo = sanitizar(cnpj);
        if (cnpjLimpo == null || isSequenciaRepetida(cnpjLimpo) || !isTamanhoValido(cnpjLimpo, CNPJ_SIZE)) {
            return false;
        }
        return true;
    }

    static boolean isTamanhoValido(String cpfCnpjLimpo, int validLength){
        if(cpfCnpjLimpo.length()==validLength){
            return true;
        }
        String message = String.format("Número incorreto de caracteres: %d %s %d ", cpfCnpjLimpo.length(), "é diferente de ", validLength);
        logger.warn(message);
        return false;
    }
}