<template>
  <div class="profile-container">
    <div class="profile-title">비밀번호 변경</div>
    <div class="profile-main">
      <div class="profile-main-form">
        <div class="profile-main-form-text">
          <v-form disabled>
            <div class="profile-main-form-text-email">
              <div>계속하려면 먼저 본인임을 인증하세요.</div>
              <v-text-field
                v-model="state.email"
                density="comfortable"
              ></v-text-field>
            </div>
          </v-form>
          <div class="m-top-d">
            <div>비밀번호 입력</div>
            <v-text-field
              v-model="state.password"
              :append-inner-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
              :type="show1 ? 'text' : 'password'"
              name="input-10-1"
              label="Password"
              @click:append-inner="show1 = !show1"
            ></v-text-field>
          </div>
        </div>
        <div class="profile-main-button">
          <div @click="chkPw">
            <v-btn
              width="380"
              height="50"
              rounded="lg"
              color="#FF0A54 "
              class="profile-main-button-user"
              style="color: white; font-size: 15px; margin-top: 15px"
            >
              다음
            </v-btn>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { reactive, onMounted } from "@vue/runtime-core";

import { useStore } from "vuex";
import { useRouter } from "vue-router";
export default {
  name: "CheckPassword",
  setup() {
    const state = reactive({
      email: "",
      model: null,
      password: "",
      userPassword: "",
      show1: false,
      show2: true,
    });

    const store = useStore();
    const router = useRouter();

    const chkPw = () => {
      if (state.password == store.state.loginUser.pw) {
        router.push({ name: "UpdatePassword" });
      } else {
        alert("비밀번호를 다시 입력해주세요.");
      }
    };
    // 초기화면 세팅
    onMounted(() => {
      const loginUser = store.state.loginUser;
      state.userPassword = loginUser.pw;
      state.email = loginUser.email;
    });
    return {
      state,
      chkPw,
    };
  },
};
</script>

<style scoped>
.img_box {
  width: 200px;
  margin: 100px;
  background-color: red;
}
.hover_bigger {
  height: 110px;
  width: 110px;
}
.hover_bigger:hover {
  border-radius: 50%;
  height: 114px;
  width: 114px;
}
.m-top-d {
  margin-top: 20px;
}

.profile-main-button {
  margin-top: 25px;
  justify-content: space-between;
  width: 270px;
  height: 30px;
  display: flex;
}

.profile-main-form-text {
  font-family: appleM;
  font-size: 14px;
}
.profile-main-form {
  width: 380px;
  height: 290px;
}
.profile-title {
  font-family: appleB;
  font-size: 45px;
  text-align: center;
  width: 300px;
  height: 80px;
}
.profile-main {
  margin-top: 50px;
  align-items: center;
  display: flex;
  justify-content: space-evenly;
  width: 640px;
  height: 300px;
}

.profile-container {
  font-family: appleB;
  margin-top: 95px;
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>
