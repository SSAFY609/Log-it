<template>
  <div class="container">
    <div class="title"> {{ loginUser.name }} 님의 취업여정 통계입니다.</div>
    <div class="subtitle">{{ loginUser.name }}님의 통계와 SSAFY인들의 전체 현황을 비교해 Log-it에서 취업여정을 관리해보세요.</div>
    <div class="content-box">
        <div class="left">
            <div class="my-statistics">
                <div class="event all">
                    <div><img class="event-img" :src="require(`@/assets/images/달력1.png`)"></div>
                    <div style="width:50%">
                        <div class="event-title">내 취업여정</div>
                        <div class="event-cnt"><div class="event-num">{{ ing_cnt + complete_cnt }}</div>개</div>
                    </div>
                </div>
                <div class="event ing">
                    <div><img class="event-img" :src="require(`@/assets/images/달력2.png`)"></div>
                    <div style="width:50%">
                        <div class="event-title">진행중인 취업여정</div>
                        <div class="event-cnt"><div class="event-num">{{ ing_cnt }}</div>개</div>
                    </div>
                </div>
                <div class="event complete">
                    <div><img class="event-img" :src="require(`@/assets/images/달력3.png`)"></div>
                    <div style="width:50%">
                        <div class="event-title">완료된 취업여정</div>
                        <div class="event-cnt"><div class="event-num">{{ complete_cnt }}</div>개</div>
                    </div>
                </div>
            </div>
            <div class="progress-chart">
                <div class="my-progress">내 진행현황</div>
                <div class="progress-box">
                    <div class="progress">
                        <div class="progress-title">서류</div>
                        <v-progress-circular
                        :rotate="360"
                        :size="200"
                        :width="24"
                        :model-value="myProgress['document']['percentage']"
                        color="red"
                        class="progress-circle"
                        >
                        {{ myProgress['document']['percentage'] }}%
                        </v-progress-circular>
                        <div>{{ myProgress['document']['pass'] }}/{{ myProgress['document']['tot'] }}</div>
                    </div>
                    <div class="progress">
                        <div class="progress-title">코딩테스트</div>
                        <v-progress-circular
                        :rotate="360"
                        :size="200"
                        :width="24"
                        :model-value="myProgress['codingtest']['percentage']"
                        color="orange"
                        class="progress-circle"
                        >
                        {{ myProgress['codingtest']['percentage'] }}%
                        </v-progress-circular>
                        <div>{{ myProgress['codingtest']['pass'] }}/{{ myProgress['codingtest']['tot'] }}</div>
                    </div>
                    <div class="progress">
                        <div class="progress-title">면접</div>
                        <v-progress-circular
                        :rotate="360"
                        :size="200"
                        :width="24"
                        :model-value="myProgress['interview']['percentage']"
                        color="green"
                        class="progress-circle"
                        >
                        {{ myProgress['interview']['percentage'] }}%
                        </v-progress-circular>
                        <div>{{ myProgress['interview']['pass'] }}/{{ myProgress['interview']['tot'] }}</div>
                    </div>
                </div>
            </div>
        </div>
        <div class="right">
            <div class="rank-box">
                <div class="rank-subtitle">SSAFY인들이</div>
                <div class="rank-title">가장 많이 지원한 기업 순위</div>
                <div v-for="(r,index) in companyRank" :key="index" class="rank-company-box">
                    <v-divider></v-divider>
                    <div class="rank-company">
                        <div style="display:flex">
                            <div class="rank-index">{{ index+1 }}</div>
                            <div>{{ r.name }}</div>
                        </div>
                        <div>{{ r.count }}</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="ct-box">
        <div class="ct-title">전체 코딩테스트 유형 그래프</div>
        <div class="ct-subtitle">전체 사용자들이 기록한 모든 회사의 알고리즘 유형 횟수입니다.</div>
        <div class="ct-content">
            <div class="ct-chart">
                <Bar :data="myData" :style="myStyle" :options="myOption"></Bar>
            </div>
        </div>
    </div>
  </div>
</template>

<script>
import { Bar } from 'vue-chartjs';
import { Chart as ChartJS, Title, BarElement, CategoryScale, LinearScale } from 'chart.js'
import { mapState } from 'vuex';
ChartJS.register(Title, BarElement, CategoryScale, LinearScale)


