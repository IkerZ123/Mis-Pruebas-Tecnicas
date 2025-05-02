package es.pruebas.exchange.model;



import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ExchangeService {

    private Map<String, Double> exchangeRates;


    private final RestTemplate restTemplate = new RestTemplate();

    private final String API_KEY = "";



    public ExchangeService() {

        if(API_KEY == null || API_KEY.isBlank()){
            exchangeRates = new HashMap<>();
            exchangeRates.put("USD", 1.0);  // Base currency
            exchangeRates.put("EUR", 0.9);  // Euro
            exchangeRates.put("GBP", 0.78); // British Pound
            exchangeRates.put("AUD", 1.4);  // Australian Dollar
            exchangeRates.put("CAD", 1.35); // Canadian Dollar
            exchangeRates.put("JPY", 110.0); // Japanese Yen
            exchangeRates.put("CNY", 6.9);   // Chinese Yuan
            exchangeRates.put("INR", 74.0);  // Indian Rupee
            exchangeRates.put("MXN", 18.0);  // Mexican Peso
            exchangeRates.put("BRL", 5.0);   // Brazilian Real
            exchangeRates.put("ZAR", 18.5);  // South African Rand
            exchangeRates.put("CHF", 0.92);  // Swiss Franc
            exchangeRates.put("NZD", 1.5);   // New Zealand Dollar
            exchangeRates.put("SEK", 10.5);  // Swedish Krona
            exchangeRates.put("NOK", 10.2);  // Norwegian Krone
            exchangeRates.put("RUB", 75.0);  // Russian Ruble
            exchangeRates.put("KRW", 1200.0); // South Korean Won
            exchangeRates.put("SGD", 1.35);  // Singapore Dollar
            exchangeRates.put("HKD", 7.8);   // Hong Kong Dollar
            return;
        }
        String url = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/USD";

        try {
            ExchangeResponse response = restTemplate.getForObject(url, ExchangeResponse.class);
            if (response != null && "success".equalsIgnoreCase(response.getResult())) {
                this.exchangeRates = response.getConversion_rates();
                System.out.println("Tasas de cambio cargadas correctamente.");
            } else {
                System.err.println("Error al obtener tasas de cambio: " + (response != null ? response.getResult() : "respuesta nula"));
            }
        } catch (Exception e) {
            System.err.println("Excepción al llamar a la API: " + e.getMessage());
        }
    }


    public double getConversion(String from, String to, double amount){
        Double valueFrom = exchangeRates.get(from);
        Double valueTo = exchangeRates.get(to);

        if(valueFrom == null || valueTo == null) throw new IllegalArgumentException();

        return valueTo * amount / valueFrom;
    }
    

    public String[] getAvailableCurrencies(){
        return exchangeRates.keySet().toArray(new String[0]);
    }
}
