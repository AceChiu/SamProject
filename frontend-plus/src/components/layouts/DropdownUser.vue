<template>
  <span class="username" v-if="isDesktop && isLogin">
    {{ user.name }}
  </span>
  <GoogleLogin :callback="callback" v-if="isDesktop && !isLogin" prompt/>
</template>
<script lang="ts" setup>
import { computed, ref, onMounted } from 'vue'
import { decodeCredential } from "vue3-google-login"
const windowWidth = ref(0)
const user = ref({});

const callback = (response: any) => {
  user.value = decodeCredential(response.credential)
  console.log("Handle the userData", user.value)
}

const isDesktop = computed(() => windowWidth.value >= 768)
const isLogin = computed(() => Object.keys(user.value).length == 0 ? false : true);
window.addEventListener('resize', () => {
  windowWidth.value = document.documentElement.clientWidth
})
onMounted(() => {
  windowWidth.value = document.documentElement.clientWidth
})
</script>
<style lang="scss">
.username {
  color: gray;
  font-size: 28px;
  cursor: pointer;
}
</style>
