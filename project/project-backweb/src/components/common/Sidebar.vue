<template>
    <div class="sidebar">
        <el-menu class="sidebar-el-menu" :default-active="onRoutes" :collapse="collapse" background-color="#324157"
            text-color="#bfcbd9" active-text-color="#20a0ff" unique-opened router>
            <template v-for="item in items">
                <template v-if="item.subs">
                    <el-submenu :index="item.index" :key="item.index">
                        <template slot="title">
                            <i :class="item.icon"></i><span slot="title">{{ item.title }}</span>
                        </template>
                        <el-menu-item v-for="(subItem,i) in item.subs" :key="i" :index="subItem.index">
                            {{ subItem.title }}
                        </el-menu-item>
                    </el-submenu>
                </template>
                <template v-else>
                    <el-menu-item :index="item.index" :key="item.index">
                        <i :class="item.icon"></i><span slot="title">{{ item.title }}</span>
                    </el-menu-item>
                </template>
            </template>
        </el-menu>
    </div>
</template>

<script>
    import bus from '../common/bus';
    export default {
        data() {
            return {
                collapse: false,
                items: [
                    // {
                    //     icon: 'el-icon-setting',
                    //     index: 'readme',
                    //     title: '自述文件'
                    // },
                    // {
                    //     icon: 'el-icon-tickets',
                    //     index: 'dashboard',
                    //     title: 'dashboard',
                    //     subs:[
                    //         {
                    //             index: 'weatherChart',
                    //             title: '天气'
                    //         },
                    //         {
                    //             index: 'trafficChart',
                    //             title: '交通'
                    //         },
                    //     ]
                    // },
                    {
                        icon: 'el-icon-tickets',
                        index: 'user',
                        title: '用户管理',
                        subs: [
                            {
                                index: 'userMgr',
                                title: '用户列表'
                            },
                            {
                                index: 'orgMgr',
                                title: '组织管理'
                            },
                            {
                                index: 'roleMgr',
                                title: '角色管理'
                            },
                            {
                                index: 'funMgr',
                                title: '权限管理'
                            },
                            {
                                index: 'userWatchMgr',
                                title: '在线用户管理'
                            }
                        ]
                    },
                    // {
                    //     icon: 'el-icon-tickets',
                    //     index: 'info',
                    //     title: '基础信息管理',
                    //     subs: [
                    //         {
                    //             index: 'contactsMgr',
                    //             title: '联系人管理'
                    //         },
                    //         {
                    //             index: 'vehicleMgr',
                    //             title: '车辆管理'
                    //         }
                    //     ]
                    // },
                    // {
                    //     icon: 'el-icon-tickets',
                    //     index: 'tms',
                    //     title: '物流管理',
                    //     subs: [
                    //         {
                    //             index: 'consignMgr',
                    //             title: '托运单管理'
                    //         },
                    //         {
                    //             index: 'pickUpMgr',
                    //             title: '上门提货'
                    //         },
                    //         {
                    //             index: 'sendoutMgr',
                    //             title: '外发管理'
                    //         },
                    //         {
                    //             index: 'vehicleTrackMgr',
                    //             title: '车辆跟踪'
                    //         },
                    //         {
                    //             index: 'stockMgr',
                    //             title: '库存管理'
                    //         },
                    //         // {
                    //         //     index: 'funMgr',
                    //         //     title: '送货上门(undo)'
                    //         // },
                    //         // {
                    //         //     index: 'funMgr',
                    //         //     title: '自提(undo)'
                    //         // },
                    //         // {
                    //         //     index: 'funMgr',
                    //         //     title: '到站确认(undo)'
                    //         // }
                    //     ]
                    // },
                    {
                        icon: 'el-icon-tickets',
                        index: 'file',
                        title: '文件管理',
                        subs: [
                            {
                                index: 'fileMgr',
                                title: '文件列表'
                            },
                        ]
                    },
                    // {
                    //     icon: 'el-icon-tickets',
                    //     index: '2',
                    //     title: '常用表格',
                    //     subs: [
                    //         {
                    //             index: 'table',
                    //             title: '基础表格'
                    //         },
                    //         {
                    //             index: 'datasource',
                    //             title: 'datasource表格'
                    //         }
                    //     ]
                    // },
                    // {
                    //     icon: 'el-icon-date',
                    //     index: '3',
                    //     title: '表单相关',
                    //     subs: [
                    //         {
                    //             index: 'form',
                    //             title: '基本表单'
                    //         },
                    //         {
                    //             index: 'editor',
                    //             title: '富文本编辑器'
                    //         },
                    //         {
                    //             index: 'markdown',
                    //             title: 'markdown编辑器'
                    //         },
                    //         {
                    //             index: 'upload',
                    //             title: '文件上传'
                    //         }
                    //     ]
                    // },
                    // {
                    //     icon: 'el-icon-rank',
                    //     index: 'drag',
                    //     title: '拖拽列表'
                    // },
                    // {
                    //     icon: 'el-icon-warning',
                    //     index: 'permission',
                    //     title: '权限测试'
                    // }
                    {
                        icon: 'el-icon-tickets',
                        index: 'stock',
                        title: '监控',
                        subs: [
                            {
                                index: 'stockListMgr',
                                title: '列表'
                            },
                        ]
                    }
                ]
            }
        },
        computed:{
            onRoutes(){
                return this.$route.path.replace('/','');
            }
        },
        created(){
            // 通过 Event Bus 进行组件间通信，来折叠侧边栏
            bus.$on('collapse', msg => {
                this.collapse = msg;
            })
        }
    }
</script>

<style scoped>
    .sidebar{
        display: block;
        position: absolute;
        left: 0;
        top: 70px;
        bottom:0;
    }
    .sidebar-el-menu:not(.el-menu--collapse){
        width: 250px;
    }
    .sidebar > ul {
        height:100%;
    }
</style>
