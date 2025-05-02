package es.pruebas.exchange.view;

import es.pruebas.exchange.model.ExchangeService;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ExchangeView {

    private final Stage stage;
    private final ExchangeService service;

    public ExchangeView(Stage stage, ExchangeService service) {
        this.stage = stage;
        this.service = service;
    }

    public void show() {
        TextField from = new TextField("USD");
        TextField to = new TextField("EUR");
        TextField amount = new TextField("100");
        Label result = new Label();
        Button convert = new Button("Convertir");

        convert.setOnAction(e -> {
            try {
                double res = service.getConversion(
                    from.getText(), to.getText(), Double.parseDouble(amount.getText())
                );
                result.setText("Resultado: " + res);
            } catch (Exception ex) {
                result.setText("Error: " + ex.getMessage());
            }
        });

        VBox root = new VBox(10, from, to, amount, convert, result);
        root.setStyle("-fx-padding: 20");
        stage.setScene(new Scene(root));
        stage.setTitle("Conversor de Monedas");
        stage.show();
    }
}

