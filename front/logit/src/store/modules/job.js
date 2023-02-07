import axiosConnector from "@/utils/axios-connector";



const job = {
    namespaced: true,
    state:{
        jobs: [
            {
            jobId: '1',
            category: '서류',
            contents: '안녕하세요. 질문입니다'
            },
            {
            jobId: '2',
            category: '면접',
            contents: '안녕하세요. 질문입니다'
            },
            

        ],
        userId: '1212',
        category: [11, 22, 33],
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
            

        }

        
    },

}

export default job;