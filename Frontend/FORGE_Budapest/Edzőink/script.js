
/* ============================================================
   EDZŐINK OLDAL - FORGE BUDAPEST
============================================================ */

import { apiGet } from "../mindegyik oldalhoz/osszekotes.js";

/* ----------------------------------------------------------
   1. EDZŐK ELÉRHETŐSÉGEI
---------------------------------------------------------- */

/** Edzők elérhetőségeit megjelenítő Bootstrap modális ablak. */
const coachDetails = {
  'Kovács Dávid': { phone: '+36 30 555 0218', email: 'david@forgebudapest.hu' },
  'Tóth Eszter': { phone: '+36 30 555 0218', email: 'eszter.toth@forgebudapest.hu' },
  'Nagy Márk': { phone: '+36 30 555 0218', email: 'mark@forgebudapest.hu' }
};

/* ----------------------------------------------------------
   2. EDZŐ ADATLAPJÁNAK MEGNYITÁSA
---------------------------------------------------------- */

document.querySelectorAll('.trainer-details').forEach((button) => {
  button.addEventListener('click', () => {

    /* ----------------------------------------------------------
       3. KIVÁLASZTOTT EDZŐ ADATAINAK LEKÉRÉSE
    ---------------------------------------------------------- */

    const card = button.closest('.trainer-card');
    const name = card.dataset.name;
    const coach = coachDetails[name];

    /* ----------------------------------------------------------
       4. EDZŐ ELÉRHETŐSÉGEINEK MEGJELENÍTÉSE
    ---------------------------------------------------------- */

    FORGE.modal('FORGE BUDAPEST', `
      <span class="tag">KAPCSOLAT</span>
      <p>Személyi edző elérhetőségei</p>
      <div class="coach-contact-details">
        <p>♙ &nbsp; EDZŐ: <strong>${name.toUpperCase()}</strong></p>
        <p>☎ &nbsp; TELEFON: <strong>${coach.phone}</strong></p>
        <p>✉ &nbsp; E-MAIL: <strong>${coach.email}</strong></p>
      </div>
      <p class="demo-note">A megadott elérhetőségek bemutató adatok.</p>
    `);
  });
});
