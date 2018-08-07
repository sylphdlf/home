<template>
    <div class="table">
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-tickets"></i> 用户管理</el-breadcrumb-item>
                <el-breadcrumb-item>角色管理</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-input v-model="select_word" placeholder="角色名称" class="handle-input mr10" @keyup.enter.native="search"></el-input>
                <el-button type="primary" icon="search" @click="search">搜索</el-button>
                <el-button type="primary" icon="search" @click="handleAdd">新增</el-button>
            </div>
            <el-table :data="tableData" border style="width: 100%" ref="multipleTable" @selection-change="handleSelectionChange">
                <el-table-column type="selection" width="35"></el-table-column>
                <el-table-column prop="code" label="角色编号"></el-table-column>
                <el-table-column prop="name" label="角色名称"></el-table-column>
                <el-table-column prop="createTime" label="创建时间" :formatter="dateFormat"></el-table-column>
                <el-table-column prop="remarks" label="备注"></el-table-column>
                <el-table-column label="操作" width="100">
                    <template slot-scope="scope">
                        <el-button size="small"
                                @click="handleEdit(scope.$index, scope.row)">编辑(todo)</el-button>
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
        <el-dialog title="角色信息" :visible.sync="dialogFormVisible" width="25%">
            <el-form :model="dialogData" label-width="80px" :rules="dialogRules" ref="dialogData">
                <el-form-item label="角色名称" prop="name">
                    <el-input v-model="dialogData.name" auto-complete="off" @keyup.enter.native="submitForm('dialogData')"></el-input>
                </el-form-item>
                <el-form-item label="角色编号" prop="code">
                    <el-input v-model="dialogData.code" auto-complete="off" @keyup.enter.native="submitForm('dialogData')"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="submitForm('dialogData')">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                url: this.$projectUrl + '/role/queryListByParams',
                tableData: [],
                multipleSelection: [],
                select_word: '',
                del_list: [],
                is_search: false,
                dataTotal:1,
                dialogFormVisible: false,
                searchForm: {
                    name: '',
                    code: '',
                },
                dialogData: {
                    id: '',
                    name: '',
                    code: '',
                },
                dialogRules: {
                    name: [
                        { required: true, message: '请输入角色名称', trigger: 'blur' }
                    ],
                    code: [
                        { required: true, message: '请输入角色编号', trigger: 'blur' }
                    ]
                },
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
            // 获取 easy-mock 的模拟数据
            getData(){
                this.$axios.post(this.url, this.searchForm).then((res) => {
                    if(res.data.total !== 0){
                        this.tableData = res.data.data.list;
                        this.dataTotal = res.data.data.total;
                    }
                })
            },
            search(){
                this.is_search = true;
                //清空数据
                if(this.select_word === ""){
                    this.searchForm.name = ""
                    this.searchForm.code = ""
                }
                this.getData();
            },
            handleAdd(){
                this.dialogData = {};
                this.dialogFormVisible = true;
            },
            //日期格式化
            dateFormat:function(row, column) {
                let date = row.createTime;
                if (date === undefined) {
                    return "";
                }
                return this.$moment(date).format("YYYY-MM-DD HH:mm:ss");
            },
            filterTag(value, row) {
                return row.tag === value;
            },
            handleEdit(index, row) {
                // this.$message('编辑第'+(index+1)+'行');
                this.dialogFormVisible = true;
                this.dialogData = row;
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
            },
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        // this.$router.push('/readme');
                    } else {
                        return false;
                    }
                });
                this.$axios.post("/project-web/role/add", this.dialogData).then(result =>{
                    if(result.data.code === "0"){
                        this.dialogFormVisible = false;
                        this.getData();
                    }else{
                        // this.messageShow.error = result.data.msg;
                        return false;
                    }
                });
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
</style>
