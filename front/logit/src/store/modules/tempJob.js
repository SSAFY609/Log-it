import axiosConnector from "@/utils/axios-connector";



const tempJob = {
    namespaced: true,
    state:{
        jobs: {

            start_date: new Date(2023,0,10), 
            end_date: new Date(2023,1,10),
            job: [
                {
                    jobId: '1',
                    jobName: '카카오',
                    category: [
                        {
                            name: '서류전형',
                            contents: {
                                question: '우리 회사에 왜 지원했나요?',
                                answer: '돈을 많이 준다는 이야기를 들었습니다.',
                            }
                        }
                    ]
                }
            ],
        
        
        },


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

export default tempJob;