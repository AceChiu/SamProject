  <template>
    <div class="home">
      <el-carousel height="600px" motion-blur>
        <el-carousel-item v-for="(image, index) in images" :key="index">
          <img :src="getImageSrc(image)" alt="Carousel Image" class="carousel-image" />
        </el-carousel-item>
      </el-carousel>
      <div class="background-home">
        <div class="section-title">
          <h2>人生必修課</h2>
          <div class="square-container">
            <div class="square-box physical" style="background-color: #ff6f61">
              <div class="label">生理</div>
            </div>
            <div class="square-box mental" style="background-color: #6b5b95">
              <div class="label">心理</div>
            </div>
            <div class="square-box society" style="background-color: #88b04b">
              <div class="label">社會</div>
            </div>
            <div class="square-box academic" style="background-color: #ffa07a">
              <div class="label">學業</div>
            </div>
          </div>
        </div>
        <div class="section-title">
          <h2>人生選修課</h2>
          <div class="square-container">
            <div class="square-box sport" style="background-color: #ff6f61">
              <div class="label">運動</div>
            </div>
            <div class="square-box art" style="background-color: #6b5b95">
              <div class="label">藝術</div>
            </div>
            <div class="square-box science" style="background-color: #88b04b">
              <div class="label">科學</div>
            </div>
            <div class="square-box life" style="background-color: #ffa07a">
              <div class="label">生活</div>
            </div>
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
    // height: 80vh;
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

  .section-title {
    padding: 20px 0 50px 0;
  }
  
  h2{
    font-size: 35px;
    text-align: center;
    color: black;
  }

  .square-container {
    display: flex;
    flex-wrap: wrap;  
    justify-content: center;
    width: calc(100% - 60px);
    margin: 0px 30px;
    padding: 30px 0px 0px 0px;
  }

  .square-box {
    // height: 230px;
    height: auto;
    aspect-ratio: 1 / 1;
    display: flex;
    align-items: center;
    justify-content: center;
    border: 1px solid #ddd;
    box-shadow: 0 2px 5px rgba(0,0,0,0.1);
    flex-basis: calc(20% - 10px); /* 基于百分比的宽度，并考虑间距 */
    position: relative;
    border: 1px solid #ddd;
    box-shadow: 0px 2px 8px rgba(0, 0, 0, 0.1);
    margin: 10px 5px;
  }

  .physical{
    background-image: url('../assets/required_courses/mental.jpg'); 
    background-size: cover; /* 確保圖片覆蓋整個方塊 */
    background-position: center; /* 讓圖片居中顯示 */
  }

  .mental{
    background-image: url('../assets/required_courses/mental.jpg'); 
    background-size: cover; /* 確保圖片覆蓋整個方塊 */
    background-position: center; /* 讓圖片居中顯示 */
  }

  .society{
    background-image: url('../assets/required_courses/society.jpg'); 
    background-size: cover; /* 確保圖片覆蓋整個方塊 */
    background-position: center; /* 讓圖片居中顯示 */
  }

  .academic{
    background-image: url('../assets/required_courses/academic.jpg'); 
    background-size: cover; /* 確保圖片覆蓋整個方塊 */
    background-position: center; /* 讓圖片居中顯示 */
  }

  .sport{
    background-image: url('../assets/elective_subjects/sport.jpg'); 
    background-size: cover; /* 確保圖片覆蓋整個方塊 */
    background-position: center; /* 讓圖片居中顯示 */
  }

  .art{
    background-image: url('../assets/elective_subjects/art.jpg'); 
    background-size: cover; /* 確保圖片覆蓋整個方塊 */
    background-position: center; /* 讓圖片居中顯示 */
  }

  .science{
    background-image: url('../assets/elective_subjects/science.jpg'); 
    background-size: cover; /* 確保圖片覆蓋整個方塊 */
    background-position: center; /* 讓圖片居中顯示 */
  }

  .life{
    background-image: url('../assets/elective_subjects/life.jpg'); 
    background-size: cover; /* 確保圖片覆蓋整個方塊 */
    background-position: center; /* 讓圖片居中顯示 */
  }

  .label {
    font-size: 20px;
    font-weight: bold;
    color: #fff;
    text-align: center;
  }

  @media (max-width: 950px) {
    .square-box {
      flex-basis: calc(50% - 30px); /* 调整为两列布局 */
    }
  } 

  @media (max-width: 480px) {
    .square-box {
      flex-basis: 100%; /* 调整为一列布局 */
    }
  }
  </style>
