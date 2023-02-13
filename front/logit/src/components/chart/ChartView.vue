<template>
  <div class="container">
    <Doughnut :data="data" :options="options" />
  </div>
</template>


<script>
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js'
import { Doughnut } from 'vue-chartjs';
import * as chartConfig from './chartConfig'

ChartJS.register(ArcElement, Tooltip, Legend)

export default {
  name: 'App',
  components: {
    Doughnut,
  },
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
</style>