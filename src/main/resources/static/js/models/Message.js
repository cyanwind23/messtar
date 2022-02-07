import {ElementBuilder} from "./ElementBuilder.js";
import {DateTimeUtil} from "../util/DateTimeUtil.js";

export class Message {
    constructor(messDto, isOwnMess) {
        this.id = messDto.messageId;
        this.sender = messDto.sender;
        this.roomId = messDto.roomId;
        this.toUser = messDto.toUser;
        this.type = messDto.type;
        this.content = messDto.content;
        this.createdTime = DateTimeUtil.toDateTime(messDto.createdTime);
        this.modified = DateTimeUtil.toDateTime(messDto.modified);
        this.status = messDto.status;
        this.isOwnMess = isOwnMess;
    }
    buildDom() {
        let messBg = this.isOwnMess ? "cyan" : "darkgray-1";

        let content = new ElementBuilder().creat("div")
            .withClass("msr-mess-ct bd-r10")
            .withClass(messBg)
            .withInnerText(this.content)
            .build();

        let menu = new MessageMenu().buildDom();

        let time = new ElementBuilder().creat("div")
            .withClass("invisible")
            .withClass("msr-mess-t to-btm h-min txt-r fs-s")
            .withInnerText(DateTimeUtil.toString(this.createdTime))
            .build();
        let mess = new ElementBuilder().creat("div")
            .withClass("flex-row bd-r10 msr-mess")
            .withChildren(content, menu, time)
            .withAttribute("mid", this.id)
            .build();
        mess.addEventListener("mouseover", () => {
            menu.classList.remove("invisible");
            time.classList.remove("invisible");
        });
        mess.addEventListener("mouseout", () => {
            menu.classList.add("invisible");
            time.classList.add("invisible");
        })
        return mess;
    }
}

export class MessageGroup {
    constructor(sender) {
        this.sender = sender;
        this.initChildren();
    }
    initChildren() {
        this.senderIcon = new ElementBuilder().creat("div")
            .withClass("mg-l-5 mg-bt-5 bd-r10 to-btm")
            .withChild(
                new ElementBuilder().creat("img")
                    .withClass("icon-s bd-r5")
                    // TODO: change to user avatar url here
                    .withAttribute("src", this.sender.avatarId)
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
        return new ElementBuilder().creat("div")
            .withClass("flex-row mg-bt-5")
            .withChildren(this.senderIcon, this.messageCtn)
            .build();
    }
}
export class MessageMenu {
    constructor() {
    }
    buildDom() {
        let replyIcon = new ElementBuilder().creat("img")
            .withClass("icon-xxs btn msr-mess-rpl to-btm")
            .withAttribute("src", "/img/reply.png")
            .build()

        let listMenu = [];
        for (let i = 0; i < 3; i++) {
            listMenu.push(new ElementBuilder().creat("div")
                .withClass("btn")
                .withInnerText("Menu item")
                .build());
        }
        let itemCtn = new ElementBuilder().creat("div")
            .withClass("mess-menu bd-r10 pd-10 pos-abs darkgray-1")
            .withClass("invisible")
            .withAttribute("style", "width: max-content; bottom: 32px; left: 32px")
            .withChildren(...listMenu)
            .build();

        return new ElementBuilder().creat("div")
            .withClass("msr-mess-mn flex-row to-btm pos-rel bd-r10")
            .withClass("invisible")
            .withChildren(
                replyIcon,
                new ElementBuilder().creat("img")
                    .withClass("icon-xxs btn pd-l10i")
                    .withAttribute("src", "/img/more.png")
                    .withEvent("click", () => {
                        itemCtn.classList.remove("invisible");
                    })
                    .withEvent("mouseover",() => {
                        itemCtn.classList.add("invisible");
                    })
                    .build(),
                itemCtn
            )
            .build();
    }
}