package org.goiabadaatomica.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class DocUtilsTest {

    private final String cpfInvalido = "asd11w3450.5";

    private final String cnpjInvalido = "11112165489aa5";

    private final String cpfValidoSemMascara = "11112147608";

    private final String cnpjValidoSemMascara = "23835678000190";

    private final String cpfValidoComMascara = "111.121.476-08";

    private final String cnpjValidoComMascara = "23.835.678/0001-90";

    @Test
    public void isNotCpfValido(){
        assertFalse(DocUtils.isCnpjValido(cpfInvalido));
    }
    @Test()
    public void isNotCnpjValido(){
        assertFalse(DocUtils.isCnpjValido(cnpjInvalido));
    }
    @Test
    public void isCpfValidoSemMascaraSucess(){
        assertTrue(DocUtils.isCpfValido(cpfValidoSemMascara));
    }
    @Test
    public void isCnpjValidoSemMascaraSucess(){
        assertTrue(DocUtils.isCnpjValido(cnpjValidoSemMascara));
    }
    @Test
    public void isCpfValidoComMascaraSucess(){
        assertTrue(DocUtils.isCpfValido(cpfValidoComMascara));
    }
    @Test
    public void isCnpjValidoComMascaraSucess(){
        assertTrue(DocUtils.isCnpjValido(cnpjValidoComMascara));
    }
}