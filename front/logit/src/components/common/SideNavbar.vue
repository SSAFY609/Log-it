<template>
    <div class="container lay2 b_lightwhite">
      <div class="logo_box lay3">
        <v-img class="logo_img"
      :src="require('../../assets/images/logit_logo_text.png')"
      height="110"
        />
        <div class="slider_box lay1"><v-icon class="silder_icon">mdi-chevron-double-left</v-icon></div>
      </div>
      
    </div>
  </template>
  
  <script>
  
  export default {
    name: 'SideNavbar',
  }
  </script>
  <style scoped>
    .container {
      position: absolute;
      z-index: 200;
    }
    .logo_box {
      width: 220px;
      height: 50px;
      margin-left: 6px;
      margin-top: 5px;
      display: flex;
    }
    .slider_box {
      width: 50px;
      height: 50px;
      background-color: #dfdfdf;
      border-radius: 8px;
      display: flex;
      justify-content: center;
      align-items: center;

      
    }
    .silder_icon {
      font-size: 40px;
      color: grey;
    }

  </style>
  