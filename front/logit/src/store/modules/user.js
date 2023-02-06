// import axios from "axios";
// import router from "@/router";
import axiosConnector from "@/utils/axios-connector";

const user = {
  namespaced: true,
  state: {},
  getters: {},
  mutations: {},
  actions: {
    uploadImage(context, user) {
      axiosConnector
        .get("user", user.email)
        .then((res) => {
          const data = {
            name: user.name,
            email: user.email,
            studentNo: user.studentNo,
            image: user.iamge,
            pw: res.data.pw,
            flag: res.data.flag,
            isDeleted: res.data.isDeleted,
          };
          axiosConnector
            .post("user/uploadImage", data)
            .then(() => {
              alert("사용자 정보 변경 성공쓰😝");
            })
            .catch((err) => {
              console.log(err);
              alert("사용자 정보 변경 실패 ~!!!!🤣");
            });
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },

  modules: {},
};

export default user;
