<template>
  <div class="data-container">
    <h2 class="data-title">프로필 생성</h2>
    <div class="data-input fill-height">
      <v-form ref="form" v-model="valid" lazy-validation @keyup="chkPw">
        <div style="margin-bottom: 5px">
          &nbsp;&nbsp;사용자 이름을 입력해주세요.
        </div>
        <v-text-field
          v-model="userName"
          name="input-10-1"
          label="예) 홍길동"
          counter
        ></v-text-field>
        <div style="margin-bottom: 5px">
          &nbsp;&nbsp;SSAFY 학번을 입력해주세요.
        </div>
        <v-text-field
          v-model="ssafyNum"
          name="input-10-1"
          :rules="[rules1.required, rules1.min]"
          label="예) 080000"
          counter
        ></v-text-field>
        <div @click="signup" class="data-button b_lightgray_l">
          <div>게정 생성</div>
        </div>
        <div class="data-link">
          <span class="data-link-Email"> 뒤로가기</span>
        </div>
      </v-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "UserData",
  data: () => ({
    rules1: {
      required: (value) => !!value || "",
      min: (v) => v.length >= 6 || "학번을 정확히 입력해주세요..",
    },
    userName: "",
    ssafyNum: "",
  }),
  methods: {
    signup() {
      this.$router.push("signup");
    },
    async chkPw() {
      const validate = await this.$refs.form.validate();
      if (validate.valid) {
        document.querySelector(".data-button").classList.add("color");
      } else {
        document.querySelector(".data-button").classList.remove("color");
      }
    },
  },
};
</script>

<style>
.color {
  background-color: #ff0a54;
}
.data-input {
  margin-top: -10px;
}
.data-link-Email {
  color: #9d9d9d;
}
.none {
  display: none;
}

.data-chkText {
  position: absolute;
  top: 219px;
  left: 77px;
  font-size: 12.9px;
}

Email {
  color: #ff0a54;
  text-decoration: underline;
}
.data-button:hover {
  background-color: #ff0a54;
  cursor: pointer;
}
.data-button:active {
  background-color: #c0003a;
}
.data-link {
  margin-top: 45px;
  text-align: center;
}

.data-container {
  position: relative;
  margin-top: 12px;
}
.data-title {
  padding: 25px;
}
.data-button {
  display: flex;
  justify-content: center;
  align-items: center;
  font-weight: bold;
  font-size: 18.5px;
  color: white;
  width: 380px;
  height: 60px;
  border-radius: 10px;
  margin-top: 8px;
}
.data-container {
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

.data-title {
  font-size: 51px;
  font-family: AppleB;
  margin-top: -13px;
  margin-bottom: 5px;
}
</style>
