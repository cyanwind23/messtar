const elmId = (id) => document.getElementById(id);
const elmClass = (className) => document.getElementsByClassName(className);

let roomContext;
let toUser; // use it for quckly send message in SINGLE room
let paths = window.location.pathname.split("/")
fetch("/room/context/" + paths[paths.length - 1])
    .then(res => res.json())
    .then(res => roomContext = res);

let input = elmId('msr-cb-input');
let placeholder = elmId('msr-cb-placeholder');
// let chatTitleBtn = elmId('msr-ct');
// let chatTitleMenu = elmId('msr-ct-menu');
// let chatTitleMenuCloseBtn = elmId('msr-ct-menu-x');
//
// // room icon
// let messtarRoomBtn = elmId("messtar-room-icon-btn");
//


// chatTitleBtn.addEventListener("click", (e) => {
//     chatTitleMenu.classList.toggle('hidden');
// });
//
// chatTitleMenuCloseBtn.addEventListener("click", (e) => {
//     chatTitleMenu.classList.add('hidden');
// });
//
// // room icon
// messtarRoomBtn.addEventListener("click", (e) => {
//     let currentPathName = window.location.pathname;
//     if (currentPathName !== "/room/messtar") {
//         window.location.href = "/room/messtar";
//     }
// });

// navigating rooms
let roomList = document.querySelectorAll(".js-room");
for (let room of roomList) {
    room.addEventListener("click", () => {
        let goToUrl = "/room/single/" + room.getAttribute("roomId");
        if (goToUrl !== window.location.pathname) {
            window.location.href = goToUrl;
        }
    });
}

// fetch room info
let csrfToken = elmId("_csrf").value;

const sendRequest = (destination, body) => {
    let request = new Request(destination, {
        method: 'POST',
        headers: new Headers({
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': csrfToken,
        }),
        body: JSON.stringify(body)
    });
    // console.log(request);
    // pass request object to `fetch()`
    fetch(request)
        .then(res => res.json())
        .then(res => console.log(res));
}

const sendMess = text => {
    if (!toUser) {
        for (user of roomContext.room.roomUsers) {
            if (user !== roomContext.loggedUser) {
                toUser = user;
            }
        }
    }

    // TODO: `text` need to be validated
    if (text.length < 1) {return;}
    let message = {
        "sender": roomContext.loggedUser,
        "roomId": null,
        "to_username": toUser,
        "type": "TEXT",
        "content": text
    }
    sendRequest("/send/user/", message);
}

/**
 * Event listeners
 */
input.addEventListener("keydown", (e) => {
    if (e.key === "Enter" && !e.shiftKey) {
        e.preventDefault();
        sendMess(input.innerHTML);
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