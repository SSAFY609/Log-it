<template>
  <div class="profile-container">
    <div class="profile-title">비밀번호 변경</div>
    <div class="profile-main">
      <div class="profile-main-form">
        <div class="profile-main-form-text">
          <v-form ref="form" v-model="valid" lazy-validation @keyup="chkPw">
            <div class="profile-main-form-text-email">
              <div>새 비밀번호를 입력해주세요.</div>
              <v-text-field
                v-model="password"
                :append-inner-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
                :rules="[rules1.required, rules1.min]"
                :type="show1 ? 'text' : 'password'"
                name="input-10-1"
                label="Password"
                density="comfortable"
                counter
                @click:append-inner="show1 = !show1"
              ></v-text-field>
            </div>
            <div class="m-top-d">
              <div>새 비밀번호를 다시 입력해주세요.</div>
              <v-text-field
                v-model="password_tmp"
                :append-inner-icon="show2 ? 'mdi-eye' : 'mdi-eye-off'"
                :type="show2 ? 'text' : 'password'"
                name="input-10-1"
                label="Password"
                counter
                @click:append-inner="show2 = !show2"
              ></v-text-field>
            </div>
          </v-form>
        </div>
        <div class="profile-main-button">
          <div>
            <v-btn
              width="380"
              height="50"
              rounded="lg"
              class="profile-main-button-user"
              style="font-size: 15px; margin-top: 15px"
              @click="modifyPw"
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
// import { reactive } from "@vue/reactivity";
// import { ref } from "vue";
export default {
  name: "UpdatePassword",
  data:() => ({
      rules1: {
        required: (value) => !!value || "",
        min: (v) => v.length >= 8 || "최소 8자리 이상 입력해주세요.",
      },
      model: null,
      password: "",
      show1: false,
      show2: false,
      password_tmp: "",
      pw: "",
      email: "",
      name: "",
      flag: "",
      studentNo: "",
      isDeleted: "",
      image: "",
    
  }),
    // 다음 버튼 클릭
  methods: {
      modifyPw ()  {
        if (!this.password.trim() || !this.password_tmp.trim()) {
          alert("입력한 비밀번호가 없습니다.");
          return;
        }
        if (
          !document
            .querySelector(".profile-main-button-user")
            .classList.contains("color")
        ) {
          alert("입력한 비밀번호가 일치하지 않습니다.");
          return;
        }
        const user = {
          pw: this.password_tmp,
          email: this.email,
          flag: this.flag,
          studentNo: this.studentNo,
          isDeleted: this.isDeleted,
          image: this.image,
        };
        this.$store.dispatch("updateUser", user);
        this.$router.push({ name: "ProfilePage" });
      },
      async chkPw () {
        const validate = await this.$refs.form.validate();
        if (validate.valid) {
          if (this.password == this.password_tmp) {
            document
              .querySelector(".profile-main-button-user")
              .classList.add("color");
          }
        } else {
          document
            .querySelector(".profile-main-button-user")
            .classList.remove("color");
        }
      }
  },
  mounted() {
    const loginUser = this.$store.state.loginUser;
    this.email = loginUser.email;
    this.name = loginUser.name;
    this.flag = loginUser.flag;
    this.studentNo = loginUser.studentNo;
    this.isDeleted = loginUser.isDeleted;
    this.image = loginUser.image;
    console.log("여기까지왔숨다")
  },
}


</script>

<style scoped>
.profile-main-button-user {
  background-color: #ededed;
  color: white;
}
.color {
  background-color: #ff0a54 !important;
}
.img_box {
  width: 200px;
  margin: 100px;
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
