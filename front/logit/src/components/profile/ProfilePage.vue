<template>
  <div class="profile-container">
    <div class="profile-title">프로필</div>
    <div class="profile-main">
      <div class="profile-main-form">
        <v-form>
          <span>이메일</span>
          <v-text-field v-model="email"></v-text-field>
          <span>이름</span>
          <v-text-field v-model="name"></v-text-field>
          <span>학번</span>
          <v-text-field v-model="ssafyNum"></v-text-field>
          <div class="profile-main-button">
            <div class="profile-main-button-user">정보 수정</div>
            <div class="profile-main-button-password">비밀번호 변경</div>
          </div>
        </v-form>
      </div>
      <div class="profile-main-photo">
        <img :src="require(`@/assets/profiles/scale (3).png`)" width="200" />
      </div>
    </div>
    <div class="profile-input-icon" @click="selProf">
      <v-icon size="large">mdi-lead-pencil</v-icon>
    </div>
    <div class="profile-photo"></div>
  </div>
</template>

<script>
export default {
  name: "ProfilePage",
  data: () => ({
    name: "이름",
    email: "asdas@gmail.com",
    ssafyNum: "084182",
  }),
  methods: {
    selProf() {},
  },
};
</script>

<style scoped>
.profile-input-icon:hover {
  cursor: pointer;
}
.profile-input-icon {
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 30px;
  position: relative;
  width: 47px;
  height: 47px;
  background-color: white;
  border: 1px solid rgb(209, 209, 209);
  top: -120px;
  left: 215px;
  box-shadow: 1px 1px 2px 0.3px;
}

.profile-main-button {
  width: 250px;
  height: 30px;
  display: flex;
  background-color: purple;
}
.profile-main-photo {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 250px;
  height: 300px;
  background-color: blue;
}
.profile-main-form {
  padding-top: 30px;
  width: 250px;
  height: 300px;
  background-color: green;
}
.profile-title {
  font-family: appleB;
  font-size: 50px;
  text-align: center;
  width: 300px;
  height: 80px;
  background-color: red;
}
.profile-main {
  align-items: center;
  display: flex;
  justify-content: space-evenly;
  width: 640px;
  height: 350px;
  background-color: orange;
}
.profile-photo {
  margin-top: -50px;
  width: 1200px;
  height: 200px;
  background-color: yellow;
}
.profile-container {
  margin-top: 120px;
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>
