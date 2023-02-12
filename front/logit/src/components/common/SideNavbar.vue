<template>
  <div class="container b_transparentgray">
    <div class="logo_box">
      <router-link :to="{name: 'MainPage'}" class="logo_img_box">
        <v-img class="logo_img"
            :src="require('../../assets/images/logit_logo_text.png')"
            height="110"
      />
      </router-link>
      
    <div @click="closeSidebar" class="slider_box b_lightgray slider_hover lay2">
        <v-icon class="silder_icon f_white">mdi-chevron-double-left</v-icon>
      </div>
    </div>

    <!-- 사이드바 메뉴 영역-->
    <div class="menu_container lay1">
      <div class="menu_item_box lay2 hover_cursor">
        <div class="menu_icon_box lay2 btn_clicked" @click="btnClicked('MainPage')">
          <v-icon class="menu_icon f_icon lay3 btn_clicked2">mdi-layers</v-icon>  
          <div class="menu_text_box f_darkgray lay3 btn_clicked2">홈</div>
        </div>
      </div>
      <div class="menu_item_box lay2 hover_cursor">
        <div class="menu_icon_box lay3" @click="btnClicked('SearchResult')">
          <v-icon class="menu_icon f_icon">mdi-magnify</v-icon>  
          <div class="menu_text_box f_darkgray lay3">검색</div>
        </div>
      </div>
      <div class="menu_item_box lay2 hover_cursor">
        <div class="menu_icon_box lay3" @click="btnClicked('GrowthList')">
          <v-icon class="menu_icon f_icon">mdi-list-status</v-icon>  
          <div class="menu_text_box f_darkgray lay3">성장일지</div>
        </div>
      </div>
      <div class="menu_item_box lay2 hover_cursor">
        <div class="menu_icon_box lay3" @click="btnClicked('JobList')">
          <v-icon class="menu_icon f_icon">mdi-briefcase</v-icon>  
          <div class="menu_text_box f_darkgray lay3">취업일지</div>
        </div>
      </div>
      <div class="menu_item_box lay2 hover_cursor">
        <div class="menu_icon_box lay3" @click="btnClicked('ProfilePage')">
          <v-icon class="menu_icon f_icon">mdi-account-circle</v-icon>  
          <div class="menu_text_box f_darkgray lay3">프로필</div>
        </div>
      </div>
      <div class="menu_item_box lay2 hover_cursor">
        <div class="menu_icon_box lay3" @click="btnClicked">
          <v-icon class="menu_icon f_icon">mdi-poll</v-icon>  
          <div class="menu_text_box f_darkgray lay3">통계</div>
        </div>
      </div>
    </div>


    <!-- 하단 옵션 메뉴 -->

    <div class="option_menu lay3">

      <router-link :to="{name: 'PageSetting'}" class="menu_item_box2 lay2 hover_cursor">
        <div class="item_container lay1">
          <div class="menu_icon_box2 lay3">
            <v-icon class="menu_icon2 f_icon">mdi-cog</v-icon>  
          </div>
          <div class="menu_text_box2 f_darkgray lay3">설정</div>
        </div>
      </router-link>

      <router-link :to="{name: 'DeveloperList'}" class="menu_item_box2 lay2 hover_cursor">
        <div class="item_container lay1">
          <div class="menu_icon_box2 lay3">
            <v-icon class="menu_icon2 f_icon">mdi-xml</v-icon>  
          </div>
        <div class="menu_text_box2 f_darkgray lay3">Developer</div>
      </div>
      </router-link>
      
    </div>
    <div class="snackbar">
      <v-snackbar v-model="snackbar" min-height="300px" variant="tonal" location="top" color="#FF0A54" class="snackbar">
        <strong>로그인</strong>을 하고 이용해주세요.
      </v-snackbar>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'

export default {
  name: 'SideNavbar',
  data(){
    return {
      snackbar: false,
    }
  },
  computed: {
    ...mapState(['loginUser'])
  },
  methods: {

    closeSidebar() {
      
      const eventTarget1 = document.querySelector('.SideNavbar_box')
      const eventTarget2 = document.querySelector('.SideNavbar_space')

      eventTarget1.classList.toggle('nosee')
      eventTarget2.classList.toggle('nosee')
      
      this.$store.dispatch(`closeSidebar`)
    },

    btnClicked(select) {
      if(!this.loginUser.id){
        this.snackbar = true;
        return
      } 
      const target = event.target.parentElement
      const removeList = document.querySelectorAll('.menu_icon_box')

      
      removeList.forEach((element) => {
        element.classList.remove("btn_clicked")
        element.firstChild.classList.remove("btn_clicked2")
        element.lastChild.classList.remove("btn_clicked2")

      })
      target.classList.toggle('btn_clicked')
      target.firstChild.classList.toggle('btn_clicked2')
      target.lastChild.classList.toggle('btn_clicked2')

      this.$router.push({name: select})
    }
  },
}
</script>
<style scoped>
  .container {
    position: fixed;
    z-index: 200;
    border-right: 1px solid #F5F5F4;
  }
  .logo_box {
    width: 220px;
    height: 70px;
    margin-left: 14px;
    display: flex;
    align-items: center;
  }
  .slider_box {
    width: 40px;
    height: 40px;
    margin-bottom: 14px;
    margin-top: 6px;
    border-radius: 8px;
    display: flex;
    justify-content: center;
    align-items: center;
    margin-right: 8px;

    
  }
  .silder_icon {
    font-size: 36px;
  }
  .menu_container {
    margin-top: 10px;
  }
  .menu_item_box {
    display: flex;
    height: 60px;
    align-items: center;
    text-decoration: none;
  }
  .menu_icon_box {
    width: 100%;
    height: 100%;
    display: flex;
    padding-left: 10px;
    align-items: center;
    border-left: 6px solid transparent;
  }
  .menu_icon {
    display: flex;
    align-items: center;
    width: 60px;
    height: 100%;
    font-size: 24px;
  }
  .menu_text_box {
    width: 100%;
    height: 100%;
    display: flex;
    align-items: center;
    padding-left: 10px;
    font-size: 20px;
    font-weight: 400;
  }
  .menu_item_box2 {
    display: flex;
    height: 60px;
    align-items: center;
    text-decoration: none;  
  }
  .menu_icon_box2 {
    margin-left: 26px;
    width: 24px;
    height: 24px;
  }
  .menu_icon2 {
    margin-top: 4px;
    font-size: 20px;
  }
  .menu_text_box2 {
    width: 140px;
    height: 30px;
    display: flex;
    padding-top: 2px;
    align-items: center;
    margin-left: 20px;
    font-size: 16px;
    font-weight: 400;
  }
  .menu_icon_box:hover {
    background-color: #efefef;
    border-left: 6px solid #929292;
  }
  .menu_item_box:hover .menu_icon {
    font-weight: 900;
    color: #191919;
  }
  .menu_item_box:hover .menu_text_box {
    color: #191919;
    font-weight: 900;
  }
  .option_menu {
    position: fixed;
    bottom: 0px;
    height: 180px;
  }
  .item_container {
    display: flex;
  }
  .logo_img_box {
    display: flex;
    width: 130px;
    height: 60px;
    justify-content: center;
    margin: 10px 20px;
  }
  .btn_clicked {
    border-left: 6px solid #929292;
    background-color: #efefef;
  }

  .btn_clicked2 {
    font-weight: 900;
    color: #191919;
  }

  .snackbar {
    font-size: 20px;
  }

  
</style>