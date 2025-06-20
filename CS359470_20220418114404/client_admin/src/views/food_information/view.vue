<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','food_name') || $check_field('add','food_name') || $check_field('set','food_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="美食名称" prop="food_name">
					<el-input id="food_name" v-model="form['food_name']" placeholder="请输入美食名称"
							  v-if="user_group === '管理员' || (form['food_information_id'] && $check_field('set','food_name')) || (!form['food_information_id'] && $check_field('add','food_name'))" :disabled="disabledObj['food_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','food_name')">{{form['food_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','food_type') || $check_field('add','food_type') || $check_field('set','food_type')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="美食类型" prop="food_type">
					<el-select id="food_type" v-model="form['food_type']"
						v-if="user_group === '管理员' || (form['food_information_id'] && $check_field('set','food_type')) || (!form['food_information_id'] && $check_field('add','food_type'))">
						<el-option v-for="o in list_food_type" :key="o['food_type']" :label="o['food_type']"
							:value="o['food_type']">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','food_type')">{{form['food_type']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','gourmet_cover') || $check_field('add','gourmet_cover') || $check_field('set','gourmet_cover')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="美食封面" prop="gourmet_cover">
					<el-upload :disabled="disabledObj['gourmet_cover_isDisabled']" id="gourmet_cover" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_gourmet_cover"
						:show-file-list="false" v-if="user_group === '管理员' || (form['food_information_id'] && $check_field('set','gourmet_cover')) || (!form['food_information_id'] && $check_field('add','gourmet_cover'))">
						<img v-if="form['gourmet_cover']" :src="$fullUrl(form['gourmet_cover'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','gourmet_cover')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['gourmet_cover'])" :preview-src-list="[$fullUrl(form['gourmet_cover'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','food_details') || $check_field('add','food_details') || $check_field('set','food_details')" :xs="24" :sm="24" :lg="24" class="el_form_editor_warp">
				<el-form-item label="美食详情" prop="food_details">
					<quill-editor v-model.number="form['food_details']"
						v-if="user_group === '管理员' || (form['food_information_id'] && $check_field('set','food_details')) || (!form['food_information_id'] && $check_field('add','food_details')) ">
					</quill-editor>
					<div v-else-if="$check_field('get','food_details')" v-html="form['food_details']"></div>
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
				field: "food_information_id",
				url_add: "~/api/food_information/add?",
				url_set: "~/api/food_information/set?",
				url_get_obj: "~/api/food_information/get_obj?",
				url_upload: "~/api/food_information/upload?",

				query: {
					"food_information_id": 0,
				},

				form: {
					"food_name":'', // 美食名称
					"food_type":'', // 美食类型
					"gourmet_cover":'', // 美食封面
					"food_details":'', // 美食详情
					"food_information_id": 0, // ID

				},
				disabledObj:{
					"food_name_isDisabled": false,
					"food_type_isDisabled": false,
					"gourmet_cover_isDisabled": false,
					"food_details_isDisabled": false,
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
			 * 上传美食封面
			 * @param {Object} param图片参数
			 */
			upload_gourmet_cover(param){
				this.uploadFile(param.file, "gourmet_cover");
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
					bl = this.$check_action('/food_information/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/food_information/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/food_information/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/food_information/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/food_information/view','get');
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
