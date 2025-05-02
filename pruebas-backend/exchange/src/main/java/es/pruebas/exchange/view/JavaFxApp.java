package es.pruebas.exchange.view;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.ConfigurableApplicationContext;

import es.pruebas.exchange.model.ExchangeService;

public class JavaFxApp extends Application {

    private ConfigurableApplicationContext context;

    @Override
    public void init() {
        context = MainApp.startSpringApp();
    }

    @Override
    public void start(Stage primaryStage) {
        ExchangeService service = context.getBean(ExchangeService.class);
        new ExchangeView(primaryStage, service).show();
    }

    @Override
    public void stop() {
        context.close();
    }
}
