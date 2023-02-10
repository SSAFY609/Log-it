import router from "@/router";
import axiosConnector from "@/utils/axios-connector"

const search = {
    namespaced: true,
    state:{
        serachResult: [],
        growths: [],
        jobs: [],
    },
    getters: {

    },
    mutations: {
        GET_GROWTHS(state, payload){
            state.growths = payload
        },
        GET_JOBS(state, payload){
            state.jobs = payload
            router.push({name: 'TimeLine'})
        },
    },
    actions: {
        getGrowths({commit, dispatch}) {
            axiosConnector.get(`growth/get_mine`
            ).then((res)=> {
                commit('GET_GROWTHS', res.data)
                dispatch('getJobs')
            }).catch((err)=>{
                console.log(err)
            })
        },
        getJobs({commit}) {
            axiosConnector.get(`job/sort`
            ).then((res)=>{
                commit('GET_JOBS', res.data)
            }).catch((err)=>{
                console.log(err)
            })
        },
        timelineSetting({dispatch}){
            dispatch('getGrowths')
        }
    }

}

export default search;