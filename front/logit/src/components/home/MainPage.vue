<template>
  <div class="container">
    <div class="discription_box lay2">
      <div class="logo_box">
        <v-img class="logo_img"
               :src="require('../../assets/images/logit_logo_text.png')"

        />
      </div>
      <div>
        <div v-if="state.loginUser.name">
          <div style="height: 150px">
          <h1 class="text"></h1>
          <h1 class="text2"></h1>
          </div>
          <p>진행중인 이벤트와 취업여정을 기록하면서 달라진 나의 모습을 발견하세요.</p>
          <router-link :to="{name: 'UserLogin'}" class="login_btn_box b_main btn_hover">
            <div class="login_btn_text f_white">시작하기</div>
          </router-link>
        </div>
        <div v-else>
          <h1>{{ state.loginUser.name }} 님 환영합니다.</h1>
          <h1>지금 바로 기록해보세요.</h1>
          <p>진행중인 이벤트와 취업여정을 기록하면서 달라진 나의 모습을 발견하세요.</p>
          <router-link :to="{name: 'FirstTimeline'}" class="login_btn_box b_main btn_hover">
            <div class="login_btn_text f_white">시작하기</div>
          </router-link>
        </div>
      </div>
      <div class="img_box lay1">
        <v-img class="laptop_img"
               :src="require('../../assets/images/laptop02.png')"
        />
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex';

export default {
    name: 'MainPage',
    setup() {
      const state = {
        loginUser: {name: '이성훈'},
      }
      return {
        state,
      }
  },
    computed: { 
      ...mapState(['loginUser'])
  },
  methods: {
    writeTitle(className, letters, s) {
      const $text = document.querySelector(className);

// 글자 입력 속도
const speed = 100;

// 타이핑 효과
const typing = async () => {  
  const letter = letters.split("");
  
  while (letter.length) {
    await wait(speed);
    $text.innerHTML += letter.shift(); 
  }
  
  // 잠시 대기
  await wait(800)
  
}

// 딜레이 기능 ( 마이크로초 )
function wait(ms) {
  return new Promise(res => setTimeout(res, ms))
}

// 초기 실행
setTimeout(typing, s);
    }
  },
  created() {
    console.log(this.$store.state.loginUser);
    console.log(this.loginUser);
    },
  mounted(){
    this.writeTitle('.text', '당신의 새로운 여정을', 1500)
    this.writeTitle('.text2', '매일 기록해 보세요', 2600)
  }
    
    

    
}
</script>

<style scoped>
.texe::after {
  content: '';
  margin-left: .4rem;
  border-right: 2px solid #777;
  animation: cursor .9s infinite steps(2);
}

@keyframes cursor {
  from { border-right: 2px solid #222; }
  to { border-right: 2px solid #777; }
}

@import url("https://cdn.jsdelivr.net/gh/orioncactus/pretendard/dist/web/static/pretendard.css");


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