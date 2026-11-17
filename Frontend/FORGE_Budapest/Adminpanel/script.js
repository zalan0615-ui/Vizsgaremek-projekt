/**
 * FORGE Budapest adminpanel (frontend DEMÓ).
 * A backend bekötéséig a felhasználók, bérletek, órák adatai localStorage-ban vannak.
 */
if (!FORGE.store.get('adminSession', false)) {
  window.location.replace(FORGE.url('adminLogin'));
}

/** A táblázatokba és űrlapértékekbe kerülő szöveg biztonságos kiírása. */
const escapeHtml = (value) => String(value ?? '').replace(/[&<>"']/g, (char) => ({
  '&': '&amp;',
  '<': '&lt;',
  '>': '&gt;',
  '"': '&quot;',
  "'": '&#39;'
}[char]));

const exampleUsers = [
  { id: 12, name: 'grain', email: 'grain@email.hu', phone: '', role: 'Tag', status: 'Aktív' },
  { id: 18, name: 'kovacsdavid', email: 'david@email.hu', phone: '', role: 'Edző', status: 'Aktív' },
  { id: 24, name: 'eszter', email: 'eszter@email.hu', phone: '', role: 'Edző', status: 'Aktív' },
  { id: 31, name: 'marktrainer', email: 'mark@email.hu', phone: '', role: 'Edző', status: 'Inaktív' },
  { id: 44, name: 'peti22', email: 'peti@email.hu', phone: '', role: 'Tag', status: 'Aktív' }
];

const examplePasses = [
  { user: 'grain', plan: 'Havi tagság', price: 19900, payment: 'cash', status: 'PAID' },
  { user: 'david', plan: 'Prémium+', price: 34900, payment: 'cash', status: 'UNPAID' },
  { user: 'eszter', plan: 'Napijegy', price: 3500, payment: 'cash', status: 'PAID' },
  { user: 'mark', plan: 'Havi tagság', price: 19900, payment: 'cash', status: 'PAID' },
  { user: 'peter', plan: 'Prémium+', price: 34900, payment: 'cash', status: 'UNPAID' }
];

let users = FORGE.store.get('adminUsers', exampleUsers);
let passes = FORGE.store.get('adminPasses', examplePasses);

/** Bal oldali nézetváltás: dashboard / users / passes / lessons. */
function setPanel(name) {
  document.querySelectorAll('.admin-nav').forEach((button) => {
    button.classList.toggle('active', button.dataset.panel === name);
  });
  document.querySelectorAll('.admin-pane').forEach((panel) => {
    panel.classList.toggle('hide', panel.id !== `panel-${name}`);
  });
  renderAdmin();
}

function oneHourLater(time) {
  const [hour, minute] = time.split(':').map(Number);
  return `${String((hour + 1) % 24).padStart(2, '0')}:${String(minute).padStart(2, '0')}`;
}

