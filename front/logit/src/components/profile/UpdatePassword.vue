<template>
   <!-- 프로필 페이지 → 비밀번호 변경 페이지 -->
  <div class="profile-container">
    <div class="profile-title">비밀번호 변경</div>
    <div class="profile-main">
      <div class="profile-main-form">
        <div class="profile-main-form-text">
          <v-form ref="form" v-model="valid" lazy-validation @keyup="chkPw">
            <div class="profile-main-form-text-email">
          <!-- 비밀번호 입력 창-->
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
import axiosConnectorFormData from "@/utils/axios-connector-formData";
import { useToast } from "vue-toastification";
const toast = useToast()

export default {
  name: "UpdatePassword",
  data: () => ({
    // 비밀번호 유효성 검사 규칙
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
    
  }),
  methods: {
    // '다음' 버튼
    //  비밀번호를 서버에 보내서 사용자 비밀번호 업데이드
    modifyPw() {
      // 비밀번호 입력했는지 검사
      if (!this.password.trim() || !this.password_tmp.trim()) {
        toast.error("비밀번호를 입력해 주세요.", {
          timeout: 2000,
          position: 'bottom-right'
        })
        return;
      }
      if (this.password != this.password_tmp) {
        toast.error("비밀번호가 일치하지 않습니다.", {
          timeout: 2000,
          position: 'bottom-right'
        });
        return;
      }
      // 비밀번호 변경 → 1. 비밀번호를 formData에 담는다
      const formData = new FormData();
      formData.append('pw', this.password_tmp);
      // 비밀번호 변경 → 2. formData를 서버에 보낸다
      axiosConnectorFormData.post("user/pw_change", formData
        ).then((res) => {
          //  console.log(res)
            if (res.data == "success") {
            toast.error("비밀번호가 변경되었습니다.", {
              timeout: 3000,
              position: 'bottom-right'
            })
            this.$router.push({ name: "ProfilePage" });
            }
        }).catch((err) => {
            // alert("비밀번호 변경이 실패하였습니다.")
            console.log(err)
            toast.error('다시한번 시도해주세요',{
              timeout: 2000,
              position: 'bottom-right'
            })
         })
    },
    // 비밀번호 유효성 검사
      async chkPw () {
        const validate = await this.$refs.form.validate();
        if (validate.valid) {
          if (this.password == this.password_tmp) {
            document
              .querySelector(".profile-main-button-user")
              .classList.add("color");
          }else{
            document
              .querySelector(".profile-main-button-user")
              .classList.remove("color");
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
    // console.log("여기까지왔숨다")
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
