# Tetris

Dette prosjektet er en implementering av det klassiske Tetris-spillet skrevet i Java. Tetris er et morsomt og utfordrende puslespill der spilleren må plassere fallende brikker for å fylle ut horisontale linjer på brettet.

## Om prosjektet

Dette Tetris-prosjektet er utviklet som en oppgave i INF101 VÅR 2024 for å demonstrere min forståelse av Java-programmering og objektorienterte prinsipper. Spillet er bygget ved hjelp av Java Swing for GUI-delen, og følger designprinsippet om Model-View-Controller (MVC) for å organisere koden på en strukturert måte.

## Arkitektur

Prosjektet er delt inn i tre hovedpakker:

- **Model**: Inneholder klassene som representerer spillets logikk og tilstand.
- **View**: Ansvarlig for å tegne spillet grafisk på skjermen.
- **Controller**: Håndterer brukerens input og oppdaterer modellen.

## Hovedklasser

- **TetrisModel**: Representerer tilstanden til Tetris-spillet, inkludert brettet og den fallende brikken.
- **TetrisView**: Tegner Tetris-modellen på skjermen.
- **TetrisController**: Styrer interaksjonen mellom modellen og visningen basert på brukerens input.

## Komme i gang

For å kjøre prosjektet, trenger du å ha Java installert på datamaskinen din. Du kan laste ned Java [her](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) (versjon 11 eller nyere anbefales). 

Du trenger også Maven, som kan lastes ned [her](https://maven.apache.org/download.cgi).

Klon prosjektet og naviger til prosjektmappen, deretter kan du bygge og kjøre det ved å bruke følgende kommandoer:

```bash
mvn clean install
mvn exec:java -Dexec.mainClass="your.package.name.TetrisMain"



 ---

Denne guiden er utviklet av Torstein Strømme (c) 2023, og er en adapsjon av David Kosbie sin Tetris-tutorial for Python [https://www.cs.cmu.edu/~112/notes/notes-tetris/index.html](https://www.cs.cmu.edu/~112/notes/notes-tetris/index.html).
