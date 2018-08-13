import Vue from 'vue';
import App from './App';
import router from './router';
import axios from 'axios';
import moment from "moment";
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';    // 默认主题
// import '../static/css/theme-green/index.css';       // 浅绿色主题
import "babel-polyfill";

Vue.use(ElementUI, { size: 'small' });
Vue.prototype.$moment = moment;
Vue.prototype.$axios = axios;
Vue.prototype.$projectUrl = "/project-web";
//日期格式化
Vue.prototype.formatDate = function(date){
    return this.$moment(date).format("YYYY-MM-DD HH:mm:ss");
};
//日期过滤器
Vue.filter("dateTimeFilter", (date)=>{
    return moment(date).format("YYYY-MM-DD HH:mm:ss");
});
//顶部通知
Vue.prototype.msgSuccess = function(msg){
    if(msg === "" || msg === undefined){
        this.$message({
            message: '成功',
            type: 'success'
        });
    }else{
        this.$message({
            message: msg,
            type: 'success'
        });
    }
};
Vue.prototype.msgFail = function(msg){
    if(msg === "" || msg === undefined){
        this.$message({
            message: '失败',
            type: 'error'
        });
    }else{
        this.$message({
            message: msg,
            type: 'error'
        });
    }
};
//使用钩子函数对路由进行权限跳转
router.beforeEach((to, from, next) => {
    if(to.meta.permission){
        const role = localStorage.getItem('ms_username');
        // 如果是管理员权限则可进入，这里只是简单的模拟管理员权限而已
        role === '13761053109' ? next() : next('/login');
    }else{
        // 简单的判断IE10及以下不进入富文本编辑器，该组件不兼容
        if(navigator.userAgent.indexOf('MSIE') > -1 && to.path === '/editor'){
            Vue.prototype.$alert('vue-quill-editor组件不兼容IE10及以下浏览器，请使用更高版本的浏览器查看', '浏览器不兼容通知', {
                confirmButtonText: '确定'
            });
        }else{
            next();
        }
    }
})

new Vue({
    router,
    render: h => h(App)
}).$mount('#app');

//拦截返回值，判断是否登录
axios.interceptors.response.use(response => {
    //未登录
    if(response.data.code === "-2"){
        router.push('/login');
    }
    return response
}, error => {
    return Promise.resolve(error.response)
});
