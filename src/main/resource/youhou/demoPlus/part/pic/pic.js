PicCategory = class PicCategory {
  /**
   *
   * @param name {string} 分类名称
   * @param oriUrl {string} 分类原网站
   * @param children {PicCategory[]} 子类型
   */
  constructor(name, oriUrl, children=[]) {
    this.id = util.generateKey()
    this.name = name
    this.oriUrl = oriUrl
    /** @type {PicCategory[]} */
    this.children = children
  }

  /**
   *
   * @param name {string} 分类名称
   * @param oriUrl {string} 分类原网站
   * @return {PicCategory}
   */
  addChild(name, oriUrl) {
    this.children.push(new PicCategory(name, oriUrl))
    return this
  }

  /**
   *
   * @param children {PicCategory[]}
   * @return {PicCategory}
   */
  setChildren(children) {
    Vue.set(this, 'children', children)
    return this
  }
}

PicImgInfo = class PicImgInfo {
  /**
   *
   * @param name {string} 图片名
   * @param oriUrl {string} 图片原网址
   * @param downFunc {Function} 下载方法
   * @param viewImgSrc {string} 预览图片地址
   * @param viewImgAlt {string} 预览图片失败展示
   */
  constructor(name, oriUrl, downFunc, viewImgSrc, viewImgAlt) {
    this.id = util.generateKey()
    this.name = name
    this.oriUrl = oriUrl
    this.downFunc = downFunc
    this.viewImgSrc = viewImgSrc
    this.viewImgAlt = viewImgAlt
  }

  /**
   *
   * @param name {string} 图片名
   * @param oriUrl {string} 图片原网址
   * @param downFunc {Function} 下载方法
   * @param viewImgSrc {string} 预览图片地址
   * @param viewImgAlt {string} 预览图片失败展示
   */
  static create({name, oriUrl, downFunc, viewImgSrc, viewImgAlt}) {
    return new PicImgInfo(name, oriUrl, downFunc, viewImgSrc, viewImgAlt)
  }
}

