import axiosConnector from "@/utils/axios-connector";
import router from "@/router";
// import axios from "axios";


const tempJob = {
    namespaced: true,
    state:{
        allJob: [],
        jobs: [
     
        ]
    },
    getters: {
        categoryCnt(state) {
            return state.category.length;
        }
    },
    mutations: {
        GET_ALL_JOB(state, payload){
            state.allJob = payload
            // router.push({name: 'JobList'})
        },


        GET_JOB(state, payload) {
            state.jobs = payload.data
            router.push({name: 'JobProgress', params: {jobId: payload.jobId}})
        },

    },
    actions: {

        // 사용자가 가지고 있는 모든 취업여정 가져오기
        getAllJob({commit}) {
            axiosConnector.get(`job`
            ).then((res)=> {
                console.log('getAllJob 성공')
                commit('GET_ALL_JOB', res.data)
            }).catch((err)=>{
                console.log(err)
            })
        },
  

        getJob({commit}, jobId) {
            
            axiosConnector.get(`job/${jobId}`
            
            ).then((res)=> {
                const payload = {
                    jobId: jobId,
                    data: res.data
                }
                commit('GET_JOB', payload)
            }).catch((err)=> {
                console.log(err)
            })
            

        },
        sendJobs({dispatch}, jobs) {
            console.log('sendJobs 실행')
            // console.log(jobs)
            axiosConnector.post('job/post-all', jobs
            ).then(()=> {
                console.log('sendJobs 성공')
                dispatch('getJob', jobs.jobId)
            }).catch((err)=> {
                console.log('엑시오스 에러')
                console.log(err)
            })
            
        },
        createJob({dispatch}, datas) {
            console.log('createJob 실행')
            // console.log(datas)
            axiosConnector.post('job', datas
            ).then((res)=> {
                console.log('createJob성공 후 응답')
                dispatch('jobSetting',res.data.id)
            
            }).catch((err)=> {
                console.log('createJob 에러')
                console.log (err)
            })
        },
        // 취업여정이벤트 첫 세팅
        jobSetting({dispatch}, jobId){
            dispatch('getJob', jobId)
        },

        
    },

}

export default tempJob;