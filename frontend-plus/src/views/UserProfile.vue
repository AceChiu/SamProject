<template>
  <div class="home-padding">
    <div class="two-side-home">
      <div class="panel">
        <h2 class="title" color="yellow">個人資料</h2>
        <el-row align="middle" class="inputForm">
          <el-col :span="24" style="display: flex; align-items: center">
            <span class="label" style="width: 200px; margin-right: 5px">使用者名稱</span>
            <el-input v-model="currentUser.name" placeholder="Enter something" readonly></el-input>
          </el-col>
        </el-row>

        <el-row align="middle" class="inputForm">
          <el-col :span="24" style="display: flex; align-items: center">
            <span class="label" style="width: 200px; margin-right: 5px">Email</span>
            <el-input
              v-model="currentUser.username"
              type="email"
              placeholder="Enter something"
              readonly
            ></el-input>
          </el-col>
        </el-row>

        <el-row align="middle" class="inputForm">
          <el-col :span="24" style="display: flex; align-items: center">
            <span class="label" style="width: 200px; margin-right: 5px">電話</span>
            <el-input v-model="currentUser.phone"></el-input>
          </el-col>
        </el-row>

        <el-row align="middle" class="inputForm">
          <el-col :span="24" style="display: flex; align-items: center">
            <span class="label" style="width: 200px; margin-right: 5px">生日</span>
            <el-date-picker
              v-model="currentUser.birthday"
              type="date"
              style="height: 40px; width: 100%"
            />
          </el-col>
        </el-row>

        <el-row align="middle" class="inputForm">
          <el-col :span="24" style="display: flex; align-items: center">
            <span class="label" style="width: 200px; margin-right: 5px">地址</span>
            <el-input v-model="currentUser.address"></el-input>
          </el-col>
        </el-row>
        <button class="button" @click="saveUser">儲存</button>
      </div>

      <div class="panel"></div>
    </div>
  </div>
</template>

<script setup lang="ts">
import pinia from '../store/store'
import { base } from '../store/base'
import { defineComponent } from 'vue'
import { postUpdateUser } from '../util/api/user-profile'
import { ElNotification } from 'element-plus'

const baseI = base(pinia)
const currentUser = baseI.getUser

function validatePhone() {
  const phone = currentUser.phone
  const mobilePattern = /^09\d{8}$/
  const landlinePattern = /^(?:02|03|037|039|04|049|05|06|07|08|089)\d{7,8}$/

  if (!phone) {
    return false
  }
  if (phone.startsWith('09')) {
    if (!mobilePattern.test(phone)) {
      ElNotification({
        title: '電話格式錯誤',
        message: '請輸入正確的手機號碼，例如：09xxxxxxxx',
        type: 'warning'
      })
      return false
    }
  } else if (!landlinePattern.test(phone)) {
    ElNotification({
      title: '電話格式錯誤',
      message: '請輸入正確的市話號碼，例如：02xxxxxxxx',
      type: 'warning'
    })
    return false
  }
  ElNotification({
    title: '電話格式正確',
    message: '電話格式正確',
    type: 'success'
  })
  return true
}

function validateAddress() {
  const address = currentUser.address
  const addressPattern = /^[\u4e00-\u9fa5a-zA-Z0-9\s,.'-]{10,}$/

  if (!addressPattern.test(address)) {
    ElNotification({
      title: '地址格式錯誤',
      message: '請輸入正確的地址',
      type: 'warning'
    })
    return false
  }
  ElNotification({
    title: '地址格式正確',
    message: '地址格式正確',
    type: 'success'
  })
  return true
}
function saveUser() {
  if (!validatePhone() || !validateAddress()) {
    return
  }

  postUpdateUser(currentUser).then((response: any) => {
    if (response.data) {
      console.log(response.data)
      ElNotification({
        title: '更新完成',
        message: response.data.username + '更新完成',
        type: 'success'
      })
    }
  })
}

defineComponent({
  name: 'UserProfileView'
})
</script>

<style lang="scss">
.home-padding {
  padding: 100px;
}

.inputForm {
  margin: 10px;
}
.label {
  font-size: 22px;
  width: 100%;
  color: white;
}

.el-input {
  height: 40px; /* 自定義輸入框高度 */
  font-size: 22px; /* 調整輸入框文字大小 */
}

.title {
  font-size: 2.5rem;
  margin-bottom: 20px;
}

.button {
  background-color: #4caf50;
  color: white;
  padding: 10px 20px;
  font-size: 1.2rem;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.button:hover {
  background-color: #45a049;
}

.subtitle {
  font-size: 1.5rem;
  margin-top: 20px;
  margin-bottom: 10px;
}
</style>
