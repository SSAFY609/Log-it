<template>
    <div style="display:flex; width:100%">
        <div style="width:280px"></div>
        <div class="container">
            <div class="title">Log-it의 타임라인 데모 페이지</div>
            <div style="font-size:24px">마우스나 버튼으로 페이지를 이동해보세요</div>
            <div class="box">
            <swiper 
            class="mySwiper"
            :modules="modules"
            @swiper="onSwiper"
            >
                <swiper-slide v-for="date in state.dates" :key="date">
                    <div class="grow">
                        <div v-for="(data, index) in date.events" :key="index" :style="`top: ${310 - 50*index}px;`" :class="`event ${data.start} ${data.period}`">
                        {{ data.title }}
                        </div>
                    </div>
                    <div class="bar">
                        <div class="hori-bar" v-for="d in date.str" :key="d">
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
                </swiper-slide>
            </swiper>
            <div class="buttons">
                <button @click="goslide(0)">&lt;&lt;</button>
                <button @click="nextSlide">&lt;</button>
                <button @click="goslide(state.slide)">오늘</button>
                <button @click="prevSlide(0)">&gt;</button>
                <button @click="goslide(-1)">&gt;&gt;</button>
            </div>
            </div>
        </div>
    </div>
</template>

<script>
import { Swiper, SwiperSlide } from "swiper/vue";
import { reactive, onBeforeMount, onMounted } from "vue";
import "swiper/css";

export default {
name: 'OpenSource',
components: {
    Swiper, SwiperSlide
},
setup(){
    const state = reactive({
        events: [
            {title: '이벤트 1', startDate: '2023-02-13', endDate: '2023-03-01'},
            {title: '이벤트 2', startDate: '2023-02-16', endDate: '2023-03-10'},
            {title: '이벤트 3', startDate: '2023-02-17', endDate: '2023-03-05'},
            {title: '이벤트 4', startDate: '2023-02-01', endDate: '2023-02-13'},
        ],
        date: [],
        dates: [],
        slide: 0,
        day: ['일', '월', '화', '수', '목', '금', '토'],
        start: ['sun', 'mon', 'tue', 'wed', 'thu', 'fri', 'sat'],
        period: ['one', 'two', 'three', 'four', 'five', 'six', 'seven'],
    })

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

    onBeforeMount(()=>{
        // 시작날짜를 기준으로 정렬
        state.events.sort(function(a, b)  {
        return str_to_date(a.startDate) - str_to_date(b.startDate);
        });

        // 최소 날짜 
        const st_str = state.events.reduce((prev, curr) => {
        const p = str_to_date(prev.startDate)
        const c = str_to_date(curr.startDate)
        return p <= c ? prev : curr;
        }).startDate

        
        const ed_str = state.events.reduce((prev, curr) => {
        const p = str_to_date(prev.endDate)
        const c = str_to_date(curr.endDate)
        return c <= p ? prev : curr;
        }).endDate

        let st = str_to_date(st_str)
        let ed = str_to_date(ed_str)

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
                events: [],
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
                const month = target.getMonth() + 1;
                const date = target.getDate();
                const day = target.getDay();
                push_date.str.push(`${month >= 10 ? month : '0' + month}/${date >= 10 ? date : '0' + date}(${state.day[day]})`);
                }
                if (i==6){
                    push_date.sat = target;
                }
            }

            // 이벤트 쪼개기
            for(let i=0; i<state.events.length; i++){
            const sd = str_to_date(state.events[i].startDate);
            const ed = str_to_date(state.events[i].endDate);
            const title = state.events[i].title;
            if (sd < push_date.sun) {
                if (ed < push_date.sun) {
                continue;
                } else if (push_date.sun <= ed && ed <= push_date.sat) {
                const week = ed.getDay();
                const event = {
                    start: state.start[0],
                    period: state.period[week],
                    title: title,
                };
                push_date.events.push(event);
                } else {
                const event = {
                    start: state.start[0],
                    period: state.period[6],
                    title: title,
                };
                push_date.events.push(event);
                }
            } else if (push_date.sun <= sd && sd <= push_date.sat){
                if(push_date.sun <= ed && ed <= push_date.sat){
                const st_week = sd.getDay();
                const ed_week = ed.getDay();
                const event = {
                    start: state.start[st_week],
                    period: state.period[ed_week - st_week],
                    title: title,
                };
                push_date.events.push(event);
                } else {
                const st_week = sd.getDay();
                const event = {
                    start: state.start[st_week],
                    period: state.period[6 - st_week],
                    title: title,
                };
                push_date.events.push(event);
                }
            } else {
                break;
            }
            }
            state.dates.push(push_date);
            new_date = addDays(new_date, 7);
            idx += 1;
        }
    })

    onMounted(()=>{
        // 오늘이 있는 페이지로 이동
        state.swiper.slideTo(state.slide)
        window.scrollTo(0,0)
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
        str_to_date,
        addDays,
        prevSlide,
        nextSlide,
        goslide,
    };
},

}
</script>

<style scoped>
.container {
margin-top: 0;
display: flex;
width: 80%;
height: 80%;
flex-direction: column;
justify-content: center;
align-items: center;
}

.title {
font-size: 40px;
font-family: appleM;
}

.box {
width: 90%;
text-align: center;
}

.swiper-slide {
width: 80%;
display: flex;
flex-direction: column;
justify-content: end;
}

.swiper {
position: static;
height: 450px;
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

.buttons {
margin-top: 30px;
}

.buttons button{
height: 50px;
width: 80px;
border: none;
border-radius: 10px;
margin: 30px 5px;
cursor: pointer;
font-size: 20px;
}

.buttons button:hover {
border-radius: 10px;
background-color: #f3f3f3;    
}

.event {
display: flex;
justify-content: center;
align-items: center;
position: absolute;
border-radius: 50px;
height: 40px;
font-size: 20px;
color: white;
cursor: pointer;
background: rgb(255,0,48) repeat-x;
background: linear-gradient(90deg, #ff0030 0%, #ff6060 20%, #ff8584 30%, #ff8584 76%, #ff0030 100%);
background-size: 300% 300%;
background-position: 1% 50%;
transition: all 1s ease;
}

.event:hover {
background-position: 99% 50%;
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


.today-date {
margin-top: 18px;
font-size: small;
}

.date {
margin-top: 20px;
font-size: medium;
}
</style>