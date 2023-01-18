<template>
  <v-app class="first">
    <div class="app_container">
      <SideNavbar class="SideNavbar_box"/>
      <TopNavbar class="TopNavbar_box" />
    </div>
      <router-view/>
    
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

  data: () => ({

  }),
}
</script>
<style>
  .lay1 {
    outline: 1px dashed crimson;
  }
  .lay2 {
    outline: 1px dashed blueviolet;
  }
  .lay3 {
    outline: 1px dashed green;
  }
  .first {
    font-family: AppleL, AppleM, AppleB, AppleH;
  }
  /* 아래로 내려갈수록 두꺼운 글씨체 */
  @font-face {
  font-family:'AppleL';
  src: url('assets/fonts/AppleSDGothicNeoL.ttf') format('truetype');
  }
  @font-face {
  font-family:'AppleM';
  src: url('assets/fonts/AppleSDGothicNeoM.ttf') format('truetype');
  }
  @font-face {
  font-family:'AppleB';
  src: url('assets/fonts/AppleSDGothicNeoB.ttf') format('truetype');
  }
  @font-face {
  font-family:'AppleH';
  src: url('assets/fonts/AppleSDGothicNeoH.ttf') format('truetype');
  }
  h1 {
    font-family: AppleH;
  }
  body {
    margin: 0;
    padding: 0;
  }

  
  .TopNavbar_box {
    width: 100%;
    height: 60px;
  }
  .SideNavbar_box {
    width: 240px;
    height: 100%;
  }
  .app_container {
    width: 100%;
    height: 100%;

    
  }
</style>
