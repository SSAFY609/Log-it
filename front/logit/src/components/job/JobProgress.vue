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
          <div class="tab_item_box f_main selected_item hover_cursor">{{ i }}</div>
        </div>
        <div class="add_btn f_main hover_cursor">+전형 추가</div>
      </div>
      <div class="contents_area">
        <div v-if="clicked=='서류'" class="contents_box lay2">
          {{datas.details[0].answer}}
          <div class="q_input_area">
            <div class="q_input_box">
              <input class="q_text" type="text" placeholder="질문을 추가하세요." autofocus>
            </div>
            <div class="a_input_box">
              <textarea  class="a_text" placeholder="답변을 작성하세요."></textarea>
            </div>
            
          </div>
          <div class="q_btn">+문답추가</div>
          서류전형
        </div>
        <div v-if="clicked=='코테'" class="contents_box lay2">
          코딩테스트
        </div>
        <div v-if="clicked=='면접'" class="contents_box lay2">
          면접전형
        </div>
        <div v-if="clicked=='추가'" class="contents_box lay2">
          전형추가
        </div>
      </div>




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
        data: "데이터",
        text: '서류전형은 어쩌구 저쩌구 더미 데이터이다!!!',
        create_content: '',
        update: false,
        clicked: '서류',
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
  height: 40px;
  display: flex;
  align-items: center;
  margin-top: 20px;
}

.tab_item_box {
  height: 40px;
  background-color: rgb(235, 235, 235);
  border-radius: 6px;
  margin-right: 6px;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0px 30px;
}

.add_btn {
  height: 40px;
  background-color: rgb(235, 235, 235);
  border-radius: 6px;
  margin-right: 6px;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0px 30px;
  font-family: appleL;
}
.selected_item {
  background-color: #FF0A54 ;
  color: white;
  z-index: 1000;
}
.contents_area {
  margin-top: 16px;
  width: 800px;
  height: 590px;
  background-color: rgb(255, 248, 254);
  border-radius: 6px;
  display: flex;
  flex-direction: column;
  align-items: center;

}
.contents_box {
  margin-top: 30px;
  width: 700px;
  height: 500px;
}
.q_btn {
  width: 100px;
  height: 40px;
  background-color: #FF0A54;
  display: flex;
  justify-content: center;
  align-items: center;
  color: white;
  border-radius: 6px;
}
.q_input_area {
  width: 100%;
  height: 400px;
  background-color: green;
}
.q_input_box {
  height: 50px;
  background-color: beige;
  display: flex;

}
.a_input_box {
  height: 300px;
  margin-top: 10px;
  border-radius: 6px;
  background-color: rgba(255,255,255,0.8);
  
}

input{
  padding-left: 12px;
  width: 700px;
  background-color: rgba(255,255,255,0.8);
  border-radius: 6px;
}
textarea {
  padding-left: 12px;
  width: 700px;
  height: 100%;
  background-color: rgba(255,255,255,0.8);
  border-radius: 6px;
}

input:focus {
  outline: none;
}
textarea:focus {
  outline: none;
}

.a_text {
  width: 700px;
  height: 100%;
  display: block;
}

 </style>
