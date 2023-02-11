<template>
  <div class="container">
    <div @click="makePDF">@@@@@@@@@@@@@@@@@@@@2</div>
    <div class="print">
      <div class="header">
        <div class="title">
          <div class="event-title">{{ growth.category }}</div>
          <div class="event-date">{{ date_to_str(growth.eventDate.startDate, growth.eventDate.endDate) }}</div>
          <div class="show-member-list" @click="member = true">
            <div v-if="growthUsers.length == 0"> {{ growth.user.name }} 님 참여중 <v-icon>mdi-account-multiple-plus</v-icon></div>
            <div v-else> {{ growth.user.name }} 님 외 {{ growthUsers.length }}명 참여중 <v-icon>mdi-account-multiple-plus</v-icon></div>
          </div>
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
              <div v-for="grass in log" :key="grass.idx">
                <div v-if="!grass.written" class="not"></div>
                <div v-else :class="`done ${grass.date}`" @click="check($event)">
                  <v-tooltip activator="parent" location="bottom">{{ grass.date }}</v-tooltip>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="progress">
        <v-timeline side="end" align="center" line-thickness="5">
          <v-timeline-item v-show="!today" class="progress-item" dot-color="rgb(255, 225, 121)" size="small">
            <div class="memo-box">
              <div class="memo-date">{{date_after(new Date())}}</div>
              <div class="memo" @click="dialog = true, progressCreate()">
                <div>텍스트를 입력하세요</div>
              </div>
            </div>
          </v-timeline-item>
          <v-timeline-item
            class="progress-item"
            v-for=" progress in [...this.firstProgress].reverse()"
            dot-color="rgb(255, 225, 121)"
            :key="progress.progressDto.progressId"
            size="small"
          >
            <div class="memo-box" :id="progress.date">
              <div class="memo-date">{{progress.date}}</div>
              <div v-if="progress.progressDto.user.id == loginUser.id" class="memo" @click="dialog = true, progressDetail(progress.date)">
                <div v-html="progress.progressDto.content"></div>
              </div>
              <div v-else class="memo isMine" @click="dialog = true, progressDetail(progress.date)">
                <div v-html="progress.progressDto.content"></div>
              </div>
            </div>
          </v-timeline-item>
        </v-timeline> 

        <!-- 여기부터 이벤트 유저 모달창 -->
        <v-dialog
          v-model="member"
          class="member-dialog"
        >
          <v-card class="member-dialog member-box">
            <v-card-title class="member-title"><v-icon>mdi-account-multiple</v-icon> 참여 목록</v-card-title>
            <div class="member-list">
              <v-avatar>
                <v-img :src="require(`@/assets/profiles/scale (${growth.user.image}).png`)"></v-img>
              </v-avatar>
              <span style="margin: 0px 10px">{{ growth.user.name }}</span>
              <v-chip color="#FF0A54">호스트</v-chip>
            </div>
            <div v-for="member in growthUsers" :key="member.id" class="member-list">
              <v-avatar>
                <v-img :src="require(`@/assets/profiles/scale (${member.image}).png`)"></v-img>
              </v-avatar>
              <span style="margin: 0px 10px">{{ member.name }}</span>
              <div class="member">
                <v-chip color="#2d8bff">멤버</v-chip>
                <v-icon v-if="is_host" color="red" class="member-delete" @click="member_delete(member.email)">mdi-close</v-icon>
              </div>
            </div>
            <v-card-actions style="justify-content:space-between" class="hover_cursor" @click="searchSet(), show = !show">
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
              <div v-show="show" style="text-align: center;">
                <v-divider></v-divider>
                <v-text-field variant="outlined" v-model:model-value="searchText" placeholder="유저를 검색해 주세요" prepend-inner-icon="mdi-magnify" @keyup="search"></v-text-field>
                <div class="search-list">
                  <div v-for="user in searchUser" :key="user.email" class="search-result">
                    <div>
                      {{ user.name }}({{ user.email }})
                    </div>
                    <v-btn color="#FF0A54" variant="text" @click="addEventUser(user.id)" icon="mdi-send"></v-btn>
                  </div>
                </div>
              </div>
            </v-expand-transition>
          </v-card>
        </v-dialog>

        <!-- 여기부터는 포스트잇 화면 -->
        <v-dialog
          v-model="dialog"
          class="memo-dialog"
        >
          <router-view></router-view>
        </v-dialog>
      </div>
      <div class="navi" data-html2canvas-ignore="true">
        <v-btn variant="outlined" icon="mdi-chevron-up" @click="pageUp"></v-btn>
        <v-btn variant="outlined" icon="mdi-chevron-down" @click="pageDown"></v-btn>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex';
