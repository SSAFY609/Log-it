<template>
  <div style="display:flex">
    <div class="container">
      <div v-if="sidebar" style="width:260px"></div>
      <div class="box">
          <h1 class="welcome">{{loginUser.name}}님 타임라인</h1>
          
          <swiper 
          class="mySwiper"
          :modules="modules"
          :navigation="true"
          :pagination="true"
          @swiper="onSwiper"
          >
              <swiper-slide v-for="(date, index) in state.dates" :key="index">
                  <div class="grow">
                    <div @click="goGrowth(data.growthId)" v-for="(data, index) in date.growths" :key="index" :class="`event ${data.start} ${data.period} floor${index + 1}`">
                      {{ data.title }}
                    </div>
                  </div>
                  <div class="bar">
                      <div class="hori-bar" v-for="(d, index) in date.str" :key="index">
                          <div v-if="d.indexOf('오늘') != -1" class="date today-date">
                              {{ d }}
                          </div>
                          <div v-else class="date">
                              {{ d }}
                          </div>
                          <span class="circle">
                          </span>
                      </div>
                  </div>
                  <div class="job">
                    <div @click="goJob(data.jobId)" v-for="(data, index) in date.jobs" :key="index" :class="`event ${data.start} ${data.period} floor-${index + 1}`">
                      {{ data.title }}
                    </div>
                  </div>
              </swiper-slide>
          </swiper>
          <div class="buttons">
            <!-- 네모모양이냐 원이냐 -->
            <!-- <v-btn color="#464646" variant="outlined" @click="goslide(0)"><v-icon>mdi-chevron-triple-left</v-icon></v-btn>
            <v-btn color="#464646" variant="outlined" @click="nextSlide"><v-icon>mdi-chevron-double-left</v-icon></v-btn>
            <v-btn color="#ff417a" variant="outlined" @click="goslide(state.slide)">Today</v-btn>
            <v-btn color="#464646" variant="outlined" @click="prevSlide(0)"><v-icon>mdi-chevron-double-right</v-icon></v-btn>
            <v-btn color="#464646" variant="outlined" @click="goslide(-1)"><v-icon>mdi-chevron-triple-right</v-icon></v-btn> -->
            <v-btn color="#717171" variant="text" @click="goslide(0)" icon="mdi-chevron-double-left"></v-btn>
            <v-btn color="#717171" variant="text" @click="nextSlide" icon="mdi-chevron-left"></v-btn>
            <v-btn color="#ff417a" variant="text" @click="goslide(state.slide)" size="large">TODAY</v-btn>
            <v-btn color="#717171" variant="text" @click="prevSlide(0)" icon="mdi-chevron-right"></v-btn>
            <v-btn color="#717171" variant="text" @click="goslide(-1)" icon="mdi-chevron-double-right"></v-btn>
          </div>
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
          start: ['sun', 'mon', 'tue', 'wed', 'thu', 'fri', 'sat'],
          period: ['one', 'two', 'three', 'four', 'five', 'six', 'seven'],
          swiper: null,
      })

      const store = useStore()

      const sidebar = computed(()=>store.state.temp.sidebar)
      const loginUser = computed(()=>store.state.temp.loginUser)

      const onSwiper = (swiper) => {
        state.swiper = swiper;
      };

      const str_to_date = (str) => {
        const arr = str.split('-');
        const year = arr[0];
        const month = parseInt(arr[1]) - 1;
        const date = parseInt(arr[2]);
        return new Date(year, month, date);
      }

      const goGrowth = (id) => {
        store.dispatch('growth/growthSetting', id)
      };
      const goJob = (id) => {
        store.dispatch('job/jobSetting', id)
      };
      
      onBeforeMount(()=>{

        const growths = store.state.timeline.growths;
        const jobs = store.state.timeline.jobs;

        // 정렬은 이미 되어있다고 생각

        const growth_st_str = growths.reduce((prev, curr) => {
          const p = str_to_date(prev.eventDate.startDate)
          const c = str_to_date(curr.eventDate.startDate)
          return p <= c ? prev : curr;
        }).eventDate.startDate

       
        const growth_ed_str = growths.reduce((prev, curr) => {
          const p = str_to_date(prev.eventDate.endDate)
          const c = str_to_date(curr.eventDate.endDate)
          return c <= p ? prev : curr;
        }).eventDate.endDate

        const job_st_str = jobs.data.reduce((prev, curr) => {
          const p = str_to_date(prev.startDate)
          const c = str_to_date(curr.startDate)
          return p <= c ? prev : curr;
        }).startDate

        const job_ed_str = jobs.data.reduce((prev, curr) => {
          const p = str_to_date(prev.endDate)
          const c = str_to_date(curr.endDate)
          return c <= p ? prev : curr;
        }).endDate

        const growth_st = str_to_date(growth_st_str)
        const growth_ed = str_to_date(growth_ed_str)
        const job_st = str_to_date(job_st_str)
        const job_ed = str_to_date(job_ed_str)

        // 성장일지와 취업일지를 비교해 시작 날짜와 끝 날짜 뽑아냄
        let st = growth_st < job_st ? growth_st : job_st;
        let ed = growth_ed > job_ed ? growth_ed : job_ed;

        // 시작날짜는 일요일부터, 끝나는 날짜는 토요일까지 될수 있도록
        // 날짜 변경하기
        if (st.getDay() != 0) {
            const num = 0 - st.getDay();
            st = addDays(st, num);
        }

        if (ed.getDay() != 6) {
            const num = 6 - ed.getDay();
            ed = addDays(ed, num);
        }

        // 테스트 (통)
        // console.log(st.toLocaleDateString());
        // console.log(ed.toLocaleDateString());

        // 최소 날짜와 최대 날짜 사이 일주일 단위로 끊기
        let new_date = st;
        let idx = 0;
        const today = new Date();
        while (new_date < ed){
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

            // 성장여정 쪼개기
            for(let i=0; i<growths.length; i++){
              const sd = str_to_date(growths[i].eventDate.startDate);
              const ed = str_to_date(growths[i].eventDate.endDate);
              const name = growths[i].category;
              const growthId = growths[i].growthId;
              if (sd < push_date.sun) {
                if (ed < push_date.sun) {
                  continue;
                } else if (push_date.sun <= ed && ed <= push_date.sat) {
                  const week = ed.getDay();
                  const event = {
                    start: state.start[0],
                    period: state.period[week],
                    title: name,
                    growthId: growthId,
                  };
                  push_date.growths.push(event);
                } else {
                  const event = {
                    start: state.start[0],
                    period: state.period[6],
                    title: name,
                    growthId: growthId,
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
                    growthId: growthId,
                  };
                  push_date.growths.push(event);
                } else {
                  const st_week = sd.getDay();
                  const event = {
                    start: state.start[st_week],
                    period: state.period[6 - st_week],
                    title: name,
                    growthId: growthId,
                  };
                  push_date.growths.push(event);
                }
              } else {
                break;
              }
            }

            // 취업 여정 쪼개기
            for(let i=0; i<jobs.count; i++){
              const sd = str_to_date(jobs.data[i].startDate);
              const ed = str_to_date(jobs.data[i].endDate);
              const name = jobs.data[i].companyName;
              const jobId = jobs.data[i].id;
              if (sd < push_date.sun) {
                if (ed < push_date.sun) {
                  continue;
                } else if (push_date.sun <= ed && ed <= push_date.sat) {
                  const week = ed.getDay();
                  const event = {
                    start: state.start[0],
                    period: state.period[week],
                    title: name,
                    jobId: jobId,
                  };
                  push_date.jobs.push(event);
                } else {
                  const event = {
                    start: state.start[0],
                    period: state.period[6],
                    title: name,
                    jobId: jobId,
                  };
                  push_date.jobs.push(event);
                }
              } else if (push_date.sun <= sd && sd <= push_date.sat){
                if(push_date.sun <= ed && ed <= push_date.sat){
                  const st_week = sd.getDay();
                  const ed_week = ed.getDay();
                  const event = {
                    start: state.start[st_week],
                    period: state.period[ed_week - st_week],
                    title: name,
                    jobId: jobId,
                  };
                  push_date.jobs.push(event);
                } else {
                  const st_week = sd.getDay();
                  const event = {
                    start: state.start[st_week],
                    period: state.period[6 - st_week],
                    title: name,
                    jobId: jobId,
                  };
                  push_date.jobs.push(event);
                }
              } else {
                break;
              }
            }
            // console.log(push_date)
            state.dates.push(push_date);
            new_date = addDays(new_date, 7);
            idx += 1;
        }

          // console.log(state.dates)
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
          goGrowth,
          goJob,
          str_to_date,
          addDays,
          prevSlide,
          nextSlide,
          goslide,
          modules: [Navigation, Pagination, Scrollbar, A11y],
      };
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
  /* font-size: 45px; */
  font-family: galaxy;
  /* font-family: appleB; */
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
  margin: 30px 5px;
}

