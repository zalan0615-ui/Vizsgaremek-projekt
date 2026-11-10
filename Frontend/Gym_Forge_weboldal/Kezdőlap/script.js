
document.addEventListener("DOMContentLoaded", function () {

    const navbar = document.getElementById("mainNavbar");

    if (!navbar) {
        return;
    }

    const navLinks = navbar.querySelectorAll(".nav-link");

    navLinks.forEach(function (link) {

        link.addEventListener("click", function () {

            if (window.innerWidth < 992) {

                const menu = bootstrap.Collapse.getOrCreateInstance(navbar);

                menu.hide();
            }

        });

    });

});
