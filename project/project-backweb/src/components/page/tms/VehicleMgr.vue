<template>
    <div class="table">
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-tickets"></i> 基础信息管理</el-breadcrumb-item>
                <el-breadcrumb-item>车辆管理</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-input v-model="select_word" placeholder="姓名" class="handle-input mr10" @keyup.enter.native="search"></el-input>
                <el-button type="primary" icon="search" @click="search">搜索</el-button>
                <el-button type="primary" icon="search" @click="add">新增</el-button>
            </div>
            <el-table :data="tableData" border style="width: 100%">
                <el-table-column prop="vehicleNumber" label="车牌号"></el-table-column>
                <el-table-column prop="vehicleType" label="车型"></el-table-column>
                <el-table-column label="长宽高">
                    <template slot-scope="scope">
                        {{scope.row.vehicleLength + '*' + scope.row.vehicleWidth + '*' + scope.row.vehicleHeight}}
                    </template>
                </el-table-column>
                <el-table-column prop="vehicleVin" label="VIN"></el-table-column>
                <el-table-column prop="driverName" label="司机姓名"></el-table-column>
                <el-table-column prop="driverMobile" label="司机手机"></el-table-column>
                <el-table-column prop="driverLicence" label="驾照"></el-table-column>
                <el-table-column prop="createTime" label="创建时间">
                    <template slot-scope="scope">
                        {{scope.row.createTime | dateTimeFilter}}
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
        <el-dialog title="车辆信息" :visible.sync="dialogFormVisible" width="40%">
            <el-form :model="dialogData" :rules="dialogRules" ref="dialogData"  label-width="80px" size="mini" :inline="true">
                <el-form-item prop="name" label="车牌号">
                    <el-input v-model="dialogData.vehicleNumber"></el-input>
                </el-form-item>
                <el-form-item prop="type" label="车型">
                    <el-select v-model="dialogData.vehicleType">
                        <el-option label="重型卡车" value="1"></el-option>
                        <el-option label="中型卡车" value="2"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item prop="vehicleLength" label="车长">
                    <el-input v-model="dialogData.vehicleLength"></el-input>
                </el-form-item>
                <el-form-item prop="vehicleWidth" label="车宽">
                    <el-input v-model="dialogData.vehicleWidth"></el-input>
                </el-form-item>
                <el-form-item prop="vehicleHeight" label="车高">
                    <el-input v-model="dialogData.vehicleHeight"></el-input>
                </el-form-item>
                <el-form-item prop="vehicleVin" label="VIN码">
                    <el-input v-model="dialogData.vehicleVin"></el-input>
                </el-form-item>
                <el-form-item prop="driverName" label="司机姓名">
                    <el-input v-model="dialogData.driverName"></el-input>
                </el-form-item>
                <el-form-item prop="driverMobile" label="司机手机">
                    <el-input v-model="dialogData.driverMobile"></el-input>
                </el-form-item>
                <el-form-item prop="driverTelephone" label="司机座机">
                    <el-input v-model="dialogData.driverTelephone"></el-input>
                </el-form-item>
                <el-form-item prop="driverAddr" label="司机地址">
                    <el-input v-model="dialogData.driverAddr"></el-input>
                </el-form-item>
                <el-form-item prop="driverLicence" label="司机驾照">
                    <el-input v-model="dialogData.driverLicence"></el-input>
                </el-form-item>
                <el-form-item prop="ownerName" label="车主姓名">
                    <el-input v-model="dialogData.ownerName"></el-input>
                </el-form-item>
                <el-form-item prop="ownerMobile" label="车主手机">
                    <el-input v-model="dialogData.ownerMobile"></el-input>
                </el-form-item>
                <el-form-item prop="accountBank" label="开户行">
                    <el-input v-model="dialogData.accountBank"></el-input>
                </el-form-item>
                <el-form-item prop="accountName" label="开户名">
                    <el-input v-model="dialogData.accountName"></el-input>
                </el-form-item>
                <el-form-item prop="accountCard" label="开户卡号">
                    <el-input v-model="dialogData.accountCard"></el-input>
                </el-form-item>
                <el-form-item prop="remarks" label="备注">
                    <el-input type="textarea" v-model="dialogData.remarks"></el-input>
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
                url: this.$projectUrl + '/vehicle/queryPageByParams',
                urlAdd: this.$projectUrl + '/vehicle/add',
                urlDel: this.$projectUrl + '/vehicle/del',
                tableData: [],
                multipleSelection: [],
                select_word: '',
                is_search: false,
                dataTotal:1,
                dialogFormVisible: false,
                searchForm: {
                    vehicleNumber: '',
                    pageNum: 1
                },
                dialogData: {
                    vehicleNumber: '',
                    vehicleType: '',
                    vehicleLength: '',
                    vehicleWidth: '',
                    vehicleHeight: '',
                    vehicleVin: '',
                    driverName: '',
                    driverMobile: '',
                    driverTelephone: '',
                    driverAddr: '',
                    driverLicence: '',
                    ownerName: '',
                    ownerMobile: '',
                    accountBank: '',
                    accountName: '',
                    accountCard: '',
                    remarks: '',
                    createTime: '',
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
                }
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
                this.searchForm.vehicleNumber = this.select_word;
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
