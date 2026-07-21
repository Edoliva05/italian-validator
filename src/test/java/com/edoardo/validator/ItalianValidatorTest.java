package com.edoardo.validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class ItalianValidatorTest {
    
    @Test
    void shouldReturnTrueWhenCodiceFiscaleIsPerfectlyValid(){
        String cf = "RSSMRA80A01H501U";
        boolean result = ItalianValidator.isValidCodiceFiscale(cf);
        assertTrue(result, "Il metodo dovrebbe restituire true per un CF corretto");
    }

    @Test
    void shouldReturnFalseWhenCodiceFiscaleIsTooShort(){
        String short_cf = "RSSMRA80A01H5";
        boolean result = ItalianValidator.isValidCodiceFiscale(short_cf);
        assertFalse(result, "Il metodo dovrebbe restituire false per un CF troppo corto");
    }

}
