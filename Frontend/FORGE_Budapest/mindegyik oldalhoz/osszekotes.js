
/* ============================================================
   FORGE BUDAPEST - BACKEND ÖSSZEKÖTÉS
============================================================ */

/* ----------------------------------------------------------
   1. BACKEND API CÍME
---------------------------------------------------------- */

const API_URL = "http://localhost:8081";


/* ----------------------------------------------------------
   2. ÁLTALÁNOS API KÉRÉS
---------------------------------------------------------- */

async function apiRequest(endpoint, method = "GET", data = null) {
    const options = {
        method,
        headers: {
            "Accept": "application/json"
        },
        credentials: "include"
    };

    if (data !== null) {
        options.headers["Content-Type"] = "application/json";
        options.body = JSON.stringify(data);
    }

    const response = await fetch(API_URL + endpoint, options);

    if (!response.ok) {
        throw new Error("API hiba: " + response.status);
    }

    if (response.status === 204) return null;

    const contentType = response.headers.get("content-type") || "";

    return contentType.includes("application/json")
        ? response.json()
        : response.text();
}


/* ----------------------------------------------------------
   3. GET - ADATOK LEKÉRÉSE
---------------------------------------------------------- */

export const apiGet = (endpoint) =>
    apiRequest(endpoint, "GET");


/* ----------------------------------------------------------
   4. POST - ÚJ ADAT LÉTREHOZÁSA
---------------------------------------------------------- */

export const apiPost = (endpoint, data) =>
    apiRequest(endpoint, "POST", data);


/* ----------------------------------------------------------
   5. PUT - ADAT MÓDOSÍTÁSA
---------------------------------------------------------- */

export const apiPut = (endpoint, data) =>
    apiRequest(endpoint, "PUT", data);


/* ----------------------------------------------------------
   6. DELETE - ADAT TÖRLÉSE
---------------------------------------------------------- */

export const apiDelete = (endpoint) =>
    apiRequest(endpoint, "DELETE");


/* ----------------------------------------------------------
   7. EXPORTÁLT API FÜGGVÉNYEK
---------------------------------------------------------- */

export const ForgeAPI = {
    get: apiGet,
    post: apiPost,
    put: apiPut,
    delete: apiDelete
};
