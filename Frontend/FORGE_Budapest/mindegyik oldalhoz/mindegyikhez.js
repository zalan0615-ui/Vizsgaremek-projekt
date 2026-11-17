/**
 * FORGE Budapest – közös JavaScript
 * Bootstrap 5.3.6: menü (Collapse), legördülő lista (Dropdown), modal és toast.
 * Minden oldalon ez fut először, majd az oldal saját script.js fájlja.
 */
(() => {
  'use strict';

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
  const store = {
    get(key, fallback) {
      try {
        return JSON.parse(localStorage.getItem('forge_' + key)) ?? fallback;
      } catch {
        return fallback;
      }
    },
    set(key, value) {
      localStorage.setItem('forge_' + key, JSON.stringify(value));
    }
  };

  function notify(message) {
    const toast = document.createElement('div');
    toast.className = 'toast forge-toast text-bg-dark border-0';
    toast.setAttribute('role', 'status');
    toast.setAttribute('aria-live', 'polite');
    toast.innerHTML = '<div class="toast-body"></div>';
    toast.querySelector('.toast-body').textContent = message;
    document.body.append(toast);
    toast.addEventListener('hidden.bs.toast', () => toast.remove(), { once: true });
    bootstrap.Toast.getOrCreateInstance(toast, { delay: 3200 }).show();
  }

  function closeModal(element) {
    const instance = bootstrap.Modal.getInstance(element);
    if (instance) instance.hide();
    else element.remove();
  }

  function modal(title, html, onOpen) {
    const element = document.createElement('div');
    element.className = 'modal fade forge-modal';
    element.tabIndex = -1;
    element.setAttribute('aria-hidden', 'true');
    element.innerHTML = `
      <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">
          <div class="modal-header">
            <h2 class="modal-title">${title.replaceAll('<', '&lt;').replaceAll('>', '&gt;')}</h2>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Bezárás"></button>
          </div>
          <div class="modal-body">${html}</div>
        </div>
      </div>`;
    document.body.append(element);
    element.addEventListener('hidden.bs.modal', () => element.remove(), { once: true });
    const instance = new bootstrap.Modal(element);
    instance.show();
    onOpen?.(element);
    return element;
  }

  window.FORGE = { paths, url, store, notify, modal, closeModal };

  const page = document.body.dataset.page || 'home';
  const links = [
    ['home', 'Kezdőlap'], ['workouts', 'Edzések'],
    ['trainers', 'Edzőink'], ['price', 'Árak'],
    ['about', 'Rólunk'], ['contact', 'Kapcsolat']
  ];
  const header = document.querySelector('#site-header');

  if (header) {
    if (page === 'admin') {
      header.className = 'site-header admin-topbar';
      header.innerHTML = `
        <div class="container nav-wrap d-flex align-items-center">
          <a class="brand" href="${url('home')}">FORGE<span>BUDAPEST</span></a>
          <div class="admin-top-info ms-auto"><b>ADMIN PANEL</b><small>adminisztrációs kezelőfelület</small></div>
          <span class="admin-account d-flex align-items-center gap-2"><span class="user-line-icon">♙</span> Admin</span>
        </div>`;
    } else {
      header.className = 'site-header navbar navbar-expand-lg navbar-dark';
      header.innerHTML = `
        <div class="container nav-wrap">
          <a class="brand navbar-brand" href="${url('home')}" aria-label="FORGE Budapest">FORGE<span>BUDAPEST</span></a>
          <button class="navbar-toggler menu-toggle" type="button" data-bs-toggle="collapse"
                  data-bs-target="#forgeNavigation" aria-controls="forgeNavigation"
                  aria-expanded="false" aria-label="Menü megnyitása">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="forgeNavigation">
            <nav class="navbar-nav nav-links" aria-label="Főmenü">
              ${links.map(([id, name]) => `<a class="nav-link ${page === id ? 'active' : ''}" href="${url(id)}">${name}</a>`).join('')}
              <a class="nav-link mobile-shortcuts" href="${url('schedule')}">Általános órarend</a>
              <a class="nav-link mobile-shortcuts" href="${url('classes')}">Foglalkozások</a>
            </nav>
            <div class="nav-actions d-flex align-items-center">
              <div class="dropdown">
                <button type="button" class="btn account-link dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                  <span class="user-line-icon">♙</span><span>Belépés</span>
                </button>
                <ul class="dropdown-menu dropdown-menu-end forge-dropdown">
                  <li><a class="dropdown-item" href="${url('login')}">Bejelentkezés</a></li>
                  <li><a class="dropdown-item" href="${url('register')}">Regisztráció</a></li>
                  <li><a class="dropdown-item" href="${url('personal')}">Saját órarend</a></li>
                  <li><a class="dropdown-item" href="${url('adminLogin')}">Adminpanel bejelentkezés</a></li>
                </ul>
              </div>
              <a class="btn forge-cta" href="${url('contact')}">PRÓBAÓRA</a>
            </div>
          </div>
        </div>`;
    }
  }

  const footer = document.querySelector('#site-footer');
  if (footer) {
    footer.className = 'site-footer';
    footer.innerHTML = `
      <div class="container footer-main d-flex flex-wrap align-items-center justify-content-between">
        <nav class="footer-menu d-flex flex-wrap" aria-label="Lábléc">
          <a href="${url('workouts')}">Erő</a>
          <a href="${url('schedule')}">Állóképesség</a>
          <a href="${url('classes')}">Funkcionális edzés</a>
          <a href="${url('trainers')}">Személyi edzés</a>
        </nav>
        <a class="footer-middle" href="${url('home')}"><span class="brand">FORGE<span>BUDAPEST</span></span></a>
        <div class="footer-hours d-flex align-items-center">
          <span class="clock-symbol">◷</span>
          <div><strong>0–24</strong><small>MINDIG VELED</small></div>
        </div>
      </div>`;
  }
})();

