
/* ============================================================
   FORGE BUDAPEST - KÖZÖS JAVASCRIPT
============================================================ */

/*
 * Bootstrap 5.3.6
 * Közös funkciók:
 * - Navbar
 * - Footer
 * - Navigáció
 * - Modal
 * - Toast értesítések
 * - LocalStorage
 * - Órarend és foglalások
 * - Frontend segédfüggvények
 */


/* ============================================================
   1. KÖZÖS BEÁLLÍTÁSOK
============================================================ */

(() => {
  'use strict';


  /* ----------------------------------------------------------
     1.1. OLDALAK ELÉRÉSI ÚTVONALAI
  ---------------------------------------------------------- */

  const paths = {
    home: 'Kezdőlap/Homepage.html',
    workouts: 'Edzések/Workout.html',
    trainers: 'Edzőink/Trainers.html',
    price: 'Árak/Price.html',
    about: 'Rólunk/About_us.html',
    contact: 'Kapcsolat/Connection.html',
    login: 'Bejelentkezés/Login.html',
    register: 'Regisztráció/Registration.html',
    schedule: 'Általános órarend/General_timetable.html',
    personal: 'Saját órarend/Personal_timetable.html',
    classes: 'Foglalkozások/classes.html',
    adminLogin: 'Adminpanel bejelentkezés/Admin_login.html',
    admin: 'Adminpanel/Admin.html'
  };

  const url = key => `../${paths[key]}`;


  /* ============================================================
     2. LOCALSTORAGE - ADATOK MENTÉSE ÉS LEKÉRÉSE
  ============================================================ */

  //Ezt NÉZD ÁT!!!
  const store = {

    get(key, fallback) {
      try {
        return JSON.parse(
          localStorage.getItem('forge_' + key)
        ) ?? fallback;
      } catch {
        return fallback;
      }
    },

    set(key, value) {
      localStorage.setItem(
        'forge_' + key,
        JSON.stringify(value)
      );
    }

  };


  /* ============================================================
     3. TOAST - ÉRTESÍTÉSEK MEGJELENÍTÉSE
  ============================================================ */

  function notify(message) {

    //EZT IS!
    const toast = document.createElement('div');

    toast.className =
      'toast forge-toast text-bg-dark border-0';

    toast.setAttribute('role', 'status');
    toast.setAttribute('aria-live', 'polite');

    toast.innerHTML =
      '<div class="toast-body"></div>';

    toast.querySelector('.toast-body').textContent =
      message;

    document.body.append(toast);

    toast.addEventListener(
      'hidden.bs.toast',
      () => toast.remove(),
      { once: true }
    );

    bootstrap.Toast.getOrCreateInstance(
      toast,
      { delay: 3200 }
    ).show();

  }


  /* ============================================================
     4. MODAL - FELUGRÓ ABLAKOK KEZELÉSE
  ============================================================ */

  /* ----------------------------------------------------------
     4.1. MODAL BEZÁRÁSA
  ---------------------------------------------------------- */

  function closeModal(element) {

    const instance =
      bootstrap.Modal.getInstance(element);

    if (instance) {
      instance.hide();
    } else {
      element.remove();
    }

  }


  /* ----------------------------------------------------------
     4.2. MODAL LÉTREHOZÁSA ÉS MEGNYITÁSA
  ---------------------------------------------------------- */

  function modal(title, html, onOpen) {

    const element = document.createElement('div');

    element.className =
      'modal fade forge-modal';

    element.tabIndex = -1;

    element.setAttribute(
      'aria-hidden',
      'true'
    );

    element.innerHTML = `
      <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">

          <div class="modal-header">

            <h2 class="modal-title">
              ${title.replaceAll('<', '&lt;').replaceAll('>', '&gt;')}
            </h2>

            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Bezárás">
            </button>

          </div>

          <div class="modal-body">
            ${html}
          </div>

        </div>
      </div>
    `;

    document.body.append(element);

    element.addEventListener(
      'hidden.bs.modal',
      () => element.remove(),
      { once: true }
    );

    const instance =
      new bootstrap.Modal(element);

    instance.show();

    onOpen?.(element);

    return element;

  }


  /* ============================================================
     5. FORGE GLOBÁLIS OBJEKTUM
  ============================================================ */

  window.FORGE = {
    paths,
    url,
    store,
    notify,
    modal,
    closeModal
  };


  /* ============================================================
     6. NAVBAR - KÖZÖS FEJLÉC
  ============================================================ */

  /* ----------------------------------------------------------
     6.1. AKTUÁLIS OLDAL MEGHATÁROZÁSA
  ---------------------------------------------------------- */

  const page =
    document.body.dataset.page || 'home';


  /* ----------------------------------------------------------
     6.2. NAVIGÁCIÓS MENÜPONTOK
  ---------------------------------------------------------- */

  const links = [
    ['home', 'Kezdőlap'],
    ['workouts', 'Edzések'],
    ['trainers', 'Edzőink'],
    ['price', 'Árak'],
    ['about', 'Rólunk'],
    ['contact', 'Kapcsolat']
  ];

  const header =
    document.querySelector('#site-header');


  /* ============================================================
     7. ADMIN NAVBAR
  ============================================================ */

  if (header) {

    if (page === 'admin') {

      header.className =
        'site-header admin-topbar';

      header.innerHTML = `
        <div class="container nav-wrap d-flex align-items-center">

          <a class="brand" href="${url('home')}">
            FORGE<span>BUDAPEST</span>
          </a>

          <div class="admin-top-info ms-auto">
            <b>ADMIN PANEL</b>
            <small>adminisztrációs kezelőfelület</small>
          </div>

          <span class="admin-account d-flex align-items-center gap-2">
            <span class="user-line-icon">♙</span>
            Admin
          </span>

        </div>
      `;

    } else {


      /* ========================================================
         8. NORMÁL NAVBAR - MENÜ ÉS BEJELENTKEZÉS
      ======================================================== */

      header.className =
        'site-header navbar navbar-expand-lg navbar-dark';

      header.innerHTML = `
        <div class="container nav-wrap">

          <!-- LOGÓ -->
          <a
            class="brand navbar-brand"
            href="${url('home')}"
            aria-label="FORGE Budapest">
            FORGE<span>BUDAPEST</span>
          </a>

          <!-- MOBIL MENÜ -->
          <button
            class="navbar-toggler menu-toggle"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#forgeNavigation"
            aria-controls="forgeNavigation"
            aria-expanded="false"
            aria-label="Menü megnyitása">

            <span class="navbar-toggler-icon"></span>

          </button>

          <div
            class="collapse navbar-collapse"
            id="forgeNavigation">

            <!-- NAVIGÁCIÓS LINKEK -->
            <nav
              class="navbar-nav nav-links"
              aria-label="Főmenü">

              ${links.map(([id, name]) => `
                <a
                  class="nav-link ${page === id ? 'active' : ''}"
                  href="${url(id)}">
                  ${name}
                </a>
              `).join('')}

              <a
                class="nav-link mobile-shortcuts"
                href="${url('schedule')}">
                Általános órarend
              </a>

              <a
                class="nav-link mobile-shortcuts"
                href="${url('classes')}">
                Foglalkozások
              </a>

            </nav>


            <!-- BEJELENTKEZÉS ÉS PRÓBAÓRA -->
            <div class="nav-actions d-flex align-items-center">

              <div class="dropdown">

                <button
                  type="button"
                  class="btn account-link dropdown-toggle"
                  data-bs-toggle="dropdown"
                  aria-expanded="false">

                  <span class="user-line-icon">♙</span>
                  <span>Belépés</span>

                </button>

                <ul class="dropdown-menu dropdown-menu-end forge-dropdown">

                  <li>
                    <a class="dropdown-item" href="${url('login')}">
                      Bejelentkezés
                    </a>
                  </li>

                  <li>
                    <a class="dropdown-item" href="${url('register')}">
                      Regisztráció
                    </a>
                  </li>

                  <li>
                    <a class="dropdown-item" href="${url('personal')}">
                      Saját órarend
                    </a>
                  </li>

                  <li>
                    <a class="dropdown-item" href="${url('adminLogin')}">
                      Adminpanel bejelentkezés
                    </a>
                  </li>

                </ul>

              </div>

              <a
                class="btn forge-cta"
                href="${url('contact')}">
                PRÓBAÓRA
              </a>

            </div>

          </div>
        </div>
      `;

    }
  }


  /* ============================================================
     9. FOOTER - KÖZÖS LÁBLÉC
  ============================================================ */

  const footer =
    document.querySelector('#site-footer');

  if (footer) {

    footer.className = 'site-footer';

    footer.innerHTML = `
      <div class="container footer-main d-flex flex-wrap align-items-center justify-content-between">

        <!-- LÁBLÉC MENÜ -->
        <nav
          class="footer-menu d-flex flex-wrap"
          aria-label="Lábléc">

          <a href="${url('workouts')}">Erő</a>
          <a href="${url('schedule')}">Állóképesség</a>
          <a href="${url('classes')}">Funkcionális edzés</a>
          <a href="${url('trainers')}">Személyi edzés</a>

        </nav>

        <!-- LÁBLÉC LOGÓ -->
        <a class="footer-middle" href="${url('home')}">
          <span class="brand">
            FORGE<span>BUDAPEST</span>
          </span>
        </a>

        <!-- NYITVATARTÁS -->
        <div class="footer-hours d-flex align-items-center">

          <span class="clock-symbol">◷</span>

          <div>
            <strong>0–24</strong>
            <small>MINDIG VELED</small>
          </div>

        </div>

      </div>
    `;

  }

})();


