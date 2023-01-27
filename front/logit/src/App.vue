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
}
</script>
<style>

@import "@/assets/style/app.css";


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


</style>