/** Közös mintaadatok. Backend bekötése után API-hívásokra cserélhetők. */
FORGE.days = ['Hétfő','Kedd','Szerda','Csütörtök','Péntek','Szombat','Vasárnap'];
FORGE.lessons = [
 {id:1,day:0,time:'09:00',title:'Női kondi',trainer:'Eszter',type:'Női',spots:8},{id:2,day:0,time:'11:00',title:'Gerinctorna',trainer:'Dávid',type:'Gerinc',spots:12},{id:3,day:0,time:'16:00',title:'Alakformáló',trainer:'Eszter',type:'Alakformáló',spots:7},{id:4,day:0,time:'18:00',title:'Latin Fitness',trainer:'Márk',type:'Latin',spots:10},
 {id:5,day:1,time:'10:30',title:'Jóga',trainer:'Márk',type:'Jóga',spots:8},{id:6,day:2,time:'09:00',title:'Női kondi',trainer:'Eszter',type:'Női',spots:7},{id:7,day:2,time:'11:00',title:'Gerinctorna',trainer:'Dávid',type:'Gerinc',spots:8},{id:8,day:2,time:'16:00',title:'Latin Fitness',trainer:'Márk',type:'Latin',spots:5},
 {id:9,day:3,time:'14:00',title:'Cross Training',trainer:'Dávid',type:'Cross',spots:5},{id:10,day:3,time:'18:00',title:'Cross Training',trainer:'Dávid',type:'Cross',spots:7},{id:11,day:4,time:'16:00',title:'Alakformáló',trainer:'Eszter',type:'Alakformáló',spots:10},{id:12,day:4,time:'19:00',title:'Jóga',trainer:'Márk',type:'Jóga',spots:9}];
FORGE.getLessons = () =>FORGE.store.get('adminLessons',FORGE.lessons);FORGE.getBookings = () =>FORGE.store.get('bookings',[]);
FORGE.bookLesson = id =>{if(!FORGE.store.get('session',null)){FORGE.notify('Foglaláshoz előbb be kell jelentkezni.');location.href=FORGE.url('login');return}let b=FORGE.getBookings();if(b.includes(id)){FORGE.notify('Ezt az órát már lefoglaltad.');return}FORGE.store.set('bookings',[...b,id]);FORGE.notify('Sikeres demófoglalás!')};

