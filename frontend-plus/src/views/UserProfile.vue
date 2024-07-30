<template>
  <div class="home">
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
</template>
<script setup lang="ts">
import pinia from '../store/store'
import { base } from '../store/base'
import { defineComponent } from 'vue'
import { postUpdateUser } from '../util/api/user-profile'
const baseI = base(pinia)
const currentUser = baseI.getUser
function saveUser() {
  postUpdateUser(currentUser).then((response: any) => {
    if (response.data) {
      console.log(response.data)
    }
  })
}

defineComponent({
  name: 'HomeView'
})
</script>
<style lang="scss">
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

.home {
  display: flex;
  flex-wrap: wrap;
  justify-content: center; /* Center panels horizontally */
  max-width: 100%;
  margin: 0 auto;
  padding: 5px;
  text-align: center;
}

.panel {
  flex: 1 1 300px; /* Allows panels to grow, shrink, and start at 300px width */
  margin: 10px; /* Add some spacing between panels */
  min-width: 250px; /* Minimum width to prevent panels from becoming too small */
  box-sizing: border-box; /* Include padding and border in element's total width and height */
  text-align: left;
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
