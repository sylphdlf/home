<template>
    <div class="table">
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-tickets"></i> 用户管理</el-breadcrumb-item>
                <el-breadcrumb-item>用户列表</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
            </div>
            <el-table :data="tableData" border style="width: 100%">
                <el-table-column type="selection" width="35"></el-table-column>
                <el-table-column prop="username" label="用户名"></el-table-column>
                <el-table-column prop="realName" label="真实姓名"></el-table-column>
                <el-table-column label="最近登录时间">
                    <template slot-scope="scope">
                        {{scope.row.lastAccessTime | dateTimeFilter}}
                    </template>
                </el-table-column>
                <el-table-column label="操作" width="300">
                    <template slot-scope="scope">
                        <el-button size="small" type="primary" @click="accessLog(scope.row)">访问记录</el-button>
                        <el-button size="small" type="warning" @click="kickOff(scope.row)">强制下线</el-button>
                        <el-button size="small" type="danger" @click="freeze(scope.row)">冻结用户</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <!--<div class="pagination">-->
                <!--<el-pagination-->
                        <!--@current-change ="handleCurrentChange"-->
                        <!--layout="prev, pager, next, total"-->
                        <!--:total="dataTotal">-->
                <!--</el-pagination>-->
            <!--</div>-->
        </div>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                url: this.$projectUrl + '/userWatch/searchOnlineUsers',
                urlKickOff: this.$projectUrl + '/userWatch/kickOffUser',
                searchForm: {

                },
                tableData: []
            }
        },
        created(){
            this.searchForm.pageNum = 1;
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
                        this.tableData = res.data.data;
                        // this.dataTotal = this.tableData.length;
                    }
                })
            },
            accessLog(row){

            },
            kickOff(row){
                this.$axios.post(this.urlKickOff, {sessionId: row.sessionId}).then((res) => {
                    if(res.data.code === "0"){
                        this.getData();
                        $this.msgSuccess();
                    }
                })
            },
            freeze(row){

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
</style>
