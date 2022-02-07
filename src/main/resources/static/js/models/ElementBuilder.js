
export class ElementBuilder {
    instance = null;

    constructor() {
    }

    /**
     * Init DOM Element with tagName
     * @param tagName
     */
    creat(tagName) {
        this.instance = document.createElement(tagName);
        return this;
    }
    withId(id) {
        this.instance.id = id
        return this;
    }
    withClass(className) {
        for (let clazz of className.split(" ")) {
            if (clazz) {
                this.instance.classList.add(clazz);
            }
        }
        return this;
    }
    withAttribute(name, value) {
        this.instance.setAttribute(name, value);
        return this;
    }
    withChild(childDom) {
        this.instance.appendChild(childDom);
        return this;
    }
    withChildren(...children) {
        for (let child of children) {
            this.instance.appendChild(child);
        }
        return this;
    }
    withInnerText(txt) {
        if (txt) {
            this.instance.innerText = txt;
        }
        return this;
    }
    withEvent(type, callback) {
        this.instance.addEventListener(type, callback);
        return this;
    }
    build() {
        return this.instance;
    }
}
