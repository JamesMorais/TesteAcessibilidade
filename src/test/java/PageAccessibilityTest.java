import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.selenium.AxeBuilder;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class PageAccessibilityTest {
    private WebDriver driver;
    //Configurando WebDriver, no caso o utilizado será o ChromeDriver
    @BeforeEach
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.get("");
    }
    @Test
    @DisplayName("Teste de Acessibilidade da página de login")
    public void TesteDeAcessibilidadeTelaDeLogin(){
        AxeBuilder builder = new AxeBuilder();
        Results results = builder.analyze(driver);
    }
    @AfterEach
    public void tearDown(){
        driver.quit();
    }
}
