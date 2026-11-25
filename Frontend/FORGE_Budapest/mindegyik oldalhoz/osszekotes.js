
/* ============================================================
   FORGE BUDAPEST - BACKEND ÖSSZEKÖTÉS
============================================================ */

/* ----------------------------------------------------------
   1. BACKEND API CÍME
---------------------------------------------------------- */

const API_URL = "http://localhost:8081";



/* ----------------------------------------------------------
   2. ÁLTALÁNOS API KÉRÉS - HIBAKERESÉS
---------------------------------------------------------- */

async function apiRequest(endpoint, method = "GET", data = null) {
    const url = API_URL + endpoint;
    const token = sessionStorage.getItem("forge_token");

    console.log("[FORGE API] Kérés indítása:", method, url);

     
/* ----------------------------------------------------------
   2.1. HTTP KÉRÉS BEÁLLÍTÁSA
---------------------------------------------------------- */

const options = {
    method,
    headers: {
        "Accept": "application/json"
    }
};


    if (token) {
        options.headers["Authorization"] = `Bearer ${token}`;
        console.log("[FORGE API] JWT token hozzáadva.");
    } else {
        console.log("[FORGE API] Nincs mentett JWT token.");
    }

    if (data !== null) {
        options.headers["Content-Type"] = "application/json";
        options.body = JSON.stringify(data);

        // A jelszót soha nem írjuk a konzolra!
        console.log("[FORGE API] JSON adatok elküldése:",
            Object.keys(data));
    }

    try {
        const response = await fetch(url, options);

        console.log("[FORGE API] HTTP státusz:", response.status);
        console.log("[FORGE API] Válasz sikeres:", response.ok);

        if (!response.ok) {
            const rawError = await response.text();
            let errorMessage = `HTTP ${response.status}`;

            try {
                const error = JSON.parse(rawError);
                errorMessage = error.message || error.error || errorMessage;
            } catch {
                // Nem JSON hibaválasz
            }

            console.error("[FORGE API] Sikertelen kérés:", {
                method,
                endpoint,
                status: response.status,
                message: errorMessage
            });

            throw new Error(errorMessage);
        }

        if (response.status === 204) {
            console.log("[FORGE API] Sikeres, üres válasz.");
            return null;
        }

        const rawResponse = await response.text();

        if (!rawResponse) return null;

        let result;

        try {
            result = JSON.parse(rawResponse);
        } catch {
            result = rawResponse;
        }

        console.log("[FORGE API] Kérés sikeresen feldolgozva.");

        return result;

    } catch (error) {
        console.error("[FORGE API] Hiba:", {
            method,
            endpoint,
            message: error.message
        });

        throw error;
    }
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
