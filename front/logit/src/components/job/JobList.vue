<template>
  <div class="container">
    <div class="event-list" >
      <h1>{{ allJob }}</h1>
      <router-link :to="{name: 'JobProgress', params: {jobId: job.jobId}}" class="event hover_cursor" v-for="job in jobs" :key="job">
        <div class="event-title">{{ job.title }}</div>
        <div class="event-date">{{ date_to_str(job.startDate, job.endDate) }}</div>
        <v-chip v-if="job.progress" variant="outlined" color="rgb(27, 182, 40)">완료</v-chip>
        <v-chip v-else variant="outlined" color="rgb(245, 21, 107)">진행중</v-chip>
      </router-link>
      <!-- 추가하기 버튼 영역 -->
      <router-link :to="{name: 'JobCreate'}" class="add_event hover_cursor">
        <v-icon class="f_icon plus_icon">mdi-plus-box</v-icon>
        <div class="event-title add_title">이벤트 추가</div>
      </router-link>
      <div v-if="jobs.length%2 == 0" class="event-else"></div>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex';
export default {
    name: 'JobList',
    data() {
      return {


      }
    },
    computed: {
      ...mapState("tempJob", ["jobs", "allJob"])
    },
    methods: {
      date_to_str(st, ed) {
        const year1 = st.getFullYear();
        const month1 = st.getMonth() + 1;
        const date1 = st.getDate();
        const year2 = ed.getFullYear();
        const month2 = ed.getMonth() + 1;
        const date2 = ed.getDate();
        return `${year1}년 ${month1}월 ${date1}일 ~ ${year2}년 ${month2}월 ${date2}일`
      },

      getDateDiff(d1, d2) {
        // d1이 시작 날짜, d2가 종료 날짜
        const diffDate = d2.getTime() - d1.getTime();
        
        return diffDate / (1000 * 60 * 60 * 24) + 1; // 밀리세컨 * 초 * 분 * 시 = 일
      }
    },
    created() {
      this.$store.dispatch('tempJob/getAllJob')

    }

}
</script>

<style scoped>

.container {
  /* background-color: gold; */
  margin-top: 100px;
  /* height: 100%; */
  width:100%;
  display: flex;
  justify-content: center;
  text-align: center;
  padding: 0 100px;
}

.event-list {
  display: flex;
  /* flex-direction: column; */
  justify-content: center;
  /* align-items: center; */
  flex-wrap: wrap;
  width: 80%;
  /* height: 200px; */
}

.event {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-color: rgba(219, 219, 219, 0.194);
  border-radius: 20px;
  height: 200px;
  width: 500px;
  margin: 10px;
  /* margin-right: 100px; */
}

.event-else {
  background-color: white;
  height: 200px;
  width: 500px;
  margin: 10px;
}

.event:hover {
  background-color: rgba(219, 219, 219, 0.335);
}

.event-title {
  font-family: appleH;
  font-size: 40px;
}

.event-date {
  font-size: 20px;
  margin-bottom: 10px;
}

.add_event {
  display: flex;
  justify-content: center;
  align-items: center;
  /* background-color: rgba(219, 219, 219, 0.194); */
  border-radius: 20px;
  height: 200px;
  width: 500px;
  margin: 10px;
  border: 6px dashed rgba(219, 219, 219, 0.335);
}
.add_event:hover {
  background-color: rgba(219, 219, 219, 0.616);
  border: 6px  rgba(219, 219, 219, 0.692);
}
.add_event:hover :nth-child(1){
  color: #313131;
}
.add_event:hover :nth-child(2){
  color: #313131;
}

.plus_icon {
  font-size: 60px;
  margin-right: 4px;
  color: #9292926e;

}
.add_title {
  color: #9292926e;
}

</style>