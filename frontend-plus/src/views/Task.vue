<template>
  <div class="background">
    <div class="two-side-home">
      <div class="panel">
        <h2 class="title" color="yellow">每日任務</h2>
        <div class="task-buttons">
          <button v-for="(task, index) in dailyTasks" :key="index" class="task-button">
            {{ task }}
          </button>
        </div>
        <button
          class="button"
          @click="spinDailyWheel"
          :disabled="dailyTasks.length < 4 || dailySpinning"
        >
          抽任務
        </button>
        <transition name="fade">
          <div v-if="dailySpinning" class="overlay">
            <div class="spinner"></div>
          </div>
        </transition>
        <div class="task-buttons">
          <button v-for="(task, index) in selectedDailyTasks" :key="index" class="task-button">
            {{ task }}
          </button>
        </div>
        <!-- 新增的每日任務項目 -->
        <div>
          <h3>回家作業</h3>
          <div class="task-container">
            <transition name="stamp-fade">
              <button v-if="!homeworkCompleted" class="task-button" @click="markHomeworkCompleted">
                完成
              </button>
            </transition>

            <transition name="stamp-animation">
              <div v-if="homeworkCompleted" class="stamp-container">
                <img src="../assets/completed.png" alt="完成印章" class="stamp" />
              </div>
            </transition>
          </div>

          <h3>閱讀任務</h3>
          <div class="task-container">
            <transition name="stamp-fade">
              <button v-if="!readingCompleted" class="task-button" @click="markReadingCompleted">
                完成
              </button>
            </transition>

            <transition name="stamp-animation">
              <div v-if="readingCompleted" class="stamp-container">
                <img src="../assets/completed.png" alt="完成印章" class="stamp" />
              </div>
            </transition>
          </div>

          <h3>運動任務</h3>
          <div class="task-container">
            <transition name="stamp-fade">
              <button v-if="!exerciseCompleted" class="task-button" @click="markExerciseCompleted">
                完成
              </button>
            </transition>

            <transition name="stamp-animation">
              <div v-if="exerciseCompleted" class="stamp-container">
                <img src="../assets/completed.png" alt="完成印章" class="stamp" />
              </div>
            </transition>
          </div>
        </div>

        <div>
          <h3>回家作業完成時數表</h3>
          <table class="task-table">
            <thead>
              <tr>
                <th>科目</th>
                <th>念書時間</th>
                <th>說明</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(entry, index) in studyHours" :key="index">
                <td>{{ entry.subject }}</td>
                <td>
                  <div class="time-input">
                    <input type="number" v-model="entry.hours" min="0" />
                    <span>小時</span>
                    <input type="number" v-model="entry.minutes" min="0" max="59" />
                    <span>分鐘</span>
                  </div>
                </td>
                <td>
                  <input type="text" v-model="entry.description" />
                </td>
              </tr>
            </tbody>
          </table>
          <button class="button" @click="saveStudyHours">儲存</button>
        </div>
      </div>
      <div class="panel">
        <h2 class="title" color="yellow">每週任務</h2>
        <div class="task-buttons">
          <button v-for="(task, index) in weeklyTasks" :key="index" class="task-button">
            {{ task }}
          </button>
        </div>
        <button
          class="button"
          @click="spinWeeklyWheel"
          :disabled="weeklyTasks.length < 4 || weeklySpinning"
        >
          抽任務
        </button>
        <transition name="fade">
          <div v-if="weeklySpinning" class="overlay">
            <div class="spinner"></div>
          </div>
        </transition>
        <div class="task-buttons">
          <button v-for="(task, index) in selectedWeeklyTasks" :key="index" class="task-button">
            {{ task }}
          </button>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
import { defineComponent } from 'vue'
import { ref } from 'vue'

const dailyTaskOptions = ['運動', '閱讀', '英文', '英文閱讀']
const weeklyTaskOptions = ['國文', '英文', '社會', '自然科學']
const dailyTasks = ref<string[]>([...dailyTaskOptions])
const weeklyTasks = ref<string[]>([...weeklyTaskOptions])
const selectedDailyTasks = ref<string[]>([])
const selectedWeeklyTasks = ref<string[]>([])
const dailySpinning = ref<boolean>(false)
const weeklySpinning = ref<boolean>(false)

const studyHours = ref([
  { subject: '國文', hours: '', minutes: '', description: '' },
  { subject: '英文', hours: '', minutes: '', description: '' },
  { subject: '數學', hours: '', minutes: '', description: '' },
  { subject: '自然', hours: '', minutes: '', description: '' },
  { subject: '社會', hours: '', minutes: '', description: '' }
])

