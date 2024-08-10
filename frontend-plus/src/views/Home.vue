<template>
  <div class="home">
    <el-carousel height="600px" motion-blur>
      <el-carousel-item v-for="(image, index) in images" :key="index">
        <img :src="getImageSrc(image)" alt="Carousel Image" class="carousel-image" />
      </el-carousel-item>
    </el-carousel>
    <div class="background-home"></div>
  </div>
</template>
<script setup lang="ts">
import { defineComponent } from 'vue'
import { ref } from 'vue'

const images = ref(['pexels-repuding-12064.jpg', 'pexels-kat-wilcox-329096-923657.jpg'])

const getImageSrc = (image: string) => {
  return new URL(`/src/assets/${image}`, import.meta.url).href
}

defineComponent({
  name: 'HomeView'
})
</script>
<style lang="scss">
.el-carousel__item {
  display: flex;
  justify-content: center;
  align-items: center;
  background-size: cover;
  background-position: center;
  height: 100vh;
}

.carousel-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}

.background-container {
  position: relative;
  height: 150vh;
  width: 100%;
}

.background {
  height: 100%;
  width: 100%;
  object-fit: cover; /* 確保圖片覆蓋整個容器 */
  background-size: cover;
  background-position: center;
  opacity: 0.6; /* 調整透明度 */
}
</style>
