<template>
    <div class="table">
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-tickets"></i> stock</el-breadcrumb-item>
                <el-breadcrumb-item>list</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-input  placeholder="代码" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="search" @click="search">搜索</el-button>
                <el-button type="primary" icon="search" @click="add">新增</el-button>
            </div>
            <el-table :data="tableData" border style="width: 100%">
                <el-table-column type="selection" width="55"/>
                <el-table-column prop="name" label="名称"/>
                <el-table-column prop="code" label="代码"/>
                <el-table-column prop="market" label="市场"/>
                <el-table-column prop="price" label="当前价格"/>
                <el-table-column prop="buyingPrice" label="买入价"/>
                <el-table-column prop="buyingCount" label="数量（手）"/>
                <el-table-column prop="buyingTotal" label="总价"/>
                <el-table-column label="操作" width="220">
                    <template slot-scope="scope">
                        <el-button type="primary" size="small" @click="handleEdit(scope.$index, scope.row)">买入</el-button>
                        <el-button type="info" size="small" @click="openInfo(scope.row)">详细</el-button>
                        <el-button v-if="scope.row.watchingStatus === 0" type="warning" size="small" @click="startWatching(scope.row)">监控</el-button>
                        <el-button v-if="scope.row.watchingStatus === 1" type="danger" size="small" @click="stopWatching(scope.row)">停止</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="pagination">
                <el-pagination
                        @current-change ="handleCurrentChange"
                        layout="prev, pager, next"
                        :total="dataTotal">
                </el-pagination>
            </div>
        </div>
        <el-dialog title="新增" :visible.sync="dialogFormVisible" width="20%">
            <el-form :model="dialogForm">
                <el-form-item>
                    <el-input placeholder="代码" v-model="dialogForm.code"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-select v-model="dialogForm.market" placeholder="请选择" value="">
                        <el-option key="sh" label="上证" value="sh"></el-option>
                        <el-option key="sz" label="深圳" value="sz"></el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="addSubmit">确 定</el-button>
            </div>
        </el-dialog>
        <el-dialog title="详细" :visible.sync="dialogFormVisible2" width="55%">
            <el-form :model="dialogForm2" :inline="true">
                <el-form-item>
                    <el-input v-model="dialogForm2.price" class="el-input">
                        <el-button slot="prepend" icon="el-icon-search">当前价</el-button>
                    </el-input>
                    <el-input v-model="dialogForm2.priceClose" class="el-input">
                        <el-button slot="prepend">昨收盘</el-button>
                    </el-input>
                    <el-input v-model="dialogForm2.priceOpen" class="el-input">
                        <el-button slot="prepend">今开盘</el-button>
                    </el-input>
                    <el-input v-model="dialogForm2.riseFall" class="el-input">
                        <el-button slot="prepend">涨&emsp;跌</el-button>
                    </el-input>
                    <el-input v-model="dialogForm2.riseFallRatio" class="el-input">
                        <el-button slot="prepend">涨跌幅</el-button>
                    </el-input>

                    <el-input v-model="dialogForm2.outerCount" class="el-input">
                        <el-button slot="prepend">外&emsp;盘</el-button>
                    </el-input>
                    <el-input v-model="dialogForm2.innerCount" class="el-input">
                        <el-button slot="prepend">内&emsp;盘</el-button>
                    </el-input>
                    <el-input v-model="dialogForm2.outerCount - dialogForm2.innerCount" class="el-input">
                        <el-button slot="prepend">差&emsp;额</el-button>
                    </el-input>
                    <br>

                    <el-input v-model="dialogForm2.priceHighest" class="el-input">
                        <el-button slot="prepend">最&emsp;高</el-button>
                    </el-input>
                    <el-input v-model="dialogForm2.priceLowest" class="el-input">
                        <el-button slot="prepend">最&emsp;低</el-button>
                    </el-input>
                    <el-input v-model="dialogForm2.dealCount" class="el-input">
                        <el-button slot="prepend">成交量</el-button>
                    </el-input>
                    <el-input v-model="(dialogForm2.dealPrice/10000).toFixed(2)" class="el-input">
                        <el-button slot="prepend">成交额</el-button>
                    </el-input>
                    <br>

                    <el-input v-model="dialogForm2.buyingOne" class="el-input">
                        <el-button slot="prepend">买&emsp;一</el-button>
                    </el-input>
                    <el-input v-model="dialogForm2.buyingTwo" class="el-input">
                        <el-button slot="prepend">买&emsp;二</el-button>
                    </el-input>
                    <el-input v-model="dialogForm2.buyingThree" class="el-input">
                        <el-button slot="prepend">买&emsp;三</el-button>
                    </el-input>
                    <el-input v-model="dialogForm2.buyingFour" class="el-input">
                        <el-button slot="prepend">买&emsp;四</el-button>
                    </el-input>
                    <el-input v-model="dialogForm2.buyingFive" class="el-input">
                        <el-button slot="prepend">买&emsp;五</el-button>
                    </el-input>

                    <el-input v-model="dialogForm2.buyingOneCount" class="el-input">
                        <el-button slot="prepend">买一量</el-button>
                    </el-input>
                    <el-input v-model="dialogForm2.buyingTwoCount" class="el-input">
                        <el-button slot="prepend">买二量</el-button>
                    </el-input>
                    <el-input v-model="dialogForm2.buyingThreeCount" class="el-input">
                        <el-button slot="prepend">买三量</el-button>
                    </el-input>
                    <el-input v-model="dialogForm2.buyingFourCount" class="el-input">
                        <el-button slot="prepend">买四量</el-button>
                    </el-input>
                    <el-input v-model="dialogForm2.buyingFiveCount" class="el-input">
                        <el-button slot="prepend">买五量</el-button>
                    </el-input>

                    <el-input v-model="dialogForm2.sellingOne" class="el-input">
                        <el-button slot="prepend">卖&emsp;一</el-button>
                    </el-input>
                    <el-input v-model="dialogForm2.sellingTwo" class="el-input">
                        <el-button slot="prepend">卖&emsp;二</el-button>
                    </el-input>
                    <el-input v-model="dialogForm2.sellingThree" class="el-input">
                        <el-button slot="prepend">卖&emsp;三</el-button>
                    </el-input>
                    <el-input v-model="dialogForm2.sellingFour" class="el-input">
                        <el-button slot="prepend">卖&emsp;四</el-button>
                    </el-input>
                    <el-input v-model="dialogForm2.sellingFive" class="el-input">
                        <el-button slot="prepend">卖&emsp;五</el-button>
                    </el-input>

                    <el-input v-model="dialogForm2.sellingOneCount" class="el-input">
                        <el-button slot="prepend">卖一量</el-button>
                    </el-input>
                    <el-input v-model="dialogForm2.sellingTwoCount" class="el-input">
                        <el-button slot="prepend">卖二量</el-button>
                    </el-input>
                    <el-input v-model="dialogForm2.sellingThreeCount" class="el-input">
                        <el-button slot="prepend">卖三量</el-button>
                    </el-input>
                    <el-input v-model="dialogForm2.sellingFourCount" class="el-input">
                        <el-button slot="prepend">卖四量</el-button>
                    </el-input>
                    <el-input v-model="dialogForm2.sellingFiveCount" class="el-input">
                        <el-button slot="prepend">卖五量</el-button>
                    </el-input>

                    <el-input v-model="dialogForm2.refreshTime" class="el-input">
                        <el-button slot="prepend">时&emsp;间</el-button>
                    </el-input>
                    <el-input v-model="dialogForm2.priceDealInfo" class="el-input">
                        <el-button slot="prepend">成交记录</el-button>
                    </el-input>

                    <el-input v-model="dialogForm2.exchangeRatio" class="el-input">
                        <el-button slot="prepend">换手率</el-button>
                    </el-input>
                    <el-input v-model="dialogForm2.earningsRatio" class="el-input">
                        <el-button slot="prepend">市盈率</el-button>
                    </el-input>
                    <el-input v-model="dialogForm2.stockAmplitude" class="el-input">
                        <el-button slot="prepend">振&emsp;幅</el-button>
                    </el-input>
                    <el-input v-model="dialogForm2.liveValue" class="el-input">
                        <el-button slot="prepend">流通市值</el-button>
                    </el-input>
                    <el-input v-model="dialogForm2.totalValue" class="el-input">
                        <el-button slot="prepend">总市值</el-button>
                    </el-input>
                    <el-input v-model="dialogForm2.priceBootRatio" class="el-input">
                        <el-button slot="prepend">市净率</el-button>
                    </el-input>
                    <el-input v-model="dialogForm2.limitUp" class="el-input">
                        <el-button slot="prepend">涨停价</el-button>
                    </el-input>
                    <el-input v-model="dialogForm2.limitDown" class="el-input">
                        <el-button slot="prepend">跌停价</el-button>
                    </el-input>
                    <el-input v-model="dialogForm2.recentlyDealInfo" style="width: 500px;">
                        <el-button slot="prepend">最近成交</el-button>
                    </el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible2 = false">取 消</el-button>
                <el-button type="primary" @click="addSubmit">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                url: this.$projectUrl + '/stockMarket/queryPageByParams',
                urlAdd: this.$projectUrl + '/stockMarket/add',
                urlStartWatch: this.$projectUrl + '/stockMarket/startWatching',
                urlStopWatch: this.$projectUrl + '/stockMarket/stopWatching',
                urlShowNewest: this.$projectUrl + '/stockMarket/showNewest',
                urlChartData: this.$projectUrl + '/stockMarket/queryChartData',
                tableData: [],
                dialogFormVisible: false,
                dialogFormVisible2: false,
                dataTotal: 1,
                timer: {},
                searchForm: {
                    id:'',
                    username: '',
                    mobile: '',
                    realName: '',
                    pageNum: 1
                },
                dialogForm: {
                    id: '',
                    code: '',
                    market: ''
                },
                dialogForm2: {
                    id: '',code: '',market: '',price: '',priceClose: '',priceOpen: '',dealCount: '',dealPrice: '',outerCount: '',
                    innerCount: '',buyingOne: '',buyingOneCount: '',buyingTwo: '',buyingTwoCount: '',buyingThree: '',buyingThreeCount: '',
                    buyingFour: '', buyingFourCount: '',buyingFive: '',buyingFiveCount: '',sellingOne: '',sellingOneCount: '',sellingTwo: '',
                    sellingTwoCount: '', sellingThree: '',sellingThreeCount: '', sellingFour: '', sellingFourCount: '',sellingFive: '',
                    sellingFiveCount: '',recentlyDealInfo: '',refreshTime: '',riseFall: '',riseFallRatio: '',priceHighest: '',
                    priceLowest: '',priceDealInfo: '',exchangeRatio: '',earningsRatio: '',stockAmplitude: '',liveValue: '',totalValue: '',
                    priceBootRatio: '',limitUp: '',limitDown: '',
                },
            }
        },
        created(){
            this.getData();
        },
        methods: {
            // 分页导航
            handleCurrentChange(val){
                this.searchForm.pageNum = val;
                this.getData();
            },
            getData(){
                this.timer = setInterval(() => {
                    this.$axios.post(this.url, this.searchForm).then((res) => {
                        if(res.data.code === "0" && res.data.total !== 0){
                            this.tableData = res.data.data.list;
                            this.dataTotal = res.data.data.total;
                        }
                    });
                }, 5000)
            },

            search(){
                this.getData();
            },
            add(){
                this.dialogForm = {};
                this.dialogFormVisible = true;
            },
            addSubmit(){
                this.$axios.post(this.urlAdd, this.dialogForm).then((res) => {
                    if(res.data.code === "0"){
                        this.msgSuccess();
                        this.getData();
                    }
                    this.dialogFormVisible = false;
                })
            },
            openInfo(row){
                this.$axios.post(this.urlShowNewest, {code: row.code}).then((res) =>{
                    if(res.data.code === "0"){
                        this.dialogForm2 = res.data.data;
                    }
                });
                this.dialogFormVisible2 = true;
            },
            // showChart(val){
            //     this.dialogForm2.searchKeys = [];
            //     this.dialogForm2.searchKeys.push(val);
            //     this.$axios.post(this.urlChartData, {code: this.dialogForm2.code, searchKeys: this.dialogForm2.searchKeys}).then((res) => {
            //         if(res.data.code === "0"){
            //             console.info(res.data.data);
            //         }
            //     });
            // },
            startWatching(row){
                this.$axios.post(this.urlStartWatch, {id: row.id, watchingStatus: 1}).then((res) => {
                    if(res.data.code === "0"){
                        this.msgSuccess();
                        this.getData();
                    }
                })
            },
            stopWatching(row){
                this.$axios.post(this.urlStopWatch, {id: row.id, watchingStatus: 0}).then((res) => {
                    if(res.data.code === "0"){
                        this.msgSuccess();
                        this.getData();
                    }
                })
            },
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
    width: 300px;
    display: inline-block;
}
.el-input {
    width: 160px;
}
</style>
