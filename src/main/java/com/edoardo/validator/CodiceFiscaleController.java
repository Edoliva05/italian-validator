package com.edoardo.validator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CodiceFiscaleController {

    @GetMapping("/api/valida/{cf}")
    public Map<String, Object> validaCF(@PathVariable String cf) {
        
        // Map che SpringBoot trasformerà in un JSON
        Map<String, Object> response = new HashMap<>();
        
        boolean isValid = ItalianValidator.isValidCodiceFiscale(cf);
        
        response.put("codiceFiscale", cf.toUpperCase());
        response.put("valido", isValid);

        // Se è valido, aggiungiamo tutti i dati extra
        if (isValid) {
            response.put("dataNascita", ItalianValidator.extractBirthDate(cf));
            response.put("sesso", ItalianValidator.extractGender(cf));
            response.put("comune", ItalianValidator.extractCodiceCatastale(cf));
        } else {
            // Se non è valido, mandiamo un messaggio di errore 
            response.put("errore", "Il codice fiscale fornito non è valido o contiene errori");
        }

        return response;
    }
}