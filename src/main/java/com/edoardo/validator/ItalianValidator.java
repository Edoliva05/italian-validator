package com.edoardo.validator;

public class ItalianValidator {
    
    public static boolean isValidCodiceFiscale(String codiceFiscale){

        //Se è null
        if(codiceFiscale == null) return false;
        
        String cf = codiceFiscale.trim().toUpperCase();

        //6 lettere, 2 numeri, 1 lettera, 2 numeri, 1 lettera, 3 numeri, 1 lettera
        String regex = "^[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$";

        //restituisce true se ripetta il pattern
        return cf.matches(regex);

    }
}
