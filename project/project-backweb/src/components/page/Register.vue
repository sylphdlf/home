<template>
    <div class="login-wrap">
        <div class="ms-title">用户注册</div>
        <div class="ms-login">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="0px" class="demo-ruleForm">
                <el-form-item prop="username">
                    <el-input v-model="ruleForm.username" placeholder="用户名"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input type="password" placeholder="密码" v-model="ruleForm.password" @keyup.enter.native="submitForm('ruleForm')"></el-input>
                </el-form-item>
                <el-form-item prop="passwordRepeat">
                    <el-input type="password" placeholder="请再次输入密码" v-model="ruleForm.passwordRepeat" @keyup.enter.native="submitForm('ruleForm')"></el-input>
                </el-form-item>
                <el-form-item prop="realName">
                    <el-input v-model="ruleForm.realName" placeholder="真实姓名"></el-input>
                </el-form-item>
                <el-form-item prop="mobile">
                    <el-input v-model="ruleForm.mobile" placeholder="手机号码"></el-input>
                </el-form-item>
                <el-form-item prop="telephone">
                    <el-input v-model="ruleForm.telephone" placeholder="座机"></el-input>
                </el-form-item>
                <el-form-item prop="gender">
                    <el-select v-model="ruleForm.gender" placeholder="性别">
                        <el-option key="1" label="男" value="1"></el-option>
                        <el-option key="2" label="女" value="2"></el-option>
                        <el-option key="3" label="不知道" value="3"></el-option>
                    </el-select>
                </el-form-item>
                <!--<el-form-item prop="orgCode">-->
                    <!--<el-select v-model="ruleForm.orgCode" placeholder="组织机构">-->
                        <!--<el-option key="1" label="银联总部" value="1"></el-option>-->
                        <!--<el-option key="2" label="银联上海" value="2"></el-option>-->
                    <!--</el-select>-->
                <!--</el-form-item>-->
                <div class="login-btn">
                    <el-button type="primary" @click="submitForm('ruleForm')">注册并登录</el-button>
                </div>
                <p style="font-size:12px;line-height:30px;color:#999;">或者&nbsp;<a style="color: blue;cursor: pointer" @click="redirect('login')">去登录</a></p>
                <p style="font-size:12px;line-height:30px;color:red;">{{messageShow.error}}</p>
            </el-form>
        </div>
    </div>
</template>

<script>
    export default {
        data: function(){
            return {
                ruleForm: {
                    username: '13761053109',
                    password: '13761053109',
                    passwordRepeat: '',
                    realName: '',
                    gender: '',
                    mobile: '',
                    telephone: '',
                    orgCode: '',
                },
                messageShow: {
                    error: '',
                },
                rules: {
                    username: [
                        { required: true, message: '请输入用户名', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: '请输入密码', trigger: 'blur' }
                    ],
                    password_repeat: [
                        { required: true, message: '2次输入的密码不一样', trigger: 'blur' }
                    ]
                }
            }
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        localStorage.setItem('ms_username',this.ruleForm.username);
                        // this.$router.push('/readme');
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
                this.$axios.post("/project-web/register/registerAjax", this.ruleForm).then(result =>{
                    if(result.data.code === "0"){
                        //直接登录
                        this.$axios.post("/project-web/login/loginAjax", this.ruleForm).then(result =>{
                            if(result.data.code === "0"){
                                this.$router.push('/weatherChart');
                            }else{
                                this.$router.push('/login');
                            }
                        });
                    }else{
                        this.messageShow.error = result.data.msg;
                        return false;
                    }
                });
            },
            redirect(name){
                this.$router.push('/' + name);
            }
        }
    }
</script>

<style scoped>
    .login-wrap{
        position: relative;
        width:100%;
        height:100%;
    }
    .ms-title{
        position: absolute;
        top:5%;
        width:100%;
        /*margin-top: -400px;*/
        text-align: center;
        font-size:30px;
        color: #fff;

    }
    .ms-login{
        position: absolute;
        left:50%;
        top:30%;
        width:300px;
        height:500px;
        margin:-150px 0 0 -190px;
        padding:40px;
        border-radius: 5px;
        background: #fff;
    }
    .login-btn{
        text-align: center;
    }
    .login-btn button{
        width:100%;
        height:36px;
    }
</style>
