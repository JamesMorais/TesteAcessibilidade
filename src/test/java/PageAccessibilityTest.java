
import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;
import com.deque.html.axecore.selenium.AxeBuilder;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.ViolationsReporter;

import java.time.Duration;
import java.util.List;

public class PageAccessibilityTest{
    private WebDriver driver;

    //Configurando WebDriver, no caso o utilizado será o ChromeDriver

    @BeforeEach
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    }
    // Métodos auxiliares
    private Results analisarAcessibilidade(String url) {
        AxeBuilder builder = new AxeBuilder();
        driver.get(url);
        return builder.analyze(driver);
    }

    private void verificarViolacoes(Results results) {
        if (results.violationFree()) {
            Assertions.assertTrue(true, "Sem violações encontradas!");
        } else {
            String customReport = ViolationsReporter.buildCustomReport(results);
            System.out.println("Relatório: " + customReport);
            Assertions.fail("Foram encontradas violações!");
        }
    }

    @Test
    @DisplayName("Teste de Acessibilidade da HomePage")
    public void TesteDeAcessibilidadeDaHomePage(){
        Results results = analisarAcessibilidade("https://lattes.cnpq.br");
        verificarViolacoes(results);
    }
    @Test
    @DisplayName("Teste de acessibilidade da página de busca de curriculo")
    public void TesteDeAcessibilidadeTelaBuscaCurriculo(){
        Results results = analisarAcessibilidade("https://buscatextual.cnpq.br/buscatextual/busca.do?metodo=apresentar");
        verificarViolacoes(results);
    }
    @AfterEach
    public void tearDown(){
        driver.quit();
    }
}
