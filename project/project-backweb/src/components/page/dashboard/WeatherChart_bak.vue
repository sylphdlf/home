<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-date"></i> 图表</el-breadcrumb-item>
                <el-breadcrumb-item>基础图表</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <!--<div class="plugins-tips">-->
                <!--vue-schart：vue.js封装sChart.js的图表组件。-->
                <!--访问地址：<a href="https://github.com/lin-xin/vue-schart" target="_blank">vue-schart</a>-->
            <!--</div>-->
            <div class="schart">
                <schart canvasId="temp_shanghai" width="1250" height="300" :data="dataTemp1" type="line" :options="optionsTemp1"></schart>
            </div>
            <div class="schart">
                <schart canvasId="temp_jiujiang" width="1250" height="300" :data="dataTemp2" type="line" :options="optionsTemp2"></schart>
            </div>
            <div class="schart">
                <schart canvasId="pm25_shanghai" width="1250" height="300" :data="dataPm251" type="line" :options="optionsPm251"></schart>
            </div>
            <div class="schart">
                <schart canvasId="pm25_jiujiang" width="1250" height="300" :data="dataPm252" type="line" :options="optionsPm252"></schart>
            </div>

            <!--<div class="schart">-->
            <!--<div class="content-title">饼状图</div>-->
            <!--<schart canvasId="pie" width="500" height="400" :data="data2" type="pie" :options="options2"></schart>-->
            <!--</div>-->
            <!--<div class="schart">-->
            <!--<div class="content-title">环形图</div>-->
            <!--<schart canvasId="ring" width="500" height="400" :data="data2" type="ring" :options="options2"></schart>-->
            <!--</div>-->
        </div>
    </div>
</template>

<script>
    import Schart from 'vue-schart';
    export default {
        components: {
            Schart
        },
        data: function (){
            return{
                url24hTemp: this.$projectUrl + '/dashboard/get24hTemp',
                url24hPm25: this.$projectUrl + '/dashboard/get24hPm25',
                dataTemp1:[],
                dataTemp2:[],
                dataPm251:[],
                dataPm252:[],
                optionsTemp1: {
                    title: '浦东 最近24小时气温走势',
                    bgColor: '#009688',
                    titleColor: '#ffffff',
                    fillColor: '#e0f2f1',
                    axisColor: '#ffffff',
                    contentColor: '#999',
                },
                optionsTemp2: {
                    title: '九江 最近24小时气温走势',
                    bgColor: '#009688',
                    titleColor: '#ffffff',
                    fillColor: '#e0f2f1',
                    axisColor: '#ffffff',
                    contentColor: '#999',
                },
                optionsPm251: {
                    title: '浦东 最近24小时pm25走势',
                    bgColor: '#009688',
                    titleColor: '#ffffff',
                    fillColor: '#e0f2f1',
                    axisColor: '#ffffff',
                    contentColor: '#999',
                },
                optionsPm252: {
                    title: '九江 最近24小时pm25走势',
                    bgColor: '#009688',
                    titleColor: '#ffffff',
                    fillColor: '#e0f2f1',
                    axisColor: '#ffffff',
                    contentColor: '#999',
                },
            }
        },
        created(){
            this.get24hTemp();
            this.get24hPm25();
        },
        methods: {
            get24hTemp() {
                this.$axios.post(this.url24hTemp, {city: "浦东新区"}).then(result =>{
                    if(result.data.code === "0"){
                        this.dataTemp1 = result.data.data;
                    }else{
                        this.messageShow.error = result.data.msg;
                        return false;
                    }
                });
                this.$axios.post(this.url24hTemp, {city: "九江市"}).then(result =>{
                    if(result.data.code === "0"){
                        this.dataTemp2 = result.data.data;
                    }else{
                        this.messageShow.error = result.data.msg;
                        return false;
                    }
                });
            },
            get24hPm25() {
                this.$axios.post(this.url24hPm25, {city: "浦东新区"}).then(result =>{
                    if(result.data.code === "0"){
                        this.dataPm251 = result.data.data;
                    }else{
                        this.messageShow.error = result.data.msg;
                        return false;
                    }
                });
                this.$axios.post(this.url24hPm25, {city: "九江市"}).then(result =>{
                    if(result.data.code === "0"){
                        this.dataPm252 = result.data.data;
                    }else{
                        this.messageShow.error = result.data.msg;
                        return false;
                    }
                });
            }
        }
    }
</script>

<style scoped>
    .schart{
        width: 600px;
        /*display: inline-block;*/
    }
    .content-title{
        clear: both;
        font-weight: 400;
        line-height: 50px;
        margin: 10px 0;
        font-size: 22px;
        color: #1f2f3d;
    }

</style>
