<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','store_name') || $check_field('add','store_name') || $check_field('set','store_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="店铺名称" prop="store_name">
					<el-input id="store_name" v-model="form['store_name']" placeholder="请输入店铺名称"
							  v-if="user_group === '管理员' || (form['good_food_shop_id'] && $check_field('set','store_name')) || (!form['good_food_shop_id'] && $check_field('add','store_name'))" :disabled="disabledObj['store_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','store_name')">{{form['store_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','food_type') || $check_field('add','food_type') || $check_field('set','food_type')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="美食类型" prop="food_type">
					<el-select id="food_type" v-model="form['food_type']"
						v-if="user_group === '管理员' || (form['good_food_shop_id'] && $check_field('set','food_type')) || (!form['good_food_shop_id'] && $check_field('add','food_type'))">
						<el-option v-for="o in list_food_type" :key="o['food_type']" :label="o['food_type']"
							:value="o['food_type']">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','food_type')">{{form['food_type']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','shop_facade') || $check_field('add','shop_facade') || $check_field('set','shop_facade')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="店铺门面" prop="shop_facade">
					<el-upload :disabled="disabledObj['shop_facade_isDisabled']" id="shop_facade" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_shop_facade"
						:show-file-list="false" v-if="user_group === '管理员' || (form['good_food_shop_id'] && $check_field('set','shop_facade')) || (!form['good_food_shop_id'] && $check_field('add','shop_facade'))">
						<img v-if="form['shop_facade']" :src="$fullUrl(form['shop_facade'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','shop_facade')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['shop_facade'])" :preview-src-list="[$fullUrl(form['shop_facade'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','store_address') || $check_field('add','store_address') || $check_field('set','store_address')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="店铺地址" prop="store_address">
					<el-input id="store_address" v-model="form['store_address']" placeholder="请输入店铺地址"
							  v-if="user_group === '管理员' || (form['good_food_shop_id'] && $check_field('set','store_address')) || (!form['good_food_shop_id'] && $check_field('add','store_address'))" :disabled="disabledObj['store_address_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','store_address')">{{form['store_address']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','hot_dishes') || $check_field('add','hot_dishes') || $check_field('set','hot_dishes')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="热推菜品" prop="hot_dishes">
					<el-input id="hot_dishes" v-model="form['hot_dishes']" placeholder="请输入热推菜品"
							  v-if="user_group === '管理员' || (form['good_food_shop_id'] && $check_field('set','hot_dishes')) || (!form['good_food_shop_id'] && $check_field('add','hot_dishes'))" :disabled="disabledObj['hot_dishes_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','hot_dishes')">{{form['hot_dishes']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','shop_details') || $check_field('add','shop_details') || $check_field('set','shop_details')" :xs="24" :sm="24" :lg="24" class="el_form_editor_warp">
				<el-form-item label="店铺详情" prop="shop_details">
					<quill-editor v-model.number="form['shop_details']"
						v-if="user_group === '管理员' || (form['good_food_shop_id'] && $check_field('set','shop_details')) || (!form['good_food_shop_id'] && $check_field('add','shop_details')) ">
					</quill-editor>
					<div v-else-if="$check_field('get','shop_details')" v-html="form['shop_details']"></div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "good_food_shop_id",
				url_add: "~/api/good_food_shop/add?",
				url_set: "~/api/good_food_shop/set?",
				url_get_obj: "~/api/good_food_shop/get_obj?",
				url_upload: "~/api/good_food_shop/upload?",

				query: {
					"good_food_shop_id": 0,
				},

				form: {
					"store_name":'', // 店铺名称
					"food_type":'', // 美食类型
					"shop_facade":'', // 店铺门面
					"store_address":'', // 店铺地址
					"hot_dishes":'', // 热推菜品
					"shop_details":'', // 店铺详情
					"good_food_shop_id": 0, // ID

				},
				disabledObj:{
					"store_name_isDisabled": false,
					"food_type_isDisabled": false,
					"shop_facade_isDisabled": false,
					"store_address_isDisabled": false,
					"hot_dishes_isDisabled": false,
					"shop_details_isDisabled": false,
				},
				//美食类型选项列表
				list_food_type: [],
			}
		},
		methods: {
			/**
			 * 获取美食类型列表
			 */
			async get_list_food_type() {
				var json = await this.$get("~/api/food_classification/get_list?");
				if(json.result && json.result.list){
					this.list_food_type = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			/**
			 * 上传店铺门面
			 * @param {Object} param图片参数
			 */
			upload_shop_facade(param){
				this.uploadFile(param.file, "shop_facade");
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							// if(dbKey === "charging_standard"){
							// 	this.form['charging_rules'] = form[dbKey];
							// 	this.disabledObj['charging_rules_isDisabled'] = true;
							// };
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){
			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/good_food_shop/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/good_food_shop/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/good_food_shop/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/good_food_shop/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/good_food_shop/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
			this.get_list_food_type();
		},
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}
</style>
