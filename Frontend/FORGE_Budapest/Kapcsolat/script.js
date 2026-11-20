/** Kapcsolatfelvételi űrlap: demó mentés és Bootstrap visszajelző modal. */
const contactForm = document.querySelector('#contact-form');

contactForm.addEventListener('submit', (event) => {
  event.preventDefault();
  FORGE.store.set('contactMessage', {
    name: contactForm.querySelector('#name').value,
    email: contactForm.querySelector('#email').value,
    message: contactForm.querySelector('#message').value,
    date: new Date().toISOString()
  });
  contactForm.reset();

  FORGE.modal('SIKERESEN ELKÜLDVE!', `
    <div class="contact-success text-center">
      <div class="success-icon">✉</div>
      <p>Az üzenetedet rögzítettük a böngészős demóban.
         Az éles üzenetküldés még nincs bekötve.</p>
      <div class="d-flex gap-2 justify-content-center flex-wrap">
        <a class="btn btn-outline" href="${FORGE.url('home')}">VISSZA</a>
        <button class="btn" type="button" id="close-success">RENDBEN</button>
      </div>
    </div>`, (dialog) => {
    dialog.querySelector('#close-success').addEventListener('click', () => FORGE.closeModal(dialog));
  });
});
