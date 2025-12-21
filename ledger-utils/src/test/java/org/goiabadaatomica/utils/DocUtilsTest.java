package org.goiabadaatomica.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class DocUtilsTest {

    private final String cpfValidoSemMascara = "52998224725";

    private final String cpfValidoComMascara = "529.982.247-25";

    private final String cpfInvalidoLetras = "asd11w3450.5";

    private final String cpfInvalidoSequenciado = "11111111111";

    private final String cpfInvalidoDigitosVerificadoresErrados = "52998224726";

    private final String cnpjValidoSemMascara = "23835678000190";

    private final String cnpjInvalidoQrdeCaracteresInferior = "238356780001";

    private final String cnpjValidoComMascara = "23.835.678/0001-90";

    private final String cnpjInvalido = "11112165489aa5";

    private final String cnpjInvalidoRepetido = "11111111111111";

    @Test
    public void isNotCpfValido(){
        assertFalse(DocUtils.isCpfValido(cpfInvalidoLetras));
        assertFalse(DocUtils.isCpfValido(cpfInvalidoDigitosVerificadoresErrados));
        assertFalse(DocUtils.isCpfValido(cpfInvalidoSequenciado));
    }

    @Test
    public void isCpfValidoSucess(){
        assertTrue(DocUtils.isCpfValido(cpfValidoComMascara));
        assertTrue(DocUtils.isCpfValido(cpfValidoSemMascara));
    }

    @Test
    public void isCnpjValidoSucess(){
        assertTrue(DocUtils.isCnpjValido(cnpjValidoComMascara));
        assertTrue(DocUtils.isCnpjValido(cnpjValidoSemMascara));
    }

    @Test
    public void isNotCnpjValidoSucess(){
        assertFalse(DocUtils.isCnpjValido(cnpjInvalido));
        assertFalse(DocUtils.isCnpjValido(cnpjInvalidoRepetido));
        assertFalse(DocUtils.isCnpjValido(cnpjInvalidoQrdeCaracteresInferior));
    }
}