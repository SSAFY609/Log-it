<template>
  <div class="container">
    <div class="header">
      <div class="title">
        <div class="event-title">{{ event.name }}</div>
        <div class="event-date">{{ date_to_str(event.start_date, event.end_date) }}</div>
        <div v-if="eventUsers.users.length == 1"> {{ eventUsers.owner.name }} 님 참여중 <v-icon @click="dialog1 = true">mdi-account-multiple-plus</v-icon></div>
        <div v-else> {{ eventUsers.owner.name }} 님 외 {{ eventUsers.users.length - 1 }}명 참여중 <v-icon @click="member = true">mdi-account-multiple-plus</v-icon></div>
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
        <v-timeline-item v-if="!today" class="progress-item" dot-color="rgb(255, 225, 121)" size="small">
          <div class="memo-box">
            <div class="memo-date">{{date_after(new Date())}}</div>
            <div class="memo" @click="dialog = true, update_mode = true, now_idx = -1">
              <div>텍스트를 입력하세요</div>
            </div>
          </div>
        </v-timeline-item>
        <v-timeline-item
          class="progress-item"
          v-for=" (item, index) in copy_progress"
          dot-color="rgb(255, 225, 121)"
          :key="item.id"
          size="small"
        >
          <div class="memo-box">
            <div class="memo-date">{{date_after(item.date)}}</div>
            <div class="memo" @click="dialog = true, create_content = item.content, now_idx = index">
              <div v-html="item.content"></div>
            </div>
          </div>
        </v-timeline-item>
      </v-timeline> 
      <v-dialog
        v-model="member"
        class="member-dialog"
      >
        <v-card class="member-dialog member-box">
          <v-card-title class="member-title">{{event.name}} 참여 목록</v-card-title>
          <div v-for="user in eventUsers.users" :key="user.user_id" class="member-list">
            <v-avatar>
              <v-img :src="require(`@/assets/profiles/scale (${user.profile}).png`)"></v-img>
            </v-avatar> 
            {{ user.name }}
            <v-chip v-if="user.name == eventUsers.owner.name">주인</v-chip>
            <v-chip v-else>멤버</v-chip>
          </div>
          <v-card-actions style="justify-content:space-between" class="hover_cursor" @click="show = !show">
            <div>
              <v-avatar><v-icon>mdi-plus</v-icon></v-avatar>
              추가하기
            </div>
            <v-btn
              :icon="show ? 'mdi-chevron-up' : 'mdi-chevron-down'"
              
              style="text-align:right"
            ></v-btn>
          </v-card-actions>
          <v-expand-transition>
            <div v-show="show">
              <v-divider></v-divider>
              <v-autocomplete
                clearable
                :items="allUsers"
                placeholder="이름 검색"
              ></v-autocomplete>
            </div>
          </v-expand-transition>
        </v-card>
      </v-dialog>
      <v-dialog
        v-model="dialog"
        class="memo-dialog"
      >
        <swiper v-if="!update_mode" 
          :effect="'cards'"
          :grabCursor="false"
          :cssMode="false"
          :modules="modules"
          class="mySwiper">
          
          <swiper-slide v-for="i in 7" :key="i" class="slide">
            <div class="memo-bg">
              <div v-if="!update_mode" style="width:100%; text-align:right;"><v-icon @click="update_mode = true">mdi-pencil</v-icon></div>
              <div v-else style="height: 25.5px"></div>
              <div class="detail-form">
                <QuillEditor 
                  class="text-editor" 
                  theme="bubble"
                  v-model:content="create_content"
                  content-type="html"
                  toolbar="essential" 
                  :read-only="true" />
              </div>
              <div class="check">
                <v-icon size="large" @click="dialog = false">mdi-close</v-icon>
              </div>
            </div>
          </swiper-slide>
        </swiper>
        <div v-else class="memo-dialog">
          <div class="detail-form memo-bg">
            <QuillEditor 
              class="text-editor" 
              theme="bubble"
              v-model:content="create_content"
              content-type="html"
              toolbar="essential" 
              placeholder="텍스트를 입력하세요"
              :read-only="false" />
            <div class="check">
              <v-icon size="large" @click="create">mdi-check</v-icon>
            </div>
          </div>
        </div>
      </v-dialog>
    </div>
  </div>
</template>

<script>
import { QuillEditor } from '@vueup/vue-quill'
import '@vueup/vue-quill/dist/vue-quill.bubble.css';
import { Swiper, SwiperSlide } from 'swiper/vue';
import { mapState } from 'vuex';
import { EffectCards } from 'swiper';

import 'swiper/css';

import 'swiper/css/effect-cards';

// import './style.css';

