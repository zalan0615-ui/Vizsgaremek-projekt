/** Bejelentkezés – csak böngészőben tárolt DEMÓ fiókokkal működik. */
const passwordInput = document.querySelector('#password');
const loginMessage = document.querySelector('#login-message');

document.querySelector('.show-password').addEventListener('click', () => {
  passwordInput.type = passwordInput.type === 'password' ? 'text' : 'password';
});

document.querySelector('#forgot-password').addEventListener('click', (event) => {
  event.preventDefault();
  FORGE.notify('Jelszó-visszaállítás csak backend bekötése után elérhető.');
});

document.querySelector('#login-form').addEventListener('submit', (event) => {
  event.preventDefault();

  const email = document.querySelector('#email').value;
  const password = passwordInput.value;
  const user = FORGE.store.get('user', null);

  if (user && user.email === email && user.password === password) {
    FORGE.store.set('session', { name: user.name, email });
    FORGE.notify('Sikeres demó bejelentkezés!');
    window.location.href = FORGE.url('personal');
  } else {
    loginMessage.textContent = 'Nincs egyező demó fiók. Előbb regisztrálj.';
  }
});
