<template>
   <div class="profile-container">
     <div class="profile-title">비밀번호 변경</div>
     <div class="profile-main">
       <div class="profile-main-form">
           <div class="profile-main-form-text">
         <v-form disabled >
             <div class="profile-main-form-text-email">
               <div>계속하려면 먼저 본인임을 인증하세요.</div>
               <v-text-field v-model="email"
               density="comfortable"></v-text-field>
             </div>
         </v-form>
             <div class="m-top-d">
               <div>비밀번호 입력</div>
               <v-text-field
                v-model="password"
                :append-inner-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
                :type="show1 ? 'text' : 'password'"
                name="input-10-1"
                label="Password"
                @click:append-inner="show1 = !show1"
               ></v-text-field>
             </div>

           </div>
           <div class="profile-main-button">
             <router-link :to="{ name: 'UpdatePassword' }">
               <v-btn
                 width="380"
                 height="47"
                 rounded="lg"
                 color="#858585"
                 class="profile-main-button-user"
                 style="color:white; font-size:15px;
                  margin-top:10px;" 
               >
                 다음
               </v-btn>
             </router-link>
           </div>
       </div>
     </div>
   </div>
 </template>
 
 <script>
 export default {
   name: "CheckPassword",
   data: () => ({
     name: "이름",
     email: "asdas@gmail.com",
     ssafyNum: "084182",
     model: null,
     photo: false,
     password: "",
     show1: false,
     show2: true,
   }),
   methods: {
     onShow() {
       this.photo = !this.photo;
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
 .hover_bigger:hover{
   border-radius: 50%;
   height: 114px;
   width:114px;
 }
 .m-top-d{
   margin-top:10px;
 }
 
 .profile-main-button {
   margin-top: 25px;
   justify-content: space-between;
   width: 270px;
   height: 30px;
   display: flex;
 }

 .profile-main-form-text{
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
   margin-top:20px;
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
 