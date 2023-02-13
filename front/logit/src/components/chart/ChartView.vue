<template >
  <div class="chart-box">
    <div class="container" style="width:600px">
      <h1>면접 질문 유형 통계</h1>
      <div >
      <Doughnut :data="data" :options="options"  />
      </div>

      <h1>코테 유형 통계</h1>
      <div>
        <Doughnut :data="codingTestData" :options="options" />
      </div>

      <h1>내 지원 통계</h1>
      <div>
        <Doughnut :data="myApplyData" :options="options" />
      </div>

      <div>
      </div>
    </div>
  </div>
</template>


<script>
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js'
import { Doughnut } from 'vue-chartjs';
import * as chartConfig from './chartConfig'
import {codingTestData} from "./chartConfig";
import {myApplyData} from "./chartConfig";


ChartJS.register(ArcElement, Tooltip, Legend)

export default {
  name: 'App',
  computed: {
    codingTestData() {
      return codingTestData
    },

    myApplyData(){
      return myApplyData
    }
  },
  components: {Doughnut},

  data() {
    return chartConfig
  }
}
</script>

<style scoped>
.container {
  margin-top: 100px;
  background-color: aquamarine;
}
.chart-box {
  width: 100%;
  display: flex;
  justify-content: center;
}
</style>