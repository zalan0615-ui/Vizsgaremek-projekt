
/* ============================================================
   FOGLALKOZÁSOK OLDAL - FORGE BUDAPEST
============================================================ */

/* ----------------------------------------------------------
   1. FOGLALKOZÁSOK ADATAI
---------------------------------------------------------- */

/** Foglalkozások: a kártyák a HTML-ben, a részletek megnyitása itt van. */
const groupClasses = [
  { name: 'GERINCTORNA', desc: 'Hát- és derékbarát mobilizáló óra, amivel könnyebb a mindennap.', freq: 'Hétfő, szerda', trainer: 'Dávid' },
  { name: 'JÓGA', desc: 'Légzés, nyújtás és tudatos mozgás, nyugodt légkörben.', freq: 'Kedd, péntek', trainer: 'Márk' },
  { name: 'NŐI KONDI', desc: 'Alakformáló és erősítő foglalkozás támogató közegben.', freq: 'Hétfő, szerda', trainer: 'Eszter' },
  { name: 'CROSS TRAINING', desc: 'Intenzív teljes testes edzés erővel és állóképességgel.', freq: 'Csütörtök, péntek', trainer: 'Dávid' },
  { name: 'LATIN FITNESS', desc: 'Pörgős kardió latin ritmusokra, mozgás és motiváció.', freq: 'Hétfő, szerda', trainer: 'Márk' },
  { name: 'ALAKFORMÁLÓ', desc: 'Célzott erősítés és testsúlyos gyakorlatok, kezdőknek is.', freq: 'Hétfő, péntek', trainer: 'Eszter' }
];

/* ----------------------------------------------------------
   2. FOGLALKOZÁSOK GOMBJAINAK ESEMÉNYKEZELÉSE
---------------------------------------------------------- */

document.querySelectorAll('[data-class]').forEach((button) => {
  button.addEventListener('click', () => {
    const classInfo = groupClasses[Number(button.dataset.class)];
    if (!classInfo) return;

    /* ----------------------------------------------------------
       3. FOGLALKOZÁS RÉSZLETEINEK MEGJELENÍTÉSE
    ---------------------------------------------------------- */

    FORGE.modal(classInfo.name, `
      <span class="eyebrow">FOGLALKOZÁS RÉSZLETEI</span>
      <p>${classInfo.desc}</p>
      <p>Edző: <strong>${classInfo.trainer}</strong></p>
      <p>Napok: <strong>${classInfo.freq}</strong></p>
      <a class="btn" href="${FORGE.url('schedule')}">ÓRAREND MEGTEKINTÉSE</a>
    `);
  });
});
