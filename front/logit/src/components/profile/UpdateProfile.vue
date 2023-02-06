<template>
  <div class="profile-container">
    <div class="profile-title">프로필</div>
    <div class="profile-main">
      <div class="profile-main-form">
          <div class="profile-main-form-text">
        <v-form disabled>
            <div class="profile-main-form-text-email">
              <div>이메일</div>
              <v-text-field v-model="email" density="compact"></v-text-field>
            </div>
        </v-form>
        <v-form>
            <div class="m-top-d">
              <div>이름</div>
              <v-text-field v-model="name" density="compact"></v-text-field>
            </div>
            <div class="m-top-d">
              <div>학번</div>
              <v-text-field v-model="ssafyNum" density="compact"></v-text-field>
            </div>
          </v-form>
        </div>
      <v-form disabled>
          <div class="profile-main-button">
            <div @click="updateUser">
              <v-btn
                width="120"
                height="40"
                rounded="lg"
                color="#FF0A54"
                class="profile-main-button-user"
                style="color: white; font-size: 15px"
              >
                <div class="profile-main-button-text">입력 저장</div>
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
      <div class="profile-main-photo" @click="onShow">
        <img class="image-box"  :src="require(`@/assets/profiles/scale (${fileNum}).png`)" width="200" />
      </div>
    </div>
    <div class="profile-input-icon" @click="onShow">
      <v-icon v-show="!fileChk" size="large">mdi-lead-pencil</v-icon>
      <v-icon @click="photoChg" v-show="fileChk" style="font-size:200%;">mdi-check</v-icon>
    </div>
    <!-- 아래 프로필 사진 선택 창-->
    <div v-show="photo" class="profile-photo">
      <v-sheet class="mx-auto" max-width="990">
        <v-slide-group
          v-model="model"
          class="pa-4"
          selected-class="bg-primary"
          show-arrows
        >
          <v-slide-group-item
            v-slot="{ isSelected, toggle, selectedClass }"
          >
          <label for="file" class="upload-btn">
          <!-- 사용자 파일 업로드 선택 창-->  
            <input @change="fileChg" id="file" type="file" accept="image/*"/>
              <div class="ma-4 select hover_cursor hover_bigger">
                <v-icon class="profile_icon f_icon lay3 btn_clicked2">mdi-folder-plus-outline</v-icon>  
              </div>
          </label>
            <div v-for="i in 36" :key="i">
          <!-- 디즈니 프로필 사진 선택 창-->
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
    </div>
  </div>
</template>

<script>

export default {
  name: "UpdateProfile",
  
  data: () => ({
    name: "이름",
    email: "asdas@gmail.com",
    ssafyNum: "084182",
    model: null,
    photo: false,
    fileDOM: "",
    previews: "",
    fileNum: "3",
    fileChk: false,
    uploadState: false,
    imageSrc: "",
    fileSrc:"",
  }),
  methods: {

    updateUser() { 
      // 파일 이미지 선택했을경우
      if (uploadState) {
        this.fileSrc = this.imageSrc;
      } else { 
        this.fileSrc = this.id;
      }
      const user = {
        name: this.name, //수정
        email: sessionStorage.getItme("user"), //로그인 된 이메일
        studentNo: this.ssafyNum, //수정
        image:this.fileSrc, //수정
      }
      this.$store.dispatch('uploadImage', user);
    //   "name" : "김설희",
    // "email" : "2750seolhee@naver.com",
    // "pw" : "1234",
    // "flag" : 8,
    // "studentNo" : "0812345",
    // "isDeleted" : 0,
    //     "image" : "1"
      this.$router.push('ProfilePage');
    },
    onShow() {
      this.photo = !this.photo;
      this.fileChk = !this.fileChk;
    },
    fileChg() { 
      const fileDOM = document.querySelector('#file');
      const previews = document.querySelectorAll('.image-box');
      this.imageSrc = URL.createObjectURL(fileDOM.files[0]);
      this.uploadState = true;
      console.log(this.uploadState)
      previews[0].src = imageSrc;
    },
    onClicked(i) {
      this.uploadState = false;
      this.fileNum = i;
     }
  },
};
</script>

<style scoped>
.image-box{
  border-radius: 50%;
}
.profile_icon{
  font-size: 50px;
color:white
}
 .upload-btn {
    display: inline-block;
    cursor: pointer;

 }
  input[type=file] {
      display: none;
  }
  
.select {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 110px;
  width: 110px;
  border-radius: 55px;
  background-color: #FF0A54;
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
  object-fit:  block;

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
  width: 1200px;
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
