import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import SearchView from '../views/SearchView.vue'
import EventView from '../views/EventView.vue'
import JobView from '../views/JobView.vue'
import ProfileView from '../views/ProfileView.vue'
import UserView from '../views/UserView.vue'
import EtcView from '../views/EtcView.vue'
import MainPage from '../components/home/MainPage.vue'
import FirstTimeline from '../components/home/FirstTimeline.vue'
import TimeLine from '../components/home/TimeLine.vue'
import SearchResult from '../components/search/SearchResult.vue'
import EventList from '../components/event/EventList.vue'
import EventCreate from '../components/event/EventCreate.vue'
import EventProgress from '../components/event/EventProgress.vue'
import ProgressCreate from '../components/event/ProgressCreate.vue'
import ProgressDetail from '../components/event/ProgressDetail.vue'
import JobCreate from '../components/job/JobCreate.vue'
import JobList from '../components/job/JobList.vue'
import JobProgress from '../components/job/JobProgress.vue'
import ProfilePage from '../components/profile/ProfilePage.vue'
import UpdateProfile from '../components/profile/UpdateProfile.vue'
import CheckPassword from '../components/profile/CheckPassword.vue'
import UpdatePassword from '../components/profile/UpdatePassword.vue'
import UserLogin from '../components/user/UserLogin.vue'
import UserEmail from '../components/user/UserEmail.vue'
import UserPassword from '../components/user/UserPassword.vue'
import UserPhoto from '../components/user/UserPhoto.vue'
import UserProfile from '../components/user/UserProfile.vue'
import UserFindPassword from '../components/user/UserFindPassword.vue'
import PageSetting from '../components/etc/PageSetting.vue'
import DeveloperList from '../components/etc/DeveloperList.vue'


const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
      children: [
        {
          path: '',
          name: 'MainPage',
          component: MainPage
        },
        {
          path: 'start',
          name: 'FirstTimeline',
          component: FirstTimeline
        },
        {
          path: 'timeline',
          name: 'TimeLine',
          component: TimeLine
        }
      ]
    },
    {
      path: '/search',
      name: 'SearchView',
      component: SearchView,
      children: [
        {
          path: '',
          name: 'SearchResult',
          component: SearchResult
        }
      ]
    },
    {
      path: '/event',
      name: 'EventView',
      component: EventView,
      children: [
        {
          path: '',
          name: 'EventList',
          component: EventList
        },
        {
          path: 'create',
          name: 'EventCreate',
          component: EventCreate,
          props: true,
        },
        {
          path: ':eventId',
          name: 'EventProgress',
          component: EventProgress
        },
        {
          path: ':eventId/create',
          name: 'ProgressCreate',
          component: ProgressCreate
        },
        {
          path: ':eventId/:progressId',
          name: 'ProgressDetail',
          component: ProgressDetail
        }
      ]
    },
    {
      path: '/job',
      name: 'JobView',
      component: JobView,
      children: [
        {
          path: 'create',
          name: 'JobCreate',
          component: JobCreate
        },
        {
          path: '',
          name: 'JobList',
          component: JobList
        },
        {
          path: ':jobId',
          name: 'JobProgress',
          component: JobProgress
        }
      ]
    },
    {
      path: '/profile',
      name: 'ProfileView',
      component: ProfileView,
      children: [
        {
          path: '',
          name: 'ProfilePage',
          component: ProfilePage
        },
        {
          path: 'update',
          name: 'UpdateProfile',
          component: UpdateProfile
        },
        {
          path: 'checkpw',
          name: 'CheckPassword',
          component: CheckPassword
        },
        {
          path: 'password',
          name: 'UpdatePassword',
          component: UpdatePassword
        }
      ]
    },
    {
      path: '/user',
      name: 'UserView',
      component: UserView,
      children: [
        {
          path: 'login',
          name: 'UserLogin',
          component: UserLogin
        },
        {
          path: 'email',
          name: 'UserEmail',
          component: UserEmail
        },
        {
          path: 'password',
          name: 'UserPassword',
          component: UserPassword
        },
        {
          path: 'photo',
          name: 'UserPhoto',
          component: UserPhoto
        },
        {
          path: 'profile',
          name: 'UserProfile',
          component: UserProfile
        },
        {
          path: 'findpw',
          name: 'UserFindPassword',
          component: UserFindPassword
        }
      ]
    },
    {
      path: '/etc',
      name: 'EtcView',
      component: EtcView,
      children: [
        {
          path: '/setting',
          name: 'PageSetting',
          component: PageSetting
        },
        {
          path: '/developers',
          name: 'DeveloperList',
          component: DeveloperList
        }
      ]
    }
  ]
})


export default router
