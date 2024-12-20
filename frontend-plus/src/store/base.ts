import { defineStore } from 'pinia'
import user from '../util/interface/user'

export const base = defineStore('base', {
  state: () => {
    return {
      loading: false,
      login: false,
      language: 'tw',
      user: {} as user,
      menuOpen: false,
      redirectUrl: '',
      token: ''
    }
  },
  getters: {
    getLoading: (state) => state.loading,
    getLogin: (state) => state.login,
    getLanguage: (state) => state.language,
    getUser: (state) => state.user,
    getRedirectUrl: (state) => state.redirectUrl,
    getToken: (state) => state.token
  },
  actions: {
    setLoading(value: boolean) {
      this.loading = value
    },
    setLogin(value: boolean) {
      this.login = value
    },
    setLanguage(value: string) {
      this.language = value
    },
    setUser(value: user) {
      this.user = value
    },
    setRedirectUrl(value: string) {
      this.redirectUrl = value
    },
    setToken(value: string) {
      this.token = value
    }
  }
})
