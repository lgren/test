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
