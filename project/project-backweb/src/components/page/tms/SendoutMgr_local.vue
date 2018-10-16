<template>
    <div class="table">
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-tickets"></i> 物流管理</el-breadcrumb-item>
                <el-breadcrumb-item>外发管理</el-breadcrumb-item>
                <el-breadcrumb-item>本地外发</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" class="demo-ruleForm" :inline="true">
                <p>运单信息</p>
                <hr style="margin-top:2px; color: #bbbbbb;"><br>
                <div class="handle-box" align="center">
                    <el-form-item prop="consignorAddr" label="始发地">
                        <el-input placeholder="始发地" class="handle-input-half" v-model="ruleForm.consignorAddr" disabled></el-input>
                    </el-form-item>
                    <el-form-item prop="consignorAddr" label="目的地">
                        <el-input placeholder="目的地" class="handle-input-half" v-model="ruleForm.consignorAddr" disabled></el-input>
                    </el-form-item>
                    <el-form-item prop="consignorAddr">
                        <el-input placeholder="备注" class="handle-input" v-model="ruleForm.consignorAddr" disabled></el-input>
                    </el-form-item>
                </div>
                <div class="handle-box" align="center">
                    <el-form-item prop="consignorTel">
                        <el-input placeholder="运单号" class="handle-input" v-model="ruleForm.consignorTel" disabled></el-input>
                    </el-form-item>
                    <el-form-item prop="consignorTel">
                        <el-input placeholder="入库单号" class="handle-input" v-model="ruleForm.consignorTel" disabled></el-input>
                    </el-form-item>
                    <el-form-item prop="consignorMobile">
                        <el-input placeholder="运单日期" class="handle-input" v-model="ruleForm.consignorMobile" disabled></el-input>
                    </el-form-item>
                    <el-form-item prop="consignorMobile">
                        <el-input placeholder="结算方式" class="handle-input" v-model="ruleForm.consignorMobile" disabled></el-input>
                    </el-form-item>
                </div>
                <div class="handle-box" align="center">
                    <el-form-item prop="consignorTel">
                        <el-input placeholder="总金额" class="handle-input" v-model="ruleForm.consignorTel" disabled></el-input>
                    </el-form-item>
                    <el-form-item prop="consignorTel">
                        <el-input placeholder="发货方" class="handle-input" v-model="ruleForm.consignorTel" disabled></el-input>
                    </el-form-item>
                    <el-form-item prop="consignorMobile">
                        <el-input placeholder="发货方电话" class="handle-input" v-model="ruleForm.consignorMobile" disabled></el-input>
                    </el-form-item>
                    <el-form-item prop="consignorAddr">
                        <el-input placeholder="发货方手机" class="handle-input" v-model="ruleForm.consignorAddr" disabled></el-input>
                    </el-form-item>
                </div>
                <div class="handle-box" align="center">
                    <el-form-item prop="consignorTel">
                        <el-input placeholder="收货方" class="handle-input" v-model="ruleForm.consignorTel" disabled></el-input>
                    </el-form-item>
                    <el-form-item prop="consignorTel">
                        <el-input placeholder="收货方电话" class="handle-input" v-model="ruleForm.consignorTel" disabled></el-input>
                    </el-form-item>
                    <el-form-item prop="consignorMobile">
                        <el-input placeholder="收货方手机" class="handle-input" v-model="ruleForm.consignorMobile" disabled></el-input>
                    </el-form-item>
                    <el-form-item prop="consignorAddr">
                        <el-input placeholder="垫付中转费" class="handle-input" v-model="ruleForm.consignorAddr" disabled></el-input>
                    </el-form-item>
                </div>
                <hr style="margin-top:2px; color: #bbbbbb;"><br>
                <el-table :data="tableData" border style="width: 100%">
                    <el-table-column prop="departure" label="品名"></el-table-column>
                    <el-table-column prop="departure" label="型号"></el-table-column>
                    <el-table-column prop="departure" label="件数"></el-table-column>
                    <el-table-column prop="departure" label="包装"></el-table-column>
                    <el-table-column prop="departure" label="体积(cm³)"></el-table-column>
                    <el-table-column prop="departure" label="重量(kg)"></el-table-column>
                    <el-table-column prop="departure" label="基本运费"></el-table-column>
                    <el-table-column prop="departure" label="申明价值"></el-table-column>
                    <el-table-column prop="departure" label="保险费"></el-table-column>
                    <el-table-column prop="departure" label="长宽高(mm)"></el-table-column>
                </el-table><br>
                <p>承运信息</p>
                <hr style="margin-top:2px; color: #bbbbbb;"><br>
                <div class="handle-box" align="center">
                    <el-form-item prop="consignorTel">
                        <el-input placeholder="承运公司" class="handle-input" v-model="ruleForm.consignorTel" disabled></el-input>
                    </el-form-item>
                    <el-form-item prop="consignorTel">
                        <el-input placeholder="本地电话" class="handle-input" v-model="ruleForm.consignorTel" disabled></el-input>
                    </el-form-item>
                    <el-form-item prop="consignorMobile">
                        <el-input placeholder="提货电话" class="handle-input" v-model="ruleForm.consignorMobile" disabled></el-input>
                    </el-form-item>
                    <el-form-item prop="consignorAddr">
                        <el-input placeholder="中转单号" class="handle-input" v-model="ruleForm.consignorAddr" disabled></el-input>
                    </el-form-item>
                </div>
                <div class="handle-box" align="center">
                    <el-form-item prop="consignorTel">
                        <el-input placeholder="中转日期" class="handle-input" v-model="ruleForm.consignorTel" disabled></el-input>
                    </el-form-item>
                    <el-form-item prop="consignorTel">
                        <el-input placeholder="中转备注" class="handle-input" v-model="ruleForm.consignorTel" disabled></el-input>
                    </el-form-item>
                </div>
                <div class="handle-box" align="center">
                    <template>
                        <el-checkbox v-model="checked1">发送短信（发货方）</el-checkbox>
                        <el-checkbox v-model="checked2">发送短信（收货方）</el-checkbox>
                    </template>
                </div>
                <p>应付</p>
                <div class="container-inner">
                    <div class="handle-box" align="center">
                        <el-form-item prop="consignorTel">
                            <el-input placeholder="现付" class="handle-input" v-model="ruleForm.consignorTel" disabled></el-input>
                        </el-form-item>
                        <el-form-item prop="consignorTel">
                            <el-input placeholder="月结" class="handle-input" v-model="ruleForm.consignorTel" disabled></el-input>
                        </el-form-item>
                        <el-form-item prop="consignorTel">
                            <el-input placeholder="回单付" class="handle-input" v-model="ruleForm.consignorTel" disabled></el-input>
                        </el-form-item>
                    </div>
                </div>
                <p>应收</p>
                <div class="container-inner">
                    <div class="handle-box" align="center">
                        <el-form-item prop="consignorTel">
                            <el-input placeholder="反中转费" class="handle-input" v-model="ruleForm.consignorTel" disabled></el-input>
                        </el-form-item>
                        <el-form-item prop="consignorTel">
                            <el-checkbox v-model="checked3">中转费已返</el-checkbox>
                        </el-form-item>
                        <el-form-item prop="consignorTel">
                            <el-input placeholder="中转成本" class="handle-input" v-model="ruleForm.consignorTel" disabled></el-input>
                        </el-form-item>
                    </div>
                </div>
                <p>其他费用</p>
                <hr style="margin-top:2px; color: #bbbbbb;"><br>
                <div class="handle-box" align="center">
                    <el-form-item prop="consignorTel">
                        <el-input placeholder="接货费" class="handle-input" v-model="ruleForm.consignorTel" disabled></el-input>
                    </el-form-item>
                    <el-form-item prop="consignorTel">
                        <el-input placeholder="送货费" class="handle-input" v-model="ruleForm.consignorTel" disabled></el-input>
                    </el-form-item>
                    <el-form-item prop="consignorTel">
                        <el-input placeholder="包装费" class="handle-input" v-model="ruleForm.consignorTel" disabled></el-input>
                    </el-form-item>
                    <el-form-item prop="consignorTel">
                        <el-input placeholder="过磅费" class="handle-input" v-model="ruleForm.consignorTel" disabled></el-input>
                    </el-form-item>
                </div>
                <div class="handle-box" align="center">
                    <el-form-item prop="consignorTel">
                        <el-input placeholder="装货费" class="handle-input" v-model="ruleForm.consignorTel" disabled></el-input>
                    </el-form-item>
                    <el-form-item prop="consignorTel">
                        <el-input placeholder="进仓费" class="handle-input" v-model="ruleForm.consignorTel" disabled></el-input>
                    </el-form-item>
                    <el-form-item prop="consignorTel">
                        <el-input placeholder="卸货费" class="handle-input" v-model="ruleForm.consignorTel" disabled></el-input>
                    </el-form-item>
                    <el-form-item prop="consignorTel">
                        <el-input placeholder="其他费" class="handle-input" v-model="ruleForm.consignorTel" disabled></el-input>
                    </el-form-item>
                </div>
                <div class="handle-box" align="center">
                    <el-form-item prop="consignorTel">
                        <el-input placeholder="吊（叉）车费" class="handle-input" v-model="ruleForm.consignorTel" disabled></el-input>
                    </el-form-item>
                    <el-form-item prop="consignorTel">
                        <el-input placeholder="费用说明" class="handle-input" v-model="ruleForm.consignorTel" disabled></el-input>
                    </el-form-item>
                    <el-form-item prop="consignorTel">
                        <el-input placeholder="预计到达" class="handle-input" v-model="ruleForm.consignorTel" disabled></el-input>
                    </el-form-item>
                </div>
                <div class="handle-box" align="center">
                    <el-button type="primary" @click="submit">提交</el-button>
                </div>
            </el-form>
        </div>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                url: this.$projectUrl + '/consign/queryPageByParams',
                ruleForm:{
                },
                checked1: false,
                checked2: false,
                checked3: false
            }
        },
        created(){
            this.searchForm.pageNum = 1;
            this.searchForm.pageSize = 5;
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
        }
    }
</script>

<style scoped>
.handle-box{
    margin-bottom: 5px;
}
.handle-select{
    width: 120px;
}
.handle-input{
    width: 238px;
    display: inline-block;
}
.handle-input-half{
    width: 120px;
    display: inline-block;
}
.container-inner{
    padding: 5px;
    background: #fff;
    border: 1px solid #ddd;
    border-radius: 5px;
    margin-bottom: 5px;
}
</style>
