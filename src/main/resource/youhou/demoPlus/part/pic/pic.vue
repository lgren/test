<template>
  <div class="pic-wrapper">
    <div>1.单击分类切换, 双击分类跳到原网页. 2.单击图片下载, 双击图片调到源网页</div>
    <div>
      <label style="margin-right: 5px;">图片高度选择
        <select v-model="config.imgHeight.value">
          <option v-for="o in config.imgHeight.selectData" :key="o.v" :value="o.v" v-html="o.name"></option>
        </select>
      </label>
      <label style="margin-right: 5px;">图片预览模式选择
        <select v-model="config.viewModel.value">
          <option v-for="o in config.viewModel.selectData" :key="o.v" :value="o.v" v-html="o.name"></option>
        </select>
      </label>
    </div>
    <ul class="pic-web-wrapper">
      <li v-for="picWeb in picWebList" class="pic-web">
        <div class="pic-web-header">
          <ul v-for="picCategory in picWeb.picCategoryList" class="pic-category-context">
            <li @click="categoryClick(picWeb, picCategory)" class="pic-category" v-html="picCategory.name"></li>
            <li v-for="picCategorySub in picCategory.children"  @click="categoryClick(picWeb, picCategorySub)" class="pic-category" v-html="picCategorySub.name"></li>
          </ul>
        </div>
        <div class="pic-web-body">
          <ul class="img-list" :style="{'flex-wrap': config.viewModel.value}">
            <li v-for="img in picWeb.imgList" class="img-li" @click="imgClick(img)">
              <img :src="img.viewImgSrc" :alt="img.viewImgAlt" class="img" :style="{'height': config.imgHeight.value + 'px',}"
                   :title="img.name">
            </li>
            <li v-if="!picWeb.imgList.length" class="img-li empty">暂无</li>
          </ul>
        </div>
        <div class="pic-web-footer">
          <myPage :curr="picWeb.pageStart" :total="picWeb.pageTotal" @change="([curr]) => imgPageChange(picWeb, curr)"/>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
_pic = {
  name: "pic",
  props: [],
  data() {
    return {
      config: {
        // 图片高度设置
        imgHeight: {
          value: 150,
          selectData: [
            {v: 100, name: '100px'},
            {v: 150, name: '150px'},
            {v: 200, name: '200px'},
            {v: 300, name: '300px'},
            {v: 400, name: '400px'},
          ],
        },
        // 预览模式
        viewModel: {
          value: 'initial', // initial wrap
          selectData: [
            {v: 'initial', name: '水平预览'},
            {v: 'wrap', name: '瀑布式预览'},
          ],
        },
      },
      picWebList: [PicWeb.baw(), PicWeb.tpfxl(),],
    }
  },
  methods: {
    /**
     *
     * @param picWeb {PicWeb}
     * @param category {PicCategory}
     */
    categoryClick(picWeb, category) {
      util.mClick([() => picWeb.pagePic(category.oriUrl)], [() => window.open(category.oriUrl, '_blank')])
    },
    /** @param img {PicImgInfo} */
    imgClick(img) {
      util.mClick([() => img.downFunc()], [() => window.open(img.oriUrl, '_blank')])
    },
    imgPageChange(picWeb, curr) {
      picWeb.pagePic(undefined, curr)
    }
  },
  mounted() {
  },
}
</script>

<style scoped type="text/css" lang="css">
.pic-wrapper {
  margin: 10px;
}

.pic-web-wrapper .pic-web {
  background-color: #eee;
  border-radius: 12px;
  padding: 5px 5px 5px 20px;
  margin: 10px 0;
}

.pic-web-wrapper .pic-web > * {
  margin-bottom: 5px;
}

.pic-web-wrapper .pic-category-context {
  display: flex;
  margin-top: 5px;
}

.pic-web-wrapper .pic-category {
  user-select: none;
  cursor: pointer;
  padding: 2px;
  margin: 0 2px;
  border: 1px solid #000;
  border-radius: 5px;
}

.pic-web-wrapper .img-list {
  display: flex;
  max-width: 100vw;
  overflow: auto;
  max-height: 418px;
}


.pic-web-wrapper .img-li {
  margin-right: 8px
}

.pic-web-wrapper .img-li.empty {
  width: 100%;
  text-align: center;
  color: #ff3939;
}

.pic-web-wrapper .img {
  cursor: pointer;
}
</style>
