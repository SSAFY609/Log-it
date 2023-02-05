<template>
  <div style="display:flex">
    <div class="container">
      <div v-if="sidebar" style="width:260px"></div>
      <div class="box">
          <h1 class="welcome">
            <div class="user-name">{{ loginUser.name }}</div>
            님의 타임라인
          </h1>
          <div class="buttons">
            <!-- 네모모양이냐 원이냐 -->
            <!-- <v-btn color="#464646" variant="outlined" @click="goslide(0)"><v-icon>mdi-chevron-triple-left</v-icon></v-btn>
            <v-btn color="#464646" variant="outlined" @click="nextSlide"><v-icon>mdi-chevron-double-left</v-icon></v-btn>
            <v-btn color="#464646" variant="outlined" @click="goslide(state.slide)"><v-icon>mdi-calendar-check</v-icon></v-btn>
            <v-btn color="#464646" variant="outlined" @click="prevSlide(0)"><v-icon>mdi-chevron-double-right</v-icon></v-btn>
            <v-btn color="#464646" variant="outlined" @click="goslide(-1)"><v-icon>mdi-chevron-triple-right</v-icon></v-btn> -->
            <v-btn color="#717171" variant="outlined" @click="goslide(0)" icon="mdi-chevron-triple-left"></v-btn>
            <v-btn color="#717171" variant="outlined" @click="nextSlide" icon="mdi-chevron-double-left"></v-btn>
            <v-btn color="#ff417a" variant="outlined" @click="goslide(state.slide)" icon="mdi-calendar-check"></v-btn>
            <v-btn color="#717171" variant="outlined" @click="prevSlide(0)" icon="mdi-chevron-double-right"></v-btn>
            <v-btn color="#717171" variant="outlined" @click="goslide(-1)" icon="mdi-chevron-triple-right"></v-btn>
          </div>
          <swiper 
          class="mySwiper"
          :modules="modules"
          :navigation="true"
          :pagination="true"
          @swiper="onSwiper"
          >
              <swiper-slide v-for="(date, index) in state.dates" :key="index">
                  <div class="grow">
                    <router-link :to="{name: 'EventProgress', params: {eventId: data.eventId}}" v-for="(data, index) in date.growths" :key="index" :class="`event ${data.start} ${data.period} floor${index + 1}`">
                      {{ data.title }}
                    </router-link>
                  </div>
                  <div class="bar">
                      <div class="hori-bar" v-for="(d, index) in date.str" :key="index">
                          <div class="date">
                              {{ d }}
                          </div>
                          <span class="circle">
                              <div class="hover"><button @click="show(index)" style="font-size:large">+</button></div>
                          </span>
                      </div>
                  </div>
                  <div class="job">
                    <div v-for="(data, index) in date.growths" :key="index" :class="`event ${data.start} ${data.period} floor-${index + 1}`">
                      {{ data.title }}
                    </div>
                  </div>
              </swiper-slide>
          </swiper>
      </div>
    </div>
  </div>
</template>

<script>
import { Swiper, SwiperSlide } from "vue-awesome-swiper";
import { Navigation, Pagination, Scrollbar, A11y } from 'swiper';
import { reactive, onBeforeMount, onMounted, computed } from "vue";
import { useStore } from "vuex";

import "swiper/css";
  
import "swiper/css/navigation";
// import 'swiper/css/pagination';
// import 'swiper/css/scrollbar';

