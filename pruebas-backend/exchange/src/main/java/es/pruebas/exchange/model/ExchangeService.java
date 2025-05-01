package es.pruebas.exchange.model;



import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ExchangeService {

    private Map<String, Double> exchangeRates;


    private final RestTemplate restTemplate = new RestTemplate();

    private final String API_KEY = "";



    public ExchangeService() {
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
    
}
