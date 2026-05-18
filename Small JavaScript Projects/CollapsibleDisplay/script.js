let toggleBtns = document.querySelectorAll(".toggle-btn");

let openQuestion = null;
let openBtn = null;

toggleBtns.forEach((btn) => {
    btn.addEventListener("click", toggleAnswer);
});

function toggleAnswer(e) {
    const clickedBtn = e.currentTarget;
    let answer = clickedBtn.parentElement.nextElementSibling;

    if (answer.classList.contains("hidden")) {
        answer.classList.remove("hidden");
        answer.classList.add("show");
        clickedBtn.innerText = "-";

        if (openQuestion) {
            openQuestion.classList.add("hidden");
            openQuestion.classList.remove("show");
            openBtn.innerText = "+";
        }

        openBtn = clickedBtn;
        openQuestion = answer;
    }  else {
        answer.classList.add("hidden");
        answer.classList.remove("show");
        clickedBtn.innerText = "+";
        openBtn = null;
        openQuestion = null;
    }
    
}