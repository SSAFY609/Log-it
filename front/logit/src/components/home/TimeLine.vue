<template>
  <div class="box">
      <div class="welcome">타임라인에 일정을 추가해보세요</div>
      <swiper 
      class="mySwiper"
      :modules="modules"
      :navigation="true"
      :pagination="true"
      >
          <swiper-slide v-for="(text, index) in dates" :key="index">
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
import { Swiper, SwiperSlide } from "swiper/vue";
import { Navigation, Pagination, Scrollbar, A11y } from 'swiper';

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
          date: [],
          dates: [],
          day: ['일', '월', '화', '수', '목', '금', '토'],
          st: new Date(),
          ed: new Date(),
          is_show: false,
          choose_date: '',
          events: [
              {event_id: 4, start_date: new Date(2023,0,10), end_date: new Date(2023,1,10), name: 'SQLD 시험 준비'},
              {event_id: 1, start_date: new Date(2023,0,16), end_date: new Date(2023,2,12), name: '알고리즘 IM형'},
              {event_id: 2, start_date: new Date(2023,1,8), end_date: new Date(2023,3,13), name: '정보처리기사'},
              {event_id: 3, start_date: new Date(2023,0,24), end_date: new Date(2023,1,7), name: '알고리즘 A형'},
          ],
      }
  },
  setup(){
      const onSwiper = (swiper) => {
          console.log(swiper);
      };
      const onSlideChange = () => {
          console.log('slide change');
      };
      return {
          onSwiper,
          onSlideChange,
          modules: [Navigation, Pagination, Scrollbar, A11y],
      };
  },
  methods: {
      addDays(date, days) {
          const clone = new Date(date);
          clone.setDate(date.getDate() + days)
          return clone;
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

      // 들어온 events에서 최소날짜와 최대날짜 뽑기
      const events = this.events;
      let st = events.reduce((prev,curr) => {
          return prev.start_date <= curr.start_date ? prev : curr;
      })
      console.log(st.start_date.toLocaleDateString());
      let ed = events.reduce((prev, curr) => {
          return curr.end_date <= prev.end_date ? prev : curr;
      })
      console.log(ed.end_date.toLocaleDateString());

      this.st = st.start_date;
      this.ed = ed.end_date;


      // 시작날짜는 일요일부터, 끝나는 날짜는 토요일까지 될수 있도록
      // 날짜 변경하기
      if (this.st.getDay() != 0) {
          const num = 0 - this.st.getDay();
          this.st = this.addDays(this.st, num);
      }

      if (this.ed.getDay() != 6) {
          const num = 6 - this.ed.getDay();
          this.ed = this.addDays(this.ed, num);
      }

      // 테스트 (통)
      // console.log(this.st.toLocaleDateString());
      // console.log(this.ed.toLocaleDateString());

      let new_date = this.st;
      while (new_date < this.ed){
          const push_date = {
              sun: new Date(),
              sat: new Date(),
              str: []
          };
          push_date.sun = new_date;
          for(let i=0; i<7; i++){
              const target = this.addDays(new_date, i);
              // const year = target.getFullYear();
              const month = target.getMonth() + 1;
              const date = target.getDate();
              const day = target.getDay();
              push_date.str.push(`${month >= 10 ? month : '0' + month}/${date >= 10 ? date : '0' + date}(${this.day[day]})`);
              if (i==6){
                  push_date.sat = target;
              }
          }
          this.dates.push(push_date);
          new_date = this.addDays(new_date, 7);
      }

      console.log(this.dates);

      const today = new Date();        
      for (let i=-3; i<4; i++){
          if(i == 0){
              const day = today.getDay();
              this.date.push(`오늘(${this.day[day]})`);
          }else{
              const target = this.addDays(today, i);
              // const year = target.getFullYear();
              const month = target.getMonth() + 1;
              const date = target.getDate();
              const day = target.getDay();
              this.date.push(`${month >= 10 ? month : '0' + month}/${date >= 10 ? date : '0' + date}(${this.day[day]})`);
          }
      }
      console.log(this.date);
  }
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