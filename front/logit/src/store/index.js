import { createStore } from 'vuex'
// import axios from 'axios'
// import router from '@/router'

import temp from './modules/temp.js'
import event from './modules/event.js'
import timeline from './modules/timeline.js'

export default createStore({
  state: {
  },
  getters: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
    temp: temp,
    event: event,
    timeline: timeline,
  }
})
