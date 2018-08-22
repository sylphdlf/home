<template>
    <div class="table">
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-tickets"></i> 物流管理</el-breadcrumb-item>
                <el-breadcrumb-item>托运单管理</el-breadcrumb-item>
                <el-breadcrumb-item>新增</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <div class="block" align="center">
                    <el-input placeholder="发货方" class="handle-input"></el-input>
                    <el-input placeholder="发货方" class="handle-input"></el-input>
                    <el-input placeholder="发货方" class="handle-input"></el-input>
                    <el-input placeholder="发货方" class="handle-input"></el-input>
                </div><br>
                <div class="block" align="center">
                    <el-input placeholder="发货方" class="handle-input"></el-input>
                    <el-input placeholder="发货方" class="handle-input"></el-input>
                    <el-input placeholder="发货方" class="handle-input"></el-input>
                    <el-input placeholder="发货方" class="handle-input"></el-input>
                </div><br>
                <div class="block" align="center">
                    <el-input placeholder="发货方" class="handle-input"></el-input>
                    <el-input placeholder="发货方" class="handle-input"></el-input>
                    <el-input placeholder="发货方" class="handle-input"></el-input>
                    <el-input placeholder="发货方" class="handle-input"></el-input>
                </div><br>
                <div class="block" align="center">
                    <el-input placeholder="发货方" class="handle-input"></el-input>
                    <el-input placeholder="发货方" class="handle-input"></el-input>
                    <el-input placeholder="发货方" class="handle-input"></el-input>
                    <el-input placeholder="发货方" class="handle-input"></el-input>
                </div>
            </div>
            <div align="center">
                <el-table :data="tableData" border style="width: 96%">
                    <!--<el-table-column type="selection" width="35"></el-table-column>-->
                    <el-table-column prop="username" label="用户名"></el-table-column>
                    <el-table-column prop="realName" label="真实姓名"></el-table-column>
                    <el-table-column prop="mobile" label="手机"></el-table-column>
                    <el-table-column prop="telephone" label="座机"></el-table-column>
                    <el-table-column prop="createTime" label="创建时间" :formatter="dateFormat"></el-table-column>
                    <el-table-column label="操作" width="100">
                        <template slot-scope="scope">
                            <el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑(todo)</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
            <br>
            <div class="handle-box">
                <div class="block" align="center">
                    <el-input placeholder="发货方" class="handle-input"></el-input>
                    <el-input placeholder="发货方" class="handle-input"></el-input>
                    <el-input placeholder="发货方" class="handle-input"></el-input>
                    <el-input placeholder="发货方" class="handle-input"></el-input>
                </div><br>
                <div class="block" align="center">
                    <el-input placeholder="发货方" class="handle-input"></el-input>
                    <el-input placeholder="发货方" class="handle-input"></el-input>
                    <el-input placeholder="发货方" class="handle-input"></el-input>
                    <el-input placeholder="发货方" class="handle-input"></el-input>
                </div><br>
                <div class="block" align="center">
                    <el-input placeholder="发货方" class="handle-input"></el-input>
                    <el-input placeholder="发货方" class="handle-input"></el-input>
                    <el-input placeholder="发货方" class="handle-input"></el-input>
                    <el-input placeholder="发货方" class="handle-input"></el-input>
                </div><br>
                <div class="block" align="center">
                    <el-input placeholder="发货方" class="handle-input"></el-input>
                    <el-input placeholder="发货方" class="handle-input"></el-input>
                    <el-input placeholder="发货方" class="handle-input"></el-input>
                    <el-input placeholder="发货方" class="handle-input"></el-input>
                </div>
            </div>
        </div>
            <!--<div class="pagination">-->
                <!--<el-pagination-->
                        <!--@current-change ="handleCurrentChange"-->
                        <!--layout="prev, pager, next, total"-->
                        <!--:total="dataTotal">-->
                <!--</el-pagination>-->
            <!--</div>-->
        <!--</div>-->
    </div>
</template>
<script>
    export default {
        data() {
            return {
                url: this.$projectUrl + '/user/queryListByParams',
                tableData: [],
                input3: '',
                input4: '',
                input5: '',
                select: ''
            }
        },
        created(){
        },
        methods: {
            dateFormat:function(row, column) {
                let date = row.createTime;
                if (date === undefined) {
                    return "";
                }
                return this.$moment(date).format("YYYY-MM-DD HH:mm:ss");
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
.el-select .el-input {
    width: 130px;
}
.input-with-select .el-input-group__prepend {
    background-color: #fff;
}
</style>
