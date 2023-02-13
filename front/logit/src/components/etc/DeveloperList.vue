<template>
  <div class="dev-box">
    <div class="open-box">
      <img :src="require(`@/assets/images/logit_logo_text.png`)" class="logo">
      <div class="title">Log-it의 오픈 소스를 만나보세요</div>
      <img :src="require(`@/assets/images/오픈 소스 메인.png`)" class="main-image">
      <div class="typing-position">
        <div class="typing"></div>      
      </div>
      <img :src="require('@/assets/images/scroll.gif')" class="scroll">
    </div>
    <div class="timeline">
      <div class="timeline-title">Timeline</div>
      <div class="timeline-subtitle">Log-it의 타임라인 오픈소스를 소개합니다.</div>
      <div class="timeline-subtitle">일정을 등록하고, 스와이프 기능을 활용해 일정을 한눈에 보고 관리해보세요.</div>
      <img :src="require('@/assets/images/오픈 소스 타임라인2.png')" class="timeline-image">
      <router-link :to="{name: 'OpenSource'}" class="demo">데모 페이지 바로가기 &gt;</router-link>
    </div>
    <img :src="require('@/assets/images/scroll.gif')" class="scroll2">
  </div>
</template>

<script>
export default {
    name: 'DeveloperList',
    mounted() {
      
      const $text = document.querySelector(".typing");
      // 글자 입력 속도
      const speed = 100;
      const letters = "/* timeline */"

      // 타이핑 효과
      const typing = async () => {  
      const letter = letters.split("");
      
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
      const letter = letters.split("");
      
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
      setTimeout(typing, 2000);
    }
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Cutive+Mono&display=swap');

.dev-box {
  width: 100%;
  text-align: center;
}
.open-box {
  display: flex;
  width: 100%;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.logo {
  margin-top: -50px;
  height: 100px;
  width: auto;
}

.title {
  font-family: appleB;
  font-size: 50px;
}

.typing {
  z-index: 100;
  font-size: 20px;
  color: rgba(79, 230, 91, 0.879);
  font-family: 'Cutive Mono', monospace;
  text-align: left;
}

.typing::after {
    content: '';
    margin-left: .4rem;
    border-right: 16px solid #ffffffb6;
    animation: cursor .9s infinite steps(2);
  }

  @keyframes cursor {
    from { border-right: 16px solid #3e3e3e; }
    to { border-right: 16px solid #ffffffb6; }
  }

.typing-position {
  text-align: left;
  position: relative;
  width: 270px;
  left: -214px;
  top: -248px;
}

.main-image {
  height: 600px;
  width: 1100px;
}

.scroll {
  position: relative;
  top: -80px;
  height: 160px;
  width: 160px;
}
.scroll2 {
  /* position: relative; */
  /* top: -80px; */
  height: 160px;
  width: 160px;
}

.timeline {
  display: flex;
  flex-direction: column;
  align-items: center;
  /* justify-content: center; */
  /* padding-top: 100px; */
  height: 750px;
  width: 100%;
  background: rgb(242, 242, 242);
}

.timeline-title {
  margin-top: 100px;
  margin-bottom: 20px;
  font-size: 50px;
  font-family: appleB;
}

.timeline-subtitle {
  font-size: 24px;
  margin-bottom: 10px;
}
.timeline-image {
  margin-top: 100px;
  margin-bottom: 80px;
  width: 1000px;
  height: auto;
}

.demo {
  font-size: 20px;
  color: #FF0A54;
  position: relative;
  left: 500px;
}

.demo:hover {
  animation: twinkle 0.5s infinite;
  font-family: appleM;
  font-size: 22px;
}

@keyframes twinkle {
    from { color: #FF0A54; }
    to { color:#ff7ba2; }
  }

</style>