/** Felhasználólista, bérletek, órák és foglalások újrarajzolása. */
function renderAdmin() {
  const query = document.querySelector('#user-search').value.toLowerCase();
  const status = document.querySelector('#user-status').value;
  const filteredUsers = users.filter((user) => {
    const matchesName = user.name.toLowerCase().includes(query);
    const matchesEmail = user.email.toLowerCase().includes(query);
    return (matchesName || matchesEmail) && (status === 'all' || user.status === status);
  });

  const userRows = document.querySelector('#user-rows');
  userRows.innerHTML = filteredUsers.map((user) => `
    <tr>
      <td>${user.id}</td>
      <td>${escapeHtml(user.name)}</td>
      <td>${escapeHtml(user.email)}</td>
      <td>
        <span class="admin-status ${user.status === 'Aktív' ? '' : 'pending'}">
          ${escapeHtml(user.status.toUpperCase())}
        </span>
      </td>
      <td>
        <div class="row-actions">
          <button type="button" data-edit-user="${user.id}">SZERKESZTÉS</button>
          <button type="button" data-delete-user="${user.id}">TÖRLÉS</button>
        </div>
      </td>
    </tr>`).join('') || '<tr><td colspan="5">Nincs ilyen felhasználó.</td></tr>';

  userRows.querySelectorAll('[data-edit-user]').forEach((button) => {
    button.addEventListener('click', () => {
      const selectedUser = users.find((user) => user.id === Number(button.dataset.editUser));
      openUserEditor(selectedUser);
    });
  });
  userRows.querySelectorAll('[data-delete-user]').forEach((button) => {
    button.addEventListener('click', () => {
      if (!window.confirm('Biztosan törlöd ezt a felhasználót?')) return;
      users = users.filter((user) => user.id !== Number(button.dataset.deleteUser));
      FORGE.store.set('adminUsers', users);
      renderAdmin();
    });
  });

  const passRows = document.querySelector('#pass-rows');
  passRows.innerHTML = passes.map((pass, index) => `
    <tr>
      <td>${escapeHtml(pass.user)}</td>
      <td>${escapeHtml(pass.plan)}</td>
      <td>${pass.price.toLocaleString('hu-HU')} Ft</td>
      <td>${escapeHtml(pass.payment)}</td>
      <td><span class="admin-status ${pass.status === 'PAID' ? '' : 'pending'}">${pass.status}</span></td>
      <td><button class="mini-outline" data-pass="${index}">STÁTUSZ MÓDOSÍTÁSA</button></td>
    </tr>`).join('');

  passRows.querySelectorAll('[data-pass]').forEach((button) => {
    button.addEventListener('click', () => {
      const index = Number(button.dataset.pass);
      passes[index].status = passes[index].status === 'PAID' ? 'UNPAID' : 'PAID';
      FORGE.store.set('adminPasses', passes);
      renderAdmin();
    });
  });

  const lessons = FORGE.getLessons();
  const lessonRows = document.querySelector('#lesson-rows');
  lessonRows.innerHTML = lessons.map((lesson) => `
    <div class="admin-class-item">
      <div>
        <strong>${escapeHtml(lesson.title)}</strong>
        <small>${escapeHtml(lesson.trainer)} · ${escapeHtml(lesson.room || 'A terem')}</small>
      </div>
      <div class="admin-class-right">
        <time>${escapeHtml(lesson.time)}–${oneHourLater(lesson.time)}</time>
        <button type="button" data-edit-lesson="${lesson.id}">SZERKESZT</button>
      </div>
    </div>`).join('');

  lessonRows.querySelectorAll('[data-edit-lesson]').forEach((button) => {
    button.addEventListener('click', () => {
      const lesson = lessons.find((item) => item.id === Number(button.dataset.editLesson));
      openLessonEditor(lesson);
    });
  });

  const currentUser = FORGE.store.get('session', null);
  const bookingRows = FORGE.getBookings().map((id) => {
    const lesson = lessons.find((item) => item.id === id);
    if (!lesson) return '';
    return `
      <tr>
        <td>${escapeHtml(currentUser?.name || 'Vendég')}</td>
        <td>${escapeHtml(lesson.title)}</td>
        <td>${FORGE.days[lesson.day]}</td>
      </tr>`;
  }).join('');

  document.querySelector('#admin-booking-rows').innerHTML = bookingRows || `
    <tr><td>grain</td><td>Gerinc torna</td><td>10.14</td></tr>
    <tr><td>david</td><td>Jóga</td><td>10.14</td></tr>
    <tr><td>mark</td><td>Cross</td><td>10.15</td></tr>`;
}

/** Bootstrap modál – új felhasználó vagy meglévő szerkesztése. */
function openUserEditor(existingUser = null) {
  const user = existingUser || {};
  FORGE.modal(existingUser ? 'FELHASZNÁLÓ SZERKESZTÉSE' : 'ÚJ FELHASZNÁLÓ', `
    <form id="user-form">
      <div class="field">
        <label>Felhasználónév</label>
        <input class="form-control" name="name" required value="${escapeHtml(user.name || '')}">
      </div>
      <div class="field">
        <label>E-mail</label>
        <input class="form-control" name="email" type="email" required value="${escapeHtml(user.email || '')}">
      </div>
      <div class="field">
        <label>Telefonszám</label>
        <input class="form-control" name="phone" value="${escapeHtml(user.phone || '')}">
      </div>
      <div class="field">
        <label>Szerepkör</label>
        <select class="form-select" name="role">
          ${['Tag', 'Edző', 'Admin'].map((role) => `<option ${user.role === role ? 'selected' : ''}>${role}</option>`).join('')}
        </select>
      </div>
      <div class="field">
        <label>Státusz</label>
        <select class="form-select" name="status">
          ${['Aktív', 'Inaktív'].map((status) => `<option ${user.status === status ? 'selected' : ''}>${status}</option>`).join('')}
        </select>
      </div>
      <button class="btn" type="submit">MENTÉS</button>
    </form>`, (dialog) => {
    dialog.querySelector('form').addEventListener('submit', (event) => {
      event.preventDefault();
      const data = Object.fromEntries(new FormData(event.target));
      if (existingUser) {
        users = users.map((item) => item.id === existingUser.id ? { ...item, ...data } : item);
      } else {
        users.push({ ...data, id: Math.max(0, ...users.map((item) => item.id)) + 1 });
      }
      FORGE.store.set('adminUsers', users);
      FORGE.closeModal(dialog);
      renderAdmin();
      FORGE.notify('Felhasználó mentve.');
    });
  });
}

