<template>
    <div class="table">
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-tickets"></i> 基础信息管理</el-breadcrumb-item>
                <el-breadcrumb-item>联系人管理</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-input v-model="select_word" placeholder="姓名" class="handle-input mr10" @keyup.enter.native="search"></el-input>
                <el-button type="primary" icon="search" @click="search">搜索</el-button>
                <el-button type="primary" icon="search" @click="add">新增</el-button>
            </div>
            <el-table :data="tableData" border style="width: 100%">
                <!--<el-table-column prop="code" label="助记码"></el-table-column>-->
                <el-table-column prop="name" label="姓名"></el-table-column>
                <el-table-column label="类型">
                    <template slot-scope="scope">
                        {{scope.row.type | formatterType}}
                    </template>
                </el-table-column>
                <el-table-column prop="telephone" label="座机"></el-table-column>
                <el-table-column prop="mobile" label="手机"></el-table-column>
                <el-table-column prop="address" label="地址"></el-table-column>
                <el-table-column label="创建时间">
                    <template slot-scope="scope">
                        {{createTime | dateTimeFilter}}
                    </template>
                </el-table-column>
                <el-table-column label="操作" width="100" align="center">
                    <template slot-scope="scope">
                        <el-button size="small" @click="del(scope.$index, scope.row)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="pagination">
                <el-pagination
                        @current-change ="handleCurrentChange"
                        layout="prev, pager, next, total"
                        :total="dataTotal">
                </el-pagination>
            </div>
        </div>
        <el-dialog title="联系人信息" :visible.sync="dialogFormVisible" width="25%">
            <el-form :model="dialogData" :rules="dialogRules" ref="dialogData"  label-width="80px">
                <el-form-item prop="name" label="姓名">
                    <el-input v-model="dialogData.name"></el-input>
                </el-form-item>
                <el-form-item prop="type" label="类型">
                    <el-select v-model="dialogData.type">
                        <el-option label="发货人" value="1"></el-option>
                        <el-option label="收货人" value="2"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item prop="telephone" label="座机">
                    <el-input placeholder="座机" v-model="dialogData.telephone"></el-input>
                </el-form-item>
                <el-form-item prop="mobile" label="手机">
                    <el-input v-model="dialogData.mobile"></el-input>
                </el-form-item>
                <el-form-item prop="address" label="地址">
                    <el-input v-model="dialogData.address"></el-input>
                </el-form-item>
                <el-form-item prop="accountBank" label="开户行" v-if="dialogData.type=='1'">
                    <el-input v-model="dialogData.accountBank"></el-input>
                </el-form-item>
                <el-form-item prop="accountCard" label="卡号" v-if="dialogData.type=='1'">
                    <el-input v-model="dialogData.accountCard"></el-input>
                </el-form-item>
                <el-form-item prop="accountName" label="账户名" v-if="dialogData.type=='1'">
                    <el-input v-model="dialogData.accountName"></el-input>
                </el-form-item>
                <el-form-item prop="invoiceType" label="是否开票" v-if="dialogData.type=='1'">
                    <el-select v-model="dialogData.invoiceType">
                        <el-option label="是" value="1"></el-option>
                        <el-option label="否" value="2"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item prop="oftenSend" label="常发品名" v-if="dialogData.type=='2'">
                    <el-input v-model="dialogData.oftenSend"></el-input>
                </el-form-item>
                <el-form-item prop="oftenPackage" label="常用包装" v-if="dialogData.type=='2'">
                    <el-input v-model="dialogData.oftenPackage"></el-input>
                </el-form-item>
                <el-form-item prop="payType" label="结算方式" v-if="dialogData.type=='2'">
                    <el-input v-model="dialogData.payType"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="addCommit">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                url: this.$projectUrl + '/contacts/queryPageByParams',
                urlAdd: this.$projectUrl + '/contacts/add',
                urlDel: this.$projectUrl + '/contacts/del',
                tableData: [],
                multipleSelection: [],
                select_word: '',
                is_search: false,
                dataTotal:1,
                dialogFormVisible: false,
                searchForm: {
                    name: '',
                    pageNum: 1
                },
                dialogData: {
                    id: '',
                    code: '',
                    name: '',
                    type: '',
                    mobile: '',
                    telephone: '',
                    address: '',
                    accountBank: '',
                    accountCard: '',
                    accountName: '',
                    invoiceType: '',
                    oftenSend: '',
                    oftenPackage: '',
                    payType: '',
                    remarks: ''
                },
                dialogRules:{
                    name: [{ required: true, message: '请输入姓名', trigger: 'blur' }],
                    // type: [{ required: true, message: '请输入类型', trigger: 'blur' }],
                    // mobile: [{ required: true, message: '请输入手机', trigger: 'blur' }],
                    // address: [{ required: true, message: '请输入地址', trigger: 'blur' }],
                    // accountBank: [{ required: true, message: '请输入开户行', trigger: 'blur' }],
                    // accountCard: [{ required: true, message: '请输入银行卡号', trigger: 'blur' }],
                    // accountName: [{ required: true, message: '请输入开户名', trigger: 'blur' }],
                    // invoiceType: [{ required: true, message: '请选择是否开票', trigger: 'blur' }],
                },
            }
        },
        created(){
            this.searchForm.pageNum = 1;
            this.getData();
        },
        filters: {
            formatterType: function (val) {
                return val===1 ? '发货人' : '收货人';
            }
        },
        methods: {
            // 分页导航
            handleCurrentChange(val){
                this.searchForm.pageNum = val;
                this.getData();
            },
            // 获取 easy-mock 的模拟数据
            getData(){
                this.$axios.post(this.url, this.searchForm).then((res) => {
                    if(res.data.code === "0" && res.data.total !== 0){
                        this.tableData = res.data.data.list;
                        this.dataTotal = res.data.data.total;
                    }
                })
            },
            search(){
                this.is_search = true;
                //清空数据
                if(this.select_word === ""){
                    this.searchForm.vehicleNumber = ""
                }
                //正则判断是否是手机
                // let rex = /^1[3456789]\d{9}$/;
                //手机
                // if (rex.test(this.select_word)) {
                //     this.searchForm.mobile = this.select_word;
                //     this.searchForm.username = "";
                // }else{
                //     this.searchForm.mobile  = "";
                //     this.searchForm.username = this.select_word;
                // }
                this.searchForm.name = this.select_word;
                this.getData();
            },
            add: function(){
                this.dialogData = {};
                this.dialogFormVisible = true;
            },
            addCommit:function(){
                this.$refs['dialogData'].validate((valid) => {
                    if (valid) {
                        // this.$router.push('/readme');
                        return false;
                    } else {
                        return false;
                    }
                });
                this.$axios.post(this.urlAdd, this.dialogData).then((res) => {
                    if(res.data.code === "0"){
                        this.msgSuccess();
                        this.dialogFormVisible = false;
                    }
                });
            },
            del(index, row) {
                this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning',
                    center: true
                }).then(() => {
                    this.$axios.post(this.urlDel,row).then((res) => {
                        if(res.data.code === '0'){
                            this.$message({
                                type: 'success',
                                message: '删除成功!'
                            });
                            this.getData();
                        }
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
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
