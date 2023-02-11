<template>
  <div class="container">
    <!-- <div style="color: #ababab; font-size: 110px; margin-bottom: 20px; font-family:google; margin-top: 180px">
      Lo-gle
    </div> -->
    <div>
      <!-- <v-icon style="color:grey; margin-right: 10px; ">mdi-magnify</v-icon> -->
      <v-icon class="magnify">mdi-magnify</v-icon>
      <input class="search_input" type="text"
        placeholder="검색어를 입력하세요." >
      <div class="recommend">
        추천 검색어 : 알고리즘 | 스터디 | 코딩 | 자기소개서
      </div>
      <div class="result">
        <div class="result-cnt">총 {{ searchResult[0].length + searchResult[1].length + searchResult[2].length }}개의 검색 결과</div>
        <v-divider></v-divider>
        <div class="growth">
          <div>성장일지</div>
          <div v-for="item in searchResult[0]" :key="item.id"></div>
        </div>
        <v-divider></v-divider>
        <div class="job">
          <div>취업일지</div>
        </div>
        <v-divider></v-divider>
        <div class="user">
          <div>사용자</div>
        </div>
      </div>
    </div>
  </div>
</template>
  
  <script>
// import { mapState } from 'vuex'
  export default {
      name: 'SearchResult',
      // computed: {
      //   ...mapState('search', ['searchResult'])
      // },
      data() {
        return {
          searchResult: [
            [{
                "type": "성장 여정 이벤트",
                "id": 13,
                "content": "알고리즘",
                "userEmail": null,
                "userName": null,
                "userProfile": null
            },
            {
                "type": "성장 과정",
                "id": 15,
                "content": "알고리즘 공부 열시미 했어용",
                "userEmail": null,
                "userName": null,
                "userProfile": null
            },
            {
                "type": "성장 과정",
                "id": 20,
                "content": "저두 알고리즘 공부 열시미 했어용",
                "userEmail": null,
                "userName": null,
                "userProfile": null
            },
            {
                "type": "성장 과정",
                "id": 28,
                "content": "안녕하세요 저는 김설희입니다. 오늘은 알고리즘을 공부했",
                "userEmail": null,
                "userName": null,
                "userProfile": null
            }],
            [{
                "type": "취업 여정 기업명",
                "id": 29,
                "content": "알고리즘 회사",
                "userEmail": null,
                "userName": null,
                "userProfile": null
            }],
            []
          ],
          
        }
      }
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
    margin-top: 300px;
    transition: all 1s ease;
    width: 900px;
    height: 60px;
    background-color: #F6F6F6;
    padding-left: 18px;
    border-radius: 30px;
    display: flex;
    align-items: center;
    box-shadow: 1px 1px 10px 0.3px rgba(61, 61, 61, 0.174)
  }

  /* .search_input {
      width: 860px;
      height: 40px;
      border-radius: 30px;
      background-color: #F6F6F6;
   } */

   .magnify {
    position: relative;
    left: 5%;
    
   }

   input {
    width: 800px;
    height: 60px;
    border: solid 10px #F6F6F6;
    border-radius: 30px;
    background-color: #F6F6F6;
    margin-top: 300px;
    transition: all 1s ease;
    font-size: 18px;
    padding-left: 6%;
    box-shadow: 1px 1px 10px 0.3px rgba(61, 61, 61, 0.174)
   }
   
   input:focus {
      outline: none;
      margin-top: 60px;
      width: 1200px;
      
   }

   input:focus ~.recommend {
    display: none;
   }

   .recommend {
    font-size: 18px;
    padding-left: 40px;
    text-align: left;
    margin-top: 20px;
   }

   .result {
    width: 97%;
    margin-top: 30px;
    margin-left: 32px;
   }

   .result-cnt {
    text-align: right;
   }
  </style>