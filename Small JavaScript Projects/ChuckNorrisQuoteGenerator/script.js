const jokeBtn = document.querySelector(".joke-btn");
let quote = document.querySelector(".quote")

async function getJoke() {
    try {
        const response = await fetch("https://api.chucknorris.io/jokes/random");
        const data = await response.json();
        quote.innerText = data.value;
    } catch (error) {
        console.log(error);
    }
}

jokeBtn.addEventListener("click", getJoke);