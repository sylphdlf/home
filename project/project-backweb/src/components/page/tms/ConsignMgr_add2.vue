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
                <el-steps :active="2" align-center>
                    <el-step title="第1步" description="填写基础信息"></el-step>
                    <el-step title="第2步" description="填写商品信息"></el-step>
                </el-steps>
            </div>
            <p>商品信息</p>
            <hr style="margin-top:2px; color: #bbbbbb;"><br>
            <div align="center">
                <el-table :data="ruleForm.productList" border style="width: 96%" highlight-current-row>
                    <el-table-column prop="productName" label="品名"></el-table-column>
                    <el-table-column prop="productType" label="型号"></el-table-column>
                    <el-table-column prop="productCount" label="件数"></el-table-column>
                    <el-table-column prop="productPackage" label="包装"></el-table-column>
                    <el-table-column prop="productVolume" label="体积"></el-table-column>
                    <el-table-column prop="productWeight" label="重量"></el-table-column>
                    <el-table-column prop="productPrice" label="申明价格"></el-table-column>
                    <el-table-column prop="productLength" label="长"></el-table-column>
                    <el-table-column prop="productWidth" label="宽"></el-table-column>
                    <el-table-column prop="productHeight" label="高"></el-table-column>
                </el-table>
                <div style="margin-top: 20px">
                    <el-button @click="addProduct">新增商品</el-button>
                </div>
            </div>
            <br>
            <p>价格信息</p>
            <hr style="margin-top:2px; color: #bbbbbb;"><br>
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" class="demo-ruleForm" :inline="true">
                <div class="handle-box" align="center">
                    <el-form-item prop="payType">
                        <el-select v-model="ruleForm.payType" placeholder="结算方式" class="handle-input">
                            <el-option label="回单付" value="1"></el-option>
                            <el-option label="现付" value="2"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item prop="transportType">
                        <el-select v-model="ruleForm.transportType" placeholder="运输方式" class="handle-input">
                            <el-option label="回单付" value="1"></el-option>
                            <el-option label="现付" value="2"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item prop="receiptType">
                        <el-select v-model="ruleForm.receiptType" placeholder="回单要求" class="handle-input">
                            <el-option label="回单付" value="1"></el-option>
                            <el-option label="现付" value="2"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item prop="deliveryType">
                        <el-select v-model="ruleForm.deliveryType" placeholder="交接方式" class="handle-input">
                            <el-option label="回单付" value="1"></el-option>
                            <el-option label="现付" value="2"></el-option>
                        </el-select>
                    </el-form-item>
                </div>
                <div class="handle-box" align="center">
                    <el-form-item prop="payNow" label="现付(元)">
                        <el-input-number size="small" v-model="ruleForm.payNow" :precision="2" :step="0.1" controls-position="right" :min="0"></el-input-number>
                    </el-form-item>
                    <el-form-item prop="payDelivery" label="提付(元)">
                        <el-input-number size="small" v-model="ruleForm.payDelivery" :precision="2" :step="0.1" controls-position="right" :min="0"></el-input-number>
                    </el-form-item>
                    <el-form-item prop="payReceipt" label="回单付(元)">
                        <el-input-number size="small" v-model="ruleForm.payReceipt" :precision="2" :step="0.1" controls-position="right" :min="0"></el-input-number>
                    </el-form-item>
                    <el-form-item prop="payMonthly" label="月结(元)">
                        <el-input-number size="small" v-model="ruleForm.payMonthly" :precision="2" :step="0.1" controls-position="right" :min="0"></el-input-number>
                    </el-form-item>
                    <el-form-item prop="payAccount" label="扣付(元)">
                        <el-input-number size="small" v-model="ruleForm.payAccount" :precision="2" :step="0.1" controls-position="right" :min="0"></el-input-number>
                    </el-form-item>
                </div>
                <div class="handle-box" align="center">
                    <el-form-item prop="basicPrice" label="基本运费">
                        <el-input-number size="small" v-model="ruleForm.basicPrice" :precision="2" :step="0.1" controls-position="right" :min="0"></el-input-number>
                    </el-form-item>
                    <el-form-item prop="vehiclePrice" label="车费">
                        <el-input-number size="small" v-model="ruleForm.vehiclePrice" :precision="2" :step="0.1" controls-position="right" :min="0"></el-input-number>
                    </el-form-item>
                    <el-form-item prop="remarks">
                        <el-input placeholder="备注" class="handle-input" v-model="ruleForm.remarks"></el-input>
                    </el-form-item>
                </div>
                <div class="handle-box" align="center">
                    <el-form-item label="合计：">
                        <div>{{sumPrice}}&nbsp;元</div>
                        <!--<el-input v-model="ruleForm.payTotal" :disabled="true"></el-input>-->
                    </el-form-item>
                </div>
                <div class="handle-box" align="center">
                    <el-button type="primary" @click="prePage"><i class="el-icon-arrow-left"></i>上一步</el-button>
                    <el-button type="primary" @click="submit">提交</el-button>
                </div>
            </el-form>
        </div>
        <el-dialog title="新增商品" :visible.sync="dialogFormVisible" width="25%">
            <el-form :model="dialogData" :rules="rules" ref="dialogData"  label-width="80px">
                <el-form-item prop="productName" label="商品名称">
                    <el-input v-model="dialogData.productName"></el-input>
                </el-form-item>
                <el-form-item prop="productType" label="型号">
                    <el-input v-model="dialogData.productType"></el-input>
                </el-form-item>
                <el-form-item prop="productPackage" label="包装">
                    <el-input v-model="dialogData.productPackage"></el-input>
                </el-form-item>
                <el-form-item prop="productCount" label="件数">
                    <el-input-number size="small" v-model="dialogData.productCount" controls-position="right" :min="0" :step="1"></el-input-number>
                </el-form-item>
                <el-form-item prop="productVolume" label="体积">
                    <el-input-number size="small" v-model="dialogData.productVolume" controls-position="right" :min="0" :step="1"></el-input-number>
                    &nbsp;<span>立方厘米</span>
                </el-form-item>
                <el-form-item prop="productWeight" label="重量">
                    <el-input-number size="small" v-model="dialogData.productWeight" controls-position="right" :min="0" :step="1"></el-input-number>
                    &nbsp;<span>公斤</span>
                </el-form-item>
                <el-form-item prop="productPrice" label="申明价格">
                    <el-input-number size="small" v-model="dialogData.productPrice" :precision="2" :step="0.1" controls-position="right" :min="0"></el-input-number>
                    &nbsp;<span>元</span>
                </el-form-item>
                <el-form-item prop="productLength" label="长">
                    <el-input-number size="small" v-model="dialogData.productLength" controls-position="right" :min="0" :step="1"></el-input-number>
                    &nbsp;<span>毫米</span>
                </el-form-item>
                <el-form-item prop="productWidth" label="宽">
                    <el-input-number size="small" v-model="dialogData.productWidth" controls-position="right" :min="0" :step="1"></el-input-number>
                    &nbsp;<span>毫米</span>
                </el-form-item>
                <el-form-item prop="productHeight" label="高">
                    <el-input-number size="small" v-model="dialogData.productHeight" controls-position="right" :min="0" :step="1"></el-input-number>
                    &nbsp;<span>毫米</span>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="addProductCommit">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                url: this.$projectUrl + '/user/queryListByParams',
                urlAddStep2: this.$projectUrl + '/consign/addStep2',
                dialogFormVisible: false,
                dialogData: {
                    productName: '',
                    productType: '',
                    productCount: '',
                    productPackage: '',
                    productVolume: '',
                    productWeight: '',
                    productPrice: '',
                    productLength: '',
                    productWidth: '',
                    productHeight: '',
                },
                ruleForm: {
                    // id: '',
                    payType: '',
                    transportType: '',
                    receiptType: '',
                    deliveryType: '',
                    basicPrice: '',
                    vehiclePrice: '',
                    payNow: '',
                    payDelivery: '',
                    payReceipt: '',
                    payMonthly: '',
                    payAccount: '',
                    payTotal: '',
                    remarks: '',
                    productList: [],
                },
                rules: {
                    payType: [{required: true, message: '请填写结算方式', trigger: 'blur'}],
                    transportType: [{required: true, message: '请填写运输方式', trigger: 'blur'}],
                    receiptType: [{required: true, message: '请填写回单要求', trigger: 'blur'}],
                    deliveryType: [{required: true, message: '请填写交接方式', trigger: 'blur'}],
                    basicPrice: [{required: true, message: '请填写基本运费', trigger: 'blur'}],
                    vehiclePrice: [{required: true, message: '请填写车费', trigger: 'blur'}],
                    payTotal: [{required: true, message: '请填写合计', trigger: 'blur'}],
                },
            }
        },
        created(){
            //上一个页面传过来的id
            this.ruleForm.id = this.$route.query.id;
        },
        computed:{
            sumPrice: function () {
                this.ruleForm.payTotal = this.ruleForm.payNow + this.ruleForm.payDelivery + this.ruleForm.payReceipt
                + this.ruleForm.payMonthly + this.ruleForm.payAccount
                + this.ruleForm.basicPrice + this.ruleForm.vehiclePrice;
                this.ruleForm.payTotal = Math.round(Number(this.ruleForm.payTotal * 100)) / 100 + "";
                return this.ruleForm.payTotal;
            }
        },
        methods: {
            addProduct: function() {
                this.dialogFormVisible = true;
            },
            addProductCommit: function(){
                this.ruleForm.productList.push(this.dialogData);
                this.dialogFormVisible = false;
                this.dialogData = {};
            },
            prePage: function () {
                this.$router.push({path:'/consignMgr_add', query: {id: this.ruleForm.id}});
            },
            submit: function () {
                this.$refs['ruleForm'].validate((valid) => {
                    if (valid) {
                        this.$axios.post(this.urlAddStep2, this.ruleForm).then((res) => {
                            if(res.data.code === '0'){
                                this.msgSuccess();
                                this.$router.push({path:'/consignMgr'});
                            }
                        });
                    } else {
                        this.msgFail("请补全表单信息");
                        return false;
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
    width: 238px;
    display: inline-block;
}
.el-select .el-input {
    width: 130px;
}
.input-with-select .el-input-group__prepend {
    background-color: #fff;
}
</style>
