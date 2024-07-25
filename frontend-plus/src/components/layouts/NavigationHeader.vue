<template>
  <el-menu
    :default-active="activeIndex"
    class="el-menu-demo"
    mode="horizontal"
    background-color="#545c64"
    text-color="#fff"
    active-text-color="#ffd04b"
    :ellipsis="false"
    @select="handleSelect"
  >
    <el-menu-item index="home">首頁</el-menu-item>
    <el-menu-item index="user-profile">個人資料</el-menu-item>
  </el-menu>
</template>
<script lang="ts" setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const $router = useRouter()
const activeIndex = ref('home')

const handleSelect = (key: string) => {
  $router.push({ name: key })
}
</script>
