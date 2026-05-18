let openBtn = document.querySelector(".open-btn");
let closeBtn = document.querySelector(".close-btn");
let overlay = document.querySelector(".overlay");


openBtn.addEventListener("click", openNotification);

function openNotification() {
    let hidden = document.querySelectorAll(".hidden");

    hidden.forEach((item) => {
        item.classList.remove("hidden");
        item.classList.add("shown");
    });
}

closeBtn.addEventListener("click", closeNotification);
overlay.addEventListener("click", closeNotification);

function closeNotification() {
    let shown = document.querySelectorAll(".shown");

    shown.forEach((item) => {
        item.classList.remove("shown");
        item.classList.add("hidden");
    });
}