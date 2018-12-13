<template>
    <div class="table">
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-lx-cascades"></i> 任务管理</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-form ref="form" :model="search_params" label-width="80px">
                    <el-row>
                        <el-col :span="6">
                            <el-form-item label="任务名称:">
                                <el-input v-model="search_params.taskName" placeholder="输入任务名/代号" ></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item label="任务描述:">
                                <el-input v-model="search_params.taskDesc" placeholder="输入任务描述" ></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-button type="primary" icon="search" @click="search">搜索</el-button>
                        </el-col>
                    </el-row>

                    <el-row>
                        <el-col :span="4">
                            <!--<el-button type="primary" icon="search" @click="batchDelete">批量删除</el-button>-->
                        </el-col>
                        <el-col :span="4">

                        </el-col>
                        <el-col >
                            <el-button type="primary" icon="search" @click="handleAdd">新增</el-button>
                        </el-col>

                    </el-row>

                </el-form>
            </div>
            <el-table :data="tableList" border class="table" ref="multipleTable" @selection-change="handleSelectionChange">
                <el-table-column type="selection" width="55" align="center"></el-table-column>
                <el-table-column prop="id" label="主键" v-if="is_show" width="150px">
                </el-table-column>
                <el-table-column prop="taskName" label="任务名称" sortable width="150px">
                </el-table-column>
                <el-table-column prop="costTime" label="任务用时" width="100px">
                </el-table-column>
                <el-table-column prop="createDate" label="创建时间" width="300px">
                </el-table-column>
                <el-table-column prop="savePath" label="保存路径" >
                </el-table-column>
                <el-table-column prop="task_desc" label="说明" >
                </el-table-column>
                <el-table-column label="操作" width="330" align="center">
                    <template slot-scope="scope">
                        <el-button type="text" icon="el-icon-delete" class="red" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                    </template>
                    <template slot-scope="scope">
                        <el-button type="text" icon="el-icon-delete" class="red" @click="handleDelete(scope.$index, scope.row)">开始</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="pagination">
                <el-pagination background @current-change="handleCurrentChange"  :current-page="pageNum" :page-size="pageSize" layout="prev, pager, next" :total="total">
                </el-pagination>
            </div>
        </div>


        <!-- 新增模拟弹出框 -->
        <el-dialog title="新增URL参数" :visible.sync="addVisible" width="40%">
            <el-form ref="form" :model="addForm" label-width="70px">
                <el-form-item label="任务名称">
                    <el-input v-model="addForm.taskName" disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="保存路径">
                    <el-input v-model="addForm.savePath"></el-input>
                </el-form-item>
                <el-form-item label="说明">
                    <el-input type="textarea" v-model="addForm.task_desc"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="addVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveAdd">确 定</el-button>
            </span>
        </el-dialog>

        <!-- 删除提示框 -->
        <el-dialog title="提示" :visible.sync="delVisible" width="300px" center>
            <div class="del-dialog-cnt">删除不可恢复，是否确定删除？</div>
            <span slot="footer" class="dialog-footer">
                <el-button @click="delVisible = false">取 消</el-button>
                <el-button type="primary" @click="deleteRow">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>

    import {postRequest} from '../../utils/api'
    import {getRequest} from '../../utils/api'
    import {putRequest} from '../../utils/api'
    import {deleteRequest} from '../../utils/api'


    export default {
        name: 'basetable',
        data() {
            return {
                url: './static/vuetable.json',
                tableList: [],
                pageSize: 10,
                pageNum: 1,
                multipleSelection: [],
                select_cate: '',
                search_params: {},
                select_word: '',
                del_list: [],
                is_show: false,
                is_search: false,
                editVisible: false,
                delVisible: false,
                addVisible:false,
                total: 0,
                addForm: {
                 },
                editForm: {
                },
                idx: -1
            }
        },
        computed: {
            data() {
                return this.tableData.filter((d) => {
                    let is_del = false;
                    for (let i = 0; i < this.del_list.length; i++) {
                        if (d.name === this.del_list[i].name) {
                            is_del = true;
                            break;
                        }
                    }
                    if (!is_del) {
                        if (d.address.indexOf(this.select_cate) > -1 &&
                            (d.name.indexOf(this.select_word) > -1 ||
                                d.address.indexOf(this.select_word) > -1)
                        ) {
                            return d;
                        }
                    }
                })
            }
        },
        methods: {
            // 分页导航
            handleCurrentChange(val) {
                this.pageNum = val;
                this.getData();
            },
            // 获取 列表
            getData() {
                this.search_params.pageSize = this.pageSize
                this.search_params.pageNum = this.pageNum
                getRequest("/comic/task/queryList", this.search_params).then((res) => {
                    this.tableList = res.data.list
                    this.total = res.data.totalRecords
                    console.log(this.tableList)
                }).catch(function (error) {
                    this.$notify.error({
                        title: '获取失败',
                        message: error.response.data.message
                    });
                });
            },
            search() {
                this.is_search = true;
                this.getData();
            },
            formatter(row, column) {
                return row.address;
            },
            filterTag(value, row) {
                return row.tag === value;
            },
            handleAdd(index, row) {
                this.addVisible = true;
            },
            handleDelete(index, row) {
                this.idx = index;
                this.delVisible = true;
            },
            delAll() {
                const length = this.multipleSelection.length;
                let str = '';
                this.del_list = this.del_list.concat(this.multipleSelection);
                for (let i = 0; i < length; i++) {
                    str += this.multipleSelection[i].name + ' ';
                }
                this.$message.error('删除了' + str);
                this.multipleSelection = [];
            },
            handleSelectionChange(val) {
                this.multipleSelection = val;
            },

            // 保存编辑
            saveAdd() {
                console.log(this.addForm);
                postRequest("/comic/task/saveTask",this.addForm).then((res) => {
                    this.$message.success(`添加任务 ${this.idx+1} 成功`);
                    this.addVisible = false;
                    this.getData();
                });
                this.addForm.savePath = null;
                this.addForm.taskDesc = null;
            },
            // 确定删除
            deleteRow(){
                const url = "/comic/task/deleteTask/" + this.tableList[this.idx].id;
                deleteRequest(url).then((res) => {
                    this.$message.success('删除成功');
                    this.delVisible = false;
                    this.getData();
                })
            }
        }
    }

</script>

<style scoped>
    .handle-box {
        margin-bottom: 20px;
    }

    .handle-select {
        width: 120px;
    }

    .handle-input {
        width: 300px;
        display: inline-block;
    }
    .del-dialog-cnt{
        font-size: 16px;
        text-align: center
    }
    .table{
        width: 100%;
        font-size: 14px;
    }
    .red{
        color: #ff0000;
    }
</style>
