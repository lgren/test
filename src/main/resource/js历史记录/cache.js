// localStorage, cookie
var cacheType = 'localStorage';

var CacheUtil = {
    get: function(cacheType, key) {
        return (this._impl[cacheType] || this._impl.localStorage).get(key);
    },
    set: function(cacheType, key, value, time) {
        return (this._impl[cacheType] || this._impl.localStorage).set(key, value, time);
    },
    del: function(cacheType, key) {
        return (this._impl[cacheType] || this._impl.localStorage).del(key);
    },
    _impl: {
        cookie: {
            get: function (key) {
                return CacheUtil._parseValue(getCookie(key));
            },
            set: function (key, value, time) {
                var valueStr = CacheUtil._stringValue(value);
                setCookie(key, valueStr, time || 'd30');
                return valueStr;
            },
            del: function (key) {
                delCookie(key);
            },
        },
        localStorage: {
            get: function (key) {
                return CacheUtil._parseValue(localStorage.getItem(key));
            },
            set: function (key, value, time) {
                var valueStr = CacheUtil._stringValue(value);
                localStorage.setItem(key, valueStr);
                return valueStr;
            },
            del: function (key) {
                localStorage.removeItem(key);
            },
        },
    },
    _stringValue: function (value) {
        return JSON.stringify(value);
    },
    _parseValue: function (value) {
        return JSON.parse(value);
    },
}

function getCache(key) {
    return CacheUtil.get(cacheType, key);
}

function setCache(key, value, time) {
    return CacheUtil.set(cacheType, key, value, time);
}

function delCache(key) {
    return CacheUtil.del(cacheType, key);
}

//region cookie 方法
/**
 * 写cookies
 * @param key
 * @param value
 * @param time 这是有设定过期时间的使用示例：
 *          s20是代表20秒
 *          h是指小时，如12小时则是：h12
 *          d是天数，30天则：d30
 */
function setCookie(key, value, time) {
    var strSec = getSec(time);
    var exp = new Date();
    exp.setTime(exp.getTime() + strSec * 1);
    document.cookie = key + "=" + escape(value) + ";expires=" + exp.toGMTString();
}

// 解析时间
function getSec(str) {
    var str1 = str.substring(1, str.length) * 1;
    var str2 = str.substring(0, 1);
    if (str2 === "s") {
        return str1 * 1000;
    } else if (str2 === "h") {
        return str1 * 60 * 60 * 1000;
    } else if (str2 === "d") {
        return str1 * 24 * 60 * 60 * 1000;
    }
}

//读取cookies
function getCookie(key) {
    var arr, reg = new RegExp("(^| )" + key + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return null;
}

//删除cookies
function delCookie(key) {
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval = getCookie(key);
    if (cval != null)
        document.cookie = key + "=" + cval + ";expires=" + exp.toGMTString();
}
//endregion