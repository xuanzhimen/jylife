import Vue from 'vue'
import VueRouter from 'vue-router'
// import Home from '../views/Home.vue'
import Login from "../components/Login";
import Home from "../components/Home";
import BlogHome from "../components/blog/BlogHome";
import TodoHome from "../components/Todo/TodoHome";
import BlogEditor from "../components/blog/BlogEditor";
import MindMapHome from "../components/mind/MindMapHome";

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'index',
        redirect: Login
    },
    {
        path: '/login',
        name: 'Login',
        component: Login
    },
    {
        path: '/home',
        name: 'Home',
        component: Home,
        redirect: '/blogHome',
        children: [
            {
                path: '/blogHome',
                name: 'BlogHome',
                component: BlogHome,
                redirect: '/blogEditor',
                children: [
                    {
                        path: '/blogEditor',
                        name: 'BlogEditor',
                        component: BlogEditor,
                    }
                ]
            },
            {
                path: '/todoHome',
                name: 'TodoHome',
                component: TodoHome
            },
            {
                path: '/mindMapHome',
                name: 'MindMapHome',
                component: MindMapHome
            }
        ]
    },
    /*
    {
        path: '/about',
        name: 'About',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/!* webpackChunkName: "about" *!/ '../views/About.vue')
    }
    */
]

const router = new VueRouter({
    routes
})

//路由导航守卫
router.beforeEach((to, from, next) => {
    if (to.path === '/login') return next()
    if (!sessionStorage.getItem('uid')) return next('/login');
    next()
})

export default router
