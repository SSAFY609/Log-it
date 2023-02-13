<template>
  <div class="container">
    <div class="discription_box lay2">
      <div class="logo_box">
        <v-img class="logo_img"
               :src="require('../../assets/images/logit_logo_text.png')"
        />
      </div>
      <div>
        <div v-if="!loginUser.name">
          <div class="writeTitle">
            <h1 class="text"></h1>
          </div>
          <p>진행중인 이벤트와 취업여정을 기록하면서 달라진 나의 모습을 발견하세요.</p>
          <router-link :to="{name: 'UserLogin'}" class="login_btn_box b_main btn_hover">
            <div class="login_btn_text f_white">시작하기</div>
          </router-link>
        </div>
        <div v-else>
          <div class="title-after-login">
            <h1>{{ loginUser.name }} 님 환영합니다.</h1>
            <h1>지금 바로 기록해보세요.</h1>
          </div>
          <p>진행중인 이벤트와 취업여정을 기록하면서 달라진 나의 모습을 발견하세요.</p>
          <router-link :to="{name: 'FirstTimeline'}" class="login_btn_box b_main btn_hover">
            <div class="login_btn_text f_white">시작하기</div>
          </router-link>
        </div>
      </div>
      <div @click="testTimeline">클릭!</div>
      <div class="img_box lay1">
        <v-img class="laptop_img"
               :src="require('../../assets/images/laptop02.png')"
        />
      </div>
      <!-- <div class="post-it">포스트잇이얌</div>
      <h1>아이고 안녕하십니까1111111</h1>
      <h1>아이고 안녕하십니까222222</h1>
      <h1>아이고 안녕하십니까3333333</h1>
      <h1>아이고 안녕하십니까444444</h1>
      <h1>아이고 안녕하십니까555555</h1> -->
    </div>
  </div>
</template>

<script>
// import router from '@/router';
import { mapState } from 'vuex';
import { onBeforeMount, onMounted, onBeforeUnmount, computed } from 'vue';
import { useStore } from 'vuex';
import { useToast } from 'vue-toastification';

export default {  
    name: 'MainPage',
    setup() {
      

      // let observe = new IntersectionObserver((e)=>{
      //   e.forEach((box)=>{
      //     if(box.isIntersecting){
      //       box.target.style.right = "-1000px";
      //       box.target.style.transform = 'rotate(0deg)';
      //     }
      //   })
      // })

      // onMounted(()=>{
      //   let postIt = document.querySelector(`.post-it`)
      //   observe.observe(postIt)
      // })
      const store = useStore()
      const toast = useToast()
      const invites = computed(()=>store.state.invites)

      const testTimeline = () => {
        store.dispatch('timeline/timelineSetting')
      }
      const loginUser = computed(()=>store.state.loginUser)

      onBeforeMount(()=>{
        if(invites.value){
          toast('알림입니다', {
            timeout: 2000,
          })
          
        }
      })

      onMounted(()=>{
        writeTitle('.text', '당신의 새로운 여정을 \n 매일 기록해 보세요', 1500)
        window.addEventListener('scroll', onScroll)
      })
      
      onBeforeUnmount(()=>{
        window.removeEventListener('scroll', onScroll);
      })

      const writeTitle = (className, letters, s) => {
      const $text = document.querySelector(className);

      // 글자 입력 속도
      const speed = 100;

      const changeLineBreak = (letter) => {
        return letter.map(text => text === "\n" ? "<br>" : text);
      }

      // 타이핑 효과
      const typing = async () => {  
        const letter = changeLineBreak(letters.split(""));
        
        while (letter.length) {
          await wait(speed);
          $text.innerHTML += letter.shift(); 
        }
        
        // 잠시 대기
        await wait(1500)
        
        // 지우는 효과
        remove();
      }
      
      // 글자 지우는 효과
      const remove = async () => {
        const letter = changeLineBreak(letters.split(""));
        
        while (letter.length) {
          await wait(speed);
          
          letter.pop();
          $text.innerHTML = letter.join(""); 
        }
        
        await wait(1000)
        // 다음 순서의 글자로 지정, 타이핑 함수 다시 실행
        typing();
      }

      // 딜레이 기능 ( 마이크로초 )
      function wait(ms) {
        return new Promise(res => setTimeout(res, ms))
      }

      // 초기 실행
      setTimeout(typing, s);
    };

    const onScroll = () => {
      const currentScrollPosition = window.pageYOffset || document.documentElement.scrollTop
      if(currentScrollPosition < 0){
        return
      }
      if (currentScrollPosition > 0 && currentScrollPosition < 500){
        const img = document.querySelector('.img_box')
        img.style.width = `${1300 + currentScrollPosition * 0.5}px`
      } 
    }

      return {
        // start,
        // state,
        loginUser,
        invites,
        testTimeline,
        writeTitle,
        onScroll,

      }
  },
    computed: { 
      ...mapState(['loginUser'])
  },

}
</script>

<style scoped>

.post-it {
  background-color: yellow;
  z-index: 100;
  height: 300px;
  width: 300px;
  position: relative;
  right: 500px;
  transition: all 1s;
}

/* h1 {
  margin: 500px 0;
  position: relative;
  z-index: 2500;
} */
  .text::after {
    content: '';
    margin-left: .4rem;
    border-right: 10px solid #ff0000;
    animation: cursor .9s infinite steps(2);
  }



  @keyframes cursor {
    from { border-right: 10px solid #ffffff; }
    to { border-right: 10px solid #ff0000; }
  }

  .writeTitle {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 250px;
  }

  .title-after-login {
    display: flex;
    align-items: center;
    flex-direction: column;
    justify-content: center;
    height: 250px;
  }

  .container {
    display:flex;
    justify-content: center;
  }
  .discription_box {
    margin-top: 70px;
    height: 2600px;
    justify-content: center;
  }
  h1 {
    text-align: center;
    font-family: appleB;
    font-size: 56px;
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
    margin-top: 50px;
    position: sticky;
    top: 70px;
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
    margin-top: 40px;
  }
  .login_btn_text {
    margin-top: 2px;
    font-size: 22px;
    font-weight: 500;
  }

</style>