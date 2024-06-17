<template>
  <span class="username" v-if="isDesktop && isLogin">
    {{ user.name }}
  </span>
  <GoogleLogin :callback="callback" v-if="isDesktop && !isLogin" prompt/>
</template>
<script lang="ts" setup>
import pinia from '../../store/store'
import { base } from '../../store/base'
import { computed, ref, onMounted } from 'vue'
import { decodeCredential } from "vue3-google-login"
import { getCurrentUser, postCreateUser } from '../../util/api/auth'
import { ElMessage } from 'element-plus'
const baseI = base(pinia)
const windowWidth = ref(0)
const user = ref({});


const callback = (response: any) => {
  user.value = decodeCredential(response.credential)
  let data = {
    "email": user.value.email,
    "name": user.value.name,
    "familyName": user.value.familyName,
    "givenName": user.value.givenName
  }
  getCurrentUser(user.value.email)
    .then((response: any) => {
      console.log(response);
      if (response.data == "") {
        // console.log(data)
        postCreateUser(data)
      }
    })
    .catch((error: any) => {
      ElMessage.error({
        message: error,
        duration: 5000
      })
    })
    .finally(() => {
      baseI.loading = false
    })
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
