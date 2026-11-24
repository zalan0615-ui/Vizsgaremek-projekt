
/* ============================================================
   KEZDŐLAP - FORGE BUDAPEST
============================================================ */

/* ----------------------------------------------------------
   1. BACKEND API IMPORTÁLÁSA
---------------------------------------------------------- */

import { apiGet } from "../mindegyik oldalhoz/osszekotes.js";


/* ----------------------------------------------------------
   2. EDZŐTEREM ADATAINAK BETÖLTÉSE
---------------------------------------------------------- */

async function loadGymInfo() {
    try {
        const result = await apiGet("/api/gym-info");
        const gym = Array.isArray(result) ? result[0] : result;

        if (!gym) {
            throw new Error("Nincs edzőtermi adat!");
        }

        /* ----------------------------------------------------------
           3. STATISZTIKÁK MEGJELENÍTÉSE
        ---------------------------------------------------------- */

        ForgeUI.setText("activeMembers", ForgeUI.formatNumber(gym.usersNumber));
        ForgeUI.setText("openingHours", gym.openingHours);
        ForgeUI.setText("premiumMachines", ForgeUI.formatNumber(gym.premiumMachineNumber));

        console.log("Backend kapcsolat sikeres!", gym);

    } catch (error) {
        console.error("Backend kapcsolati hiba:", error);
        ForgeUI.setText("activeMembers", "—");
        ForgeUI.setText("openingHours", "—");
        ForgeUI.setText("premiumMachines", "—");
    }
}


/* ----------------------------------------------------------
   4. ADATOK AUTOMATIKUS BETÖLTÉSE
---------------------------------------------------------- */

loadGymInfo();
