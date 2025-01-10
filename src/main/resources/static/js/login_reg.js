document.addEventListener("DOMContentLoaded", () => {
    const loginFrame = document.querySelector(".login_frame");
    const loginLink = document.querySelector(".front .log_link");
    const registerLink = document.querySelector(".back .log_link");

    loginLink.addEventListener("click", () => {
        loginFrame.classList.add("flipped");
    });

    registerLink.addEventListener("click", () => {
        loginFrame.classList.remove("flipped");
    });
});
