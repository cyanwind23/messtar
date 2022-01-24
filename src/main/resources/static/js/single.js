"use strict"
import {elmId, elmClass} from "./lib.js";
import {RoomCard} from "./models/RoomCard.js"

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
// let input = elmId('msr-cb-input');
// let placeholder = elmId('msr-cb-placeholder');
// let chatTitleBtn = elmId('msr-ct');
// let chatTitleMenu = elmId('msr-ct-menu');
// let chatTitleMenuCloseBtn = elmId('msr-ct-menu-x');
//
// // room icon
// let messtarRoomBtn = elmId("messtar-room-icon-btn");

// const displayRoomList = rooms => {
//     let roomList = elmId("msr-cl");
//     for (let room of rooms) {
//         roomList.innerHTML += new RoomCard("", room.roomId).toDOM();
//     }
// }

// const sendRequest = (destination, type, body) => {
//     // create request object
//     if (type === "GET") {
//         fetch(destination)
//             .then(res => res.json())
//             .then(res => console.log((res)));
//     } else if (type === "POST") {
//         let request = new Request(destination, {
//             method: 'POST',
//             headers: new Headers({
//                 'Content-Type': 'application/json',
//                 'X-CSRF-TOKEN': token,
//             })
//         });
//         fetch(request)
//             .then(res => res.json())
//             .then(res => console.log(res))
//     }
// }
// sendRequest("/room/single/getrooms", 'GET');

/**
 * Event listeners
 */
// input.addEventListener("keydown", (e) => {
//     if (e.key === "Enter" && !e.shiftKey) {
//         e.preventDefault();
//         input.innerHTML = "";
//         placeholder.classList.remove('hidden');
//     }
// });
// input.addEventListener("input", (e) => {
//     if (input.innerText.trim() !== "") {
//         placeholder.classList.add('hidden');
//     } else {
//         placeholder.classList.remove('hidden');
//     }
// });
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