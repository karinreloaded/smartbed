package at.fhv.uct.adaptaal.smartbed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({
        "at.fhv.uct.adaptaal.smartbed.domain",
        "at.fhv.uct.adaptaal.smartbed.persistence",
        "at.fhv.uct.adaptaal.smartbed.controllers",
        "at.fhv.uct.adaptaal.smartbed.services"
})
public class SmartbedApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmartbedApplication.class, args);
    }
}
