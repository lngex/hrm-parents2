<template>
	<section>
		<!--工具条-->
		<el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
			<el-form :model="filters" :inline="true">
				<el-form-item>
					<el-input v-model="filters.keywords" placeholder="关键字" ></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="warning" v-on:click="getCourses">查询</el-button>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click="addHandler" >新增</el-button>
				</el-form-item>
				<el-form-item>
					<el-button type="success" @click="onLineCourse" >上线</el-button>
				</el-form-item>
				<el-form-item>
					<el-button type="danger" @click="offLineCourse" >下线</el-button>
				</el-form-item>
				<el-form-item>
					<el-button type="info" @click="uploadResources" >上传资源</el-button>
				</el-form-item>
			</el-form>
		</el-col>

		<!--列表v-loading="listLoading"-->
		<el-table @row-click="rowClick" :data="courses" v-loading="listLoading"
				  highlight-current-row  style="width: 100%;"
				  @selection-change="handleSelectionChange"
		>
			<!--多选框-->
			<el-table-column type="selection" width="55">
			</el-table-column>
			<!--索引值,为什么不用id,id不序号-->
			<el-table-column type="index" width="60">
			</el-table-column>
			<!--其他都设置值,只有一个不设置值就自动适应了-->
			<el-table-column prop="name" label="课程名称">
			</el-table-column>
			<!--<el-table-column prop="courseType.name" label="类型">-->
			<!--</el-table-column>-->
			<el-table-column prop="gradeName" label="等级">
			</el-table-column>
			<el-table-column prop="status" label="状态" :formatter="statusFormatter">
			</el-table-column>
			<el-table-column prop="forUser" label="适用人群">
			</el-table-column>
			<!--<el-table-column prop="tenantName" label="所属机构">-->
			<!--</el-table-column>-->
			<el-table-column prop="userName" label="创建用户">
			</el-table-column>
			<el-table-column prop="startTime" label="开课时间">
			</el-table-column>
			<el-table-column prop="endTime" label="结课时间">
			</el-table-column>
			<el-table-column label="操作" width="200">
				<template scope="scope">
					<el-button size="small"  @click="edit(scope.row)">编辑</el-button>
					<el-button type="danger" size="small" @click="del(scope.row)">删除</el-button>
				</template>
			</el-table-column>
		</el-table>
		<!--工具条-->
		<el-col :span="24" class="toolbar">
			<el-button type="danger" @click="batchDel()">批量删除</el-button>
			<el-pagination layout="prev, pager, next" @current-change="handleCurrentChange"  :page-size="10" :total="total" style="float:right;">
			</el-pagination>
		</el-col>

		<!--新增界面-->
		<el-dialog title="新增" :visible.sync="addFormVisible"  :close-on-click-modal="false">
			<el-form :model="addForm" label-width="80px"  ref="addForm">
				<el-form-item label="课程名称" prop="name">
					<el-input v-model="addForm.name" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="适用人群" prop="forUser">
					<el-input v-model="addForm.forUser" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="课程等级" prop="gradeId">
					<el-radio-group v-model="addForm.gradeId">
						<el-radio v-for="grade in grades" :label="grade.id">{{grade.name}}</el-radio>
					</el-radio-group>
				</el-form-item>

				<el-form-item label="开课日期" >
					<el-date-picker
							v-model="addForm.startTime"
							type="date"
							value-format="yyyy-MM-dd"
							size="small"
							placeholder="课程开始日期">
					</el-date-picker>
				</el-form-item>
				<el-form-item label="节课日期" >
					<el-date-picker
							v-model="addForm.endTime"
							type="date"
							value-format="yyyy-MM-dd"
							size="small"
							placeholder="课程结束日期">
					</el-date-picker>
				</el-form-item>


				<el-form-item label="收费规则" prop="gradeId">
					<el-radio-group v-model="addForm.chargeId">
						<el-radio @change="changeCharge" v-for="charge in charges" :label="charge.id">{{charge.name}}</el-radio>
					</el-radio-group>
				</el-form-item>
				<el-form-item label="课程价格" prop="price">
					<el-input :disabled="priceDisabled" v-model="addForm.price" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="课程原价">
					<el-input :disabled="priceDisabled" v-model="addForm.priceOld" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="咨询QQ" prop="qq">
					<el-input v-model="addForm.qq" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="结束时间" >
					<el-date-picker
							v-model="addForm.expires"
							type="date"
							value-format="yyyy-MM-dd"
							size="small"
							placeholder="营销结束时间">
					</el-date-picker>
				</el-form-item>


				<el-form-item label="课程类型" prop="coursetTypId">
					<el-cascader
							:props="courseTypeProps"
							v-model="addForm.courseTypeId"
							placeholder="试试搜索：指南"
							:options="courseTypes"
							expand-trigger="hover"
							:show-all-levels="false"
							filterable
							change-on-select
					></el-cascader>
				</el-form-item>

				<el-form-item prop="pic" label="封面">
					<!--<el-input type="text" v-model="employee.logo" auto-complete="off" placeholder="请输入logo！"></el-input>-->
					<el-upload
							class="upload-demo"
							action="https://lngex.oss-cn-beijing.aliyuncs.com"
							:data="uploadData"
							:before-upload="beforeUpload"
							:on-success="handleSuccess"
							:on-remove="handleRemove"
							:file-list="fileList"
							list-type="picture">
						<el-button size="small" type="primary">点击上传</el-button>
						<div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
					</el-upload>

				</el-form-item>


				<el-form-item label="课程简介" prop="description">
					<el-input
							type="textarea"
							:rows="2"
							placeholder="请输入内容"
							v-model="addForm.description">
					</el-input>
				</el-form-item>

				<el-form-item label="课程详情" prop="intro">
					<div class="edit_container">
						<quill-editor
								v-model="addForm.intro"
								ref="myQuillEditor"
								class="editer"
								:options="editorOption"
								@ready="onEditorReady($event)">
						</quill-editor>
					</div>
				</el-form-item>


			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click.native="addFormVisible = false">取消</el-button>
				<el-button type="primary" @click.native="addSubmit" >提交</el-button>
			</div>
		</el-dialog>
	</section>
