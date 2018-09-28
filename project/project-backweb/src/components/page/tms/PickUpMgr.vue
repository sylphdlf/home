<template>
    <div class="table">
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-tickets"></i> 物流管理</el-breadcrumb-item>
                <el-breadcrumb-item>托运单管理</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <div class="block"></div>
            </div>
            <div class="handle-box">
                <el-button type="primary" icon="search" @click="search">搜索</el-button>
                <el-button type="primary" icon="search" @click="create">新增</el-button>
            </div>
            <template>
                <el-table :data="tableData" border style="width: 100%">
                    <el-table-column prop="pickupCode" label="批次"></el-table-column>
                    <el-table-column prop="site" label="始发地"></el-table-column>
                    <el-table-column prop="price" label="派车成本（元）"></el-table-column>
                    <el-table-column prop="count" label="订单数量"></el-table-column>
                    <el-table-column label="接货时间">
                        <template slot-scope="scope" width="135">
                            {{scope.row.pickupTime | dateTimeFilter}}
                        </template>
                    </el-table-column>
                    <el-table-column label="创建时间" width="135">
                        <template slot-scope="scope">
                            {{scope.row.createTime | dateTimeFilter}}
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
                url: this.$projectUrl + '/pickup/queryPageByParams',
                tableData: [],
                multipleSelection: [],
                select_word: '',
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
                // formLabelWidth: '50px'
                pickerOptions2: {
                    shortcuts: [{
                        text: '最近一周',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                            picker.$emit('pick', [start, end]);
                        }
                    }, {
                        text: '最近一个月',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                            picker.$emit('pick', [start, end]);
                        }
                    }, {
                        text: '最近三个月',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
                            picker.$emit('pick', [start, end]);
                        }
                    }]
                },
                options: [{
                    value: '选项1',
                    label: '黄金糕'
                }, {
                    value: '选项2',
                    label: '双皮奶'
                }, {
                    value: '选项3',
                    label: '蚵仔煎'
                }, {
                    value: '选项4',
                    label: '龙须面'
                }, {
                    value: '选项5',
                    label: '北京烤鸭'
                }],
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
                this.$router.push('/pickupMgr_add');
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
