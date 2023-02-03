import { createStore } from 'vuex'
import axios from "axios";

const baseURL = "http://localhost:9090/user"

export default createStore({
  state: {
  },
  getters: {
  },
  mutations: {
    
  },
  actions: {
    login({commit}, user){
      const URL = `${baseURL}/login`
      axios({
        url: URL,
        method: 'POST',
        params: user
      }).then((res)=>{
        console.log(res);
        commit('LOGIN_USER', res);
      }).catch((err)=>{
        alert("으악!!!!!!! 로그인 실패");
        console.log(err);
      })
    }
  },
  modules: {
  }
})
