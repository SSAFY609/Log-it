<template>
  <div class="profile-container">
    <div class="profile-title">프로필</div>
    <div class="profile-main">
      <div class="profile-main-form">
        <v-form disabled>
          <div class="profile-main-form-text">
            <div class="profile-main-form-text-email">
              <div>이메일</div>
              <v-text-field v-model="email" density="compact"></v-text-field>
            </div>
            <div class="m-top-d">
              <div>이름</div>
              <v-text-field v-model="name" density="compact"></v-text-field>
            </div>
            <div class="m-top-d">
              <div>학번</div>
              <v-text-field v-model="ssafyNum" density="compact"></v-text-field>
            </div>
          </div>
          <div class="profile-main-button">
            <router-link :to="{ name: 'UpdateProfile' }">
              <v-btn
                width="120"
                height="40"
                rounded="lg"
                color="#FF0A54"
                class="profile-main-button-user"
                style="color: white; font-size: 15px"
              >
                <div class="profile-main-button-text">정보 수정</div>
              </v-btn>
            </router-link>
            <router-link :to="{ name: 'CheckPassword' }">
              <v-btn
                height="40"
                rounded="lg"
                color="#FF0A54"
                style="color: white; font-size: 15px"
                class="profile-main-button-password"
              >
                <div class="profile-main-button-text">비밀번호 변경</div>
              </v-btn>
            </router-link>
          </div>
        </v-form>
      </div>
      <div class="profile-main-photo" @click="onShow">
        <img :src="require(`@/assets/profiles/scale (3).png`)" width="200" />
      </div>
    </div>
    <div class="profile-input-icon" @click="onShow">
      <v-icon size="large">mdi-lead-pencil</v-icon>
    </div>
    <div v-show="photo" class="profile-photo">
      <v-sheet class="mx-auto" max-width="990">
        <v-slide-group
          v-model="model"
          class="pa-4"
          selected-class="bg-primary"
          show-arrows
        >
          <v-slide-group-item
            v-for="n in 36"
            :key="n"
            v-slot="{ isSelected, toggle, selectedClass }"
          >
            <div v-for="i in 36" :key="i">
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
  name: "ProfilePage",
  data: () => ({
    name: "이름",
    email: "asdas@gmail.com",
    ssafyNum: "084182",
    model: null,
    photo: false,
  }),
  methods: {
    onShow() {
      this.photo = !this.photo;
    },
  },
};
</script>

<style scoped>
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
