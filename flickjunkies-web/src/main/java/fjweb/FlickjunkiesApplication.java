package fjweb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(scanBasePackages = "com.fjapi")
@EnableJpaAuditing
public class FlickjunkiesApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(FlickjunkiesApplication.class, args);
    }
}
