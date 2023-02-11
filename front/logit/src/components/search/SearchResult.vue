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
        <div class="result-cnt">총 {{ growths.length + jobs.length + users.length }}개의 검색 결과</div>
        <v-divider></v-divider>
        <div class="growth">
          <div class="title">성장일지</div>
          <div></div>
          <div v-for="growth in growths" :key="growth.id">
            <div>{{ growth.id }}</div>
            <div style="display:flex;">
              <div>{{ growth.preStr }}</div>
              <div class="keyword">{{ growth.keyword }}</div>
              <div>{{ growth.nextStr }}</div>
            </div> 
          </div>
        </div>
        <v-divider></v-divider>
        <div class="job">
          <div class="title">취업일지</div>
          <div></div>
          <div v-for="job in jobs" :key="job.id">
            <div>{{ job.id }}</div>
            <div style="display:flex;">
              <div>{{ job.preStr }}</div>
              <div class="keyword">{{ job.keyword }}</div>
              <div>{{ job.nextStr }}</div>
            </div> 
          </div>
        </div>
        <v-divider></v-divider>
        <div class="user">
          <div class="title">사용자</div>
          <div></div>
          <div v-for="user in users" :key="user.id">
            <div>{{ user.id }}</div>
            <div style="display:flex;">
              <div>{{ user.preStr }}</div>
              <div class="keyword">{{ user.keyword }}</div>
              <div>{{ user.nextStr }}</div>
            </div> 
          </div>
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
          growths: [
              {
                  "type": "성장 과정",
                  "id": 7,
                  "preStr": "",
                  "keyword": "알고",
                  "nextStr": "리즘 열심히 해야됩니다. 생각보다 더 어렵습니다. ",
                  "userEmail": null,
                  "userName": null,
                  "userProfile": null,
                  "long": false
              },
              {
                  "type": "성장 과정",
                  "id": 8,
                  "preStr": "를 했습니다. ",
                  "keyword": "알고",
                  "nextStr": "리즘은 내일 더 열심",
                  "userEmail": null,
                  "userName": null,
                  "userProfile": null,
                  "long": false
              },
              {
                  "type": "성장 과정",
                  "id": 9,
                  "preStr": "를 했습니다. ",
                  "keyword": "알고",
                  "nextStr": "리즘은 내일 더 열심",
                  "userEmail": null,
                  "userName": null,
                  "userProfile": null,
                  "long": false
              }
          ],
          jobs: [
              {
                  "type": "취업 여정 기업명",
                  "id": 2,
                  "preStr": "",
                  "keyword": "알고",
                  "nextStr": "리즘 회사",
                  "userEmail": null,
                  "userName": null,
                  "userProfile": null,
                  "long": false
              }
          ],
          users: [
              {
                  "type": "회원 이름",
                  "id": 5,
                  "preStr": "",
                  "keyword": "알고",
                  "nextStr": "리즘러버",
                  "userEmail": "love_algorithm@naver.com",
                  "userName": "알고리즘러버",
                  "userProfile": "1",
                  "long": false
              }
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
      width: 1000px;
      
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

   hr {
    margin-bottom: 10px;
   }

   .title {
    /* font-size: 20px; */
    margin-bottom: 10px;
   }

   .keyword {
    color: #FF0A54;
   }
  </style>