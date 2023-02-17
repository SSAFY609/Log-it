<template>
  <div class="profile-container">
    <h2 class="profile-title">프로필 생성</h2>
    <div class="profile-scroll-container fill-height">
      <div class="profile-scroll">
        <!-- 파일 업로드 부분 -->
        <v-col
          cols="3"
          v-for="i in 36"
          :key="i"
          @click="selProf"
          class="profile-input-img"
        >
          <router-link @click="onClicked(i)" :to="{ name: 'UserProfile' }">
            <img
              :src="require(`@/assets/profiles/scale (${i}).png`)"
              width="110"
            />
          </router-link>
          <!-- </v-col> -->
        </v-col>
      </div>
    </div>
  </div>
</template>

<script>
import { reactive } from "vue";
export default {
  name: "UserPhoto",
  props: ["user"],
  setup(props, { emit }) {
    const state = reactive({
      image: "",
    });
    const onClicked = (i) => {
      emit("updateUserPhoto", i);
    };
    const selectFile = () => {};
    return {
      selectFile,
      state,
      onClicked,
    };
  },
};
</script>
<style scoped>
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
  height: 107.2px;
  width: 107.2px;
  border-radius: 50%;
  background-color: #ff0a54;
}

.filebox input[type="file"] {
  position: absolute;
  width: 0;
  height: 0;
  padding: 0;
  overflow: hidden;
  border: 0;
}

.input-file-button {
  padding: 6px 25px;
  background-color: #ff6600;
  border-radius: 4px;
  color: white;
  cursor: pointer;
}
.profile-input-img {
  margin-right: 33px;
}
.profile-scroll {
  display: flex;
  flex-wrap: wrap;
  margin-left: 5px;
  height: 420px;
  width: 420px;
  overflow-y: scroll;
}
.profile-scroll::-webkit-scrollbar {
  background: none;
  width: 10px;
}
.profile-scroll::-webkit-scrollbar-thumb {
  background-color: #7d7d7da8;
  border-radius: 10px;
}
.profile-container {
  height: 85%;
  margin-top: 12px;
  display: flex;
  width: 100%;
  flex-direction: column;
  padding: 10px 30px;
  justify-content: center;
  align-items: center;
}
.profile-title {
  padding: 25px;
  font-size: 51px;
  font-family: AppleB;
  margin-top: -45px;
  margin-bottom: -30px;
}
</style>
