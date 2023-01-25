<template>
  <div class="box">
      <h1 class="welcome">타임라인에 일정을 추가해보세요</h1>
      <swiper 
      class="mySwiper"
      :modules="modules"
      :navigation="true"
      :pagination="true"
      @swiper="onSwiper"
      @slideChange="onSlideChange"
      >
          <swiper-slide v-for="(text, index) in state.dates" :key="index">
              <div class="one">엘렐레펠ㄹ렐레</div>
              <div class="bar">
                  <div class="hori-bar" v-for="(d, index) in text.str" :key="index">
                      <!-- <div class="today-date" v-if="index == 4">
                          {{ date[index-1] }}
                      </div> -->
                      <div class="date">
                          {{ d }}
                      </div>
                      <span class="circle">
                          <div class="hover"><button @click="show(index)">+</button></div>
                      </span>
                  </div>
              </div>
              <a class="one" href="#">엘렐레</a>
          </swiper-slide>
          <!-- <div class="swiper-button-prev">전</div>
          <div class="swiper-button-next">후</div> -->
      </swiper>
      <div class="show-btn">
          <div v-if="this.choose_date" style="margin-bottom: 20px;">
              {{ choose_date }}
          </div>
          <div>
              <span class="add-event nosee"><button>성장여정추가</button></span>
              <span class="add-job nosee"><button>취업여정추가</button></span>
          </div>
      </div>
  </div>
</template>

<script>
import { Swiper, SwiperSlide } from "vue-awesome-swiper";
import { Navigation, Pagination, Scrollbar, A11y } from 'swiper';
import { reactive, onBeforeMount, onMounted } from "vue";

import "swiper/css";
  
import "swiper/css/navigation";
// import 'swiper/css/pagination';
// import 'swiper/css/scrollbar';

export default {
  name: 'FirstTimeline',
  components: {
      Swiper, SwiperSlide,
  },
  data() {
      return {
          
      }
  },
  setup(){

      const state = reactive({
          date: [],
          dates: [],
          day: ['일', '월', '화', '수', '목', '금', '토'],
          st: new Date(),
          ed: new Date(),
          is_show: false,
          choose_date: '',
          slide: 0,
          events: [
              {event_id: 4, start_date: new Date(2023,0,10), end_date: new Date(2023,1,10), name: 'SQLD 시험 준비'},
              {event_id: 1, start_date: new Date(2023,0,16), end_date: new Date(2023,2,12), name: '알고리즘 IM형'},
              {event_id: 2, start_date: new Date(2023,1,8), end_date: new Date(2023,3,13), name: '정보처리기사'},
              {event_id: 3, start_date: new Date(2023,0,24), end_date: new Date(2023,1,7), name: '알고리즘 A형'},
          ],
          swiper: null,
      })

      const onSwiper = (swiper) => {
        // this.swiper = swiper;
        console.log(swiper);
        // console.log(this.swiper);
        console.log(swiper['slideTo']);
        state.swiper = swiper;
        // swiper['slideTo'](this.slide_to, 1000, false)
        
      };
      
      const onSlideChange = () => {
          console.log('slide change');
      };

      onBeforeMount(()=>{
          // 들어온 events에서 최소날짜와 최대날짜 뽑기
          const events = state.events;
          let st = events.reduce((prev,curr) => {
              return prev.start_date <= curr.start_date ? prev : curr;
          })
          console.log(st.start_date.toLocaleDateString());
          let ed = events.reduce((prev, curr) => {
              return curr.end_date <= prev.end_date ? prev : curr;
          })
          console.log(ed.end_date.toLocaleDateString());

          state.st = st.start_date;
          state.ed = ed.end_date;


          // 시작날짜는 일요일부터, 끝나는 날짜는 토요일까지 될수 있도록
          // 날짜 변경하기
          if (state.st.getDay() != 0) {
              const num = 0 - state.st.getDay();
              state.st = addDays(state.st, num);
          }

          if (state.ed.getDay() != 6) {
              const num = 6 - state.ed.getDay();
              state.ed = addDays(state.ed, num);
          }

          // 테스트 (통)
          // console.log(state.st.toLocaleDateString());
          // console.log(state.ed.toLocaleDateString());

          let new_date = state.st;
          let idx = 0;
          const today = new Date();
          while (new_date < state.ed){
              const push_date = {
                  sun: new Date(),
                  sat: new Date(),
                  str: []
              };
              push_date.sun = new_date;
              for(let i=0; i<7; i++){
                  const target = addDays(new_date, i);
                  if (target.toLocaleDateString() == today.toLocaleDateString()){
                    state.slide = idx;
                  }
                  // const year = target.getFullYear();
                  const month = target.getMonth() + 1;
                  const date = target.getDate();
                  const day = target.getDay();
                  push_date.str.push(`${month >= 10 ? month : '0' + month}/${date >= 10 ? date : '0' + date}(${state.day[day]})`);
                  if (i==6){
                      push_date.sat = target;
                  }
              }
              state.dates.push(push_date);
              new_date = addDays(new_date, 7);
              idx += 1;
          }
          console.log(state.slide)
          console.log(state.dates);

          // for (let i=-3; i<4; i++){
          //     if(i == 0){
          //         const day = today.getDay();
          //         state.date.push(`오늘(${state.day[day]})`);
          //     }else{
          //         const target = addDays(today, i);
          //         // const year = target.getFullYear();
          //         const month = target.getMonth() + 1;
          //         const date = target.getDate();
          //         const day = target.getDay();
          //         state.date.push(`${month >= 10 ? month : '0' + month}/${date >= 10 ? date : '0' + date}(${this.day[day]})`);
          //     }
          // }
          // console.log(state.date);
      })

      onMounted(()=>{
        console.log('onMounted?');
        console.log(state.slide);
        state.swiper.slideTo(state.slide)
      })
      
      const addDays = (date, days) => {
          const clone = new Date(date);
          clone.setDate(date.getDate() + days)
          return clone;
      }

      return {
          onSwiper,
          onSlideChange,
          state,
          addDays,
          modules: [Navigation, Pagination, Scrollbar, A11y],
      };
  },
  methods: {
      slideChange() {
        console.log(this.$refs.mySwiper.$swiper.activeIndex);
      },
      
      show(index){
          const idx = index - 1;
          this.choose_date = this.date[idx];
          if(!this.is_show){
              this.is_show = true;
              const btn1 = document.querySelector(".add-event");
              const btn2 = document.querySelector(".add-job");
              btn1.classList.toggle('nosee');
              btn2.classList.toggle('nosee');

          }

          // const btn1 = document.querySelector(".add-event");
          // const btn2 = document.querySelector(".add-job");
          // btn1.classList.toggle('nosee');
          // btn2.classList.toggle('nosee');
      },
      change(idx){
          if(idx == 1){
              this.swiper.slidePrev();
          }else{
              this.swiper.slideNext();
          }
      }
  },
  created(){

    
  },
}
</script>