export default {
    name: 'EventProgress',
    data() {
      return {
        eventId: 0,
        grass: [],
        copy_progress: [],
        allUsers: [],
        period: 0,
        member: false,
        dialog: false,
        create_content: '',
        day: ['일', '월', '화', '수', '목', '금', '토'],
        update_mode: false,
        today: false,
        show: false,
      }
    },
    components: {
      QuillEditor,
      Swiper,
      SwiperSlide,
    },
    setup() {
      return {
        modules: [EffectCards],
      };
    },
    computed: {
      ...mapState('temp', ['loginUser', 'event', 'eventUsers', 'progress', 'users']),
      
      change_image(id){
        return `@assets/profiles/scale (${id}).png`;
      }
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
      },
      addDays(date, days) {
        const clone = new Date(date);
        clone.setDate(date.getDate() + days)
        return clone;
      },
      create() {
        // create
        if (this.now_idx == -1){
          //create 요청
        }
        // 나머지는 update 요청
        else{
          this.copy_progress[this.now_idx].content = this.create_content;
          console.log(this.create_content);
        }
        // this.dialog = false;
        this.update_mode = false;
      },
      date_after(i) {
        const today = new Date();
        const year = i.getFullYear();
        const month = i.getMonth() + 1;
        const date = i.getDate();
        const day = i.getDay();
        if (today.toLocaleDateString() == i.toLocaleDateString()){
          return `오늘 (${this.day[day]})`;
        }
        return `${year}-${month >= 10 ? month : '0' + month}-${date >= 10 ? date : '0' + date} (${this.day[day]})`;
      },
      update_content() {
        this.update_mode = true;
      }
    },
    created() {
      // 파람스로 이벤트 아이디 추출
      this.eventId = this.$route.params.eventId;

      // 이벤트 아이디에 해당하는 호출.....
      // this.$store.dispatch('temp/getEvent', eventId);
      // this.$store.dispatch('temp/getEventUsers', eventId);
      // this.$store.dispatch('temp/getProgress', eventId);


      // 잔디를 구성하기 위한 작업,,,,
      // 글이 있냐 없냐를 클래스로 분리,,, -> 근데 공유 일지는 어케 처리해야하징 날짜만 받아야하나,,,,,,, 후하
      // 내일 더미 데이터로 실험해 봐야 할 듯
      const st = this.event.start_date;
      const ed = this.event.end_date;
      this.period = this.getDateDiff(st, ed);
      
      let idx = 0;
      for (let i=0; i<this.period ;i++) {
        if (idx == this.progress.length){
          this.grass.push('not');
          continue
        }
        const target = this.addDays(st, i);
        if (this.progress[idx].date.toLocaleDateString() == target.toLocaleDateString()) {
          this.grass.push('done');
          idx += 1;
        } else {
          this.grass.push('not');
        }
      }

      // 잔디 확인용 테스트
      // console.log(this.grass)
      
      const today = new Date()
      for(let i=0; i<this.progress.length; i++){
        if (this.progress[i].date.toLocaleDateString() == today.toLocaleDateString()) {
          this.today = true;
          break;
        } 
      }

      this.copy_progress = [...this.progress].reverse();

      for(let i=0; i<this.users.length; i++){
        const user = `${this.users[i].name} (${this.users[i].email})`
        this.allUsers.push(user)
      }
    }



}
</script>

<style scoped>

.mySwiper {
  width: 680px;
  height: 710px;
}

.slide {
  width: 680px;
  height: 710px;
  /* background-color: white; */
}

h1 {
  font-family: galaxy;
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

.member-dialog {
  font-family: appleL;
  display: flex;
  border-radius: 15px;
  width: 500px;
}

.member-box {
  padding: 35px;
}

.member-title {
  font-family: appleB;
  font-size: 35px;
  text-align: center;
  margin-bottom: 30px;
}

.member-list {
  height: 60px;
  padding: 0.5rem;
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
  font-family: galaxy;
  font-size: 25px;
}
.memo-date {
  width: 200px;
  font-size: 20px;
  font-family: appleL;
}

.memo {
  background-image: url('../../assets/images/memo_large.png');
  /* background-color: rgb(255, 255, 177);
  box-shadow: 1px 1px 10px 0.1px rgba(125, 124, 83, 0.738); */

  /* margin-left: 200px; */
  width: 450px;
  height: 410px;
  /* padding: 50px 60px; */
}

.memo >div {
  width: 330px;
  height: 300px;
  margin: 50px 60px;
  overflow: hidden;
}

.memo :hover {
  cursor: pointer;
}

.memo-dialog {
  display: flex;
  width: 650px;
  height: 700px;
}

.memo-bg {
  /* background-image: url('../../assets/images/memo_create_bg.png'); */
  background-color: rgb(255, 255, 155);
  font-family: galaxy;
  padding: 40px;
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
  width: 600px;
  height: 620px;
}

.detail-form {
  width: 100%;
  height: 100%;
  font-size: 25px;
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

.check {
  /* position: absolute; */
  top: 610px;
}
</style>