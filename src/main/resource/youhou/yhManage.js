// ==UserScript==
// @name         银海管理系统拓展
// @namespace    http://tampermonkey.net/
// @version      0.1
// @description  try to take over the world!
// @author       You
// @include      /^http:\/\/erp\.yinhai\.com:808[2|6]/
// @icon         data:image/gif;base64,R0lGODlhAQABAAAAACH5BAEKAAEALAAAAAABAAEAAAICTAEAOw==
// @require      file:///Users/lgren/Project/Java/0My/test/src/main/resource/youhou/yhManage.js
// @grant        none
// ==/UserScript==


(function () {
  console.log('网页精灵加载中...')
  setInterval(function() {
    findAllFrame(document).forEach(f => (f.contentWindow || f).openWindow=openWindow)
    findAllFrame(document).forEach(f => (f.contentWindow || f).showModalDialog=showModalDialog)

  }, 500)

  document.head.appendChild(createHtml(`
<style>
  .lgrenTab {position: fixed;right: 0;top: 0;height: 100vh;width: 120px;font-size: 14px;z-index: 999;border: 1px solid #00c1de;box-shadow: 0 0 20px rgba(0,198,226,.5);background-color: rgba(255, 255, 255, 0.8);transition: top 1s;}
  .lgrenTab .item {width: 100%;height: 50px;text-align: center;line-height: 50px;}
  .lgrenTab .footer {position: absolute;bottom: 0;}
  .lgrenTab.hidden {top: calc(-100vh + 50px);transition: top 1s;}
</style>
`))
  if (!/body|BODY/.test(document.body.tagName)) {
    const nowBody = document.body
    const newBody = document.createElement('body')
    newBody.style.margin = 0
    newBody.appendChild(nowBody)
    document.body = newBody
  }
  if (document.querySelectorAll('.lgrenTab').length === 0) {
    const lgrenTab = document.body.appendChild(createHtml(`<div class="lgrenTab"></div>`))
    lgrenTab.appendChild(createHtml(`<div class="item footer"><a href="javascript:;" target="_blank" onclick="onShow(this)">关闭</a></div>`))
    lgrenTab.appendChild(createHtml(`<div class="item"><a href="http://erp.yinhai.com:8082/yh_sos.aspx" target="_blank">信息系统主页</a></div>`))
    lgrenTab.appendChild(createHtml(`<div class="item"><a href="http://erp.yinhai.com:8082/index_new.aspx" target="_blank">项目运营管理系统</a></div>`))
    lgrenTab.appendChild(createHtml(`<div class="item"><a href="http://erp.yinhai.com:8086/index.aspx" target="_blank">考勤管理系统</a></div>`))
    //  lgrenTab.appendChild(createHtml(`<div class="item"><a href="javascript:;" target="_blank" onclick="onWriteLog()">写日志</a></div>`))
    //  lgrenTab.appendChild(createHtml(`<div class="item"><a href="javascript:;" target="_blank" onclick="onSignIn()">考勤备注</a></div>`))
    //  lgrenTab.appendChild(createHtml(`<div class="item"><a href="javascript:;" target="_blank" onclick="onLeave()">新增请假</a></div>`))
  }

  console.log('网页精灵加载完成!')
})();


// 创建节点
function createHtml(text) {
  var test = document.createElement(`div`)
  test.innerHTML = text
  return test.children[0]
}


function onShow(node) {
  const lgrenTabList = document.querySelectorAll('.lgrenTab')
  for (const lgrenTab of lgrenTabList) {
    if (lgrenTab.classList.contains('hidden')) {
      lgrenTab.classList.remove('hidden')
      node.innerHTML = '关闭'
    } else {
      lgrenTab.classList.add('hidden')
      node.innerHTML = '打开'
    }
  }

}

// 写日志
function onWriteLog() {
  openWindow({
    url: `http://erp.yinhai.com:8082/WebFrm_Gzrz.aspx?id=${+new Date()}`,
    width: 900,
    left: 400,
    closeBack () {
      console.log('检测到[写日志]窗口关闭')
      const thisIframe = document.querySelectorAll('#mainTab iframe')[1]
      thisIframe && thisIframe.contentWindow.location.reload()

    }
  })
}

// 考勤备注
function onSignIn() {
  openWindow({
    url: `http://erp.yinhai.com:8086/webfrm_kqbzxx_gr.aspx?random=${Math.random()}`,
    width: 700,
    left: 600,
    closeBack () {
      console.log('检测到[考勤备注]窗口关闭')
      const thisIframe = document.querySelector('frame[name=mainFrame]')
      thisIframe && thisIframe.contentWindow.location.reload()
    }
  })
}


// 新增请假
function onLeave() {
  openWindow({
    url: `http://erp.yinhai.com:8086/webfrm_ygqjzy_xz.aspx`,
    width: 1000,
    left: 450,
    closeBack () {
      console.log('检测到[新增请假]窗口关闭')
      const thisIframe = document.querySelector('frame[name=mainFrame]')
      thisIframe && thisIframe.contentWindow.document.getElementById("Img_cx").click()
    }
  })

}

// 打开窗口
function openWindow({url, target='_blank', left, top=200, width, height=600, closeBack, afterFunc}) {
  const thisWindow = window.open(url, target, `channelmode=0,toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=1,resizable=1,width=${width},height=${height},top=${top},left=${left}`,true)

  afterFunc && afterFunc()
  const loop = setInterval(function() {
    if(thisWindow.closed) {
      clearInterval(loop)

      closeBack && closeBack()
    }
  }, 500)
}

function showModalDialog(url, title, config='') {
  const map = new Map(Object.entries({top: 200, dialogHeight: 600, dialogWidth: 1000, target: '_blank'}))
  config.split(';').filter(s => s && s.length).map(s => s.split(/=|:/)).forEach(([k,v]) => map.set(k,v))
  map.set('dialogWidth', 120 + map.get('dialogWidth').replace(/\D/g, '')*1)
  map.set('left', (1900-map.get('dialogWidth'))/2)
  const thisWindow = window.open(url, map.get('target'), `channelmode=0,toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=1,resizable=1,width=${map.get('dialogWidth')},height=${map.get('dialogHeight')},top=${map.get('top')},left=${map.get('left')}`,true)

}

function findAllFrame(thisD, tagArr=['iframe', 'frame'], set=new Set()) {
  set.add(thisD)
  for(tag of tagArr) {
    thisD.querySelectorAll(tag).forEach(d => findAllFrame(d, tagArr, set))
  }
  return set
}