import html2canvas from 'html2canvas';
import jsPDF from 'jspdf';

export default {
    name: 'GrowthProgress',
    data() {
      return {
        growthId: 0,
        allUsers: [],
        member: false,
        dialog: false,
        day: ['일', '월', '화', '수', '목', '금', '토'],
        show: false,
        is_host: false,
        search_user: null,
        searchText: '',
        propTitle: 'mypdf',
      }
    },
    computed: {
      ...mapState(['loginUser']),
      ...mapState('growth', [ 'today', 'growth', 'searchUser', 'growthUsers', 'firstProgress', 'log']),
      
      change_image(id){
        return `@assets/profiles/scale (${id}).png`;
      }
    },
    methods: {
      progressCreate(){
        this.$router.push({name: 'ProgressCreate'})
      },
      progressDetail(date){
        const data = {
          growthId: this.growthId,
          date: date
        }
        this.$store.dispatch('growth/getDateProgress', data)
      },
      date_to_str(st, ed) {
        const arr1 = st.split('-');
        const arr2 = ed.split('-');
        const year1 = arr1[0];
        const month1 = parseInt(arr1[1]);
        const date1 = parseInt(arr1[2]);
        const year2 = arr2[0];
        const month2 = parseInt(arr2[1]);
        const date2 = parseInt(arr2[2]);
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
      today_string(){
        const i = new Date()
        const year = i.getFullYear();
        const month = i.getMonth() + 1;
        const date = i.getDate();
        return `${year}-${month >= 10 ? month : '0' + month}-${date >= 10 ? date : '0' + date}`;

      },
      member_delete(email){
        console.log(email, this.event.event_id);
        // this.$store.dispatch('deleteEventUser', this.event.event_id, email);
      },
      addEventUser(id){
          const data = {
            growthId: this.growth.growthId,
            userId: id,
          }
          console.log(data);
          this.$store.dispatch('growth/addGrowthUser', data);

          // 추가하기 버튼 누르면 어디까지 닫아야 하남,,,,??
          // this.show = false;
          // this.member = false;
        
      },
      check(event){
        // console.log(event.target.classList)
        if(event.target.classList.contains('done')){
          const go = event.target.classList[1]
          // console.log(go)
          // console.log(this.$refs[go])
          // this.$refs[go].scrollIntoView({behavior: "smooth", block: 'center'})
          // console.log(go)
          // this.$refs['state3'].scrollIntoView({behavior: "smooth"})
          // this.$refs['bottom'].scrollIntoView({behavior: "smooth"})
          document.getElementById(go).scrollIntoView({behavior: "smooth", block: 'center'})
          // window.scrollTo(0,100)
        }
      },
      pageUp() {
        window.scrollTo({top: 0, left: 0, behavior: 'smooth'});
      },
      pageDown() {
        window.scrollTo({left: 0, top: document.body.scrollHeight, behavior: 'smooth'});
      },
      makePDF () {
			window.html2canvas = html2canvas //Vue.js 특성상 window 객체에 직접 할당해야한다.
			let that = this
			let pdf = new jsPDF('p', 'mm', 'a4')
			let canvas = pdf.canvas
			const pageWidth = 210 //캔버스 너비 mm
			const pageHeight = 295 //캔버스 높이 mm
			canvas.width = pageWidth

			let ele = document.querySelector(".print")
			let width = ele.offsetWidth // 셀렉트한 요소의 px 너비
			let height = ele.offsetHeight // 셀렉트한 요소의 px 높이
			let imgHeight = pageWidth * height/width // 이미지 높이값 px to mm 변환

			if(!ele){
				console.warn('not exist.')
				return false
			}
			html2canvas(ele).then((canvas) => {
					let position = 0
					const imgData = canvas.toDataURL('image/png')
					pdf.addImage(imgData, 'png', 0, position, pageWidth, imgHeight, undefined, 'slow')
          
					//Paging 처리
					let heightLeft = imgHeight //페이징 처리를 위해 남은 페이지 높이 세팅.
					heightLeft -= pageHeight
					while (heightLeft >= 0) {
            position = heightLeft - imgHeight
						pdf.addPage();
						pdf.addImage(imgData, 'png', 0, position, pageWidth, imgHeight)
						heightLeft -= pageHeight
					}
          
					pdf.save(that.propTitle.toLowerCase() +'.pdf')
				},
        
			);	

		},
      search() {
        if (this.searchText){
          const data = {
            growthId: this.growth.growthId,
            userName: this.searchText
          }
          this.$store.dispatch('growth/searchUser', data)
        } else {
          this.$store.commit('growth/SEARCH_USER_RESET')
        }
      },
      searchSet() {
        if(this.show){
          this.$store.commit('growth/SEARCH_USER_RESET')
        }
      }
    },
    created() {
      // 파람스로 이벤트 아이디 추출
      this.growthId = this.$route.params.growthId;

      // if(this.firstProgress[this.firstProgress.length-1].date == this.today_string()){
      //   this.today = true;
      // }else{
      //   this.today = false;
      // }


      console.log(this.loginUser.id)
      console.log(this.growth.user.id)
      if (this.loginUser.id == this.growth.user.id) {
        this.is_host = true
        console.log(this.is_host)
      }
    },
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
  margin-top: 50px;
  height: 100%;
  width:100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  /* justify-content: center; */
}

.print {
  width: 100%;
  padding-top: 100px;
}
.title {
  text-align: center;
  display: flex;
  flex-direction: column;
  align-items: center;
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

.done:hover{
  cursor: pointer;
}

.show-member-list {
  border: solid 1px rgb(160, 160, 160);
  width: 70%;
  height: 40px;
  border-radius: 30px;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 10px;
  cursor: pointer;
}

.show-member-list:hover {
  background-color: rgba(128, 128, 128, 0.168);
  border: none;
  color: black;
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
  display: flex;
  align-items: center;
  height: 60px;
  padding: 0.5rem;
}

.member {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 310px;
}

.member-delete{
  height: 40px;
  width: 40px;
  border-radius: 50%;
  cursor: pointer;
}

.member-delete:hover{
  background-color: rgba(255, 182, 182, 0.34);
}

.search-user div {
  font-family: appleL;
}


.search-list {
  font-size: 16px;
}

.search-result {
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 50px;
  padding: 0 10px;
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
  font-size: 22px;
}
.memo-date {
  width: 200px;
  font-size: 20px;
  font-family: appleL;
}

.memo {
  /* background-image: url('../../assets/images/memo_large.png'); */
  background-image: url('../../assets/images/post-it/default.png');
  /* background-color: rgb(255, 255, 177);
  box-shadow: 1px 1px 10px 0.1px rgba(125, 124, 83, 0.738); */

  /* margin-left: 200px; */
  width: 450px;
  height: 480px;
  /* padding: 50px 60px; */
}

.isMine {
  background-image: url('../../assets/images/post-it/default2.png');
}

.memo>div {
  width: 330px;
  height: 300px;
  margin: 110px 70px;
  overflow: hidden;
  word-wrap: break-word;
}

/* .memo>div::-webkit-scrollbar {
  background: none;
  width: 5px;
  height: 10px;
}

.memo>div::-webkit-scrollbar-thumb {
  background-color: #7d7d7d4b;
  border-radius: 10px;
} */

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
  width: 599px;
  height: 620px;
}

.pink {
  background-color: rgb(255, 199, 224);
}

.writer {
  display: flex;
  font-family: appleL;
  font-size: 20px;
  justify-content: space-between;
  align-items: center;
  width:100%;
}

.writer>div{
  display: flex;
  align-items: center;
}

.detail-form {
  width: 100%;
  height: 100%;
  font-size: 25px;
}

.memo-create {
  width: 650px;
  height: 700px;
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
  margin-top: 10px;
  top: 610px;
}

.heart {
  display: flex;
  align-items: center;
  font-family: appleB;
  font-size: 30px;
}

.heart>button{
  margin: 0 10px;
}

.navi {
  display: flex;
  flex-direction: column;
  position: fixed;
  right: 8%;
  bottom: 8%;
  
}
.navi button {
  margin: 5px 0;
  color: grey;
}
</style>