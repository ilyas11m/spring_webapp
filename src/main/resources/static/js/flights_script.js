document.addEventListener("DOMContentLoaded", function () {
    const createButton = document.querySelector(".create .buttons_action");
    const createModal = document.getElementById("createModal");
    const closeCreateModal = document.getElementById("closeCreateModal");
    const createModalContent = createModal.querySelector(".modal-content");

    createButton.addEventListener("click", function () {
        createModal.classList.add("show");
        setTimeout(() => {
            createModalContent.classList.add("show");
        }, 50);
    });

    closeCreateModal.addEventListener("click", function () {
        createModalContent.classList.remove("show");
        setTimeout(() => {
            createModal.classList.remove("show");
        }, 200);
    });

    window.addEventListener("click", function (event) {
        if (event.target === createModal) {
            createModalContent.classList.remove("show");
            setTimeout(() => {
                createModal.classList.remove("show");
            }, 200);
        }
    });

    const deleteByIdButton = document.querySelector(".delete_by_id .buttons_action");
    const deleteByIdModal = document.getElementById("deleteByIdModal");
    const closeDeleteByIdModal = document.getElementById("closeDeleteByIdModal");
    const deleteByIdModalContent = deleteByIdModal.querySelector(".modal-content");

    deleteByIdButton.addEventListener("click", function () {
        deleteByIdModal.classList.add("show");
        setTimeout(() => {
            deleteByIdModalContent.classList.add("show");
        }, 50);
    });

    closeDeleteByIdModal.addEventListener("click", function () {
        deleteByIdModalContent.classList.remove("show");
        setTimeout(() => {
            deleteByIdModal.classList.remove("show");
        }, 200);
    });

    window.addEventListener("click", function (event) {
        if (event.target === deleteByIdModal) {
            deleteByIdModalContent.classList.remove("show");
            setTimeout(() => {
                deleteByIdModal.classList.remove("show");
            }, 200);
        }
    });
});
