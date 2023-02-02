<template>
  <div class="container">
    <div class="box">
        <h1 class="welcome">날짜를 선택해 ✔</h1>
        <h1 class="welcome" style="margin-bottom: 100px">타임라인에 일정을 추가해보세요</h1>
        <div class="show-btn" v-show="this.is_click">
            <div v-if="this.choose_date" style="margin-bottom: 20px; font-size:30px">
                {{ choose_date }}
            </div>
            <div>
                <span class="add-event"><v-btn>성장여정추가</v-btn></span>
                <span class="add-job"><v-btn>취업여정추가</v-btn></span>
            </div>
        </div>
        <!-- <v-btn @click="dialog=true">열기</v-btn>
        <v-dialog v-model="dialog">
          <v-card>
            <v-card-text>
              모달창열렸땀
            </v-card-text>
            <v-card-actions>
              <v-btn color="primary" block @click="dialog = false">닫기</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog> -->
        <div class="bar-box">
          <div class="bar">
              <div class="hori-bar" v-for="index in 7" :key="index">
                  <div class="today-date" v-if="index == 4">
                      {{ date[index-1] }}
                  </div>
                  <div class="date" v-else>
                      {{ date[index-1] }}
                  </div>
                  <span class="circle">
                      <v-menu open-on-click transition="slide-y-transition">
                        <template v-slot:activator="{ props }"><button class="hover" v-bind="props" style="font-size:large" @click="choose_date = real_date[index-1]">+</button></template>
                        <v-list>
                          <v-list-item class="list-item">
                            <router-link :to="{name: 'EventCreate', query: {st: date_to_str}}">성장 여정 추가</router-link>
                          </v-list-item>
                          <v-list-item class="list-item">
                            <router-link :to="{name: 'JobCreate', query: {st: date_to_str}}">취업 여정 추가</router-link>
                          </v-list-item>
                        </v-list>
                      </v-menu>
                  </span>
              </div>
          </div>
        </div>
    </div>
  </div> 
</template>

<script>
export default {
  name: 'FirstTimeline',
  data() {
      return {
          real_date: [],
          date: [],
          day: ['일', '월', '화', '수', '목', '금', '토'],
          is_show: false,
          choose_date: new Date(),
          is_click: false,
          dialog: false,
      }
  },
  computed: {
    date_to_str() {
      const year = this.choose_date.getFullYear();
      const month = this.choose_date.getMonth()+1;
      const date = this.choose_date.getDate();
      return `${year}-${month >= 10 ? month : '0' + month}-${date >= 10 ? date : '0' + date}`;
    }
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
          this.is_click = true;

          // const btn1 = document.querySelector(".add-event");
          // const btn2 = document.querySelector(".add-job");
          // btn1.classList.toggle('nosee');
          // btn2.classList.toggle('nosee');
      },
      open_menu(i) {
        console.log(i)
      }
  },
  created(){
      const today = new Date();        
      for (let i=-3; i<4; i++){
          if(i == 0){
              const day = today.getDay();
              this.real_date.push(today);
              this.date.push(`오늘(${this.day[day]})`);
          }else{
              const target = this.addDays(today, i);
              this.real_date.push(target);
              // const year = target.getFullYear();
              const month = target.getMonth() + 1;
              const date = target.getDate();
              const day = target.getDay();
              this.date.push(`${month >= 10 ? month : '0' + month}/${date >= 10 ? date : '0' + date}(${this.day[day]})`);
          }
      }
      // console.log(this.date);
  }
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

.box {
  margin-top: 150px;
  width: 70%;
  text-align: center;
}

.welcome {
  margin-bottom: 10px;
  font-family: appleB;
  font-size: 50px;
}

.bar-box {
  display: flex;
  flex-direction: column;
  justify-content: center;
}


.bar {
  display: flex;
  margin-top: 50px;
}

.hori-bar {
  margin-top: 20px;
  width: 100%;
  display: flex;
  justify-content: center;
  border-top: 7px solid #d0d0d0;
  position: relative;
  transition: all 200ms ease-in ;
}

.hover {
  color:white ;
}

.hover button {
  border: none;
  border-radius: 50%;
  background-color: white;
  color: #a6a6a6;
  text-align: center;
  font-size: medium;
}

.today-date button {
  background-color: #ffb272;
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

.list-item {
  font-family: appleL;
  font-size: 20px;
}

.list-item:hover {
  background-color: rgba(133, 133, 133, 0.473);
}

/* .show-btn {
  margin-top: 100px;
} */

.hover button {
  background-color: rgba(1, 1, 1, 0);
}

.circle {
  display: flex;
  flex-direction: column;
  content: '';
  width: 35px;
  height: 35px;
  background-color: #ffffff;
  border-radius: 50%;
  border: 6px solid #a6a6a6;
  position: absolute;
  top: -20px;
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
  width: 40px;
  height: 40px;
  border-radius: 50%;
  border: 7px solid #a6a6a6;
  top: -22px;
}

.today-date ~.circle:hover button {
  color: #ffb272;
}

.circle:hover button{
  color: #a6a6a6;
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
  margin-top: 27px;
  font-size: x-large;
}

.date {
  margin-top: 30px;
  font-size: large;
}
</style>