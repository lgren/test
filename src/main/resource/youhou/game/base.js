class Type {
  constructor(name, url) {

    this._name = name;
    this._url = url;
    this._typeList = [];
  }

  addType(type) {
    this._typeList.push(type)
  }

  //region get set
  get name() {
    return this._name;
  }

  set name(value) {
    Vue.set(this, '_name', value)
  }

  get url() {
    return this._url;
  }

  set url(value) {
    Vue.set(this, '_url', value)
  }

  //endregion
}

class PageInfo {
  constructor(curr, total, pageList, firstAndLastList, list) {

    this._curr = curr;
    this._total = total;
    this._pageList = pageList;
    this._firstAndLastList = firstAndLastList;
    this._list = list;
  }

  //region get set
  get curr() {
    return this._curr;
  }

  set curr(value) {
    Vue.set(this, '_curr', value);
  }

  get total() {
    return this._total;
  }

  set total(value) {
    Vue.set(this, '_total', value);
  }

  get pageList() {
    return this._pageList;
  }

  set pageList(value) {
    Vue.set(this, '_pageList', value);
  }

  get firstAndLastList() {
    return this._firstAndLastList;
  }

  set firstAndLastList(value) {
    Vue.set(this, '_firstAndLastList', value);
  }

  get list() {
    return this._list;
  }

  set list(value) {
    this._list = value;
  }
  //endregion
}

class QueryCondition {
  constructor(name, value = undefined) {

    this._name = name;
    this._value = value;

    this._conditionList = [];
  }

  addQueryCondition(name, queryConditionValue = undefined) {
    this.conditionList.push(new QueryCondition(name, queryConditionValue))
    return this
  }

  /**
   *
   * @param {QueryCondition} queryCondition
   * @returns {QueryCondition}
   */
  pushQueryCondition(...queryCondition) {
    this.conditionList.push(queryCondition)
    return this
  }

  //region get set
  get name() {
    return this._name;
  }

  set name(value) {
    Vue.set(this, '_name', value);
  }

  get value() {
    return this._value;
  }

  set value(value) {
    Vue.set(this, '_value', value);
  }

  get conditionList() {
    return this._conditionList;
  }

  set conditionList(value) {
    Vue.set(this, '_conditionList', value);
  }
  //endregion
}

class WebInfo {
  constructor(name, indexUrl, currQueryUrl = indexUrl) {
    this._name = name;
    this._indexUrl = indexUrl;
    this._currQueryUrl = currQueryUrl;
    this._currQueryData = undefined;

    this._headerList = []

    this._queryConditionList = []

    this._currPage = {}
  }

  /**
   *
   * @param {QueryCondition} queryCondition
   * @returns {WebInfo}
   */
  pushQueryCondition(...queryCondition) {
    this.queryConditionList.push(queryCondition)
    return this
  }

  /**
   * 获取图片
   * @returns Promise<WebInfo.getPageInfo(...)>
   */
  getPage(url, data) {
    return new Promise((res, rej) => res(WebInfo.getPageInfo({curr: 1, total: 1, list: []})))
  }

  /**
   * 创建分页
   * @param curr 当前页
   * @param total 总页
   * @param list 得到的数据
   * @returns {PageInfo}
   */
  static getPageInfo(curr = 1, total = 1, list = []) {
    const pageNums = []
    const firstAndLastList = []
    let hasFirst = true
    let hasLast = true
    if (total < 10) {
      for (let i = 1; i < total + 1; i++) {
        pageNums.push(i)
      }
    } else {
      hasFirst = curr < 6;
      hasLast = (curr + 5) > total;
      const needAdd = ((curr - 4) > 0 ? 0 : 4 - curr + 1);
      const needSub = ((curr + 4) < total ? 0 : curr + 4 - total);
      pageNums.push(curr + needAdd - needSub)
      for (let i = 4; i > 0; i--) {
        pageNums.push(curr - i + needAdd - needSub, curr + i + needAdd - needSub)
      }
      pageNums.sort((l, r) => l - r)
    }
    const pageList = pageNums.map(n => ({
      page: n,
      name: String(n),
    }))
    !hasFirst && firstAndLastList.push({
      page: 1,
      name: `首页[1]`,
    })
    !hasLast && firstAndLastList.push({
      page: total,
      name: `尾页[${total}]`,
    })
    return new PageInfo(curr, total, pageList, firstAndLastList, list)
  }

