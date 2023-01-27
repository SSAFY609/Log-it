<template>
    <div class="signup-container">
        <h2 class="signup-title">계정 생성</h2>
        <div class="signup-email fill-height">
          <v-form
               ref="form"
               v-model="valid"
               lazy-validation
               @keyup="emailCheck"
            > 
            <div style="margin-bottom:5px">&nbsp;&nbsp;이메일을 입력해주세요.</div>
            <v-text-field
               v-model="email"
               :rules="rules2"
               label="E-mail"
               class="mb-2"
               required

            >
            </v-text-field>
            <div class="signup-button b_lightgray_l">
               <div>다음</div>
            </div>
            <div class="signup-link">
                  <span>이미 회원이신가요?</span>
               <router-link :to="{name: 'UserLogin'}">
                  <span class="signup-link-Login" > 로그인 바로가기</span>
               </router-link>
            </div>
         </v-form>
      </div>
    </div>
  </template>
  
  <script>
export default {
   name: 'UserEmail',
   data: () => ({
      rules2:
         [
            value => !!value || '',
            value => (value || '').length <= 20 || 'Max 20 characters',
            value => {
               const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
               return pattern.test(value) || '유효하지 않은 이메일 형식입니다.'
            },
         ],
         email:"",
   }),
   methods: {
      async emailCheck() {
         const validate = await this.$refs.form.validate()
         if (validate.valid) {
            document.querySelector('.signup-button').classList.add('color');
         } else {
            document.querySelector('.signup-button').classList.remove('color');
         }
      },
   },
}

  </script>
  
  <style>
   .signup-link-Login{
      color: #FF0A54;
      text-decoration:underline;
   }
   .signup-button:hover {
      background-color: #FF0A54;
      cursor: pointer;
   }
   .signup-button:active{
      background-color: #C0003A;
   } 
   .signup-link{
      margin-top:55px;
      text-align: center;
   }
  .color{
   background-color: #FF0A54;
  }
   .signup-container{
      margin-top: 12px;
   }
   .signup-title{
      padding:30px
   }
  .signup-button{
   display: flex;
   justify-content: center;
   align-items: center;
   font-weight:bold;
   font-size: 18.5px;
   color: white;
   width: 380px;
   height: 60px;
   border-radius: 10px;
  }
   .signup-container{
   display: flex;
   width: 100%;
   flex-direction: column;
   padding: 10px 30px;
   justify-content: center;
   align-items: center;
   }

   .signup-form{
   width:90%;
   }
   
  .signup-title{
   font-size: 51px;
   font-family: AppleB;
   margin-top:-13px;
   margin-bottom: 5px;
  }
  </style>