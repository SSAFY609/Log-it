import { createStore } from "vuex";
import axios from "axios";
import router from "@/router";
import axiosConnector from "@/utils/axios-connector";
import axiosConnectorFormData from "@/utils/axios-connector-formData";

// import { useRouter } from "vue-router";
// import temp from './modules/temp.js'
// import event from './modules/event.js'
// import timeline from './modules/timeline.js'

// const getToken = () => {
//   const token = sessionStorage.getItem("token");
//   return {
//     Authorization: `bearer ${token}`,
//   };
// }; 

import tempJob from './modules/tempJob.js'
import job from './modules/job.js'
import temp from './modules/temp.js'
import growth from "./modules/growth.js";
import timeline from "./modules/timeline.js";
import search from "./modules/search.js";

const baseURL = "https://i8a609.p.ssafy.io/api/user";
// const baseURL = "http://localhost:9090/user";
export default createStore({

  state: {
    loginUser : {},
    sidebar: true,
  },
  getters: {},
  mutations: {
    LOGIN_USER(state, payload) {
      state.loginUser = payload
      sessionStorage.setItem("token", payload["jwt-auth-token"]);
      router.push({ name: "MainPage" });
    },
    LOG_OUT(state) {
      sessionStorage.removeItem("token");
      sessionStorage.removeItem("email");

      state.loginUser = {};
    },
    GET_USER(state, payload) {
      state.loginUser = payload;
      console.log(state.loginUser);
      router.push({ name: "ProfilePage" });
    },
    OPEN_SIDEBAR(state){
      state.sidebar = true;
    },
    CLOSE_SIDEBAR(state){
      state.sidebar = false;
    },
  },
  actions: {
    // 유저 로그인
    login({ commit }, user) {
      const URL = `${baseURL}/login`;
      console.log(user)
      axios({
        url: URL,
        method: "POST",
        data: user,
      })
        .then((res) => {
          if (res.data.result == "사용자 없음") { 
            alert("사용자가 없습니다.")
            return
          }          
          if (res.data.result == "비밀번호 틀림") { 
            alert("로그인에 실패하였습니다.")
            return
          }
          commit("LOGIN_USER", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 유저 회원가입하기
    signup({ dispatch }, user) {
      const URL = `${baseURL}/regist`;
      axios({
        url: URL,
        method: "POST",
        data: user,
      })
        .then(() => {
          dispatch("login", user);
          router.push({ name: "UserSignupComplete" });
        })
        .catch((err) => {
          console.log(err);
        });
    },

    // 유저 로그아웃하기
    logout({ commit }) {
      axiosConnector
        .post("user/logout")
        .then(() => {
          commit("LOG_OUT");
        })
        .catch((err) => {
          console.log(err);
        });
    },

    // 비밀번호 변경하기 => 고쳐야함
    // updataPassword.vue에 작성

    // 비밀번호 재발급
    sendPw(context, email) {
      const URL = `${baseURL}/sendPw`;
      axios({
        url: URL,
        method: "POST",
        params: { email: email },
      })
      .then(() => {
        console.log("성공");
        // commit("LOGIN_USER", res.data);
      })
      .catch((err) => {
        alert("으악!!!!!!! 로그인 실패");
        console.log(err);
      });
    },

    // 이메일 중복 검사하기
    // UserEmail.vue에 작성

    // 이미지 업로드하기
    // 이미지 정보 갱신 : 이미지 업로드 후(uploadImage) 유저 정보를 불러와(getUser) vuex에 저장
    uploadImage({ dispatch }, formData) {
      axiosConnectorFormData
        .post("user/uploadImage", formData)
        .then((res) => { 
          if (res.data == "success") {
            dispatch("getUser");
          }
        })
        .catch((err) => { 
          console.log(err);
        })
    },

    uploadFile({ dispatch}, formData) {
      axiosConnectorFormData
        .post("user/uploadFile", formData)
        .then((res) => { 
          if (res.data == "success") {
            dispatch("getUser");
          }
          console.log(res.data)
        })
        .catch((err) => { 
          console.log(err);
        })
    },
    
    // 유저 정보 업로드하기
    // 유저 정보 갱신 : 유저 정보 업데이트(updateUser) 유저 정보 불러와(getUser) vuex에 저장
    updateUser({ dispatch }, user) { 
      axiosConnector
        .post("user", user)
        .then((res) => {
          if (res.data.result == "사용자 없음") {
            return
          }       
            dispatch("getUser");
        })
        .catch((err) =>{ 
        alert("유저 정보 업데이트 실패해쓰어 ~~")
          console.log(err);
        })
    },

    // 유저 정보 가져오기
    getUser({ commit }) {
      axiosConnector
        .get("user")
        .then((res) => {
          commit("GET_USER", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    // sidebar 열기
    openSidebar({commit}){
      commit('OPEN_SIDEBAR');
    },
    // sidebar 닫기
    closeSidebar({commit}){
      commit('CLOSE_SIDEBAR');
    },

  },

  modules: {
    temp: temp,
    growth: growth,
    timeline: timeline,
    search: search,
    job: job,
    tempJob: tempJob,
  },
});
