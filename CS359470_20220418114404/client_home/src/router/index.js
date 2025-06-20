import Vue from 'vue';
import VueRouter from 'vue-router';
import index from '../views/index.vue'
import login from '../views/account/login.vue';

Vue.use(VueRouter)

const routes = [
	// 主页ss
	{
		path: '/',
		name: 'index',
		component: index
	},
	// 登录
	{
		path: '/account/login',
		name: 'login',
		component: login
	},
	// 忘记密码
	{
		path: '/account/forgot',
		name: 'forgot',
		component: () => import('../views/account/forgot.vue')
	},
	// 注册账号
	{
		path: '/account/register',
		name: 'register',
		component: () => import('../views/account/register.vue')
	},
	// 媒体图片
	{
		path: '/media/image',
		name: 'media_image',
		component: () => import('../views/media/image.vue')
	},
	// 音乐
	{
		path: '/media/music',
		name: 'media_music',
		component: () => import('../views/media/music.vue')
	},
	// 媒体视频
	{
		path: '/media/video',
		name: 'media_video',
		component: () => import('../views/media/video.vue')
	},
	// 浏览网站
	// 收藏路由
	{
		path: '/user/collect',
		name: 'collect_list',
		component: () => import('../views/user/collect.vue')
	},
	// 公告路由
	{
		path: '/notice/list',
		name: 'notice_list',
		component: () => import('../views/notice/list.vue')
	},
	{
		path: '/notice/details',
		name: 'notice_details',
		component: () => import('../views/notice/details.vue')
	},
	//美食达人列表路由
	{
		path: '/gourmet_master/list',
		name: '/gourmet_master_list',
		component: () => import('../views/gourmet_master/list.vue')
	},
	//美食达人详情路由
	{
		path: '/gourmet_master/details',
		name: '/gourmet_master_details',
		component: () => import('../views/gourmet_master/details.vue')
	},
	//美食好店列表路由
	{
		path: '/good_food_shop/list',
		name: '/good_food_shop_list',
		component: () => import('../views/good_food_shop/list.vue')
	},
	//美食好店详情路由
	{
		path: '/good_food_shop/details',
		name: '/good_food_shop_details',
		component: () => import('../views/good_food_shop/details.vue')
	},
	//美食信息列表路由
	{
		path: '/food_information/list',
		name: '/food_information_list',
		component: () => import('../views/food_information/list.vue')
	},
	//美食信息详情路由
	{
		path: '/food_information/details',
		name: '/food_information_details',
		component: () => import('../views/food_information/details.vue')
	},
	//美食提问列表路由
	{
		path: '/food_questions/list',
		name: '/food_questions_list',
		component: () => import('../views/food_questions/list.vue')
	},
	//美食提问详情路由
	{
		path: '/food_questions/details',
		name: '/food_questions_details',
		component: () => import('../views/food_questions/details.vue')
	},

	// 用户路由
	{
		path: '/user/index',
		name: 'user_index',
		component: () => import('../views/user/index.vue')
	},
	// 基本信息
	{
		path: '/user/info',
		name: 'user_info',
		component: () => import('../views/user/info.vue')
	},
	// 找回密码
	{
		path: '/user/password',
		name: 'user_password',
		component: () => import('../views/user/password.vue')
	},

	// 搜索
	{
		path: '/search',
		name: 'search',
		component: () => import('../views/search/index.vue')
	},
	// 局部搜索
	{
		path: '/search/details',
		name: 'search_details',
		component: () => import('../views/search/details.vue')
	}
]

const router = new VueRouter({
	mode: 'history',
	base: process.env.BASE_URL,
	routes
})

router.afterEach((to, from, next) => {
	let title = "城院美食交流网站-home";
	document.title = title;
	document.logo = "城院美食交流网站"
})

export default router
