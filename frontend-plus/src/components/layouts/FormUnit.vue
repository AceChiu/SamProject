<template>
  <el-row align="middle">
    <el-col :span="10">
      <span class="label">使用者名稱</span>
    </el-col>
    <el-col :span="14">
      <el-input v-model="input" placeholder="Enter something"></el-input>
    </el-col>
  </el-row>
</template>
<script setup lang="ts">
import { defineComponent } from 'vue'
import { ref } from 'vue'

const input = ref('XXX')

defineComponent({
  name: 'FormUnit'
})
</script>
<style lang="scss" scoped>
.label {
  display: flex;
  align-items: center;
  height: 100%;
  font-size: 24px; /* 調整文字大小 */
  color: white;
}
</style>
