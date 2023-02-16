<template>
 <!-- 프로필 페이지 → 비밀번호 인증 페이지 -->
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
          <div >
            <v-btn
            @click="checkPw"
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
import axiosConnectorFormData from "@/utils/axios-connector-formData";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import { useToast } from "vue-toastification";
const toast = useToast()


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

    // '다음' 버튼
    //  서버에 비밀번호를 보내서 이메일과 맞는 비밀번호인지 확인
    const checkPw = () => {
      // 비밀번호 확인 → 1. 비밀번호 formData에 담기
      const formData = new FormData();
      formData.append('pw', state.password);
      // 비밀번호 확인 → 2. formData 서버에 보내서 비밀번호 확인
      axiosConnectorFormData.post("user/pw_confirm", formData)
        .then((res) => {
          console.log(res)
          if (res.data == "success") {
             router.push({ name: "UpdatePassword" });  
           }
          if (res.data == "비밀번호 틀림") { 
              toast.error("비밀번호를 다시 입력해주세요.",{
                timeout: 2000,
                position: 'bottom-right'
              })
           }
           }).catch((err) => {
              toast.error("비밀번호를 다시 입력해주세요.",{
                timeout: 2000,
                position: 'bottom-right'
              })
              console.log(err);
           })
    }
     
    // 초기화면 세팅
    onMounted(() => {
      const loginUser = store.state.loginUser;
      state.email = loginUser.email;
    });
    return {
      checkPw,
      state,
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
