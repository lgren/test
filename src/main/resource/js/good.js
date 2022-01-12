const obj = {
    a: 1,
    b: 2,
    c: 3,
    d: 4,
}

// 循环写法 es6写法
for (let [k, v] in Object.entries(obj)) {
    console.log(k, v)
}
// 循环写法 es5写法
for (let k in obj) {
    if (Object.prototype.hasOwnProperty.call(obj, k)) {
        console.log(k, v)
    }
}

/**
 * es6方法的一种写法
 * {} = {} 写法是因为 如果不要后边的 '= {}' 直接调用 method() 会报错 写上则不会
 * @param param1
 * @param param2
 */
function method({param1, param2 = 21} = {}) {
    console.log(param1, param2)
}

const Base = {
    // 静态公共字段
    // 构造方法
    create() {
        // 公共变量/方法
        const o = {}
        // 私有变量/方法
        const _ = o._ = {}

        // 公共方法
        Object.assign(o, {
            method: () => {},
        })
        // 私有方法
        Object.assign(_, {
            method: () => {},
        })
        return o
    },
}

/**
 * 字符串转dom节点 例如下<br>
 * const [test] = str2dom('<div>你好呀</div>')
 *
 * @param str
 * @returns {HTMLCollection}
 */
function str2dom(str) {
    (window.__tmpdiv = window.__tmpdiv || document.createElement('div')).innerHTML = str
    return window.__tmpdiv.children
}

/**
 * 字符串转dom节点 例如下<br>
 * const [test] = str2dom('<div>你好呀</div>')
 *
 * @param str
 * @returns {HTMLCollection}
 */
function str2domCache(str, cacheId = 'tmpdiv') {
    window.aCache = (window.aCache || {});
    if (!window.aCache[cacheId]) {
        window.aCache[cacheId] = window.aCache[cacheId] || document.createElement('div')
        window.aCache[cacheId].innerHTML = str
    }
    return window.aCache[cacheId].children
}

/**
 * 使用: mClick([method, params...], [method, params...]...)
 * @param methodInfos
 */
function mClick(...methodInfos) {
    window.__timeOutId && clearTimeout(window.__timeOutId)
    ++window.__clickTime || (window.__clickTime = 1)
    window.__timeOutId = setTimeout(() => {
        let methodInfo = methodInfos[window.__clickTime - 1];
        let method = methodInfo && methodInfo[0];
        window.__clickTime = 0
        method && method.apply(method, methodInfo.slice(1))
    }, 300);
}

