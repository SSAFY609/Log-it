<template>
   <div class="fpw-container">
     <h2 class="fpw-title">계정 확인</h2>
     <div class="fpw-email fill-height">
       <v-form ref="form" v-model="valid" lazy-validation @keyup="chkEmail">
         <div  style="margin-bottom: 5px;">&nbsp;&nbsp;가입되어 있는 이메일 주소를 입력해주세요.</div>
         <v-text-field
           v-model="email_tmp"
           :rules="rules2"
           label="E-mail"
           class="mb-2"
           required
         >
         </v-text-field>
         <div class="fs">
            <div style="margin-bottom: 13px; margin-top:-5px;">&nbsp;- 전송된 메일로 인증을 완료하면 임시 비밀번호가 발급됩니다 .</div>
            <div style="margin-bottom: 18px">&nbsp;- 인증 메일은 발송 후 24시간 내에만 유효합니다.</div>
         </div>
         <div @click="fpwEmail" class="fpw-button b_lightgray_l">
           <div>다음</div>
         </div>
         <div class="fpw-link">
           <span>이미 회원이신가요?</span>
           <router-link :to="{ name: 'UserLogin' }">
             <span class="fpw-link-Login"> 로그인 바로가기</span>
           </router-link>
         </div>
       </v-form> 
     </div>
   </div>
 </template>
 
 <script>

 export default {
   name: "UserEmail",
   data: () => ({
     rules2: [
       (value) => !!value || "",
       (value) => (value || "").length <= 20 || "Max 20 characters",
       (value) => {
         const pattern =
           /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
         return pattern.test(value) || "";
       },
     ],
     email_tmp: "",
     email: "",
     email_help: "",
   }),
   methods: {
     fpwEmail() {
       if (!this.email_tmp.trim()) {
         alert("입력된 이메일 주소가 없습니다.");
         return;
       }
       else if (!document.querySelector(".fpw-button").classList.contains("color")) {
         alert("이메일 주소를 확인해주세요.");
         return;
       }
       else {
        const email = this.email_tmp
        console.log(email)
        this.$store.dispatch("sendPw", email);
        this.$router.push("reset");
       }
     },

     async chkEmail() { 
      const validate  = await this.$refs.form.validate();
       if (validate.valid) {
         document.querySelector(".fpw-button").classList.add("color");
       } else {
         document.querySelector(".fpw-button").classList.remove("color");
       }
     },
   },
 };
 </script>
 
 <style scoped>
   .fs{
      font-size: 14.5px;
   }

 .none {
   display: none;
 }

 .animation {
   animation: fadeInText 0.2s 0.2s ease-out forwards;
 }
 
 @keyframes fadeInText {
   100% {
     opacity: 1;
   }
 }
 
 .fpw-link-Login {
   color: #ff0a54;
   text-decoration: underline;
 }
 .fpw-button:hover {
   background-color: #ff0a54;
   cursor: pointer;
 }
 .fpw-button:active {
   background-color: #c0003a;
 }
 .fpw-link {
   margin-top: 55px;
   text-align: center;
 }
 .color {
   background-color: #ff0a54;
 }
 .fpw-container {
   position: relative;
   margin-top: 12px;
 }
 .fpw-title {
   padding: 20px;
 }
 .fpw-button {
   display: flex;
   justify-content: center;
   align-items: center;
   font-weight: bold;
   font-size: 18.5px;
   color: white;
   width: 380px;
   height: 60px;
   border-radius: 10px;
 }
 .fpw-container {
   display: flex;
   width: 100%;
   flex-direction: column;
   padding: 10px 30px;
   justify-content: center;
   align-items: center;
 }
 
 .fpw-form {
   width: 90%;
 }
 
 .fpw-title {
   font-size: 51px;
   font-family: AppleB;
   margin-top: -13px;
   margin-bottom: 5px;
 }
 </style>
 