<template>
  <v-app class="first">
    <div class="app_container">
      <SideNavbar class="SideNavbar_box"/>
      <TopNavbar class="TopNavbar_box" />
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
    color: white;
  }
  .f_black {
    color: #191919;
  }
  .f_lightgray {
    color: #A1A1A0;
  }
  .f_darkgray {
    color: #464646;
  }
  .f_main {
    color: #FF0A54;
  }
  .f_sub {
    color: #FE91BD;
  }
  .f_green {
    color: green;
  }

  /* 배경 컬러*/
  .b_main {
    background-color: #FF0A54;
  }
  .b_sub {
    background-color: #FE91BD;
  }
  .b_white {
    background-color: white;
  }
  .b_black {
    background-color: #191919;
  }
  .b_lightgray {
    background-color: #A1A1A0;
  }
  .b_darkgray {
    background-color: #464646;
  }
  .b_lightwhite {
    background-color: #FBFBFA;
  }
  .b_lightgray_l{
    background-color: #EDEDED;
  }



  .TopNavbar_box {
    width: 100%;
    height: 60px;
  }
  .SideNavbar_box {
    width: 240px;
    height: 100%;
  }
  .app_container {
    width: 100%;
    height: 100%;
  }
  .router_view_container {
    height: 100%;
  }

  .dark_mode_btn {
    margin-top: 600px;
    margin-left: 500px;
  }
  


</style>
