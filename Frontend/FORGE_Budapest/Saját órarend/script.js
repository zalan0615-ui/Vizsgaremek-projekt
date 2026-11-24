
/* ============================================================
   SAJÁT ÓRAREND - FORGE BUDAPEST
============================================================ */

/* ----------------------------------------------------------
   1. FELHASZNÁLÓ FOGLALÁSAINAK MEGJELENÍTÉSE
---------------------------------------------------------- */

/** A felhasználó foglalásainak megjelenítése (böngészős demó). */
function renderBookings() {
  const session = FORGE.store.get('session', null);
  const container = document.querySelector('#my-lessons');

  /* ----------------------------------------------------------
     2. BEJELENTKEZÉS ELLENŐRZÉSE
  ---------------------------------------------------------- */

  if (!session) {
    container.innerHTML = `
      <div class="empty-state">
        A saját foglalásaid megtekintéséhez jelentkezz be.
        <a class="btn" href="${FORGE.url('login')}">BELÉPÉS</a>
      </div>`;
    return;
  }

  /* ----------------------------------------------------------
     3. LEFOGLALT FOGLALKOZÁSOK LEKÉRÉSE
  ---------------------------------------------------------- */

  const bookings = FORGE.getBookings();
  const lessons = FORGE.getLessons().filter((lesson) => bookings.includes(lesson.id));

  /* ----------------------------------------------------------
     4. FOGLALÁSOK MEGJELENÍTÉSE A HTML-BEN
  ---------------------------------------------------------- */

  container.innerHTML = lessons.length
    ? lessons.map((lesson) => `
        <div class="my-lesson">
          <div>
            <strong>${lesson.title}</strong>
            <p>${FORGE.days[lesson.day]} · ${lesson.time} · ${lesson.trainer}</p>
          </div>
          <button class="btn btn-outline" type="button" data-cancel="${lesson.id}">LEMONDÁS</button>
        </div>`).join('')
    : '<div class="empty-state">Nincs lefoglalt foglalkozásod.</div>';

  /* ----------------------------------------------------------
     5. FOGLALÁS LEMONDÁSA
  ---------------------------------------------------------- */

  container.querySelectorAll('[data-cancel]').forEach((button) => {
    button.addEventListener('click', () => {
      const id = Number(button.dataset.cancel);
      FORGE.store.set('bookings', FORGE.getBookings().filter((booked) => booked !== id));
      renderBookings();
      FORGE.notify('Foglalás törölve.');
    });
  });
}

/* ----------------------------------------------------------
   6. FOGLALÁSOK AUTOMATIKUS BETÖLTÉSE
---------------------------------------------------------- */

renderBookings();
