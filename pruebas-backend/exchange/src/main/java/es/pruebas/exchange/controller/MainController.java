package es.pruebas.exchange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.pruebas.exchange.model.ConvertResult;
import es.pruebas.exchange.model.ExchangeService;


@RestController
public class MainController {

    
    @Autowired
    private ExchangeService service;


    @GetMapping("/convert")
    public ResponseEntity<?> convert(@RequestParam String from, @RequestParam String to, @RequestParam double amount){
        try{
            return ResponseEntity.ok(new ConvertResult(from, to, amount, service.getConversion(from, to, amount)));
        }
        catch(IllegalArgumentException i){
            if(amount < 0){
                return ResponseEntity.badRequest().body("Error: Cantidad a transformar negativa");    
            }
            return ResponseEntity.badRequest().body("Error: Código de moneda inválido");
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor: " + e.getMessage());
        }
    }
    
}