/** Bootstrap modál – új óra vagy meglévő óra szerkesztése. */
function openLessonEditor(existingLesson = null) {
  const lesson = existingLesson || {};
  const dayOptions = FORGE.days.map((name, index) => `
    <option value="${index}" ${lesson.day === index ? 'selected' : ''}>${name}</option>`).join('');

  FORGE.modal(existingLesson ? 'ÓRA SZERKESZTÉSE' : 'ÚJ ÓRA', `
    <form id="edit-lesson">
      <div class="field"><label>Foglalkozás neve</label>
        <input class="form-control" name="title" required value="${escapeHtml(lesson.title || '')}">
      </div>
      <div class="field"><label>Edző</label>
        <input class="form-control" name="trainer" required value="${escapeHtml(lesson.trainer || '')}">
      </div>
      <div class="field"><label>Terem</label>
        <input class="form-control" name="room" required value="${escapeHtml(lesson.room || 'A terem')}">
      </div>
      <div class="field"><label>Nap</label>
        <select class="form-select" name="day">${dayOptions}</select>
      </div>
      <div class="field"><label>Idő</label>
        <input class="form-control" name="time" type="time" required value="${escapeHtml(lesson.time || '17:00')}">
      </div>
      <div class="field"><label>Típus</label>
        <input class="form-control" name="type" required value="${escapeHtml(lesson.type || 'Cross')}">
      </div>
      <button class="btn" type="submit">MENTÉS</button>
    </form>`, (dialog) => {
    dialog.querySelector('form').addEventListener('submit', (event) => {
      event.preventDefault();
      const data = Object.fromEntries(new FormData(event.target));
      const lessons = FORGE.getLessons();
      data.day = Number(data.day);
      data.id = existingLesson?.id || Date.now();
      data.spots = existingLesson?.spots || 10;
      FORGE.store.set('adminLessons', existingLesson
        ? lessons.map((item) => item.id === existingLesson.id ? data : item)
        : [...lessons, data]);
      FORGE.closeModal(dialog);
      renderAdmin();
      FORGE.notify('Óra mentve.');
    });
  });
}

/** Oldal eseménykezelői. */
document.querySelectorAll('[data-panel]').forEach((button) => {
  button.addEventListener('click', () => setPanel(button.dataset.panel));
});
document.querySelectorAll('[data-goto]').forEach((button) => {
  button.addEventListener('click', () => setPanel(button.dataset.goto));
});
document.querySelector('#admin-logout').addEventListener('click', () => {
  FORGE.store.set('adminSession', false);
  window.location.href = FORGE.url('adminLogin');
});
document.querySelector('#user-search').addEventListener('input', renderAdmin);
document.querySelector('#user-status').addEventListener('change', renderAdmin);
document.querySelector('#add-user').addEventListener('click', () => openUserEditor());
document.querySelector('#quick-lesson').addEventListener('submit', (event) => {
  event.preventDefault();
  const data = Object.fromEntries(new FormData(event.target));
  const lessons = FORGE.getLessons();
  FORGE.store.set('adminLessons', [
    ...lessons,
    { ...data, id: Date.now(), day: 0, spots: 10, type: 'Cross' }
  ]);
  event.target.reset();
  renderAdmin();
  FORGE.notify('Új óra hozzáadva.');
});

renderAdmin();
