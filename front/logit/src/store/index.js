import { createStore } from "vuex";
import router from "@/router";
import axios from "axios";

const baseURL = "http://localhost:9090/user";
const getToken = () => {
  const token = sessionStorage.getItem("token");
  return {
    Authorization: `bearer ${token}`,
  };
};
export default createStore({
  state: {
    loginUser: {},
  },
  getters: {},
  mutations: {
    LOGIN_USER(state, payload) {
      state.loginUser = payload;
      console.log(state.loginUser);
      sessionStorage.setItem("token", payload["jwt-auth-token"]);
      router.push({ name: "MainPage" });
    },
    LOG_OUT(state) {
      sessionStorage.removeItem("token");
      state.loginUser = {};
    },
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
    logout({ commit }) {
      const URL = `${baseURL}/logout`;
      axios({
        url: URL,
        method: "POST",
        headers: getToken(),
      })
        .then(() => {
          console.log("성공했어 ~크크큭 😋");
          commit("LOG_OUT");
        })
        .catch((err) => {
          alert("으악!!!!!!! 로그인 실패");
          console.log(err);
        });
    },
  },
  modules: {},
});
