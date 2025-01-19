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

    const editButton = document.querySelector(".edit .buttons_action");
    const editModal = document.getElementById("editModal");
    const closeEditModal = document.getElementById("closeEditModal");
    const editModalContent = editModal.querySelector(".modal-content");

    editButton.addEventListener("click", function () {
        editModal.classList.add("show");
        setTimeout(() => {
            editModalContent.classList.add("show");
        }, 50);
    });

    closeEditModal.addEventListener("click", function () {
        editModalContent.classList.remove("show");
        setTimeout(() => {
            editModal.classList.remove("show");
        }, 200);
    });

    window.addEventListener("click", function (event) {
        if (event.target === editModal) {
            editModalContent.classList.remove("show");
            setTimeout(() => {
                editModal.classList.remove("show");
            }, 200);
        }
    });

    const findButton = document.querySelector(".find .buttons_action");
    const findModal = document.getElementById("findModal");
    const closeFindModal = document.getElementById("closeFindModal");
    const findModalContent = findModal.querySelector(".modal-content");

    findButton.addEventListener("click", function () {
        findModal.classList.add("show");
        setTimeout(() => {
            findModalContent.classList.add("show");
        }, 50);
    });

    closeFindModal.addEventListener("click", function () {
        findModalContent.classList.remove("show");
        setTimeout(() => {
            findModal.classList.remove("show");
        }, 200);
    });

    window.addEventListener("click", function (event) {
        if (event.target === findModal) {
            findModalContent.classList.remove("show");
            setTimeout(() => {
                findModal.classList.remove("show");
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
