export class RoomCard {
    constructor(imgUrl, name) {
        this.imgUrl = imgUrl ? imgUrl : "http://localhost:8080/img/messages.png";
        this.name = name;
    }

    toDOM() {
        // return new DOMParser().parseFromString(`<div class="bd-r10 menu-item card">
        //             <div class="mg-r-10">
        //                 <img src="${this.imgUrl}" alt="" class="icon-s"/>
        //             </div>
        //             <div>
        //                 <h4 class="no-pdmg fs-16">${this.name}</h4>
        //                 <p class="no-pdmg fs-s">Lorem ipsum dolor sit</p>
        //             </div>
        //         </div>`, "text/xml").documentElement;
        return `<div class="bd-r10 menu-item card">
                    <div class="mg-r-10">
                        <img src="${this.imgUrl}" alt="" class="icon-s"/>
                    </div>
                    <div>
                        <h4 class="no-pdmg fs-16">${this.name}</h4>
                        <p class="no-pdmg fs-s">Lorem ipsum dolor sit</p>
                    </div>
                </div>`;
    }
    toString() {
        return `RoomCard[imgUrl="${this.imgUrl}", name="${this.name}"]`;
    }
}