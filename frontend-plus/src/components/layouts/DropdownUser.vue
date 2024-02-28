<template>
  <el-dropdown @command="handleCommand" class="dropdown-user">
    <span class="el-dropdown-link" v-if="isDesktop">
      Welcome, {{ user.username }}<el-icon class="el-icon--right"><arrow-down /></el-icon>
    </span>
    <span v-else class="el-dropdown-link"
      ><el-icon size="20"><Avatar /></el-icon
    ></span>
    <template #dropdown>
      <!-- <el-dropdown-menu>
        <el-dropdown-item command="logout">Logout</el-dropdown-item>
        <el-dropdown-item command="switchUser">Switch User</el-dropdown-item>
      </el-dropdown-menu> -->

      <el-dropdown-menu>
        <el-dropdown-item command="login">Logout</el-dropdown-item>
        <el-dropdown-item command="switchUser">Switch User</el-dropdown-item>
      </el-dropdown-menu>
    </template>
  </el-dropdown>
</template>
<script lang="ts" setup>
import { computed, ref, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { ArrowDown, Avatar } from '@element-plus/icons-vue'
import { base } from '../../store/base'
import { useRouter } from 'vue-router'
import { getCurrentUser, putSwitchUser } from '../../util/api/auth'
import Cookies from 'js-cookie'

const router = useRouter()
const baseI = base()
const user: any = computed(() => baseI.getUser)

const windowWidth = ref(0)
const handleCommand = (command: string | number | object) => {
  if (command === "login") {
    //login
  
  } else if (command === 'logout') {
    Cookies.remove(import.meta.env.VITE_APP_AUTH_TOKEN_NAME)
    router.push({ name: 'home' })
  } else {
    ElMessageBox.prompt('Please input User Account', {
      confirmButtonText: 'OK',
      cancelButtonText: 'Cancel'
    })
      .then(({ value }) => {
        baseI.setLoading(true)
        putSwitchUser(value)
          .then((response: any) => {
            Cookies.set(import.meta.env.VITE_APP_AUTH_TOKEN_NAME, response.data.access_token, {
              expires: 1000 * 60 * 60 * 6
            })
            getCurrentUser()
              .then((response: any) => {
                baseI.setUser(response.data)
              })
              .catch((error: any) => {
                baseI.setLogin(false)
                Cookies.remove(import.meta.env.VITE_APP_AUTH_TOKEN_NAME)
                console.error(error)
              })
          })
          .catch((error: any) => {
            ElMessageBox.alert(error.response.data.messages[1], error.response.data.messages[0])
          })
          .finally(() => {
            baseI.setLoading(false)
          })
      })
      .catch(() => {
        ElMessage({
          type: 'info',
          message: 'Input canceled'
        })
      })
  }
}
const isDesktop = computed(() => windowWidth.value >= 768)
window.addEventListener('resize', () => {
  windowWidth.value = document.documentElement.clientWidth
})
onMounted(() => {
  windowWidth.value = document.documentElement.clientWidth
})
</script>
<style lang="scss">
.dropdown-user {
  color: #fff !important;
  font-size: 14px;
  cursor: pointer;
}
</style>
