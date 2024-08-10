<template>
  <div>
    <el-container class="layout">
      <el-container class="layout-container">
        <el-header class="header">
          <DefaultHeader />
        </el-header>
        <el-container class="layout-container__main">
          <el-main>
            <slot />
          </el-main>
          <el-footer class="footer"
            >Copyright &copy; 2023 Delta IT . All Rights Reserved.</el-footer
          >
        </el-container>
      </el-container>
    </el-container>
  </div>
</template>
<script setup lang="ts">
import DefaultHeader from './DefaultHeader.vue'
</script>
<style lang="scss" scoped>
.layout {
  height: 200vh;
}
.layout-container {
  height: 200vh;
  &__main {
    overflow-y: auto;
  }
}
.aside {
  position: relative;
  background-color: #fff;
  box-shadow: 0 0 10px rgba(#000, 0.1);
}
.header {
  background-color: $delta-gold;
  line-height: 60px;
  text-align: center;
  font-size: 16px;
  color: #fff;
}
.el-main {
  padding: 0 !important;
}
.footer {
  background-color: $delta-silver-white;
  height: unset;
  text-align: center;
  font-size: 12px;
}
.desktop-aside-menu {
  @media (max-width: 767.98px) {
    display: none;
  }
}
.mobile-sidebar {
  display: none;
  @media (max-width: 767.98px) {
    display: block;
  }
}
.navigation-header {
  @media (max-width: 767.98px) {
    display: none;
  }
}
</style>
