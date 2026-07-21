package com.edoardo.validator;

import java.time.LocalDate;

public class ItalianValidator {

    private static final int[] VALORI_DISPARI = {
        1, 0, 5, 7, 9, 13, 15, 17, 19, 21, 2, 4, 18, 20, 11, 3, 6, 8, 12, 14, 16, 10, 22, 25, 24, 23
    };
    
    //Metodo che controlla, dato un codice fiscale se sia valido o meno, restituisce un booleano
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

    //Metodo che dato un codice fiscale fa il return della data di nascita del soggetto
    public static LocalDate extractBirthDate(String codiceFiscale){

        if (!isValidCodiceFiscale(codiceFiscale)) {
            throw new InvalidCodiceFiscaleException("Impossibile estrarre dati: il Codice Fiscale " + codiceFiscale + " non è valido.");
        }

        //Estraiamo i dati per poi passare ai controlli
        String yearString = codiceFiscale.substring(6,8);
        char monthString = (codiceFiscale.charAt(8));
        String dayString = codiceFiscale.substring(9,11);

        //DAY
        int num_day = Integer.parseInt(dayString);
        //caso donna
        if(num_day > 40){
            num_day -= 40;
        }

        //MONTH
        int num_month = 0;
        switch(monthString){
            case 'A':
                num_month = 1;
                break;
            case 'B':
                num_month = 2;
                break;
            case 'C':
                num_month = 3;
                break;
            case 'D':
                num_month = 4;
                break;
            case 'E':
                num_month = 5;
                break;
            case 'H':
                num_month = 6;
                break;
            case 'L':
                num_month = 7;
                break;
            case 'M':
                num_month = 8;
                break;
            case 'P':
                num_month = 9;
                break;
            case 'R':
                num_month = 10;
                break;
            case 'S':
                num_month = 11;
                break;
            case 'T':
                num_month = 12;
                break;
        }

        //YEAR
        int num_year = Integer.parseInt(yearString);
        String year;
        if(num_year > 30){
            year = "19" + yearString;
        }else{
            year = "20" + yearString;
        }

        num_year = Integer.parseInt(year);

        return LocalDate.of(num_year, num_month, num_day);
        
    }

    //Metodo che dato un codice fiscale fa il return del genere del soggetto
    public static String extractGender(String codiceFiscale){
        if (!isValidCodiceFiscale(codiceFiscale)) {
            throw new InvalidCodiceFiscaleException("Impossibile estrarre dati: il Codice Fiscale " + codiceFiscale + " non è valido.");
        }

        String dayString = codiceFiscale.substring(9,11);
        int num_day = Integer.parseInt(dayString);

        if(num_day > 40){
            return "f";
        }

        return "m";

    }

    //Metodo che dato un codice fiscale fa il return del codice catastale del soggetto
    public static String extractCodiceCatastale(String codiceFiscale){
        if (!isValidCodiceFiscale(codiceFiscale)) {
            throw new InvalidCodiceFiscaleException("Impossibile estrarre dati: il Codice Fiscale " + codiceFiscale + " non è valido.");
        }

        return codiceFiscale.substring(11,15);

    }
}
