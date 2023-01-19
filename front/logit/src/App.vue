<template>
  <v-app class="first">
    <div class="app_container">
      <TopNavbar class="TopNavbar_box" />
      <SideNavbar class="SideNavbar_box"/>
      <div class="SideNavbar_space"></div>
      <router-view class="router_view_container"></router-view>
    </div>

  </v-app>
</template>

<script>

// Components
import TopNavbar from './components/common/TopNavbar.vue';
import SideNavbar from '@/components/common/SideNavbar.vue';

export default {
  name: 'App',
  components: {
    TopNavbar,
    SideNavbar,
  },
  
  methods: {

  },

  data: () => ({

  }),


}
</script>
<style>

/* 아웃라인 보고싶지 않으면 주석처리 */
  
  .lay1 {
    outline: 1px dashed crimson;
  }
  .lay2 {
    outline: 1px dashed blueviolet;
  }
  .lay3 {
    outline: 1px dashed green;
  }


  .first {
    font-family: AppleL, AppleM, AppleB, AppleH;
    height: 100%;
  }
  /* 아래로 내려갈수록 두꺼운 글씨체 */
  @font-face {
  font-family:'AppleL';
  src: url('assets/fonts/AppleSDGothicNeoL.ttf') format('truetype');
  }
  @font-face {
  font-family:'AppleM';
  src: url('assets/fonts/AppleSDGothicNeoM.ttf') format('truetype');
  }
  @font-face {
  font-family:'AppleB';
  src: url('assets/fonts/AppleSDGothicNeoB.ttf') format('truetype');
  }
  @font-face {
  font-family:'AppleH';
  src: url('assets/fonts/AppleSDGothicNeoH.ttf') format('truetype');
  }
  h1 {
    font-family: AppleH;
  }
  body {
    margin: 0;
    padding: 0;
  }
  /* 폰트 컬러*/
  .f_white {
    color: white !important;
  }
  .f_black {
    color: #191919 !important;
  }
  .f_lightgray {
    color: #DFDFDF !important;
  }
  .f_darkgray {
    color: #464646 !important;
  }
  .f_main {
    color: #FF0A54 !important;
  }
  .f_sub {
    color: #FE91BD !important;
  }

  /* 배경 컬러*/
  .b_main {
    background-color: #FF0A54 !important;
  }
  .b_sub {
    background-color: #FE91BD !important;
  }
  .b_white {
    background-color: white !important;
  }
  .b_black {
    background-color: #191919 !important;
  }
  .b_lightgray {
    background-color: #DFDFDF !important;
  }
  .b_darkgray {
    background-color: #464646 !important;
  }
  .b_transparentwhite {
    background-color: rgba(250,250,250,1)!important;
  }
  .b_transparentgray {
    background-color: rgba(200,200,200,0.3)!important;
  }



  .TopNavbar_box {
    width: 100%;
    height: 60px;
  }
  .SideNavbar_box {
    width: 240px;
    height: 100%;
    left: 0px;
  }
  .SideNavbar_space {
    width: 240px;
    height: 100%;
    margin-left: 20px;
  }
  .app_container {
    width: 100%;
    height: 100%;
    display: flex;
  }
  .router_view_container {
    width: 100%;
    height: 100%;
  }
  .nosee {
    display: none;
  }
  .hover_cursor:hover {
    cursor: pointer;
  }

  


</style>
