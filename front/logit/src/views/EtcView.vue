<template>
   <div class="container">
      <router-view></router-view>
   </div>
</template>
  
  <script>
  export default {
     name: 'EtcView',
  }
  </script>
  
  <style scoped>
  .container {
   display: flex;
   margin-top: 150px;
   justify-content: center;
  }
  </style>