.event {
  display: block;
  position: absolute;
  border-radius: 50px;
  height: 30px;
  font-size: 20px;
  color: white;
  cursor: pointer;
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
  /* background-color: rgb(255, 126, 147); */
  background-color: #ff477ecc;
}
.floor2 {
  top: 95px;
  /* background-color: rgb(255, 185, 100); */
  background-color: #ff5c8abe;
}
.floor3 {
  top: 60px;
  /* background-color: rgb(255, 126, 147); */
  background-color: #ff7096c0;
}

.floor4 {
  background-color: #ff85a2bb;
  top: 25px;
  /* background-color: rgb(255, 210, 46); */
}
.floor-1 {
  /* background-color: rgb(27, 171, 255); */
  background-color: #ff8800b8;
}
.floor-2 {
  top: 280px;
  /* background-color: rgb(255, 210, 155); */
  /* background-color: rgb(255, 126, 147); */
  background-color: #ffa200bd;
}
.floor-3 {
  top: 315px;
  /* background-color: rgb(255, 255, 172); */
  /* background-color: rgb(255, 185, 100); */
  background-color: #ffc400bb;
}

.floor-4 {
  top: 350px;
  /* background-color: rgb(255, 126, 147); */
  /* background-color: rgb(183, 255, 183); */
  background-color: #ffd000bf;

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
  border-color: #494949;
}

.today-date ~.circle:hover {
  border-color: #494949;
  color: #494949;
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