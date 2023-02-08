 <template>
  <div class="container">
    <div class="job_box_area lay3">
      <div class="header_area lay2">
        <h1>{{ datas.title }} 취업 여정</h1>
        <span v-if="datas.endDate">{{ date_to_str(datas.startDate, datas.endDate) }}</span>  
        <span v-else>{{ datas.startDate}} ~ 진행중</span>
      </div>
      <div class="tab_area lay3">
        <div v-for="i in datas.datas" :key="i" class="tab_area_box">
          <div class="tab_item_box f_main hover_cursor" @click="clickTab">{{ i.jobCategory }}</div>
        </div>
        <div class="add_btn f_main hover_cursor">+전형 추가</div>
      </div>


      <div class="contents_area"> 

        <!-- <h1>임시 데이터 확인</h1>
        <div>{{ datas.datas[0].list }}</div>
        <h1>^^^^^^^^^^^^^^</h1> -->

        <!-- DB에서 불러온 데이터 보여주는 영역 -->
        <div class="show_container">
          <div class="show_box" v-for="i in datas.datas" :key="i">
            <div v-if="clicked==i.jobCategory" class="show_inner_box">

              <h2 class="category_text">{{i.name}} 전형</h2>
              
              
              <!-- 서류전형-->
              <div v-if="i.jobCategory=='서류'">
                <div class="db_board_list" v-for="(el, index) in i.list" :key="index">
                  <h2>[ 질문{{ index + 1 }} ]</h2>
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
                      <textarea  class="a_text" placeholder="답변을 작성하세요."></textarea>
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
              <!-- 코테 영역 -->
              <div v-if="i.jobCategory=='코딩테스트'" class="ct_area">
                   
                <!-- 인풋창 영역-->
                <div class="ct_input_area">

                  <div class="test_item" v-for="(el, index) in ct_datas.list" :key="index">
                    <h2>[ 문제{{ index + 1 }} ]</h2>
                    <h3 class="temp">문제 유형</h3>
                    <div ref="option_area" class="option_types_area">
                      <!-- <div class="chip_box" v-for="item in testList" :key="item">
                        <input class="radio_item" type="radio" :name="`ct_category${index}`" :id="`${item}`" @click="changeOption" :value="`${item}`">
                        <label :for="`${item}`">{{ item }}</label>
                      </div> -->
                      
                      <div class="chip_box hover_cursor">
                        <input type="radio" :name="`ct_category${index}`" id="BFS" @click="changeOption" value="BFS">
                        <label for="BFS">BFS</label>
                      </div>
                      <div class="chip_box hover_cursor">
                        <input type="radio" :name="`ct_category${index}`" id="DFS" @click="changeOption" value="DFS">
                        <label for="DFS">DFS</label>
                      </div>
                      <div class="chip_box hover_cursor">
                        <input type="radio" :name="`ct_category${index}`" id="그래프" @click="changeOption" value="그래프">
                        <label for="그래프">그래프</label>
                      </div>
                      <div class="chip_box hover_cursor">
                        <input type="radio" :name="`ct_category${index}`" id="구현" @click="changeOption" value="구현">
                        <label for="구현">구현</label>
                      </div>
                      <div class="chip_box hover_cursor">
                        <input type="radio" :name="`ct_category${index}`" id="문자열" @click="changeOption" value="문자열">
                        <label for="문자열">문자열</label>
                      </div>
                      <div class="chip_box hover_cursor">
                        <input type="radio" :name="`ct_category${index}`" id="정렬" @click="changeOption" value="정렬">
                        <label for="정렬">정렬</label>
                      </div>
                      <div class="chip_box hover_cursor">
                        <input type="radio" :name="`ct_category${index}`" id="이분탐색" @click="changeOption" value="이분탐색">
                        <label for="이분탐색">이분탐색</label>
                      </div>
                      <div class="chip_box hover_cursor">
                        <input type="radio" :name="`ct_category${index}`" id="자료구조" @click="changeOption" value="자료구조">
                        <label for="자료구조">자료구조</label>
                      </div>
                      <div class="chip_box hover_cursor">
                        <input type="radio" :name="`ct_category${index}`" id="완전탐색" @click="changeOption" value="완전탐색">
                        <label for="완전탐색">완전탐색</label>
                      </div>
                      <div class="chip_box hover_cursor">
                        <input type="radio" :name="`ct_category${index}`" id="힙" @click="changeOption" value="힙">
                        <label for="힙">힙</label>
                      </div>
                      <div class="chip_box hover_cursor">
                        <input type="radio" :name="`ct_category${index}`" id="트리" @click="changeOption" value="트리">
                        <label for="트리">트리</label>
                      </div>
                      <div class="chip_box hover_cursor">
                        <input type="radio" :name="`ct_category${index}`" id="DP" @click="changeOption" value="DP">
                        <label for="DP">DP</label>
                      </div>
                      <div class="chip_box hover_cursor">
                        <input type="radio" :name="`ct_category${index}`" id="그리디" @click="changeOption" value="그리디">
                        <label for="그리디">그리디</label>
                      </div>

                      <div class="chip_box hover_cursor">
                        <input type="radio" :name="`ct_category${index}`" id="백트랙킹" @click="changeOption" value="백트랙킹">
                        <label for="백트랙킹">백트랙킹</label>
                      </div>


                      
                    
                    </div>
                    <div class="ct_contents_area">
                      <h3>문제 내용</h3>
                      <textarea class="ct_textarea" v-model="el.content"></textarea>
                    </div>
                  </div>
                </div>



                
              </div>






              <!-- <div v-if="i.jobCategory=='면접'">
                <div class="면접_area">
                  <div v-for="(item, index) in i.list" :key="index">
                    <div>{{ item.id }}</div>
                    <div>{{ item.category }}</div>
                    <div>{{ item.question }}</div>
                    <div>{{ item.answer }}</div>
                  </div>
                </div>
              </div> -->

                            <!-- 서류전형-->
                <div v-if="i.jobCategory=='면접'">
                  <div class="db_board_list" v-for="(item, index) in i.list" :key="index">
                    <h2>[ 질문{{ index + 1 }} ]</h2>
                    <div class="q_input_box">
                    <input class="q_text" type="text" v-model="item.question">
                  </div>
                  <div class="a_input_box">
                    <textarea class="a_text" v-model="item.answer"></textarea>
                  </div>
                </div>
                

                <!-- 인풋창 영역-->
                <div class="contents_box lay2">
                  <div class="q_input_area nosee">
                    <div class="q_input_box">
                      <input class="q_text" type="text" placeholder="질문을 추가하세요." autofocus>
                    </div>
                    <div class="a_input_box">
                      <textarea  class="a_text" placeholder="답변을 작성하세요."></textarea>
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


              
            </div>




          </div>    
        </div>




      </div>




    </div>
   </div>
 </template>
 
 <script>
