import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import './assets/css/global.css'
import axios from 'axios'
import Editor from '@toast-ui/editor';
import 'codemirror/lib/codemirror.css'; // Editor's Dependency Style
import '@toast-ui/editor/dist/toastui-editor.css'; // Editor's Style
import _ from 'lodash'
import 'highlight.js/styles/github.css';
import codeSyntaxHighlight from '@toast-ui/editor-plugin-code-syntax-highlight';
import Client from 'ali-oss'
import MindElixir, { E } from 'mind-elixir'

Vue.prototype.$OOS = Client

Vue.prototype.$editor= Editor
Vue.prototype._ = _

// 设置axios跟路径
Vue.prototype.$http = axios
axios.defaults.baseURL = 'http://47.95.13.206:8080/'
// axios.defaults.headers = {'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'}
axios.defaults.headers = {'Content-Type': 'application/json;charset=utf-8'}
Vue.config.productionTip = false
//mindMap
Vue.prototype.$mindMap = MindElixir
Vue.prototype.$mindMapE = E

let vm = new Vue({
    router,
    render: h => h(App)
}).$mount('#app');
