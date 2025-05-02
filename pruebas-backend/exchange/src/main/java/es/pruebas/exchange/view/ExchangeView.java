package es.pruebas.exchange.view;

import es.pruebas.exchange.model.ExchangeService;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.FXCollections;

public class ExchangeView {

    private final Stage stage;
    private final ExchangeService service;

    public ExchangeView(Stage stage, ExchangeService service) {
        this.stage = stage;
        this.service = service;
    }

    public void show() {
        String[] currencies = service.getAvailableCurrencies();

        ComboBox<String> from = new ComboBox<>(FXCollections.observableArrayList(currencies));
        from.setValue("USD");

        ComboBox<String> to = new ComboBox<>(FXCollections.observableArrayList(currencies));
        to.setValue("EUR");

        TextField amount = new TextField("100");
        Label result = new Label();
        Button convert = new Button("Convertir");

        convert.setOnAction(e -> {
            try {
                double res = service.getConversion(
                    from.getValue(), to.getValue(), Double.parseDouble(amount.getText())
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

