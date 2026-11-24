
/* ============================================================
   BEJELENTKEZÉS OLDAL - FORGE BUDAPEST
============================================================ */

/* ----------------------------------------------------------
   1. JELSZÓMEZŐ ÉS HIBAÜZENET KIVÁLASZTÁSA
---------------------------------------------------------- */

/** Bejelentkezés – csak böngészőben tárolt DEMÓ fiókokkal működik. */
const passwordInput = document.querySelector('#password');
const loginMessage = document.querySelector('#login-message');

/* ----------------------------------------------------------
   2. JELSZÓ MEGJELENÍTÉSE ÉS ELREJTÉSE
---------------------------------------------------------- */

document.querySelector('.show-password').addEventListener('click', () => {
  passwordInput.type = passwordInput.type === 'password' ? 'text' : 'password';
});

/* ----------------------------------------------------------
   3. ELFELEJTETT JELSZÓ GOMB KEZELÉSE
---------------------------------------------------------- */

document.querySelector('#forgot-password').addEventListener('click', (event) => {
  event.preventDefault();
  FORGE.notify('Jelszó-visszaállítás csak backend bekötése után elérhető.');
});

/* ----------------------------------------------------------
   4. BEJELENTKEZÉSI ŰRLAP ELKÜLDÉSE
---------------------------------------------------------- */

document.querySelector('#login-form').addEventListener('submit', (event) => {
  event.preventDefault();

  /* ----------------------------------------------------------
     5. FELHASZNÁLÓ ADATAINAK LEKÉRÉSE
  ---------------------------------------------------------- */

  const email = document.querySelector('#email').value;
  const password = passwordInput.value;
  const user = FORGE.store.get('user', null);

  /* ----------------------------------------------------------
     6. BEJELENTKEZÉSI ADATOK ELLENŐRZÉSE
  ---------------------------------------------------------- */

  if (user && user.email === email && user.password === password) {

    /* ----------------------------------------------------------
       7. SIKERES BEJELENTKEZÉS
    ---------------------------------------------------------- */

    FORGE.store.set('session', { name: user.name, email });
    FORGE.notify('Sikeres demó bejelentkezés!');
    window.location.href = FORGE.url('personal');

  } else {

    /* ----------------------------------------------------------
       8. SIKERTELEN BEJELENTKEZÉS
    ---------------------------------------------------------- */

    loginMessage.textContent = 'Nincs egyező demó fiók. Előbb regisztrálj.';
  }
});
