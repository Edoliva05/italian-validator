# API Validatore Codice Fiscale

Una API REST scritta in Java con Spring Boot per validare i codici fiscali italiani ed estrarre in automatico i dati anagrafici.

Ho sviluppato questo progetto seguendo il Test-Driven Development (TDD), così da assicurarmi che l'algoritmo ministeriale e i vari casi limite fossero coperti per bene fin dall'inizio.

## Funzionalità

- **Validazione Formale:** Controlla tramite regex e tramite l'algoritmo ministeriale se la lettera di controllo finale è corretta.
- **Estrazione Dati:**
  - `Data di Nascita`: Calcolata gestendo il problema del cambio di millennio (distingue chi è nato nel 1900 da chi è nato nel 2000).
  - `Sesso`: Capisce se è uomo o donna in base al giorno di nascita.
  - `Codice Catastale`: Estrae i 4 caratteri del comune di nascita.
- **Gestione Errori:** Se gli passi un codice non valido o inventato, l'API non crasha ma ti restituisce un JSON di errore pulito grazie a delle eccezioni customizzate.

## Stack

- Java 17
- Spring Boot 3
- Maven
- JUnit 5 (per i test)
- Swagger / OpenAPI (per la documentazione)

## Come farlo partire

Se vuoi provarlo in locale, ti basta avere Java e Maven installati.

**Per avviarlo in modalità sviluppo:**
```bash
git clone https://github.com/Edoliva05/italian-validator.git
cd italian-validator
mvn spring-boot:run
```

**Per compilare l'eseguibile (il classico file .jar):**
```bash
mvn clean package
java -jar target/italian-validator-1.0.0.jar
```

## Interfaccia Swagger

Una volta avviato il progetto (di base gira sulla porta 8080), puoi aprire il browser e andare su:

👉 **[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)**

Troverai l'interfaccia grafica già pronta per testare l'endpoint al volo e vedere come funziona senza dover usare Postman o fare richieste da terminale.

## Esempio di utilizzo

Facendo una richiesta `GET` a `http://localhost:8080/api/valida/RSSMRA80A01H501U` il server risponde così:

```json
{
  "codiceFiscale": "RSSMRA80A01H501U",
  "valido": true,
  "dataNascita": "1980-01-01",
  "sesso": "m",
  "comune": "H501"
}
```

Mentre se passi un codice sbagliato:

```json
{
  "codiceFiscale": "RSSMRA80A01H501A",
  "valido": false,
  "errore": "Il codice fiscale fornito non è valido o contiene errori."
}
```