<template>
    <div class="container">
        <div class="block">
            <p>银联中国</p>
            <el-tree :data="dataParse" node-key="id" default-expand-all :expand-on-click-node="false">
                <span class="custom-tree-node" slot-scope="{ node, data }">
                    <span>{{ node.label }}</span>
                    <span>
                        <el-button type="text" size="mini" @click="() => append(data)">新增</el-button>
                        <el-button type="text" size="mini" @click="() => remove(node, data)">删除</el-button>
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
                    <el-input v-model="orgData.name" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="组织编号" prop="code">
                    <el-input placeholder="请输入内容" v-model="orgData.code">
                        <template slot="prepend">{{orgData.parentCode}}</template>
                    </el-input>
                </el-form-item>
                <el-form-item label="上级组织">
                    <el-input v-model="orgData.parentName" auto-complete="off" disabled></el-input>
                </el-form-item>
                <el-form-item label="备注">
                    <el-input type="textarea" v-model="orgData.remarks" auto-complete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="submitForm('orgData')">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>
<script>
    export default {
        data: function() {
            // const data = [{
            //     id: 1,
            //     label: '银联上海(YLSH)',
            //     parent:"01",
            //     children: [{
            //         id: 4,
            //         label: '银联浦东(YLSH01)',
            //         parent:"0101",
            //         children: [{
            //             id: 9,
            //             label: '银联浦东张江(YLSH0101)',
            //             parent:"010101",
            //         }, {
            //             id: 10,
            //             label: '银联浦东唐镇(YLSH0102)',
            //             parent:"010102",
            //         }]
            //     }]
            // }, {
            //     id: 2,
            //     label: '银联北京',
            //     parent:"02",
            //     children: [{
            //         id: 5,
            //         label: '银联海淀',
            //         parent:"0201"
            //     }, {
            //         id: 6,
            //         label: '银联房山',
            //         parent:"0202"
            //     }]
            // }, {
            //     id: 3,
            //     label: '银联深圳',
            //     parent:"03",
            //     children: [{
            //         id: 7,
            //         label: '银联福田',
            //         parent:"0301"
            //     }, {
            //         id: 8,
            //         label: '银联罗湖',
            //         parent:"0302"
            //     }]
            // }];
            return {
                addRootBtn:false,
                // dataParse: JSON.parse(JSON.stringify(data)),
                dataParse: [],
                dialogFormVisible: false,
                orgData: {
                    id: '',
                    name: '',
                    parentCode: '',
                    parentName: '',
                    remarks: '',
                    code: ''
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
                }
            }
        },
        mounted: function () {
            //获取初始化tree节点
            this.$axios.post("/project-web/org/getOrgTree", this.treeReqForm).then(result =>{

                if(result.data.code === "0"){

                //无节点，展示新增根节点按钮
                }else if(result.data.code === "org_001"){
                    this.addRootBtn = true;
                }else{
                    this.messageShow.error = result.data.msg;
                    return false;
                }
            });
        },
        methods: {
            addRoot() {
                this.orgData = {};
                this.dialogFormVisible = true;
            },
            append(data) {
                this.orgData = {};
                //设置dialog信息
                this.orgData.parentCode = data.parent;
                this.orgData.parentName = data.label;
                this.dialogFormVisible = true;
                // const newChild = { id: id++, label: 'testtest', children: [] };
                // if (!data.children) {
                //     this.$set(data, 'children', []);
                // }
                // data.children.push(newChild);
            },
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        // this.$router.push('/readme');
                    } else {
                        return false;
                    }
                });
                // this.$axios.post("/project-web/login/loginAjax", this.ruleForm).then(result =>{
                //     console.info(result);
                //     if(result.data.code === "0"){
                //         this.$router.push('/charts');
                //     }else{
                //         this.messageShow.error = result.data.msg;
                //         return false;
                //     }
                // });
            },
            remove(node, data) {
                const parent = node.parent;
                const children = parent.data.children || parent.data;
                const index = children.findIndex(d => d.id === data.id);
                children.splice(index, 1);
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


