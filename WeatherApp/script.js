let searchBtn = document.querySelector(".search-btn");
let searchBar = document.querySelector(".search-bar");
let date = document.querySelector(".date");
let city = document.querySelector(".city");
let weatherImg = document.querySelector(".weather-img");
let imgLabel = document.querySelector(".img-label");
let temp = document.querySelector(".temp");
let lowTemp = document.querySelector(".low-temp");
let highTemp = document.querySelector(".high-temp");

// Date formatting
const months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
let today = new Date();

let day = today.getDate();
let month = months[today.getMonth()];
let year = today.getFullYear()

date.innerText = `${month} ${day}, ${year}`;

// Load initial UI with Greensboro
getWeather();

async function getWeather() {
    try {
        let searchCity = getCity();
        let response = await fetch(`https://api.openweathermap.org/data/2.5/weather?q=${searchCity}&appid=14a1acd1da1b8a72f607990a16e56e77&units=imperial`);
        let data = await response.json();
        console.log(data);

        updateDashboard(data);
    } catch (error) {
        console.log(error);
    }
}

function getCity() {
    // Return correctly formatted city from search input
    return searchBar.value.trim() || "Greensboro";
}

function updateDashboard(data) {
    if (invalidInput(data)) {
        return;
    };

    city.innerText = data.name;
    const icon = data.weather[0].icon;
    weatherImg.src = `https://openweathermap.org/img/wn/${icon}@2x.png`;
    imgLabel.innerText = data.weather[0].main;
    temp.innerText = Math.trunc(data.main.temp).toString() + "°F";
    lowTemp.innerText = data.main.temp_min.toString() + "°F";
    highTemp.innerText = data.main.temp_max.toString() + "°F";
}

function invalidInput(data) {
    if (data.cod != 200) {
        searchBar.value = "";
        searchBar.placeholder = "City not found";
        return true;
    }
    return false
}

searchBtn.addEventListener("click", getWeather);
searchBar.addEventListener("keydown", function(e) {
    if (e.key === "Enter") {
        getWeather();
    }
});
`
EXAMPLE JSON
{
    "coord": {
        "lon": -71.0598,
        "lat": 42.3584
    },
    "weather": [
        {
            "id": 803,
            "main": "Clouds",
            "description": "broken clouds",
            "icon": "04d"
        }
    ],
    "base": "stations",
    "main": {
        "temp": 71.71,
        "feels_like": 70.83,
        "temp_min": 67.41,
        "temp_max": 80.89,
        "pressure": 1021,
        "humidity": 48,
        "sea_level": 1021,
        "grnd_level": 1017
    },
    "visibility": 10000,
    "wind": {
        "speed": 10,
        "deg": 226,
        "gust": 20
    },
    "clouds": {
        "all": 81
    },
    "dt": 1779124921,
    "sys": {
        "type": 2,
        "id": 2003257,
        "country": "US",
        "sunrise": 1779096003,
        "sunset": 1779148892
    },
    "timezone": -14400,
    "id": 4930956,
    "name": "Boston",
    "cod": 200
}
    `