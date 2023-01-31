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
            <div v-for="i in 10" :key="i" class="day">{{ i }}</div>
          </div>
          <!-- <div v-for="i in week" :key="i" class="week">
            <div v-for="j in 10" class="square" :key="j"></div>
          </div>
          <div class="week">
            <div v-for="j in rest" :key="j" class="square"></div>
          </div> -->
          <div class="week">
            <div v-for="i in grass" :key="i" :class="i"></div>
          </div>
        </div>
      </div>
    </div>
    <div class="progress">
      <v-timeline side="end" align="center" line-thickness="5">
        <v-timeline-item
          class="progress-item"
          v-for=" (item, index) in items"
          dot-color="rgb(255, 225, 121)"
          :key="item.id"
          size="small"
        >
          <div class="memo-box">
            <div class="memo-date">{{item.date}}</div>
            <div class="memo" @click="dialog = true, create_content = item.content, now_idx = index">
              <!-- <div v-if="!create_content">텍스트를 입력하세요</div> -->
              <div>{{ item.content }}</div>
            </div>
          </div>
        </v-timeline-item>
      </v-timeline> 
      <v-dialog
        v-model="dialog"
        class="memo-dialog"
      >
        <div class="memo-dialog memo-bg">
          <textarea name="" id="" cols="30" rows="10" wrap="hard" v-model="create_content" placeholder="텍스트를 입력하세요"></textarea>
          <div>
            <v-icon size="large" @click="create">mdi-check</v-icon>
            <!-- <v-icon size="large" @click="dialog=false">mdi-close</v-icon> -->
          </div>
        </div>
      </v-dialog>
    </div>
  </div>
</template>

<script>
export default {
    name: 'EventProgress',
    data() {
      return {
        event: {
          event_id: 1,
          category: '알고리즘 IM형',
          start_date: new Date(2023, 0, 16),
          end_date: new Date(2023,1,12)
        },
        progress: [
          {progress_id: 1, event_id: 1, date: new Date(2023, 0, 17), content: '열심히 했따ㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏ', photo: 'url'},
          {progress_id: 2, event_id: 1, date: new Date(2023, 0, 18), content: '안녕하세요 111111111111', photo: 'url'},
          {progress_id: 3, event_id: 1, date: new Date(2023, 0, 19), content: '안녕하이 2222222222222222222', photo: 'url'},
          {progress_id: 4, event_id: 1, date: new Date(2023, 0, 24), content: '열심히 했따ㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏ', photo: 'url'},
          {progress_id: 5, event_id: 1, date: new Date(2023, 0, 26), content: '열심히 했따ㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏ', photo: 'url'},
          {progress_id: 6, event_id: 1, date: new Date(2023, 0, 27), content: '열심히 했따ㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏ', photo: 'url'},
          {progress_id: 7, event_id: 1, date: new Date(2023, 0, 29), content: '열심히 했따ㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏ', photo: 'url'},
          {progress_id: 8, event_id: 1, date: new Date(2023, 0, 30), content: '열심히 했따ㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏ', photo: 'url'},
          {progress_id: 9, event_id: 1, date: new Date(2023, 1, 1), content: '열심히 했따ㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏ', photo: 'url'},
          {progress_id: 10, event_id: 1, date: new Date(2023, 1, 2), content: '열심히 했따ㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏ', photo: 'url'},
          {progress_id: 11, event_id: 1, date: new Date(2023, 1, 4), content: '열심히 했따ㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏ', photo: 'url'},
          {progress_id: 12, event_id: 1, date: new Date(2023, 1, 5), content: '열심히 했따ㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏ', photo: 'url'},
          {progress_id: 13, event_id: 1, date: new Date(2023, 1, 7), content: '그래그래', photo: 'url'},
          {progress_id: 14, event_id: 1, date: new Date(2023, 1, 10), content: '열심히 했따ㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏ', photo: 'url'},
          {progress_id: 15, event_id: 1, date: new Date(2023, 1, 11), content: '화이팅이다', photo: 'url'},
        ],
        grass: [],
        period: 0,
        week: 0,
        rest: 0,
        items: [],
        dialog: false,
        create_content: '',
        day: ['일', '월', '화', '수', '목', '금', '토'],
        now_idx: 0

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
      },
    },
    methods: {
      getDateDiff(d1, d2) {
        // d1이 시작 날짜, d2가 종료 날짜
        const diffDate = d2.getTime() - d1.getTime();
        return diffDate / (1000 * 60 * 60 * 24) + 1; // 밀리세컨 * 초 * 분 * 시 = 일
      },
      addDays(date, days) {
        const clone = new Date(date);
        clone.setDate(date.getDate() + days)
        return clone;
      },
      create() {
        this.items[this.now_idx].content = this.create_content;
        console.log(this.create_content);
        this.dialog = false;
      },
      date_after(i) {
        const year = i.getFullYear();
        const month = i.getMonth() + 1;
        const date = i.getDate();
        const day = i.getDay();
        return `${year}-${month >= 10 ? month : '0' + month}-${date >= 10 ? date : '0' + date} (${this.day[day]})`;
      }
    },
    created() {
      const st = this.event.start_date;
      const ed = this.event.end_date;
      this.period = this.getDateDiff(st, ed);
      this.week = parseInt(this.period / 10);
      this.rest = this.period % 10;

      let idx = 0;
      for (let i=0; i<this.period ;i++) {
        if (idx == this.progress.length){
          break;
        }
        const target = this.addDays(st, i);
        if (this.progress[idx].date.toLocaleDateString() == target.toLocaleDateString()) {
          this.grass.push('done');
          const str = this.date_after(this.progress[idx].date);
          this.items.push({
            date: str,
            content: this.progress[idx].content
          })
          idx += 1;
        } else {
          this.grass.push('not');
        }
      }
      this.items.reverse()
      console.log(this.items)
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
  flex-direction: column;
  align-items: center;
  /* justify-content: center; */
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
  flex-wrap: wrap;
  width: 600px;
  margin: 10px 0;
}

.not {
  background-color: rgb(241, 241, 241);
  border: 2px solid rgb(192, 192, 192);
  border-radius: 2px;
  height: 35px;
  width: 35px;
  margin-right: 25px;
  margin-bottom: 10px;
}

.done {
  /* background-image: url('../../assets/images/memo_grass.png'); */
  /* background-image: url('../../assets/images/memo_grass2.png'); */
  background-image: url('../../assets/images/memo_yellow_size.png');
  width: 35px;
  height: 35px;
  margin-right: 25px;
  margin-bottom: 10px;
}

.progress {
  margin-top: 100px;
}

.progress-item {
  margin-bottom: 30px;
}

.memo-box {
  display: flex;
  align-items: center;
  font-family: event;
  font-size: 25px;
}
.memo-date {
  width: 200px;
}

.memo {
  background-image: url('../../assets/images/memo_large.png');
  /* margin-left: 200px; */
  width: 450px;
  height: 410px;
  padding: 50px 60px;
}

.memo :hover {
  cursor: pointer;
}

.memo-dialog {
  display: flex;
  width: 730px;
  height: 710px;
}

.memo-bg {
  background-image: url('../../assets/images/memo_create_bg.png');
  font-family: event;
  padding: 50px;
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
}

.memo-bg >textarea {
  width: 93%;
  height: 90%;
  font-size: 25px;
  outline: none;
  resize: none;
}

.icon {
  height: 30px;
  width: 30px;
}
</style>