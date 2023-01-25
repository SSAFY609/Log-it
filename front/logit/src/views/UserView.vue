<template>
    <div class="user-container b_lightgray_l">
      <div class="user-box">
         <div class="logo_box lay3 " style="padding-top:30px">
             <v-img class="logo_img"
             :src="require('../assets/images/logit_logo_text.png')"
              height="50">
              </v-img>
         </div>
         <router-view></router-view>
      </div>
    </div>
  </template>
  
  <script>
  export default {
     name: 'UserView',
  }
  </script>
  
  <style scoped>
  .user-container{
   display: flex;
   justify-content: center;
   align-items: center;
   background-color: EDEDED;
  }
  .user-box{
   margin: 60px 0px;
   background-color: white;
   border-radius: 20px;
   width: 500px;
   height: 600px;
  }
  </style>