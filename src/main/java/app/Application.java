package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import controller.OptimizerController;
import service.WorkforceOptimizer;

@SpringBootApplication
@ComponentScan(basePackageClasses = {OptimizerController.class, WorkforceOptimizer.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}