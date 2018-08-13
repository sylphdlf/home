<template>
    <div class="header">
        <div class="collapse-btn" @click="collapseChage">
            <i class="el-icon-menu"></i>
        </div>
        <div class="logo">TMS管理系统</div>
        <div class="user-info">
            <el-dropdown trigger="click" @command="handleCommand">
                <span class="el-dropdown-link">
                    <img class="user-logo" src="../../../static/img/img.jpg">
                    {{username}}
                </span>
                <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item command="logout">退出登录</el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
        </div>
    </div>
</template>
<script>
    import bus from '../common/bus';
    export default {
        data() {
            return {
                logOutUrl:this.$projectUrl + '/login/logoutAjax',
                collapse: false,
                name: 'linxin'
            }
        },
        computed:{
            username(){
                let username = localStorage.getItem('ms_username');
                return username ? username : this.name;
            }
        },
        methods:{
            handleCommand(command) {
                if(command === 'logout'){
                    localStorage.removeItem('ms_username')
                    this.$axios.get(this.logOutUrl).then((res) => {
                        if(res.data.code === "0"){
                            this.$router.push('/login');
                        }else{
                            this.msgFail("登出出现问题")
                        }
                    });

                }
            },
            collapseChage(){
                this.collapse = !this.collapse;
                bus.$emit('collapse', this.collapse);
            }
        }
    }
</script>
<style scoped>
    .header {
        position: relative;
        box-sizing: border-box;
        width: 100%;
        height: 70px;
        font-size: 22px;
        line-height: 70px;
        color: #fff;
    }
    .collapse-btn{
        float: left;
        padding: 0 21px;
        cursor: pointer;
    }
    .collapse-btn:hover{
        background: rgb(40,52,70);
    }
    .header .logo{
        float: left;
        width:250px;
        /* text-align: center; */
    }
    .user-info {
        float: right;
        padding-right: 50px;
        font-size: 16px;
        color: #fff;
    }
    .user-info .el-dropdown-link{
        position: relative;
        display: inline-block;
        padding-left: 50px;
        color: #fff;
        cursor: pointer;
        vertical-align: middle;
    }
    .user-info .user-logo{
        position: absolute;
        left:0;
        top:15px;
        width:40px;
        height:40px;
        border-radius: 50%;
    }
    .el-dropdown-menu__item{
        text-align: center;
    }
</style>
