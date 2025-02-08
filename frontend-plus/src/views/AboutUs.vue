<template>
    <div class="background">
      <div class="section-title">
        <h2>關於我們</h2>
      </div>
    </div>
  </template>
  <script setup lang="ts">
  import { defineComponent } from 'vue'

  defineComponent({
    name: 'AboutUs'
  })
  </script>
  <style lang="scss">
    .section-title {
      padding: 20px 0 50px 0;
    }
    
    h2{
      font-size: 35px;
      text-align: center;
      color: white;
    }

  </style>
  