<template>
  <div class="signup-container">
    <h2 class="signup-title">계정 생성</h2>
    <div class="signup-email fill-height">
      <v-form ref="form" v-model="valid" lazy-validation @keyup="chkEmail">
        <div class="fs" style="margin-bottom: 5px">
          &nbsp;&nbsp;이메일을 입력해주세요.
        </div>
        <v-text-field
          v-model="email"
          :rules="rules2"
          label="E-mail"
          class="mb-2"
          required
        >
        </v-text-field>
        <span class="signup-email-chkText"></span>
        <div @click="signupEmail" class="signup-button">
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
import axios from 'axios';
import { useToast } from 'vue-toastification';
const toast = useToast()

export default {
  name: "UserEmail",
  data: () => ({
    rules2: [
      (value) => !!value || "",
      (value) => (value || "").length <= 30 || "최대 30자를 초과하였습니다.",
      (value) => {
        const pattern =
          /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return pattern.test(value) || "유효하지 않은 이메일 형식입니다.";
      },
    ],
    email: "",
    email_help: "",
  }),

  methods: {
    // 계정생성 - 이메일
    signupEmail() {
      if (!this.email.trim()) {
        toast.error("입력된 이메일 주소가 없습니다.",{
          position: 'bottom-right',
          timeout: 2000
        });
        return;
      }
      if (
        !document.querySelector(".signup-button").classList.contains("color")
      ) {
        toast.error("사용할 수 없는 이메일입니다.",{
          position: 'bottom-right',
          timeout: 2000
        });
        return;
      }
      this.$emit("updateUserEmail", this.email);
      this.$router.push({ name: "UserPassword" });
    },

    // 이메일 검사 - 유효성 검사, 중복 검사
    async chkEmail() {
      const validate = await this.$refs.form.validate();
      // 이메일 유효성 검사
      if (validate.valid) {
        // 이메일 중복 검사
        console.log(this.email)
        axios({
            url: "https://i8a609.p.ssafy.io/api/user/check",
            method: "GET",
            params: {
              email: this.email
            }
          }).then(()=>{
            this.email_help = `${this.email}은 사용 가능한 이메일입니다.`;
            document.querySelector(".signup-email-chkText").innerHTML =this.email_help;
            document.querySelector(".signup-button").classList.add("color");
          }).catch(()=>{
            this.email_help = `${this.email}은 사용할 수 없는 이메일입니다.`;
            document.querySelector(".signup-email-chkText").innerHTML =this.email_help;
            document.querySelector(".signup-button").classList.remove("color");
          })
        } else {
        document.querySelector(".signup-email-chkText").innerHTML = "";
        document.querySelector(".signup-button").classList.remove("color");
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
  margin-top: 30px;
  justify-content: center;
  align-items: center;
  font-weight: bold;
  font-size: 18.5px;
  color: white;
  width: 380px;
  height: 60px;
  border-radius: 10px;
  background: #EDEDED;;
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
