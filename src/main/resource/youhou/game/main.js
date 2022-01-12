// ==UserScript==
// @name         自我主页2
// @namespace    http://tampermonkey.net/
// @version      0.1
// @description  try to take over the world!
// @author       You
// @include      **
// @icon         data:image/gif;base64,R0lGODlhAQABAAAAACH5BAEKAAEALAAAAAABAAEAAAICTAEAOw==
// @require      https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js
// @require      file:///Users/lgren/Project/Java/0My/test/src/main/resource/youhou/game/util.js
// @require      file:///Users/lgren/Project/Java/0My/test/src/main/resource/youhou/game/base.js
// @require      file:///Users/lgren/Project/Java/0My/test/src/main/resource/youhou/game/part/jym/jym.js
// @resource     myPage file:///Users/lgren/Project/Java/0My/test/src/main/resource/youhou/game/component/myPage.vue
// @resource     jym file:///Users/lgren/Project/Java/0My/test/src/main/resource/youhou/game/part/jym/jym.vue
// @resource     index file:///Users/lgren/Project/Java/0My/test/src/main/resource/youhou/game/index.vue
// @grant        GM_xmlhttpRequest
// @grant        GM_addStyle
// @grant        GM_getResourceText
// @grant        GM_getResourceURL
// ==/UserScript==
if (document.getElementById("__game")) {
  GM_addStyle(`
    * { margin: 0; padding: 0; }
    ul, li { list-style: none; }
    .flex { display: flex; }
  `)
  const myPage = parseVue(GM_getResourceText('myPage'));
  const jym = parseVue(GM_getResourceText('jym'), {myPage,});
  const index = parseVue(GM_getResourceText('index'), {jym,});

  new Vue({
    el: '#__game',
    components: {index,},
    template: `<index></index>`,
  })
}
