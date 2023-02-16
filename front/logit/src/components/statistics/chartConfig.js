export const data = {
    labels: ['네트워크', '운영체제', '프로젝트', '데이터베이스'],
    datasets: [
        {
            backgroundColor: ["rgb(242,165,152)",
                "rgb(255,232,157)",
                "rgb(236,107,109)",
                "rgb(122,231,125)",
                "rgb(195,233,151)"],
            // backgroundColor: ['#41B883', '#E46651', '#00D8FF', '#DD1B16'],
            data: [40, 20, 80, 10]
        }
    ]
};

export const codingTestData = {
    labels: ['그래프 탐색', 'BFS', '동적 프로그래밍', '자료구조', '힙'],
    datasets: [
        {
            backgroundColor: ["#99CCFF",
                "#999933",
                "#666699",
                "#CC9933",
                "#006666",
                "#3399FF",
                "#993300",
                "#CCCC99",
                "#666666",],
            // backgroundColor: ['#41B883', '#E46651', '#00D8FF', '#DD1B16'],
            data: [40, 20, 80, 10, 30]
        }
    ]
}

export const myApplyData = {
    labels: ['합격', '불합격', '진행중'],
    datasets: [
        {
            backgroundColor: ["blue",
                "red",
                "#CCCCCC"],
            // backgroundColor: ['#41B883', '#E46651', '#00D8FF', '#DD1B16'],
            data: [40, 20, 80]
        }
    ]
}


export const options = {
    plugins: {
        datalabels: {
            color:"white",
            formatter: (value, ctx) => {
                let sum = 0;
                let dataArr = ctx.chart.data.datasets[0].data;
                dataArr.map(data => {
                    sum += data;
                });
                if((value*100 / sum)<10){
                    return '';
                }

                let percentage = (value*100 / sum).toFixed(1)+"%";
                return percentage;
            },
        },
        title: {
            display: true,
            text: '면접 질문 유형 통계',
            font:{
                size:20
            }
        },
        legend: {
            display: true,
            position: "right",
            labels: {
                boxWidth: 8,
                padding: 10,
                usePointStyle: true,
                pointStyle: "circle",
                font: {
                    size: 14,
                    family: 'AppleM'
                },
            },
            fullSize: true,
            align: "center"
        },
        tooltip: {
            boxWidth: 15,
            bodyFont: {
                size: 14
            }
        }
    },
    responsive: true,
    maintainAspectRatio: false,
    layout: {
        padding: {
            top: 50,
            bottom: 50
        }
    },
    elements: {
        arc: {
            // borderWidth: 2
        }
    },
    animation: {
        duration: 5000
    }
};

export const codingTestDataOptions = {
    plugins: {
        datalabels: {
            color:"white",
            formatter: (value, ctx) => {
                let sum = 0;
                let dataArr = ctx.chart.data.datasets[0].data;
                dataArr.map(data => {
                    sum += data;
                });
                if((value*100 / sum)<10){
                    return '';
                }

                let percentage = (value*100 / sum).toFixed(1)+"%";
                return percentage;
            },
        },
        title: {
            display: true,
            text: '코테 유형 통계',
            font:{
                size:20
            }
        },
        legend: {
            display: true,
            position: "right",
            labels: {
                boxWidth: 8,
                padding: 10,
                usePointStyle: true,
                pointStyle: "circle",
                font: {
                    size: 14,
                    family: 'AppleM'
                },
            },
            fullSize: true,
            align: "center"
        },
        tooltip: {
            boxWidth: 15,
            bodyFont: {
                size: 14
            }
        }
    },
    responsive: true,
    maintainAspectRatio: false,
    layout: {
        padding: {
            top: 50,
            bottom: 50
        }
    },
    elements: {
        arc: {
            // borderWidth: 2
        }
    },
    animation: {
        duration: 5000
    }
};

export const myApplyDataOptions = {
    plugins: {
        datalabels: {
            color:"white",
            formatter: (value, ctx) => {
                let sum = 0;
                let dataArr = ctx.chart.data.datasets[0].data;
                dataArr.map(data => {
                    sum += data;
                });
                if((value*100 / sum)<10){
                    return '';
                }

                let percentage = (value*100 / sum).toFixed(1)+"%";
                return percentage;
            },
        },
        title: {
            display: true,
            text: '내 지원 통계',
            font:{
                size:20
            }
        },
        legend: {
            display: true,
            position: "right",
            labels: {
                boxWidth: 8,
                padding: 10,
                usePointStyle: true,
                pointStyle: "circle",
                font: {
                    size: 14,
                    family: 'AppleM'
                },
            },
            fullSize: true,
            align: "center"
        },
        tooltip: {
            boxWidth: 15,
            bodyFont: {
                size: 14
            }
        }
    },
    responsive: true,
    maintainAspectRatio: false,
    layout: {
        padding: {
            top: 50,
            bottom: 50
        }
    },
    elements: {
        arc: {
            // borderWidth: 2
        }
    },
    animation: {
        duration: 5000
    }
};
