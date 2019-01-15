<template>
    <div class="table">
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-tickets"></i> 用户管理</el-breadcrumb-item>
                <el-breadcrumb-item>权限管理</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-input v-model="searchForm.name" placeholder="菜单名称" class="handle-input mr10" @keyup.enter.native="search"></el-input>
                <el-select v-model="searchForm.level" placeholder="菜单层级" class="handle-select">
                    <el-option label="全部" value=""></el-option>
                    <el-option label="一级" value="1"></el-option>
                    <el-option label="二级" value="2"></el-option>
                </el-select>
                <el-button type="primary" icon="search" @click="search">搜索</el-button>
                <!--<el-button type="primary" icon="search" @click="addMenuLevel1">新增一级菜单</el-button>-->
                <!--<el-button type="primary" icon="search" @click="handleSort()">一级菜单排序</el-button>-->
            </div>
            <el-table :data="tableData" border style="width: 100%" highlight-current-row>
                <el-table-column type="selection" width="35"></el-table-column>
                <el-table-column prop="name" label="菜单名称"></el-table-column>
                <el-table-column prop="code" label="菜单编号"></el-table-column>
                <el-table-column prop="level" label="菜单层级">
                    <template slot-scope="scope">
                        <span v-if="scope.row.level===1">一级菜单</span>
                        <span v-if="scope.row.level===2">二级菜单</span>
                    </template>
                </el-table-column>
                <el-table-column prop="remarks" label="备注"></el-table-column>
                <el-table-column prop="createTime" label="创建时间">
                    <template slot-scope="scope">
                        {{this.createTime | dateTimeFilter}}
                    </template>
                </el-table-column>
                <el-table-column label="操作" width="180">
                    <template slot-scope="scope">
                        <!--<el-button type="primary" size="small" @click="addFunction(scope.$index, scope.row)">新增权限</el-button>-->
                        <!--<el-button v-if="scope.row.level===1" type="primary" size="small" @click="showMenuListWin(scope.row)">绑定菜单</el-button>-->
                        <el-button v-if="scope.row.level===2" type="primary" size="small" @click="functionList(scope.$index, scope.row)">查看权限</el-button>
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
        <el-dialog title="权限信息" :visible.sync="dialogFormVisible" width="25%">
            <el-form :model="dialogData" label-width="80px" :rules="dialogRules" ref="dialogData">
                <el-form-item label="权限名称" prop="name">
                    <el-input v-model="dialogData.name" @keyup.enter.native="submitForm('dialogData')" disabled></el-input>
                </el-form-item>
                <el-form-item label="权限编号">
                    <el-input placeholder="" v-model="dialogData.code" disabled>
                        <template slot="prepend">{{dialogData.parentCode}}</template>
                    </el-input>
                </el-form-item>
                <el-form-item label="权限类型" prop="type">
                    <el-select v-model="dialogData.type" placeholder="请选择" @change="dialogDataTypeChange">
                        <!--<el-option label="菜单" value="1"></el-option>-->
                        <!--<el-option label="按钮" value="2"></el-option>-->
                        <el-option label="资源" value="3"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="访问路径" prop="path" v-if="dialogData.type=='1'||dialogData.type=='3'">
                    <div class="block">
                        <el-cascader :options="defaults" v-model="selectedOptions" @change="handleChange" :show-all-levels="false"></el-cascader>
                    </div>
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
        <el-dialog title="新增菜单" :visible.sync="dialogMenuVisible" width="25%">
            <el-form :model="dialogData" label-width="80px" :rules="dialogRules" ref="dialogData">
                <el-form-item label="菜单名称" prop="name">
                    <el-input v-model="dialogData.name" @keyup.enter.native="addMenuSubmit('dialogData')"></el-input>
                </el-form-item>
                <el-form-item label="备注">
                    <el-input type="textarea" v-model="dialogData.remarks"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="addMenuSubmit('dialogData')">确 定</el-button>
            </div>
        </el-dialog>
        <el-dialog title="排序" :visible.sync="dialogSortVisible" width="25%">
            <el-form :model="sortData" label-width="80px" :rules="dialogRules" ref="dialogData">
                <div class="drag-box">
                    <div class="drag-box-item">
                        <!--<div class="item-title">排序</div>-->
                        <draggable v-model="todo" :options="dragOptions">
                            <transition-group tag="div" id="todo" class="item-ul">
                                <div v-for="(item,index) in todo" class="drag-list" :key="index">
                                    {{item.content}}
                                </div>
                            </transition-group>
                        </draggable>
                    </div>
                </div>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogSortVisible = false">取 消</el-button>
                <el-button type="primary" @click="submitForm('sortData')">确 定</el-button>
            </div>
        </el-dialog>
        <!-- Table -->
        <el-dialog title="菜单列表" :visible.sync="dialogMenuListVisible">
            <el-table :data="dialogMenuListData" @selection-change="handleSelectionChange" ref="multipleTable">
                <el-table-column type="selection" property="checked" width="55"></el-table-column>
                <el-table-column property="name" label="名称" width="150"></el-table-column>
                <el-table-column property="level" label="类型" width="200">
                    <template slot-scope="scope">
                        <span v-if="scope.row.level===2">二级菜单</span>
                    </template>
                </el-table-column>
                <el-table-column property="path" label="地址"></el-table-column>
            </el-table>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogMenuListVisible = false">取 消</el-button>
                <el-button type="primary" @click="dialogMenuSubmit">确 定</el-button>
            </div>
        </el-dialog>
        <!-- Table -->
        <el-dialog title="资源列表" :visible.sync="dialogTableVisible">
            <el-table :data="gridData">
                <el-table-column property="name" label="名称" width="150"></el-table-column>
                <el-table-column property="type" label="类型" width="200">
                    <template slot-scope="scope">
                        <span v-if="scope.row.type===3">资源</span>
                    </template>
                </el-table-column>
                <el-table-column property="path" label="地址"></el-table-column>
                <!--<el-table-column label="操作" width="200">-->
                    <!--<template slot-scope="scope">-->
                        <!--<el-button size="small" type="warning" @click="functionList(scope.$index, scope.row)">删除</el-button>-->
                    <!--</template>-->
                <!--</el-table-column>-->
            </el-table>
        </el-dialog>
    </div>
