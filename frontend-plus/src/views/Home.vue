<template>
  <div class="home">
    <img alt="Vue logo" class="element-plus-logo" src="../assets/logo.png" />
    <HelloWorld msg="Hello Vue 3.0 + Element Plus + Vite in Delta" />
  </div>
</template>
<script setup lang="ts">
import { defineComponent } from 'vue'

defineComponent({
  name: 'HomeView'
})
</script>
<style lang="scss">
.element-plus-logo {
  width: 50%;
}
</style>
