<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-date"></i> 图表</el-breadcrumb-item>
                <el-breadcrumb-item>基础图表</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <template>
                <div style="width: 1200px">
                    <canvas id="traffic_time"></canvas>
                </div>
            </template>
        </div>
    </div>
</template>
<script>
    import Chart from 'chart.js';
    export default {
        data() {
            return {
                urlMorningTraffic: this.$projectUrl + "/dashboard/getMorningTraffic",
                label:[],
                data: [],
                data2: [],
                data3: [],
            }
        },
        created() {
            this.get24hTemp();
        },
        methods: {
            get24hTemp(){
                this.$axios.post(this.urlMorningTraffic, {}).then(result =>{
                    if(result.data.code === "0"){
                        this.label = result.data.data.label;
                        this.data = result.data.data.data;
                        this.data2 = result.data.data.data2;
                        this.data3 = result.data.data.data3;
                        //初始化图表
                        this.initCharts();
                    }else{
                        this.messageShow.error = result.data.msg;
                        return false;
                    }
                });
            },
            initCharts(){
                let ctx = document.getElementById("traffic_time");
                let myChart = new Chart(ctx, {
                    type: "bar",
                    data: {
                        labels: this.label,
                        datasets: [
                            {
                                label: "早高峰路况",
                                backgroundColor: "green",
                                borderColor: "green",
                                borderWidth: 1,
                                pointStrokeColor: "#fff",
                                pointStyle: "circle",
                                data: this.data,
                                cubicInterpolationMode: "monotone",
                                spanGaps: "false",
                                fill: "false"
                            },
                            // {
                            //     label: "唐陆路路况",
                            //     backgroundColor: "blue",
                            //     borderColor: "blue",
                            //     borderWidth: 1,
                            //     pointStrokeColor: "#fff",
                            //     pointStyle: "circle",
                            //     data: this.data2,
                            //     cubicInterpolationMode: "monotone",
                            //     spanGaps: "false",
                            //     fill: "false"
                            // },
                            // {
                            //     label: "华夏中路张江路路况",
                            //     backgroundColor: "green",
                            //     borderColor: "green",
                            //     borderWidth: 1,
                            //     pointStrokeColor: "#fff",
                            //     pointStyle: "circle",
                            //     data: this.data3,
                            //     cubicInterpolationMode: "monotone",
                            //     spanGaps: "false",
                            //     fill: "false"
                            // }
                        ],
                    },
                    options: {
                        // "animation": {
                        //     "duration": 1,
                        //     "onComplete": function() {
                        //         let chartInstance = this.chart,
                        //             ctx = chartInstance.ctx;
                        //
                        //         ctx.font = Chart.helpers.fontString(Chart.defaults.global.defaultFontSize, Chart.defaults.global.defaultFontStyle, Chart.defaults.global.defaultFontFamily);
                        //         ctx.textAlign = 'center';
                        //         ctx.textBaseline = 'bottom';
                        //
                        //         this.data.datasets.forEach(function(dataset, i) {
                        //             let meta = chartInstance.controller.getDatasetMeta(i);
                        //             meta.data.forEach(function(bar, index) {
                        //                 let data = dataset.data[index];
                        //                 ctx.fillText(data, bar._model.x, bar._model.y - 5);
                        //             });
                        //         });
                        //     }
                        // },
                        animation: {// 这部分是数值显示的功能实现
                            onComplete: function () {
                                var chartInstance = this.chart,
                                    ctx = chartInstance.ctx;
                                // 以下属于canvas的属性（font、fillStyle、textAlign...）
                                ctx.font = Chart.helpers.fontString(Chart.defaults.global.defaultFontSize, Chart.defaults.global.defaultFontStyle, Chart.defaults.global.defaultFontFamily);
                                ctx.fillStyle = "black";
                                ctx.textAlign = 'center';
                                ctx.textBaseline = 'bottom';
                                this.data.datasets.forEach(function (dataset, i) {
                                    var meta = chartInstance.controller.getDatasetMeta(i);
                                    meta.data.forEach(function (bar, index) {
                                        var data = dataset.data[index];
                                        ctx.fillText(data, bar._model.x, bar._model.y - 5);
                                    });
                                });
                            }
                        }
                    }
                });
            }
        }
    }
</script>

<style scoped>
    .small {
        width: 400px;
        height: 400px;
    }
</style>
