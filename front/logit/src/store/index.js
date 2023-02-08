import { createStore } from "vuex";
import axios from "axios";
import router from "@/router";
import axiosConnector from "@/utils/axios-connector";
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
    // 로그인했을 때 주는 데이터 받으려면 키를 만들어놔야 되나..?
    loginUser: {
      email: "sky@naver.com",
      name: "오하늘",
      image: "3",
      studentNo: "0845123",
      pw: "1234",
      flag: "",
      isDeleted: "",
    },
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
    GET_USER(state, payload) {
      state.loginUser = payload;
      router.push({ name: "ProfilePage" });
    },
  },
  actions: {
    // 유저 로그인
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
    // 유저 회원가입하기
    signup(context, user) {
      axiosConnector
        .post("user/regist", user)
        .then(() => {
          alert("회원가입 성공했어 ~ ^^ ");
          router.push({ name: "UserSignupComplete" });
        })
        .catch((err) => {
          alert("회원가입 실패했어 ~!!!");
          console.log(err);
        });
    },
    // 유저 로그아웃하기
    logout({ commit }) {
      // const URL = `${baseURL}/logout`;
      axiosConnector
        .post("user/logout")
        .then(() => {
          console.log("성공했어 ~크크큭 😋");
          commit("LOG_OUT");
        })
        .catch((err) => {
          alert("으악!!!!!!! 로그아웃 실패");
          console.log(err);
        });
    },
    // 비밀번호 변경하기
    updatePassword({ dispatch }, user) {
      axiosConnector
        .post("user", user)
        .then(() => {
          alert("정보 변경 성공했다구 ~~");
          dispatch("getUser");
        })
        .catch((err) => {
          alert("비.밀.번.호.변.경.실.패");
          console.log(err);
        });
    },
    // 유저 정보 변경하기
    updateUser({ dispatch }, user) {
      axiosConnector
        .post("user", user)
        .then(() => {
          alert("정보 변경 성공헀어 키야~~~ 😂");
          dispatch("getUser");
        })
        .catch((err) => {
          alert("정.보.변.경.실.패");
          console.log(err);
        });
    },
    // 비밀번호 재발급
    sendPw({dispatch}){
      axiosConnector
      .post("user/sendPw")
      .then(()=>{
        alert("비밀번호 재발급은 했어 ~");
        dispatch("getUser")
      })
      .catch((err)=>{
        alert("비밀번호 재발급 실패 ...... 🤣");
        console.log(err);
      })
    },
    // 이메일 중복 검사하기
    // UserEmail.vue에 작성
    // axiosConnector.get("user/check", {
    //   params: {
    //     email:this.email
    //   }
    // })
    //   .then((res)=>{
    //     console.log(res)
    //     alert("헉!! 이메일 중복검사 성공~!!")
    //     this.email_help = `${this.email}은 사용 가능한 이메일입니다.`;
    //   }).catch((err)=>{
    //     console.log(err);
    //     this.email_help = `${this.email}은 사용할 수 없는 이메일입니다.`;
    //   })
   
    // 유저 정보 가져오기
    getUser({ commit }) {
      axiosConnector
        .get("user")
        .then((res) => {
          commit("GET_USER", res.data);
        })
        .catch((err) => {
          alert("여기서 실패 헀스니다.");
          console.log(err);
        });
    },
  },

  modules: {
    // temp: temp,
    // event: event,
    // timeline: timeline,
  },
});
