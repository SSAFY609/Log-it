<template>
    <div class="signup-container">
        <h2 class="signup-title">계정 생성</h2>
        <div class="signup=email fill-height">
          <v-form
            ref="form"
            v-model="valid"
            lazy-validation
            > 
            <v-text-field
               v-model="email"
               :rules="emailRules"
               label="E-mail"
               required
            >
            </v-text-field>
            <div class="signup-button">
               <span>다음</span>
            </div>
         </v-form>
      </div>
    </div>
  </template>
  
  <script>
export default {
   name: 'UserEmail'
}

  </script>
  
  <style>
  .signup-button{
   display: flex;
   justify-content: center;
   align-items: center;
   font-weight:bold;
   font-size: 18.5px;
   color: white;
   width: 380px;
   height: 60px;
   background-color: #FF0A54;
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

   .signup-email{
   width:100%;
   }

  .signup-title{
   font-size: 45px;
   font-family: AppleB;
   margin-top:-13px;
   margin-bottom: 5px;
  }
  </style>