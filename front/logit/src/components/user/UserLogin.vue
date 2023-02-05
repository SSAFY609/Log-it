<template>
  <div class="login-container lay1">
    <h2 class="login-title lay2">로그인</h2>
    <div class="login-form lay3">
      <div class="login-form-input">
        <v-form ref="form" lazy-validation @keyup="loginCheck">
          <!-- 이메일 입력 칸-->
          <v-text-field
            v-model="user.email"
            :rules="rules2"
            class="mb-2"
            counter
            label="Email"
          ></v-text-field>
          <!-- 비밀번호 입력 칸-->
          <v-text-field
            v-model="user.pw"
            :append-inner-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
            :rules="[rules1.required, rules1.min]"
            :type="show1 ? 'text' : 'password'"
            name="input-10-1"
            label="Password"
            counter
            @click:append-inner="show1 = !show1"
          ></v-text-field>
        </v-form>
      </div>
      <!-- 소셜 로그인 버튼 칸-->
      <div class="login-form-OauthBtn">
        <div class="logo-img-kakao">
          <v-img
            class="logo-img kakao-logo-img"
            :src="require('../../assets/images/kakao_logo.png')"
            height="22px"
          />
          <span class="logo-text">카카오 로그인</span>
        </div>
        <div class="logo-img-google">
          <v-img
            class="logo-img google-logo-img"
            :src="require('../../assets/images/google_logo.png')"
            height="22px"
          />
          <span class="logo-text google-logo-text">구글 로그인</span>
        </div>
      </div>
      <div class="login-form-input-button">
        <div @click="login" class="login-form-input-button-btn b_lightgray_l">
          <v-icon class="login-btn menu_icon f_darkgray"
            >mdi-arrow-right</v-icon
          >
        </div>
      </div>
      <div class="login-form-link">
        <router-link class="text-color" :to="{ name: 'UserFindPassword' }">
          <span>비밀번호를 잊으셨습니까?</span>
        </router-link>

        <div class="login-form-link-signup">
          <span
            >Log-it이 처음인가요?
            <router-link :to="{ name: 'UserEmail' }">
              <span class="login-form-link-signup-link">회원가입 바로가기</span>
            </router-link>
          </span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "UserLogin",

  data: () => ({
    rules2: [
      (value) => !!value || "",
      (value) => (value || "").length <= 1 || "Max 20 characters",
      (value) => {
        const pattern =
          /^(([^<script>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return pattern.test(value) || "유효하지 않은 이메일 형식입니다.";
      },
    ],
    rules1: {
      required: (value) => !!value || "",
      min: (v) => v.length >= 1 || "최소 8자리 이상 입력해주세요.",
    },
    user: {
      email: "",
      pw: "",
    },
    show1: false,
    show2: true,
  }),

  methods: {
    async loginCheck() {
      const validate = await this.$refs.form.validate();
      if (validate.valid) {
        document
          .querySelector(".login-form-input-button-btn")
          .classList.add("color");
      } else {
        document
          .querySelector(".login-form-input-button-btn")
          .classList.remove("color");
      }
    },
    login() {
      const user = {
        email: this.email,
        pw: this.pw,
      };
      this.$store.dispatch("login", user);
    },
    kakaoLogin() {},
    googleLogin() {},
    toSignup() {
      this.$router.push("email");
    },
  },
};
</script>
<style scoped>
.text-color {
  color: #393939;
}
.login-form-link-signup {
  margin-top: 9px;
}
.login-form-link-signup-link {
  color: #ff0a54;
  text-decoration-line: underline;
}

.color {
  background-color: #ff0a54;
}

.google-logo-img {
  left: -13px;
}
.kakao-logo-img {
  left: -5px;
}
.login-form-link {
  font-family: appleL;
  margin-top: 13px;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.login-form-link:hover {
  cursor: pointer;
}
.login-container {
  font-size: 15px;
  font-family: appleM;
  display: flex;
  width: 100%;
  flex-direction: column;
  padding: 10px 30px;
  justify-content: center;
  align-items: center;
}
.login-title {
  font-size: 45px;
  font-family: AppleB;
  margin-top: -13px;
  margin-bottom: 5px;
}
.login-form {
  width: 100%;
  padding: 0px 30px;
}
.login-form-OauthBtn {
  display: flex;
  justify-content: space-between;
}

.logo-img-kakao {
  justify-content: center;
  align-items: center;
  text-align: center;
  display: flex;
  border-radius: 6px;
  width: 167px;
  height: 45px;
  margin: 10px 0px;
}
.logo-img-google {
  justify-content: center;
  align-items: center;
  display: flex;
  border-radius: 6px;
  width: 167px;
  height: 45px;
  margin: 10px 0px;
}
.logo-text {
  bottom: -2px;
  right: 18px;
  position: relative;
}
.logo_img:hover {
  cursor: pointer;
}

.login-form-input-button {
  display: flex;
  justify-content: center;
}
.login-form-input-button-btn {
  border-radius: 15px;
  margin-top: 15px;
  margin-bottom: 15px;
  width: 85px;
  height: 85px;
  display: flex;
  justify-content: center;
  align-items: center;
  transition: background-color 0.2s;
  cursor: pointer;
}
.login-form-input-button-btn:hover {
  background-color: #ff0a54;
  cursor: pointer;
}
.login-form-input-button-btn:active {
  background-color: #c0003a;
}
.login-btn {
  cursor: pointer;
  display: inline-block;
  position: relative;
  font-size: 50px !important;
  color: white;
}
.login-form-OauthBtn:hover {
  cursor: pointer;
}
.logo-img-kakao {
  background-color: #fee500;
  justify-content: center;
}
.logo-img-google {
  background-color: white;
  border: 1px solid rgb(207, 207, 207);
}
.logo-text {
  font-size: 17px;
  color: #191919;
}
.google-logo-img {
  left: -10px;
}
.google-logo-text {
  right: 27px;
}
</style>
