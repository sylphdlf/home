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
                <el-input v-model="select_word" placeholder="用户名/手机号码" class="handle-input mr10" @keyup.enter.native="search"></el-input>
                <el-button type="primary" icon="search" @click="search">搜索</el-button>
                <el-button type="primary" icon="search" @click="add">新增</el-button>
            </div>
            <el-table :data="tableData" border style="width: 100%">
                <el-table-column type="selection" width="35"></el-table-column>
                <el-table-column prop="username" label="用户名"></el-table-column>
                <el-table-column prop="realName" label="真实姓名"></el-table-column>
                <el-table-column prop="mobile" label="手机"></el-table-column>
                <el-table-column prop="telephone" label="座机"></el-table-column>
                <el-table-column prop="orgCode" label="组织机构编码"></el-table-column>
                <el-table-column label="创建时间">
                    <template slot-scope="scope">
                        {{this.createTime | dateTimeFilter}}
                    </template>
                </el-table-column>
                <el-table-column label="操作" width="270">
                    <template slot-scope="scope">
                        <el-button size="small" type="primary" @click="bindingOrgWin(scope.row)">关联组织</el-button>
                        <el-button size="small" type="primary" @click="bindingRoleWin(scope.row)">绑定角色</el-button>
                        <el-button size="small" type="warning" @click="del(scope.row)">删除</el-button>
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
            <el-form :model="userFormData" label-width="80px">
                <el-form-item label="用户名">
                    <el-input v-model="userFormData.username" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="真实姓名">
                    <el-input v-model="userFormData.realName"></el-input>
                </el-form-item>
                <el-form-item label="手机">
                    <el-input v-model="userFormData.mobile"></el-input>
                </el-form-item>
                <el-form-item label="座机">
                    <el-input v-model="userFormData.telephone"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
            </div>
        </el-dialog>
        <el-dialog title="角色列表" :visible.sync="dialogTableVisible">
            <el-table :data="tableDataRole" @selection-change="handleSelectionChange" ref="multipleTable">
                <el-table-column type="selection" width="35" property="checked"></el-table-column>
                <el-table-column property="name" label="名称" width="200"></el-table-column>
                <el-table-column property="code" label="编号" width="200"></el-table-column>
                <el-table-column prop="createTime" label="创建时间">
                    <template slot-scope="scope">
                        {{scope.row.createTime | dateTimeFilter}}
                    </template>
                </el-table-column>
                <el-table-column property="remarks" label="备注"></el-table-column>
            </el-table>
            <div class="pagination">
                <el-pagination
                    @current-change ="handleCurrentChange"
                    layout="prev, pager, next, total"
                    :total="dataTotalRole">
                </el-pagination>
            </div>
            <div style="margin-top: 10px" align="center">
                <el-button type="primary" @click="bindingRole">确认勾选</el-button>
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
            return {
                url: this.$projectUrl + '/user/queryListByParams',
                roleUrl: this.$projectUrl + '/user/getRolePageByUser',
                bindingRoleUrl: this.$projectUrl + '/user/bindingRole',
                urlDel: this.$projectUrl + '/user/del',
                orgTreeLazyUrl: this.$projectUrl + '/org/getOrgTreeLazy',
                urlBindingOrg: this.$projectUrl + '/user/bindingOrg',
                tableData: [],
                tableDataRole:[],
                dataOrgParse:[],//组织机构dialog中的数据
                select_word: '',
                dataTotal:1,
                dataTotalRole:1,
                dialogFormVisible: false,
                dialogTableVisible: false,//角色展示窗口
                dialogOrgTreeVisible: false,//组织机构树
                searchForm: {
                    id:'',
                    username: '',
                    mobile: '',
                    realName: '',
                    pageNum: 1
                },
                userFormData: {
                    id: '',
                    username: '',
                    realName: '',
                    mobile: '',
                    telephone: '',
                    org_code: ''
                },
                tableCheckedOriginalData: [],//角色列表(初始已选择)
                tableCheckedChangedData: [],//角色列表(更改后已选择)
                orgTreeOptions:[],//组织机构树
                treeReqForm: {
                    id: "",
                    code: "",
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
            getData(){
                this.$axios.post(this.url, this.searchForm).then((res) => {
                    if(res.data.code === "0" && res.data.total !== 0){
                        this.tableData = res.data.data.list;
                        this.dataTotal = res.data.data.total;
                    }
                })
            },
            search(){
                //清空数据
                if(this.select_word === ""){
                    this.searchForm.mobile = "";
                    this.searchForm.username = "";
                }
                //正则判断是否是手机
                let rex = /^1[3456789]\d{9}$/;
                //手机
                if (rex.test(this.select_word)) {
                    this.searchForm.mobile = this.select_word;
                    this.searchForm.username = "";
                }else{
                    this.searchForm.mobile  = "";
                    this.searchForm.username = this.select_word;
                }
                this.getData();
            },
            add(){
                this.dialogFormVisible = true;
            },
            bindingRoleWin(data){
                this.dialogTableVisible = true;
                this.searchForm.id = data.id;
                this.getRoleData();
            },
            handleSelectionChange(val){
                this.multipleSelection = val;
            },
            getRoleData(){
                this.tableCheckedOriginalData = [];
                this.$axios.post(this.roleUrl, this.searchForm).then((res) => {
                    if(res.data.code === "0" && res.data.total !== 0){
                        this.tableDataRole = res.data.data.list;
                        this.dataTotalRole = res.data.data.total;

                        this.tableDataRole.forEach(row => {
                            if(row.checked === true){
                                this.tableCheckedOriginalData.push(row.id);
                                this.selectRow(row);
                            }
                        });
                    }
                })
            },
            //绑定角色
            bindingRole(){
                //初始化
                this.tableCheckedChangedData = [];
                // this.tableCheckedData;
                // this.this.multipleSelection;
                this.multipleSelection.forEach(row => {
                    this.tableCheckedChangedData.push(row.id);
                });
                this.$axios.post(this.bindingRoleUrl, {id:this.searchForm.id,
                    originalIds:this.tableCheckedOriginalData,
                    changedIds:this.tableCheckedChangedData}).then(res => {
                    if(res.data.code === "0"){
                        this.msgSuccess();
                        this.getRoleData();
                    }
                });
            },
            selectRow(row) {
                setTimeout(()=>{
                    this.$refs.multipleTable.toggleRowSelection(row);
                },10)
            },
            del(row){
                this.$confirm('此操作将永久删除此用户，且无法恢复, 是否继续?', '提示', {
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
                    }else{
                        this.addRootBtn = false;
                        // this.messageShow.error = result.data.msg;
                        return false;
                    }
                });
            },
            bindingOrgWin(row){
                this.userFormData.id = row.id;
                this.userFormData.orgCode = row.orgCode;
                this.dialogOrgTreeVisible = true;
            },
            bindingOrg(){
                if(this.userFormData.orgCode !== "" && this.userFormData.orgCode !== undefined){
                    this.$confirm('此操作将解绑当前组织，并且用户之前添加的数据可能无法查看，是否继续?', '提示', {
                        cancelButtonText: '取消',
                        confirmButtonText: '确定',
                        type: 'warning'
                    }).then(() => {
                        let checkedNode = this.$refs.orgTree.getCheckedNodes();
                        this.userFormData.orgCode = checkedNode[0].code;
                        this.$axios.post(this.urlBindingOrg, this.userFormData).then(result =>{
                            if(result.data.code === "0"){
                                this.msgSuccess();
                            }else{
                                this.msgFail();
                            }
                        });
                    }).catch(() => {
                        this.msgWarn("取消");
                    });
                }else{
                    let checkedNode = this.$refs.orgTree.getCheckedNodes();
                    this.userFormData.orgCode = checkedNode[0].code;
                    this.$axios.post(this.urlBindingOrg, this.userFormData).then(result =>{
                        if(result.data.code === "0"){
                            this.msgSuccess();
                        }else{
                            this.msgFail();
                        }
                    });
                }
                this.dialogOrgTreeVisible = false;
                this.getData();
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
}
.handle-select{
    width: 120px;
}
.handle-input{
    width: 300px;
    display: inline-block;
}
</style>
