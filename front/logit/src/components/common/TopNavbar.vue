<template>
  <div class="container b_transparentwhite">
    <div class="left_box">
      <div @click="openSidebar" class="slider_hover slider_box b_lightgray"><v-icon class="silder_icon f_white">mdi-chevron-double-right</v-icon></div>
    </div>
    <div class="right_box">
      <div class="search_box_container">
        <div class="search_box right_box_items hover_cursor"  id="searchId" @click="searchSlider">
          <div class="search_icon_box"><v-icon class="search_icon">mdi-magnify</v-icon></div>
          <div class="search_content_box">
            <input class="search_content" type="text" placeholder="검색">    
          </div>  
        </div>
      </div>
      <div v-if="!loginUser.id" class="login_btn_box right_box_items">
        <router-link :to="{name: 'UserLogin'}" class="login_btn b_main btn_hover">
          <div class="login_btn_text f_white">로그인</div>
        </router-link>
      </div>
      <div v-else class="profile_box right_box_items">
        <!-- <div @click="openProfile" class="profile_id hover_cursor ">{{loginUser.name}} 님</div> -->
        <div @click="openProfile" class="profile_img_box hover_cursor">
          <v-img v-if="loginUser.image.length < 5" class="logo_img"
            :src="require(`../../assets/profiles/scale (${loginUser.image}).png`)"
            height="110"
          />    
          <v-img v-else class="logo_img"
            :src="loginUser.image"
            height="110"
          />    
        </div>
        <div class="profile_slider_box nosee">
          <ul>
            <div class="login-user">{{loginUser.name}} 님</div>
            <li @click="goTimeline">타임라인</li>
            <li @click="goProfile">프로필</li>
            <li @click="logout">로그아웃</li>

          </ul>
        </div>
      </div>
    </div>
    
    <div v-if="modal" class="out_box_area" @click="minimize_search" :v-model="modal"></div>

  </div>
</template>

<script>

import { computed } from '@vue/reactivity'
import { useStore } from 'vuex'
import router from '@/router'

export default {
  name: 'TopNavbar',
  data() {
    return {
      modal : false
    }
  },

  methods: {
    logout() {
      this.$store.dispatch('logout')
    },
    searchSlider() {
      const target = document.querySelector('.search_box')
      const target2 = document.querySelector('.search_icon_box')
      const target3 = document.querySelector('.search_icon')
      const target4 = document.querySelector('.search_content_box')
      const target5 = document.querySelector('.search_content')
      const targetInput = document.querySelector('.search_content')


        
      if(event.target == target || event.target == target2 || event.target == target3 || event.target == target4 || event.target == target5)  {
        target.classList.add('max_animation')
        target.classList.add('search_back_on')
        targetInput.focus()
        this.modal = true
        
      }

    },
    minimize_search() {
      const target = document.querySelector('.search_box')
      target.classList.remove('max_animation')
      target.classList.remove('search_back_on')
      this.modal = false
    }
    

  },
  created(){
    const token = sessionStorage.getItem('token')
    if(token){
      this.$store.dispatch('getUserOnly')
    }
  },
  setup() {
    const state = {


    }
  
    const store = useStore()
    
    const loginUser = computed(()=>store.state.loginUser)

    const openSidebar = () => {
      const eventTarget1 = document.querySelector('.SideNavbar_box')
      const eventTarget2 = document.querySelector('.SideNavbar_space')

      eventTarget1.classList.toggle('nosee')
      eventTarget2.classList.toggle('nosee')


      store.dispatch(`openSidebar`)

    }
    const openProfile = () => {
      const target = document.querySelector('.profile_slider_box')

      target.classList.toggle('nosee')
    }

    const goProfile = () => {
      router.push({name: 'ProfilePage'})
    }
    
    const goTimeline = () => {
      store.dispatch('timeline/timelineSetting')
    }

    return {
      state,
      loginUser,
      openSidebar,
      openProfile,
      goProfile,
      goTimeline,
    }
    
  },
}

