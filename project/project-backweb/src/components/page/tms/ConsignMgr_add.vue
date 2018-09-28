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
            <p>发货人信息</p>
            <hr style="margin-top:2px; color: #bbbbbb;"><br>
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" class="demo-ruleForm" :inline="true">
                <div class="handle-box" align="center">
                    <el-form-item prop="consignorName">
                        <el-input placeholder="发货人" v-model="ruleForm.consignorName">
                            <template slot="prepend"><el-button icon="el-icon-search" @click="openConsignorWin"></el-button></template>
                        </el-input>
                    </el-form-item>
                    <el-form-item prop="consignorTel">
                        <el-input placeholder="座机" class="handle-input" v-model="ruleForm.consignorTel" disabled></el-input>
                    </el-form-item>
                    <el-form-item prop="consignorMobile">
                        <el-input placeholder="手机" class="handle-input" v-model="ruleForm.consignorMobile" disabled></el-input>
                    </el-form-item>
                    <el-form-item prop="consignorAddr">
                        <el-input placeholder="发货方地址" class="handle-input" v-model="ruleForm.consignorAddr" disabled></el-input>
                    </el-form-item>
                </div>
                <p>收货人信息</p>
                <hr style="margin-top:2px; color: #bbbbbb;"><br>
                <div class="handle-box" align="center">
                    <el-form-item prop="consigneeName">
                        <el-input placeholder="收货人" v-model="ruleForm.consigneeName">
                            <template slot="prepend"><el-button icon="el-icon-search" @click="openConsigneeWin"></el-button></template>
                        </el-input>
                    </el-form-item>
                    <el-form-item prop="consigneeTel">
                        <el-input placeholder="座机" class="handle-input" v-model="ruleForm.consigneeTel" disabled></el-input>
                    </el-form-item>
                    <el-form-item prop="consigneeMobile">
                        <el-input placeholder="手机" class="handle-input" v-model="ruleForm.consigneeMobile" disabled></el-input>
                    </el-form-item>
                    <el-form-item prop="consigneeAddr">
                        <el-input placeholder="收货人地址" class="handle-input" v-model="ruleForm.consigneeAddr" disabled></el-input>
                    </el-form-item>
                </div>
                <p>送货信息</p>
                <hr style="margin-top:2px; color: #bbbbbb;"><br>
                <div class="handle-box" align="center">
                    <el-form-item prop="driverName">
                        <el-input placeholder="司机" v-model="ruleForm.driverName">
                            <template slot="prepend"><el-button icon="el-icon-search" @click="openDriverWin"></el-button></template>
                        </el-input>
                    </el-form-item>
                    <el-form-item prop="vehicleNumber">
                        <el-input placeholder="车牌号" class="handle-input" v-model="ruleForm.vehicleNumber" disabled></el-input>
                    </el-form-item>
                    <el-form-item prop="driverMobile">
                        <el-input placeholder="手机" class="handle-input" v-model="ruleForm.driverMobile" disabled></el-input>
                    </el-form-item>
                    <el-form-item prop="loadingPlace">
                        <el-input placeholder="装货地" class="handle-input" v-model="ruleForm.loadingPlace"></el-input>
                    </el-form-item>
                </div>
                <div class="handle-box" align="center">
                    <el-form-item prop="vehicleLeaveTime">
                        <el-date-picker v-model="ruleForm.vehicleLeaveTime" type="datetime" placeholder="出车时间" class="handle-input"></el-date-picker>
                    </el-form-item>
                    <el-form-item prop="cargoLeaveTime">
                        <el-date-picker v-model="ruleForm.cargoLeaveTime" type="datetime" placeholder="发货时间" class="handle-input"></el-date-picker>
                    </el-form-item>
                    <el-form-item prop="expectedArriveTime">
                        <el-date-picker v-model="ruleForm.expectedArriveTime" type="datetime" placeholder="要求到货时间" class="handle-input"></el-date-picker>
                    </el-form-item>
                    <el-form-item prop="cargoPackageTime">
                        <el-date-picker v-model="ruleForm.cargoPackageTime" type="datetime" placeholder="装货时间" class="handle-input"></el-date-picker>
                    </el-form-item>
                </div>
                <p>商品信息</p>
                <hr style="margin-top:2px; color: #bbbbbb;"><br>
                <div class="handle-box" align="center">
                    <el-form-item prop="productName">
                        <el-input placeholder="商品名称" v-model="ruleForm.productName" class="handle-input"></el-input>
                    </el-form-item>
                    <el-form-item prop="productType">
                        <el-input placeholder="型号" v-model="ruleForm.productType" class="handle-input"></el-input>
                    </el-form-item>
                    <el-form-item prop="productPackaging">
                        <el-input placeholder="包装" v-model="ruleForm.productPackaging" class="handle-input"></el-input>
                    </el-form-item>
                    <el-form-item prop="productRemarks">
                        <el-input placeholder="备注" v-model="ruleForm.productRemarks" class="handle-input"></el-input>
                    </el-form-item>
                </div>
                <div class="handle-box" align="center">
                    <el-form-item prop="productVolume" label="体积(cm³)">
                        <el-input-number size="small" v-model="ruleForm.productVolume" controls-position="right" :min="0" :step="1"></el-input-number>
                    </el-form-item>
                    <el-form-item prop="productWeight" label="重量(kg)">
                        <el-input-number size="small" v-model="ruleForm.productWeight" controls-position="right" :min="0" :step="1"></el-input-number>
                    </el-form-item>
                    <el-form-item prop="productPrice" label="申明价格(元)">
                        <el-input-number size="small" v-model="ruleForm.productPrice" :precision="2" :step="0.1" controls-position="right" :min="0"></el-input-number>
                    </el-form-item>
                    <el-form-item prop="productLength" label="长*宽*高(mm)">
                        <el-input-number size="small" v-model="ruleForm.productLength" controls-position="right" :min="0" :step="1"></el-input-number>
                        <el-input-number size="small" v-model="ruleForm.productWidth" controls-position="right" :min="0" :step="1"></el-input-number>
                        <el-input-number size="small" v-model="ruleForm.productHeight" controls-position="right" :min="0" :step="1"></el-input-number>
                    </el-form-item>
                </div>
                <p>库存信息</p>
                <hr style="margin-top:2px; color: #bbbbbb;"><br>
                <div class="handle-box" align="center">
                    <el-form-item prop="productCount" label="数量">
                        <el-input-number size="small" v-model="ruleForm.stockCount" controls-position="right" :min="0" :step="1" :precision="0"></el-input-number>
                    </el-form-item>
                    <el-form-item prop="productVolume" label="库存体积(cm³)">
                        <el-input-number size="small" v-model="ruleForm.stockVolume" controls-position="right" :min="0" :step="1"></el-input-number>
                    </el-form-item>
                    <el-form-item prop="productWeight" label="库存重量(kg)">
                        <el-input-number size="small" v-model="ruleForm.stockWeight" controls-position="right" :min="0" :step="1"></el-input-number>
                    </el-form-item>
                    <el-form-item label="总价值：">
                        <div>{{sumStockPrice}}&nbsp;元</div>
                        <!--<el-input v-model="ruleForm.payTotal" :disabled="true"></el-input>-->
                    </el-form-item>
                    <el-form-item prop="productPrice" label="总价值(元)">
                        <el-input-number size="small" v-model="ruleForm.stockPrice" :precision="2" :step="0.1" controls-position="right" :min="0"></el-input-number>
                    </el-form-item>
                </div>
                <p>价格信息</p>
                <hr style="margin-top:2px; color: #bbbbbb;"><br>
                <div class="handle-box" align="center">
                    <el-form-item prop="payType">
                        <el-select v-model="ruleForm.payType" placeholder="结算方式" class="handle-input" value="">
                            <el-option label="回单付" value="1"></el-option>
                            <el-option label="现付" value="2"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item prop="transportType">
                        <el-select v-model="ruleForm.transportType" placeholder="运输方式" class="handle-input" value="">
                            <el-option label="回单付" value="1"></el-option>
                            <el-option label="现付" value="2"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item prop="receiptType">
                        <el-select v-model="ruleForm.receiptType" placeholder="回单要求" class="handle-input" value="">
                            <el-option label="回单付" value="1"></el-option>
                            <el-option label="现付" value="2"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item prop="deliveryType">
                        <el-select v-model="ruleForm.deliveryType" placeholder="交接方式" class="handle-input" value="">
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
                </div>
                <div class="handle-box" align="center">
                    <el-form-item prop="payAccount" label="扣付(元)">
                        <el-input-number size="small" v-model="ruleForm.payAccount" :precision="2" :step="0.1" controls-position="right" :min="0"></el-input-number>
                    </el-form-item>
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
                    <el-button type="primary" @click="submit">提交<i class="el-icon-arrow-right"></i></el-button>
                </div>
            </el-form>
        </div>

        <el-dialog title="发货人查询" :visible.sync="consignorShow">
            <div class="handle-box">
                <el-input v-model="searchFormContacts.search" placeholder="姓名/电话/手机" class="handle-input mr10" @keyup.enter.native="consignorSearch"></el-input>
                <el-button type="primary" icon="el-icon-search" @click="consignorSearch"></el-button>
            </div>
            <el-table :data="consignorData" highlight-current-row @current-change="selectConsignor">
                <el-table-column property="name" label="发货人" width="150"></el-table-column>
                <el-table-column property="telephone" label="座机" width="200"></el-table-column>
                <el-table-column property="mobile" label="手机"></el-table-column>
                <el-table-column property="address" label="发货方地址"></el-table-column>
            </el-table>
            <div class="pagination">
                <el-pagination
                    @current-change ="consignorPageChange"
                    layout="prev, pager, next, total"
                    :total="consignorTotal">
                </el-pagination>
            </div>
            <div slot="footer" class="dialog-footer">
                <el-button @click="consignorShow = false">取 消</el-button>
                <el-button type="primary" @click="selectConsignorConfirm">确 定</el-button>
            </div>
        </el-dialog>
        <el-dialog title="收货人查询" :visible.sync="consigneeShow">
            <div class="handle-box">
                <el-input v-model="searchFormContacts.search" placeholder="姓名/电话/手机" class="handle-input mr10" @keyup.enter.native="consigneeSearch"></el-input>
                <el-button type="primary" icon="el-icon-search" @click="consigneeSearch"></el-button>
            </div>
            <el-table :data="consigneeData" highlight-current-row @current-change="selectConsignee">
                <el-table-column property="name" label="收货人" width="150"></el-table-column>
                <el-table-column property="telephone" label="座机" width="200"></el-table-column>
                <el-table-column property="mobile" label="手机"></el-table-column>
                <el-table-column property="address" label="收货方地址"></el-table-column>
            </el-table>
            <div class="pagination">
                <el-pagination
                    @current-change ="consigneePageChange"
                    layout="prev, pager, next, total"
                    :total="consignorTotal">
                </el-pagination>
            </div>
            <div slot="footer" class="dialog-footer">
                <el-button @click="consigneeShow = false">取 消</el-button>
                <el-button type="primary" @click="selectConsigneeConfirm">确 定</el-button>
            </div>
        </el-dialog>
        <el-dialog title="车辆查询" :visible.sync="driverShow">
            <div class="handle-box">
                <el-input v-model="searchFormDriver.search" placeholder="姓名/电话/手机" class="handle-input mr10" @keyup.enter.native="driverSearch"></el-input>
                <el-button type="primary" icon="el-icon-search" @click="driverSearch"></el-button>
            </div>
            <el-table :data="driverData" highlight-current-row @current-change="selectDriver">
                <el-table-column property="vehicleNumber" label="车牌号"></el-table-column>
                <el-table-column property="vehicleType" label="车型"></el-table-column>
                <el-table-column label="长宽高">
                    <template slot-scope="scope">
                        {{scope.row.vehicleLength + '*' + scope.row.vehicleWidth + '*' + scope.row.vehicleHeight}}
                    </template>
                </el-table-column>
                <el-table-column property="vehicleVin" label="VIN码"></el-table-column>
                <el-table-column property="driverName" label="司机姓名"></el-table-column>
                <el-table-column property="driverLicence" label="驾照"></el-table-column>
            </el-table>
            <div class="pagination">
                <el-pagination
                    @current-change ="driverPageChange"
                    layout="prev, pager, next, total"
                    :total="driverTotal">
                </el-pagination>
            </div>
            <div slot="footer" class="dialog-footer">
                <el-button @click="driverShow = false">取 消</el-button>
                <el-button type="primary" @click="selectDriverConfirm">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                urlConsignor: this.$projectUrl + '/contacts/queryConsignorPage',
                urlConsignee: this.$projectUrl + '/contacts/queryConsigneePage',
                urlVehicle: this.$projectUrl + '/vehicle/queryPageByParams',
                urlAdd: this.$projectUrl + '/consign/add',
                urlRecord: this.$projectUrl + '/consign/getConsignRecord',
                selectedConsignor:{},
                selectedConsignee:{},
                selectedDriver:{},
                consignorShow: false,
                consigneeShow: false,
                driverShow: false,
                searchFormContacts:{
                    pageNum: 1,
                },
                searchFormDriver:{
                    pageNum: 1,
                },
                consignorData: [],
                consigneeData: [],
                driverData: [],
                consignorTotal:1,
                consigneeTotal:1,
                driverTotal:1,
                ruleForm: {
                    consignorId: '',
                    consignorName: '',
                    consignorTel: '',
                    consignorMobile: '',
                    consignorAddr: '',
                    consigneeId: '',
                    consigneeName: '',
                    consigneeTel: '',
                    consigneeMobile: '',
                    consigneeAddr: '',
                    vehicleId: '',
                    driverName: '',
                    vehicleNumber: '',
                    driverMobile: '',
                    loadingPlace: '',
                    vehicleLeaveTime: '',
                    cargoLeaveTime: '',
                    expectedArriveTime: '',
                    cargoPackageTime: '',
                    payType: '',
                    transportType: '',
                    receiptType: '',
                    deliveryType: '',
                    basicPrice: 0,
                    vehiclePrice: 0,
                    payNow: 0,
                    payDelivery: 0,
                    payReceipt: 0,
                    payMonthly: 0,
                    payAccount: 0,
                    payTotal: 0,
                    remarks: '',
                    productName: '',
                    productType: '',
                    productPackaging: '',
                    productVolume: 0,
                    productWeight: 0,
                    productPrice: 0,
                    productLength: 0,
                    productWidth: 0,
                    productHeight: 0,
                    productRemarks: '',
                    stockCount: 0,
                    stockVolume: 0,
                    stockWeight: 0,
                    stockPrice: 0,
                },
                rules: {
                    consignor: [{ required: true, message: '请填写发货人', trigger: 'blur' }],
                    consignorMobile: [{ required: true, message: '请填写发货人手机', trigger: 'blur' }],
                    consignorAddr: [{ required: true, message: '请填写发货人地址', trigger: 'blur' }],
                    consignee: [{ required: true, message: '请填写收货人', trigger: 'blur' }],
                    consigneeMobile: [{ required: true, message: '请填写收货人手机', trigger: 'blur' }],
                    consigneeAddr: [{ required: true, message: '请填写收货人地址', trigger: 'blur' }],
                    driverName: [{ required: true, message: '请填写司机姓名', trigger: 'blur' }],
                    vehicleNumber: [{ required: true, message: '请填写车牌号', trigger: 'blur' }],
                    driverMobile: [{ required: true, message: '请填写司机手机', trigger: 'blur' }],
                    loadingPlace: [{ required: true, message: '请填写装货地址', trigger: 'blur' }],
                    vehicleLeaveTime: [{ required: true, message: '请填写出车时间', trigger: 'blur' }],
                    cargoLeaveTime: [{ required: true, message: '请填写货物发出时间', trigger: 'blur' }],
                    expectedArriveTime: [{ required: true, message: '请填写要求到货时间', trigger: 'blur' }],
                    cargoPackageTime: [{ required: true, message: '请填写装货时间', trigger: 'blur' }],
                }
            }
        },
        created(){
            // //上一个页面传过来的id
            // this.ruleForm.id = this.$route.query.id;
            // console.info(this.ruleForm.id);
            // if(this.ruleForm.id !== '' && this.ruleForm.id !== undefined){
            //     this.$axios.post(this.urlRecord, this.ruleForm).then((res) => {
            //         if(res.data.code === '0'){
            //             this.ruleForm = res.data.data;
            //         }
            //     })
            // }
        },
        computed:{
            sumPrice: function () {
                this.ruleForm.payTotal = this.ruleForm.payNow + this.ruleForm.payDelivery + this.ruleForm.payReceipt
                    + this.ruleForm.payMonthly + this.ruleForm.payAccount
                    + this.ruleForm.basicPrice + this.ruleForm.vehiclePrice;
                this.ruleForm.payTotal = Math.round(this.ruleForm.payTotal * 100) / 100;
                return this.ruleForm.payTotal;
            },
            sumStockPrice: function () {
                this.ruleForm.stockPrice = this.ruleForm.productPrice * this.ruleForm.stockCount;
                return this.ruleForm.stockPrice;
            }
        },
        methods: {
            // 寄件人
            consignorPageChange(val){
                this.consignorSearch.pageNum = val;
                this.getConsignorData();
            },
            openConsignorWin: function(){
                this.consignorShow = true;
                this.getConsignorData();
            },
            consignorSearch: function(){
                this.getConsignorData();
            },
            getConsignorData: function(){
                this.$axios.post(this.urlConsignor, this.searchFormContacts).then((res) => {
                    if(res.data.code === '0' && res.data.total !== 0){
                        this.consignorData = res.data.data.list;
                        this.consignorTotal = res.data.data.total;
                    }
                });
            },
            selectConsignor(val){
                this.selectedConsignor = val;
                console.info(this.selectedConsignor);
            },
            selectConsignorConfirm: function(){
                if(this.selectedConsignor.id===undefined){
                    this.msgWarn("请点击一条数据");
                    return false;
                }
                this.ruleForm.consignorId = this.selectedConsignor.id;
                this.ruleForm.consignorName = this.selectedConsignor.name;
                this.ruleForm.consignorTel = this.selectedConsignor.telephone;
                this.ruleForm.consignorMobile = this.selectedConsignor.mobile;
                this.ruleForm.consignorAddr = this.selectedConsignor.address;
                this.consignorShow = false;
            },
            //收件人
            openConsigneeWin: function(){
                this.consigneeShow = true;
                this.getConsigneeData();
            },
            consigneePageChange(val){
                this.consigneeSearch.pageNum = val;
                this.getConsigneeData();
            },
            selectConsignee(val){
                this.selectedConsignee = val;
            },
            selectConsigneeConfirm: function(){
                if(this.selectedConsignee.id===undefined){
                    this.msgWarn("请点击一条数据");
                    return false;
                }
                this.ruleForm.consigneeId = this.selectedConsignee.id;
                this.ruleForm.consigneeName = this.selectedConsignee.name;
                this.ruleForm.consigneeTel = this.selectedConsignee.telephone;
                this.ruleForm.consigneeMobile = this.selectedConsignee.mobile;
                this.ruleForm.consigneeAddr = this.selectedConsignee.address;
                this.consigneeShow = false;
            },
            consigneeSearch: function(){
                this.getConsigneeData();
            },
            getConsigneeData: function(){
                this.$axios.post(this.urlConsignee, this.searchFormContacts).then((res) => {
                    if(res.data.code === '0' && res.data.total !== 0){
                        this.consigneeData = res.data.data.list;
                        this.consigneeTotal = res.data.data.total;
                    }
                });
            },
            //司机信息
            openDriverWin: function(){
                this.driverShow = true;
                this.getDriverData();
            },
            driverPageChange(val){
                this.consignorSearch.pageNum = val;
                this.getDriverData();
            },
            driverSearch: function(){

            },
            getDriverData(){
                this.$axios.post(this.urlVehicle, this.searchFormDriver).then((res) => {
                    if(res.data.code === '0' && res.data.total !== 0){
                        this.driverData = res.data.data.list;
                        this.driverTotal = res.data.data.total;
                    }
                });
            },
            selectDriver(val){
                this.selectedDriver = val;
            },
            selectDriverConfirm: function(){
                if(this.selectedDriver.id===undefined){
                    this.msgWarn("请点击一条数据");
                    return false;
                }
                this.ruleForm.vehicleId = this.selectedDriver.id;
                this.ruleForm.driverName = this.selectedDriver.driverName;
                this.ruleForm.vehicleNumber = this.selectedDriver.vehicleNumber;
                this.ruleForm.driverMobile = this.selectedDriver.driverMobile;
                this.driverShow = false;
            },
            submit:function () {
                this.$refs['ruleForm'].validate((valid) => {
                    if (valid) {
                        this.$axios.post(this.urlAdd, this.ruleForm).then((res) => {
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
