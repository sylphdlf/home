<template>
    <div class="table">
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-tickets"></i> 物流管理</el-breadcrumb-item>
                <el-breadcrumb-item>外发管理</el-breadcrumb-item>
                <el-breadcrumb-item>本地外发</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-input v-model="select_word" placeholder="姓名" class="handle-input mr10" @keyup.enter.native="search"></el-input>
                <el-button type="primary" icon="search" @click="">搜索</el-button>
            </div>
            <el-table :data="tableData" border style="width: 100%" @selection-change="handleSelectionChange">
                <el-table-column type="selection" width="35" fixed></el-table-column>
                <el-table-column prop="departure" label="始发地" width="120"></el-table-column>
                <el-table-column prop="destination" label="目的地" width="120"></el-table-column>
                <el-table-column prop="productName" label="品名" width="120"></el-table-column>
                <el-table-column prop="stockCount" label="件数" width="80"></el-table-column>
                <el-table-column prop="productWeight" label="重量(kg)" width="80"></el-table-column>
                <el-table-column prop="productVolume" label="体积(cm³)" width="80"></el-table-column>
                <el-table-column prop="consignorName" label="发货方" width="120"></el-table-column>
                <el-table-column prop="consignorAddr" label="发货方地址" width="120"></el-table-column>
                <el-table-column prop="consignorTel" label="发货方电话" width="100"></el-table-column>
                <el-table-column prop="consignorMobile" label="发货方手机" width="100"></el-table-column>
                <el-table-column prop="consigneeName" label="收货方" width="120"></el-table-column>
                <el-table-column prop="consigneeAddr" label="收货方地址" width="120"></el-table-column>
                <el-table-column prop="consigneeTel" label="收货方电话" width="120"></el-table-column>
                <el-table-column prop="consigneeMobile" label="收货方手机" width="100"></el-table-column>
                <el-table-column label="创建时间" width="135">
                    <template slot-scope="scope">
                        {{scope.row.createTime | dateTimeFilter}}
                    </template>
                </el-table-column>
            </el-table>
            <div class="pagination">
                <el-pagination @current-change ="handleCurrentChange" layout="prev, pager, next, total" :total="dataTotal"></el-pagination>
            </div>
            <div align="center"><el-button type="primary" icon="el-icon-sort" circle @click="chooseData"></el-button></div><br><br>
            <el-table :data="tableData2" border style="width: 100%">
                <!--<el-table-column prop="departure" label="始发地" width="120"></el-table-column>-->
                <!--<el-table-column prop="destination" label="目的地" width="120"></el-table-column>-->
                <el-table-column label="派车成本(分摊)" width="120">
                    <template slot-scope="scope">
                        <el-input-number size="small" controls-position="right" :precision="2" :step="0.1" style="width: 100px"></el-input-number>
                    </template>
                </el-table-column>
                <el-table-column prop="productName" label="品名" width=""></el-table-column>
                <el-table-column prop="stockCount" label="件数" width=""></el-table-column>
                <el-table-column prop="productWeight" label="重量(kg)" width=""></el-table-column>
                <el-table-column prop="productVolume" label="体积(cm³)" width=""></el-table-column>
                <el-table-column prop="consignorName" label="发货方" width=""></el-table-column>
                <!--<el-table-column prop="consignorAddr" label="发货方地址" width="120"></el-table-column>-->
                <el-table-column prop="consignorTel" label="发货方电话" width=""></el-table-column>
                <el-table-column prop="consignorMobile" label="发货方手机" width=""></el-table-column>
                <el-table-column prop="consigneeName" label="收货方" width=""></el-table-column>
                <!--<el-table-column prop="consigneeAddr" label="收货方地址" width="120"></el-table-column>-->
                <el-table-column prop="consigneeTel" label="收货方电话" width=""></el-table-column>
                <el-table-column prop="consigneeMobile" label="收货方手机"></el-table-column>
            </el-table><br>
            <p>车辆信息</p>
            <hr style="margin-top:2px; color: #bbbbbb;"><br>
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" class="demo-ruleForm" label-width="100px" :inline="true">
                <div class="handle-box" align="center">
                    <el-form-item prop="driverName">
                        <el-input placeholder="接货司机" v-model="ruleForm.driverName">
                            <template slot="prepend"><el-button icon="el-icon-search" @click="openDriverWin"></el-button></template>
                        </el-input>
                    </el-form-item>
                    <el-form-item prop="consignorTel">
                        <el-input placeholder="接货车牌号" class="handle-input" v-model="ruleForm.vehicleNumber"></el-input>
                    </el-form-item>
                    <el-form-item prop="consignorTel">
                        <el-input placeholder="司机手机" class="handle-input" v-model="ruleForm.driverMobile"></el-input>
                    </el-form-item>
                </div>
                <div class="handle-box" align="center">
                    <el-form-item prop="consignorTel">
                        <el-date-picker v-model="ruleForm.pickupTime" type="datetime" placeholder="接货时间" class="handle-input"></el-date-picker>
                    </el-form-item>
                    <el-form-item prop="consignorTel">
                        <el-input placeholder="派车成本" class="handle-input" v-model="ruleForm.price"></el-input>
                    </el-form-item>
                    <el-form-item prop="consignorTel">
                        <el-input placeholder="接货备注" class="handle-input" v-model="ruleForm.remarks"></el-input>
                    </el-form-item>
                </div>
            </el-form>
            <div class="handle-box" align="center">
                <el-button type="primary" @click="submit">提交</el-button>
            </div>
        </div>
        <el-dialog title="车辆查询" :visible.sync="driverShow">
            <div class="handle-box">
                <el-input v-model="searchFormDriver.search" placeholder="姓名/电话/手机" class="handle-input mr10" @keyup.enter.native="driverSearch"></el-input>
                <el-button type="primary" icon="el-icon-search" @click="driverSearch"></el-button>
            </div>
            <el-table :data="driverData" highlight-current-row @current-change="selectDriver">
                <el-table-column property="vehicleNumber" label="车牌号"></el-table-column>
                <el-table-column property="vehicleType" label="车型"></el-table-column>
                <el-table-column label="长宽高">
                    <template slot-scope="scope">
                        {{scope.row.vehicleLength + '*' + scope.row.vehicleWidth + '*' + scope.row.vehicleHeight}}
                    </template>
                </el-table-column>
                <el-table-column property="vehicleVin" label="VIN码"></el-table-column>
                <el-table-column property="driverName" label="司机姓名"></el-table-column>
                <el-table-column property="driverLicence" label="驾照"></el-table-column>
            </el-table>
            <div class="pagination">
                <el-pagination
                    @current-change ="driverPageChange"
                    layout="prev, pager, next, total"
                    :total="driverTotal">
                </el-pagination>
            </div>
            <div slot="footer" class="dialog-footer">
                <el-button @click="driverShow = false">取 消</el-button>
                <el-button type="primary" @click="selectDriverConfirm">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                url: this.$projectUrl + '/consign/queryPageByParams',
                urlAdd: this.$projectUrl + '/pickup/add',
                urlVehicle: this.$projectUrl + '/vehicle/queryPageByParams',
                tableData: [],
                tableData2: [],
                tableDateSelect: [],
                select_word: '',
                dataTotal:1,
                dialogFormVisible: false,
                driverShow: false,
                driverTotal:1,
                searchForm: {
                    status: '',
                },
                searchFormDriver:{
                    pageNum: 1,
                },
                userData: {
                    id: '',
                    username: '',
                    realName: '',
                    mobile: '',
                    telephone: ''
                },
                driverData: [],
                ruleForm:{
                    pickupTime: '',
                    price:0,
                },
                rules:{

                }
            }
        },
        created(){
            this.searchForm.pageNum = 1;
            this.searchForm.pageSize = 5;
            this.getData();
        },
        methods: {
            // 分页导航
            handleCurrentChange(val){
                this.searchForm.pageNum = val;
                this.getData();
            },
            getData(){
                this.$axios.post(this.url, this.searchForm).then((res) => {
                    if(res.data.code === "0" && res.data.total !== 0){
                        this.tableData = res.data.data.list;
                        this.dataTotal = res.data.data.total;
                    }
                })
            },
            handleSelectionChange(val) {
                this.tableDateSelect = val;
                console.info(this.tableDateSelect);
            },
            chooseData: function(){
                this.tableData2 = this.tableData2.concat(this.tableDateSelect);
            },
            nextPage:function () {
                this.$router.push({path:'/pickUpMgr2'});
            },
            //司机信息
            openDriverWin: function(){
                this.driverShow = true;
                this.getDriverData();
            },
            driverPageChange(val){
                this.consignorSearch.pageNum = val;
                this.getDriverData();
            },
            driverSearch: function(){

            },
            getDriverData(){
                this.$axios.post(this.urlVehicle, this.searchFormDriver).then((res) => {
                    if(res.data.code === '0' && res.data.total !== 0){
                        this.driverData = res.data.data.list;
                        this.driverTotal = res.data.data.total;
                    }
                });
            },
            selectDriver(val){
                this.selectedDriver = val;
            },
            selectDriverConfirm: function(){
                if(this.selectedDriver.id===undefined){
                    this.msgWarn("请点击一条数据");
                    return false;
                }
                this.ruleForm.vehicleId = this.selectedDriver.id;
                this.ruleForm.driverName = this.selectedDriver.driverName;
                this.ruleForm.vehicleNumber = this.selectedDriver.vehicleNumber;
                this.ruleForm.driverMobile = this.selectedDriver.driverMobile;
                this.driverShow = false;
            },
            submit: function () {
                this.ruleForm.list = this.tableData2;
                this.$refs['ruleForm'].validate((valid) => {
                    if (valid) {
                        this.$axios.post(this.urlAdd, this.ruleForm).then((res) => {
                            if(res.data.code === '0'){
                                this.msgSuccess();
                                this.$router.push({path:'/pickupMgr'});
                            }
                        });
                    } else {
                        this.msgFail("请补全表单信息");
                        return false;
                    }
                });
            }
        }
    }
</script>

<style scoped>
.handle-box{
    margin-bottom: 20px;
}
.handle-select{
    width: 120px;
}
.handle-input{
    width: 238px;
    display: inline-block;
}
</style>
