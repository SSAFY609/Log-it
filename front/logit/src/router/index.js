import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import SearchView from '../views/SearchView.vue'
import GrowthView from '../views/GrowthView.vue'
import JobView from '../views/JobView.vue'
import ProfileView from '../views/ProfileView.vue'
import UserView from '../views/UserView.vue'
import EtcView from '../views/EtcView.vue'
import MainPage from '../components/home/MainPage.vue'
import FirstTimeline from '../components/home/FirstTimeline.vue'
import TimeLine from '../components/home/TimeLine.vue'
import SearchResult from '../components/search/SearchResult.vue'
import GrowthList from '../components/growth/GrowthList.vue'
import GrowthCreate from '../components/growth/GrowthCreate.vue'
import GrowthProgress from '../components/growth/GrowthProgress.vue'
import ProgressCreate from '../components/growth/ProgressCreate.vue'
import ProgressDetail from '../components/growth/ProgressDetail.vue'
import ProgressUpdate from '../components/growth/ProgressUpdate.vue'
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
import UserData from '../components/user/UserData.vue'
import UserPasswordReset from '../components/user/UserPasswordReset.vue'
import UserSignupComplete from '../components/user/UserSignupComplete.vue'
import PageSetting from '../components/etc/PageSetting.vue'
import DeveloperList from '../components/etc/DeveloperList.vue'
import ChartView from '../components/chart/ChartView.vue'
import StatisticsView from "@/views/StatisticsView.vue";
import OpenSource from '../components/etc/OpenSource.vue'


const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: "/",
      name: "home",
      component: HomeView,
      children: [
        {
          path: "",
          name: "MainPage",
          component: MainPage,
        },
        {
          path: "start",
          name: "FirstTimeline",
          component: FirstTimeline,
        },
        {
          path: "timeline",
          name: "TimeLine",
          component: TimeLine,
        },
      ],
    },
    {
      path: "/statistics",
      name: "statistics",
      component: StatisticsView,
      children: [
        {
          path: "",
          name: "ChartView",
          component: ChartView,
        },
      ],
    },
    {
      path: "/search",
      name: "SearchView",
      component: SearchView,
      children: [
        {
          path: "",
          name: "SearchResult",
          component: SearchResult,
        },
      ],
    },
    {
      path: "/growth",
      name: "GrowthView",
      component: GrowthView,
      children: [
        {
          path: "",
          name: "GrowthList",
          component: GrowthList,
        },
        {
          path: 'create',
          name: 'GrowthCreate',
          component: GrowthCreate,
          props: true,
        },
        {
          path: ':growthId',
          name: 'GrowthProgress',
          component: GrowthProgress,
          children: [
            {
              path: "create",
              name: "ProgressCreate",
              component: ProgressCreate,
            },
            {
              path: "detail",
              name: "ProgressDetail",
              component: ProgressDetail,
            },
            {
              path: "update",
              name: "ProgressUpdate",
              component: ProgressUpdate,
            }
          ]
        },
      ],
    },
    {
      path: "/job",
      name: "JobView",
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
          path: ":jobId",
          name: "JobProgress",
          component: JobProgress,
        },
      ],
    },
    {
      path: "/profile",
      name: "ProfileView",
      component: ProfileView,
      children: [
        {
          path: "",
          name: "ProfilePage",
          component: ProfilePage,
        },
        {
          path: "update",
          name: "UpdateProfile",
          component: UpdateProfile,
        },
        {
          path: "checkpw",
          name: "CheckPassword",
          component: CheckPassword,
        },
        {
          path: "password",
          name: "UpdatePassword",
          component: UpdatePassword,
        },
      ],
    },
    {
      path: "/user",
      name: "UserView",
      component: UserView,
      children: [
        {
          path: "login",
          name: "UserLogin",
          component: UserLogin,
          props: true,
        },
        {
          path: "email",
          name: "UserEmail",
          component: UserEmail,
          props: true,
        },
        {
          path: "password",
          name: "UserPassword",
          component: UserPassword,
          props: true,
        },
        {
          path: "photo",
          name: "UserPhoto",
          component: UserPhoto,
          props: true,
        },
        {
          path: "profile",
          name: "UserProfile",
          component: UserProfile,
          props: true,
        },
        {
          path: "findpw",
          name: "UserFindPassword",
          component: UserFindPassword,
        },
        {
          path: "data",
          name: "UserData",
          component: UserData,
          props: true,
        },
        {
          path: "reset",
          name: "UserPasswordReset",
          component: UserPasswordReset,
          props:true,
        },
        {
          path: "signup",
          name: "UserSignupComplete",
          component: UserSignupComplete,
          props:true,
        }
      ],
    },
    {
      path: "/etc",
      name: "EtcView",
      component: EtcView,
      children: [
        {
          path: "/setting",
          name: "PageSetting",
          component: PageSetting,
        },
        {
          path: "/developers",
          name: "DeveloperList",
          component: DeveloperList,
        },
        {
          path: "/demo",
          name: "OpenSource",
          component: OpenSource,
        },
      ],
    },
  ],
});

export default router;
