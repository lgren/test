<template>
  <div>
    <div class="header">
      <ul class="type-context">
        <li class="type" @click="log">
          打印数据
        </li>
        <li
            v-for="type in web.typeList"
            :key="type.url"
            class="type"
            @click="mClick([() => web.getPage(type.url)], [() => open(type.url)])"
        >
          {{ type.name }}
        </li>
      </ul>
    </div>
    <ul class="goods-context">
      <li v-for="goods in web.currPage.list" :key="goods.url" class="goods-item">
        <a :href="goods.url" style="width: 300px" :title="goods.name" v-html="goods.name" target="_blank"></a>
        <span style="color: rgba(255, 82, 82, 1); width: 50px;" v-html="goods.price"></span>
        <span style="width: 400px;" v-html="goods.service"></span>
        <span style="width: 50px;" v-html="goods.credit"></span>
        <span style="width: 180px;" v-html="goods.okRate"></span>
      </li>
    </ul>
    <div class="footer">
      <myPage :currPage="web.currPage" @click="curr => web.getPage(undefined, curr)"/>
    </div>
  </div>
</template>

<script>
_Index = {
  name: 'index',
  data() {
    return {
      mClick,
      open: url => window.open(url, '_blank'),
      web: new JYM(),
    }
  },
  mounted() {
    const vm = this
    this.web.getPage(`https://m.jiaoyimao.com/api2/goodsList/getGoodsListByCondition`, {
      gameId: 1004502,
      platformId: 3,
      stdCatId: 182731,
      jymCatId: 182732,
      jymCatName: '成品号',
      clientId: 110,
      clientName: '全部客户端',
      page: 1,
      sort: 2,
      cname: '成品号',
      systerName: '苹果',
      sortName: '最新发布',
    })
  },
  methods: {
    log() {
      console.log(deepCopy(this.web))
    },
  }
}
</script>

<style scoped type="text/less" lang="less">
.type-context {
  display: flex;
}

.type {
  user-select: none;
  cursor: pointer;
  padding: 2px 2px 0 2px;
  margin: 0 2px;
  border: 1px solid #000;
  border-radius: 5px;
}

.goods-item>* {
  display: inline-block;
}

</style>
