class VItem{
  constructor() {
    this._type = undefined
    this._name = undefined
    this._url = undefined
    this._target = undefined
    this._obj = undefined
    this._click = undefined
  }
  initUrl(name, url, target='_blank') {
    this.type = 'url'
    this.name = name
    this.url = url
    this.target = target
    return this
  }
  initObj(name, obj, clickList) {
    this.type = 'obj'
    this.name = name
    this.obj = obj
    const item = this
    clickList.forEach(o => {
      o[1] = item
      return o
    })
    this.click = clickList
    return this
  }

  isUrl() {
    return this.type === 'url'
  }

  isObj() {
    return this.type === 'obj'
  }

  //region get set
  get type() {
    return this._type;
  }

  set type(value) {
    Vue.set(this, '_type', value);
  }

  get name() {
    return this._name;
  }

  set name(value) {
    Vue.set(this, '_name', value);
  }

  get url() {
    return this._url;
  }

  set url(value) {
    Vue.set(this, '_url', value);
  }

  get target() {
    return this._target;
  }

  set target(value) {
    Vue.set(this, '_target', value);
  }

  get obj() {
    return this._obj;
  }

  set obj(value) {
    Vue.set(this, '_obj', value);
  }

  get click() {
    return this._click;
  }

  set click(value) {
    Vue.set(this, '_click', value);
  }

//endregion
}

class View {
  constructor() {
    this.headerList = []
    this.contentList = []
    this.footerList = []

  }

  getOrCreateForList(list, i=undefined, j=undefined) {
    if (i < 0 || j < 0) {
      throw new Error(`View.list: 列表序列号不能小于0: {list:${JSON.stringify(list)},i:${i},j:${j},}`);
    }
    let row = list[i];
    if (row == null) {
      if (j == null) {
        Vue.set(list, i, row = new VItem())
      } else {
        Vue.set(list, i, row = Array(j + 1))
      }
    }
    if (row instanceof VItem) {
      return row;
    } else {
      let col = row[j];
      if (col == null) {
        Vue.set(row, j, col = new VItem())
      }
      return col;
    }
  }

  header(i=undefined, j=undefined) {
    return this.getOrCreateForList(this.headerList, i, j)
  }

  content(i=undefined, j=undefined) {
    return this.getOrCreateForList(this.contentList, i, j)
  }

  footer(i=undefined, j=undefined) {
    return this.getOrCreateForList(this.footerList, i, j)
  }


  getList(list) {
    return list.filter(o => o != null).map(o => o instanceof VItem ? o : o.filter(i => i != null))
  }

  getHeaderList() {
    return this.getList(this.headerList)
  }

  getContentList() {
    return this.getList(this.contentList)
  }

  getFooterList() {
    return this.getList(this.footerList)
  }

  static newBH3() {
    const bh3View = new View();
    bh3View.header(0, 0).initUrl('崩坏三-手游版-苹果', 'https://m.jiaoyimao.com/jg1004502-3/f182731-c182732/o110/')
    bh3View.header(0, 1).initUrl('崩坏三-手游版-安卓', 'https://m.jiaoyimao.com/jg1004502-2/f182782-c182783/o110/')
    bh3View.header(1, 0).initObj('苹果查询对象(700元以上, 80级及其以下)', {
      gameId: 1004502,
      platformId: 3,
      stdCatId: 182731,
      jymCatId: 182732,
      jymCatName: `成品号`,
      clientId: 110,
      clientName: `全部客户端`,
      page: 1,
      sort: 2,
      cname: `成品号`,
      systerName: `苹果`,
      'r-101': `700`,
      'r-1609236672220325': `,80`,
      condition: `{"r-101":"700","r-1609236672220325":",80"}`,
      sortName: `最新发布`,
    }, [[(item) => console.log(item.obj)]]);
    return bh3View
  }
}