/* ============================================================
   10. ÓRAREND - KÖZÖS ADATOK
============================================================ */

/* ------------------------------------------------------------
   10.1. HÉT NAPJAI
------------------------------------------------------------ */

FORGE.days = [
  'Hétfő',
  'Kedd',
  'Szerda',
  'Csütörtök',
  'Péntek',
  'Szombat',
  'Vasárnap'
];


/* ------------------------------------------------------------
   10.2. FOGLALKOZÁSOK MINTAADATAI
------------------------------------------------------------ */

FORGE.lessons = [

  {
    id: 1,
    day: 0,
    time: '09:00',
    title: 'Női kondi',
    trainer: 'Eszter',
    type: 'Női',
    spots: 8
  },

  {
    id: 2,
    day: 0,
    time: '11:00',
    title: 'Gerinctorna',
    trainer: 'Dávid',
    type: 'Gerinc',
    spots: 12
  },

  {
    id: 3,
    day: 0,
    time: '16:00',
    title: 'Alakformáló',
    trainer: 'Eszter',
    type: 'Alakformáló',
    spots: 7
  },

  {
    id: 4,
    day: 0,
    time: '18:00',
    title: 'Latin Fitness',
    trainer: 'Márk',
    type: 'Latin',
    spots: 10
  },

  {
    id: 5,
    day: 1,
    time: '10:30',
    title: 'Jóga',
    trainer: 'Márk',
    type: 'Jóga',
    spots: 8
  },

  {
    id: 6,
    day: 2,
    time: '09:00',
    title: 'Női kondi',
    trainer: 'Eszter',
    type: 'Női',
    spots: 7
  },

  {
    id: 7,
    day: 2,
    time: '11:00',
    title: 'Gerinctorna',
    trainer: 'Dávid',
    type: 'Gerinc',
    spots: 8
  },



  {
    id: 8,
    day: 2,
    time: '16:00',
    title: 'Latin Fitness',
    trainer: 'Márk',
    type: 'Latin',
    spots: 5
  },

  {
    id: 9,
    day: 3,
    time: '14:00',
    title: 'Cross Training',
    trainer: 'Dávid',
    type: 'Cross',
    spots: 5
  },

  {
    id: 10,
    day: 3,
    time: '18:00',
    title: 'Cross Training',
    trainer: 'Dávid',
    type: 'Cross',
    spots: 7
  },

  {
    id: 11,
    day: 4,
    time: '16:00',
    title: 'Alakformáló',
    trainer: 'Eszter',
    type: 'Alakformáló',
    spots: 10
  },

  {
    id: 12,
    day: 4,
    time: '19:00',
    title: 'Jóga',
    trainer: 'Márk',
    type: 'Jóga',
    spots: 9
  }

];


