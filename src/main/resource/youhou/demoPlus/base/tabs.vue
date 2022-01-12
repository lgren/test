<template>
  <div class="tabs-wrapper">
    <ul class="tabs-header">
    </ul>
    <div class="tabs-body">
    </div>
  </div>
</template>

<script>
_tab = {
  name: "tab",
  props: ['value'],
  data() {
    return {
      v: undefined,
      isNotChangeV: false,
    }
  },
  methods: {
    // 内部改变一律使用此方法
    changeV(nv, isNotChangeV = false) {
      this.isNotChangeV = isNotChangeV;
      this.v = nv;
      this.$emit('input', this.v);
      this.$emit('change', this.v);
    },
  },
  mounted() {
    this.changeV(this.value)
  },
  watch: {
    value(nv, ov) {
      !this.isNotChangeV && this.changeV(nv)
    }
  },
}
</script>

<style scoped type="text/css" lang="css">
.tabs-header {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-wrap: wrap;
}
</style>
