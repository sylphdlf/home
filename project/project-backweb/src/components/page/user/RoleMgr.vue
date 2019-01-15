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
                <el-table-column prop="orgName" label="所属组织"></el-table-column>
                <el-table-column prop="createTime" label="创建时间">
                    <template slot-scope="scope">
                        {{scope.row.createTime | dateTimeFilter}}
                    </template>
                </el-table-column>
                <el-table-column prop="remarks" label="备注"></el-table-column>
                <el-table-column label="操作" width="175">
                    <template slot-scope="scope">
                        <!--<el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>-->
                        <el-button type="primary" size="small" @click="funTreeWin(scope.$index, scope.row)">绑定权限</el-button>
                        <el-button type="warning" size="small" @click="del(scope.row)">删除</el-button>
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
                <el-form-item label="所属机构" prop="org">
                    <el-input placeholder="请选择组织机构" v-model="dialogData.orgName" disabled>
                        <el-button slot="append" icon="el-icon-search" @click="openOrgTreeWin"></el-button>
                    </el-input>
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
        <el-dialog title="绑定组织" :visible.sync="dialogOrgTreeVisible" width="25%">
            <div class="block">
                <el-tree :data="dataOrgParse" show-checkbox node-key="id" ref="orgTree"
                         :expand-on-click-node="false"
                         :check-strictly="true"
                         :highlight-current="true"
                         :load="lazyNode" @check-change="checkChange" @node-click="nodeClick"
                         lazy>
                <span class="custom-tree-node" slot-scope="{ node, data }">
                    <span>{{ node.label }}</span>
                </span>
                </el-tree>
                <div class="handle-box" align="center">
                    <el-button type="primary" @click="bindingOrg">确认</el-button>
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
                orgTreeLazyUrl: this.$projectUrl + '/org/getOrgTreeLazy',
                urlDel: this.$projectUrl + '/role/del',
                dataParse: [],//权限树数据
                dataOrgParse:[],//组织机构dialog中的数据
                tableData: [],
                multipleSelection: [],
                select_word: '',
                is_search: false,
                dataTotal:1,
                dialogFormVisible: false,
                dialogTreeVisible: false,
                dialogOrgTreeVisible: false,
                searchForm: {
                    name: '',
                    code: '',
                },
                dialogData: {
                    id: '',
                    name: '',
                    code: '',
                    orgId: '',
                    orgName: ''
                },
                dialogRules: {
                    name: [
                        { required: true, message: '请输入角色名称', trigger: 'blur' }
                    ],
                    code: [
                        { required: true, message: '请输入角色编号', trigger: 'blur' }
                    ],
                },
                dialogTreeData:{
                    id: '',
                    roleId: '',
                    targetIds: [],
                    originalIds: [],//默认选中的key
                },
                orgTreeOptions:[],//组织机构树
                treeReqForm: {
                    id: "",
                    code: "",
                },
                editCheckedId: '',
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
                //加载组织机构一级树
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
            openOrgTreeWin(){
                this.dialogOrgTreeVisible = true;
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
            del(row){
                this.$confirm('此操作将永久删除此角色，可能会导致某些用户无法访问某些菜单, 是否继续?', '提示', {
                    cancelButtonText: '取消',
                    confirmButtonText: '确定',
                    type: 'warning'
                }).then(() => {
                    this.$axios.post(this.urlDel, {id:row.id}).then((res) => {
                        if(res.data.code === "0"){
                            this.getData();
                            this.msgSuccess();
                        }
                    });
                }).catch(() => {
                    this.msgWarn("取消");
                });
            },
            //懒加载树节点
            lazyNode(node, resolve){
                if(node.data.length === 0){
                    this.treeReqForm.parentCode = '0';
                }else{
                    this.treeReqForm.parentCode = node.data.code;
                }
                this.$axios.post(this.orgTreeLazyUrl, this.treeReqForm).then(result =>{
                    if(result.data.code === "0"){
                        resolve(result.data.data);
                        this.addRootBtn = false;
                    }else if(result.data.code === "org_001"){//无节点，展示新增根节点按钮
                        this.addRootBtn = true;
                    }else{
                        this.addRootBtn = false;
                        // this.messageShow.error = result.data.msg;
                        return false;
                    }
                });
            },
            bindingOrg(){
                let checkedNode = this.$refs.orgTree.getCheckedNodes();
                this.dialogData.orgId = checkedNode[0].id;
                this.dialogData.orgName = checkedNode[0].label;
                this.dialogOrgTreeVisible = false;
            },
            checkChange(item, node, self){
                if(node === true){
                    this.editCheckedId = item.id;
                    this.$refs.orgTree.setCheckedKeys([item.id])
                }else {
                    if(this.editCheckedId === item.id){
                        this.$refs.orgTree.setCheckedKeys([item.id])
                    }
                }
            },
            nodeClick(item, node, self){
                this.editCheckedId = item.id;
                this.$refs.orgTree.setCheckedKeys([item.id]);
            }
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
