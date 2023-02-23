<template>
  <!-- 프로필 페이지 -->
  <div class="profile-container">
    <div class="profile-title">프로필</div>
    <div class="profile-main">
      <div class="profile-main-form">
        <v-form disabled>
          <div class="profile-main-form-text">
            <!-- 프로필 정보 -->
            <div class="profile-main-form-text-email">
              <div>이메일</div>
              <v-text-field v-model="state.email" density="compact"></v-text-field>
            </div>
            <div class="m-top-d">
              <div>이름</div>
              <v-text-field v-model="state.name" density="compact"></v-text-field>
            </div>
            <div class="m-top-d">
              <div>학번</div>
              <v-text-field v-model="state.studentNo" density="compact"></v-text-field>
            </div>
          </div>
          <div class="profile-main-button">
            <div>
              <v-btn
                @click="toUpdate"
                width="120"
                height="40"
                rounded="lg"
                color="#858585"
                class="profile-main-button-user"
                style="color: white; font-size: 15px"
              >
                <div class="profile-main-button-text">정보 수정</div>
              </v-btn>
            </div>
            <router-link :to="{ name: 'CheckPassword' }">
              <v-btn
                height="40"
                rounded="lg"
                color="#858585"
                style="color: white; font-size: 15px"
                class="profile-main-button-password"
              >
                <div class="profile-main-button-text">비밀번호 변경</div>
              </v-btn>
            </router-link>
          </div>
        </v-form>
      </div>
      <!-- 사용자 프로필 사진 -->
      <div class="profile-main-photo" >
        <img
          class="image-box"
          width="200"
          height="200"
        />
      </div>
    </div>
  </div>
</template>

<script>
import { reactive, onMounted, computed } from "@vue/runtime-core";
import { useRouter } from "vue-router"; 
import { useStore } from "vuex";

export default {
  name: "ProfilePage",
  setup() {
    const router = useRouter();   
    const state = reactive({
      model: null,
      email: "",
      name: "",
      studentNo:"",
    });

    const store = useStore()
    const loginUser = computed(()=>store.state.loginUser)

    // '정보 수정' 버튼
    // 프로필 페이지(ProfilePage) → 프로필 변경 페이지(UpdateProfile)
    const toUpdate = () => {
      router.push({name:"UpdateProfile"});
    }

    // 초기화면 세팅
    onMounted(() => {
      const previews = document.querySelectorAll(".image-box");
      const loginUser = store.state.loginUser
      state.email = loginUser.email;
      state.name = loginUser.name;
      state.studentNo = loginUser.studentNo;
      state.image = loginUser.image;

      // 사용자 프로필 이미지 세팅
        // 숫자(디즈니)일 경우 
        // console.log(state.image)
      if (state.image.length < 5) {
        previews[0].src = require(`@/assets/profiles/scale (${loginUser.image}).png`)
        // const previews = document.querySelectorAll(".image-box");
        // previews[0].src = state.image;
      } else {
        // 파일일 경우 
        // const previews = document.querySelectorAll(".image-box");
        previews[0].src = state.image;
      }
    });

    return {
      toUpdate,
      loginUser,
      state,
    };
  },
};
</script>

<style scoped>
.image-box {
  border-radius: 50%;
}
.profile_icon {
  font-size: 50px;
  color: white;
}
.upload-btn {
  display: inline-block;
  cursor: pointer;
}
input[type="file"] {
  display: none;
}

.select {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 110px;
  width: 110px;
  border-radius: 55px;
  background-color: #ff0a54;
}

.profile-main-button-text {
  margin-top: 3px;
}
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
  margin-top: -5px;
}
.profile-input-icon:hover {
  cursor: pointer;
}
.profile-input-icon {
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 30px;
  position: relative;
  width: 49px;
  height: 49px;
  background-color: white;
  border: 1px solid rgb(209, 209, 209);
  top: -100px;
  left: 215px;
  box-shadow: 1px 1px 2px 0.3px;
}

.profile-main-button {
  margin-top: 20px;
  justify-content: space-between;
  width: 270px;
  height: 30px;
  display: flex;
}
.profile-main-photo {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 270px;
  height: 290px;
  object-fit: block;
}
.profile-main-photo:hover {
  cursor: pointer;
}
.profile-main-form-text {
  font-family: appleM;
  font-size: 14px;
}
.profile-main-form {
  width: 270px;
  height: 290px;
}
.profile-title {
  font-family: appleB;
  font-size: 50px;
  text-align: center;
  width: 300px;
  height: 80px;
}
.profile-main {
  margin-top: 15px;
  align-items: center;
  display: flex;
  justify-content: space-evenly;
  width: 640px;
  height: 300px;
}
.profile-photo {
  margin-top: -30px;
  width: 1000px;
  height: 200px;
}
.profile-container {
  font-family: appleB;
  margin-top: 95px;
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>
