let addBtn = document.querySelector(".add-btn");
let input = document.querySelector(".task-input");
let taskList = document.querySelector(".task-list");

// Extra varaible to keep track of task count
let tasks = 0;

addBtn.addEventListener("click", function(e) {
    let task = input.value;
    if(input.value !== "") {
        input.value = "";
        taskList.innerHTML += createTask(task);
        tasks++;
    }
});

taskList.addEventListener("click", (e) => {
    if (e.target.classList.contains("trash-btn")) {
        e.target.closest(".task").remove();
        tasks--;
    }
    
    if (e.target.classList.contains("check-btn")) {
        e.target.closest(".task").classList.toggle("checked");
    }
})
function createTask(task) {
    // Generate HTML For new task
    return `
        <div class="task">
                <h3 class="task-name">${task}</h3>
                <div class="task-btns">
                    <button class="check-btn">✔</button>
                    <button class="trash-btn">&#128465;</button>
                </div>
            </div>
    `
}

