<template>
  <div class="profile-container">
    <div class="profile-title">프로필 변경</div>
    <div class="profile-main">
      <div class="profile-main-form">
        <div class="profile-main-form-text">
          <v-form
            ref="form"
            v-model="valid"
            lazy-validation
            disabled
            @keyup="chkPw"
          >
            <div class="profile-main-form-text-email">
              <div>이메일</div>
              <v-text-field
                v-model="email"
                density="comfortable"
              ></v-text-field>
            </div>
          </v-form>
          <div class="m-top-d">
            <div>이름</div>
            <v-text-field
              v-model="name"
              density="comfortable"
              @click="name = ''"
            ></v-text-field>
          </div>
          <div class="m-top-d">
            <div>학번</div>
            <v-text-field
              v-model="ssafyNum"
              name="input-10-1"
              :rules="[rules1.required, rules1.min]"
              density="comfortable"
              @click="ssafyNum = ''"
            ></v-text-field>
          </div>
        </div>
        <div class="profile-main-button">
          <router-link :to="{ name: 'ProfilePage' }">
            <v-btn
              width="380"
              height="50"
              rounded="lg"
              color="#FF0A54 "
              class="profile-main-button-user"
              style="color: white; font-size: 15px; left: 60px; margin-top: 8px"
            >
              입력 저장
            </v-btn>
          </router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "UpdateProfile",
  data: () => ({
    rules1: {
      required: (value) => !!value || "",
      min: (v) => v.length >= 7 || "학번을 정확히 입력해주세요..",
    },
    name: "이름",
    email: "asdas@gmail.com",
    ssafyNum: "0845182",
    model: null,
    photo: false,
  }),
  methods: {
    onShow() {
      this.photo = !this.photo;
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
  justify-content: center;
  width: 260px;
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
