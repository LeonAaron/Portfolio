// Search elements
let searchInput = document.querySelector(".search-input");
let searchBtn = document.querySelector(".search-btn");

// Display elements
let pokemonImg = document.querySelector(".pokemon-img");
let pokemonName = document.querySelector(".pokemon-name");

// Stats
let hp = document.querySelector(".hp");
let attack = document.querySelector(".attack");
let defense = document.querySelector(".defense");
let speed = document.querySelector(".speed");


async function getPokemon() {
    try {
        let name = searchInput.value.trim().toLowerCase();

        const response = await fetch(`https://pokeapi.co/api/v2/pokemon/${name}`);
        const data = await response.json();
        console.log(data);

        pokemonName.innerText = data.name;
        pokemonImg.src = data.sprites.front_default;
        pokemonImg.style.display = "block";
        data.stats.forEach(stat => {
            if (stat.stat.name === "hp") hp.innerText = stat.base_stat;
            if (stat.stat.name === "attack") attack.innerText = stat.base_stat;
            if (stat.stat.name === "defense") defense.innerText = stat.base_stat;
            if (stat.stat.name === "speed") speed.innerText = stat.base_stat;
        });
    } catch(error) {
        console.log(error);
    }
}

searchBtn.addEventListener("click", getPokemon);
searchInput.addEventListener("keydown", (e) => {
    if (e.key === "Enter") getPokemon();
});


