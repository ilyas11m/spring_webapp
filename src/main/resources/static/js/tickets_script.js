document.addEventListener("DOMContentLoaded", function () {
    const createButton = document.querySelector(".create .buttons_action");
    const createModal = document.getElementById("createModal");
    const closeCreateModal = document.getElementById("closeCreateModal");
    const modalContent = createModal.querySelector(".modal-content");

    createButton.addEventListener("click", function () {
        createModal.classList.add("show"); 
        setTimeout(() => {
            modalContent.classList.add("show"); 
        }, 50);
    });

    closeCreateModal.addEventListener("click", function () {
        modalContent.classList.remove("show"); 
        setTimeout(() => {
            createModal.classList.remove("show"); 
        }, 200); 
    });

    window.addEventListener("click", function (event) {
        if (event.target === createModal) {
            modalContent.classList.remove("show"); 
            setTimeout(() => {
                createModal.classList.remove("show"); 
            }, 200);
        }
    });
});
