package com.edoardo.validator;

public class ItalianValidator {

    private static final int[] VALORI_DISPARI = {
        1, 0, 5, 7, 9, 13, 15, 17, 19, 21, 2, 4, 18, 20, 11, 3, 6, 8, 12, 14, 16, 10, 22, 25, 24, 23
    };
    
    public static boolean isValidCodiceFiscale(String codiceFiscale){

        //Se è null
        if(codiceFiscale == null) return false;
        
        String cf = codiceFiscale.trim().toUpperCase();

        //6 lettere, 2 numeri, 1 lettera, 2 numeri, 1 lettera, 3 numeri, 1 lettera
        String regex = "^[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$";

        //restituisce true se ripetta il pattern
        if(!cf.matches(regex)){
            return false;
        }

        //Algoritmo per validare il carattere finale di controllo
        int somma = 0;

        for(int i=0; i<15; i++){
            char c = cf.charAt(i);

            int charValue;
            if (c >= '0' && c <= '9') {
                charValue = c - '0';
            } else {
                charValue = c - 'A';
            }

            if(i%2 == 0){
                somma += VALORI_DISPARI[charValue];
            }else{
                somma += charValue;
            }
        }

        int r = somma % 26;

        char controlCharacter = (char) (r + 'A');
        char effectiveControlCharacter = cf.charAt(15);

        return controlCharacter == effectiveControlCharacter;

    }
}
