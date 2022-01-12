class JymGoods {
  constructor(obj) {
    this.id = obj.goodsId;
    this.name = obj.title;
    this.url = obj.GoodsDetailUrl;
    this.price = obj.price;
    // this.service = obj.price;


    const nameAndUrlN = goodsN.querySelector('.is-account a');
    const typeAndServiceN = goodsN.querySelector('.con');

    this.name = nameAndUrlN.innerText
    this.url = nameAndUrlN.href
    this.price = goodsN.querySelector('.price').innerText
    this.service = typeAndServiceN.innerText.match(/(?<=游戏区服： ).*?$/g)
    this.credit = [...typeAndServiceN.querySelectorAll('p span:not(.link)')].map(o => o.classList[0].replace('level-', '')).join('')
    this.okRate = typeAndServiceN.querySelector('p span.link').innerText
  }
}

class JYM extends WebInfo {
  constructor() {
    super('交易猫', 'https://m.jiaoyimao.com/')
    this.addType(new Type('崩坏3[苹果版]', 'https://m.jiaoyimao.com/jg1004502-3/f182731-c182732/o110/'))
    this.addType(new Type('崩坏3', 'https://m.jiaoyimao.com/jg1004502-2/f182782-c182783/o110/'))

    // 苹果第二页
    // https://m.jiaoyimao.com/api2/goodsList/getGoodsListByCondition
    //1 gameId: 1004502
    //1 platformId: 3
    //1 stdCatId: 182731
    //1 jymCatId: 182732
    //1 jymCatName: 成品号
    //1 clientId: 110
    //1 clientName: 全部客户端
    // page: 2
    // sort: 1
    //1 cname: 成品号
    //1 systerName: 苹果

    // 安卓第二页
    // gameId: 1004502
    // platformId: 2
    // stdCatId: 182782
    // jymCatId: 182783
    // jymCatName: 成品号
    // clientId: 110
    // clientName: 全部客户端
    // page: 2
    // sort: 1
    // cname: 成品号
    // systerName: 安卓
  }

  init() {
    const bh3Apple = new QueryCondition('崩坏三-苹果', {
      gameId: 1004502,
      platformId: 3,
      stdCatId: 182731,
      jymCatId: 182732,
      jymCatName: '成品号',
      clientId: 110,
      clientName: '全部客户端',
      cname: '成品号',
      systerName: '苹果',
    });
    bh3Apple.addQueryCondition('价格', {
      'r-101': '700,1200',
      'condition': {"r-101":"700,1200"}
    })
    const bh3Android = new QueryCondition('崩坏三-安卓', {
      gameId: 1004502,
      platformId: 2,
      stdCatId: 182782,
      jymCatId: 182783,
      jymCatName: '成品号',
      clientId: 110,
      clientName: '全部客户端',
      cname: '成品号',
      systerName: '安卓',
    });
    this.pushQueryCondition(bh3Apple, bh3Android)
  }


  getPage(url = this.currQueryUrl, data = this.currQueryData, page = data && data.page || 1) {
    this.currQueryUrl = url
    this.currQueryUrl = data
    const _this = this
    return new Promise((res, rej) => ajax({url, data,}).then(([r]) => {
      console.log(r)
      // const curr = page
      // const total = 9999
      // const goodsList = [...doc.querySelectorAll('ul.specialList li[name=goodsItem]')].map(n => new JymGoods(n));

      // const oriPage = doc.querySelector(`.mod-page`)
      // const curr = oriPage.querySelector('a.on').innerText * 1
      // const total = oriPage.innerText.match(/(?!=共)\d+(?=页)/g)[0] * 1
      // const goodsList = [...doc.querySelectorAll('ul.specialList li[name=goodsItem]')].map(n => new JymGoods(n));
      // _this.currPage = WebInfo.getPageInfo(1, 1, [])
      // console.log(_this.name, '图片获取', 'get', '请求地址', url, '\n', '结果', _this.currPage)
      // res(_this.currPage)
    }))
  }
}
