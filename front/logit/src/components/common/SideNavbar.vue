<template>
    <div class="container b_transparentwhite">
      <div class="logo_box hover_cursor">
        <v-img class="logo_img"
      :src="require('../../assets/images/logit_logo_text.png')"
      height="110"
        />
        <div @click="closeSidebar" class="slider_box b_lightgray hover_cursor"><v-icon class="silder_icon f_white">mdi-chevron-double-left</v-icon></div>
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
      font-size: 40px;
    }

  </style>
  