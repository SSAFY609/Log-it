 <template>
  <div class="container">
    <div class="job_box_area lay3">
      <div class="header_area lay2">
        <h1>{{ datas.name }} 취업 여정</h1>
        <span v-if="datas.endDate">{{ datas.startDate}} ~ {{ datas.endDate }}</span>  
        <span v-else>{{ datas.startDate}} ~ 진행중</span>
      </div>
      <div class="tab_area lay3">
        <div v-for="i in datas.category" :key="i">
          <div class="tab_item_box f_main">{{ i }}</div>
        </div>
        <div class="add_btn f_main">+전형 추가</div>
      </div>






























      <!-- <v-card color="basil" class="top_area">
        <v-card-title class="text-center justify-center py-4 title_area">
          <h1 class="font-weight-bold text-h2 text-basil">
          {{ datas.name}} 취업여정
          </h1>
          <div> 2022.01.02 ~ 진행중</div>
        </v-card-title>

    <v-tabs
      v-model="tab"
      bg-color="transparent"
      color="basil"
      grow
    >
      <v-tab
        class="tab_text_area"
        v-for="item in datas.category"
        :key="item"
        :value="item"
      >
      {{ item }}
      </v-tab>
    </v-tabs>
    <v-window v-model="tab">
      <v-window-item
        v-for="item in datas.category"
        :key="item"
        :value="item" 
      >
    <v-card
      class="job_text_area"
      color="#fff099"
      flat
    >
        <v-card-text class="job_text lay2">
          <div v-if="update">
            <div> 게시글 수정 모드</div>
            <v-btn @click="update=false"> 저장 </v-btn>
            <br>
            <div>{{ datas.details[0].question }}</div>
            <v-textarea
              class="lay3 input_item_box"
              bg-color="transparent"
              variant=null
              v-model:model-value="datas.details[0].answer"
            >{{ datas.details }}</v-textarea>
            {{ datas.details }}
            
          </div>
          <div v-else>
            <div> 게시글 읽기 모드</div>
            <v-btn @click="update=true"> 수정 </v-btn>
            <br>
            <div>{{ datas.details[0].question }}</div>
            <v-textarea
              class="lay3 input_item_box"
              bg-color="transparent"
              variant=null
              readonly
              v-model:model-value="datas.details[0].answer"
            >{{ datas.details }}</v-textarea>
            {{ datas.details }}
          </div>  
        </v-card-text>
        </v-card>
      </v-window-item>
    </v-window>
  </v-card> -->
    </div>
   </div>
 </template>
 
 <script>



 export default {
    name: 'JobProgress',

    components: {

    },

    data () {
      return {
        tab: 'Appetizers',
        items: [
          '서류전형', '코딩테스트', '기술면접',
        ],
        text: '서류전형은 어쩌구 저쩌구 더미 데이터이다!!!',
        create_content: '',
        update: false,
        datas: {
          name: "카카오",
          startDate: "2021-01-03",
          endDate: null,
          category: ["서류", "코딩테스트", "면접"],
          details: [
            {eventId: "1", eventName: "서류전형", question: "이 기업에 지원한 이유는 무엇인가요?" , answer:"돈을 많이 줍디다."},
            {eventId: "2", eventname: "코딩테스트"}
          ],
        }
      }
    },
 }
 </script>
 
 <style scoped>

 /* .input_item_box {
  height: 100px;
 }
 .title_area {
  padding-top: 34px!important;
  padding-bottom: 2px!important;
 }
 .top_area {
  border-radius: 10px;
 }

 .tab_text_area {
  font-size: 24px;  
 }

 .job_text {
    font-size: 24px;
    height: 560px;
    font-family: event;
 } */

 /* .job_text_area {
  height: 600px;
  padding: 30px;
 } */
/* Helper classes */
/* .bg-basil {
  background-color: #fff099 !important;
}

.text-basil {
  color: #191919 !important;
}


.editor_area {
  font-size: 30px;
}

.detail-form {
  height: 400px;
}
*/
.container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.job_box_area {
  margin-top: 120px;
  width: 800px;
  height: 800px;
  border-radius: 20px;
}


h1 {
  font-size: 50px;
  font-weight: 100;
}

.header_area {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-top: 20px;
}

span {
  font-size: 24px;
  font-family: appleL;  
}

.tab_area {
  height: 60px;
  display: flex;
  align-items: center;


}

.tab_item_box {
  height: 60px;
  background-color: rgb(235, 235, 235);
  border-top-left-radius: 8px;
  border-top-right-radius: 8px;
  margin-right: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0px 30px;
}

.add_btn {
  height: 60px;
  background-color: rgb(235, 235, 235);
  border-top-left-radius: 8px;
  border-top-right-radius: 8px;
  margin-right: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0px 30px;
  font-family: appleM;
}


 </style>
