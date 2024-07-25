<template>
  <div>
    <!-- 折疊menu的圖標 -->
    <div class="menu-toggle" @click="toggleMenu">
      <i v-if="!isCollapsed" class="el-icon-menu">=</i>
      <i v-else class="el-icon-close">X</i>
    </div>
    <!-- 導航條 -->
    <el-menu
      :default-active="activeIndex"
      class="navigation-header"
      mode="horizontal"
      @select="handleSelect"
      background-color="#FFD700"
      :ellipsis="false"
      active-text-color="#473e02"
    >
      <el-menu-item index="1">首頁</el-menu-item>
      <el-menu-item index="2">個人資料</el-menu-item>
      <el-menu-item index="3">任務</el-menu-item>
      <el-menu-item index="4">課程</el-menu-item>
      <el-menu-item index="5">關於我們</el-menu-item>
    </el-menu>

    <!-- 手機上的展開頁面 -->
    <div v-if="isCollapsed" class="mobile-menu">
      <el-menu
        :default-active="activeIndex"
        class="mobile-navigation-header"
        mode="vertical"
        @select="handleSelect"
        background-color="#FFD700"
        :ellipsis="false"
        active-text-color="#473e02"
      >
        <el-menu-item index="1">首頁</el-menu-item>
        <el-menu-item index="2">個人資料</el-menu-item>
        <el-menu-item index="3">任務</el-menu-item>
        <el-menu-item index="4">課程</el-menu-item>
        <el-menu-item index="5">關於我們</el-menu-item>
      </el-menu>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { ref } from 'vue'

const activeIndex = ref('1')
const isCollapsed = ref(false)
const handleSelect = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}
const toggleMenu = () => {
  isCollapsed.value = !isCollapsed.value
}
</script>

<style scoped>
.navigation-header {
  display: flex;
  justify-content: space-around;
  flex-grow: 1; /* 讓導航條佔據剩餘空間 */
}

.el-menu-item {
  white-space: nowrap;
  padding: 0 10px; /* 減少 padding 以節省空間 */
  flex: none;
  --el-menu-hover-text-color: #e2dede;
}

/* 手機和小螢幕樣式 */
@media (max-width: 767.98px) {
  .navigation-header {
    display: none; /* 在小螢幕上隱藏導航條 */
  }

  .menu-toggle {
    display: block; /* 顯示折疊菜單的圖標 */
    cursor: pointer;
    padding: 10px;
    background-color: #ffd700;
    text-align: center;
  }

  .menu-icon {
    width: 24px; /* 設置圖標的寬度 */
    height: 24px; /* 設置圖標的高度 */
  }

  .mobile-menu {
    display: flex;
    flex-direction: column; /* 將菜單項目改為縱向排列 */
    background-color: #ffd700;
    position: absolute;
    top: 50px; /* 調整這個值來確保菜單不會覆蓋圖標 */
    width: 100%;
    z-index: 1000;
  }

  .mobile-navigation-header {
    display: flex;
    flex-direction: column;
  }
}

/* 在大螢幕上隱藏折疊菜單的圖標和展開的菜單頁面 */
@media (min-width: 768px) {
  .menu-toggle {
    display: none;
  }

  .mobile-menu {
    display: none;
  }
}
</style>
