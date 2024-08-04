<template>
  <div class="home">
    <el-carousel height="600px" motion-blur>
      <el-carousel-item v-for="(image, index) in images" :key="index">
        <img :src="getImageSrc(image)" alt="Carousel Image" class="carousel-image" />
      </el-carousel-item>
    </el-carousel>
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
</style>
