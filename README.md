**Viikkotehtävä 5: Sääsovellus**

Sovellus hakee säädataa OpenWeatherMap-rajapinnasta.

Miten sovellus toimii?
Verkkoyhteydet (Retrofit): Retrofit toimii sovelluksen "nettiselaimena". Se hoitaa yhteyden tekemisen sääpalvelimeen ja hakee sieltä tiedot.

JSON-data olioiksi (Gson): Sääpalvelin vastaa JSON-tekstillä. Gson-kirjasto kääntää tämän tekstin automaattisesti

Taustatyö (Coroutines): Sään haku tehdään taustalla "omalla kaistallaan" eikä näyttö jäädy haun ajaksi. UI päivittyy heti, kun vastaus saapuu.

Tilan hallinta (UI-state): ViewModel pitää kirjaa siitä, missä mennään Jetpack Compose tarkkailee tätä tilaa ja piirtää ruudun automaattisesti uusiksi, kun tilanne muuttuu.

Turvallinen API-avain: API-avain on piilotettu local.properties-tiedostoon, josta se siirtyy BuildConfig-olion kautta koodiin. Näin avain ei vuoda
