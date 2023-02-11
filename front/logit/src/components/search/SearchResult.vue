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
          <div v-for="growth in growths" :key="growth.id" class="result-one">
            <div v-if="growth.type == '성장 여정 이벤트'">
              <div class="eventName">
                <div>{{ growth.preStr }}</div>
                <div class="keyword">{{ growth.keyword }}</div>
                <div>{{ growth.nextStr }}</div>
              </div>
              <div class="eventDate">
                {{ growth.startDate }} ~ {{ growth.endDate }}
              </div>
            </div>
            <div v-else>
              <div class="eventName">
                {{ growth.eventName }}
              </div>
              <div class="progress">
                ...
                <div>{{ growth.preStr }}</div>
                <div class="keyword">{{ growth.keyword }}</div>
                <div>{{ growth.nextStr }}</div>
                ...
              </div> 
            </div>
          </div>
        </div>
        <div class="spacer"></div>
        <v-divider></v-divider>
        <div class="job">
          <div class="title">취업일지</div>
          <div></div>
          <div v-for="job in jobs" :key="job.id" class="result-one">
            <div v-if="job.type == '취업 여정 기업명'">
              <div class="eventName">
                <div>{{ job.preStr }}</div>
                <div class="keyword">{{ job.keyword }}</div>
                <div>{{ job.nextStr }}</div>
              </div>
              <div class="eventDate">
                {{ job.startDate }} ~ {{ job.endDate }}
              </div>
            </div>
          </div>
        </div>
        <div class="spacer"></div>
        <v-divider></v-divider>
        <div class="user">
          <div class="title">사용자</div>
          <div></div>
          <div v-for="user in users" :key="user.id" class="result-one">
            <div class="result-user">
              <div class="user-image">
                <img :src="require(`@/assets/profiles/scale (${user.userProfile}).png`)">
              </div>
              <div>
                <div class="eventName">
                  <div>{{ user.preStr }}</div>
                  <div class="keyword">{{ user.keyword }}</div>
                  <div>{{ user.nextStr }}</div>
                </div>
                <div class="user-email">{{ user.userEmail }}</div>
              </div>
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
                  "type": "성장 여정 이벤트",
                  "id": 12,
                  "preStr": "",
                  "keyword": "알고",
                  "nextStr": "리즘 스터디",
                  "userEmail": null,
                  "userName": null,
                  "userProfile": null,
                  "startDate": "2023-02-15",
                  "endDate": "2023-02-20",
                  "eventName": null,
                  "long": false
              },
              {
                  "type": "성장 과정",
                  "id": 14,
                  "preStr": "",
                  "keyword": "알고",
                  "nextStr": "리즘 공부하고 싶지 않아서 안했습니다. 다음",
                  "userEmail": null,
                  "userName": null,
                  "userProfile": null,
                  "startDate": null,
                  "endDate": null,
                  "eventName": "알고리즘 스터디",
                  "long": true
              },
              {
                  "type": "성장 과정",
                  "id": 15,
                  "preStr": ". 전 그냥 ",
                  "keyword": "알고",
                  "nextStr": "리즘이나 풀게요ㅜㅜ",
                  "userEmail": null,
                  "userName": null,
                  "userProfile": null,
                  "startDate": null,
                  "endDate": null,
                  "eventName": "cs 스터디",
                  "long": true
              }
          ],
          jobs: [
            {
                "type": "취업 여정 기업명",
                "id": 16,
                "preStr": "",
                "keyword": "알고",
                "nextStr": "리즘 회사",
                "userEmail": null,
                "userName": null,
                "userProfile": null,
                "startDate": null,
                "endDate": null,
                "eventName": null,
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
                  "startDate": null,
                  "endDate": null,
                  "eventName": null,
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
    display: none;
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
    margin-bottom: 20px;
   }

   .keyword {
    color: #FF0A54;
   }

   .result-one {
    margin-bottom: 20px;
   }

   .eventName {
    font-size: 24px;
    font-family: appleM;
    display: flex;
    cursor: pointer;
   }

   .eventName:hover {
    font-family: appleB;
   }

   .eventDate {
    margin-left: 10px;
   }

   .progress {
    display: flex;
    margin-left: 10px;
   }

   .result-user {
    display: flex;
   }

   .user-image img{
    height: 60px;
    width: 60px;
    margin-right: 20px;
   }

   .spacer {
    height: 30px;
   }
  </style>