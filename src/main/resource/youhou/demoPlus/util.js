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

const util = {
  key: 0,
  domParser: new DOMParser(),
  urlEncode(param, key, encode) {
    if (param == null) return '';
    let paramStr = '';
    const t = typeof (param);
    if (t === 'string' || t === 'number' || t === 'boolean') {
      paramStr += '&' + key + '=' + ((encode == null || encode) ? encodeURIComponent(param) : param);
    } else {
      for (const i in param) {
        if (param.hasOwnProperty(i)) {
          const k = key == null ? i : (param instanceof Array ? `${key}[${i}]` : `${key}.${i}`);
          paramStr += util.urlEncode(param[i], k, encode);
        }
      }
    }
    return paramStr;
  },
  /**
   *
   * @param method 方法 默认get
   * @param url
   * @param data
   * @param responseType 默认json 返回类型 [arraybuffer, blob, json, stream, html]
   * @param encoding 默认utf-8 编码 [utf-8, gbk]
   * @return {Promise<[Document, response, string]>}
   */
  ajax({url, data, method = 'get', responseType = 'json', encoding = 'utf-8'}) {
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
        config.url = `${url}?${util.urlEncode(data).substring(1)}`;
        config.data = undefined
      }
      if (responseType.toLowerCase() === 'html') {
        config.responseType = 'blob'
        config.onload = r => util.blob2Str(r.response, encoding).then(text => {
          resolve([util.domParser.parseFromString(text, 'text/html'), r, text])
        })
      }
      GM_xmlhttpRequest(config);
    });
  },
  deepCopy(listOrObj) {
    if (listOrObj == null) return listOrObj
    if (listOrObj.constructor === Array) {
      return listOrObj.map(o => !!o && (o.constructor === Array || Object) ? util.deepCopy(o) : o)
    } else if (listOrObj.constructor === Object) {
      const newObj = {}
      for (const [k, v] of Object.entries(listOrObj)) {
        newObj[k] = (!!v && (v.constructor === Array || Object)) ? util.deepCopy(v) : v
      }
      return newObj
    }
    return listOrObj
  },
  // 使用: mClick(...[method, ...params])
  mClick(...methodInfos) {
    window.__timeOutId && clearTimeout(window.__timeOutId)
    ++window.__clickTime || (window.__clickTime = 1)
    window.__timeOutId = setTimeout(() => {
      let methodInfo = methodInfos[window.__clickTime - 1];
      let method = methodInfo && methodInfo[0];
      window.__clickTime = 0
      method && method.apply(method, methodInfo.slice(1))
    }, 300);
  },
  // 类似java的Optional map, or, orGet, isNull, get, hasThen
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
  logD(...params) {
    return params.map(o => util.deepCopy(o))
  },
  blob2Str(blob, encoding = 'utf-8') {
    return new Promise((resolve, reject) => {
      const reader = new FileReader()
      reader.readAsText(blob, encoding)
      reader.onload = () => resolve(reader.result)
    })
  },
  parseVue(str, components = {}) {
    const templateStr = /<template>([\s\S]*)<\/template>/g.exec(str)[1]
    const scriptStr = /<script.*?>([\s\S]*)<\/script>/g.exec(str)[1]
    const styleStr = /<style.*?>([\s\S]*)<\/style>/g.exec(str)[1]
    const vueObj = eval(scriptStr)
    vueObj.template = templateStr
    vueObj.components = components
    GM_addStyle(styleStr)
    return vueObj
  },
  get(obj, propOrIndex, defaultVOrGet) {
    if (obj[propOrIndex] == null && defaultVOrGet != null) {
      Vue.set(obj, propOrIndex, defaultVOrGet instanceof Function ? defaultVOrGet() : defaultVOrGet);
      // Vue.set(map, attribute.getTypeStr(), new AttrItemColl(attribute))
    }
    return obj[propOrIndex]
  },
  generateKey() {
    Vue.set(util, 'util', util.key + 1)
    return util.key
  },
  /**
   * 例1: 起始页(curr):0, 限制个数(limit):10, 总共数据(total):(1210)
   *    使用: calcPage(curr, Math.ceil(total/limit))
   * 例2: 起始页(curr):0, 限制个数(limit):10, 总共数据(total):(1210)
   *    使用: calcPage(curr, Math.ceil(total/limit))
   *
   * @param curr {number} 当前页
   * @param pageTotal {number} 页码数的总数 如果未知此数, 已知数据总数(total)和限制(limit)个数 则pageTotal = Math.ceil(total/limit)
   * @param showFirstPage = 1 {number} 第一页实际展示的数字, 默认为1
   * @param firstPage = 0 {number} 第一页对应的数字, 大部分数据库都为0, 例如mysql数据库limit 0, 10, 第一位的0则为此数
   * @param currLeftCount = 2 {number} 当前页数左边还有的页码数有currLeftNum个, 总共显示的就是 currLeftCount + currRightCount + 1个
   * @param currRightCount = 3 {number} 当前页数右边还有的页码数有currRightNum个, 总共显示的就是 currLeftCount + currRightCount + 1个
   * @return {{next: {showPage: string, page: number, isCurr: boolean}, showPageList: {showPage: string, page: number, isCurr: boolean}[], last: {showPage: string, page: number, isCurr: boolean}, previous: {showPage: string, page: number, isCurr: boolean}, first: {showPage: string, page: number, isCurr: boolean}}}
   */
  calcPage(curr, pageTotal, {showFirstPage = 1, firstPage = 0, currLeftCount = 2, currRightCount = 3} = {}) {
    const overShowFirstPage = showFirstPage - firstPage
    const lastPage = pageTotal + firstPage - 1
    const showPageList = []
    // 总展示页数(currLeftCount + currRightCount + 1)大于总数(pageTotal)
    if (currLeftCount + currRightCount + 1 >= pageTotal) {
      for (let i = firstPage; i <= lastPage; i++) {
        showPageList.push({page: i, showPage: String(i + overShowFirstPage), isCurr: i === curr})
      }
    } else {
      let pageMin = curr - currLeftCount;// pageMin可能小于 firstPage
      const overFirst = Math.max(firstPage - pageMin, 0)// 比首页小多少
      let pageMax = curr + currRightCount + overFirst// pageMin可能大于 lastPage
      const overLst = Math.max(pageMax - lastPage, 0)// 比首页小多少
      pageMin = pageMin + overFirst - overLst
      pageMax = pageMax - overLst
      for (let i = pageMin; i <= pageMax; i++) {
        showPageList.push({page: i, showPage: String(i + overShowFirstPage), isCurr: i === curr})
      }
    }
    // 首页
    const first = {page: firstPage, showPage: String(firstPage + overShowFirstPage), isCurr: firstPage === curr}
    // 末页
    const last = {page: lastPage, showPage: String(lastPage + overShowFirstPage), isCurr: lastPage === curr}
    // 上一页
    const previousNum = Math.min(curr, firstPage)
    const previous = {
      page: previousNum,
      showPage: String(previousNum + overShowFirstPage),
      isCurr: previousNum === curr
    }
    // 下一页
    const nextNum = Math.max(curr, lastPage)
    const next = {page: nextNum, showPage: String(nextNum + overShowFirstPage), isCurr: nextNum === curr}
    return {showPageList, first, last, previous, next}
  },
  log: {
    styleData(data={}, ...dataArr) {
      if (data.constructor.name !== 'Object') {
        return [data, ...dataArr]
      }
      const strList = ['\n'];
      const styleList = []
      const otherList = []
      for (let [k, v] of Object.entries(data)) {
        strList.push('%c')
        strList.push(k);
        styleList.push(v || '');
      }
      otherList.push(...dataArr);
      return !strList.length ? otherList : [strList.join(''), ...styleList, ...otherList]
    }
  }
}

console.logS = function (data={}, ...dataArr) {
  this.log(...util.log.styleData(data, ...dataArr))
}
console.logPic = function (picWebName, name, url, data={}) {
  // 打印图片
  // console.log("%c ", "background: url(http://p79mwfmry.bkt.clouddn.com/logo50.jpg) no-repeat center;padding-left:80px;padding-bottom: 80px;border-radius:50%;")
  const commonFontStyle = 'color:#fff;padding: 0 4px;border-radius: 2px;'
  //666 0681d0 68bc48
  this.logS({
    [name]: `${commonFontStyle}background-color:#0681d0;`,
    [picWebName]: `${commonFontStyle}background-color:#666;`,
  }, url, {picWeb: this, ...data})
}
