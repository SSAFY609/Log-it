<template>
  <div class="user-container b_lightgray_l">
    <div class="user-box">
      <div class="logo_box lay3" style="padding-top: 30px">
        <v-img
          class="logo_img"
          :src="require('../assets/images/logit_logo_text.png')"
          height="50"
        >
        </v-img>
      </div>
      <router-view
        @updateUserProfile="updateUserProfile"
        @updateUserPhoto="updateUserPhoto"
        @updateUserEmail="updateUserEmail"
        @updateUserPassword="updateUserPassword"
        @updateUserData="updateUserData"
        :user="user"
       
      ></router-view>
    </div>
  </div>
</template>

<script>
import { reactive } from "vue";
export default {
  name: "UserView",
  setup() {
    const state = reactive({
    });
    const user = reactive({
      email: "이메일 ",
      password: "암호 ",
      profile: "1",
      uName: "김나현(●'◡'●)",
      sfNum: "학번 ",
    });

    const updateUserEmail = (email) => {
      user.email = email;
    };
    const updateUserPhoto = (i) => {
      user.profile = i;
    };
    const updateUserPassword = (password) => {
      user.password = password;
    };
    const updateUserData = (userName, ssafyNum) => {
      user.uName = userName;
      user.sfNum = ssafyNum;
    };
    const updateUserProfile = (id) => {
      user.profile = id;
    };
    return {
      state,
      user,
      updateUserPhoto,
      updateUserProfile,
      updateUserEmail,
      updateUserPassword,
      updateUserData,
    };
  },
};
</script>

<style scoped>
.user-container {
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: EDEDED;
}
.user-box {
  margin-top: 50px;
  background-color: white;
  border-radius: 20px;
  width: 470px;
  height: 600px;
}
</style>
