/** Regisztráció – kizárólag a böngészős demonstrációhoz. */
const registerForm = document.querySelector('#register-form');
const registerMessage = document.querySelector('#register-message');

registerForm.addEventListener('submit', (event) => {
  event.preventDefault();

  const name = registerForm.querySelector('#name').value;
  const email = registerForm.querySelector('#email').value;
  const password = registerForm.querySelector('#password').value;
  const confirmPassword = registerForm.querySelector('#password2').value;

  if (password !== confirmPassword) {
    registerMessage.textContent = 'A két jelszó nem egyezik!';
    return;
  }

  FORGE.store.set('user', { name, email, password });
  FORGE.store.set('session', { name, email });
  FORGE.notify('Demófiók létrehozva!');
  window.location.href = FORGE.url('personal');
});
