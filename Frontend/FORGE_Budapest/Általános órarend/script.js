
/* ============================================================
   HETI ÓRAREND - FORGE BUDAPEST
============================================================ */

import { apiGet } from "../mindegyik oldalhoz/osszekotes.js";

/* ------------------------------------------------------------
   1. FOGLALKOZÁSOK SZÍNEI
------------------------------------------------------------ */

const scheduleColors = {
  Női: '#752b59',
  Gerinc: '#4c8d39',
  Alakformáló: '#945176',
  Latin: '#ba4b04',
  Cross: '#528d2c',
  Jóga: '#7a1dca'
};


/* ------------------------------------------------------------
   2. ÓRAREND MEGJELENÍTÉSE
------------------------------------------------------------ */

function renderTimetable() {

  const board = document.querySelector('#timetable');
  let cells = '';


  /* ----------------------------------------------------------
     3. OSZLOPFEJLÉCEK - IDŐ, HÉTFŐTŐL PÉNTEKIG
  ---------------------------------------------------------- */

  for (let day = 0; day < 6; day++) {

    const name = day === 0
      ? 'IDŐ'
      : FORGE.days[day - 1].toUpperCase();

    cells += `
      <div class="cell head"
        style="grid-column:${day + 1};grid-row:1">
        ${name}
      </div>`;
  }


  /* ----------------------------------------------------------
     4. IDŐRÁCS - 8:00-TÓL 20:30-IG
  ---------------------------------------------------------- */

  for (let slot = 0; slot < 26; slot++) {

    const hour = 8 + Math.floor(slot / 2);
    const minute = slot % 2 ? '30' : '00';

    cells += `
      <div class="cell time-cell"
        style="grid-column:1;grid-row:${slot + 2}">
        <span>
          ${String(hour).padStart(2, '0')}:${minute}
        </span>
      </div>`;

    for (let day = 0; day < 5; day++) {

      cells += `
        <div class="cell"
          style="grid-column:${day + 2};
                 grid-row:${slot + 2}">
        </div>`;
    }
  }


  /* ----------------------------------------------------------
     5. FOGLALKOZÁSOK ELHELYEZÉSE AZ ÓRARENDBEN
  ---------------------------------------------------------- */

  FORGE.getLessons()
    .filter((lesson) => lesson.day < 5)
    .forEach((lesson) => {

      const [hour, minute] = lesson.time
        .split(':')
        .map(Number);

      const start =
        2 +
        (hour - 8) * 2 +
        (minute >= 30 ? 1 : 0);

      if (start < 2 || start > 27) return;

      const length = Math.min(
        lesson.type === 'Jóga' ? 2 : 3,
        28 - start
      );

      const color =
        scheduleColors[lesson.type] || '#6e6e6e';

      cells += `
        <button
          type="button"
          class="schedule-event"
          data-id="${lesson.id}"
          style="
            grid-column:${lesson.day + 2};
            grid-row:${start}/span ${length};
            background:${color}
          "
          title="${lesson.title} – Foglalás">

          <strong>${lesson.title}</strong>
          <small>${lesson.trainer}</small>

        </button>`;
    });


  /* ----------------------------------------------------------
     6. ÓRAREND BETÖLTÉSE A HTML-BE
  ---------------------------------------------------------- */

  board.innerHTML = cells;


  /* ----------------------------------------------------------
     7. FOGLALKOZÁSRA KATTINTÁS
  ---------------------------------------------------------- */

  board.querySelectorAll('.schedule-event').forEach((button) => {

    button.addEventListener('click', () => {

      const lesson = FORGE.getLessons().find(
        (item) => item.id === Number(button.dataset.id)
      );

      if (!lesson) return;


      /* ------------------------------------------------------
         8. FOGLALKOZÁS ADATAINAK MEGJELENÍTÉSE MODÁLBAN
      ------------------------------------------------------ */

      FORGE.modal(
        lesson.title.toUpperCase(),

        `
          <p>
            ${FORGE.days[lesson.day]},
            ${lesson.time} · Edző: ${lesson.trainer}
          </p>

          <p>
            ${lesson.spots} szabad hely (demó)
          </p>

          <button class="btn" id="schedule-book">
            FOGLALÁS
          </button>
        `,

        (dialog) => {


          /* --------------------------------------------------
             9. FOGLALÁS GOMB ESEMÉNYKEZELŐJE
          -------------------------------------------------- */

          dialog.querySelector('#schedule-book')
            .addEventListener('click', () => {

              FORGE.closeModal(dialog);

              FORGE.bookLesson(lesson.id);

            });
        }
      );

    });
  });
}


/* ------------------------------------------------------------
   10. ÓRAREND AUTOMATIKUS MEGJELENÍTÉSE
------------------------------------------------------------ */

renderTimetable();
