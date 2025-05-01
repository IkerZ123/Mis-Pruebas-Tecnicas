package es.pruebas.exchange.model;

import java.util.Map;

import lombok.Getter;


@Getter
public class ExchangeResponse {
    

    private String result;

    private Map<String, Double> conversion_rates;


}
