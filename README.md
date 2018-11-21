# SnackandHack
Smart Country Hackathon
<img src="https://github.com/RikeOne/Norderstedt/blob/master/lutze%20logo.png" width="256" height="256" title="Logo">

## Challenge 3: Auf dem Weg zu Schule

https://github.com/hackerstolz/smart-country-hacks-challenges/tree/master/cities/norderstedt
Prototypische entwicklung eines Wearables für Schulkinder entsprechend der Description.

### Arduino-Code
wird auf Lilypad geladen
Wiring entsprechend der Comments im Code
benötigte Hardware: Flora Neopixel RGB-LEDs, Adafruit Bluefruit LE Modul

### Android Code
- Kotlin, Java
- kommuniziert über BLE mit Lilypad
- Map-Activity stellt Karte dar, Karte wird über Google Maps implementiert und über geoJSON entsprechender Layer drübergelegt
- angedacht ist ein Parser der bereitsgestellten Daten: http://dcat-ap.de/def/politicalGeocoding/regionalKey/010600063063
- siehe Comments im Code
