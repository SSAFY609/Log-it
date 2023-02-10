<template>
  <div class="container">
    <div style="color: #ababab; font-size: 110px; margin-bottom: 20px; font-family:google; margin-top: 180px">
      Lo-gle
    </div>
    <div class="search-box">
      <v-icon style="color:grey; margin-right: 10px; ">mdi-magnify</v-icon>
      <input class="search_input" type="text"
        placeholder="검색어를 입력하세요." >
    </div>
  </div>
</template>
  
  <script>
  
  export default {
      name: 'SearchResult',
  }
  </script>
  
  <style scoped>
  .container {
    width: 100%;
    height: 100%;
    margin-top: 100px;
    display: flex;
    flex-direction: column;
    /* flex-direction: row; */
    /* justify-content: center; */
  
  }
  .search-box {
    width: 900px;
    height: 60px;
    background-color: #F6F6F6;
    padding-left: 18px;
    border-radius: 30px;
    display: flex;
    align-items: center;
    box-shadow: 1px 1px 10px 0.3px rgba(61, 61, 61, 0.174)
  }

  .search_input {
      width: 860px;
      height: 40px;
      border-radius: 30px;
      background-color: #F6F6F6;
      /* margin-left: 15px; */
   }

   
   input:focus {
      outline: none;
   }

  </style>