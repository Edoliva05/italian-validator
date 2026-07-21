package com.edoardo.validator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Validazione Fiscale", description = "Endpoint per il controllo e l'estrazione dati dal Codice Fiscale Italiano")
public class CodiceFiscaleController {

    @GetMapping("/api/valida/{cf}")
    @Operation(
        summary = "Valida ed estrae i dati anagrafici", 
        description = "Passando un codice fiscale valido di 16 caratteri alfanumerici, restituisce data di nascita, sesso e codice catastale del comune."
    )
    public Map<String, Object> validaCF(

            @Parameter(description = "Il Codice Fiscale da validare (es. RSSMRA80A01H501U)", required = true) 
            @PathVariable String cf) {
            
        Map<String, Object> response = new HashMap<>();
        
        boolean isValid = ItalianValidator.isValidCodiceFiscale(cf);
        
        response.put("codiceFiscale", cf.toUpperCase());
        response.put("valido", isValid);

        if (isValid) {
            response.put("dataNascita", ItalianValidator.extractBirthDate(cf));
            response.put("sesso", ItalianValidator.extractGender(cf));
            response.put("comune", ItalianValidator.extractCodiceCatastale(cf));
        } else {
            response.put("errore", "Il codice fiscale fornito non è valido o contiene errori.");
        }

        return response;
    }
}