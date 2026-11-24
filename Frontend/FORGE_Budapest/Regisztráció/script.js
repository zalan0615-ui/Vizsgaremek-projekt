
/* ============================================================
   REGISZTRÁCIÓ - FORGE BUDAPEST
============================================================ */

/* ----------------------------------------------------------
   1. REGISZTRÁCIÓS ŰRLAP ÉS ÜZENET KIVÁLASZTÁSA
---------------------------------------------------------- */

/** Regisztráció – kizárólag a böngészős demonstrációhoz. */
const registerForm = document.querySelector('#register-form');
const registerMessage = document.querySelector('#register-message');

/* ----------------------------------------------------------
   2. REGISZTRÁCIÓS ŰRLAP ELKÜLDÉSE
---------------------------------------------------------- */

registerForm.addEventListener('submit', (event) => {
  event.preventDefault();

  /* ----------------------------------------------------------
     3. FELHASZNÁLÓI ADATOK BEOLVASÁSA
  ---------------------------------------------------------- */

  const name = registerForm.querySelector('#name').value;
  const email = registerForm.querySelector('#email').value;
  const password = registerForm.querySelector('#password').value;
  const confirmPassword = registerForm.querySelector('#password2').value;

  /* ----------------------------------------------------------
     4. JELSZAVAK EGYEZÉSÉNEK ELLENŐRZÉSE
  ---------------------------------------------------------- */

  if (password !== confirmPassword) {
    registerMessage.textContent = 'A két jelszó nem egyezik!';
    return;
  }

  /* ----------------------------------------------------------
     5. FELHASZNÁLÓ MENTÉSE ÉS BEJELENTKEZTETÉSE
  ---------------------------------------------------------- */

  FORGE.store.set('user', { name, email, password });
  FORGE.store.set('session', { name, email });

  /* ----------------------------------------------------------
     6. SIKERES REGISZTRÁCIÓ ÉS ÁTIRÁNYÍTÁS
  ---------------------------------------------------------- */

  FORGE.notify('Demófiók létrehozva!');
  window.location.href = FORGE.url('personal');
});