import { mapState } from 'vuex';

 export default {
    name: 'JobProgress',

    components: {

    },
    computed: {
      ...mapState("job", ["testList"]),
      ...mapState("tempJob", ["jobs"])
    },

    data () {
      return {
        create_content: '',
        update: false,
        chip: true,
        clicked: '',
        jobId: '',
        datas: {},
        ct_datas: {},
        category: {},
 
      }
    },

    methods: {
      addQuestion() {
        const target = document.querySelector('.q_input_area')
        target.classList.toggle('nosee')


      },
      clickTab() {

        const removeList = document.querySelectorAll('.tab_item_box')
        const target = event.target
        


        removeList.forEach(element => {
          element.classList.remove('selected_item')
        });

        target.classList.add('selected_item')
        this.clicked = target.innerText
      },

      selectType() {
        const target = event.target
        const removeList = document.querySelectorAll('.chip_box')
        console.log(target)
        console.log(removeList)
        removeList.forEach(element => {
          element.classList.remove('selected_chip')  
        });

        target.classList.toggle('selected_chip')
      },

      date_to_str(st, ed) {
        const year1 = st.getFullYear();
        const month1 = st.getMonth() + 1;
        const date1 = st.getDate();
        const year2 = ed.getFullYear();
        const month2 = ed.getMonth() + 1;
        const date2 = ed.getDate();
        return `${year1}년 ${month1}월 ${date1}일 ~ ${year2}년 ${month2}월 ${date2}일`
      },

      changeOption() {

        const targetName = event.target.name
        const targetOption = event.target.value
        const target = event.target


        this.category[`${targetName}`] = targetOption
        target.classList.add('checked')
        // console.log(this.category)
        // console.log(target)

        const target2 = event.target
        const removeList = document.querySelectorAll('.chip_box')
        
        console.log(target2)
        console.log(removeList)

        removeList.forEach(element => {
          element.classList.remove('selected_chip')  
        });

        target2.parentElement.classList.toggle('selected_chip')

        


      }
    },

    created() {
      const jobId = this.$route.params.jobId
      const datas = this.$store.state.tempJob.jobs
      
      for(let i=0; i< datas.length; i++) {
        if(datas[i].jobId == jobId) {
          this.datas = datas[i]
        } 
      }

      for(let i=0; i < this.datas.datas.length; i++) {
          if (this.datas.datas[i].jobCategory == '코딩테스트') {
            this.ct_datas = this.datas.datas[i]
          }
      }

      // console.log(this.ct_datas)
      for(let i=0; i < this.ct_datas.list.length; i++) {
        // console.log(this.ct_datas.list[i].category)
        this.category[`ct_category${i}`] = this.ct_datas.list[i].category
      }

      


    },
    mounted() {


        const target = document.querySelector('.tab_area_box')
        

        target.firstChild.classList.add('selected_item')
        this.clicked = target.firstChild.innerText
      
      

        

        
      

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
  margin-bottom: 80px;
  

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
  display: flex;
  flex-direction: column;
  width:690px;
  margin-bottom: 100px;
}

.db_board_list {
  display: flex;
  flex-direction: column;
  background-color: transparent;
  margin-top: 20px;
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
  /* background-color: rgb(145, 255, 255); */
  width: 100%;
}

.option_types_area {
  display: flex;
  flex-wrap: wrap;
  width: 700px;
  height: 100px;
}




.test_item {
  width: 100%;
  height: 100%;
}

.ct_contents_area {
  background-color: transparent;
  width: 690px;
}

.ct_textarea {
  width: 100%;
  height: 200px !important;
  margin-bottom: 20px;
}
.ct_area {
  margin-top: 20px;
}
.면접_area {
  margin-top: 20px;
}
.chip_box {
  width: 90px;
  height: 36px;
  border-radius: 50px;
  background-color: #DFDFDF;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-right: 10px;
}
.chip_box:hover {
  background-color: #FF0A54;
  color: white;
}

label {
  width: 150px;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
label:hover {
  cursor: pointer;
}

.category_text {
  font-family: appleB;
  font-size: 30px;
}
.selected_chip {
  background-color: #FF0A54;
  color: white;
}

 </style>
