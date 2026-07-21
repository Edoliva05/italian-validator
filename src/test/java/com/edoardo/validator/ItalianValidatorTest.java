package com.edoardo.validator;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    void shouldReturnFalseWhenCodiceFiscaleIsTooLong(){
        String long_cf = "RSSMRA80A01H501UUU";
        boolean result = ItalianValidator.isValidCodiceFiscale(long_cf);
        assertFalse(result, "Il metodo dovrebbe restituire false per un CF troppo lungo");
    }

    @Test
    void shouldReturnFalseWhenCodiceFiscaleContainsSpecialCharacters(){
        String invalid_cf = "RSSMRA80@01H501U";
        boolean result = ItalianValidator.isValidCodiceFiscale(invalid_cf);
        assertFalse(result, "Il metodo dovrebbe restituire false se un CF contiene caratteri speciali");
    }

    @Test
    void shouldReturnTrueWhenCodiceFiscaleHasPaddingSpaces(){
        String cf = "  RSSMRA80A01H501U ";
        boolean result = ItalianValidator.isValidCodiceFiscale(cf);
        assertTrue(result, "Il metodo dovrebbe restituire true anche se un CF contiene spazi ai bordi");
    }

    @Test
    void shouldReturnFalseWhenTheControlCharacterIsWrong(){
        String cf = "RSSMRA80A01H501A";
        boolean result = ItalianValidator.isValidCodiceFiscale(cf);
        assertFalse(result, "Il metodo dovrebbe restituire false se il carattere di controllo finale è sbagliato");
    }

    @Test
    void shouldExtractCorrectDateFromCodiceFiscale(){
        String cf = "RSSMRA80A01H501U";
        LocalDate dataAttesa = LocalDate.of(1980, 1, 1);
        LocalDate dataEstratta = ItalianValidator.extractBirthDate(cf);

        assertEquals(dataAttesa, dataEstratta, "Il metodo dovrebbe restituire il 1 Gennaio 1980");

    }

}
