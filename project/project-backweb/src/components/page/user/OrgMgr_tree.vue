<template>
    <div class="container">
        <div class="block">
            <el-tree :data="dataParse" node-key="id" :expand-on-click-node="false" :load="lazyNode" lazy>
                <span class="custom-tree-node" slot-scope="{ node, data }">
                    <span>{{ node.label }}</span>
                    <span>
                        <el-button type="text" size="mini" @click="() => append(data)">新增</el-button>
                        <el-button type="text" size="mini" @click="() => bindingRoleWin(data)">绑定/解绑角色</el-button>
                        <el-button type="text" size="mini" @click="() => remove(node, data)">删除(todo)</el-button>
                    </span>
                </span>
            </el-tree>
            <div class="handle-box" align="center">
                <el-button type="primary" icon="search" v-show="addRootBtn" @click="addRoot">新增根节点</el-button>
            </div>
        </div>
        <el-dialog title="组织信息" :visible.sync="dialogFormVisible" width="25%">
            <el-form :model="orgData" label-width="80px" :rules="orgDataRules" ref="orgData">
                <el-form-item label="组织名称" prop="name">
                    <el-input v-model="orgData.name"></el-input>
                </el-form-item>
                <el-form-item label="组织编号" prop="code">
                    <el-input placeholder="请输入内容" v-model="orgData.code">
                        <template slot="prepend">{{orgData.parentCode}}</template>
                    </el-input>
                </el-form-item>
                <el-form-item label="上级组织">
                    <el-input v-model="orgData.parentName" disabled></el-input>
                </el-form-item>
                <el-form-item label="备注">
                    <el-input type="textarea" v-model="orgData.remarks"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="submitForm('orgData')">确 定</el-button>
            </div>
        </el-dialog>
        <!-- Table -->
        <el-dialog title="角色列表" :visible.sync="dialogTableVisible">
            <el-table :data="tableData" @selection-change="handleSelectionChange" ref="multipleTable">
                <el-table-column type="selection" width="35" property="checked"></el-table-column>
                <el-table-column property="name" label="名称" width="200"></el-table-column>
                <el-table-column property="code" label="编号" width="200"></el-table-column>
                <el-table-column property="createTime" label="创建时间" width="200" :formatter="dateFormat"></el-table-column>
                <el-table-column property="remarks" label="备注"></el-table-column>
            </el-table>
            <div class="pagination">
                <el-pagination
                    @current-change ="handleCurrentChange"
                    layout="prev, pager, next, total"
                    :total="dataTotal">
                </el-pagination>
            </div>
            <div style="margin-top: 10px" align="center">
                <el-button type="primary" @click="bindingRole">确认勾选</el-button>
            </div>
        </el-dialog>
    </div>
</template>
<script>
    export default {
        data: function() {
            const data = [];
            return {
                roleUrl: this.$projectUrl + '/org/getRolePageByOrg',
                addNodeUrl: this.$projectUrl + '/org/addOrgNode',
                bindingRoleUrl: this.$projectUrl + '/org/bindingRole',
                orgTreeLazyUrl: this.$projectUrl + '/org/getOrgTreeLazy',
                addRootBtn:false,
                dataParse: JSON.parse(JSON.stringify(data)),
                dialogFormVisible: false,//新增窗口
                dialogTableVisible: false,//角色展示窗口
                orgData: {
                    id: '',
                    name: '',
                    parentCode: '',
                    parentName: '',
                    remarks: '',
                    code: '',
                },
                orgDataRules: {
                    name: [
                        { required: true, message: '请输入组织名称', trigger: 'blur' }
                    ],
                    code: [
                        { required: true, message: '请输入组织编号', trigger: 'blur' }
                    ]
                },
                treeReqForm: {
                    id: "",
                    code: "",
                },
                curNodeData:{},
                searchForm:{},
                tableData: [],//角色列表
                tableCheckedOriginalData: [],//角色列表(初始已选择)
                tableCheckedChangedData: [],//角色列表(更改后已选择)
                dataTotal:1,
                multipleSelection: [],//角色选择框
            }
        },
        mounted: function () {
            //获取初始化tree节点
            // this.refreshRoot();
        },
        created() {
            this.searchForm.pageNum = 1;
        },
        methods: {
            refreshRoot() {
                this.$axios.get("/project-web/org/getOrgTreeRoot").then(result =>{
                    if(result.data.code === "0"){
                        this.dataParse = result.data.data;
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
            //新增根节点
            addRoot(data) {
                this.orgData = {};
                this.dialogFormVisible = true;
                this.curNodeData = data;
            },
            //添加节点
            append(data) {
                this.orgData = {};
                //设置dialog信息
                this.orgData.parentCode = data.code;
                this.orgData.parentName = data.name;
                this.dialogFormVisible = true;
                this.curNodeData = data;
            },
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        // this.$router.push('/readme');
                    } else {
                        return false;
                    }
                });
                if(this.orgData.parentCode!=null){
                    this.orgData.code = this.orgData.parentCode + this.orgData.code;
                }
                this.$axios.post(this.addNodeUrl, this.orgData).then(result =>{
                    if(result.data.code === "0"){
                        this.dialogFormVisible = false;
                        if(result.data.data.parentCode === "0"){
                            this.refreshRoot();
                        }else{
                            //处理节点
                            const newChild = { id: result.data.data.id,
                                label: result.data.data.name + "(" + result.data.data.code + ")",
                                code: result.data.data.code,
                                name: result.data.data.name,
                                children: [] };
                            this.data = newChild;
                            if (!this.curNodeData.children) {
                                this.$set(this.curNodeData, 'children', []);
                            }
                            this.curNodeData.children.push(newChild);
                            this.orgData = {};
                        }
                    }else{
                        // this.messageShow.error = result.data.msg;
                        return false;
                    }
                });
            },
            remove(node, data) {
                const parent = node.parent;
                const children = parent.data.children || parent.data;
                const index = children.findIndex(d => d.id === data.id);
                children.splice(index, 1);
            },
            bindingRoleWin(data){
                this.dialogTableVisible = true;
                this.searchForm.id = data.id;
                this.getRoleData();
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
            // 分页导航
            handleCurrentChange(val){
                this.searchForm.pageNum = val;
                this.getRoleData();
            },
            getRoleData(){
                this.tableCheckedOriginalData = [];
                this.$axios.post(this.roleUrl, this.searchForm).then((res) => {
                    if(res.data.code === "0" && res.data.total !== 0){
                        this.tableData = res.data.data.list;
                        this.dataTotal = res.data.data.total;

                        this.tableData.forEach(row => {
                            if(row.checked === true){
                                this.tableCheckedOriginalData.push(row.id);
                                this.selectRow(row);
                            }
                        });
                    }
                })
            },
            handleSelectionChange(val){
                this.multipleSelection = val;
            },
            dateFormat:function(row, column) {
                let date = row.createTime;
                if (date === undefined) {
                    return "";
                }
                return this.$moment(date).format("YYYY-MM-DD HH:mm:ss");
            },
            selectRow(row) {
                setTimeout(()=>{
                    this.$refs.multipleTable.toggleRowSelection(row);
                },10)
            },
        }
    };
</script>
<style>
    .custom-tree-node {
        flex: 1;
        display: flex;
        align-items: center;
        justify-content: space-between;
        font-size: 14px;
        padding-right: 8px;
    }
</style>


