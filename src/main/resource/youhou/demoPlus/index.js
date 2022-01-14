// ==UserScript==
// @name         完全体主页
// @namespace    http://tampermonkey.net/
// @version      0.1
// @description  try to take over the world!
// @author       You
// @include      **
// @icon         data:image/gif;base64,R0lGODlhAQABAAAAACH5BAEKAAEALAAAAAABAAEAAAICTAEAOw==
// @connect      m.jiaoyimao.com
// @connect      image.fenxianglu.cn
// @connect      pic.netbian.com

// @require      file:///Users/lgren/Project/Java/0My/test/src/main/resource/youhou/js/vue.min.js
// @require      file:///Users/lgren/Project/Java/0My/test/src/main/resource/youhou/js/sweetalert2.all.min.js
// @require      file:///Users/lgren/Project/Java/0My/test/src/main/resource/youhou/demoPlus/util.js

// @resource     myPage file:///Users/lgren/Project/Java/0My/test/src/main/resource/youhou/demoPlus/component/myPage.vue
// @resource     tabs file:///Users/lgren/Project/Java/0My/test/src/main/resource/youhou/demoPlus/base/tabs.vue

// @require      file:///Users/lgren/Project/Java/0My/test/src/main/resource/youhou/demoPlus/part/pic/pic.js
// @resource     pic file:///Users/lgren/Project/Java/0My/test/src/main/resource/youhou/demoPlus/part/pic/pic.vue

// @require      file:///Users/lgren/Project/Java/0My/test/src/main/resource/youhou/demoPlus/part/bh3/bh3.js
// @require      file:///Users/lgren/Project/Java/0My/test/src/main/resource/youhou/demoPlus/part/bh3/bh3Data.js
// @resource     bh3 file:///Users/lgren/Project/Java/0My/test/src/main/resource/youhou/demoPlus/part/bh3/bh3.vue


// @require      file:///Users/lgren/Project/Java/0My/test/src/main/resource/youhou/demoPlus/index.js
// @grant        GM_xmlhttpRequest
// @grant        GM_addStyle
// @grant        GM_getResourceText
// @grant        GM_getResourceURL
// ==/UserScript==

/*
// @require      https://cdn.jsdelivr.net/npm/vue@2/dist/vue.min.js
// @require      https://cdn.jsdelivr.net/npm/sweetalert2@10.16.6/dist/sweetalert2.all.min.js
说明:
1. 跨域网站: @connect <url>
  例如 @connect      m.jiaoyimao.com
2. js资源: @require <url>
  例如 联网 @require      https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js
  例如 本机 @require      file:///Users/lgren/Project/Java/0My/test/src/main/resource/youhou/demoPlus/util.js
3. 其他文件资源 @resource <name> <url>
  例如 vue文件 @resource     myPage file:///Users/lgren/Project/Java/0My/test/src/main/resource/youhou/demoPlus/component/myPage.vue
*/
const isDebug_mode = false;
Vue.config.debug = isDebug_mode;
Vue.config.devtools = isDebug_mode;
Vue.config.productionTip = isDebug_mode;

const thisUrl = document.location.href
// csdn code不可选中
if (thisUrl.indexOf('blog.csdn.net') > -1) {
  console.log(`油猴`, `csdn code不可选中`)
  setInterval(() => {
    document.querySelectorAll('code').forEach(n => n.style.userSelect='text')
  }, 5000)
}

// 米游社不可选中修改以及复制有添加内容
if (thisUrl.indexOf('bbs.mihoyo.com/bh3/wiki') > -1) {
  console.log(`油猴`, `米游社不可选中修改以及复制有添加内容`)
  document.body.style.userSelect = 'initial'
  document.addEventListener('copy', e => {
    console.log('lgren', e.clipboardData)
    let htmlT = e.clipboardData.getData('text/html')
    e.clipboardData.setData('text/html', htmlT.substring(0, htmlT.lastIndexOf('<br><br>')))
    let plainT = e.clipboardData.getData('text/plain')
    e.clipboardData.setData('text/plain', plainT.substring(0, plainT.lastIndexOf('\n\n')))
  })
}

// 个人主页
if (document.getElementById("__game")) {
  GM_addStyle(`
    * { margin: 0; padding: 0; }
    ul, li { list-style: none; }
    .flex { display: flex; }
  `);
  const myPage = util.parseVue(GM_getResourceText('myPage'));
  const tabs = util.parseVue(GM_getResourceText('tabs'));

  const pic = util.parseVue(GM_getResourceText('pic'), { myPage, });


  const bh3 = util.parseVue(GM_getResourceText('bh3'));

  new Vue({
    el: '#__game',
    components: {tabs, pic, bh3, },
    template: `
<div style="margin: 20px;">
  <pic/>
  <tabs/>
  <bh3/>
  <div>
    <label>
      交易猫苹果查询: 苹果 700以上 按照最新发布排序<br>
      <textarea style="width: 430px; height: 280px;">
        uutil.ajax({
          url: 'https://m.jiaoyimao.com/api2/goodsList/getGoodsListByCondition',
          data: {
            gameId: 1004502,
            platformId: 3,
            stdCatId: 182731,
            jymCatId: 182732,
            jymCatName: \`成品号\`,
            clientId: 110,
            clientName: \`全部客户端\`,
            page: 1,
            sort: 2,
            cname: \`成品号\`,
            systerName: \`苹果\`,
            'r-101': \`700\`,
            'r-1609236672220325': \`,80\`,
            condition: \`{"r-101":"700","r-1609236672220325":",80"}\`,
            sortName: \`最新发布\`,
          }}).then(([r]) => {
            for(const goods of r.data) {
              console.log(goods.title, goods.price, { url: goods.GoodsDetailUrl, })
            }
          })
      </textarea>
      <textarea>
        GoodsDetailUrl: "https://m.jiaoyimao.com/goods/1635009081765584.html"
        abtestId: "aliabtest188963_172607"
        category: {rootId: 1, firstCategoryId: 1, firstCategoryName: "帐号", categoryId: 1475983452895595, categoryName: "米哈游帐号", …}
        defaultImage: null
        description: "没时间玩了，全角色毕业号，诚心可以代价私聊"
        detailUrl: "https://m.jiaoyimao.com/goods/1635009081765584.html"
        discount: 10
        experimentId: "54684"
        features: (3) [{…}, {…}, {…}]
        gmtCreate: "2021-11-29T13:08:48.000Z"
        goodsId: 1635009081765584
        images: (17) [{…}, {…}, {…}, {…}, {…}, {…}, {…}, {…}, {…}, {…}, {…}, {…}, {…}, {…}, {…}, {…}, {…}]
        keyProperties: (6) [{…}, {…}, {…}, {…}, {…}, {…}]
        originPrice: null
        originTitle: "全角色毕业号"
        price: 10800
        promotionTags: []
        sellerId: 1592279980775558
        slotId: "43a27731fec74753946744479c18c44561b1d46d"
        solutionId: null
        status: 3
        storage: 1
        tags: (2) [{…}, {…}]
        title: "【88级】全角色毕业号"
        track: null
        unitPrice: 0
        useItemDetailUrl: true
      </textarea>
    </label>
  </div>
</div>
`,
    data() {
      return {
      }
    },
    mounted() {
      unsafeWindow.uutil = util
    },
  });
}
