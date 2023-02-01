<template>
    <div class="fpw-container">
      <h2 class="fpw-title">암호 재발급</h2>
      <div class="fpw-email fill-height">
          <div>
             <div style="font-size:19px;  margin-bottom: 90px; margin-top:-5px;">&nbsp;임시 발급된 비밀번호로 로그인하세요.</div>
          </div>
          <div @click="toLogin" class="fpw-button b_lightgray_l">
            <div>다음</div>
          </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: "UserPasswordReset",
    data: () => ({
    }),

    methods: {
        toLogin() {
            this.$router.push("signup");
        }
      },
  };
  </script>
  
  <style scoped>
    .fpw-email{
        margin-top:10px
    }
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
    padding: 47px;
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
  