<template>
  <div class="container">
    <div class="discription_box lay2">
      <div class="logo_box lay1">
        <v-img class="logo_img"
               :src="require('../../assets/images/logit_logo_text.png')"

        />
      </div>
      <div>
        <h1>매일 새로운 여정을</h1>
        <h1>기록해보세요.</h1>
        <p>진행중인 이벤트와 취업여정을 기록하면서 <br/>달라진 나의 모습을 발견해보아요.
        </p>
      </div>
      <div class="login_btn_box b_main hover_cursor">
        <div class="login_btn_text f_white">시작하기</div>
      </div>
      <div class="img_box lay1">
        <v-img class="laptop_img"
               :src="require('../../assets/images/laptop2.png')"
        />
      </div>
    </div>
  </div>
</template>

<script>
export default {
    name: 'MainPage',
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
    font-size: 70px;
  }
  h1 {
    text-align: center;
    font-family: appleB;
  }
  p {
    text-align: center;
    font-size: 28px;
    margin-top: 10px;
  }
  .logo_box {
    width: 200px;
    height: 70px;
    margin: 10px auto;
  }
  .logo_img {
    margin-left: 6px;
    margin-top: 20px;
  }
  .img_box {
    width: 2000px;
  }
  .laptop_img {
    width: 100%;
  }
  .login_btn_box {
    width: 240px;
    height: 56px;
    border-radius: 6px;
    display: flex;
    justify-content: center;
    align-items: center;
    margin: 0 auto;
    margin-top: 10px;
  }
  .login_btn_text {
    margin-top: 2px;
    font-size: 26px;
    font-weight: 500;
  }

</style>