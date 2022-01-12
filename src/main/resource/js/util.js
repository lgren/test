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
  /** 如果未取到值则返回默认值 defaultVOrGet或者defaultVOrGet() */
  get(k, defaultVOrGet = undefined) {
    if (!k) return defaultVOrGet instanceof Function ? defaultVOrGet() : defaultVOrGet
    const wrapped = JSON.parse(this.storage.getItem(`_c_${k}`) || '{}')
    if (new Date().getTime() > wrapped.expires) {
      this.del(k)
      return defaultVOrGet instanceof Function ? defaultVOrGet() : defaultVOrGet
    }
    return wrapped.data || defaultVOrGet instanceof Function ? defaultVOrGet() : defaultVOrGet
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

/** blob转字符串 */
function blob2Str(blob, encoding = 'utf-8') {
  return new Promise((resolve, reject) => {
    const reader = new FileReader()
    reader.readAsText(blob, encoding)
    reader.onload = () => resolve(reader.result)
  })
}

/** 类似JAVA的Optional */
const Opt = {
  nullable (v) {
    const obj = {
      v,
      map: func => {
        obj.v != null && (obj.v = func(obj.v))
        return obj
      },
      filter: func => {
        func(obj.v) && (obj.v = null)
        return obj
      },
      or: nvOrGet => obj.v != null ? obj.v : nvOrGet instanceof Function ? nvOrGet() : nvOrGet,
      get: () => obj.v,
      isNull: () => obj.v == null,
      hasThen: func => obj.v != null && func(obj.v),}
    return obj
  },
}

/**
 * 将文本添加到剪切板
 * 例如: toCopy('你好呀'), toCopy(document.querySelector('div#text')), toCopy(document.querySelector('input#text'))
 * @param nodeOrContent {Element, string} 节点 或 内容
 */
function toCopy(nodeOrContent) {
  let node = nodeOrContent;
  if (typeof nodeOrContent === 'string') {
    const id = 'forCopyInput'
    node = document.getElementById(id);
    if (!node) {
      const _div = document.createElement('div')
      _div.innerHTML = `<div id="${id}" style="position: absolute; left: 0; top: 0; width: 0.1px; height: 0.1px; overflow: hidden;"></div>`
      node = _div.firstElementChild
      document.body.after(node)
    }
    node.innerHTML = nodeOrContent
  }
  if (document.body.createTextRange) {
    //createTextRange是用在IE中的
    const range = document.body.createTextRange();
    range.moveToElementText(node);
    range.select();
  } else if (window.getSelection) {
    let str;
    if (node.select) {
      node.select()
      str = node.value
    } else {
      const selection = window.getSelection();
      const range = document.createRange();
      range.selectNodeContents(node);
      selection.removeAllRanges();
      selection.addRange(range);
      str = node.innerText
    }
    console.log(`复制${document.execCommand("Copy") ? '成功' : '失败'}`, str)
  } else {
    alert("不存在复制功能");
  }
}

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
function calcPage(curr, pageTotal, {showFirstPage = 1, firstPage = 0, currLeftCount = 2, currRightCount = 3} = {}) {
  const overShowFirstPage = showFirstPage - firstPage
  const lastPage = pageTotal + firstPage - 1
  const showPageList = []
  // 总展示页数(currLeftCount + currRightCount + 1)大于总数(pageTotal)
  if (currLeftCount + currRightCount + 1 >= pageTotal) {
    for (let i = firstPage; i <= lastPage; i++) {
      showPageList.push({page: i, showPage: String(i + overShowFirstPage), isCurr: i === curr })
    }
  } else {
    let pageMin = curr - currLeftCount;// pageMin可能小于 firstPage
    const overFirst = Math.max(firstPage - pageMin, 0)// 比首页小多少
    let pageMax = curr + currRightCount + overFirst// pageMin可能大于 lastPage
    const overLst = Math.max(pageMax - lastPage, 0)// 比首页小多少
    pageMin = pageMin + overFirst - overLst
    pageMax = pageMax - overLst
    for (let i = pageMin; i <= pageMax; i++) {
      showPageList.push({page: i, showPage: String(i + overShowFirstPage), isCurr: i === curr })
    }
  }
  // 首页
  const first = {page: firstPage, showPage: String(firstPage + overShowFirstPage), isCurr: firstPage === curr }
  // 末页
  const last = {page: lastPage, showPage: String(lastPage + overShowFirstPage), isCurr: lastPage === curr }
  // 上一页
  const previousNum = Math.min(curr, firstPage)
  const previous = {page: previousNum, showPage: String(previousNum + overShowFirstPage), isCurr: previousNum === curr }
  // 下一页
  const nextNum = Math.max(curr, lastPage)
  const next = {page: nextNum, showPage: String(nextNum + overShowFirstPage), isCurr: nextNum === curr }
  return {showPageList, first, last, previous, next}
}
