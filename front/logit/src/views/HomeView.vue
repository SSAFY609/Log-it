<template>
  <div class="container">
    <router-view/>
  </div>
</template>

<script>
import { defineComponent } from 'vue';



export default defineComponent({
  name: 'HomeView',

  // components: {
  //   TopNavbar,
  //   SideNavbar,
  // },
});
</script>
<style scoped>

  /* .TopNavbar_box {
    width: 100%;
    height: 100px;
  }
  .SideNavbar_box {
    display: felx;
    width: 200px;
    height: 100%;
  } */
</style>
