<template>
  <span style="display: inline-block">
    <a v-if="item.isUrl()" :href="item.url" :title="item.name" v-html="item.name" :target="item.target"></a>
    <label v-else-if="item.isObj()" @click.self="util.mClick(...item.click)">
      {{ item.name }}
      <br/>
      <textarea v-html="JSON.stringify(item.obj)"></textarea>
    </label>
  </span>
</template>

<script>
_item = {
  name: "vitem",
  props: ['item'],
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
</style>
