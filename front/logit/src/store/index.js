
import { createStore } from 'vuex'
import axios from 'axios'
import router from '@/router'
import axiosConnector from '@/utils/axios-connector';
// import temp from './modules/temp.js'
// import event from './modules/event.js'
// import timeline from './modules/timeline.js'

const baseURL = "http://localhost:9090/user";
// const getToken = () => {
//   const token = sessionStorage.getItem("token");
//   return {
//     Authorization: `bearer ${token}`,
//   };
// };
export default createStore({
  state: {
    loginUser: {email:'sky@naver.com', name: '오하늘', profile:'3', studentNo: '0845123'},
  },
  getters: {},
  mutations: {
    LOGIN_USER(state, payload) {
      state.loginUser = payload;
      console.log(state.loginUser);
      sessionStorage.setItem("token", payload["jwt-auth-token"]);
      sessionStorage.setItem("email", payload["user"]);
      router.push({ name: "MainPage" });
    },
    LOG_OUT(state) {
      sessionStorage.removeItem("token");
      state.loginUser = {};
    },
    GET_USER (state,payload){
      state.loginUser = payload;
      router.push({ name: "ProfilePage" });
    }
    
  },
  actions: {
    login({ commit }, user) {
      const URL = `${baseURL}/login`;
      axios({
        url: URL,
        method: "POST",
        data: user,
      })
        .then((res) => {
          console.log(res);
          commit("LOGIN_USER", res.data);
        })
        .catch((err) => {
          alert("으악!!!!!!! 로그인 실패");
          console.log(err);
        });
    },
    signup(context, user) {
      axiosConnector.post('user/regist', user
      ).then(() => { 
        alert("회원가입 성공했어 ~ ^^ ");
        router.push({ name: "UserSignupComplete" });
      }).catch((err) => {
        alert("회원가입 실패했어 ~!!!");
        console.log(err)
      })
    },
    logout({ commit }) {
      // const URL = `${baseURL}/logout`;
      axiosConnector.post('user/logout'
      ).then(() => { 
        console.log("성공했어 ~크크큭 😋");
        commit("LOG_OUT");
      }).catch((err) => { 
        alert("으악!!!!!!! 로그아웃 실패");
        console.log(err);
      })
    },
    updateUser({dispatch}, user) {
      axiosConnector
        .post('user', user)
        .then(() => {
          alert("정보 변경 성공헀어 키야~~~ 😂");
          dispatch('getUser')
        })
        .catch((err) => {
          alert("정.보.변.경.실.패");
          console.log(err);
        });
    },
    getUser({ commit }) {
      axiosConnector.get('user'
      ).then((res) => { 
        commit("GET_USER",res.data);
      }).catch((err) => { 
        alert("여기서 실패 헀스니다.")
        console.log(err);
      })
    }
  },

  modules: {
    // temp: temp,
    // event: event,
    // timeline: timeline,
  }
})
