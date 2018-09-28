<template>
    <div class="table">
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-tickets"></i> 物流管理</el-breadcrumb-item>
                <el-breadcrumb-item>外发管理</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-button type="primary" icon="search" @click="search">搜索</el-button>
                <el-button type="primary" icon="search" @click="create">新增</el-button>
                <el-button type="primary" icon="search" @click="sendoutLocal">本地外发</el-button>
                <el-button type="primary" icon="search" @click="create">送出外发</el-button>
            </div>
            <template>
            <el-table :data="tableData" border style="width: 100%">
                <el-table-column type="selection" width="35"></el-table-column>
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
        <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="25%">
            <el-form :model="userData" label-width="80px">
                <el-form-item label="用户名">
                    <el-input v-model="userData.username" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="真实姓名">
                    <el-input v-model="userData.realName"></el-input>
                </el-form-item>
                <el-form-item label="手机">
                    <el-input v-model="userData.mobile"></el-input>
                </el-form-item>
                <el-form-item label="座机">
                    <el-input v-model="userData.telephone"></el-input>
                </el-form-item>
            </el-form>
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
                multipleSelection: [],
                // select_word: '',
                del_list: [],
                is_search: false,
                dataTotal:1,
                dialogFormVisible: false,
                searchForm: {
                    status: '',
                },
                userData: {
                    id: '',
                    username: '',
                    realName: '',
                    mobile: '',
                    telephone: ''
                },
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
            create(){
                this.$router.push('/consignMgr_add');
            },
            sendoutLocal(){
                this.$router.push('/sendout_local');
            }
        }
    }
</script>

<style scoped>
.handle-box{
    margin-bottom: 20px;
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
