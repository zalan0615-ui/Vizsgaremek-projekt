
/* ============================================================
   BEJELENTKEZÉS - FORGE BUDAPEST
============================================================ */

/* ----------------------------------------------------------
   1. BACKEND BEJELENTKEZÉS IMPORTÁLÁSA
---------------------------------------------------------- */

import { loginUser } from "../mindegyik oldalhoz/login.js";

console.log("[LOGIN] Bejelentkezési modul betöltve.");

/* ----------------------------------------------------------
   2. HTML ELEMEK ELLENŐRZÉSE
---------------------------------------------------------- */

const passwordInput = document.querySelector("#password");
const loginMessage = document.querySelector("#login-message");
const loginForm = document.querySelector("#login-form");
const emailInput = document.querySelector("#email");

console.log("[LOGIN] HTML elemek:", {
    form: !!loginForm,
    email: !!emailInput,
    password: !!passwordInput,
    message: !!loginMessage
});

if (!loginForm || !emailInput || !passwordInput || !loginMessage) {
    console.error("[LOGIN] Hiányzó HTML elem! Ellenőrizd az ID-kat.");
}

/* ----------------------------------------------------------
   3. JELSZÓ MEGJELENÍTÉSE
---------------------------------------------------------- */

const showPassword = document.querySelector(".show-password");

if (showPassword && passwordInput) {
    showPassword.addEventListener("click", () => {
        passwordInput.type =
            passwordInput.type === "password" ? "text" : "password";
    });
} else {
    console.warn("[LOGIN] A jelszó megjelenítése gomb nem található.");
}

/* ----------------------------------------------------------
   4. ELFELEJTETT JELSZÓ
---------------------------------------------------------- */

const forgotPassword = document.querySelector("#forgot-password");

if (forgotPassword) {
    forgotPassword.addEventListener("click", (event) => {
        event.preventDefault();
        FORGE.notify("A jelszó-visszaállítás még nincs bekötve.");
    });
}

/* ----------------------------------------------------------
   5. BEJELENTKEZÉS ELKÜLDÉSE
---------------------------------------------------------- */

if (loginForm && emailInput && passwordInput && loginMessage) {

    loginForm.addEventListener("submit", async (event) => {
        event.preventDefault();

        console.log("[LOGIN] Bejelentkezési gomb megnyomva.");

        const identifier = emailInput.value.trim();
        const password = passwordInput.value;
        const submitButton =
            loginForm.querySelector('button[type="submit"]');

        loginMessage.textContent = "";

        if (!identifier || !password) {
            console.error("[LOGIN] Hiányzó e-mail/felhasználónév vagy jelszó.");
            loginMessage.textContent = "Minden mezőt ki kell tölteni!";
            return;
        }

        console.log("[LOGIN] Azonosító:", identifier);
        console.log("[LOGIN] Jelszó megadva:", password.length > 0);

        /* ----------------------------------------------------------
           6. BACKEND MEGHÍVÁSA
        ---------------------------------------------------------- */

        try {
            if (submitButton) submitButton.disabled = true;

            console.log("[LOGIN] POST /api/auth/login indítása.");

            const result = await loginUser(identifier, password);

            console.log("[LOGIN] Backend bejelentkezés sikeres.");
            console.log("[LOGIN] Token érkezett:", !!result?.token);

            /* ----------------------------------------------------------
               7. MUNKAMENET MENTÉSE
            ---------------------------------------------------------- */

            FORGE.store.set("session", {
                name: identifier
            });

            console.log("[LOGIN] Frontend munkamenet mentve.");

            /* ----------------------------------------------------------
               8. ÁTIRÁNYÍTÁS
            ---------------------------------------------------------- */

            FORGE.notify("Sikeres bejelentkezés!");

            console.log("[LOGIN] Átirányítás a saját órarendre.");

            window.location.href = FORGE.url("personal");

        } catch (error) {

            /* ----------------------------------------------------------
               9. HIBA MEGJELENÍTÉSE
            ---------------------------------------------------------- */

            console.error("[LOGIN] BEJELENTKEZÉSI HIBA:", error);
            console.error("[LOGIN] Hiba típusa:", error.name);
            console.error("[LOGIN] Hibaüzenet:", error.message);

            loginMessage.textContent =
                "Sikertelen bejelentkezés! " + error.message;

        } finally {
            if (submitButton) submitButton.disabled = false;
        }
    });

} else {
    console.error("[LOGIN] Nem sikerült regisztrálni az űrlap eseménykezelőjét.");
}
