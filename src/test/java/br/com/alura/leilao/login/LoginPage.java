package br.com.alura.leilao.login;

import br.com.alura.leilao.PageObject;
import br.com.alura.leilao.leiloes.LeiloesPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;


public class LoginPage extends PageObject {

    public static final String URL_LOGIN = "http://localhost:8080/login";

    public LoginPage(){
        super(null);
        browser.navigate().to(URL_LOGIN);
    }

   public void preencheFormularioDeLogin(String usuario,String senha){
       browser.findElement(By.id("username")).sendKeys(usuario);
       browser.findElement(By.id("password")).sendKeys(senha);
   }

   public LeiloesPage efetuaLogin(){
       browser.findElement(By.id("login-form")).submit();

       return new LeiloesPage(browser);
   }

    public boolean validaPaginaDeLogin(){
        return browser.getCurrentUrl().equals(URL_LOGIN);
    }

    public boolean validaPaginaDeLoginInvaido(){
        return browser.getCurrentUrl().equals(URL_LOGIN + "?error");
    }

    public String validaUsuarioLogado(){
        try{
            return browser.findElement(By.id("usuario-logado")).getText();
        }catch (NoSuchElementException e) {
            return null;
        }
    }

    public void navegaParaPaginasDeLances(){
        browser.navigate().to("http://localhost:8080/leiloes/2");
    }

    public boolean contemTexto(String texto){
        return browser.getPageSource().contains(texto);
    }
}
