package es.pruebas.exchange.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<ConvertResult> convert(@RequestParam String from, @RequestParam String to, @RequestParam double amount){
        try{
            return ResponseEntity.ok(new ConvertResult(from, to, amount, service.getConversion(from, to, amount)));
        }
        catch(IllegalArgumentException i){
            return ResponseEntity.badRequest().build();
        }
    }
    
}
