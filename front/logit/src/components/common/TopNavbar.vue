<template>
  <div class="container lay1">
    <div class="left_box lay2">
      <div class="slider_box lay1"><v-icon class="silder_icon">mdi-menu</v-icon></div>
    </div>
    <div class="right_box lay2">
      <div class="search_box right_box_items lay1"></div>
      <div class="login_btn_box right_box_items lay1">
        <div class="login_btn">
          <div class="login_btn_text f_white">로그인</div>
        </div>
      </div>
      <div class="profile_box right_box_items lay1"></div>
    </div>
    

  </div>
</template>

<script>

export default {
  name: 'TopNavbar',
  
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
    border-bottom: 0.5px solid rgba(216, 216, 216, 0.575);
    background-color: rgba(255,255,255,0.9);
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
  .left_box {
    width: 14%;
    height: 50px;
  }
  .right_box {
    width: 30%;
    height: 50px;
    display: flex;
  }
  .right_box_items {
    height: 50px;
  }
  .search_box {
    width: 60%;
  }
  .login_btn_box {
    width: 40%;
    display: flex;
    align-items: center;
    justify-content: end;
  }
  .profile_box {
    width: 40%;
    display: none;
  }
  .login_btn {
    width: 140px;
    height: 42px;
    background-color: #FF0A54;
    border-radius: 8px;
    margin-right: 10px;

  }
  .login_btn_text {
  }
</style>
