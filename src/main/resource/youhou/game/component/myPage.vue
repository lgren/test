<template>
  <ul class="page-wrapper">
    <li
        v-for="page in currPage.pageList"
        @click="$emit('click', page.page)"
        :class="{ 'page-li': true, 'page-curr': page.page === currPage.curr, }"
        v-html="page.name">
    </li>
    <li>
      跳转到<input type="number" @keyup.enter="pageEnter" class="page-li-input">页
    </li>
    <li
        v-for="page in currPage.firstAndLastList"
        @click="$emit('click', page.page)"
        :class="{ 'page-li': true, 'page-curr': page.page === currPage.curr, }"
        v-html="page.name">
    </li>
  </ul>
</template>

<script>
_myPage = {
  name: "myPage",
  props: ['currPage'],
  mounted() {
  },
  methods: {
    pageEnter(e) {
      this.$emit('click', e.target.valueAsNumber)
    },
  },
}
</script>

<style scoped type="text/less" lang="less">
.page-wrapper {
  display: flex;
}
.page-li {
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

.page-li-input {
  outline: none;
  display: inline-block;
  padding: 0;
  margin: 0px 5px;
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
