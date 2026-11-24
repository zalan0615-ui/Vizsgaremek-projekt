
/* ============================================================
   FORGE BUDAPEST - BACKEND ÖSSZEKÖTÉS
============================================================ */

(() => {
    "use strict";

    /* ----------------------------------------------------------
       1. BACKEND API CÍMÉNEK BEÁLLÍTÁSA
    ---------------------------------------------------------- */

    const API_URL = "http://localhost:8081";


    /* ----------------------------------------------------------
       2. ÁLTALÁNOS GET KÉRÉS
    ---------------------------------------------------------- */

    async function apiGet(endpoint) {
        const response = await fetch(
            API_URL + endpoint,
            {
                method: "GET",
                headers: {
                    "Accept": "application/json"
                },
                cache: "no-store"
            }
        );

        if (!response.ok) {
            throw new Error(
                "API hiba: " + response.status
            );
        }

        return response.json();
    }


    /* ----------------------------------------------------------
       3. KÖZÖS BACKEND FÜGGVÉNYEK
    ---------------------------------------------------------- */

    window.ForgeAPI = {
        get: apiGet
    };


    /* ----------------------------------------------------------
       4. EDZŐTEREM ADATAINAK BETÖLTÉSE
    ---------------------------------------------------------- */

    async function loadGymInfo() {

        /* ------------------------------------------------------
           4.1. STATISZTIKAI ELEMEK ELLENŐRZÉSE
        ------------------------------------------------------ */

        const activeMembers =
            document.getElementById("activeMembers");

        if (!activeMembers) return;

        try {

            /* --------------------------------------------------
               4.2. ADATOK LEKÉRÉSE A BACKENDBŐL
            -------------------------------------------------- */

            const result = await apiGet("/api/gym-info");

            const gym = Array.isArray(result)
                ? result[0]
                : result;

            if (!gym) {
                throw new Error("Nincs edzőtermi adat!");
            }


            /* --------------------------------------------------
               4.3. AKTÍV TAGOK SZÁMÁNAK MEGJELENÍTÉSE
            -------------------------------------------------- */

            ForgeUI.setText(
                "activeMembers",
                ForgeUI.formatNumber(gym.usersNumber)
            );


            /* --------------------------------------------------
               4.4. NYITVATARTÁS MEGJELENÍTÉSE
            -------------------------------------------------- */

            ForgeUI.setText(
                "openingHours",
                gym.openingHours
            );


            /* --------------------------------------------------
               4.5. PRÉMIUM GÉPEK SZÁMÁNAK MEGJELENÍTÉSE
            -------------------------------------------------- */

            ForgeUI.setText(
                "premiumMachines",
                ForgeUI.formatNumber(
                    gym.premiumMachineNumber
                )
            );

            console.log(
                "Backend kapcsolat sikeres!",
                gym
            );

        } catch (error) {

            /* --------------------------------------------------
               4.6. HIBAKEZELÉS
            -------------------------------------------------- */

            console.error(
                "Backend kapcsolati hiba:",
                error
            );

            ForgeUI.setText("activeMembers", "—");
            ForgeUI.setText("openingHours", "—");
            ForgeUI.setText("premiumMachines", "—");
        }
    }


    /* ----------------------------------------------------------
       5. ADATOK AUTOMATIKUS BETÖLTÉSE
    ---------------------------------------------------------- */

    if (document.readyState === "loading") {
        document.addEventListener(
            "DOMContentLoaded",
            loadGymInfo
        );
    } else {
        loadGymInfo();
    }

})();
