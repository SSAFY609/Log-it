<template>
   <div class="container">
      <div class="background-area" @click="modal=false"></div>
      <div class="search_big_area">
         <div class="search_big_box">
            <input class="search_input" type="text"
                   v-model="state"
                   autocomplete="off"
                   @input="filterStates"
                   placeholder="검색어를 입력하세요."
            >
            <div class="filterd_box" 
                 v-if="filteredStates && modal"
                 
                 >
               <ul>
                  <li v-for="(item, i) in filteredStates" :key="i"
                      @click="setState(item)"
                      class="hover_cursor"
                  >{{ item }}</li>
               </ul>
            </div>
         </div>
         <div class="search_btn_box b_main f_white btn_hover">검색</div>   
      </div>
   </div>
</template>
  
<script>


   export default {
      name: 'SearchView',
      
      data() {
         return {
            state: '',
            states: [
               "알고리즘",
               "알고리즘A형",
               "알고리즘B형",
               "관통프로젝트",
               "알고리즘IM형",
               "알고리즘B동생",
               "알고리즘A동생",
               "알고리즘B누나",
               "알고리즘A누나",
               "공진호",
               "이성훈",
               "김나현",
               "김설희",
               "최강혁",
               "오하늘",
               "김신일",
            ],
            filteredStates: [],
            modal: false,
         }
      },

      methods: {
         filterStates(e) {
            this.filteredStates = this.states.filter(state => {
               this.state = e.target.value
               const result = state.toLowerCase().includes(this.state.toLowerCase())
               
               
               return result
            })
            console.log(this.state.length)
               if(this.state.length > 0 && this.filteredStates.length > 0) {
                  this.modal = true
               } else {
                  this.modal = false
               }
         },

         setState(state) {
            this.state = state
            this.modal = false
         },


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

      width: 700px;
      height: 60px;
      background-color: #F6F6F6;
      border-radius: 8px;
      display: flex;
      align-items: center;
      z-index: 200;
   }
   .search_input {
      width: 660px;
      height: 40px;
      margin-left: 20px;
      background-color: #F6F6F6;
   }

   input:focus {
      outline: none;
   }

   .search_big_area {
      display: flex;
      width:870px;
      height: 70px;
      align-items: center;
      margin-top: 240px;
      justify-content: center;
      z-index: 2000;
   }
   .search_btn_box {
      width: 100px;
      height: 60px;
      margin-left: 10px;
      border-radius: 6px;
      display: flex;
      justify-content: center;
      align-items: center;
      font-size: 22px;
      z-index: 2000;
   }
   .filterd_box {
      position: absolute;
      width: 700px;
      background-color: #F6F6F6;
      top: 310px;
      border-radius: 6px;
      padding-top: 10px;
      padding-bottom: 10px;
   }
   .filterd_box li {
      padding-top: 10px;
      padding-bottom: 10px;
      padding-left: 22px;
      width: 100%;
      height: 100%;
      background-color: #F6F6F6;
      display: flex;
      align-items: center;
   }
   .background-area {
      position: absolute;
      width: 100%;
      height: 100%;
      top:0px;
      left:0px;
      z-index: 1000;
   }
  </style>