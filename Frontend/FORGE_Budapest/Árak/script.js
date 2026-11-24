
/* ============================================================
   ÁRAK OLDAL - FORGE BUDAPEST
============================================================ */


/* ------------------------------------------------------------
   1. BÉRLETEK ADATAI ÉS ÁRAI
------------------------------------------------------------ */

const plans = [
  { name: 'NAPIJEGY', price: 3500 },
  { name: 'HAVI TAGSÁG', price: 19900 },
  { name: 'PRÉMIUM+', price: 34900 }
];


/* ------------------------------------------------------------
   2. BÉRLETVÁSÁRLÁS GOMBOK ESEMÉNYKEZELÉSE
------------------------------------------------------------ */

document.querySelectorAll('[data-buy]').forEach((button) => {

  button.addEventListener('click', () => {

    const plan = plans[Number(button.dataset.buy)];


    /* --------------------------------------------------------
       3. BÉRLETVÁLASZTÁS - FELUGRÓ ABLAK
    -------------------------------------------------------- */

    FORGE.modal('BÉRLETVÁLASZTÁS', `

      <p>
        Választott csomag:
        <strong style="color:var(--lime)">
          ${plan.name} · ${plan.price.toLocaleString('hu-HU')} Ft
        </strong>
      </p>

      <p>
        Bemutató fizetés – valódi levonás nem történik.
      </p>


      <!-- VÁSÁRLÁSI ŰRLAP -->
      <form id="purchase-form">

        <div class="field">
          <label>Felhasználónév</label>
          <input name="name" required>
        </div>

        <div class="field">
          <label>E-mail cím</label>
          <input type="email" name="email" required>
        </div>

        <button class="btn" type="submit">
          Demo vásárlás mentése
        </button>

      </form>

    `, (modal) => {


      /* ------------------------------------------------------
         4. VÁSÁRLÁSI ŰRLAP ELKÜLDÉSE
      ------------------------------------------------------ */

      modal.querySelector('form').addEventListener('submit', (event) => {

        event.preventDefault();

        const data = Object.fromEntries(
          new FormData(event.target)
        );


        /* ----------------------------------------------------
           5. DEMÓ VÁSÁRLÁS MENTÉSE
        ---------------------------------------------------- */


        FORGE.store.set('purchase', {
          ...data,
          plan: plan.name,
          price: plan.price,
          date: new Date().toISOString()
        });


        /* ----------------------------------------------------
           6. FELUGRÓ ABLAK BEZÁRÁSA ÉS ÉRTESÍTÉS
        ---------------------------------------------------- */


        FORGE.closeModal(modal);


        FORGE.notify('Demó bérlet rögzítve.');


      });


    });


  });


});
