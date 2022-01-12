<template>
  <div class="equip-calc-context">
    <div class="equip-calc">
      <div class="equip-data-wrapper">
        <div class="equip-data-context">
          <div class="item attribute">
            <ul>
              <li>属性名</li>
              <li style="cursor: pointer; color: #0F88EB" @click="addEquipUse">添加</li>
            </ul>
          </div>
          <div v-for="equipUse in equipContext.equipUseList" :key="equipUse.id" class="item equipUseData header">
            <ul>
              <li>{{ equipUse.id }}</li>
              <li style="cursor: pointer; color: #ff0000" @click="delEquipUse(equipUse)">移除</li>
            </ul>
            <ul>
              <select style="display: list-item; color: red;" @change="e => changeArms(equipUse, e)">
                <option v-for="arms in allArmsList" :value="arms.name" v-html="arms.name"></option>
              </select>
              <select style="display: list-item; color: red;" @change="e => changeEquip(equipUse, e)">
                <option v-for="arms in allUpList" :value="arms.name" v-html="arms.name"></option>
              </select>
              <select style="display: list-item; color: red;" @change="e => changeEquip(equipUse, e)">
                <option v-for="arms in allMiddleList" :value="arms.name" v-html="arms.name"></option>
              </select>
              <select style="display: list-item; color: red;" @change="e => changeEquip(equipUse, e)">
                <option v-for="arms in allDownList" :value="arms.name" v-html="arms.name"></option>
              </select>
              <select style="display: list-item;" @change="e => changeCompare(equipUse, e)">
                <option value="" v-html="'未选择'"></option>
                <option v-for="(equipUse, i) in equipContext.equipUseList" :value="i" v-html="equipUse.id"></option>
              </select>

            </ul>
          </div>
        </div>
        <div class="equip-data-context" v-for="attr in equipContext.getAllAttributeList()">
          <div class="item attribute" v-html="attr.getTypeStr()"></div>
          <template v-for="equipUse in equipContext.equipUseList">
            <div class="item equipUseData" style="display: flex;" :key="equipUse.id">
              <template v-if="equipUse.hasAttr(attr)" style="display: flex">
                <div
                    @click="clickDataAll(equipUse.getAttr(attr))"
                    :class="{hasAttrDesc: hasDesc(equipUse.getAttr(attr))}"
                    style="flex: 1;"
                >
                  {{ equipUse.getDataAll(attr) }}
                </div>
                <template v-if="equipUse.equipUseR">
                  <div style="flex: 1;">
                    <span v-if="equipUse.compareDataAll(attr)"  :style="{color: equipUse.compareDataAll(attr) > 0 ? '#6fb700' : 'red'}">
                      ({{ equipUse.compareDataAll(attr) }})
                    </span>
                  </div>
                  <div><=></div>
                  <div
                      @click="clickDataAll(equipUse.equipUseR.getAttr(attr))"
                      :class="{hasAttrDesc: hasDesc(equipUse.equipUseR.getAttr(attr))}"
                      style="flex: 1;"
                  >
                    {{ equipUse.equipUseR.getDataAll(attr) }}
                  </div>
                </template>
              </template>
            </div>
          </template>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
_bh3 = {
  name: "bh3",
  props: [],
  data() {
    return {
      log: console.log,
      fire: (d) => Swal.fire(d),
      attributeMap: {},
      armsMap: bh3Map.armsMap,
      equipMap: bh3Map.equipMap,
      allEquipMap: bh3Map.allEquipMap,
      allEquipList: bh3Map.allEquipList,
      equipContext: new EquipContext().addEquipUse(new EquipUse(), new EquipUse()),
      key: 0,
    }
  },
  computed: {
    allArmsList() {
      return Object.values(this.armsMap)
    },
    allUpList() {
      return this.allEquipList.filter(o => o.pos === Equip.POS.UP)
    },
    allMiddleList() {
      return this.allEquipList.filter(o => o.pos === Equip.POS.MIDDLE)
    },
    allDownList() {
      return this.allEquipList.filter(o => o.pos === Equip.POS.DOWN)
    },
    hasDesc: () => attr => attr && !!attr.descList.length,
  },
  methods: {
    changeArms(equipUse, e) {
      const equip = this.armsMap[e.target.value];
      e.target.style.color = equip === this.armsMap.武器 ? 'red' : 'initial'
      equipUse.setEquip(equip)
    },
    changeEquip(equipUse, e) {
      const equip = this.allEquipMap[e.target.value];
      e.target.style.color = equip === this.equipMap.圣痕.上 || equip === this.equipMap.圣痕.中 || equip === this.equipMap.圣痕.下
          ? 'red'
          : 'initial'
      equipUse.setEquip(equip)
    },
    /**
     *
     * @param equipUse {EquipUse}
     * @param e
     */
    changeCompare(equipUse, e) {
      const equipUseR = this.equipContext.equipUseList[e.target.value]
      equipUse.compareWith(equipUseR)
    },
    /**
     *
     * @param attr {Attribute}}
     */
    clickDataAll(attr) {
      this.hasDesc(attr) && this.fire({
        html: attr.getAllDescHtml(),
        showConfirmButton: false,
      })
    },
    addEquipUse() {
      this.equipContext.addEquipUse(new EquipUse())
    },
    delEquipUse(equipUse) {
      this.equipContext.delEquipUse(equipUse)
    },
  },
  mounted() {
  },
}
</script>

<style scoped type="text/css" lang="css">
.equip-select-context {
  display: flex;
  align-items: center;
  justify-content: center;
}
.equip-data-context {
  /*grid布局*/
  display: flex;
  /*形成两列的布局，并指定两列各占多少，没有指定grid-template-columns就是默认各占一行，没有table类似的效果，会产生横向滚动条*/
  /*grid-template-columns: 90px 175px 175px;*/
  /*设置div的列宽，1rem在html文本font-size默认为16px,根据字体大小改变*/
  /*grid-column-gap: 1rem;*/
  /*设置div之间的行距*/
  /*grid-row-gap: 1rem;*/
  /*上面两个的简写*/
  /*grid-gap:1rem;*/

}

.equip-data-context > .item {
  background: #eee;
  padding: 3px;
  text-align: center;
}
.equip-data-context > .item.header {
  display: grid;
  grid-template-columns: 20px 1fr;
}
.equip-data-context > .item.attribute {
  width: 90px;
}
.equip-data-context > .item.equipUseData {
  width: 200px;
}
.hasAttrDesc {
  cursor: pointer;
  border-right: 2px red solid;
}

.equip-data-context > .item:nth-child(odd) {
  background: #ddd;
}

.equip-data-context:nth-child(odd) > .item {
  background: #ddd;
}

.equip-data-context:nth-child(odd) > .item:nth-child(odd) {
  background: #eee;
}

/*//.equip-data-wrapper {*/
/*//  // grid布局*/
/*//  display:grid;*/
/*//  // 形成两列的布局，并指定两列各占多少，没有指定grid-template-columns就是默认各占一行，没有table类似的效果，会产生横向滚动条*/
/*//  grid-template-columns:60% 40%;*/
/*//  // 设置div的列宽，1rem在html文本font-size默认为16px,根据字体大小改变*/
/*//  grid-column-gap:1rem;*/
/*//  // 设置div之间的行距*/
/*//  grid-row-gap:1rem;*/
/*//  // 上面两个的简写*/
/*//  // grid-gap:1rem;*/
/*//}*/
</style>
