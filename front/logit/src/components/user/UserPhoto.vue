<template>
   <div class="profile-container">
    <h2 class="profile-title">프로필 생성</h2>
    <div class="profile-scroll-container fill-height">
     <!-- 프로필 사진 선택 폼-->
     <div style="height: 200px;">
    <v-virtual-scroll :items="items">
      <div v-for="i in 1000" :key="i">{{ items }}</div>
    </v-virtual-scroll>
  </div>
   
   </div>   
  </div>
  </template>
  
  <script>
  export default {
   name: 'UserPhoto',
   data: () => ({
      items: Array.from({ length: 1000 }, (k, v) => v + 1),
}),
   methods: {

   } 
}
  </script>
  <style scoped>

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
  margin-bottom: 5px;
}
  </style>