/**
 * Created by dailf on 2017/7/18.
 */
var app = new Vue({
    el: '#app',
    data: {
        message: 'Hello Vue!'
    }
});
var app2 = new Vue({
    el: '#app-2',
    data: {
        message: '页面加载于 ' + new Date()
    }
});
var app3 = new Vue({
    el: '#app-3',
    data: {
        seen: false
    }
});
var app4 = new Vue({
    el: '#app-4',
    data: {
        todos: [
            { text: '1111' },
            { text: '2222' },
            { text: '3333' }
        ]
    }
});
var app5 = new Vue({
    el: '#app-5',
    data: {
        message: 'Hello Vue.js!'
    },
    methods: {
        reverseMessage: function () {
            this.message = this.message.split('').reverse().join('')
        }
    }
});
var app6 = new Vue({
    el: '#app-6',
    data: {
        message: 'Hello Vue!'
    }
});
// 定义名为 todo-item 的新组件
Vue.component('todo-item1', {
    template: '<li>这是个待办项</li>'
});
var app65 = new Vue({
    el: '#app-65'
});
Vue.component('todo-item', {
    props: ['todo'],
    template: '<li>{{ todo.text }}</li>'
})
var app7 = new Vue({
    el: '#app-7',
    data: {
        groceryList: [
            { id: 0, text: '蔬菜' },
            { id: 1, text: '奶酪' },
            { id: 2, text: '随便其他什么人吃的东西' }
        ]
    }
});
var vm = new Vue({
    data: {
        a: 1
    },
    created: function () {
        // `this` 指向 vm 实例
        console.log('a is: ' + this.a)
    }
});