export default {
    name: 'StatisticsPage',
    components: {
        Bar,
    },
    data(){
        return {
            myData: {
                labels:  [],
                datasets: [
                    {
                        // backgroundColor: 'rgba(255,185,185, 0.7)',
                        backgroundColor: ['red', 'green', 'gold'],
                        data: [],
                    }
                ],
            },
            // myData: {},
            myOption: {
                responsive: false,
                gridLines: {
                    display: false,
                }
            },
            myStyle: {
                height: '100%',
                position: 'relative',
                width: '100%'

            }
        }
    },
    computed: {
        ...mapState(['loginUser']),
        ...mapState('statistics', ['ing_cnt', 'complete_cnt', 'companyRank', 'myProgress', 'algorithm', 'algorithm_cnt'])
    },
    methods: {
        // setChart() {
        //     let data = this.algorithm_cnt;
        //     let datasets = []
        //     datasets.push({
        //         backgroundColor: 'pink',
        //         data : data
        //     })

        //     this.myData = {
        //         labels: this.algorithm,
        //         datasets: datasets
        //     }
        //     console.log(this.myData)
        //     this.renderChart(this.myData, this.options)
        // }
    },

    created() {
        this.myData.labels = this.algorithm;
        this.myData.datasets[0].data = this.algorithm_cnt
    }

}
</script>

<style scoped>
.container{
    width: 100%;
    height: 100%;
    background: rgb(243, 243, 243);
    display: flex;
    flex-direction: column;
    align-items: center;
    /* justify-content: center; */
    padding: 150px 100px;
}

.title{
    font-size: 50px;
    font-family: appleB;
    /* margin-bottom: 50px; */
}

.subtitle{
    font-size: 20px;
    margin-bottom: 40px;
}

.content-box {
    width: 100%;
    height: 100%;
    display: flex;
}

.left {
    height: 100%;
    width: 70%;
    /* background: yellow; */
    display: flex;
    flex-direction: column;
}

.right{
    height: 100.5%;
    width: 30%;
    /* background: green; */
    display: flex;
    justify-content: center;
    padding-top: 1.2%;

}
.my-statistics{
    height: 30%;
    width: 100%;
    /* background: white; */
    display: flex;
    justify-content: space-evenly;
    align-items: center;
}

.event {
    display: flex;
    /* justify-content: center; */
    align-items: center;
    height: 80%;
    width: 30%;
    /* background: rgb(172, 172, 172); */
    background-color: white;
    border-radius: 10px;
}

.event-img {
    font-size: 40px;
    height: 50px;
    width: auto;
    margin: 0 30px;
    color: rgb(176, 176, 176);
}

.event-title{
    /* width: 100px; */
    font-size: 20px;
    font-family: appleM;
    text-align: center;
}

.event-cnt {
    display: flex;
    justify-content: end;
    align-items: center;
    font-size: 20px;
}

.event-num {
    color: red;
    font-family: galaxy;
    font-size: 36px;
    margin-right: 20px;
}

.rank-box {
    width: 90%;
    height: 95%;
    padding: 5%;
    background: white;
    border-radius: 10px;
    display: flex;
    flex-direction: column;
    /* align-items: center; */
}

.rank-subtitle{
    font-size: 18px;
    margin-top: 20px;
}

.rank-title{
    font-size: 28px;
    font-family: appleB;
    /* margin-top: 20px; */
    margin-bottom: 30px;
}

.rank-company-box{
    height: 60px;
    margin-bottom: 10px;
    /* background-color: rgb(225, 225, 225); */
    display: flex;
    flex-direction: column;
    font-size: 20px;
    /* align-items: center; */
    justify-content: center;
}

.rank-company-box>hr{
    margin-bottom: 10px;
}

.rank-company{
    display: flex;
    padding: 0 10px;
    justify-content: space-between;
}

.rank-index{
    font-family: appleB;
    font-size: 24px;
    margin-right: 20px;
}

.my-progress {
    position: relative;
    top: 30px;
    font-size: 18px;
    left: -41%;
    width: 100px;
    height: 34px;
    color: white;
    background: #7c7c7c;
    display: flex;
    justify-content: center;
    align-items: center;
    border-radius: 6px;
}

.progress-chart {
    height: 70%;
    width: 100%;
    /* background: yellowgreen; */
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    margin-top: -24px;
}

.progress-box {
    width: 96%;
    height: 90%;
    margin-top: 14px;
    border-radius: 10px;
    background: white;
    display: flex;
    align-items: center;
    justify-content: space-evenly;
}
.progress {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
}

.progress-title{
    font-family: appleM;
    font-size: 22px;
}

.progress-circle {
    font-size: 24px;
    font-family: appleM;
    margin: 20px 0;
}

.ct-box{
    margin-top: 200px;
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 100%;
}

.ct-content {
    width: 97%;
    height: 600px;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    background: white;
    border-radius: 10px;
    padding: 4% 0;
}

.ct-title{
    font-size: 40px;
    font-family: appleB;
}

.ct-subtitle{
    font-size: 18px;
    margin-bottom: 30px;
}

.ct-chart{
    width: 90%;
    height: 95%;
    display: flex;
    justify-content: center;
    align-items: center;

}

</style>