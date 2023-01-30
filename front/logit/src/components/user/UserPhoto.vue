<template>
  <div class="profile-container">
    <h2 class="profile-title">프로필 생성</h2>
    <div class="profile-scroll-container fill-height">
      <div class="profile-scroll">
        <!-- <span
          v-for="i in 36"
          :key="i"
          @click="selProf"
          class="profile-input-img"
        > -->
        <v-col
          cols="3"
          v-for="i in 36"
          :key="i"
          @click="selProf"
          class="profile-input-img"
        >
          <img
            :src="require(`@/assets/profiles/scale (${i}).png`)"
            width="110"
          />
          <!-- </v-col> -->
        </v-col>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "UserPhoto",
  data: () => ({}),
  methods: {
    imgUrl(num) {
      return `@/assets/profiles/scale (${num}).png`;
    },
  },
  computed: {},
};
</script>
<style scoped>
.profile-scroll {
  margin-left: 5px;
  height: 350px;
  width: 420px;
  overflow-y: scroll;
  margin-top: -10px;
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
  margin-top: -13px;
}
</style>
