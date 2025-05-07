package es.pruebas.exchange.view;

import javafx.application.Application;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import es.pruebas.exchange.ExchangeApplication;

public class MainApp {

    public static void main(String[] args) {
        // Lanza JavaFX
        Application.launch(JavaFxApp.class, args);
    }

    public static ConfigurableApplicationContext startSpringApp() {
        return new SpringApplicationBuilder(ExchangeApplication.class)
                .headless(false)
                .run();
    }
}