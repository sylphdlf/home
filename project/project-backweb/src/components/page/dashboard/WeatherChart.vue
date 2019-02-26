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
                    <canvas id="weather_city"></canvas>
                </div>
                <div style="width: 1200px">
                    <canvas id="weather_city2"></canvas>
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
                url24hTemp: this.$projectUrl + "/dashboard/get24hWeather",
                ctx:{},
                label:[],
                data: [],
                data2: [],
                data3: [],
                data4: [],
            }
        },
        mounted(){
            this.ctx = document.getElementById("weather_city");
        },
        created() {
            this.get24hTemp();
        },
        methods: {
            get24hTemp(){
                this.$axios.get(this.url24hTemp).then(result =>{
                    if(result.data.code === "0"){
                        this.label = result.data.data.label;
                        this.data = result.data.data.data;
                        this.data2 = result.data.data.data2;
                        this.data3 = result.data.data.data3;
                        this.data4 = result.data.data.data4;
                        //初始化图表
                        this.initCharts();
                    }else{
                        this.messageShow.error = result.data.msg;
                        return false;
                    }
                });
            },
            initCharts(){
                let myChart = new Chart(this.ctx, {
                    type: "line",
                    data: {
                        labels: this.label,
                        datasets: [
                            {
                                label: "浦东新区气温",
                                backgroundColor: "cyan",
                                borderColor: "cyan",
                                borderWidth: 1,
                                // pointStrokeColor: "green",
                                pointStyle: "circle",
                                data: this.data,
                                cubicInterpolationMode: "monotone",
                                spanGaps: "false",
                                fill: "false"
                            },
                            {
                                label: "浦东新区pm25",
                                backgroundColor: "orange",
                                borderColor: "orange",
                                borderWidth: 1,
                                // pointStrokeColor: "#fff",
                                pointStyle: "circle",
                                data: this.data2,
                                cubicInterpolationMode: "monotone",
                                spanGaps: "false",
                                fill: "false"
                            },
                            {
                                label: "九江市气温",
                                backgroundColor: "#20B2AA",
                                borderColor: "#20B2AA",
                                borderWidth: 1,
                                // pointStrokeColor: "green",
                                pointStyle: "circle",
                                data: this.data3,
                                cubicInterpolationMode: "monotone",
                                spanGaps: "false",
                                fill: "false"
                            },
                            {
                                label: "九江市pm25",
                                backgroundColor: "khaki",
                                borderColor: "khaki",
                                borderWidth: 1,
                                // pointStrokeColor: "#fff",
                                pointStyle: "circle",
                                data: this.data4,
                                cubicInterpolationMode: "monotone",
                                spanGaps: "false",
                                fill: "false"
                            },
                        ]
                    },
                    options: {
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

</style>
