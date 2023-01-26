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

  


</style>
