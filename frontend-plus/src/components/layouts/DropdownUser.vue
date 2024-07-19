<template>
  <span class="username" v-if="isDesktop && isLogin">
    {{ userDetial.name }}
  </span>
  <GoogleLogin :callback="callback" v-if="isDesktop && !isLogin" prompt/>
</template>
<script lang="ts" setup>
import pinia from '../../store/store'
import user from '../../util/interface/user'
import login from '../../util/interface/login'
import { base } from '../../store/base'
import { computed, ref, onMounted } from 'vue'
import { decodeCredential } from "vue3-google-login"
import { getIsExistedUser, postRegister } from '../../util/api/auth'
// import { ElMessage } from 'element-plus'
const baseI = base(pinia)
const windowWidth = ref(0)

const loginDetail = ref<login>({
  username: "",
  password: ""
})

const userDetial = ref<user>({
    username: "",
    password: "",
    
    email: "",
    googleId: "",
    name: "",
    familyName: "",
    givenName: "",
    
    birthday: null,
    phone: "",
    address: ""
})

const callback = (response: any) => {
  const googleUser = decodeCredential(response.credential)
  loginDetail.value.username = googleUser.email
  loginDetail.value.password = googleUser.sub
  getIsExistedUser(loginDetail.value.username)
    .then((response: any) => {
      if (!response.data) {
  
      } else {
        postRegister(userDetail.) {

        }
      }
    })
    .catch((error: any) => {
      console.log(error)
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