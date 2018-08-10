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
                <el-input v-model="select_word" placeholder="权限名称" class="handle-input mr10" @keyup.enter.native="search"></el-input>
                <el-select v-model="select_level" placeholder="菜单层级" class="handle-select">
                    <el-option label="一级" value="1"></el-option>
                    <el-option label="二级" value="2"></el-option>
                </el-select>
                <el-button type="primary" icon="search" @click="search">搜索</el-button>
                <el-button type="primary" icon="search" @click="handleSort()">一级菜单排序</el-button>
            </div>
            <el-table :data="tableData" border style="width: 100%" highlight-current-row>
                <el-table-column type="selection" width="35"></el-table-column>
                <el-table-column prop="name" label="菜单名称"></el-table-column>
                <el-table-column prop="code" label="菜单编号"></el-table-column>
                <el-table-column prop="level" label="菜单层级"></el-table-column>
                <el-table-column prop="remarks" label="备注"></el-table-column>
                <el-table-column prop="createTime" label="创建时间" :formatter="dateFormat"></el-table-column>
                <el-table-column label="操作" width="200">
                    <template slot-scope="scope" v-if="scope.row.level===2">
                        <el-button type="primary" size="small" @click="addFunction(scope.$index, scope.row)">新增权限</el-button>
                        <el-button size="small" @click="handleEdit(scope.$index, scope.row)">查看权限</el-button>
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
                    <el-input v-model="dialogData.name" @keyup.enter.native="submitForm('dialogData')"></el-input>
                </el-form-item>
                <el-form-item label="权限编号" prop="code">
                    <el-input placeholder="请输入内容" v-model="dialogData.code">
                        <template slot="prepend">{{dialogData.parentCode}}</template>
                    </el-input>
                </el-form-item>
                <el-form-item label="权限类型" prop="type">
                    <el-select v-model="dialogData.type" placeholder="请选择" @change="dialogDataTypeChange">
                        <el-option label="菜单" value="1"></el-option>
                        <el-option label="按钮" value="2"></el-option>
                        <el-option label="资源" value="3"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="菜单层级" prop="level" v-if="dialogData.type=='1'">
                    <el-select v-model="dialogData.level" placeholder="请选择">
                        <el-option label="一级菜单" value="1"></el-option>
                        <el-option label="二级菜单" value="2"></el-option>
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
                staticMenuUrl: this.$projectUrl + "/fun/getDefaultMenu",
                staticSourceUrl: this.$projectUrl + "/fun/getDefaultSource",
                tableData: [],
                multipleSelection: [],
                select_word: '',
                select_level: '',
                del_list: [],
                is_search: false,
                dataTotal:1,
                dialogFormVisible: false,
                dialogSortVisible: false,
                searchForm: {
                    name: '',
                    code: '',
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
            addFunction(index, row){
                console.info(row);
                this.dialogData = {};
                this.dialogFormVisible = true;
                this.dialogData.parentCode = row.code;
            },
            handleSort(){
                this.dialogSortVisible = true;
            },
            //日期格式化
            dateFormat:function(row, column) {
                let date = row.createTime;
                if (date === undefined) {
                    return "";
                }
                return this.formatDate(date);
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
                    console.info(this.defaults);
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
                this.$axios.post("/fun/add", this.dialogData).then(result =>{
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
                console.info(value);
                if(value === "1"){
                    this.getDefaultMenu();
                }else if(value === "3" && this.defaultSources.length === 0){
                    this.getDefaultSource();
                }

            },
            handleChange(value) {
                console.log(value);
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