/* ============================================================
   11. FOGLALKOZÁSOK LEKÉRÉSE
============================================================ */

FORGE.getLessons = () => {
  return FORGE.store.get(
    'adminLessons',
    FORGE.lessons
  );
};


/* ============================================================
   12. FOGLALÁSOK LEKÉRÉSE
============================================================ */

FORGE.getBookings = () => {
  return FORGE.store.get(
    'bookings',
    []
  );
};


/* ============================================================
   13. ÓRAFOGLALÁS - DEMÓ
============================================================ */

FORGE.bookLesson = id => {

  /* ----------------------------------------------------------
     13.1. BEJELENTKEZÉS ELLENŐRZÉSE
  ---------------------------------------------------------- */

  if (!FORGE.store.get('session', null)) {

    FORGE.notify(
      'Foglaláshoz előbb be kell jelentkezni.'
    );

    location.href = FORGE.url('login');

    return;
  }


  /* ----------------------------------------------------------
     13.2. MEGLÉVŐ FOGLALÁS ELLENŐRZÉSE
  ---------------------------------------------------------- */

  let bookings = FORGE.getBookings();

  if (bookings.includes(id)) {

    FORGE.notify(
      'Ezt az órát már lefoglaltad.'
    );

    return;
  }


  /* ----------------------------------------------------------
     13.3. FOGLALÁS MENTÉSE
  ---------------------------------------------------------- */

  FORGE.store.set(
    'bookings',
    [...bookings, id]
  );

  FORGE.notify(
    'Sikeres demófoglalás!'
  );

};


/* ============================================================
   14. FRONTEND SEGÉDFÜGGVÉNYEK
============================================================ */

window.ForgeUI = {


  /* ----------------------------------------------------------
     14.1. SZÖVEG MEGJELENÍTÉSE HTML ELEMBEN
  ---------------------------------------------------------- */

  setText(id, value) {

    const element =
      document.getElementById(id);

    if (!element) return;

    element.textContent =
      value === null ||
      value === undefined ||
      value === ""
        ? "—"
        : String(value);

  },


  /* ----------------------------------------------------------
     14.2. SZÁMOK FORMÁZÁSA
  ---------------------------------------------------------- */

  formatNumber(value) {

    if (
      value === null ||
      value === undefined ||
      value === ""
    ) {
      return "—";
    }

    const number = Number(value);

    return Number.isFinite(number)
      ? number.toLocaleString('hu-HU')
      : "—";

  }

};
