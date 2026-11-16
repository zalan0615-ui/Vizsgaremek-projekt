# FORGE Budapest – átlátható, szerkeszthető forráskód

## Hol találom a kódot?

- **A HTML fájlokban** van az egyes oldalak tartalma és elrendezése. A fix bérletkártyákat és foglalkozáskártyákat is ide helyeztem át.
- A **saját `style.css`** az adott oldal egyedi elrendezését és kinézetét szabályozza.
- A **`mindegyik oldalhoz/General.css`** fájlban találod az összes oldalon azonos színeket, betűtípusokat, menüt, láblécet, gombokat, űrlapmezőket és a közös reszponzív szabályokat.
- A **`mindegyik oldalhoz/mindegyikhez.js`** fájl állítja össze a közös menüt és láblécet, és tartalmazza a demó foglalási adatkezelést.
- A **saját `script.js`** az adott oldal egyedi működését biztosítja.

A kódot kifejtettem és behúzásokkal tagoltam. A sorok számának növekedése főleg formázásból ered, nem extra funkciókból. Egyes dinamikus tartalmak (például az órarendi foglalások, admin felhasználók) továbbra is JS-ből jelennek meg, mert azok adatok alapján változnak.

Indítás: VS Code > Live Server > `Kezdőlap/Homepage.html`.

**Figyelem:** az autentikáció, fizetés és foglalás demó. A projekt nem tartalmaz szervert vagy adatbázist. A vizuális megjelenést böngészőben és Figma mellett érdemes még ellenőrizni.
