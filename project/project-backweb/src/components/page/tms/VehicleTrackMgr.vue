<template>
    <div class="table">
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-tickets"></i> 物流管理</el-breadcrumb-item>
                <el-breadcrumb-item>车辆跟踪</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <div class="block">
                </div>
            </div>
            <div class="handle-box">
                <el-button type="primary" icon="search" @click="search">搜索</el-button>
                <el-button type="primary" icon="search" @click="track">跟踪</el-button>
                <el-button type="primary" icon="search" @click="">确认到达</el-button>
                <el-button type="primary" icon="search" @click="">取消到达</el-button>
            </div>
            <template>
            <el-table :data="tableData" border style="width: 100%">
                <el-table-column prop="status" label="提交状态" :formatter="statusFormatter" width="80"></el-table-column>
                <el-table-column prop="departure" label="始发地"></el-table-column>
                <el-table-column prop="destination" label="目的地"></el-table-column>
                <el-table-column prop="productName" label="品名" width="120"></el-table-column>
                <el-table-column prop="consignorName" label="发货方" width="120"></el-table-column>
                <el-table-column prop="consignorMobile" label="发货方手机" width="100"></el-table-column>
                <el-table-column prop="consigneeName" label="收货方" width="100"></el-table-column>
                <el-table-column prop="consigneeMobile" label="收货方手机" width="100"></el-table-column>
                <el-table-column label="创建时间" width="135">
                    <template slot-scope="scope">
                        {{scope.row.createTime | dateTimeFilter}}
                    </template>
                </el-table-column>
                <el-table-column label="操作" width="80">
                    <template slot-scope="scope">
                        <el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                    </template>
                </el-table-column>
            </el-table>
            </template>
            <div class="pagination">
                <el-pagination @current-change ="handleCurrentChange" layout="prev, pager, next, total" :total="dataTotal"></el-pagination>
            </div>
        </div>
        <el-dialog title="跟踪信息" :visible.sync="dialogFormVisible" width="75%">
            <el-form :model="trackInfo" label-width="80px" :inline="true">
                <div class="handle-box" align="center">
                    <el-form-item prop="consignorTel" label="发车批次">
                        <el-input class="handle-input" v-model="trackInfo.consignorTel" disabled></el-input>
                    </el-form-item>
                    <el-form-item prop="consignorTel" label="合同编号">
                        <el-input class="handle-input" v-model="trackInfo.consignorTel" disabled></el-input>
                    </el-form-item>
                    <el-form-item prop="consignorMobile" label="始发地">
                        <el-input class="handle-input" v-model="trackInfo.consignorMobile" disabled></el-input>
                    </el-form-item>
                    <el-form-item prop="consignorAddr" label="目的地">
                        <el-input class="handle-input" v-model="trackInfo.consignorAddr" disabled></el-input>
                    </el-form-item>
                    <el-form-item prop="consignorAddr" label="发车时间">
                        <el-input class="handle-input" v-model="trackInfo.consignorAddr" disabled></el-input>
                    </el-form-item>
                    <el-form-item prop="consignorAddr" label="制单人">
                        <el-input class="handle-input" v-model="trackInfo.consignorAddr" disabled></el-input>
                    </el-form-item>
                </div>
            </el-form>
            <el-tabs type="border-card">
                <el-tab-pane label="跟踪信息">
                    <el-table :data="tableData" border style="width: 100%">
                        <el-table-column label="跟踪时间">
                            <template slot-scope="scope">
                                {{scope.row.createTime | dateTimeFilter}}
                            </template>
                        </el-table-column>
                        <el-table-column prop="departure" label="省份"></el-table-column>
                        <el-table-column prop="departure" label="到达城市"></el-table-column>
                        <el-table-column prop="departure" label="在途信息"></el-table-column>
                        <el-table-column prop="departure" label="跟踪人"></el-table-column>
                    </el-table><br>
                    <el-form :model="trackForm" class="demo-ruleForm" :inline="true">
                        <div class="handle-box">
                            <el-form-item prop="consignorAddr" label="跟踪时间">
                                <el-input class="handle-input" v-model="trackForm.consignorAddr" disabled></el-input>
                            </el-form-item>
                            <el-form-item prop="consignorAddr" label="到达城市">
                                <el-input class="handle-input" v-model="trackForm.consignorAddr" disabled></el-input>
                            </el-form-item>
                            <el-form-item prop="consignorAddr" label="省">
                                <el-input class="handle-input" v-model="trackForm.consignorAddr" disabled></el-input>
                            </el-form-item>市
                        </div>
                        <div class="handle-box">
                            <el-form-item prop="consignorAddr" label="位置信息">
                                <el-input type="textarea" :rows="3" style="width:500px" v-model="trackForm.consignorAddr" disabled></el-input>
                            </el-form-item>
                        </div>
                    </el-form>
                </el-tab-pane>
                <el-tab-pane label="配载明细">配载明细</el-tab-pane>
                <el-tab-pane label="运输合同">运输合同</el-tab-pane>
                <el-tab-pane label="地图查询">
                    <iframe width='1165' height='440' frameborder='0' scrolling='no' marginheight='0' marginwidth='0' src='http://f.amap.com/31xA1_0F95TJB'></iframe>'
                </el-tab-pane>
            </el-tabs>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                url: this.$projectUrl + '/consign/queryPageByParams',
                tableData: [],
                dataTotal:1,
                dialogFormVisible: false,
                searchForm:{

                },
                trackInfo:{

                },
                trackForm:{},

            }
        },
        created(){
            this.searchForm.pageNum = 1;
            this.getData();
        },
        methods: {
            statusFormatter: function(row, column){
                if(row.status === 0){
                    return '草稿';
                }else if(row.status === 1){
                    return '待提交';
                }else if(row.status === 2){
                    return '已提交';
                }
            },
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
            search(){
                this.getData();
            },
            track(){
                this.dialogFormVisible = true;
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
    width: 300px;
    display: inline-block;
}
.demo-table-expand {
    font-size: 0;
}
.demo-table-expand label {
    width: 90px;
    color: #99a9bf;
}
.demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 25%;
}
</style>
