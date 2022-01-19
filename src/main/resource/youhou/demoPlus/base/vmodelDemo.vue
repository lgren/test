<template>

</template>

<script>
_vmodelDemo = {
  name: "vmodelDemo",
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

<style scoped type="text/less" lang="less">

</style>
