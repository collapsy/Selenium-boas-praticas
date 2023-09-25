package br.com.alura.leilao.login;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class LoginTest {

    private LoginPage paginaDeLogin;


    @BeforeEach
    public void beforeEach(){
        this.paginaDeLogin = new LoginPage();

    }

    @AfterEach
    public void afterEach(){
        this.paginaDeLogin.fechar();
    }

    @Test
    public void deveriaEfeturarLoginComDadosValidos(){
        paginaDeLogin.preencheFormularioDeLogin("fulano","pass");
        paginaDeLogin.efetuaLogin();

        Assert.assertFalse(paginaDeLogin.validaPaginaDeLogin());
        Assert.assertEquals("fulano",paginaDeLogin.validaUsuarioLogado());
    }

    @Test
    public void naoDeveriaLogarComDadosInvalidos(){
        paginaDeLogin.preencheFormularioDeLogin("invalido","gdgdfgdvxcvb");
        paginaDeLogin.efetuaLogin();

        Assert.assertTrue(paginaDeLogin.validaPaginaDeLoginInvaido());
        Assert.assertNull("fulano",paginaDeLogin.validaUsuarioLogado() );
        Assert.assertTrue(paginaDeLogin.contemTexto("Usuário e senha inválidos."));

    }

    @Test
    public void naoDeveriaAcessarPaginaRestritaSemEstarLogado(){
        paginaDeLogin.navegaParaPaginasDeLances();

        Assert.assertTrue(paginaDeLogin.validaPaginaDeLogin());
        Assert.assertFalse(paginaDeLogin.contemTexto("Dados do Leilão"));
    }
}
