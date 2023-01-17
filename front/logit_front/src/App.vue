<template>
  <img class="logit_logo" alt="logit_logo" src="..\src\assets\images\logit_logo_text.png">
  <HelloWorld msg="LOGIT에 오신걸 환영합니다."/>
</template>

<script>
import HelloWorld from './components/HelloWorld.vue'

export default {
  name: 'App',
  components: {
    HelloWorld
  }
}
</script>

<style>

<!-- 전체 기본 폰트 AppleSDGothic 적용-->
#app {
  font-family: 'AppleSDGothicNeo', Helvetica, Arial, sans-serif;
}

@font-face {
  font-family:'AppleSDGothicNeo';
  src: url('assets/fonts/AppleSDGothicNeoH.ttf') format('truetype');
  font-weight: 400;
}
@font-face {
  font-family:'AppleSDGothicNeo';
  src: url('assets/fonts/AppleSDGothicNeoL.ttf') format('truetype');
  font-weight: 400;
}
@font-face {
  font-family:'AppleSDGothicNeo';
  src: url('assets/fonts/AppleSDGothicNeoM.ttf') format('truetype');
  font-weight: 400;
}
@font-face {
  font-family:'AppleSDGothicNeo';
  src: url('assets/fonts/AppleSDGothicNeoUL.ttf') format('truetype');
  font-weight: 400;
}

<!-- 앱 전체 기본 세팅-->

#app {
  margin: 0;
  padding: 0;
}
.logit_logo {
  width: 140px;
}
  

</style>
