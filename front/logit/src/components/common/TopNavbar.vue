<template>
  <div class="container b_transparentwhite">
    <div class="left_box">
      <div @click="openSidebar" class="slider_hover slider_box b_lightgray"><v-icon class="silder_icon f_white">mdi-chevron-double-right</v-icon></div>
    </div>
    <div class="right_box lay3">
      <div class="search_box right_box_items hover_cursor">
        <div class="search_icon_box"><v-icon class="search_icon">mdi-magnify</v-icon></div>
        <div class="search_content_box">
          <div class="search_content">검색</div>
        </div>
      </div>
      <div class="login_btn_box right_box_items">
        <router-link :to="{name: 'UserLogin'}" class="login_btn b_main btn_hover">
          <div class="login_btn_text f_white">로그인</div>
        </router-link>
      </div>
      <div class="profile_box right_box_items"></div>
    </div>
    

  </div>
</template>

<script>

export default {
  name: 'TopNavbar',
  methods: {
    openSidebar() {
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
    z-index: 100;
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 0 10px;
    border-bottom: 0.5px solid rgba(216, 216, 216, 0.6);
    backdrop-filter: blur(10px);
  }

  .slider_box {
    width: 40px;
    height: 40px;
    border-radius: 8px;
    display: flex;
    justify-content: flex-end;
    align-items: center;
    
  }
    
  .silder_icon {
    font-size: 34px;
  }
  .left_box {
    width: 100px;
    height: 50px;
    display: flex;
    align-items: center;
  }
  .right_box {
    width: 300px;
    height: 50px;
    display: flex;
    align-items: center;
  }
  .right_box_items {
    height: 40px;
  }
  .search_box {
    width: 100px;
    height: 40px;
    display: flex;
    align-items: center;
    justify-content: center;
    border-radius: 4px;
    margin-right: 10px;
  }
  .search_box:hover {
    background-color: #efefef;
  }
  .login_btn_box {
    width: 200px;
    height: 36px;
    display: flex;
    align-items: flex-start;
    justify-content: end;
    border-left: 1.5px solid lightgray;
    
  }
  .profile_box {
    width: 230px;
    display: none;
  }
  .login_btn {
    width: 100px;
    height: 36px;
    border-radius: 6px;
    margin-right: 10px;
    display: flex;
    justify-content: center;
    align-items: center;
    

  }
  .login_btn_text {
    margin-top: 2px;
    font-size: 16px;
  }
  .search_icon_box {
    width: 36px;
    height: 36px;  
    display: flex;
    align-items: center;
    margin-left: 0px;
  }
  .search_icon {
    font-size: 26px;

  }
  .search_content {
    height: 26px;
    width: 60px;
    display: flex;
    justify-content: start;
    align-items: center;
    font-size: 20px;
    
  }
  .search_content_box {
    width: 40px;
    height: 36px;  
    display: flex;
    align-items: center;
    justify-content: center;
  }
</style>