PicWeb = class PicWeb {
  /**
   *
   * @param name 网站名
   * @param indexUrl 网站首页
   * @param currTypeUrl 网站当前类型url
   */
  constructor(name, indexUrl, currTypeUrl = indexUrl) {
    this.id = util.generateKey()

    this.name = name
    this.indexUrl = indexUrl
    this.currTypeUrl = currTypeUrl
    /** @type {PicCategory[]} */
    this.picCategoryList = []
    /** @type {PicImgInfo[]} */
    this.imgList = []

    this.headerList = []
    /** @type {MyPage} */
    this.page = new MyPage(1, 1)
    this.pageStart = 1
    this.pageTotal = 1

    /**
     *
     * @param url {string}
     * @param page {number}
     * @returns {Promise<PicImgInfo>}
     */
    this.pagePic = (url = this.currTypeUrl, page = 1) => new Promise((res, rej) => res([]))
  }

  /**
   * @param imgList {PicImgInfo[]}
   * @return {PicWeb}
   */
  setImgList(imgList) {
    Vue.set(this, 'imgList', imgList)
    return this
  }

  /**
   *
   * @return {PicWeb}
   */
  static tpfxl() {
    const picWeb = new PicWeb('图片分享录', 'https://image.fenxianglu.cn/');

    picWeb.picCategoryList = [
      new PicCategory('电脑壁纸', `${picWeb.indexUrl}/qn/category/1`)
        .addChild('风景', `${picWeb.indexUrl}/qn/category/1/1`)
        .addChild('美女', `${picWeb.indexUrl}/qn/category/1/3`)
        .addChild('动漫', `${picWeb.indexUrl}/qn/category/1/4`)
        .addChild('二次元', `${picWeb.indexUrl}/qn/category/1/5`)
        .addChild('卡通', `${picWeb.indexUrl}/qn/category/1/6`)
        .addChild('游戏', `${picWeb.indexUrl}/qn/category/1/2`)
        .addChild('爱情', `${picWeb.indexUrl}/qn/category/1/11`)
        .addChild('汽车', `${picWeb.indexUrl}/qn/category/1/7`)
        .addChild('背景', `${picWeb.indexUrl}/qn/category/1/8`)
        .addChild('动物', `${picWeb.indexUrl}/qn/category/1/9`)
        .addChild('星空', `${picWeb.indexUrl}/qn/category/1/10`)
        .addChild('影视', `${picWeb.indexUrl}/qn/category/1/20`)
        .addChild('人物', `${picWeb.indexUrl}/qn/category/1/19`)
        .addChild('美食', `${picWeb.indexUrl}/qn/category/1/13`)
        .addChild('4K', `${picWeb.indexUrl}/qn/category/1/21`)
        .addChild('5K', `${picWeb.indexUrl}/qn/category/1/23`)
      ,
      new PicCategory('手机壁纸', `${picWeb.indexUrl}/qn/category/3`)
        .addChild('风景', `${picWeb.indexUrl}/qn/category/3/1`)
        .addChild('美女', `${picWeb.indexUrl}/qn/category/3/3`)
        .addChild('动漫', `${picWeb.indexUrl}/qn/category/3/4`)
        .addChild('二次元', `${picWeb.indexUrl}/qn/category/3/5`)
        .addChild('卡通', `${picWeb.indexUrl}/qn/category/3/6`)
        .addChild('游戏', `${picWeb.indexUrl}/qn/category/3/2`)
        .addChild('爱情', `${picWeb.indexUrl}/qn/category/3/11`)
        .addChild('汽车', `${picWeb.indexUrl}/qn/category/3/7`)
        .addChild('背景', `${picWeb.indexUrl}/qn/category/3/8`)
        .addChild('动物', `${picWeb.indexUrl}/qn/category/3/9`)
        .addChild('星空', `${picWeb.indexUrl}/qn/category/3/10`)
        .addChild('影视', `${picWeb.indexUrl}/qn/category/3/20`)
        .addChild('人物', `${picWeb.indexUrl}/qn/category/3/19`)
        .addChild('美食', `${picWeb.indexUrl}/qn/category/3/13`)
      ,
      new PicCategory('其他', `${picWeb.indexUrl}/qn/category/2`)
        .addChild('长腿', `${picWeb.indexUrl}/qn/category/2/12`)
        .addChild('情侣', `${picWeb.indexUrl}/qn/category/2/14`)
        .addChild('清纯', `${picWeb.indexUrl}/qn/category/2/15`)
        .addChild('制服', `${picWeb.indexUrl}/qn/category/2/16`)
        .addChild('诱惑', `${picWeb.indexUrl}/qn/category/2/17`)
        .addChild('御姐', `${picWeb.indexUrl}/qn/category/2/18`)
        .addChild('牛仔', `${picWeb.indexUrl}/qn/category/2/25`)
      ,
    ]

    picWeb.pagePic = (url = picWeb.currTypeUrl, page = 1) => {
      picWeb.currTypeUrl = url
      url = url + (page === 1 ? '' : `index_${page}.html`)
      return new Promise((res, rej) => {
        util.ajax({
          url: url,
          responseType: 'html',
        }).then(([d, r, t]) => {
          picWeb.setImgList(util.nullable(d.querySelector('.flex-images')).map(node => [...node.children]).or([]).map(o => {
            const _title_img = o.querySelector('a.title')
            const _img = o.querySelector('img')
            // 获取图片的img的href
            const href = _title_img.getAttribute('href');

            const _down = o.querySelector('a.down')
            let downType = _down.dataset['type']
            let downName = _down.dataset['name']
            let downFrom = _down.dataset['from']

            let url = picWeb.indexUrl
            let downParams = {name: downName,}
            downType && (downParams.type = downType)
            switch (downFrom) {
              case "qiniu":
                url += "/qiniu/download";
                break;
              case "qn":
                url += "/qn/download";
                downParams.bucket_key = _down.dataset['bucketKey']
                downParams.id = _down.dataset['id']
                break;
              default:
                url += "/download"
            }
            return PicImgInfo.create({
              name: _title_img.innerText,
              oriUrl: `${picWeb.indexUrl}${href}`,
              downFunc() {
                util.ajax({
                  url: `${url}?${encodeURI(Object.entries(downParams).map(([k, v]) => `${k}=${v}`).join('&'))}`,
                  responseType: 'json',
                }).then(([r, rs]) => {
                  const a = document.createElement("a");
                  a.download = r.data.filename;
                  a.style.display = "none";
                  switch (downFrom) {
                    case "qiniu":
                    case "qn":
                      a.href = "data:image/jpg;base64," + r.data.content;
                      break;
                    default:
                      let e = "";
                      r.data.content.data.forEach(function (a, t) {
                        e += String.fromCharCode(a)
                      })
                      a.href = "data:image/jpg;base64," + e
                  }
                  document.body.appendChild(a)
                  a.click()
                  a.remove()

                }).catch(e => console.log(e))
              },
              viewImgSrc: _img.src,
              viewImgAlt: _img.alt,
            })
          }))
          // const start = util.nullable(d.querySelector('.pages .page-number.pgCurrent')).map(n => n.innerText * 1).or(1);
          // const pageItemArr = d.querySelectorAll('.page-number');
          // const pageTotal = util.nullable(pageItemArr[pageItemArr.length - 1]).map(n => n.innerText * 1).or(1);
          // picWeb.page.changePage(start, pageTotal)
          // picWeb.page.changePage(page, 999)
          Vue.set(picWeb, 'pageStart', page)
          console.logPic(picWeb.name, '图片获取', url, {list: picWeb.imgList})

          res(picWeb.imgList)
        })
      })
    }

    picWeb.pagePic()
    return picWeb
  }

  /**
   *
   * @return {PicWeb}
   */
  static baw() {
    const picWeb = new PicWeb('彼岸网', 'https://pic.netbian.com/');
    picWeb.loginUrl = `${picWeb.indexUrl}/e/memberconnect/?apptype=qq`
    picWeb.isLogin = false
    picWeb.headerList.push({tag: 'a', type: 'normal', show: true, url: picWeb.indexUrl, name: picWeb.name})
    picWeb.headerList.push({tag: 'a', type: 'login', show: true, url: picWeb.loginUrl, name: '登陆链接'})

    // 获取分类
    util.ajax({url: picWeb.indexUrl, method: "get", responseType: 'html', encoding: 'gbk',}).then(([d, r, t]) => {
      // 判断是否已经登陆 如果已经登陆则不显示登陆链接
      picWeb.isLogin = !!document.querySelector('.in')
      if (!picWeb.isLogin) for (let header of picWeb.headerList) if (header.type === 'login') header.show = false
      // 获取到分类节点
      let typeNodeList = [...[...d.querySelectorAll(`a.nav-link`)].find(o => o.innerHTML === '分类').nextElementSibling.children]
      // 将分类节点封装成通用分类对象


      picWeb.picCategoryList = [new PicCategory('首页', picWeb.indexUrl, typeNodeList.map(o => new PicCategory(o.innerHTML, `${picWeb.indexUrl}${o.getAttribute('href')}`)))]
      console.logPic(picWeb.name, '获取分类', picWeb.indexUrl, {list: picWeb.picCategoryList})
    })

    picWeb.pagePic = (url = picWeb.currTypeUrl, page = 1) => {
      picWeb.currTypeUrl = url
      url = url + (page === 1 ? '' : `index_${page}.html`)
      return new Promise((res, rej) => {
        util.ajax({
          url: url,
          responseType: 'html',
          encoding: 'gbk',
        }).then(([d, r, t]) => {
          // 需要的图片节点列表
          let imgANodeList = [...d.querySelectorAll(`.slist li > a`)];
          // 获取当前分类id
          let classScript = [...d.querySelectorAll('script[src]')].filter(s => s.src.indexOf('/e/public/onclick/?enews=doclass&classid=') > -1);
          let classid = classScript.length > 0 ? classScript[0].src.match(/\d*$/)[0] : undefined
          // 将图片节点封装成通用图片对象
          picWeb.imgList = imgANodeList.map(o => {
            // 获取图片的img标签
            const _img = o.querySelector('img')
            // 获取图片的img的href
            const href = o.getAttribute('href');
            return PicImgInfo.create({
              name: o.querySelector('b').innerHTML,
              oriUrl: `${picWeb.indexUrl}${href}`,
              downFunc: () => window.open(`${picWeb.indexUrl}/downpic.php?id=${href.match(/[0-9]+/g)[0]}${classid && '&classid=' + classid}`, '_blank'),
              viewImgSrc: `${picWeb.indexUrl}${_img.getAttribute('src')}`,
              viewImgAlt: _img.alt,
            })
          })
          // 处理分页
          let pageNode = d.querySelector('.page')
          // picWeb.page.changePage(pageNode.querySelector('b').innerHTML * 1, Math.max.apply(Math.max, [...pageNode.children].map(o => o.innerHTML * 1).filter(i => !isNaN(i))))
          Vue.set(picWeb, 'pageStart', pageNode.querySelector('b').innerHTML * 1)
          Vue.set(picWeb, 'pageTotal', Math.max.apply(Math.max, [...pageNode.children].map(o => o.innerHTML * 1).filter(i => !isNaN(i))))
          console.logPic(picWeb.name, '图片获取', url, {list: picWeb.imgList})
          res(picWeb.imgList)
        })
      })
    }

    picWeb.pagePic()

    return picWeb
  }
}
