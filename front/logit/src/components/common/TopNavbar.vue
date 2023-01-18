<template>
  <div class="container lay1">
    <div class="left_box lay2">
      <div class="slider_box lay1"><v-icon class="silder_icon">mdi-menu</v-icon></div>
    </div>
    <div class="right_box lay2">
    
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
    width: 40%;
    height: 50px;
  }
</style>
