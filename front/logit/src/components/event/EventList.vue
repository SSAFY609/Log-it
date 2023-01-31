<template>
  <div class="container">
    <div class="header">
      <div class="title">
        <div class="event-title">{{ event.category }}</div>
        <div class="event-date">{{ date_to_str }}</div>
      </div>
      <div class="grass-box">
        <div class="grass">
          <div class="days">
            <div v-for="i in 15" :key="i" class="day">{{ i }}</div>
          </div>
          <div v-for="i in week" :key="i" class="week">
            <div v-for="j in 15" class="square" :key="j"></div>
          </div>
          <div class="week">
            <div v-for="j in rest" :key="j" class="square"></div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
    name: 'EventList',
    data() {
      return {
        event: {
          event_id: 1,
          category: '알고리즘 IM형',
          start_date: new Date(2023, 0, 16),
          end_date: new Date(2023,2,12)
        },
        period: 0,
        week: 0,
        rest: 0,
      }
    },
    computed: {
      date_to_str() {
        const st = this.event.start_date;
        const ed = this.event.end_date;
        const year1 = st.getFullYear();
        const month1 = st.getMonth() + 1;
        const date1 = st.getDate();
        const year2 = ed.getFullYear();
        const month2 = ed.getMonth() + 1;
        const date2 = ed.getDate();
        return `${year1}년 ${month1}월 ${date1}일 ~ ${year2}년 ${month2}월 ${date2}일`
      }
    },
    methods: {
      getDateDiff(d1, d2) {
        // d1이 시작 날짜, d2가 종료 날짜
        const diffDate = d2.getTime() - d1.getTime();
        
        return diffDate / (1000 * 60 * 60 * 24) + 1; // 밀리세컨 * 초 * 분 * 시 = 일
      }
    },
    created() {
      const st = this.event.start_date;
      const ed = this.event.end_date;
      this.period = this.getDateDiff(st, ed);
      this.week = parseInt(this.period / 15);
      this.rest = this.period % 15;
    }

}
</script>

<style scoped>

.test {
  background-image: url('../../assets/images/memo_grass.png');
  width: 30px;
  height: 30px;
}

.header {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 200px;
}

.container {
  /* background-color: gold; */
  margin-top: 150px;
  height: 100%;
  width:100%;
  display: flex;
  justify-content: center;
}

.title {
  text-align: center;
  margin-right: 100px;
}

.event-title {
  font-family: appleH;
  font-size: 60px;
}

.event-date {
  font-size: 20px;
}

.days {
  display: flex;
  align-items: flex-end;
}

.day {
  font-size: large;
  height: 35px;
  width: 35px;
  margin-right: 25px;
  text-align: center;
}

.day >span{
  font-size: large;
}

.week {
  display: flex;
  margin: 10px 0;
}

.square {
  background-color: rgb(241, 241, 241);
  border: 2px solid rgb(192, 192, 192);
  border-radius: 2px;
  height: 35px;
  width: 35px;
  margin-right: 25px;
}
</style>