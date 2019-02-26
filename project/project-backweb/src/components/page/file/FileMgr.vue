<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-date"></i> 文件管理</el-breadcrumb-item>
                <el-breadcrumb-item>文件列表</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-button type="primary" @click="openUploadWin">上传</el-button>
            </div>
            <el-table :data="tableData" border style="width: 100%">
                <el-table-column prop="orgName" label="文件名"></el-table-column>
                <el-table-column prop="size" label="文件大小">
                    <template slot-scope="scope">
                        {{(scope.row.size/1024/1024).toFixed(2) + " M"}}
                    </template>
                </el-table-column>
                <el-table-column label="创建时间">
                    <template slot-scope="scope">
                        {{scope.row.createTime | dateTimeFilter}}
                    </template>
                </el-table-column>
                <el-table-column label="操作" width="180">
                    <template slot-scope="scope">
                        <el-button size="small" type="primary" @click="">下载</el-button>
                        <el-button size="small" type="warning" @click="">删除</el-button>
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
        <el-dialog title="文件上传" :visible.sync="dialogVisible" width="50%">
            <el-upload
                class="upload-demo"
                ref="upload"
                :action="uploadUrl"
                :on-preview="handlePreview"
                :on-remove="handleRemove"
                :file-list="fileList"
                :on-success="handleSuccess"
                :auto-upload="false" :multiple="true">
                <span><el-button slot="trigger" size="small" type="primary">选取文件</el-button></span>
                <div slot="tip" class="el-upload__tip">文件大小不能超过2G</div>
            </el-upload>

            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="success" @click="submitUpload">上 传</el-button>
            </div>
        </el-dialog>

    </div>
</template>

<script>
    export default {
        data() {
            return {
                url: this.$projectUrl + '/file/queryListByParams',
                uploadUrl: this.$projectUrl + "/file/upload",
                dialogVisible: false,
                tableData: [],
                fileList: [],
                searchForm: {
                    pageNum: 1
                },
            };
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
            openUploadWin(){
                this.dialogVisible = true;
            },
            submitUpload() {
                this.$refs.upload.submit();
            },
            handleRemove(file, fileList) {
                console.log(file, fileList);
            },
            handlePreview(file) {
                console.log(file);
            },
            handleSuccess(response, file, fileList){
                console.info(event)
            },
        }
    }
</script>
<style>
    .handle-box{
        margin-bottom: 20px;
    }
    .el-upload--text{
        width:auto;
        height:auto
    }
</style>
