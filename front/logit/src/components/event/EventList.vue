<template>
  <div class="container">
    <div class="event-list">
      <router-link :to="{name: 'EventProgress', params: {eventId: event.event_id}}" class="event hover_cursor" v-for="event in events" :key="event.event_id">
        <div class="event-title">{{ event.name }}</div>
        <div class="event-date">{{ date_to_str(event.start_date, event.end_date) }}</div>
        <v-chip v-if="event.done" variant="outlined" color="rgb(27, 182, 40)">완료</v-chip>
        <v-chip v-else variant="outlined" color="rgb(245, 21, 107)">진행중</v-chip>
      </router-link>
      <div v-if="events.length%2 == 1" class="event-else"></div>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex';
export default {
    name: 'EventList',
    data() {
      return {
        // period: 0,
        // week: 0,
        // rest: 0,
      }
    },
    computed: {
      ...mapState("temp", ["events"])
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
      // const st = this.event.start_date;
      // const ed = this.event.end_date;
      // this.period = this.getDateDiff(st, ed);
      // this.week = parseInt(this.period / 15);
      // this.rest = this.period % 15;
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

</style>