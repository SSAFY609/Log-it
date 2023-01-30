<template>
  <v-app class="first">
    <div class="app_container">
      <TopNavbar class="TopNavbar_box" />
      <SideNavbar class="SideNavbar_box" />
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
  
  /* .lay1 {
    outline: 1px dashed crimson;
  }
  .lay2 {
    outline: 1px dashed blueviolet;
  }
  .lay3 {
    outline: 1px dashed green;
  } */


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
    font-family: AppleB;
  }
  body {
    margin: 0;
    padding: 0;
  }
  a {
    text-decoration: none;
  }

  /* 폰트 컬러*/
  .f_white {
    color: white ;
  }
  .f_black {
    color: #191919 ;
  }
  .f_lightgray {
    color: #DFDFDF ;
  }
  .f_darkgray {
    color: #464646 ;
  }
  .f_main {
    color: #FF0A54 ;
  }
  .f_sub {
    color: #FE91BD ;
  }
  .f_icon {
    color: #8A8A8A;
  }

  /* 배경 컬러*/
  .b_main {
    background-color: #FF0A54 ;
  }
  .b_sub {
    background-color: #FE91BD ;
  }
  .b_white {
    background-color: white ;
  }
  .b_black {
    background-color: #191919 ;
  }
  .b_lightgray {
    background-color: #DFDFDF ;
  }
  .b_darkgray {
    background-color: #464646 ;
  }
  .b_transparentwhite {
    background-color: rgba(255,255,255,0.8);
  }
  .b_transparentgray {
    background-color: #F6F6F6;
  }
  .b_lightgray_l{
    background-color: #EDEDED;
  }
  .b_kakao{
    background-color: #fae100;
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
    width: 270px;
    height: 100%;
    /* margin-left:60px; */
    /* outline: 2px dashed green; */
    /* background-color: red; */
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
  .btn_hover:hover {
    cursor:pointer;
    background-color: #c9023e;
  }
  
  .slider_hover:hover {
    cursor: pointer;
    background-color: #c7c7c7;
  }

  /* .swiper-button-prev {
  color: #ffffff;
  height: 30px;
  width: 30px;
  border-radius: 50%;
  background-color: #c4c4c4;
  left: 10% !important;
  top: 45.9%;
}

.swiper-button-prev::before{
  content: '<';
  font-size: large;
  color: #ffffff;
}

.swiper-button-next {
  height: 30px;
  width: 30px;
  border-radius: 50%;
  background-color: #c4c4c4;
  right: 10% !important;
  top: 45.9%;
}

.swiper-button-next::before{
  content: '>';
  font-size: large;
  color: #ffffff;
} */

.swiper-button-next::after,
.swiper-button-prev::after {
display: none;
}

/* 나중에 성장일지 v-chip의 배경색 바꾸기 위한 과정.....
.v-chip__underlay {
    background: red;
} */

</style>
