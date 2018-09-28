<template>
    <div class="table">
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-tickets"></i> 物流管理</el-breadcrumb-item>
                <el-breadcrumb-item>库存管理</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-input v-model="searchForm.productName" placeholder="商品名称" class="handle-input mr10" @keyup.enter.native="search"></el-input>
                <el-button type="primary" icon="search" @click="search">搜索</el-button>
            </div>
            <template>
            <el-table :data="tableData" border style="width: 100%">
                <el-table-column prop="productName" label="商品名称"></el-table-column>
                <el-table-column prop="count" label="数量"></el-table-column>
                <!--<el-table-column prop="departure" label="冻结数量"></el-table-column>-->
                <el-table-column prop="volume" label="体积(cm³)"></el-table-column>
                <el-table-column prop="weight" label="重量(kg)"></el-table-column>
                <el-table-column prop="price" label="价格(元)"></el-table-column>
                <el-table-column prop="remarks" label="备注"></el-table-column>
                <el-table-column label="创建时间" width="135">
                    <template slot-scope="scope">
                        {{scope.row.createTime | dateTimeFilter}}
                    </template>
                </el-table-column>
            </el-table>
            </template>
            <div class="pagination">
                <el-pagination @current-change ="handleCurrentChange" layout="prev, pager, next, total" :total="dataTotal"></el-pagination>
            </div>
        </div>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                url: this.$projectUrl + '/stock/queryPageByParams',
                tableData: [],
                multipleSelection: [],
                del_list: [],
                is_search: false,
                dataTotal:1,
                dialogFormVisible: false,
                searchForm: {
                    // status: '',
                },
                options: [{
                    value: '选项1',
                    label: '黄金糕'
                }, {
                    value: '选项2',
                    label: '双皮奶'
                }, {
                    value: '选项3',
                    label: '蚵仔煎'
                }, {
                    value: '选项4',
                    label: '龙须面'
                }, {
                    value: '选项5',
                    label: '北京烤鸭'
                }],
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
                this.getData();
            },
        }
    }
</script>

<style scoped>
.handle-input{
    width: 300px;
    display: inline-block;
}
.handle-box{
    margin-bottom: 20px;
}
.demo-table-expand label {
    width: 90px;
    color: #99a9bf;
}
.demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 25%;
}
</style>
