Konditerem weboldal

Szoftverfejlesztés és -tesztelés vizsgaremek vizsgarész
 A vizsgázóknak minimum 2, maximum 3 fős fejlesztői csapatot alkotva kell a vizsgát megelőzően egy komplex szoftveralkalmazást lefejleszteniük. A szoftveralkalmazásnak az alábbi elvárásoknak kell megfelelni:
Életszerű, valódi problémára nyújt megoldást.
Adattárolási és -kezelési funkciókat is megvalósít.
RESTful architektúrának megfelelő szerver és kliens oldali komponenseket egyaránt tartalmaz.
A kliens oldali komponens vagy komponensek egyaránt alkalmasak asztali és mobil eszközökön történő használatra. Mobil eszközre kifejlesztett kliens esetén natív mobil alkalmazás, vagy azzal hozzávetőlegesen megegyező felhasználói élményt nyújtó webes kliens egyaránt alkalmazható. Asztali eszközökre fejlesztett kliens oldali komponensnél mindenképpen szükséges webes megvalósítás is, de emellett opcionálisan natív, asztali alkalmazás is a csomag része lehet. (pl. A felhasználóknak szánt interfész webes megjelenítést használ, míg az adminisztrációs felület natív asztali alkalmazásként készül el).
A forráskódnak a tiszta kód elveinek megfelelően kell készülnie.
A szoftver célját, komponenseinek technikai leírását, működésének műszaki feltételeit és használatának rövid bemutatását tartalmazó dokumentáció is része a csomagnak.


Csoportagok:
Varga Zalán (frontend, Adatbázis), Grain Martin (Backend)

Keretrendszer:
Frontend: react
Backend:  Node.js

Téma:
Kond weboldali
 
Projektvezető:
Varga Zalán

Milyen problémát old meg?
Kondi weboldal:
Nagyváros(Budapesti konditerem) ezért az ott lakóknak fogja megkönnyíteni az életét.
-Több szolgáltatás van az épületben(kozmetika, szauna, szolárium, masszázs)
-Több személyi edző dolgozik a kondiban, ezért könnyen lehet edzéstervet előállítatni, edzéseket lefolytatni.
-A személyi edzőktől lehet kérni edzéstervet, étrend tervet.
-Könnyen elérhető, városközpontban helyezkedik el, nagy parkoló áll rendelkezésre.
-Nagy a tér, sok a kondikellék(gépek, súlyzók, súlyok)

Mit tercezünk: 

-Egy bejelntkező felületet. 

Hogyan:
(A bejelentkezést egy biztonságos, REST API-ra épülő JWT (JSON Web Token) alapú hitelesítéssel valósítom meg, ahol a jelszavak titkosítva tárolódnak az adatbázisban. A sikeres azonosítás után a kliens oldali React alkalmazás eltárolja a tokent, így biztosítva a védett útvonalakhoz való hozzáférést és a gördülékeny felhasználói élményt.Magyarázd el, mi az a Rest Api.Hogyan csinálná a react? Egyszerüen, hogy egy kezdö is megértse.)

3 különbözö bérlet:(
-Napijegy
-Havi tagság
-Prémium+
)

 

