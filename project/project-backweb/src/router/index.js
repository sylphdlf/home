import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            redirect: '/login'
        },
        {
            path: '/readme',
            component: resolve => require(['../components/common/Home.vue'], resolve),
            children:[
                {
                    path: '/',
                    component: resolve => require(['../components/page/dashboard/WeatherChart_bak.vue'], resolve)
                },
                {
                    path: '/userMgr',
                    component: resolve => require(['../components/page/user/UserMgr.vue'], resolve)
                },
                {
                    path: '/orgMgr',
                    component: resolve => require(['../components/page/user/OrgMgr_tree.vue'], resolve)
                },
                {
                    path: '/roleMgr',
                    component: resolve => require(['../components/page/user/RoleMgr.vue'], resolve)
                },
                {
                    path: '/funMgr',
                    component: resolve => require(['../components/page/user/FunMgr.vue'], resolve)
                },
                //用户监控
                {
                    path: '/userWatchMgr',
                    component: resolve => require(['../components/page/user/UserWatchMgr.vue'], resolve)
                },
                //联系人管理
                {
                    path: '/contactsMgr',
                    component: resolve => require(['../components/page/tms/ContactsMgr.vue'], resolve)
                },
                //车辆管理
                {
                    path: '/vehicleMgr',
                    component: resolve => require(['../components/page/tms/VehicleMgr.vue'], resolve)
                },
                //物流管理
                {
                    path: '/consignMgr',
                    component: resolve => require(['../components/page/tms/ConsignMgr.vue'], resolve)
                },
                //物流管理-新增
                {
                    path: '/consignMgr_add',
                    component: resolve => require(['../components/page/tms/ConsignMgr_add.vue'], resolve)
                },
                {
                    path: '/consignMgr_add2',
                    component: resolve => require(['../components/page/tms/ConsignMgr_add2.vue'], resolve)
                },
                //上门提货
                {
                    path: '/pickUpMgr',
                    component: resolve => require(['../components/page/tms/PickUpMgr.vue'], resolve)
                },
                {
                    path: '/pickUpMgr_add',
                    component: resolve => require(['../components/page/tms/PickUpMgr_add.vue'], resolve)
                },
                //外发管理
                {
                    path: '/sendoutMgr',
                    component: resolve => require(['../components/page/tms/SendoutMgr.vue'], resolve)
                },
                {
                    path: '/sendoutMgr_local',
                    component: resolve => require(['../components/page/tms/SendoutMgr_local.vue'], resolve)
                },
                {
                    path: '/sendoutMgr_third',
                    component: resolve => require(['../components/page/tms/SendoutMgr_third.vue'], resolve)
                },
                //车辆跟踪
                {
                    path: '/vehicleTrackMgr',
                    component: resolve => require(['../components/page/tms/VehicleTrackMgr.vue'], resolve)
                },
                //库存管理
                {
                    path: '/stockMgr',
                    component: resolve => require(['../components/page/tms/StockMgr.vue'], resolve)
                },


                {
                    path: '/stockListMgr',
                    component: resolve => require(['../components/page/stock/StockListMgr.vue'], resolve)
                },
                {
                    path: '/table',
                    component: resolve => require(['../components/page/BaseTable.vue'], resolve)
                },
                {
                    // vue-datasource组件
                    path: '/datasource',
                    component: resolve => require(['../components/page/VueTable.vue'], resolve)
                },
                {
                    path: '/form',
                    component: resolve => require(['../components/page/BaseForm.vue'], resolve)
                },
                {
                    // 富文本编辑器组件
                    path: '/editor',
                    component: resolve => require(['../components/page/VueEditor.vue'], resolve)
                },
                {
                    // markdown组件
                    path: '/markdown',
                    component: resolve => require(['../components/page/Markdown.vue'], resolve)
                },
                {
                    // 图片上传组件
                    path: '/fileMgr',
                    component: resolve => require(['../components/page/file/FileMgr.vue'], resolve)
                },
                {
                    // vue-schart组件
                    path: '/weatherChart',
                    component: resolve => require(['../components/page/dashboard/WeatherChart.vue'], resolve)
                },
                {
                    // vue-schart组件
                    path: '/trafficChart',
                    component: resolve => require(['../components/page/dashboard/TrafficChart.vue'], resolve)
                },
                {
                    // 拖拽列表组件
                    path: '/drag',
                    component: resolve => require(['../components/page/DragList.vue'], resolve)
                },
                {
                    // 权限页面
                    path: '/permission',
                    component: resolve => require(['../components/page/Permission.vue'], resolve),
                    meta: {permission: true}
                }
            ]
        },
        {
            path: '/login',
            component: resolve => require(['../components/page/Login.vue'], resolve)
        },
        {
            path: '/register',
            component: resolve => require(['../components/page/Register.vue'], resolve)
        },
    ]
})
