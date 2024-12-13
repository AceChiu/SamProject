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

    const images = ref(['carousel_0.jpg','carousel_1.jpg', 'carousel_2.jpg', 'carousel_3.jpg', 'carousel_4.jpg'])

    const getImageSrc = (image: string) => {
      return new URL(`/src/assets/carousel/${image}`, import.meta.url).href
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
      height: 100%;
      width: 100%;
    }
    
    
    .carousel-image {
      width: 100%;
      height: 100%;
      object-fit: contain;
    }

    .el-carousel__item:nth-child(2n) {
      background-color: black;
    }

    .el-carousel__item:nth-child(2n + 1) {
      background-color: black;
    }

    .section-title {
      padding: 20px 0 50px 0;
    }
    
    h2{
      font-size: 35px;
      text-align: center;
      color: white;
    }

    .square-container {
      display: flex;
      flex-wrap: wrap;  
      justify-content: space-between;
      width: 100%;
      margin: 0;
      padding: 0;
    }

    .square-box {
      height: auto;
      aspect-ratio: 1 / 1;
      display: flex;
      align-items: center;
      justify-content: center;
      box-shadow: 0 2px 5px rgba(0,0,0,0.1);
      flex-basis: 25%; /* 基于百分比的宽度，并考虑间距 */
      position: relative;
      box-shadow: 0px 2px 8px rgba(0, 0, 0, 0.1);
      overflow: hidden;
      transition: transform 0.3s ease, box-shadow 0.3s ease;
    }

    /* 覆蓋層 */
    .square-box::before {
      content: '';
      position: absolute;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      background: inherit; /* 繼承父層背景圖片 */
      filter: blur(0px); /* 模糊效果 */
      transition: filter 0.3s ease; /* 平滑模糊過渡 */
      z-index: 0; /* 位於最底層 */
    }

    .square-box:hover::before {
      filter: blur(10px); /* 增加模糊效果 */
      opacity: 1; /* 顯示模糊背景 */
    }

    /* 在 hover 時，增加覆蓋層的漸層效果 */
    .square-box::after {
      content: '';
      position: absolute;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      background: linear-gradient(
        to bottom,
        rgba(255, 255, 255, 0.4),
        rgba(255, 255, 255, 0.2)
      ); /* 漸層白色覆蓋 */
      opacity: 0; /* 初始隱藏 */
      transition: opacity 0.3s ease; /* 平滑過渡 */
      z-index: 1; /* 位於文字之下，模糊層之上 */
      filter: blur(5px); /* 增加模糊強度 */
    }

    .square-box:hover::after {
        opacity: 1; /* 顯示漸層覆蓋 */
    }
      
      /* hover 時覆蓋層變白 */  
      .square-box:hover::before {
        background-color: rgba(255, 255, 255, 0.6); /* 半透明白色覆蓋 */
      }

    .label {
      font-size: 20px;
      font-weight: bold;
      color: #fff;
      text-align: center;
      opacity: 0; /* 初始隱藏 */
      transform: translateY(20px); /* 初始位置向下 */
      transition: transform 0.3s ease, opacity 0.3s ease; /* 動畫過渡效果 */
      text-shadow: 0px 2px 5px rgba(0, 0, 0, 0.5); /* 文字陰影 */
    }
    .square-box:hover .label {
      opacity: 1; /* 顯示文字 */
      transform: translateY(0); /* 文字上移至中心 */
    }
    
    .physical{
      background-image: url('../assets/required_courses/physic.jpg'); 
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
      background-image: url('../assets/elective_subjects/a.jpg'); 
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
