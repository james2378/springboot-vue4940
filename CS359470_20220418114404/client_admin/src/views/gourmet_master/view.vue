<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','name_of_talent') || $check_field('add','name_of_talent') || $check_field('set','name_of_talent')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="达人姓名" prop="name_of_talent">
					<el-input id="name_of_talent" v-model="form['name_of_talent']" placeholder="请输入达人姓名"
							  v-if="user_group === '管理员' || (form['gourmet_master_id'] && $check_field('set','name_of_talent')) || (!form['gourmet_master_id'] && $check_field('add','name_of_talent'))" :disabled="disabledObj['name_of_talent_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','name_of_talent')">{{form['name_of_talent']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','avatar_of_talent') || $check_field('add','avatar_of_talent') || $check_field('set','avatar_of_talent')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="达人头像" prop="avatar_of_talent">
					<el-upload :disabled="disabledObj['avatar_of_talent_isDisabled']" id="avatar_of_talent" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_avatar_of_talent"
						:show-file-list="false" v-if="user_group === '管理员' || (form['gourmet_master_id'] && $check_field('set','avatar_of_talent')) || (!form['gourmet_master_id'] && $check_field('add','avatar_of_talent'))">
						<img v-if="form['avatar_of_talent']" :src="$fullUrl(form['avatar_of_talent'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','avatar_of_talent')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['avatar_of_talent'])" :preview-src-list="[$fullUrl(form['avatar_of_talent'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','talent_address') || $check_field('add','talent_address') || $check_field('set','talent_address')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="达人地址" prop="talent_address">
					<el-input id="talent_address" v-model="form['talent_address']" placeholder="请输入达人地址"
							  v-if="user_group === '管理员' || (form['gourmet_master_id'] && $check_field('set','talent_address')) || (!form['gourmet_master_id'] && $check_field('add','talent_address'))" :disabled="disabledObj['talent_address_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','talent_address')">{{form['talent_address']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','specialty_cuisine') || $check_field('add','specialty_cuisine') || $check_field('set','specialty_cuisine')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="拿手菜系" prop="specialty_cuisine">
					<el-input id="specialty_cuisine" v-model="form['specialty_cuisine']" placeholder="请输入拿手菜系"
							  v-if="user_group === '管理员' || (form['gourmet_master_id'] && $check_field('set','specialty_cuisine')) || (!form['gourmet_master_id'] && $check_field('add','specialty_cuisine'))" :disabled="disabledObj['specialty_cuisine_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','specialty_cuisine')">{{form['specialty_cuisine']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','introduction_to_talent') || $check_field('add','introduction_to_talent') || $check_field('set','introduction_to_talent')" :xs="24" :sm="24" :lg="24" class="el_form_editor_warp">
				<el-form-item label="达人简介" prop="introduction_to_talent">
					<quill-editor v-model.number="form['introduction_to_talent']"
						v-if="user_group === '管理员' || (form['gourmet_master_id'] && $check_field('set','introduction_to_talent')) || (!form['gourmet_master_id'] && $check_field('add','introduction_to_talent')) ">
					</quill-editor>
					<div v-else-if="$check_field('get','introduction_to_talent')" v-html="form['introduction_to_talent']"></div>
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
				field: "gourmet_master_id",
				url_add: "~/api/gourmet_master/add?",
				url_set: "~/api/gourmet_master/set?",
				url_get_obj: "~/api/gourmet_master/get_obj?",
				url_upload: "~/api/gourmet_master/upload?",

				query: {
					"gourmet_master_id": 0,
				},

				form: {
					"name_of_talent":'', // 达人姓名
					"avatar_of_talent":'', // 达人头像
					"talent_address":'', // 达人地址
					"specialty_cuisine":'', // 拿手菜系
					"introduction_to_talent":'', // 达人简介
					"gourmet_master_id": 0, // ID

				},
				disabledObj:{
					"name_of_talent_isDisabled": false,
					"avatar_of_talent_isDisabled": false,
					"talent_address_isDisabled": false,
					"specialty_cuisine_isDisabled": false,
					"introduction_to_talent_isDisabled": false,
				},
			}
		},
		methods: {
			/**
			 * 上传达人头像
			 * @param {Object} param图片参数
			 */
			upload_avatar_of_talent(param){
				this.uploadFile(param.file, "avatar_of_talent");
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
					bl = this.$check_action('/gourmet_master/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/gourmet_master/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/gourmet_master/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/gourmet_master/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/gourmet_master/view','get');
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
