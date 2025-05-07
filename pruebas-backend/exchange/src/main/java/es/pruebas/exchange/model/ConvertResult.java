package es.pruebas.exchange.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ConvertResult {

    private String from;
    private String to;
    private double originalAmount;
    private double convertedAmount;
    
}
