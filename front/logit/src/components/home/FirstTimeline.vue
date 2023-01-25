<template>
  <div class="box">
      <div class="welcome">타임라인에 일정을 추가해보세요</div>
      <div class="bar">
          <div class="hori-bar" v-for="index in 7" :key="index">
              <div class="today-date" v-if="index == 4">
                  {{ date[index-1] }}
              </div>
              <div class="date" v-else>
                  {{ date[index-1] }}
              </div>
              <span class="circle">
                  <!-- <div class="add-event nosee"><button>성장여정추가</button></div> -->
                  <div class="hover"><button @click="show(index)">+</button></div>
                  <!-- <div class="add-job nosee"><button>취업여정추가</button></div> -->
              </span>
          </div>
      </div>
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
export default {
  name: 'FirstTimeline',
  data() {
      return {
          date: [],
          day: ['일', '월', '화', '수', '목', '금', '토'],
          is_show: false,
          choose_date: '',
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
      }
  },
  created(){
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
.box {
  margin: 0 50px;
}

.bar {
  display: flex;
}

.hori-bar {
  margin-top: 50px;
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
  font-size: x-small;
}
</style>