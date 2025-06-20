<template>
  <div class="page_search">
	<div class="warp">
	  <div class="container">
		<div class="row">
		  <div class="col-12">
			<div class="card_result_search">
			  <div class="title">搜索结果</div>
			  <list_result_search
				v-if="$check_action('/food_classification/list', 'get')"
				:list="result_food_classification_food_type"
				title="美食分类美食类型"
				source_table="food_classification"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/gourmet_master/list', 'get')"
				:list="result_gourmet_master_name_of_talent"
				title="美食达人达人姓名"
				source_table="gourmet_master"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/good_food_shop/list', 'get')"
				:list="result_good_food_shop_store_name"
				title="美食好店店铺名称"
				source_table="good_food_shop"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/food_information/list', 'get')"
				:list="result_food_information_food_name"
				title="美食信息美食名称"
				source_table="food_information"
			  ></list_result_search>
			</div>
		  </div>
		</div>
	  </div>
	</div>
  </div>
</template>

<script>
import mixin from "../../mixins/page.js";
import list_result_search from "../../components/diy/list_result_search.vue";

export default {
  mixins: [mixin],
  data() {
	return {
	  "query": {
		word: "",
	  },
			"result_food_classification_food_type":[],
			"result_gourmet_master_name_of_talent":[],
			"result_good_food_shop_store_name":[],
			"result_food_information_food_name":[],
	};
  },
  methods: {
	/**
	 * 获取food_type
	 */
	get_food_classification_food_type(){
		this.$get("~/api/food_classification/get_list?like=0", { page: 1, size: 10, "food_type": this.query.word }, (json) => {
		  if (json.result) {
			var result_food_classification_food_type = json.result.list;
			result_food_classification_food_type.map(o => o.title = o['food_type'])
	  			this.result_food_classification_food_type = result_food_classification_food_type
		 	}
		});
	},
	/**
	 * 获取name_of_talent
	 */
	get_gourmet_master_name_of_talent(){
		this.$get("~/api/gourmet_master/get_list?like=0", { page: 1, size: 10, "name_of_talent": this.query.word }, (json) => {
		  if (json.result) {
			var result_gourmet_master_name_of_talent = json.result.list;
			result_gourmet_master_name_of_talent.map(o => o.title = o['name_of_talent'])
	  			this.result_gourmet_master_name_of_talent = result_gourmet_master_name_of_talent
		 	}
		});
	},
	/**
	 * 获取store_name
	 */
	get_good_food_shop_store_name(){
		this.$get("~/api/good_food_shop/get_list?like=0", { page: 1, size: 10, "store_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_good_food_shop_store_name = json.result.list;
			result_good_food_shop_store_name.map(o => o.title = o['store_name'])
	  			this.result_good_food_shop_store_name = result_good_food_shop_store_name
		 	}
		});
	},
	/**
	 * 获取food_name
	 */
	get_food_information_food_name(){
		this.$get("~/api/food_information/get_list?like=0", { page: 1, size: 10, "food_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_food_information_food_name = json.result.list;
			result_food_information_food_name.map(o => o.title = o['food_name'])
	  			this.result_food_information_food_name = result_food_information_food_name
		 	}
		});
	},

  },
  components: { list_result_search },
	created(){
    this.query.word = this.$route.query.word || "";
  },
  mounted() {
		this.get_food_classification_food_type();
		this.get_gourmet_master_name_of_talent();
		this.get_good_food_shop_store_name();
		this.get_food_information_food_name();
  },
  watch: {
	$route() {
	  $.push(this.query, this.$route.query);
	  this.get_food_classification_food_type();
	  this.get_gourmet_master_name_of_talent();
	  this.get_good_food_shop_store_name();
	  this.get_food_information_food_name();
	},
  },
};
</script>

<style scoped>
.card_search {
  text-align: center;
}
.card_result_search>.title {
  text-align: center;
  padding: 10px 0;
}
</style>