  //region get set
  get name() {
    return this._name;
  }

  set name(value) {
    Vue.set(this, '_name', value);
  }

  get indexUrl() {
    return this._indexUrl;
  }

  set indexUrl(value) {
    Vue.set(this, '_indexUrl', value);
  }

  get currQueryUrl() {
    return this._currQueryUrl;
  }

  set currQueryUrl(value) {
    Vue.set(this, '_currQueryUrl', value);
  }

  get currQueryData() {
    return this._currQueryData;
  }

  set currQueryData(value) {
    Vue.set(this, '_currQueryData', value);
  }

  get headerList() {
    return this._headerList;
  }

  set headerList(value) {
    Vue.set(this, '_headerList', value);
  }

  get currPage() {
    return this._currPage;
  }

  set currPage(value) {
    Vue.set(this, '_currPage', value);
  }

  get queryConditionList() {
    return this._queryConditionList;
  }

  set queryConditionList(value) {
    Vue.set(this, '_queryConditionList', value);
  }
  //endregion
}

// class JymGoods {
//   constructor(goodsN) {
//     const nameAndUrlN = goodsN.querySelector('.is-account a');
//     const typeAndServiceN = goodsN.querySelector('.con');
//
//     this.name = nameAndUrlN.innerText
//     this.url = nameAndUrlN.href
//     this.price = goodsN.querySelector('.price').innerText
//     this.service = typeAndServiceN.innerText.match(/(?<=游戏区服： ).*?$/g)
//     this.credit = [...typeAndServiceN.querySelectorAll('p span:not(.link)')].map(o => o.classList[0].replace('level-', '')).join('')
//     this.okRate = typeAndServiceN.querySelector('p span.link').innerText
//   }
// }
//
//
//
//
// class JYM extends WebInfo {
//   constructor() {
//     super('交易猫', 'https://www.jiaoyimao.com')
//     this.addType(new Type('崩坏3[苹果版]', 'https://www.jiaoyimao.com/g4502/'))
//     this.addType(new Type('崩坏3', 'https://www.jiaoyimao.com/g4508/'))
//     this.queryConditionList = [
//       {
//         desc: '价格区间',
//         curr: { value: 'p5', desc: '500以上',},
//         list: [
//           { value: 'p1', desc: '30以下',},
//           { value: 'p2', desc: '30-100',},
//           { value: 'p3', desc: '100-300',},
//           { value: 'p4', desc: '300-500',},
//           { value: 'p5', desc: '500以上',},
//         ]
//       },
//       {
//         desc: '排序方式',
//         curr: { value: 'p5', desc: '500以上',},
//         list: [
//           { value: 'r1', desc: '按时间倒序',},
//           { value: 'r2', desc: '按单价高到低排序',},
//           { value: 'r3', desc: '按价格高到低排序',},
//           { value: 'r4', desc: '按价格低到高排序',},
//         ]
//       },
//     ]
//   }
//
//   getPage(url = this.currQueryUrl, page = 1) {
//     const _this = this
//     _this.currQueryUrl = url
//     url = url + (page === 1 ? '' : `r1-n${page}.html`)
//     return new Promise((res, rej) => {
//       ajax({
//         url: url,
//         method: "get",
//         type: 'html',
//       }).then(([r, txt, doc]) => {
//         const oriPage = doc.querySelector(`.mod-page`)
//         const curr = oriPage.querySelector('a.on').innerText * 1
//         const total = oriPage.innerText.match(/(?!=共)\d+(?=页)/g)[0] * 1
//         const goodsList = [...doc.querySelectorAll('ul.specialList li[name=goodsItem]')].map(n => new JymGoods(n));
//         _this.currPage = WebInfo.getPageInfo(curr, total, goodsList)
//         console.log(_this.name, '图片获取', 'get', '请求地址', url, '\n', '结果', _this.currPage)
//         res(_this.currPage)
//       })
//     })
//   }
// }
