import {ElementBuilder} from "./ElementBuilder.js";
import {DateTimeUlti} from "../ulti/DateTimeUlti.js";

export class Message {
    constructor(messDto, isOwnMess) {
        this.id = messDto.messageId;
        this.sender = messDto.sender;
        this.roomId = messDto.roomId;
        this.toUser = messDto.toUser;
        this.type = messDto.type;
        this.content = messDto.content;
        this.createdTime = DateTimeUlti.toDateTime(messDto.createdTime);
        this.modified = DateTimeUlti.toDateTime(messDto.modified);
        this.status = messDto.status;
        this.isOwnMess = isOwnMess;
    }
    buildDom() {
        // chose style for own mess or other's mess
        // TODO: it should be added to lib so that we can chose from lib
        let timeClass = "msr-mess-t to-btm h-min txt-r fs-s";
        let cntClass = "flex-row bd-r10 msr-mess";

        if (this.isOwnMess) {
            cntClass = "flex-row fl-rrv bd-r10 msr-mess";
            timeClass = "msr-mess-t to-btm h-min fs-s";
        }

        let content = new ElementBuilder().creat("div")
            .withClass("bd-r10 msr-mess-ct")
            .withInnerText(this.content)
            .build();
        let replyIcon = new ElementBuilder().creat("div")
            .withClass("msr-mess-rpl pd-r5 to-btm")
            .withChild(
                new ElementBuilder().creat("img")
                    .withClass("icon-xxs btn")
                    .withAttribute("src", "/img/reply.png")
                    .build()
            )
            .build()
        let menuIcon = new ElementBuilder().creat("div")
            .withClass("msr-mess-mn to-btm")
            .withChild(
                new ElementBuilder().creat("img")
                    .withClass("icon-xxs btn")
                    .withAttribute("src", "/img/more.png")
                    .build()
            )
            .build()
        let time = new ElementBuilder().creat("div")
            .withClass(timeClass)
            .withInnerText(DateTimeUlti.toString(this.createdTime))
            .build();
        return new ElementBuilder().creat("div")
            .withClass(cntClass)
            .withChildren(content, replyIcon, menuIcon, time)
            .build();
    }
}

export class MessageGroup {
    constructor(sender, isOwnMess) {
        this.isOwnMess = isOwnMess;
        this.sender = sender;
        this.initChildren();
    }
    initChildren() {
        this.senderIcon = new ElementBuilder().creat("div")
            .withClass("mg-l-5 mg-bt-5 bd-r10 to-btm")
            .withChild(
                new ElementBuilder().creat("img")
                    .withClass("icon-s")
                    // TODO: change to user avatar url here
                    .withAttribute("src", "/img/messages.png")
                    .build()
            )
            .build()

        this.messageCtn = new ElementBuilder().creat("div")
            .withClass("msr-mgr full-size pd-5")
            .build();
    }
    addMessage(message) {
        this.messageCtn.appendChild(message);
    }
    buildDom() {
        // chose style for own mess or other's mess
        // TODO: it should be added to lib so that we can chose from lib
        if (this.isOwnMess) {
            return new ElementBuilder().creat("div")
                .withClass("flex-row mg-bt-5")
                .withChildren(this.messageCtn)
                .build();
        }
        return new ElementBuilder().creat("div")
            .withClass("flex-row mg-bt-5")
            .withChildren(this.senderIcon, this.messageCtn)
            .build();
    }
}