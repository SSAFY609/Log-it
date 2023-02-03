<template>
   <div class="container">
      <div class="search_big_box">
         <div class="v_search_box lay3">
            <v-autocomplete class="search_input_text" 
            :items=state.SearchList
            autofocus
            clearable
            variant="solo"
            prefix
            
            ></v-autocomplete>
         </div>   
      </div>
   </div>
  </template>
  
  <script>
  export default {
     name: 'SearchView',
   
     setup() {
      
      const state = {
         SearchList: [
        "알고리즘",
        "알고리즘A형",
        "알고리즘B형",
        "알고리즘IM형",
        "관통프로젝트",
        "알고리즘A동생",
        "알고리즘B동생",
        "알고리즘A누나",
        "알고리즘B누나",
        "이성훈",
        "공진호",
        "김설희",
        "김나현",
        "오하늘",
        "최강혁",
        "김신일",
      ]
      }
      return {
         state,
      }
     }     
  
   }
  </script>
  
  <style scoped>
   .container {
      display: flex;
      flex-direction: column;
      align-items: center;
   }

   .search_big_box {
      margin-top: 240px;
      width: 700px;
      height: 60px;
      background-color: rgb(226, 226, 226);
      border-radius: 8px;
      display: flex;
      align-items: center;
   }
   .search_icon {
      margin-left: 10px;
      font-size: 30px;
   }
   .v_search_box {
      width: 600px;
      height: 50px;
   }
  
  </style>