const elmId = (id) => document.getElementById(id);
const elmClass = (className) => document.getElementsByClassName(className);

let input = elmId('msr-cb-input');
let placeholder = elmId('msr-cb-placeholder');
let chatTitleBtn = elmId('msr-ct');
let chatTitleMenu = elmId('msr-ct-menu');
let chatTitleMenuCloseBtn = elmId('msr-ct-menu-x');

// room icon
let messtarRoomBtn = elmId("messtar-room-icon-btn");

/**
 * Event listeners
 */
input.addEventListener("keydown", (e) => {
    if (e.key === "Enter" && !e.shiftKey) {
        e.preventDefault();
        input.innerHTML = "";
        placeholder.classList.remove('hidden');
    }
});
input.addEventListener("input", (e) => {
    if (input.innerText.trim() !== "") {
        placeholder.classList.add('hidden');
    } else {
        placeholder.classList.remove('hidden');
    }
});

chatTitleBtn.addEventListener("click", (e) => {
    chatTitleMenu.classList.toggle('hidden');
});

chatTitleMenuCloseBtn.addEventListener("click", (e) => {
    chatTitleMenu.classList.add('hidden');
});

// room icon
messtarRoomBtn.addEventListener("click", (e) => {
    let currentPathName = window.location.pathname;
    if (currentPathName !== "/room/messtar") {
        window.location.href = "/room/messtar";
    }
});