<template>
  <div class="background">
    <div class="home-padding">
      <div class="two-side-home">
        <div class="panel">
          <h2 class="title" color="yellow">個人資料</h2>
          <el-row align="middle" class="inputForm">
            <el-col :span="24" style="display: flex; align-items: center">
              <span class="label" style="width: 200px; margin-right: 5px">使用者名稱</span>
              <el-input
                v-model="currentUser.name"
                placeholder="Enter something"
                readonly
              ></el-input>
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

          <!-- Switch 按鈕和手機號碼輸入框同一行 -->
          <el-row align="middle" class="inputForm">
            <el-col :span="24" style="display: flex; align-items: center">
              <span class="label" style="width: 70px; margin-right: 55px">手機</span>
              <el-switch
                v-model="hasMobile"
                style="
                  margin-left: 0;
                  --el-switch-on-color: #13ce66;
                  --el-switch-off-color: #c0c0c0;
                "
              ></el-switch>
              <el-input
                v-model="currentUser.mobile"
                :disabled="!hasMobile"
                placeholder="手機號碼"
                style="margin-left: 10px; flex-grow: 1"
              ></el-input>

              <!-- 手機號碼輸入框 v-model="currentUser.phone"-->
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
              <div class="twzipcode"></div>
              <!-- 這是我們要放入 TWzipcode 的地方 -->
            </el-col>
          </el-row>

          <el-row align="middle" class="inputForm">
            <el-col :span="24" style="display: flex; align-items: center">
              <span class="label" style="width: 200px; margin-right: 5px">街道地址</span>
              <el-input
                v-model="currentUser.address"
                placeholder="請輸入地址詳細資訊"
                class="input-with-select"
              >
              </el-input>
            </el-col>
          </el-row>

          <button class="button" @click="saveUser">儲存</button>
        </div>

        <div class="panel"></div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import pinia from '../store/store'
import { validatePhone, validateAddress, validateMobile } from '../util/common'
import { base } from '../store/base'
import { defineComponent, ref, onMounted } from 'vue'
import { postUpdateUser } from '../util/api/user-profile'
import { ElNotification } from 'element-plus'
// @ts-ignore
import TWzipcode from 'twzipcode.js' // 引入 twzipcode.js

const baseI = base(pinia)
const currentUser = baseI.getUser

// 定義 switch 的狀態
const hasMobile = ref(false)

onMounted(() => {
  // 初始化 TWzipcode 並使用它
  const twzipcode = new TWzipcode('.twzipcode', {
    combine: false, // 是否將郵遞區號併入鄉鎮市區清單
    county: {
      name: 'city', // 縣市 select name 屬性
      label: '請選擇縣市',
      value: '臺北市', // 預設選擇的縣市
      css: ['county', 'form-control'] // 自定義縣市 select 樣式
    },
    district: {
      name: 'district', // 鄉鎮 select name 屬性
      label: '請選擇鄉鎮',
      value: '大安區' // 預設選擇的鄉鎮
    },
    zipcode: false,
    address: false
  })

  // 例如：獲取選中的值並打印
  const selectedData = twzipcode.get()
  console.log('選擇的縣市:', selectedData.county)
  console.log('選擇的鄉鎮:', selectedData.district)
})

function saveUser() {
  if (!validatePhone(currentUser.phone) || !validateAddress(currentUser.address)) {
    return
  }
  if (hasMobile.value && !validateMobile(currentUser.mobile)) {
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
.background {
  background-image: url('../assets/logo.jpg');
  height: 200vh;
  width: 100%;
  background-size: cover;
  background-position: center;
  opacity: 0.6; /* 調整透明度 */
  z-index: -1;
}
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

.twzipcode {
  display: flex;
  align-items: center;
  width: 100%;
}

.twzipcode select {
  width: 100%; /* 调整选单宽度以填满父容器 */
  height: 40px; /* 与输入框的高度一致 */
  font-size: 22px; /* 调整文字大小以与输入框一致 */
  padding: 0 10px; /* 添加内边距以使内容更有空间 */
  box-sizing: border-box; /* 确保 padding 不会影响元素的总宽度 */
  // margin-right: 10px; /* 使两个 select 元素之间有一定的间距 */
}
</style>
