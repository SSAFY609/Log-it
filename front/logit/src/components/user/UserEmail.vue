<template>
  <div class="signup-container">
    <h2 class="signup-title">계정 생성</h2>
    <div class="signup-email fill-height">
      <v-form ref="form" v-model="valid" lazy-validation @keyup="chkEmail">
        <div class="fs" style="margin-bottom: 5px">&nbsp;&nbsp;이메일을 입력해주세요.</div>
        <v-text-field
          v-model="email_tmp"
          :rules="rules2"
          label="E-mail"
          class="mb-2"
          required
        >
        </v-text-field>
        <span class="signup-email-chkText"></span>
        <div @click="signupEmail" class="signup-button b_lightgray_l">
          <div>다음</div>
        </div>
        <div class="signup-link">
          <span>이미 회원이신가요?</span>
          <router-link :to="{ name: 'UserLogin' }">
            <span class="signup-link-Login"> 로그인 바로가기</span>
          </router-link>
        </div>
      </v-form> 
    </div>
  </div>
</template>

<script>
export default {
  name: "UserEmail",
  data: () => ({
    rules2: [
      (value) => !!value || "",
      (value) => (value || "").length <= 20 || "Max 20 characters",
      (value) => {
        const pattern =
          /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return pattern.test(value) || "유효하지 않은 이메일 형식입니다.";
      },
    ],
    email_tmp: "",
    email: "",
    email_help: "",
  }),

  methods: {
    // 계정생성 - 이메일
    signupEmail() {
      if (!this.email_tmp.trim()) {
        alert("입력된 이메일 주소가 없습니다.");
        return;
      }
      if (
        !document.querySelector(".signup-button").classList.contains("color")
      ) {
        alert("이메일 주소를 확인해주세요.");
        return;
      }
      this.$router.push("password");
    },

    // 이메일 검사 - 유효성 검사, 중복 검사,
    async chkEmail() {
      const validate = await this.$refs.form.validate();
      // 이메일 유효성 검사 완료하면
      if (validate.valid) {
        // 이메일 중복 검사
        // 중복될 경우 -> "이미 사용중인 이메일입니다" + 버튼 비활성화
        // 중복이 아닐 경우 -> "사용할 수 있는 이메일입니다." + 버튼 활성화
        var params = new URLSearchParams();
        params.append("email", this.email_tmp);
        //http.post("URL,params")
        //.then((res=>{
        //if(res.data.response==" 실패"){
        // this.email_help='이미 사용중인 이메일입니다.'
        // document.querySelector(".signup-button").classList.remove("color");
        // return;
        //}
        // this.email = this.email_tmp
        // this.email_help = '${this.email}는 사용 가능한 이메일입니다.'
        //}))
        this.email_help = "asd@asd.com은 사용 가능한 이메일입니다.";
        document.querySelector(".signup-email-chkText").innerHTML =
          this.email_help;
        //  document.querySelector(".signup-email-chkText").add("animation");
        document.querySelector(".signup-button").classList.add("color");
      } else {
        document.querySelector(".signup-email-chkText").innerHTML = "";
        document.querySelector(".signup-button").classList.remove("color");
        //  document.querySelector(".signup-email-chkText").remove("animation");
      }
    },
  },
};
</script>

<style>

.none {
  display: none;
}

.signup-email-chkText {
  position: absolute;
  top: 219px;
  left: 77px;
  font-size: 12.9px;
}
.animation {
  animation: fadeInText 0.2s 0.2s ease-out forwards;
}

@keyframes fadeInText {
  100% {
    opacity: 1;
  }
}

.signup-link-Login {
  color: #ff0a54;
  text-decoration: underline;
}
.signup-button:hover {
  background-color: #ff0a54;
  cursor: pointer;
}
.signup-button:active {
  background-color: #c0003a;
}
.signup-link {
  margin-top: 55px;
  text-align: center;
}
.color {
  background-color: #ff0a54;
}
.signup-container {
  position: relative;
  margin-top: 12px;
}
.signup-title {
  padding: 25px;
}
.signup-button {
  display: flex;
  justify-content: center;
  align-items: center;
  font-weight: bold;
  font-size: 18.5px;
  color: white;
  width: 380px;
  height: 60px;
  border-radius: 10px;
}
.signup-container {
  display: flex;
  width: 100%;
  flex-direction: column;
  padding: 10px 30px;
  justify-content: center;
  align-items: center;
}

.signup-form {
  width: 90%;
}

.signup-title {
  font-size: 51px;
  font-family: AppleB;
  margin-top: -13px;
  margin-bottom: 5px;
}
</style>
