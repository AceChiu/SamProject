<template>
  <div class="home">
    <el-carousel height="600px" motion-blur>
      <el-carousel-item v-for="(image, index) in images" :key="index">
        <img :src="getImageSrc(image)" alt="Carousel Image" class="carousel-image" />
      </el-carousel-item>
    </el-carousel>
    <div class="background-home">
      <div class="square-container">
        <div class="square-box" style="background-color: #ff6f61">
          <div class="label">運動</div>
        </div>
        <div class="square-box" style="background-color: #6b5b95">
          <div class="label">藝術</div>
        </div>
        <div class="square-box" style="background-color: #88b04b">
          <div class="label">音樂</div>
        </div>
        <div class="square-box" style="background-color: #ffa07a">
          <div class="label">科技</div>
        </div>
      </div>

      <div class="square-container">
        <div class="square-box" style="background-color: #ff6f61">
          <div class="label">運動</div>
        </div>
        <div class="square-box" style="background-color: #6b5b95">
          <div class="label">藝術</div>
        </div>
        <div class="square-box" style="background-color: #88b04b">
          <div class="label">音樂</div>
        </div>
        <div class="square-box" style="background-color: #ffa07a">
          <div class="label">科技</div>
        </div>
      </div>
    </div>
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
  height: 80vh;
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

.square-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  width: calc(100% - 60px);
  margin: 0px 30px;
  padding: 30px 0px 0px 0px;
}

.square-box {
  flex-grow: 1;
  flex-basis: calc(10% - 10px); /* 基于百分比的宽度，并考虑间距 */
  height: 220px;
  display: flex;
  align-items: center;
  justify-content: center;
  border: 1px solid #ddd;
  box-shadow: 0px 2px 8px rgba(0, 0, 0, 0.1);
  position: relative;
  margin: 10px 5px;
}

.label {
  font-size: 20px;
  font-weight: bold;
  color: #fff;
  text-align: center;
}

@media (max-width: 768px) {
  .square-box {
    flex-basis: calc(50% - 10px); /* 调整为两列布局 */
  }
}

@media (max-width: 480px) {
  .square-box {
    flex-basis: 100%; /* 调整为一列布局 */
  }
}
</style>
