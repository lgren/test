<template>
  <ul class="page-wrapper">
    <li
        v-for="page in myPage.showPageList"
        @click="$emit('change', [page.page, myPage])"
        :class="{ 'page-item': true, 'page-curr': page.isCurr, }"
        v-html="page.showPage">
    </li>
    <li>
      跳转到<input type="number" @keyup.enter="e => $emit('change', [e.target.valueAsNumber, myPage])" class="page-item-input">页
    </li>
    <li
        v-for="page in [myPage.first, myPage.last]"
        @click="$emit('change', [page.page, myPage])"
        :class="{ 'page-item': true, 'page-curr': page.isCurr, }"
        v-html="page.showPage">
    </li>
  </ul>
</template>

<script>
MyPage = class MyPage {
  /**
   *
   * @param curr {number}
   * @param total {number}
   */
  constructor(curr, total) {
    this.id = util.generateKey()

    this.curr = undefined
    this.total = undefined
    this.showPageList = undefined
    this.first = undefined
    this.last = undefined
    this.changePage(curr, total)
  }

  changePage(curr, total) {
    /** @type {{next: {showPage: string, page: number, isCurr: boolean}, showPageList: {showPage: string, page: number, isCurr: boolean}[], last: {showPage: string, page: number, isCurr: boolean}, previous: {showPage: string, page: number, isCurr: boolean}, first: {showPage: string, page: number, isCurr: boolean}}}*/
    const pageInfo = util.calcPage(curr, total, {firstPage: 1, currLeftCount: 4, currRightCount: 5})
    Vue.set(this, 'curr', curr)
    Vue.set(this, 'total', total)
    Vue.set(this, 'showPageList', pageInfo.showPageList)
    Vue.set(this, 'first', pageInfo.first)
    Vue.set(this, 'last', pageInfo.last)
  }
}

/**
 * change([number, page])
 */
_myPage = {
  name: "myPage",
  props: ['curr', 'total'],
  data() {
    return {
      myPage: new MyPage(this.curr, this.total)
    }
  },
  watch: {
    curr() { this.myPage.changePage(this.curr, this.total) },
    total() { this.myPage.changePage(this.curr, this.total) },
  },
  mounted() {
  },
}
</script>

<style scoped type="text/less" lang="less">
.page-wrapper {
  display: flex;
}
.page-item {
  user-select: none;
  cursor: pointer;
  display: inline-block;
  padding: 0 4px;
  margin: 0 2px;
  border: 1px solid #000;
  border-radius: 5px;
  min-width: 15px;
  text-align: center;
  box-shadow: 2px 2px 10px 0 #dadada;
}

.page-item-input {
  outline: none;
  display: inline-block;
  padding: 0;
  margin: 0 5px;
  border: 1px solid rgb(0, 0, 0);
  border-radius: 5px;
  width: 50px;
  height: calc(100% - 2px);
  text-align: center;
  box-shadow: rgb(218, 218, 218) 2px 2px 10px 0px;
}

.page-curr {
  border: 1px solid rgba(99, 181, 4, 1) !important;
  background-color: rgba(99, 181, 4, 1) !important;
  box-shadow: 2px 2px 10px 0 rgba(99, 181, 4,0.48);
  color: #FFF !important;
}
</style>
