<template>
    <div class= "login-container lay1">
      <h2 class= "login-title lay2">로그인</h2>
      <div class= "login-form lay3">
         <div class= "login-form-input">
            <v-text-field
            v-model="email"
            :readonly="loading"
            :rules="rules2"
            class="mb-2"
            clearable
            label="Email"
             ></v-text-field>
             <v-text-field
              v-model="password"
              :append-inner-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
              :rules="[rules1.required, rules1.min]"
              :type="show1 ? 'text' : 'password'"
              name="input-10-1"
              label="Password"
          
              clearable
              @click:append-inner="show1 = !show1"
          ></v-text-field>
         
        </div>
        <div class="login-form-OauthBtn ">
            <div class="logo-img-kakao">
              <v-img class="logo-img"
              :src="require('../../assets/images/kakao_logo.png')"
              height="25px"
               />
              <span class="logo-text">카카오 로그인</span>
            </div>
            <div class="logo-img-google">
              <v-img class="logo-img google-logo-img"
              :src="require('../../assets/images/google_logo.png')"
              height="25px"
               />
              <span class="logo-text google-logo-text">구글 로그인</span>
            </div>
        </div>
        <div  @click="loginCheck" class="login-form-input-button ">
          <div class="login-form-input-button-btn b_lightgray_l">
            <v-icon class="login-btn menu_icon f_darkgray ">mdi-arrow-right</v-icon> 
          </div>
        </div>
        <div class="login-form-link">
          <span >비밀번호를 잊으셨습니까?</span>
          <div>
          <span>Log-it이 처음인가요?<spam>회원가입 바로가기</spam></span>
          </div> 
        </div>

      </div>
    </div>
  </template>
  
  <script >
    
  export default {
  name: 'UserLogin',
  
  data: () => ({
    rules1: {
          required: value => !!value || 'Required.',
          min: v => v.length >= 8 || '최소 8자리 이상 입력해주세요.',
          emailMatch: () => (`The email and password you entered don't match`),
        },
    rules2:
      [
        value => !!value || 'Required.',
        value => (value || '').length <= 20 || 'Max 20 characters',
        value => {
          const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
          return pattern.test(value) || '유효하지 않은 이메일 형식입니다.'
        },       
    ],
    
    email: "",
    show1: false,
    show2: true,
    password: '',
    rules1Check: false,
    rules2Check: false,
    }),
 
   methods: {
     kakaoLogin() {},
     googleLogin() {},
     loginCheck() {}

  },

}
  </script >
  
  <style scoped>
.login-form-link{
  display: flex;
  flex-direction: column;
  align-items: center;

}
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
   justify-content: space-between;
  }

  .logo-img-kakao{
    justify-content: center;
    align-items: center;
    display: flex;
    border-radius: 6px;
    width: 182px;
    height: 45px;
    margin: 10px 0px;
  } .logo-img-google{
    justify-content: center;
    align-items: center;
    display: flex;
    border-radius: 6px;
    width: 182px;
    height: 45px;
    margin: 10px 0px;
  }
  .logo-text{
    right: 18px;
    position: relative;
  }
  .logo_img:hover{
    cursor: pointer;
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
    display: flex;
    justify-content: center;
    align-items: center;
    transition: background-color .2s;
    cursor: pointer;
  }
  .login-form-input-button-btn:hover{
    background-color: #FF0A54 ;
    cursor: pointer;
  }
  .login-form-input-button-btn:active{
    background-color: #C0003A;
  }
  .login-btn{
    cursor: pointer;
    display: inline-block;
    position:relative;
    font-size: 50px !important;
    color:white;
  }
  .login-form-OauthBtn:hover{
    cursor: pointer;
  }
  .logo-img-kakao{
    background-color: #FEE500;
    justify-content: center;
  }
  .logo-img-google{
    background-color: white;
    border: 1px solid rgb(207, 207, 207);
  }
  .logo-text{
    font-size: 18px;
    color:#191919;
    ;
  }
  .google-logo-img{
    right: 8px;
  }
  .google-logo-text{
    right:23px;
  }
  </style>
