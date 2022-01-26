import {Message, MessageGroup} from "./models/Message.js";

const elmId = (id) => document.getElementById(id);
const elmClass = (className) => document.getElementsByClassName(className);

// TODO: must be unify structure and name of messageDto in all methods and classes
/** Load context then load mess for room **/
let lstMess;
let lastMessGr;
let roomContext;
let toUser; // use it for quickly send message in SINGLE room
let paths = window.location.pathname.split("/")
let chatView = elmId("msr-cv");

const displayMess = messDto => {
    let isOwnMess = messDto.sender === roomContext.loggedUser;
    if (!lastMessGr || lastMessGr.sender !== messDto.sender) {
        // create new group then display
        lastMessGr = new MessageGroup(messDto.sender, isOwnMess);
        chatView.appendChild(lastMessGr.buildDom());
    }
    lastMessGr.addMessage(new Message(messDto, isOwnMess).buildDom())
    // scroll chatView to bottom
    chatView.scrollTop = chatView.scrollHeight;
}
// Load roomContext include messages
fetch("/room/context/" + paths[paths.length - 1])
    .then(res => res.json())
    .then(res => {
        roomContext = res;
        // console.log(roomContext);
        lstMess = roomContext.messages.reverse();
        // TODO: write method that display first 30 messages
        for (let mess of lstMess) {
            displayMess(mess);
        }
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
    // TODO: [sendMess] - should add time in message payload before send to server, then server must save it
    // TODO: message may need to set dynamic type
    if (text.length < 1) {return;}
    let now = Date.now(); // this return milliseconds since 01/01/1970 00:00:00
    let message = {
        "sender": roomContext.loggedUser,
        "roomId": roomContext.room.roomId,
        "toUser": toUser,
        "type": "TEXT",
        "content": text,
        "createdTime": now,
        "modified" : now
    }
    // console.log(message);
    displayMess(message);
    sendRequest("/send/user/", message);
}

/** Subscribe all chanels **/
// Just subscribe to user and all multiple rooms
let stompClient;
let multipleRooms;
const notifyMessage = (message, notify) => {
    let toRoom;
    for (let room of roomList) {
        if (room.getAttribute("roomId") === message.roomId) {
            toRoom = room;
            break;
        }
    }
    // if option `notify` then add notify that this room has new mess
    if (notify) {
        toRoom.classList.add("notify-unseen");
    }
    let chatList = elmId("msr-cl");
    chatList.prepend(toRoom);
}
const onReceive = response => {
    let message = JSON.parse(response.body)
    // if coming message is not in this room
    console.log(response, message);
    if (roomContext.room.roomId !== message.roomId) {
        notifyMessage(message, true);
    } else {
        notifyMessage(message, false);
        displayMess(message);
    }
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