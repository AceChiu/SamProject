<template>
  <span class="username" v-if="isDesktop && isLogin">
    {{ userDetial.name }}
  </span>
  <GoogleLogin :callback="callback" v-if="isDesktop && !isLogin" prompt/>
</template>
<script lang="ts" setup>
import pinia from '../../store/store'
import user from '../../util/interface/user'
import { base } from '../../store/base'
import { computed, ref, onMounted } from 'vue'
import { decodeCredential } from "vue3-google-login"
import { getCurrentUser, postCreateUser } from '../../util/api/auth'
import { ElMessage } from 'element-plus'
const baseI = base(pinia)
const windowWidth = ref(0)
const userDetial = ref<user>({
    email: "",
    name: "",
    familyName: "",
    givenName: "",
    googleId: ""
});


const callback = (response: any) => {
  const googleUser = decodeCredential(response.credential)
  userDetial.value.email = googleUser.email
  userDetial.value.name = googleUser.given_name
  userDetial.value.familyName = googleUser.family_name
  userDetial.value.givenName = googleUser.given_name
  userDetial.value.googleId = googleUser.sub
  console.log(googleUser)
  getCurrentUser(userDetial.value.email)
    .then((response: any) => {
      console.log(response);
      if (response.data == "") {
        // console.log(data)
        postCreateUser(userDetial.value)
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
const isLogin = computed(() => userDetial.value.googleId == "" ? false : true)
const isDesktop = computed(() => windowWidth.value >= 768)
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
../../util/interface/user