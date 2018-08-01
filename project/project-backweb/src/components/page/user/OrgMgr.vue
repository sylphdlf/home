<template>
    <div class="container">
        <div class="block">
            <p>银联中国</p>
            <el-tree :data="data5" show-checkbox node-key="id" default-expand-all :expand-on-click-node="false">
                <span class="custom-tree-node" slot-scope="{ node, data }">
                    <span>{{ node.label }}</span>
                    <span>
                        <el-button type="text" size="mini" @click="() => append(data)">新增</el-button>
                        <el-button type="text" size="mini" @click="() => remove(node, data)">删除</el-button>
                    </span>
                </span>
            </el-tree>
        </div>
    </div>
</template>
<script>
    let id = 1000;

    export default {
        data() {
            const data = [{
                id: 1,
                label: '银联上海' + '(YLSH)',
                children: [{
                    id: 4,
                    label: '银联浦东',
                    children: [{
                        id: 9,
                        label: '银联浦东张江'
                    }, {
                        id: 10,
                        label: '银联浦东唐镇'
                    }]
                }]
            }, {
                id: 2,
                label: '银联北京',
                children: [{
                    id: 5,
                    label: '银联海淀'
                }, {
                    id: 6,
                    label: '银联房山'
                }]
            }, {
                id: 3,
                label: '银联深圳',
                children: [{
                    id: 7,
                    label: '银联福田'
                }, {
                    id: 8,
                    label: '银联罗湖'
                }]
            }];
            return {
                data4: JSON.parse(JSON.stringify(data)),
                data5: JSON.parse(JSON.stringify(data))
            }
        },

        methods: {
            append(data) {
                const newChild = { id: id++, label: 'testtest', children: [] };
                if (!data.children) {
                    this.$set(data, 'children', []);
                }
                data.children.push(newChild);
            },

            remove(node, data) {
                const parent = node.parent;
                const children = parent.data.children || parent.data;
                const index = children.findIndex(d => d.id === data.id);
                children.splice(index, 1);
            },
        }
    };
</script>
<style>
    .custom-tree-node {
        flex: 1;
        display: flex;
        align-items: center;
        justify-content: space-between;
        font-size: 14px;
        padding-right: 8px;
    }
</style>