const homeworkCompleted = ref(false)
const readingCompleted = ref(false)
const exerciseCompleted = ref(false)

function spinDailyWheel() {
  if (dailyTasks.value.length === 0 || dailySpinning.value) return
  dailySpinning.value = true
  setTimeout(() => {
    const index = Math.floor(Math.random() * dailyTasks.value.length)
    const selectedTask = dailyTasks.value[index]
    selectedDailyTasks.value.push(selectedTask)
    dailyTasks.value.splice(index, 1)
    dailySpinning.value = false
  }, 2000)
}

function spinWeeklyWheel() {
  if (weeklyTasks.value.length === 0 || weeklySpinning.value) return
  weeklySpinning.value = true
  setTimeout(() => {
    const index = Math.floor(Math.random() * weeklyTasks.value.length)
    weeklyTasks.value.splice(index, 1)
    const selectedTask = weeklyTasks.value[index]
    selectedWeeklyTasks.value.push(selectedTask)
    weeklySpinning.value = false
  }, 2000)
}

function saveStudyHours() {
  const formattedStudyHours = studyHours.value.map((entry) => ({
    ...entry,
    totalMinutes: Number(entry.hours) * 60 + Number(entry.minutes || 0) // 計算總分鐘數
  }))
  console.log('Study Hours:', formattedStudyHours)
}

function markHomeworkCompleted() {
  homeworkCompleted.value = true
}

function markReadingCompleted() {
  readingCompleted.value = true
}

function markExerciseCompleted() {
  exerciseCompleted.value = true
}

defineComponent({
  name: 'TaskView'
})
</script>
<style lang="scss">
.overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 999;
}

.spinner {
  border: 16px solid #f3f3f3; /* Light grey */
  border-top: 16px solid #3498db; /* Blue */
  border-radius: 50%;
  width: 120px;
  height: 120px;
  animation: spin 2s linear infinite;
}

@keyframes spin {
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(360deg);
  }
}
.animate-spin {
  animation: spin 2s linear infinite;
}

@keyframes spin {
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(360deg);
  }
}

.title {
  font-size: 2.5rem;
  // margin-bottom: 20px;
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

.task-container {
  margin-top: 30px;
}

.task-box {
  background-color: #f0f0f0;
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 20px;
  font-size: 1.2rem;
  margin-top: 10px;
}

.task-buttons {
  padding-top: 10px;
  display: flex;
  flex-wrap: wrap;
}

.task-button {
  background-color: #007bff;
  color: white;
  padding: 10px 20px;
  font-size: 1.2rem;
  border: none;
  border-radius: 5px;
  margin-right: 10px;
  margin-bottom: 10px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.task-button:hover {
  background-color: #0056b3;
}

.stamp-container {
  position: relative;
  width: 120px; /* 根據圖片大小調整 */
  height: 120px;
  margin-top: 10px;
}

.stamp {
  position: absolute;
  width: 100%;
  height: auto;
  animation: stamp-animation 0.7s ease-out forwards;
}

@keyframes stamp-animation {
  0% {
    transform: scale(3); /* 從大尺寸開始 */
    opacity: 0; /* 初始透明 */
  }
  50% {
    transform: scale(1.1); /* 短暫放大 */
    opacity: 1; /* 漸顯 */
  }
  100% {
    transform: scale(1); /* 回到正常大小 */
    opacity: 1; /* 完全顯示 */
  }
}

.stamp-fade-enter-active,
.stamp-fade-leave-active {
  transition: opacity 0.3s;
}

.stamp-fade-enter-from,
.stamp-fade-leave-to {
  opacity: 0;
}

.stamp-animation-enter-active {
  animation: stamp-animation 0.5s ease-out forwards;
}

.completed-animation {
  font-size: 1.2rem;
  color: #4caf50;
  font-weight: bold;
  animation: bounce 0.5s ease-in-out;
  text-align: center;
  margin-top: 10px;
}

@keyframes bounce {
  0% {
    transform: scale(0.9);
  }
  50% {
    transform: scale(1.2);
  }
  100% {
    transform: scale(1);
  }
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.3s;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}

.zoom-enter-active,
.zoom-leave-active {
  transition:
    transform 0.3s,
    opacity 0.3s;
}

.zoom-enter-from,
.zoom-leave-to {
  transform: scale(0.8);
  opacity: 0;
}

.task-table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 10px;
}

.task-table th,
.task-table td {
  border: 1px solid #ccc;
  padding: 8px;
  text-align: left;
}

.task-table th {
  background-color: #f4f4f4;
}
</style>
