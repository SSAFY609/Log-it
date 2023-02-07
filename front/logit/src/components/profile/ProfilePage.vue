<template>
  <div class="profile-container">
    <div class="profile-title">프로필</div>
    <div class="profile-main">
      <div class="profile-main-form">
        <v-form disabled>
          <div class="profile-main-form-text">
            <div class="profile-main-form-text-email">
              <div>이메일</div>
              <v-text-field v-model="loginUser.email" density="compact"></v-text-field>
            </div>
            <div class="m-top-d">
              <div>이름</div>
              <v-text-field v-model="loginUser.name" density="compact"></v-text-field>
            </div>
            <div class="m-top-d">
              <div>학번</div>
              <v-text-field v-model="loginUser.studentNo" density="compact"></v-text-field>
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
    <!-- 프로필 사진 아이콘 -->
    <!--
    <div class="profile-input-icon" @click="onShow">
      <v-icon v-show="fileChk" size="large">mdi-lead-pencil</v-icon>
      <v-icon v-show="!fileChk" style="font-size: 200%">mdi-check</v-icon>
    </div>
    -->
    <!-- 아래 프로필 사진 선택 창-->
    <!--
     <div v-show="photo" class="profile-photo">
      <v-sheet class="mx-auto" max-width="990">
        <v-slide-group
          v-model="model"
          class="pa-4"
          selected-class="bg-primary"
          show-arrows
        >
          <v-slide-group-item v-slot="{ isSelected, toggle, selectedClass }">
          -->
    <!-- 사용자 파일 업로드 선택 창-->
    <!--
              <input @change="fileChg" id="file" type="file" accept="image/*" />
              <div class="ma-4 select hover_cursor hover_bigger">
                <v-icon class="profile_icon f_icon lay3 btn_clicked2"
                  >mdi-folder-plus-outline</v-icon
                >
              </div>
            </label>
            <div v-for="i in 36" :key="i">
              -->
    <!-- 디즈니 프로필 사진 선택 창-->
    <!--
              <v-img
                color="grey-lighten-1"
                :src="require(`@/assets/profiles/scale (${i}).png`)"
                :class="['ma-4', selectedClass]"
                class="hover_cursor hover_bigger"
                style="border-radius: 55px"
                @click="toggle, onClicked(i)"
              >
                <div class="d-flex fill-height align-center justify-center">
                  <v-fade-transition>
                    <v-img
                      class="hover_cursor"
                      v-if="isSelected"
                      :src="require(`@/assets/profiles/scale (3).png`)"
                      height="110"
                      width="110"
                      style="filter: brightness(65%); border-radius: 55px"
                    ></v-img>
                  </v-fade-transition>
                </div>
              </v-img>
            </div>
          </v-slide-group-item>
        </v-slide-group>
      </v-sheet>
    </div> -->
  </div>
</template>

<script>
import { reactive, onMounted, computed } from "@vue/runtime-core";
import { useRouter } from "vue-router"; 
import { useStore } from "vuex";

export default {
  name: "ProfilePage",
  props: ['fileSrc'],
  setup() {
    const router = useRouter();   
    const state = reactive({
      model: null,
    });

    const store = useStore()

    const loginUser = computed(()=>store.state.loginUser)
    
    const toUpdate = () => {
      router.push({name:"UpdateProfile"});
    }

    onMounted(() => {
      const previews = document.querySelector(".image-box");
      const loginUser = store.state.loginUser
      if (loginUser.profile.length < 3) {
        previews.src = require(`@/assets/profiles/scale (${loginUser.profile}).png`)
      } else {
        previews.src = loginUser.profile
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
