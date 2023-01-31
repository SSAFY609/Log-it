<template>
  <div class="password-container">
   <h2 class="password-title">프로필 생성</h2>
   <div class="password-input fill-height">
     <v-form ref="form" v-model="valid" lazy-validation @keyup="chkPw">
       <div  style="margin-bottom: 5px">&nbsp;&nbsp;사용자 이름을 입력해주세요.</div>
       <v-text-field
               v-model="userName"
               name="input-10-1"
               label="예) 홍길동"
               counter
         ></v-text-field>
       <div style="margin-bottom: 5px">&nbsp;&nbsp;SSAFY 학번을 입력해주세요.</div>
       <v-text-field
               v-model="ssafyNum"
               name="input-10-1"
               :rules="[rules1.required, rules1.min]"
               label="예) 080000"
               counter
         ></v-text-field>
       <span class="password-chkText"></span>
       <div @click="signPw" class="password-button b_lightgray_l">
         <div>게정 생성</div>
       </div>
       <div class="password-link">
         <router-link :to="{ name: 'UserProfile'}">
           <span class="password-link-Email"> 뒤로가기</span>
         </router-link>
       </div>
     </v-form> 
   </div>
 </div>
 </template>
 
 <script>
 export default {
  name: 'UserData',
  data: () => ({
     rules1: {
         required: value => !!value || '',
         min: v => v.length >= 6 || '학번을 정확히 입력해주세요..',
       },
   userName: "",
   ssafyNum: "",
}),

 methods: {
   // 계정생성 - 암호
   signPw() {
     if (!this.password_tmp.trim()) {
       alert("입력된 암호가 없습니다.");
       return;
     }
     if (
       !document.querySelector(".password-button").classList.contains("color")
     ) {
       alert("입력된 암호가 일치하지 않습니다.");
       return;
     }
     this.$router.push({name: 'UserProfile', params: {id : 1, password: this.password}});
   },

   async chkPw() {
     const validate = await this.$refs.form.validate();
      if (validate.valid) {
         if (this.password == this.password_tmp) {
            document.querySelector(".password-button").classList.add("color");
         }
     } else {
       document.querySelector(".password-button").classList.remove("color");
     }
   },
 },
 }
 </script>
 
 <style>
.password-input{
     margin-top:-10px;
  }
  .password-link-Email{
     color:#9d9d9d;
  }
 .none {
 display: none;
}

.password-chkText {
 position: absolute;
 top: 219px;
 left: 77px;
 font-size: 12.9px;
}
.animation {
 animation: fadeInText 0.2s 0.2s ease-out forwards;
}

@keyframes fadeInText {
 100% {
   opacity: 1;
 }
}
Email{
 color: #ff0a54;
 text-decoration: underline;
}
.password-button:hover {
 background-color: #ff0a54;
 cursor: pointer;
}
.password-button:active {
 background-color: #c0003a;
}
.password-link {
 margin-top: 45px;
 text-align: center;
}
.color {
 background-color: #ff0a54;
}
.password-container {
 position: relative;
 margin-top: 12px;
}
.password-title {
 padding: 25px;
}
.password-button {
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
.password-container {
 display: flex;
 width: 100%;
 flex-direction: column;
 padding: 10px 30px;
 justify-content: center;
 align-items: center;
}

.signup-form {
 width: 90%;
}

.password-title {
 font-size: 51px;
 font-family: AppleB;
 margin-top: -13px;
 margin-bottom: 5px;
}
 </style>