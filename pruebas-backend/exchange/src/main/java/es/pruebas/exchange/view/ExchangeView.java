package es.pruebas.exchange.view;

import es.pruebas.exchange.model.ExchangeService;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
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
        // Lista de monedas disponibles
        String[] currencies = service.getAvailableCurrencies();

        // Etiquetas y ComboBox para seleccionar monedas
        Label fromLabel = new Label("Selecciona la moneda de origen:");
        ComboBox<String> from = new ComboBox<>(FXCollections.observableArrayList(currencies));
        from.setValue("USD"); // Valor predeterminado
        HBox fromBox = new HBox(10, fromLabel, from); // Agrupar Label y ComboBox

        Label toLabel = new Label("Selecciona la moneda de destino:");
        ComboBox<String> to = new ComboBox<>(FXCollections.observableArrayList(currencies));
        to.setValue("EUR"); // Valor predeterminado
        HBox toBox = new HBox(10, toLabel, to); // Agrupar Label y ComboBox

        // Campo para la cantidad
        Label amountLabel = new Label("Cantidad:");
        TextField amount = new TextField("100");
        HBox amountBox = new HBox(10, amountLabel, amount); // Agrupar Label y TextField

        // Resultado de la conversión
        Label resultLabel = new Label("Resultado:");
        Label result = new Label();

        // Botón para convertir
        Button convert = new Button("Convertir");


        // Acción del botón "Convertir"
        convert.setOnAction(e -> {
            try {
                double res = service.getConversion(
                    from.getValue(), to.getValue(), Double.parseDouble(amount.getText())
                );
                result.setText("Resultado: " + res + " " + to.getValue());
            } catch (Exception ex) {
                result.setText("Error: " + ex.getMessage());
            }
        });

        // Diseño de la ventana
        VBox root = new VBox(15, fromBox, toBox, amountBox, convert, resultLabel, result);
        root.setStyle("-fx-padding: 20; -fx-font-size: 14px;");

        // Configuración de la escena y la ventana
        Scene scene = new Scene(root, 500, 400); // Ventana más grande
        stage.setScene(scene);
        stage.setTitle("Conversor de Monedas Mejorado");
        stage.show();
    }
}

