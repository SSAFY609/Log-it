<template>
  <div class="container">
    <div class="discription_box lay2">
      <div class="logo_box">
        <v-img class="logo_img"
               :src="require('../../assets/images/logit_logo_text.png')"

        />
      </div>
      <div>
        <div v-if="state.loginUser == null">
          <h1>당신의 새로운 여정.</h1>
          <h1>매일 기록해보세요</h1>
        </div>
        <div v-else>
          <h1>{{ state.loginUser.name }} 님 환영합니다.</h1>
          <h1>지금 바로 기록해보세요.</h1>
        </div>
        <p>진행중인 이벤트와 취업여정을 기록하면서 달라진 나의 모습을 발견하세요.</p>

      </div>
      <router-link :to="{name: 'UserLogin'}" class="login_btn_box b_main btn_hover">
        <div class="login_btn_text f_white">시작하기</div>
      </router-link>
      <div class="img_box lay1">
        <v-img class="laptop_img"
               :src="require('../../assets/images/laptop02.png')"
        />
      </div>
    </div>
  </div>
</template>

<script>
export default {
    name: 'MainPage',
    setup() {
      const state = {
        loginUser: {name: '이성훈'},
      }
      return {
        state,
      }
    }
    
    

    
}
</script>

<style scoped>
  .container {
    height: 100%;
    display:flex;
    align-items: center;
    justify-content: center;
  }
  .discription_box {
    margin-top: 70px;
    height: 1300px;
    justify-content: center;
  }
  h1 {
    text-align: center;
    font-family: appleB;
    font-size: 50px;
    ;
  }
  p {
    text-align: center;
    font-size: 24px;
    margin-top: 6px;
  }
  .logo_box {
    width: 200px;
    height: 70px;
    margin: 0 auto;
    margin-top: 10px;
    
  }
  .logo_img {
    margin-left: 6px;
    margin-top: 10px;
  }
  .img_box {
    width: 1300px;
    display: flex;
    justify-content: center;
    margin-top: 0px;
  }
  .laptop_img {
    margin: 0 auto;
  }
  .login_btn_box {
    width: 200px;
    height: 50px;
    border-radius: 6px;
    display: flex;
    justify-content: center;
    align-items: center;
    margin: 0 auto;
    margin-top: 20px;
  }
  .login_btn_text {
    margin-top: 2px;
    font-size: 22px;
    font-weight: 500;
  }

</style>