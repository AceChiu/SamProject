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
defineComponent({
  name: 'TaskView'
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
</style>
