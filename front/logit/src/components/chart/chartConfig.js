export const data = {
    labels: ['네트워크', '운영체제', '프로젝트', '데이터베이스'],
    datasets: [
        {
            backgroundColor: [ 'rgb(255, 99, 132)', 'rgb(54, 162, 235)','rgb(255, 205, 86)', '#DD1B16'],
            // backgroundColor: ['#41B883', '#E46651', '#00D8FF', '#DD1B16'],
            data: [40, 20, 80, 10]
        }
    ]
};

export const options = {
    responsive: true,
    maintainAspectRatio: false,
}
