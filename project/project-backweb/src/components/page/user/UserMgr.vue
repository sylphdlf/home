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
                <el-input v-model="select_word" placeholder="用户名/真实姓名/手机号码" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="search" @click="search">搜索</el-button>
            </div>
            <el-table :data="tableData" border style="width: 100%" ref="multipleTable" @selection-change="handleSelectionChange">
                <el-table-column type="selection" width="35"></el-table-column>
                <el-table-column prop="username" label="用户名"></el-table-column>
                <el-table-column prop="realName" label="真实姓名"></el-table-column>
                <el-table-column prop="mobile" label="手机"></el-table-column>
                <el-table-column prop="telephone" label="座机"></el-table-column>
                <el-table-column prop="createTime" label="创建时间"></el-table-column>
                <el-table-column label="操作" width="100">
                    <template slot-scope="scope">
                        <el-button size="small"
                                @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
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
        <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="25%">
            <el-form :model="userData" label-width="70px">
                <el-form-item label="用户名">
                    <el-input v-model="userData.username" auto-complete="off" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="真实姓名">
                    <el-input v-model="userData.realName" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="手机">
                    <el-input v-model="userData.mobile" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="座机">
                    <el-input v-model="userData.telephone" auto-complete="off"></el-input>
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
                url: this.$projectUrl + '/user/queryListByParams',
                tableData: [],
                cur_page: 1,
                multipleSelection: [],
                select_word: '',
                del_list: [],
                is_search: false,
                dataTotal:1,
                dialogFormVisible: false,
                userData: {
                    id: '',
                    username: '',
                    realName: '',
                    mobile: '',
                    telephone: ''
                },
                // formLabelWidth: '50px'
            }
        },
        created(){
            this.getData();
        },
        computed: {
            data(){
                // return this.tableData.filter((d) => {
                    // console.info(d);
                //     let is_del = false;
                //     for (let i = 0; i < this.del_list.length; i++) {
                //         if(d.name === this.del_list[i].name){
                //             is_del = true;
                //             break;
                //         }
                //     }
                //     if(!is_del){
                //         if(d.address.indexOf(this.select_cate) > -1 &&
                //             (d.name.indexOf(this.select_word) > -1 ||
                //             d.address.indexOf(this.select_word) > -1)
                //         ){
                //             return d;
                //         }
                //     }
                // })
            }
        },
        methods: {
            // 分页导航
            handleCurrentChange(val){
                this.cur_page = val;
                this.getData();
            },
            // 获取 easy-mock 的模拟数据
            getData(){
                // 开发环境使用 easy-mock 数据，正式环境使用 json 文件
                // if(process.env.NODE_ENV === 'development'){
                //     this.url = '/ms/table/list';
                // };
                this.$axios.post(this.url, {pageNum:this.cur_page}).then((res) => {
                    console.info(res.data);
                    this.tableData = res.data.data.list;
                    this.dataTotal = res.data.data.total;
                })
            },
            search(){
                this.is_search = true;
                console.info(this.select_word);
                //正则判断是否是手机
                var rex = /^1[3456789]\d{9}$/;
                //手机
                if (rex.test(this.select_word)) {
                    
                }else{

                }
            },
            // dateFormat:function(row, column) {
            //     var date = row[column.property];
            //     if (date == undefined) {
            //         return "";
            //     }
            //     return this.$moment(date).format("YYYY-MM-DD HH:mm:ss");
            // },
            filterTag(value, row) {
                return row.tag === value;
            },
            handleEdit(index, row) {
                // this.$message('编辑第'+(index+1)+'行');
                this.dialogFormVisible = true;
                this.userData = row;
                console.info(row)
            },
            handleDelete(index, row) {
                this.$message.error('删除第'+(index+1)+'行');
            },
            delAll(){
                const length = this.multipleSelection.length;
                let str = '';
                this.del_list = this.del_list.concat(this.multipleSelection);
                for (let i = 0; i < length; i++) {
                    str += this.multipleSelection[i].name + ' ';
                }
                this.$message.error('删除了'+str);
                this.multipleSelection = [];
            },
            handleSelectionChange(val) {
                this.multipleSelection = val;
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
