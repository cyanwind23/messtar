const elmId = (id) => document.getElementById(id);
const elmClass = (className) => document.getElementsByClassName(className);

var input = elmId('msr-cb-input');
var placeholder = elmId('msr-cb-placeholder');
var chatTitleBtn = elmId('msr-ct');
var chatTitleMenu = elmId('msr-ct-menu');
var chatTitleMenuCloseBtn = elmId('msr-ct-menu-x');

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