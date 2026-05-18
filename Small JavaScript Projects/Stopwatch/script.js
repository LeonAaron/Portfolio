let playBtn = document.querySelector(".play-btn");
let resetBtn = document.querySelector(".reset-btn");

let watch = document.querySelector(".watch");

let currSeconds = 0;
let on = false;

let id = null;


resetBtn.addEventListener("click", function(e) {
    on = false;
    currSeconds = 0;
    watch.innerText = "00:00:00";
    clearInterval(id);
    id = null;

    if (playBtn.classList.contains("pause-btn")) {
        playBtn.classList.remove("pause-btn");
        playBtn.innerHTML = "&blacktriangleright;";
    }
});

playBtn.addEventListener("click", function(e) {
    if(!on) {
        // Set state
        on = true;

        //Change playBtn
        playBtn.classList.add("pause-btn");
        playBtn.innerHTML = "||";
        // Incriment currSeconde by 1 every 1,000ms
        id = setInterval(() => {
            currSeconds += 1;
            updateWatch();
        }, 1000);
    } else {
        on = false;

        playBtn.classList.remove("pause-btn");
        playBtn.innerHTML = "&blacktriangleright;";

        clearInterval(id);
    }
});

function updateWatch() {
    let tempSeconds = currSeconds;
    let hours = Math.floor(tempSeconds / 3600);
    tempSeconds -= (hours * 3600);
    let minutes = Math.floor(tempSeconds / 60);
    let seconds = tempSeconds - (minutes * 60);
    
    hours = formatTime(hours);
    minutes = formatTime(minutes);
    seconds = formatTime(seconds);

    let result = hours + ":" + minutes + ":" + seconds
    watch.innerText = result;
}

function formatTime(value) {
    if (value < 10) {
        return "0" + value.toString();
    }
    return value.toString();
}

