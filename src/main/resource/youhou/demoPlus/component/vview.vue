<template>
  <div>
    <nav>
      <template v-for="row in view.getHeaderList()">
        <div v-if="row instanceof Array">
          <vitem v-for="(col, j) in row" :key="j" :item="col" class="item"/>
        </div>
        <div v-else>
          <vitem :item="row" class="item"/>
        </div>
      </template>
    </nav>
    <main style="height: 200px;">身体</main>
    <footer class="footer">尾</footer>
  </div>
</template>

<script>
_view = {
  name: "vview",
  props: ['view'],
  data() {
    return {
      util,
    }
  },
  mounted() {
  },
  methods: {
  },
}
</script>

<style scoped type="text/less" lang="less">
nav {
}
nav .item {
  margin-right: 5px;
}
.context {
  //display: flex;
  //flex-direction: column;
}
</style>
