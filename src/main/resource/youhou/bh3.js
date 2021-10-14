// ==UserScript==
// @name         崩坏三月光社打开新窗口打开界面
// @namespace    http://tampermonkey.net/
// @version      0.1
// @description  try to take over the world!
// @author       You
// @include      https://m.3rdguide.com/ap/teamnew
// @icon         data:image/gif;base64,R0lGODlhAQABAAAAACH5BAEKAAEALAAAAAABAAEAAAICTAEAOw==
// @require      file:///Users/lgren/Project/Java/0My/test/src/main/resource/youhou/bh3.js
// @grant        none
// ==/UserScript==


(() => {
  for (const a of document.querySelectorAll('.re_item--new>a')) {
    a.target = '_blank'
  }
  console.log('lgren begin')
  let template = document.querySelectorAll('#J-tplList')[0].innerHTML
  template = document.querySelectorAll('#J-tplList')[0].innerHTML = template.replace('<a href="{{d[i].info_url}}">', '<a href="{{d[i].info_url}}" target="_blank">')
  console.log('lgren end')
})();