</template>
<script>
    import draggable from 'vuedraggable'
    export default {
        components:{
            draggable
        },
        data() {
            return {
                url: this.$projectUrl + '/fun/queryMenuByParams',
                funUrl: this.$projectUrl + '/fun/queryFunByParams',
                addUrl: this.$projectUrl + '/fun/add',
                staticMenuUrl: this.$projectUrl + "/fun/getMenu",
                staticSourceUrl: this.$projectUrl + "/fun/getDefaultSource",
                addMenuUrl: this.$projectUrl + "/fun/addMenu",
                bindingMenuUrl: this.$projectUrl + "/fun/bindingMenuByUser",
                tableData: [],
                dialogMenuListData: [],
                multipleSelection: [],
                dataTotal:1,
                dialogFormVisible: false,//新增权限窗口
                dialogMenuVisible: false,//新增菜单窗口
                dialogSortVisible: false,//排序窗口
                dialogTableVisible: false,//权限列表窗口
                dialogMenuListVisible: false,//菜单列表
                searchForm: {
                    name: '',
                    level:'',
                },
                dialogData: {
                    id: '',
                    name: '',
                    code: '',
                    parentCode: '',
                    type: '',
                    path: '',
                },
                dialogRules: {
                    name: [
                        { required: true, message: '请输入权限名称', trigger: 'blur' }
                    ],
                    code: [
                        { required: true, message: '请输入权限编号', trigger: 'blur' }
                    ],
                    type: [
                        { required: true, message: '请选择权限类型', trigger: 'blur' }
                    ],
                    path: [
                        { required: true, message: '请输入请选择菜单', trigger: 'blur' }
                    ]
                },
                sortData:{

                },
                dragOptions:{
                    animation: 120,
                    scroll: true,
                    group: 'sortlist',
                    ghostClass: 'ghost-style'
                },
                todo: [
                    {
                        content: '开发图表组件'
                    },
                    {
                        content: '开发拖拽组件'
                    },
                    {
                        content: '开发权限测试组件'
                    }
                ],
                defaultMenus:[],
                defaultSources:[],
                defaults:[],
                selectedOptions: [],
                gridData: [],
                dialogMenuForm:{
                    id: '',
                    code: '',
                    parentCode: '',
                    children: [],//用户选择后的菜单
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
            addFunction(index, row){
                this.dialogData = {};
                this.selectedOptions = [];
                this.dialogFormVisible = true;
                this.dialogData.parentCode = row.code;
            },
            //获取子权限列表
            functionList(index, row){
                this.dialogTableVisible = true;
                console.info(row.code);
                this.$axios.post(this.funUrl,row).then((res) => {
                    if(res.data.code === "0"){
                        console.info(res.data.data);
                        this.gridData = res.data.data;
                    }else{
                        return false;
                    }
                });
            },
            handleSort(){
                this.dialogSortVisible = true;
            },
            handleEdit(index, row) {
                // this.$message('编辑第'+(index+1)+'行');
                this.dialogFormVisible = true;
                this.dialogData = row;
            },
            handleDelete(index, row) {
                this.$message.error('删除第'+(index+1)+'行');
            },
            getDefaultMenu(){
                this.$axios.get(this.staticMenuUrl).then((res) => {
                    this.defaultMenus = JSON.parse(res.data.data);
                    this.defaults = this.defaultMenus;
                })
            },
            getDefaultSource(){
                this.$axios.get(this.staticSourceUrl).then((res) => {
                    this.defaultSources = JSON.parse(res.data.data);
                    this.defaults = this.defaultSources;
                })
            },
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        // this.$router.push('/readme');
                    } else {
                        return false;
                    }
                });
                this.$axios.post(this.addUrl, this.dialogData).then(result =>{
                    if(result.data.code === "0"){
                        this.dialogFormVisible = false;
                        this.getData();
                        this.msgSuccess();
                    }else{
                        // this.messageShow.error = result.data.msg;
                        return false;
                    }
                });
            },
            dialogDataTypeChange(value){
                if(value === "1"){
                    this.getDefaultMenu();
                }else if(value === "3" && this.defaultSources.length === 0){
                    this.getDefaultSource();
                }

            },
            handleChange(obj){
                this.dialogData.path = obj[ obj.length- 1] + "";
                this.dialogData.name = "";
                let label = "";
                if(obj.length > 1){
                    this.defaults.forEach(function(value,index,array){
                        if(value.value === obj[0]){
                            let children = value.children;
                            children.forEach(function(childValue,index,array){
                                if(childValue.value === obj[ obj.length- 1]){
                                    label = childValue.label;
                                }
                            });
                        }
                    });
                }
                this.dialogData.name = label;
            },
            addMenuLevel1(){
                this.dialogMenuVisible = true;
            },
            addMenuSubmit(formName){
                this.$axios.post(this.addMenuUrl, this.dialogData).then(result =>{
                    if(result.data.code === "0"){
                        this.dialogMenuVisible = false;
                        this.getData();
                        this.msgSuccess();
                    }else{
                        // this.messageShow.error = result.data.msg;
                        return false;
                    }
                });
            },
            showMenuListWin(row){
                this.dialogMenuForm.id = row.id;
                this.dialogMenuForm.parentCode = row.code;
                this.dialogMenuListVisible = true;
                this.$axios.post(this.staticMenuUrl, this.dialogMenuForm).then((result) => {
                    if(result.data.code === "0"){
                        this.dialogMenuListData = result.data.data;
                        this.dialogMenuListData.forEach(row => {
                            if(row.checked === true){
                                this.selectMenuRow(row);
                            }
                        });
                    }
                })
            },
            selectMenuRow(row) {
                setTimeout(()=>{
                    this.$refs.multipleTable.toggleRowSelection(row);
                },10)
            },
            handleSelectionChange(val){
                this.multipleSelection = val;
            },
            dialogMenuSubmit(){
                //初始化
                this.dialogMenuForm.children = [];
                this.multipleSelection.forEach(row => {
                    row.checked = true;
                    this.dialogMenuForm.children.push(row);
                });
                this.$axios.post(this.bindingMenuUrl, this.dialogMenuForm).then(res => {
                    if(res.data.code === "0"){
                        this.msgSuccess();
                        this.dialogMenuListVisible = false;
                    }
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
    width: 200px;
    display: inline-block;
}

.drag-box{
    display: flex;
    user-select: none;
}
.drag-box-item {
    flex: 1;
    max-width: 330px;
    min-width: 300px;
    background-color: #eff1f5;
    margin-right: 16px;
    border-radius: 6px;
    border: 1px #e1e4e8 solid;
}
.item-title{
    padding: 8px 8px 8px 12px;
    font-size: 14px;
    line-height: 1.5;
    color: #24292e;
    font-weight: 600;
}
.item-ul{
    padding: 0 8px 8px;
    height: 500px;
    overflow-y: scroll;
}
.item-ul::-webkit-scrollbar{
    width: 0;
}
.drag-list {
    border: 1px #e1e4e8 solid;
    padding: 10px;
    margin: 5px 0 10px;
    list-style: none;
    background-color: #fff;
    border-radius: 6px;
    cursor: pointer;
    -webkit-transition: border .3s ease-in;
    transition: border .3s ease-in;
}
.drag-list:hover {
    border: 1px solid #20a0ff;
}
.drag-title {
    font-weight: 400;
    line-height: 25px;
    margin: 10px 0;
    font-size: 22px;
    color: #1f2f3d;
}
.ghost-style{
    display: block;
    color: transparent;
    border-style: dashed
}
</style>
