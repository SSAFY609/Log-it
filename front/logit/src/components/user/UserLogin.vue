<template>
    <div class= "login-container lay1">
      <h2 class= "login-title lay2">로그인</h2>
      <div class= "login-form lay3">
         <div class= "login-form-input">
            <v-text-field
            v-model="email"
            :readonly="loading"
            :rules="rules"
            class="mb-2"
            clearable
            label="Email"
            style="border-radius:20px;"
             ></v-text-field>
            <v-text-field
            v-model="password"
            :readonly="loading"
            :rules="[required]"
            clearable 
            label="Password"
            placeholder="Enter your password"
            ></v-text-field>
        </div>
        <div class="login-form-OauthBtn ">
          <v-img class="logo_img_kakao" @click="kakaoLogin" 
          :src="require('../../assets/images/kakao_login_btn.png')"
          height="80"
          /><v-img class="logo_img_google" @click="googleLogin" 
          :src="require('../../assets/images/google_login_btn.png')"
          height="80"
          />
        </div>
        <div  @click="loginCheck" class="login-form-input-button ">
          <div class="login-form-input-button-btn b_lightgray_l"></div>
          
        </div>
        <div class="login-form-link">

        </div>

      </div>
    </div>
  </template>
  
  <script>
  export default {
  name: 'UserLogin',
  data: () => ({
      rules: [
        value => !!value || 'Required.',
        value => (value || '').length <= 20 || 'Max 20 characters',
        value => {
          const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
          return pattern.test(value) || 'Invalid e-mail.'
        },
    ],
    email: "",
    password:"",
    }),
 
   methods: {
     kakaoLogin() {},
     googleLogin() {},
     loginCheck() {}

  },

}
  </script >
  
  <style scoped>
  .login-container{
   display: flex;
   width: 100%;
   flex-direction: column;
   padding: 10px 30px;
   justify-content: center;
   align-items: center;
  }
  .login-title{
   font-size: 40px;
   font-family: AppleB;
  }
  .login-form {
   width: 100%;
   padding: 0px 30px;
  }
  .login-form-OauthBtn{
   display: flex;
   justify-content: space-around;
  }

  .logo_img:hover{
    cursor: pointer;
  }
  .logo_img_kakao{
    margin-right:10px;
  }
  .logo_img_google{
    margin-left:10px;
  }
  .login-form-input-button{
    display: flex;
    justify-content: center;
  }
  .login-form-input-button-btn{
    border-radius: 15px;
    margin-top: 15px;
    margin-bottom: 15px;
    width:85px;
    height: 85px;
  }
  </style>