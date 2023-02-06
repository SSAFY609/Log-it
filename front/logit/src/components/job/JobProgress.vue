 <template>
  <div class="container">
    <div class="job_box_area lay3">
      <div class="header_area lay2">
        <h1>{{ datas.name }} 취업 여정</h1>
        <span v-if="datas.endDate">{{ datas.startDate}} ~ {{ datas.endDate }}</span>  
        <span v-else>{{ datas.startDate}} ~ 진행중</span>
      </div>
      <div class="tab_area lay3">
        <div v-for="i in datas.details" :key="i" class="tab_area_box">
          <div class="tab_item_box f_main hover_cursor" @click="clickTab">{{ i.eventName }}</div>
        </div>
        <div class="add_btn f_main hover_cursor">+전형 추가</div>
      </div>
      <div class="contents_area"> 
        <!-- DB에서 불러온 데이터 보여주는 영역 -->
        <div class="show_container">
          <div class="show_box" v-for="i in datas.details" :key="i">
            <div v-if="clicked==i.eventName" class="show_inner_box">
              <div v-if="i.type=='서류'">


                <div class="db_board_list" v-for="el in i.boardList" :key="el">
                  <div class="q_input_box">
                    <input class="q_text" type="text" v-model="el.question">
                  </div>
                  <div class="a_input_box">
                    <textarea class="a_text" v-model="el.answer"></textarea>
                  </div>
                </div>
                

                <!-- 인풋창 영역-->
                <div class="contents_box lay2">
                  <div class="q_input_area nosee">
                    <div class="q_input_box">
                      <input class="q_text" type="text" placeholder="질문을 추가하세요." autofocus>
                    </div>
                    <div class="a_input_box">
                      <textarea  class="a_text" placeholder="답변을 작성하세요." v-model="datas.details[0].answer"></textarea>
                    </div>  
                  </div>

                  <!-- 추가하기 버튼 -->
                  <div class="add_q_btn_box hover_cursor" @click="addQuestion">
                    <div name="add_q_btn" id="add_q_btn" class="q_btn " >
                     <v-icon class="f_icon plus_icon">mdi-plus</v-icon>
                   </div>
                    <p>추가하기</p>
                  </div>
                </div>
            </div>



              <div v-if="i.type=='코테'">
                
                
                <!-- 인풋창 영역-->
                <div class="ct_input_area">
                  <v-autocomplete
                    clearable
                    chips
                    label="Autocomplete"
                    :items="algorithmTypes"
                    multiple
                    variant="solo"
                ></v-autocomplete>
                  

                </div>


                <div>{{ i.eventId }}</div>
                <div>{{ i.eventName }}</div>
                <div>{{ i.contents }}</div>
                <div>{{ i.testType }}</div>
              </div>




              <div v-if="i.type=='면접'">
                <div>{{ i.eventId }}</div>
                <div>{{ i.eventName }}</div>
                <div>{{ i.boardList[0].question }}</div>
                <div>{{ i.boardList[0].answer }}</div>
                <div>{{ i.boardList[1].question }}</div>
                <div>{{ i.boardList[1].answer }}</div>
                <div>{{ i.boardList[2].question }}</div>
                <div>{{ i.boardList[2].answer }}</div>
              </div>




              
            </div>
          </div>    
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
        text: '서류전형은 어쩌구 저쩌구 더미 데이터이다!!!',
        create_content: '',
        update: false,
        clicked: '',
        algorithmTypes: ["dfs", "bfs","greedy", "tree", "stack", "que",],
        datas: {
          name: "카카오",
          startDate: "2021-01-03",
          endDate: null,
          details: [
            {eventId: "1", eventName: "서류전형", type: "서류", boardList: [
              {bId: "01", question: "[질문1] 지원자의 성장과정과 노력들에 대해서 작성하시오.", 
               answer: "안녕하십니까 저는 이성훈입니다.이러한 결과에 초점을 맞추어 찍찍이를 이용하여 간단하게 붙일 수 있는 방충망을 주력 제품으로 삼았습니다.또한, 설문 조사 시 받은 메신저 아이디로 방충망 무료 설치 이벤트를 제안하였고, 승낙한 분들의 집에 직접 방충망을 설치하기 시작했습니다.  이 경험을 통해 위기가 발생해도 해답을 찾는 방법을 배웠고, 무엇보다 하고자 하는 의지가 중요하다는 것을 배웠습니다. 항상 함께하는 팀원에게 감사함을 느끼며 업무에 임하는 직원이 되겠습니다."},
              {bId: "02", question: "[서류]우리 회사에 지원한 동기는?", answer: "돈을 많이 준다고 들었습니다만?"},
              {bId: "03", question: "[서류]본인의 강점을 말해보세요", answer: "저는 MZ세대 입니다."},
            ]

            },
            {eventId: "2", eventName: "코딩테스트", type: "코테" , testType: ["dfs", "greedy", "tree",], contents: "코딩테스트 내용입니다. 어쩌구 저쩌구"},
            {eventId: "3", eventName: "임원면접", type: "면접", boardList: [
              {bId: "01", question: "자기소개 해보세요.", answer: "안녕하십니까 저는 이성훈입니다."},
              {bId: "02", question: "우리 회사에 지원한 동기는?", answer: "돈을 많이 준다고 들었습니다만?"},
              {bId: "03", question: "본인의 강점을 말해보세요", answer: "저는 MZ세대 입니다."},
            ]}
          ],
        }
      }
    },

    methods: {
      addQuestion() {
        const target = document.querySelector('.q_input_area')
        console.log(target)
        target.classList.toggle('nosee')


      },
      clickTab() {

        const removeList = document.querySelectorAll('.tab_item_box')
        const target = event.target
        
        console.log(target.innerText)


        removeList.forEach(element => {
          element.classList.remove('selected_item')
        });

        target.classList.add('selected_item')
        this.clicked = target.innerText



      }
    }

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
  height: 100%;
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
  background-color: #F6F6F6;
  border-radius: 6px;
  margin-right: 6px;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0px 30px;
}

.add_btn {
  height: 40px;
  background-color: #F6F6F6;
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
  min-height: 600px;
  background-color: #F6F6F6;
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-top: 20px;
  

}
.contents_box {
  margin-top: 30px;
  width: 100%;
}
.q_btn {
  width: 40px;
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
}
.q_input_box {
  height: 50px;
  background-color: rgba(255,255,255,0.8);
  display: flex;

}
.a_input_box {
  height: 300px;
  margin-top: 10px;
  border-radius: 6px;
  
}

input{
  padding-left: 12px;
  border-radius: 6px;
  width: 100%;
  font-size: 18px;
}
textarea {
  padding-left: 12px;
  padding-right: 12px;
  padding-top: 14px;
  height: 100%;
  background-color:  rgba(255,255,255,0.8);
  border-radius: 6px;
  border: none;
  resize: none;
  font-size: 18px;
}

input:focus {
  outline: none;
}
textarea:focus {
  outline: none;
}

.a_text {
  width: 100%;
  height: 100%;

}

.plus_icon {
  color: white;
}

.show_container {

  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  padding: 12px;
}

.show_box {
  background-color: transparent;
}
.show_inner_box {
  width:690px
}

.db_board_list {
  display: flex;
  flex-direction: column;
  background-color: transparent;
  margin-top: 40px;
}

.add_q_btn_box {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 140px;
}
.add_q_btn_box p {
  font-size: 24px;
  font-family: appleM;
  margin-left: 10px;
  color: #464646;
}
.ct_input_area {
  background-color: rgb(145, 255, 255);
  width: 100%;
  height: 300px;


}
 </style>