</script>
<style scoped>
  .container {
    position: fixed;
    z-index: 100;
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 0 10px;
    border-bottom: 0.5px solid rgba(216, 216, 216, 0.6);
    backdrop-filter: blur(5px);
  }

  .slider_box {
    width: 40px;
    height: 40px;
    border-radius: 8px;
    display: flex;
    justify-content: flex-end;
    align-items: center;
    
  }
    
  .silder_icon {
    font-size: 34px;
  }
  .left_box {
    width: 100px;
    height: 50px;
    display: flex;
    align-items: center;
  }
  .right_box {
    width: 620px;
    height: 50px;
    display: flex;
    align-items: center;
  }
  .right_box_items {
    height: 100%;
  }
  .search_box {
    z-index: 500;
    width: 100px;
    height: 40px;
    display: flex;
    align-items: center;
    justify-content: start;
    border-radius: 4px;
    margin-right: 10px;
    /* background-color: aqua; */
  }
  .search_box:hover {
    background-color: #efefef;
  }
  .login_btn_box {
    width: 220px;
    height: 36px;
    display: flex;
    align-items: flex-start;
    justify-content: end;
    border-left: 1.5px solid lightgray;
    
  }
  .profile_box {
    width: 220px;
    display: flex;
    align-items: center;
    justify-content: end;

  }
  .login_btn {
    width: 100px;
    height: 36px;
    border-radius: 6px;
    margin-right: 10px;
    display: flex;
    justify-content: center;
    align-items: center;
    

  }
  .login_btn_text {
    margin-top: 2px;
    font-size: 16px;
  }
  .search_icon {
    font-size: 26px;
    margin-left:10px;

  }
  .search_content {
    height: 26px;
    width: 100%;
    margin-left: 10px;
    display: flex;
    justify-content: start;
    align-items: center;
    font-size: 20px;

  }

  .search_content_box {
    width: 100%;
    height: 36px;  
    display: flex;
    align-items: center;
    justify-content: center;
  } 
  .profile_img_box {
    width: 30px;
    height: 30px;
    border-radius: 50px;
    background-color: gray;
    margin-right: 10px;
  }
  .profile_id {
    margin-right: 10px;
    font-size: 15px;
  }
  .profile_slider_box {
    margin: 0 auto;
    width: 140px;
    height: 160px;
    position: absolute;
    border-radius: 6px;
    top: 60px;
    background-color: #ffffff;
    backdrop-filter: blur(20px);

    box-shadow: 4px 4px 20px 4px rgba(59, 59, 59, 0.1);
    

  }

  .login-user{
    display: flex;
    justify-content: end;
    align-items: center;
    height: 40px;
    width:140px;
    list-style-type: none;
    border-radius: 4px;
    padding: 0 20px;
    background: rgba(218, 218, 218, 0.419);
    font-family: appleM;
  }


  li {
    display: flex;
    justify-content: left;
    align-items: center;
    height: 40px;
    width:140px;
    list-style-type: none;
    border-radius: 4px;
    padding-left: 20px;

    
  }
  li:hover {
    background-color: #efefef;
    color:#191919;
    font-weight: 900;
    cursor: pointer;
  }

  .search_box_container{
    width: 400px;
    height: 100%;
    display: flex;
    justify-content: end;
    align-items: center;
  }

  .max_animation {
  animation-duration: 0.4s;
  animation-name: max_animation;
  animation-fill-mode:forwards;
  animation-direction: alternate;
  background-color: #efefef;
}


@keyframes maxAnimation {
  from {
    width: 100px;
  }

  to {
    width: 370px
  }
}

input:focus {outline: none;} 

.search_input_text {
  height: 100%;
  background-color: none;
  margin-bottom: 14px;
}

input:focus {
  outline: none;
  width: 100%;
}

.out_box_area {
  position: absolute;
  z-index: 400;
  top: 0px;
  left: 0px;
  width: 100%;
  height: 1080px;
  background-color: transparent;
}
.search_back_on {
  background-color: #efefef;
}
</style>
