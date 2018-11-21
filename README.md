# SnackandHack
Smart Country Hackathon

## Challenge 3: Auf dem Weg zu Schule

Prototypische entwicklung eines Wearables für Schulkinder entsprechend der Description

### Arduino-Code
wird auf Lilypad geladen
Wiring entsprechend der Comments
benötigte Hardware: Flora Neopixel RGB-LEDs, Adafruit Bluefruit LE Modul

### Android Code
- Kotlin, Java
- kommuniziert über BLE mit Lilypad
- Map-Activity stellt Karte dar, Karte wird über Google Maps implementiert und über geoJSON entsprechender Layer drübergelegt
- angedacht ist ein Parser der bereitsgestellten Daten: http://dcat-ap.de/def/politicalGeocoding/regionalKey/010600063063