</template>

<script>
    import { quillEditor } from "vue-quill-editor"; //调用编辑器
    import "quill/dist/quill.core.css"
	import "quill/dist/quill.snow.css"
	import "quill/dist/quill.bubble.css"

	export default {
        computed: {
			editor() {
                return this.$refs.myQuillEditor.quill
            }
		},
        components: {//使用编辑器
            quillEditor
        },
		data() {
			return {
				ids:[],
				fileList:null,
				filename:null,
				uploadData: {  //提交到OSS的参数
					policy: '',
					signature: '',
					key: '',
					ossaccessKeyId: '',
					dir: '',
					host: ''
				},
                row:"",
                courseTypeProps:{
                    value:"id",
                    label:"name"
				},
                priceDisabled:true,
                editorOption: {},//富文本编辑框配置
			    grades:[
			    		{"id":1 , "name":"青铜"},
						{"id":2 , "name":"白银"},
						{"id":3 , "name":"黄金"},
						{"id":4 , "name":"铂金"},

				],
                charges:[
					{"id":1 , "name":"免费"},
					{"id":2 , "name":"收费"}
				],
				courseTypes:[],
                addFormVisible:false,
				//images:[xxx.jgp,xxxx,jpg,xxxx.jpg],
				addForm:{
                    startTime:'',
                    endTime:'',
                    expires:'',
                    name:'',
                    forUser:'',
                    gradeId:'',
                    courseTypeId:'',
                    description:'',
                    intro:'',
                    chargeId:'',
                    price:'',
                    priceOld:'',
                    qq:'',
                    pic:'',
				},
                listLoading:false,
				//查询对象
				filters:{
					keywords:''
				},
				page:1,//当前页,要传递到后台的
				total:0, //分页总数
			    courses:[], //当前页数据
			}
		},
		methods: {
			batchDel(){
				if(!this.ids[0]){
					this.$message({ message: '老铁，你不选中数据，臣妾上不了啊....',type: 'error'});
				}
				var ids = this.ids.map(item => item.id)
				alert("sda")
				this.$http.post("/course/course/batchdel",ids).then(res=>{
					res=res.data
					if(res.success){
						this.$message({
							message: '删除成功',
							type: 'success'
						});
						this.getDepartments();
					}else {
						this.$message({
							message: '删除失败',
							type: 'error'
						});
					}
				})
			},
			handleSelectionChange(val){
				this.ids = val;
			},
			async beforeUpload(){
				await this.$http.get("/oss/alioss/up").then(response=>{
					//设置相关的参数
					var resultObj = response.data.resultObj;
					this.uploadData.policy = resultObj.policy;

					this.uploadData.signature = resultObj.signature;
					this.uploadData.ossaccessKeyId = resultObj.accessid;
					//上传的文件名，使用UUID处理一下
					this.uploadData.key = resultObj.dir + '/'+this.getUUID()+'_${filename}';
					this.uploadData.dir = resultObj.dir;
					this.uploadData.host = resultObj.host;
				});
			},
			handleSuccess(res, file) {
				// this.fileList.pop();
				//上传的完整的文件地址
				var urlPath = this.uploadData.host + '/' + this.uploadData.key.replace("${filename}",file.name) ;
				this.addForm.pic = urlPath;
				this.$message({message: '上传成功，图片地址：'+this.addForm.pic, type: 'success' });
			},
			handleRemove(file, fileList) {
				console.log(file)
				let index = this.addForm.pic.lastIndexOf("/")
				let images = this.addForm.pic.substring(index+1)
				this.filename={name:images}
				this.$http.post("/oss/alioss/del",this.filename).then(request=>{
					request=request.data
					if(request.success){
						this.this.addForm.pic = null;
					}
				})

			},
			getUUID() {
				var s = [];
				var hexDigits = "0123456789abcdef";
				for (var i = 0; i < 36; i++) {
					s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
				}
				s[14] = "4"; // bits 12-15 of the time_hi_and_version field to 0010
				s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1); // bits 6-7 of the clock_seq_hi_and_reserved to 01
				s[8] = s[13] = s[18] = s[23] = "-";
				var uuid = s.join("");
				return uuid;
			},
            /*handleSuccess(response, file, fileList){
                if(response.success){
                    this.addForm.pic = response.resultObj;
                }else{
                    this.$message({
                        message: '上传失败!',
                        type: 'error'
                    });
                }
            },*/
            addSubmit(){
                this.addForm.courseTypeId = this.addForm.courseTypeId[this.addForm.courseTypeId.length - 1];
				/**

                chargeId: 2
                description: "1111"
                gradeId: 1
                intro: "<p>111<img src="data:image/jpeg;base64,/9j/4AAQSkZ"
                price: "1111"
                qq: "111"
                forUser: "1"
				 **/
				var gradeName;
				for(var i = 0 ; i < this.grades.length ; i++){
                    var grade = this.grades[i];
                    if(grade.id === this.addForm.gradeId){
                        gradeName = grade.name;
                        break;
					}
				}

                var param = {
                    course:{
                        courseTypeId:this.addForm.courseTypeId,
                        name:this.addForm.name,
                        forUser:this.addForm.forUser,
                        gradeId:this.addForm.gradeId,
                        gradeName:gradeName,
                        pic:this.addForm.pic,
                        startTime:this.addForm.startTime,
                        endTime:this.addForm.endTime
					},
                	courseDetail:{
                        description:this.addForm.description,
                        intro:this.addForm.intro
					},
                	courseMarket:{
                        charge:this.addForm.chargeId,
                        qq:this.addForm.qq,
                        price:this.addForm.price,
                        priceOld:this.addForm.priceOld,
                        expires:this.addForm.expires
					}
				};

                this.$http.post("/course/course/save",param).then(res=>{
                    var ajaxResult = res.data;
                    if(ajaxResult.success){
                        this.$message({
                            message: '保存成功!',
                            type: 'success'
                        });
                        this.addFormVisible = false;
                        this.getCourses();
                    }else{
                        this.$message({
                            message: '上传失败!',
                            type: 'error'
                        });
                    }
				});
			},
			getGrades(){
              this.$http.get("/system/systemdictionaryitem/listBySn/dj").then(result=>{
                  this.grades = result.data.resultObj;
              });
			},
            getCourseTypes(){
              this.$http.get("/course/courseType").then(result=>{
                  this.courseTypes = result.data.resultObj;
              });
			},
            changeCharge(chargeId){
                console.log(chargeId);
                if(chargeId === 1){
                    this.priceDisabled = true;
                    this.addForm.price = "";
                    this.addForm.priceOld = "";
				}else{
                    this.priceDisabled = false;
				}
			},
            onEditorReady(editor) {
                //当富文本编辑框初始化好执行
            },
            addHandler(){
				this.addFormVisible = true;
			},
            handleCurrentChange(curentPage){
                this.page = curentPage;
                this.getCourses();
			},
            getCourses(){
                //发送Ajax请求后台获取数据  axios
				//添加分页条件及高级查询条件
				let para = {
				    "page":this.page,
					"keyword":this.filters.keywords
				};
				this.listLoading = true; //显示加载圈
				//分页查询
                this.$http.post("/course/course/list",para) //$.Post(.....)
                    .then(result=>{
                        this.total = result.data.total;
                        this.courses = result.data.rows;
                        this.listLoading = false;  //关闭加载圈
                    });
			},
            onLineCourse(){

            	if(!this.ids[0]){
					this.$message({ message: '老铁，你不选中数据，臣妾上不了啊....',type: 'error'});
				}
            	var ids = this.ids.map(item => item.id)
            	console.log(this.ids)
				this.$http.post("/course/course/onLineCourse",ids).then(res=> {
					var ajaxResult = res.data;
					if (ajaxResult.success) {
						this.$message({message: '老铁，上线成功.', type: 'success'});
						this.getCourses();
					} else {
						this.$message({message: ajaxResult.message, type: 'error'});
					}
					//获取选中的行
					/*if(!this.row || this.row  === ""){
                    this.$message({ message: '老铁，你不选中数据，臣妾上不了啊....',type: 'error'});
				    return;
				}

				this.$http.post("/course/course/onLineCourse/"+this.row.id).then(res=>{
				    var ajaxResult = res.data;
				    if(ajaxResult.success){
                        this.$message({ message: '老铁，上线成功.',type: 'success'});
                        this.getCourses();
					}else{
                        this.$message({ message: ajaxResult.message,type: 'error'});
					}
				})*/
				})
			},
            offLineCourse(){
                //获取选中的行
				if(!this.ids[0]){
					this.$message({ message: '老铁，你不选中数据，臣妾上不了啊....',type: 'error'});
				}
				var ids = this.ids.map(item => item.id)
                this.$http.post("/course/course/offLineCourse",ids).then(res=>{
                    var ajaxResult = res.data;
                    if(ajaxResult.success){
                        this.$message({ message: '老铁，下线成功.',type: 'success'});
                        this.getCourses();
                    }else{
                        this.$message({ message: ajaxResult.message,type: 'error'});
                    }
                })
			},
            rowClick(row){
				this.row = row;
				console.log(this.row);
			},
            //性别显示转换
            statusFormatter: function (row, column) {
                return row.status == 1 ? '已上线' : '未上线';
            },
		},
		mounted() {
		    this.getCourses();
		    this.getGrades();
		    this.getCourseTypes();
		}
	}

</script>

<style scoped>

</style>