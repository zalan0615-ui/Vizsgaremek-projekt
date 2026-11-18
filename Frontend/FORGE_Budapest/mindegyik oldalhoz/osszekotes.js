
/* =====================================
   FORGE BUDAPEST - BACKEND ÖSSZEKÖTÉS
===================================== */

(() => {
    "use strict";

    const API_URL = "http://localhost:8081";

    // Általános GET kérés
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

    // Közös backend függvények
    window.ForgeAPI = {
        get: apiGet
    };

    // Edzőterem adatainak betöltése
    async function loadGymInfo() {

        // Csak akkor fut, ha az oldalon
        // megtalálhatóak a statisztikák.
        const activeMembers =
            document.getElementById("activeMembers");

        if (!activeMembers) return;

        try {
            const result = await apiGet("/api/gym-info");

            // A backend List-et ad vissza
            const gym = Array.isArray(result)
                ? result[0]
                : result;

            if (!gym) {
                throw new Error("Nincs edzőtermi adat!");
            }

            // Aktív tagok száma
            ForgeUI.setText(
                "activeMembers",
                ForgeUI.formatNumber(gym.usersNumber)
            );

            // Nyitvatartás
            ForgeUI.setText(
                "openingHours",
                gym.openingHours
            );

            // Prémium gépek száma
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
            console.error(
                "Backend kapcsolati hiba:",
                error
            );

            ForgeUI.setText("activeMembers", "—");
            ForgeUI.setText("openingHours", "—");
            ForgeUI.setText("premiumMachines", "—");
        }
    }

    // Oldal betöltése után indul
    if (document.readyState === "loading") {
        document.addEventListener(
            "DOMContentLoaded",
            loadGymInfo
        );
    } else {
        loadGymInfo();
    }

})();
