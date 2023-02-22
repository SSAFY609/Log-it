<template>
  <div style="display:flex">
    <div class="container">
      <div v-if="sidebar" style="width:260px"></div>
      <div class="box">
          <h1 class="welcome">
            <!-- <img class="cal" :src="require(`@/assets/images/cal.png`)" /> -->
            <img class="cal" :src="require(`@/assets/images/cal2.png`)" />
            <div class="user-name">
              {{loginUser.name}}
            </div>
            님의 타임라인
          </h1>
          <div style="font-size: 20px;">LOG-IT과 함께하는 SSAFY 여정</div>
          <div style="font-size: 20px; margin-bottom:40px">{{loginUser.name}}님의 한 주 일정을 확인하고 타임라인을 클릭해 상세페이지로 가서 기록하고 관리해보세요.</div>
         
          
          <swiper 
          class="mySwiper"
          :modules="modules"
          @swiper="onSwiper"
          >
              <swiper-slide v-for="(date, index) in state.dates" :key="index">
                  <div class="grow">
                    <div @click="goGrowth(data.growthId)" v-for="(data, index) in date.growths" :key="index" :class="`event growth-event ${data.start} ${data.period} floor${index + 1}`">
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
                    <div @click="goJob(data.jobId)" v-for="(data, index) in date.jobs" :key="index" :class="`event job-event ${data.start} ${data.period} floor-${index + 1}`">
                      {{ data.title }}
                    </div>
                  </div>
              </swiper-slide>
          </swiper>
          <div class="buttons">
            <v-btn color="#717171" variant="text" size="60" @click="goslide(0)" icon="mdi-chevron-double-left"></v-btn>
            <v-btn color="#717171" variant="text" size="60" @click="nextSlide" icon="mdi-chevron-left"></v-btn>
            <v-btn color="#ff417a" variant="text" size="60" @click="goslide(state.slide)">오늘</v-btn>
            <v-btn color="#717171" variant="text" size="60" @click="prevSlide(0)" icon="mdi-chevron-right"></v-btn>
            <v-btn color="#717171" variant="text" size="60" @click="goslide(-1)" icon="mdi-chevron-double-right"></v-btn>
          </div>
      </div>
      
    </div>
  </div>
</template>

<script>
import { Swiper, SwiperSlide } from "vue-awesome-swiper";
import { Scrollbar, A11y } from 'swiper';
import { reactive, onBeforeMount, onMounted, computed } from "vue";
import { useStore } from "vuex";

import "swiper/css";
  
export default {
  name: 'FirstTimeline',
  components: {
      Swiper, 
      SwiperSlide,
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

      const sidebar = computed(()=>store.state.sidebar)
      const loginUser = computed(()=>store.state.loginUser)

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
        store.dispatch('tempJob/jobSetting', id)
      };
      
      onBeforeMount(()=>{
        const growths = store.state.timeline.growths;
        const jobs = store.state.timeline.jobs;

        let growth_st_str = null
        let growth_ed_str = null
        let job_st_str = null
        let job_ed_str = null
        let growth_st = null
        let growth_ed = null
        let job_st = null
        let job_ed = null
        
        
        // 정렬은 이미 되어있다고 생각
        if(growths.length != 0){
          growth_st_str = growths.reduce((prev, curr) => {
            const p = str_to_date(prev.eventDate.startDate)
            const c = str_to_date(curr.eventDate.startDate)
            return p <= c ? prev : curr;
          }).eventDate.startDate
          
          
          growth_ed_str = growths.reduce((prev, curr) => {
            const p = str_to_date(prev.eventDate.endDate)
            const c = str_to_date(curr.eventDate.endDate)
            return c <= p ? prev : curr;
          }).eventDate.endDate

          growth_st = str_to_date(growth_st_str)
          growth_ed = str_to_date(growth_ed_str)
        }
        
        if(jobs.count != 0){
          job_st_str = jobs.data.reduce((prev, curr) => {
            const p = str_to_date(prev.startDate)
            const c = str_to_date(curr.startDate)
            return p <= c ? prev : curr;
          }).startDate
  
          job_ed_str = jobs.data.reduce((prev, curr) => {
            const p = str_to_date(prev.endDate)
            const c = str_to_date(curr.endDate)
            return c <= p ? prev : curr;
          }).endDate
          
          job_st = str_to_date(job_st_str)
          job_ed = str_to_date(job_ed_str)
        }

        
        // 성장일지와 취업일지를 비교해 시작 날짜와 끝 날짜 뽑아냄
        let st = null;
        let ed = null;

        if (growth_st && job_st){
          st = growth_st < job_st ? growth_st : job_st;
          ed = growth_ed > job_ed ? growth_ed : job_ed;
        } else if (growth_st){
          st = growth_st
          ed = growth_ed
        } else if (job_st) {
          st = job_st
          ed = job_ed
        } else {
          const today = new Date()
          st = addDays(today, -3)
          ed = addDays(today, 3)
        }


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
          modules: [ Scrollbar, A11y],
      };
  },
}
</script>

<style scoped>
.cal {
  height: 64px;
  width: 54px;
  margin-right: 16px;
  margin-bottom: 10px;
  /* box-shadow: 1px 5px 10px grey; */
}
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
  height: 450px;
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
  /* margin-bottom: 40px; */
  font-size: 50px;
  font-family: appleB;
  color: #191919;
}

.user-name {
  font-size: 50px;
  /* font-size: 45px; */
  /* font-family: galaxy; */
  font-family: appleB;
  margin-right: 5px;
  color: #191919;
}

.progress{
  height: 600px;
  width: 100%;
  background-color: #a6a6a623;
  display: flex;
  justify-content: center;
  align-items: center;
}

.progress div {
  margin: 0 20px;

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
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  border-radius: 50px;
  height: 32px;
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


.growth-event{
  background: rgb(255,0,48) repeat-x;
  background: linear-gradient(90deg, #ff0030 0%, #ff6060 20%, #ff8584 30%, #ff8584 76%, #ff0030 100%);
  background-size: 300% 300%;
  background-position: 1% 50%;
  transition: all 1s ease;
}
.growth-event:hover,
.job-event:hover {
  background-position: 99% 50%;
}
.job-event{
  background: rgb(255, 196, 0) repeat-x;
  background: linear-gradient(90deg, rgb(255, 196, 0) 0%, rgba(255,182,56,1) 76%, rgba(255,226,175,1) 100%);
  background-size: 300% 300%;
  background-position: 1% 50%;
  transition: all 1s ease;
}

.floor1 {
  top: 130px;
}
.floor2 {
  top: 90px;
}
.floor3 {
  top: 50px;
}
.floor4 {
  top: 10px;
}
.floor-1 {
  top: 270px;
}
.floor-2 {
  top: 310px;
}
.floor-3 {
  top: 350px;
}
.floor-4 {
  top: 390px;

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
  width: 24px;
  height: 24px;
  top: -17px;
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