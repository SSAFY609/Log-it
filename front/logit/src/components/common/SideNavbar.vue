<template>
    <div class="container b_transparentgray">
      <div class="logo_box hover_cursor">
        <v-img class="logo_img"
      :src="require('../../assets/images/logit_logo_text.png')"
        />
        <div @click="closeSidebar" class="slider_box b_lightgray hover_cursor"><v-icon class="silder_icon f_white">mdi-chevron-double-left</v-icon></div>
      </div>

      <!-- 사이드바 메뉴 영역-->
      <div class="menu_container lay1">
        <div class="menu_item_box lay2 hover_cursor">
          <div class="menu_icon_box lay3">
            <v-icon class="menu_icon f_darkgray">mdi-layers</v-icon>  
          </div>
          <div class="menu_text_box f_darkgray lay3">홈</div>
        </div>
        <div class="menu_item_box lay2 hover_cursor">
          <div class="menu_icon_box lay3">
            <v-icon class="menu_icon f_darkgray">mdi-magnify</v-icon>  
          </div>
          <div class="menu_text_box f_darkgray lay3">검색</div>
        </div>
        <div class="menu_item_box lay2 hover_cursor">
          <div class="menu_icon_box lay3">
            <v-icon class="menu_icon f_darkgray">mdi-list-status</v-icon>  
          </div>
          <div class="menu_text_box f_darkgray lay3">성장일지</div>
        </div>
        <div class="menu_item_box lay2 hover_cursor">
          <div class="menu_icon_box lay3">
            <v-icon class="menu_icon f_darkgray">mdi-briefcase</v-icon>  
          </div>
          <div class="menu_text_box f_darkgray lay3">취업일지</div>
        </div>
        <div class="menu_item_box lay2 hover_cursor">
          <div class="menu_icon_box lay3">
            <v-icon class="menu_icon f_darkgray">mdi-account-circle</v-icon>  
          </div>
          <div class="menu_text_box f_darkgray lay3">프로필</div>
        </div>
        <div class="menu_item_box lay2 hover_cursor">
          <div class="menu_icon_box lay3">
            <v-icon class="menu_icon f_darkgray">mdi-poll</v-icon>  
          </div>
          <div class="menu_text_box f_darkgray lay3">통계</div>
        </div>
      </div>


      <!-- 하단 옵션 메뉴 -->

      <div class="option_menu lay3">
        <div class="menu_item_box2 lay2 hover_cursor">
          <div class="item_container lay1">
            <div class="menu_icon_box2 lay3">
              <v-icon class="menu_icon2 f_darkgray">mdi-cog</v-icon>  
            </div>
            <div class="menu_text_box2 f_darkgray lay3">설정</div>
          </div>
        </div>

        <div class="menu_item_box2 lay2 hover_cursor">
          <div class="item_container lay1">
            <div class="menu_icon_box2 lay3">
              <v-icon class="menu_icon2 f_darkgray">mdi-xml</v-icon>  
            </div>
          <div class="menu_text_box2 f_darkgray lay3">Developer</div>
        </div>
      </div>
      </div>
    </div>
  </template>
  
  <script>
  
  export default {
    name: 'SideNavbar',
    methods: {

      closeSidebar() {
        const eventTarget1 = document.querySelector('.SideNavbar_box')
        const eventTarget2 = document.querySelector('.SideNavbar_space')

        eventTarget1.classList.toggle('nosee')
        eventTarget2.classList.toggle('nosee')
      }
    }
  }
  </script>
  <style scoped>
    .container {
      position: fixed;
      z-index: 200;
      border-right: 1px solid #F5F5F4;
    }
    .logo_box {
      width: 220px;
      height: 50px;
      margin-left: 6px;
      margin-top: 5px;
      display: flex;
        
      align-items: center;
    }
    .slider_box {
      width: 40px;
      height: 40px;
      border-radius: 8px;
      display: flex;
      justify-content: center;
      align-items: center;

      
    }
    .silder_icon {
      font-size: 36px;
    }
    .menu_container {
      margin-top: 50px;
    }
    .menu_item_box {
      display: flex;
      height: 70px;
      align-items: center;
    }
    .menu_icon_box {
      margin-left: 26px;
      width: 30px;
      height: 30px;
    }
    .menu_icon {
      font-size: 30px;
    }
    .menu_text_box {
      width: 140px;
      height: 30px;
      display: flex;
      padding-top: 2px;
      align-items: center;
      margin-left: 20px;
      font-size: 24px;
      font-weight: 400;
    }
    .menu_item_box2 {
      display: flex;
      height: 60px;
      align-items: center;  
    }
    .menu_icon_box2 {
      margin-left: 26px;
      width: 24px;
      height: 24px;
    }
    .menu_icon2 {
      font-size: 24px;
    }
    .menu_text_box2 {
      width: 140px;
      height: 30px;
      display: flex;
      padding-top: 2px;
      align-items: center;
      margin-left: 20px;
      font-size: 20px;
      font-weight: 400;
    }
    .menu_item_box:hover {
      background-color: #efefef;
    }
    .menu_item_box:hover .menu_icon {
      font-weight: 900;
      color: #191919;
    }
    .menu_item_box:hover .menu_text_box {
      color: #191919;
      font-weight: 900;
    }
    .option_menu {
      margin-top: 560px;
      height: 240px;
    }
    .item_container {
      display: flex;
    }
    
  </style>
  