<template>
  <div class="container b_transparentwhite">
    <div class="left_box">
      <div @click="openSidebar" class="slider_box b_lightgray hover_cursor"><v-icon class="silder_icon f_white">mdi-chevron-double-right</v-icon></div>
    </div>
    <div class="right_box">
      <div class="search_box right_box_items">
        <div class="search_icon_box hover_cursor"><v-icon class="search_icon">mdi-magnify</v-icon></div>
        <div class="search_content_box hover_cursor">
          <div class="search_content">검색</div>
        </div>
      </div>
      <div class="login_btn_box righ  t_box_items">
        <div class="login_btn b_main hover_cursor">
          <div class="login_btn_text f_white">로그인</div>
        </div>
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
    justify-content: center;
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
    width: 600px;
    height: 50px;
    display: flex;
  }
  .right_box_items {
    height: 50px;
  }
  .search_box {
    width: 70%;
    display: flex;
    align-items: center;
    justify-content: end;
  }
  .login_btn_box {
    width: 230px;
    display: flex;
    align-items: center;
    justify-content: end;
  }
  .profile_box {
    width: 230px;
    display: none;
  }
  .login_btn {
    width: 120px;
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
    justify-content: center;
  }
  .search_icon {
    font-size: 26px;
  }
  .search_content {
    height: 26px;
    width: 100px;
    display: flex;
    justify-content: start;
    align-items: center;
    font-size: 20px;
    border-right: 1.5px solid lightgray;
  }
  .search_content_box {
    height: 36px;  
    display: flex;
    align-items: center;
    justify-content: center;
  }
</style>
