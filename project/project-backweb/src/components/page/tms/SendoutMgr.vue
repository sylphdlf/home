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
                <el-button type="primary" icon="search" @click="sendoutThird">送出外发</el-button>
                <el-button type="primary" icon="search" @click="signFor">签收</el-button>
                <el-button type="primary" icon="search" @click="signFor">跟踪(TODO)</el-button>
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
        <el-dialog title="签收" :visible.sync="dialogFormVisible" width="50%">
            <p>签收信息</p>
            <hr style="margin-top:2px; color: #bbbbbb;"><br>
            <el-form :model="userData" label-width="70px" :inline="true">
                <div class="handle-box" align="center">
                    <el-form-item label="签收日期">
                        <el-input class="handle-input" v-model="userData.username" :disabled="true"></el-input>
                    </el-form-item>
                    <el-form-item label="签收人">
                        <el-input class="handle-input" v-model="userData.realName"></el-input>
                    </el-form-item>
                    <el-form-item label="身份证号">
                        <el-input class="handle-input" v-model="userData.mobile"></el-input>
                    </el-form-item>
                    <el-form-item label="联系电话">
                        <el-input class="handle-input" v-model="userData.telephone"></el-input>
                    </el-form-item>
                    <el-form-item label="签收类型">
                        <el-input class="handle-input" v-model="userData.telephone"></el-input>
                    </el-form-item>
                    <el-form-item label="备注">
                        <el-input class="handle-input" v-model="userData.telephone"></el-input>
                    </el-form-item>
                </div>
                <div class="handle-box" align="center">
                    <template>
                        <el-checkbox v-model="checked1">发送短信（发货方）</el-checkbox>
                        <el-checkbox v-model="checked2">发送短信（收货方）</el-checkbox>
                    </template>
                </div>
                <p>运单信息</p>
                <hr style="margin-top:2px; color: #bbbbbb;"><br>
                <div class="handle-box" align="center">
                    <el-form-item label="运单号">
                        <el-input class="handle-input" v-model="userData.username" :disabled="true"></el-input>
                    </el-form-item>
                    <el-form-item label="始发地">
                        <el-input class="handle-input" v-model="userData.username" :disabled="true"></el-input>
                    </el-form-item>
                    <el-form-item label="目的地">
                        <el-input class="handle-input" v-model="userData.username" :disabled="true"></el-input>
                    </el-form-item>
                </div>
                <div class="handle-box" align="center">
                    <el-form-item label="发货方">
                        <el-input class="handle-input" v-model="userData.username" :disabled="true"></el-input>
                    </el-form-item>
                    <el-form-item label="收货方">
                        <el-input class="handle-input" v-model="userData.username" :disabled="true"></el-input>
                    </el-form-item>
                    <el-form-item label="到货日期">
                        <el-input class="handle-input" v-model="userData.username" :disabled="true"></el-input>
                    </el-form-item>
                </div>
                <div class="handle-box">
                    <el-form-item label="运单日期">
                        <el-input class="handle-input" v-model="userData.username" :disabled="true"></el-input>
                    </el-form-item>
                </div>
                <p>中转信息</p>
                <hr style="margin-top:2px; color: #bbbbbb;"><br>
                <div class="handle-box" align="center">
                    <el-form-item label="承运公司">
                        <el-input class="handle-input" v-model="userData.username" :disabled="true"></el-input>
                    </el-form-item>
                    <el-form-item label="中转单号">
                        <el-input class="handle-input" v-model="userData.username" :disabled="true"></el-input>
                    </el-form-item>
                    <el-form-item label="本地电话">
                        <el-input class="handle-input" v-model="userData.username" :disabled="true"></el-input>
                    </el-form-item>
                </div>
                <div class="handle-box" align="center">
                    <el-form-item label="提货电话">
                        <el-input class="handle-input" v-model="userData.username" :disabled="true"></el-input>
                    </el-form-item>
                    <el-form-item label="货物名称">
                        <el-input class="handle-input" v-model="userData.username" :disabled="true"></el-input>
                    </el-form-item>
                    <el-form-item label="中转日期">
                        <el-input class="handle-input" v-model="userData.username" :disabled="true"></el-input>
                    </el-form-item>
                </div>
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
                checked1:false,
                checked2:false,
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
            sendoutLocal(){
                this.$router.push('/sendoutMgr_local');
            },
            sendoutThird(){
                this.$router.push('/sendoutMgr_third');
            },
            signFor(){
                this.dialogFormVisible = true;
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
.handle-input{
    width: 175px;
    display: inline-block;
}
.handle-input-half{
    width: 80px;
    display: inline-block;
}
</style>