<style>

.swiper-slide {
  width: 80%;
}

.swiper {
  position: static;
}

.box {
  margin: 20% 8%;
  text-align: center;
}

.welcome {
  margin-bottom: 40px;
}

.bar {
  display: flex;
  margin-bottom: 10px;
}

.hori-bar {
  margin-top: 20px;
  width: 100%;
  display: flex;
  justify-content: center;
  border-top: 3px solid #d0d0d0;
  position: relative;
  transition: all 200ms ease-in ;
}

.hover {
  display: none;
  color:#a6a6a6 ;
}

.hover button {
  border: none;
  border-radius: 50%;
  background-color: white;
  z-index: -100;
  color: #a6a6a6;
  text-align: center;
}

.today-date button {
  color: #ffb272;
}

.one {
  display: block;
  background-color: pink;
  position: relative;
  height: 20px;
  left: 28.6%;
  width: 28.6%;
}

.swiper-pagination {
  position: static;
  margin-top: 20px;
}

.swiper-button-prev {
  color: #ffffff;
  height: 22px;
  width: 22px;
  border-radius: 50%;
  background-color: #c4c4c4;
  left: 4%;
  top: 36.5%;
}

.swiper-button-prev::before{
  content: '<';
  font-size: small;
}

.swiper-button-next {
  color: #ffffff;
  height: 22px;
  width: 22px;
  border-radius: 50%;
  background-color: #c4c4c4;
  right: 4%;
  top: 36.5%;
}

.swiper-button-next::before{
  content: '>';
  font-size: small;
}

.swiper-button-next::after,
.swiper-button-prev::after {
display: none;
}
/**
.add-event{
  position: absolute;
  width: 600%;
  left: -50px;
  top: -35px;
}

.add-job{
  position: absolute;
  width: 600%;
  left: -50px;
  top: 30px;
}**/

.nosee {
  display: none;
}

.show-btn {
  margin-top: 50px;
}

.circle {
  display: flex;
  flex-direction: column;
  content: '';
  width: 10px;
  height: 10px;
  background-color: #ffffff;
  border-radius: 10px;
  border: 3px solid #a6a6a6;
  position: absolute;
  top: -10px;
  transition: all 200ms step-start;
}

.today-date ~.circle {
  border-color: #ffb272;
}

.today-date ~.circle:hover {
  border-color: #ffb272;
  color: #ffb272;
}

.circle:hover {
  color:#a6a6a6 ;
  width: 20px;
  height: 20px;
  border-radius: 25px;
  border: 4px solid #a6a6a6;
  top: -16px;
}

.circle:hover >.hover{
  display: block;
}

/**.circle:hover >.add-event{
  display: block;
  position: absolute;
  width: 600%;
  left: -50px;
  top: -35px;
}

.circle:hover >.add-job{
  display: block;
  position: absolute;
  width: 600%;
  left: -50px;
  top: 30px;
}
**/

.today-date {
  margin-top: 18px;
  font-size: small;
}

.date {
  margin-top: 20px;
  font-size: medium;
}
</style>