import {
  createRouter,
  createWebHashHistory,
  RouterOptions,
  Router,
  RouteRecordRaw
} from 'vue-router'
import Cookies from 'js-cookie'
import pinia from '../store/store'
import { base } from '../store/base'
import { getCurrentUser } from '../util/api/user-profile'
import { ElMessage } from 'element-plus'
import scrollToTop from '~/util/composables/basic'
// import { useRouter, useRoute } from 'vue-router'

const baseI = base(pinia)
// const $router = useRouter()
// const $route = useRoute()
const routes: RouteRecordRaw[] = [
  { path: '/', redirect: '/home' },
  { path: '/home', name: 'home', component: () => import('../views/Home.vue') },
  {
    path: '/user-profile',
    name: 'user-profile',
    component: () => import('../views/UserProfile.vue')
  },
  {
    path: '/task-view',
    name: 'task-view',
    component: () => import('../views/TaskView.vue')
  },
  {
    path: '/course-required',
    name: 'course-required',
    component: () => import('../views/CourseRequired.vue')
  },
  {
    path: '/course-elective',
    name: 'course-elective',
    component: () => import('../views/CourseElective.vue')
  },
  {
    path: '/system-task',
    name: 'system-task',
    component: () => import('../views/SystemTask.vue')
  },
  {
    path: '/system-course',
    name: 'system-course',
    component: () => import('../views/SystemCourse.vue')
  },
  {
    path: '/about-us',
    name: 'about-us',
    component: () => import('../views/AboutUs.vue')
  },
  { path: '/:pathMatch(.*)*', name: 'Error', component: () => import('../views/ErrorPage.vue') }
]

const options: RouterOptions = {
  history: createWebHashHistory(),
  routes,
  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition
    } else if (to.hash) {
      return {
        el: to.hash
      }
    } else {
      scrollToTop()
    }
  }
}

const router: Router = createRouter(options)

router.beforeEach((to, from, next) => {
  if (Cookies.get(import.meta.env.VITE_APP_AUTH_TOKEN_NAME)) {
    if (Object.keys(baseI.getUser)?.length === 0) {
      getCurrentUser()
        .then((response: any) => {
          baseI.setUser(response.data)
        })
        .catch((error: any) => {
          Cookies.remove(import.meta.env.VITE_APP_AUTH_TOKEN_NAME)
          ElMessage.error({
            message: error,
            duration: 5000
          })
        })
        .finally(() => {
          baseI.loading = false
        })
    }
    next()
  } else {
    next()
  }
})
router.afterEach(() => {
  baseI.loading = false
})
export default router
