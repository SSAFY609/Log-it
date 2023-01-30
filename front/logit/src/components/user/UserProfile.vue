<template>
   <div class="profile-container">
    <h2 class="profile-title">프로필 생성</h2>
    <div class="profile-input fill-height">
      <v-form ref="form" v-model="valid" lazy-validation>
     <!-- 프로필 사진 선택 폼-->
         <div class="profile-input-form" >
            <img :src="require('@/assets/profiles/scale (4).png')" @click="selProf"
               class="profile-input-img"/>
         </div>
         <div class="profile-input-icon" @click="selProf" >
            <v-icon size="large">mdi-lead-pencil</v-icon>
         </div>
     <!-- 닉네임 입력 칸-->
        
        <div @click="signProf" class="profile-button b_lightgray_l">
          <div>다음</div>
        </div>
        <div class="profile-link">
          <router-link :to="{ name: 'UserPassword' }">
            <span class="profile-link-data"> 뒤로가기</span>
          </router-link>
        </div>
      </v-form> 
    </div>
  </div>
  </template>
  
  <script>
  export default {
   name: 'UserProfile',
   data: () => ({
      preview: '',
      saveFile:null,
      fileList:[]
}),
   methods: {
      selProf() { 
         this.$router.push("photo");
      }
     } 
}

  </script>
  
  <style scoped>

.profile-input-form{
   margin-top: 20px;
   display: flex;
   justify-content: center;
}
.profile-input-img{
   left: 200px;
   width:165px;
   height: 165px;
   border-radius: 90px;
   box-shadow: 1px 1px 5px 0.3px;
}
.profile-input-img:hover{
   cursor: pointer;
}
.profile-input-icon:hover{
   cursor: pointer;
}   
.profile-input-icon{
   display: flex;
   justify-content: center;
   align-items: center;
   border-radius: 30px;
   position:relative;
   width:47px;
   height: 47px;
   background-color: white;
   border: 1px solid rgb(209, 209, 209);
   top:-43px;
   left: 200px;
   box-shadow: 1px 1px 3px 0.3px;
}

.profile-input{
   margin-top:-10px;
}
.profile-link-data{
   color:#9d9d9d;
}

.profile-button:hover {
  background-color: #ff0a54;
  cursor: pointer;
}
.profile:active {
  background-color: #c0003a;
}
.profile-link {
  margin-top: 25px;
  text-align: center;
}
.color {
  background-color: #ff0a54;
}
.Profile-container {
  position: relative;
  margin-top: 12px;
}
.profile-title {
  padding: 25px;
}
.profile-button {
  display: flex;
  justify-content: center;
  align-items: center;
  font-weight: bold;
  font-size: 18.5px;
  color: white;
  width: 380px;
  height: 60px;
  border-radius: 10px;
  margin-top: 8px;
}
.profile-container {
  display: flex;
  width: 100%;
  flex-direction: column;
  padding: 10px 30px;
  justify-content: center;
  align-items: center;
}

.profile-title {
  font-size: 51px;
  font-family: AppleB;
  margin-top: -13px;
  margin-bottom: 5px;
}
  </style>