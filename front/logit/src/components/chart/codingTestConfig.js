export const data = {
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
            data: [40, 20, 80, 10,30]
        }
    ]
};

export const options = {
    plugins: {
        legend: {
            display: true,
            position: "left",
            labels: {
                boxWidth: 8,
                padding: 10,
                usePointStyle: true,
                pointStyle: "circle",
                font: {
                    size: 14
                }
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
            borderWidth: 2
        }
    },
    animation: {
        duration: 5000
    }
};
