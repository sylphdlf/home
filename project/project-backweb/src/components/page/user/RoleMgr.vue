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
            <el-table :data="tableData" border style="width: 100%" @selection-change="handleSelectionChange">
                <el-table-column type="selection" width="35"></el-table-column>
                <el-table-column prop="code" label="角色编号"></el-table-column>
                <el-table-column prop="name" label="角色名称"></el-table-column>
                <el-table-column prop="createTime" label="创建时间" :formatter="dateFormat"></el-table-column>
                <el-table-column prop="remarks" label="备注"></el-table-column>
                <el-table-column label="操作" width="175">
                    <template slot-scope="scope">
                        <el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                        <el-button type="primary" size="small" @click="funTreeWin(scope.$index, scope.row)">绑定权限</el-button>
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
                    <el-input v-model="dialogData.name" @keyup.enter.native="submitForm('dialogData')"></el-input>
                </el-form-item>
                <el-form-item label="角色编号" prop="code">
                    <el-input v-model="dialogData.code" @keyup.enter.native="submitForm('dialogData')"></el-input>
                </el-form-item>
                <el-form-item label="备注">
                    <el-input type="textarea" v-model="dialogData.remarks"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="submitForm('dialogData')">确 定</el-button>
            </div>
        </el-dialog>
        <el-dialog title="绑定权限" :visible.sync="dialogTreeVisible" width="25%">
            <div class="block">
                <el-tree :data="dataParse" show-checkbox node-key="id" ref="funTree"
                         default-expand-all>
                <span class="custom-tree-node" slot-scope="{ node, data }">
                    <span>{{ node.label }}</span>
                </span>
                </el-tree>
                <div class="handle-box" align="center">
                    <el-button type="primary" @click="bindingFun">确认</el-button>
                </div>
            </div>
        </el-dialog>
    </div>
</template>
<script>
    export default {
        data() {
            const data = [];
            return {
                url: this.$projectUrl + '/role/queryListByParams',
                roleAddUrl: this.$projectUrl + '/role/add',
                funTreeUrl: this.$projectUrl + '/fun/getFunTree',
                bindingFunUrl: this.$projectUrl + '/role/bindingFun',
                dataParse: [],
                tableData: [],
                multipleSelection: [],
                select_word: '',
                del_list: [],
                is_search: false,
                dataTotal:1,
                dialogFormVisible: false,
                dialogTreeVisible: false,
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
                dialogTreeData:{
                    id: '',
                    roleId: '',
                    targetIds: [],
                    originalIds: [],//默认选中的key
                }
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
            handleSelectionChange(val) {
                this.multipleSelection = val;
            },
            funTreeWin(index, row) {
                this.dialogTreeData = {};
                this.dialogTreeData.id = row.id;
                this.dialogTreeData.roleId = row.id;
                this.dialogTreeVisible = true;
                this.$axios.post(this.funTreeUrl,this.dialogTreeData).then(res => {
                    if(res.data.code === "0"){
                        this.dataParse = JSON.parse(JSON.stringify(res.data.data.list));
                        this.dialogTreeData.originalIds = res.data.data.originalIds;
                        this.$refs.funTree.setCheckedKeys(this.dialogTreeData.originalIds, true);
                    }else{
                        // this.messageShow.error = result.data.msg;
                        return false;
                    }
                });
            },
            //绑定权限
            bindingFun(){
                this.dialogTreeData.targetIds = [];
                let checkedArray = this.$refs.funTree.getCheckedKeys();
                let halfCheckedArray = this.$refs.funTree.getHalfCheckedKeys();
                this.dialogTreeData.targetIds.push.apply(this.dialogTreeData.targetIds, checkedArray);
                this.dialogTreeData.targetIds.push.apply(this.dialogTreeData.targetIds, halfCheckedArray);
                this.$axios.post(this.bindingFunUrl, this.dialogTreeData).then(res => {
                    if(res.data.code === "0"){
                        this.msgSuccess();
                    }
                });
            },
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        // this.$router.push('/readme');
                    } else {
                        return false;
                    }
                });
                this.$axios.post(this.roleAddUrl, this.dialogData).then(result =>{
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
    margin-top: 10px
}
.handle-select{
    width: 120px;
}
.handle-input{
    width: 300px;
    display: inline-block;
}
</style>
