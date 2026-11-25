
/* ============================================================
   FORGE BUDAPEST - FELHASZNÁLÓI BEJELENTKEZÉS
============================================================ */

/* ----------------------------------------------------------
   1. BACKEND API IMPORTÁLÁSA
---------------------------------------------------------- */

import { apiPost } from "./osszekotes.js";


/* ----------------------------------------------------------
   2. FELHASZNÁLÓ BEJELENTKEZTETÉSE
---------------------------------------------------------- */

export async function loginUser(identifier, password) {

    const response = await apiPost("/api/auth/login", {
        identifier: identifier,
        password: password
    });

    /* ----------------------------------------------------------
       3. JWT TOKEN ELLENŐRZÉSE
    ---------------------------------------------------------- */

    if (!response || !response.token) {
        throw new Error("Nem érkezett token a szervertől.");
    }

    /* ----------------------------------------------------------
       4. TOKEN ELMENTÉSE
    ---------------------------------------------------------- */

    sessionStorage.setItem("forge_token", response.token);

    return response;
}


/* ----------------------------------------------------------
   5. KIJELENTKEZÉS
---------------------------------------------------------- */

export function logoutUser() {
    sessionStorage.removeItem("forge_token");
    FORGE.store.set("session", null);
}


/* ----------------------------------------------------------
   6. BEJELENTKEZÉS ÁLLAPOTÁNAK ELLENŐRZÉSE
---------------------------------------------------------- */

export function isLoggedIn() {
    return !!sessionStorage.getItem("forge_token");
}
