/* ============================================================
   ADMIN BEJELENTKEZÉS - FORGE BUDAPEST
============================================================ */

const adminPassword = document.querySelector('#admin-password');

document.querySelector('.show-password').addEventListener('click', () => {
  adminPassword.type = adminPassword.type === 'password' ? 'text' : 'password';
});

document.querySelector('#admin-login').addEventListener('submit', (event) => {
  event.preventDefault();

  const name = document.querySelector('#admin-name').value;
  const password = adminPassword.value;

  if (name === 'admin' && password === 'forge123') {
    FORGE.store.set('adminSession', true);
    window.location.href = FORGE.url('admin');
  } else {
    document.querySelector('#admin-error').textContent = 'Hibás demó bejelentkezési adatok!';
  }
});
