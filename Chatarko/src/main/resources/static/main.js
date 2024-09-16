var korisnickaStrana  = document.querySelector("#username-page");
var chatStrana = document.querySelector("#chat-page");
var korisnickaForma = document.querySelector("#usernameForm");
var porukaskaForma = document.querySelector("#messageForm");

var inputPoruke = document.querySelector("#message");
var svePoruke = document.querySelector("#messageArea");
var ucitavanje = document.querySelector("#connecting");

var StompKlijent = null;
var korisnik = null;

var boje = ['#2196F3', '#32c787', '#00BCD4', '#ff5652', '#ffc107', '#ff85af', '#FF9800', '#39bbb0'];

const konektujSe = (event) => {
    event.preventDefault();
    korisnik = document.querySelector("#name").value.trim();

    if (korisnik) {
        korisnickaStrana.classList.add("hidden");
        chatStrana.classList.remove("hidden");

        var soket = new SockJS("/ws");
        StompKlijent = Stomp.over(soket);

        StompKlijent.connect({}, onKonekcija, onError);
    }
};

function getAvatarcicBoju(messageSender) {
    var hash = 0;
    for (var i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
    }
    var index = Math.abs(hash % boje.length); // Corrected to use 'boje' instead of 'colors'
    return boje[index];
}

const onKonekcija = () => {
    // Subscribe to the public topic to receive messages
    StompKlijent.subscribe("/topic/public", onPorukaPrimljena);

    // Send the user's name to the server
    StompKlijent.send("/app/chat.dodajKorisnika", {}, JSON.stringify({posiljalac: korisnik, tipPoruke: "ULAZ"}));

    ucitavanje.classList.add("hidden");
};

const onPorukaPrimljena = (payload) => {
    var poruka = JSON.parse(payload.body);

    // Create message UI element
    var elementPoruke = document.createElement("li");

    if (poruka.tipPoruke === "ULAZ") {
        elementPoruke.classList.add("event-message");
        poruka.content = poruka.posiljalac + " se ukljucio u chat";
    } else if (poruka.tipPoruke === "IZLAZ") {
        elementPoruke.classList.add("event-message");
        poruka.content = poruka.posiljalac + " je napustio chat";
    } else {
        elementPoruke.classList.add("chat-message");

        var avatarcicElement = document.createElement("i");
        var avatarcicTekst = document.createTextNode(poruka.posiljalac[0]);
        avatarcicElement.appendChild(avatarcicTekst);
        avatarcicElement.style["background-color"] = getAvatarcicBoju(poruka.posiljalac);

        elementPoruke.appendChild(avatarcicElement);

        var korisnickiElement = document.createElement("span");
        var korisnickiTekst = document.createTextNode(poruka.posiljalac);
        korisnickiElement.appendChild(korisnickiTekst);
        elementPoruke.appendChild(korisnickiElement);
    }

    var textElement = document.createElement("p");
    var tekstPoruke = document.createTextNode(poruka.content);

    textElement.appendChild(tekstPoruke);
    elementPoruke.appendChild(textElement);

    svePoruke.appendChild(elementPoruke);
    svePoruke.scrollTop = svePoruke.scrollHeight;
};

const posaljiPoruku = (event) => {
    event.preventDefault();
    var tijeloPoruke = inputPoruke.value.trim();

    // Check if there is a message and if the connection is active
    if (tijeloPoruke && StompKlijent) {
        var ChatPoruka = {
            posiljalac: korisnik,
            tijeloPoruke: tijeloPoruke, // Corrected key-value format
            tipPoruke: "CHAT"
        };

        StompKlijent.send("/app/chat.posaljiPoruku", {}, JSON.stringify(ChatPoruka));
        inputPoruke.value = ""; // Clear the message input
    }
};

const onError = () => {
    ucitavanje.textContent = "Nije moguće uspostaviti vezu!";
    ucitavanje.style.color = "red"; // Fixed typo 'collor' to 'color'
};

korisnickaForma.addEventListener('submit', konektujSe);
porukaskaForma.addEventListener('submit', posaljiPoruku);