export default {
  name: 'FirstTimeline',
  components: {
      Swiper, 
      SwiperSlide,
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
          // events: [
          //     {event_id: 4, start_date: new Date(2023,0,10), end_date: new Date(2023,1,10), name: 'SQLD 시험 준비'},
          //     {event_id: 1, start_date: new Date(2023,0,16), end_date: new Date(2023,2,12), name: '알고리즘 IM형'},
          //     {event_id: 2, start_date: new Date(2023,1,8), end_date: new Date(2023,3,13), name: '정보처리기사'},
          //     {event_id: 3, start_date: new Date(2023,0,24), end_date: new Date(2023,1,7), name: '알고리즘 A형'},
          // ],
          start: ['sun', 'mon', 'tue', 'wed', 'thu', 'fri', 'sat'],
          period: ['one', 'two', 'three', 'four', 'five', 'six', 'seven'],
          swiper: null,
      })

      const store = useStore()

      const sidebar = computed(()=>store.state.temp.sidebar)
      const loginUser = computed(()=>store.state.temp.loginUser)

      const onSwiper = (swiper) => {
        // this.swiper = swiper;
        console.log(swiper);
        // console.log(this.swiper);
        console.log(swiper['slideTo']);
        state.swiper = swiper;
        // swiper['slideTo'](this.slide_to, 1000, false)
        
      };
      
      onBeforeMount(()=>{
        // vuex에 접근해서 events 가공하기............. 후하 이게 근데 되려나 싶기도 하공,,,,
        const events = store.state.temp.events;

          // event 가져와서 start_date 순으로 정렬 (오름차순)
          // const events = state.events;
          events.sort((a, b) => {
            return a.start_date - b.start_date;
          });
        
          // 들어온 events에서 최소날짜와 최대날짜 뽑기
          let st = events.reduce((prev,curr) => {
              return prev.start_date <= curr.start_date ? prev : curr;
          })
          // console.log(st.start_date.toLocaleDateString());
          let ed = events.reduce((prev, curr) => {
              return curr.end_date <= prev.end_date ? prev : curr;
          })
          // console.log(ed.end_date.toLocaleDateString());

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

          // 최소 날짜와 최대 날짜 사이 일주일 단위로 끊기
          let new_date = state.st;
          let idx = 0;
          const today = new Date();
          while (new_date < state.ed){
              const push_date = {
                  sun: new Date(),
                  sat: new Date(),
                  str: [],
                  growths: [],
                  jobs: [],
              };
              push_date.sun = new_date;
              for(let i=0; i<7; i++){
                  const target = addDays(new_date, i);
                  if (target.toLocaleDateString() == today.toLocaleDateString()){
                    state.slide = idx;
                    const day = target.getDay();
                    push_date.str.push(`오늘(${state.day[day]})`);
                  }
                  else{
                    // const year = target.getFullYear();
                    const month = target.getMonth() + 1;
                    const date = target.getDate();
                    const day = target.getDay();
                    push_date.str.push(`${month >= 10 ? month : '0' + month}/${date >= 10 ? date : '0' + date}(${state.day[day]})`);
                  }
                  if (i==6){
                      push_date.sat = target;
                  }
              }

              // 이벤트들 등록 (쪼개서)
              for(let i=0; i<events.length; i++){
                const sd = events[i].start_date;
                const ed = events[i].end_date;
                const name = events[i].name;
                const eventId = events[i].event_id;
                if (sd < push_date.sun) {
                  if (ed < push_date.sun) {
                    continue;
                  } else if (push_date.sun <= ed && ed <= push_date.sat) {
                    const week = ed.getDay();
                    const event = {
                      start: state.start[0],
                      period: state.period[week],
                      title: name,
                      eventId: eventId,
                    };
                    push_date.growths.push(event);
                  } else {
                    const event = {
                      start: state.start[0],
                      period: state.period[6],
                      title: name,
                      eventId: eventId,
                    };
                    push_date.growths.push(event);
                  }
                } else if (push_date.sun <= sd && sd <= push_date.sat){
                  if(push_date.sun <= ed && ed <= push_date.sat){
                    const st_week = sd.getDay();
                    const ed_week = ed.getDay();
                    const event = {
                      start: state.start[st_week],
                      period: state.period[ed_week - st_week],
                      title: name,
                      eventId: eventId,
                    };
                    push_date.growths.push(event);
                  } else {
                    const st_week = sd.getDay();
                    const event = {
                      start: state.start[st_week],
                      period: state.period[6 - st_week],
                      title: name,
                      eventId: eventId,
                    };
                    push_date.growths.push(event);
                  }
                } else {
                  break;
                }
              }
              state.dates.push(push_date);
              new_date = addDays(new_date, 7);
              idx += 1;
          }
          // console.log(state.slide)
          // console.log(state.dates);

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
        // 오늘이 있는 페이지로 이동
        console.log(state.slide);
        state.swiper.slideTo(state.slide)
      })
      
      const addDays = (date, days) => {
          const clone = new Date(date);
          clone.setDate(date.getDate() + days)
          return clone;
      }

      const prevSlide = () => {
        state.swiper.slideNext()
      }

      const nextSlide = () => {
        state.swiper.slidePrev()
      }

      const goslide = (page) => {
        if (page == -1) {
          page = state.dates.length;
        }
        state.swiper.slideTo(page)
      }

      return {
          onSwiper,
          state,
          sidebar,
          loginUser,
          addDays,
          prevSlide,
          nextSlide,
          goslide,
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

<style scoped>
.container {
  /* background-color: gold; */
  height: 100%;
  width:100%;
  display: flex;
  justify-content: center;
}

.swiper-slide {
  width: 80%;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.swiper {
  position: static;
  height: 400px;
}

.box {
  margin-top: 150px;
  width: 70%;
  text-align: center;
  /* background-color: pink; */
}

.welcome {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 40px;
  font-size: 45px;
  font-family: appleB;
}

.user-name {
  font-size: 55px;
  font-family: galaxy;
  margin-right: 5px;
}

.grow div{
  margin-top: 3px;
}

.bar {
  display: flex;
  margin-top: 10px;
  margin-bottom: 10px;
}

.hori-bar {
  margin-top: 20px;
  width: 100%;
  display: flex;
  justify-content: center;
  border-top: 6px solid #d0d0d0;
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
  background-color: rgba(255, 255, 172, 0);
  color: #a6a6a6;
  text-align: center;
  font-size: medium;

}

.today-date button {
  color: #ffb272;
}

.buttons button{
  margin: 10px 5px;
}

.event {
  display: block;
  position: absolute;
  border-radius: 4px;
  height: 30px;
  font-size: 20px;
}

.mon {
  left: 14.28%;
}

.tue {
  left: 28.56%;
}

.wed {
  left: 42.84%;
}

.thu {
  left: 57.12%;
}

.fri {
  left: 71.4%;
}

.sat {
  left: 85.68%;
}

.one {
  width: 14.28%;
}

.two {
  width: 28.56%;
}

.three {
  width: 42.84%;
}

.four {
  width: 57.12%;
}

.five {
  width: 71.4%;
}

.six {
  width: 85.68%;
}

.seven {
  width: 100%;
}


.floor1 {
  top: 130px;
  background-color: rgb(255, 197, 207);
}
.floor2 {
  top: 95px;
  background-color: rgb(255, 210, 155);
}
.floor3 {
  top: 60px;
  background-color: rgb(255, 255, 172);
}

.floor4 {
  top: 25px;
  background-color: rgb(183, 255, 183);
}
.floor-1 {
  background-color: rgb(255, 197, 207);
}
.floor-2 {
  top: 280px;
  background-color: rgb(255, 210, 155);
}
.floor-3 {
  top: 315px;
  background-color: rgb(255, 255, 172);
}

.floor-4 {
  top: 350px;
  background-color: rgb(183, 255, 183);
}

.swiper-button-prev {
  color: #ffffff;
  height: 30px;
  width: 30px;
  border-radius: 50%;
  background-color: #c4c4c4;
  left: 4%;
  top: 45.9%;
}

.swiper-button-prev::before{
  content: '<';
  font-size: large;
  color: #ffffff;
}

.swiper-button-next {
  height: 30px;
  width: 30px;
  border-radius: 50%;
  background-color: #c4c4c4;
  right: 4%;
  top: 45.9%;
}

.swiper-button-next::before{
  content: '>';
  font-size: large;
  color: #ffffff;
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
  width: 15px;
  height: 15px;
  background-color: #ffffff;
  border-radius: 50%;
  border: 5px solid #a6a6a6;
  position: absolute;
  top: -13px;
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
  width: 24px;
  height: 24px;
  border-radius: 50%;
  border: 5px solid #a6a6a6;
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