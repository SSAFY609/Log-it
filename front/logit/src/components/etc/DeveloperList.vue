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
    <div class="code-block">
      <div class="code-block-title">오픈 소스 사용법</div>
      <div class="code-block-subtitle">events에 나만의 event를 넣어 사용해 주세요.</div>
      <div class="code">
        <div class="line"></div>
        <div style="margin-top: 24px">
          <div class="tag">&lt;script&gt;</div>
          <div>...</div>
          <div>export default &#123;</div>
          <div>setup &#40;&#41; &#123;</div>
          <div class="inner-1">const state = reactive &#40;&#123;</div>
          <div class="inner-2"><div class="pink">events</div> : [</div>
          <div class="inner-3">&#123;<div class="code-hv" @click="scroll('go_title')">title</div>: '이벤트 1',<div class="code-hv" @click="scroll('st')">startDate</div>: '2023-02-13', <div class="code-hv" @click="scroll('ed')">endDate</div>:'2023-03-01'&#125;</div>
          <div class="inner-3">&#123;<div class="code-hv" @click="scroll('go_title')">title</div>: '이벤트 2',<div class="code-hv" @click="scroll('st')">startDate</div>: '2023-02-16', <div class="code-hv" @click="scroll('ed')">endDate</div>:'2023-03-10'&#125;</div>
          <div class="inner-3">&#123;<div class="code-hv" @click="scroll('go_title')">title</div>: '이벤트 3',<div class="code-hv" @click="scroll('st')">startDate</div>: '2023-02-17', <div class="code-hv" @click="scroll('ed')">endDate</div>:'2023-03-05'&#125;</div>
          <div class="inner-3">&#123;<div class="code-hv" @click="scroll('go_title')">title</div>: '이벤트 4',<div class="code-hv" @click="scroll('st')">startDate</div>: '2023-02-01', <div class="code-hv" @click="scroll('ed')">endDate</div>:'2023-02-13'&#125;</div>
          <div class="inner-2">]</div>
          <div class="inner-1">&#125;&#41;</div>
          <div>&#125;</div>
          <div>&#125;</div>
          <div>...</div>
          <div class="tag">&lt;/script&gt;</div>
        </div>
      </div>
      <a href="https://github.com/SSAFY609/Logit-TimeLine" class="demo github">코드 전문 보러가기 &gt;</a>
    </div>
    <div class="usage">
      <div class="params">변수 설명</div>
      <div class="usage-box" id="go_title">
        <div class="usage-title">title</div>
        <div class="usage-content">이벤트의 제목을 뜻하는 변수입니다.</div>
      </div>
      <div class="usage-box" id="st">
        <div class="usage-title">startDate</div>
        <div class="usage-content">이벤트의 시작 날짜를 뜻하는 변수입니다. &#40; 'yyyy-MM-dd' 의 형태로 입력하세요. &#41;</div>
      </div>
      <div class="usage-box" id="ed">
        <div class="usage-title">endDate</div>
        <div class="usage-content">이벤트의 종료 날짜를 뜻하는 변수입니다. &#40; 'yyyy-MM-dd' 의 형태로 입력하세요. &#41;</div>
      </div>
    </div>
    <div>
       
    </div>
  </div>
</template>

<script>
export default {
    name: 'DeveloperList',
    methods: {
      scroll(target){
          document.getElementById(target).scrollIntoView({behavior: "smooth", block: 'center'})
      },
    },
    mounted() {
      window.scrollTo(0,0)

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
  color: #191919;
  position: relative;
  left: 400px;
  top: -300px;
}
.github {
  color: #191919;
  left: 560px;
  top: -770px;
  font-family: appleL;
  font-size: 20px;
}

.demo:hover {
  font-family: appleM;
  font-size: 22px;
  color:#ff004c;
}

.code-block-title {
  font-size: 50px;
  font-family: appleB;
}

.code-block-subtitle {
  font-size: 20px;
  margin-bottom: 40px;
}

.code-block {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.code {
  background: #222222e5;
  display: flex;
  padding: 30px;
  padding-left: 60px;
  font-family: consola;
  font-size: 22px;
  color: rgb(215, 215, 215);
  height: 640px;
  border-radius: 20px;
  width: 80%;
  text-align: left;
  margin-bottom: 80px;
}
.line {
  height: 580px;
  margin-right: 70px;
  border-right: solid 2px rgba(255, 255, 255, 0.177);
}

.inner-1 {
  display: flex;
  margin-left: 40px;
}
.inner-2 {
  display: flex;
  margin-left: 80px;
}
.inner-3 {
  display: flex;
  margin-left: 120px;
}

.tag {
  color: rgb(0, 234, 255);
}

.pink {
  color: #fff200;
}

.code-hv {
  cursor: pointer;
  margin: 0 10px;
}

.code-hv:hover {
  color: #26ff00;
  /* font-family: consolaB; */
}

.params {
  font-size: 50px;
  font-family: appleB;
  margin-bottom: 40px;
}

.usage {
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: left;
  padding: 50px 0;
}

.usage-box {
  background-color: #ededed;
  width: 80%;
  height: 60px;
  border-radius: 8px;
  display: flex;
  align-items: center;
  margin-bottom: 20px;
}

.usage-title{
  font-size: 36px;
  font-family: appleM;
  margin-left: 40px;
  width: 160px;
}

.usage-title{
  font-size: 26px;
}
</style>
