<template>
  <div>
    <div class="row">
      <div class="col-md-12">
        <h1>Horizontal Timeline with Swiper</h1>
        Credit : 
        <ul>
          <li>Horizontal timeline CSS based on <a href="https://codepen.io/abhisharma2/pen/vEKWVo">https://codepen.io/abhisharma2/pen/vEKWVo</a> with a few modifications.</li>  
          <li>Website development step <a href="https://xbsoftware.com/blog/website-development-process-full-guide/">https://xbsoftware.com/blog/website-development-process-full-guide/</a></li>
          <li>Swiper Grab Cursor <a href="http://idangero.us/swiper/demos/12-grab-cursor.html">http://idangero.us/swiper/demos/12-grab-cursor.html</a></li>
        </ul>
        <swiper v-bind="swiperOptions" class="swiper-container" @swiper="setSwiperRef">
          <p class="swiper-control">
            <button type="button" class="btn btn-default btn-sm prev-slide">Prev</button>
            <button type="button" class="btn btn-default btn-sm next-slide">Next</button>
          </p>
          <div class="swiper-wrapper timeline">
            <div class="swiper-slide" v-for="item in steps" :key="item.dateLabel">
              <div class="timestamp">
                <span class="date">{{item.dateLabel}}</span>
              </div>
              <div class="status">
                <span>{{item.title}}</span>
              </div>
            </div>
          </div>
          <!-- Add Pagination -->
          <div class="swiper-pagination"></div>
        </swiper>
      </div>
    </div>
  </div>
</template>
HTML
<script>
const data = [
  { dateLabel: 'January 2017', title: 'Gathering Information' },
  { dateLabel: 'February 2017', title: 'Planning' },
  { dateLabel: 'March 2017', title: 'Design' },
  { dateLabel: 'April 2017', title: 'Content Writing and Assembly' },
  { dateLabel: 'May 2017', title: 'Coding' },
  { dateLabel: 'June 2017', title: 'Testing, Review & Launch' },
  { dateLabel: 'July 2017', title: 'Maintenance' }
];
import { Swiper } from "swiper/vue";
// import 'swiper/swiper.scss';

export default {
  name: 'TimeLine',
  components: {
    Swiper
  },
  data() {
    return {
      steps: data,
      swiperOptions: {
        slidesPerView: 3,
        grabCursor: true,
        paginationClickable: true,
        nextButton: '.next-slide',
        prevButton: '.prev-slide',
      }
    }
  },
  
}
</script>

<style>
body {
  background: #e8eeff;
}
#app {
  padding: 50px 0;
}
.timeline {
  margin: 50px 0;
  list-style-type: none;
  display: flex;
  padding: 0;
  text-align: center;
}
.timeline li {
  transition: all 200ms ease-in;
}
.timestamp {
  width: 200px;
  margin-bottom: 20px;
  padding: 0px 40px;
  display: flex;
  flex-direction: column;
  align-items: center;
  font-weight: 100; 
}
.status {
  padding: 0px 40px;
  display: flex;
  justify-content: center;
  border-top: 4px solid #3e70ff;
  position: relative;
  transition: all 200ms ease-in ;
}
  
.status span {
  font-weight: 600;
  padding-top: 20px;
}
.status span:before {
  content: '';
  width: 25px;
  height: 25px;
  background-color: #e8eeff;
  border-radius: 25px;
  border: 4px solid #3e70ff;
  position: absolute;
  top: -15px;
  left: 42%;
  transition: all 200ms ease-in;
}
.swiper-control {
  text-align: right;
}

.swiper-container {
  width: 100%;
  height: 250px;
  margin: 50px 0;
  overflow: hidden;
  padding: 0 20px 30px 20px;
}
.swiper-slide {
  width: 200px;
  text-align: center;
  font-size: 18px;
}
.swiper-slide:nth-child(2n) {
  width: 40%;
}
.swiper-slide:nth-child(3n) {
  width: 20%;
}
</style>