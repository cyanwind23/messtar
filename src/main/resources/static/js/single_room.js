const elmId = (id) => document.getElementById(id);
const elmClass = (className) => document.getElementsByClassName(className);

/** Load context **/
let roomContext;
let toUser; // use it for quickly send message in SINGLE room
let paths = window.location.pathname.split("/")
fetch("/room/context/" + paths[paths.length - 1])
    .then(res => res.json())
    .then(res => {
        roomContext = res;
        // diplayRoomInfo();
    });

/** navigating rooms - room list **/
let roomList = document.querySelectorAll(".js-room");
for (let room of roomList) {
    room.addEventListener("click", () => {
        let goToUrl = "/room/single/" + room.getAttribute("roomId");
        if (goToUrl !== window.location.pathname) {
            window.location.href = goToUrl;
        }
    });
}

/** Define POST fetch function **/
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
    fetch(request)
        .then(res => res.json())
        .then(res => console.log(res));
}
const validateInput = text => {
    // TODO: `text` need to be validated stronger
    return text.trim();
}
const sendMess = text => {
    if (!toUser) {
        for (let user of roomContext.room.roomUsers) {
            if (user !== roomContext.loggedUser) {
                toUser = user;
            }
        }
    }

    text = validateInput(text);
    console.log(text);
    if (text.length < 1) {return;}
    let message = {
        "sender": roomContext.loggedUser,
        "roomId": roomContext.room.roomId,
        "toUser": toUser,
        "type": "TEXT",
        "content": text
    }
    console.log(message);
    sendRequest("/send/user/", message);
}

/** Subscribe all chanels **/
// Just subscribe to user and all multiple rooms
let stompClient;
let multipleRooms;
const displayMessage = message => {
    let mess = elmClass("msr-mess-ct")[0];
    mess.innerText = message.content;
}

const onReceive = response => {
    console.log(response);
    displayMessage(JSON.parse(response.body));
}
const onError = () => {
    console.log("Connect to Room: failed!");
}
const onConnected = options => {
    // Subscribe for room
    multipleRooms.forEach(room => {
        stompClient.subscribe("/room/" + room.roomId, onReceive);
    })

    // Subscribe for notification
    stompClient.subscribe("/user/queue/message", onReceive);
}
const subscribe = () => {
    const socket = new SockJS('http://localhost:8080/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnected, onError);
}
fetch("/room/multiple/getrooms")
    .then(res => res.json())
    .then(res => {
        multipleRooms = res;
        subscribe();
    })

/**
 * Event listeners
 */
let input = elmId('msr-cb-input');
let placeholder = elmId('msr-cb-placeholder');
input.addEventListener("keydown", (e) => {
    if (e.key === "Enter" && !e.shiftKey) {
        e.preventDefault();
        sendMess(input.innerText);
        input.innerText = "";
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