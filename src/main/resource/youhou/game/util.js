// 解析vue 1.template 2.script 3.style
function parseVue(str, components = {}) {
  const templateStr = /<template>([\s\S]*)<\/template>/g.exec(str)[1]
  const scriptStr = /<script.*?>([\s\S]*)<\/script>/g.exec(str)[1]
  const styleStr = /<style.*?>([\s\S]*)<\/style>/g.exec(str)[1]
  const vueObj = eval(scriptStr)
  vueObj.template = templateStr
  vueObj.components = components
  GM_addStyle(styleStr)
  return vueObj
}

/**
 * 缓存工具
 *
 * @version es6
 */
const CacheUtil = {
  // window.localStorage 或 window.sessionStorage 或 其他具有 setItem(k, v) {}, getItem(k) {return v}, removeItem(k) {return delV} 的缓存
  storage: window.localStorage,
  /** 设置值 expires_ms: 以毫秒为单位的的过期时间设置 */
  set(k, v, expires_ms = 86400 * 1000 * 365) {
    if (!k) return false
    this.storage.setItem(`_c_${k}`, JSON.stringify({expires: new Date().getTime() + expires_ms, data: v}))
  },
  /** 如果未取到值则返回默认值 defaultV */
  get(k, defaultV = undefined) {
    if (!k) return defaultV
    const wrapped = JSON.parse(this.storage.getItem(`_c_${k}`) || '{}')
    if (new Date().getTime() > wrapped.expires) {
      this.del(k)
      return defaultV
    }
    return wrapped.data || defaultV
  },
  del(k) {
    return this.storage.removeItem(`_c_${k}`)
  },
  useLocalModel() {
    this.storage = window.localStorage
  },
  useSessionModel() {
    this.storage = window.sessionStorage
  },
  useWindowModel() {
    this.storage = {
      setItem: (k, v) => window[k] = v,
      getItem: (k) => window[k],
      removeItem: (k) => {
        const v = window[k]
        delete window[k]
        return v
      }
    }
  },
}

function blob2Str(blob, encoding = 'utf-8') {
  return new Promise((resolve, reject) => {
    const reader = new FileReader()
    reader.readAsText(blob, encoding)
    reader.onload = () => resolve(reader.result)
  })
}

// 类似java的Optional map, or, orGet, isNull, get, hasThen
const Opt = {
  nullable(v) {
    const obj = {
      v,
      map: func => {
        obj.v != null && (obj.v = func(obj.v))
        return obj
      },
      or: nv => obj.v == null ? nv : obj.v,
      orGet: nvGet => obj.v == null ? nvGet() : obj.v,
      isNull: () => obj.v == null,
      get: () => obj.v,
      hasThen: func => obj.v != null && func(obj.v),
    }
    return obj
  },
}

// 使用: mClick(...[method, ...params])
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


function deepCopy(listOrObj) {
  if (listOrObj == null) return listOrObj
  if (listOrObj.constructor === Array) {
    return listOrObj.map(o => !!o && (o.constructor === Array || Object) ? deepCopy(o) : o)
  } else if (listOrObj.constructor === Object) {
    const newObj = {}
    for (const [k, v] of Object.entries(listOrObj)) {
      newObj[k] = (!!v && (v.constructor === Array || Object)) ? deepCopy(v) : v
    }
    return newObj
  }
  return listOrObj
}

const domParser = new DOMParser();
/**
 *
 * @param method 方法 默认get
 * @param url
 * @param data
 * @param responseType 默认json 返回类型 [arraybuffer, blob, json, stream, html]
 * @param encoding 默认utf-8 编码 [utf-8, gbk]
 */
function ajax({ url, data, method = 'get', responseType = 'json', encoding = 'utf-8' }) {
  return new Promise((resolve, reject) => {
    const config = {
      url,
      method,
      data,
      responseType,
      onload: r => resolve([r.response, r]),
      onerror: e => reject(e),
    }
    if (method.toLowerCase() === 'get' && data != null) {
      config.url = `${url}?${urlEncode(data).substring(1)}`;
      config.data = undefined
    }
    if (responseType.toLowerCase() === 'html') {
      config.responseType = 'blob'
      config.onload = r => blob2Str(r.response, encoding).then(text => {
        (success || resolve)([domParser.parseFromString(text, 'text/html'), r, text])
      })
    }
    GM_xmlhttpRequest(config);
  });
}

function urlEncode(param, key, encode) {
  if (param == null) return '';
  let paramStr = '';
  const t = typeof (param);
  if (t === 'string' || t === 'number' || t === 'boolean') {
    paramStr += '&' + key + '=' + ((encode == null || encode) ? encodeURIComponent(param) : param);
  } else {
    for (const i in param) {
      if (param.hasOwnProperty(i)) {
        const k = key == null ? i : (param instanceof Array ? `${key}[${i}]` : `${key}.${i}`);
        paramStr += urlEncode(param[i], k, encode);
      }
    }
  }
  return paramStr;
}
