import axiosConnector from "@/utils/axios-connector";



const job = {
    namespaced: true,
    state:{
        categoryList: [ "서류", "코테", "면접", "기타" ],
        testList: [ "BFS", "DFS", "그래프", "구현", "문자열", "정렬", "이분탐색", "자료구조", "완전탐색", "힙", "트리", "DP", "그리디", "백트랙킹"
        ],

    
    },
    getters: {
        getCategoryCnt(state) {
            return state.category.length;
        }
    },
    mutations: {
        GET_DATAS(state, payload) {
            state.jobs = payload
        }

    },
    actions: {
        getJobs({commit}, jobs) {
            axiosConnector.get('job', jobs
            ).then((res)=> {
                commit('GET_DATAS', res.jobs)
            }).catch((err)=> {
                console.log(err)
            })
            

        },
        // 취업여정이벤트 첫 세팅
        jobSetting({dispatch}, jobId){
            dispatch('getGrowthUsers', jobId)
            dispatch('getFirstProgress', jobId)
            dispatch('getLikeProgress', jobId)
            dispatch('getLog', jobId)
            dispatch('getGrowth', jobId)
        },

        
    },

}

export default job;