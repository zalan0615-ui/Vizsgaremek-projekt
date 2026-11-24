
/* ============================================================
   KAPCSOLAT OLDAL - FORGE BUDAPEST
============================================================ */

import { apiPost } from "../mindegyik oldalhoz/osszekotes.js";

/* ----------------------------------------------------------
   1. KAPCSOLATFELVÉTELI ŰRLAP KIVÁLASZTÁSA
---------------------------------------------------------- */

/** Kapcsolatfelvételi űrlap: demó mentés és Bootstrap visszajelző modal. */
const contactForm = document.querySelector('#contact-form');

/* ----------------------------------------------------------
   2. ŰRLAP ELKÜLDÉSÉNEK KEZELÉSE
---------------------------------------------------------- */

contactForm.addEventListener('submit', (event) => {
  event.preventDefault();

  /* ----------------------------------------------------------
     3. ÜZENET MENTÉSE A BÖNGÉSZŐBE
  ---------------------------------------------------------- */

  FORGE.store.set('contactMessage', {
    name: contactForm.querySelector('#name').value,
    email: contactForm.querySelector('#email').value,
    message: contactForm.querySelector('#message').value,
    date: new Date().toISOString()
  });

  /* ----------------------------------------------------------
     4. ŰRLAP KIÜRÍTÉSE
  ---------------------------------------------------------- */

  contactForm.reset();

  /* ----------------------------------------------------------
     5. SIKERES ÜZENETKÜLDÉS - FELUGRÓ ABLAK
  ---------------------------------------------------------- */

  FORGE.modal('SIKERESEN ELKÜLDVE!', `
    <div class="contact-success text-center">
      <div class="success-icon">✉</div>
      <p>Az üzenetedet rögzítettük a böngészős demóban.
         Az éles üzenetküldés még nincs bekötve.</p>
      <div class="d-flex gap-2 justify-content-center flex-wrap">
        <a class="btn btn-outline" href="${FORGE.url('home')}">VISSZA</a>
        <button class="btn" type="button" id="close-success">RENDBEN</button>
      </div>
    </div>`, (dialog) => {

    /* ----------------------------------------------------------
       6. FELUGRÓ ABLAK BEZÁRÁSA
    ---------------------------------------------------------- */

    dialog.querySelector('#close-success').addEventListener('click', () => FORGE.closeModal(dialog));
  });
});
