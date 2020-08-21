<template>
    <div>
        <el-container>
            <el-aside class="aside-nav">
                <el-menu
                        :unique-opened="true"
                        :isCollapse="true"
                        default-active="2"
                        class="el-menu-vertical-demo"
                        background-color="#707b86"
                        text-color="#fff"
                        active-text-color="#67C23A">

                    <!--一级菜单-->
                    <el-submenu :index="item.id + ''" v-for="item in navData.data" :key="item.id">
                        <template slot="title">
                            <span slot="title">{{item.name}}</span>
                            <div class="deleteCategory el-icon-delete"
                                 @click.stop.prevent="deleteCategory(item.id)"></div>
                        </template>
                        <!--二级菜单-->
                        <el-submenu :index="subItem.id + ''" v-for="subItem in item.children" :key="subItem.id">
                            <template slot="title">
                                <span slot="title">{{subItem.name}}</span>
                                <div class="deleteCategory el-icon-delete"
                                     @click.stop.prevent="deleteCategory(subItem.id)"></div>
                            </template>
                            <!--三级菜单-->
                            <el-submenu :index="subSubItem.id + ''" v-for="subSubItem in subItem.children"
                                        :key="subSubItem.id">
                                <template slot="title">
                                    <span slot="title">{{subSubItem.name}}</span>
                                    <div class="deleteCategory el-icon-delete"
                                         @click.stop.prevent="deleteCategory(subSubItem.id)"></div>
                                </template>
                                <!--四级菜单（文章标题）-->
                                <el-menu-item @click="getAssay(subSubSubItem.id)"
                                              v-for="subSubSubItem in subSubItem.children">{{subSubSubItem.name}}
                                    <div class="deleteCategory deleteAssay el-icon-delete"
                                         @click.stop.prevent="deleteAssay(subSubSubItem.id)"></div>
                                </el-menu-item>
                                <el-menu-item><input @blur="addAssay(subSubItem.id, categoryName4, $event)"
                                                     class="categoryName"
                                                     type="text" v-model="categoryName4" placeholder="add">
                                </el-menu-item>
                            </el-submenu>
                            <el-menu-item><input @blur="addCategory(subItem.id, categoryName3, $event)"
                                                 class="categoryName"
                                                 type="text" v-model="categoryName3" placeholder="add"></el-menu-item>
                        </el-submenu>
                        <el-menu-item><input @blur="addCategory(item.id, categoryName2, $event)" class="categoryName"
                                             type="text"
                                             v-model="categoryName2" placeholder="add"></el-menu-item>
                    </el-submenu>
                    <el-menu-item><input @blur="addCategory(0, categoryName1, $event)" class="categoryName" type="text"
                                         v-model="categoryName1" id="000" placeholder="add"></el-menu-item>
                </el-menu>
            </el-aside>
            <el-main>
                <div id="editor"></div>
            </el-main>
        </el-container>
    </div>
</template>
<script>
    import codeSyntaxHighlight from '@toast-ui/editor-plugin-code-syntax-highlight';

    let editor;
    let vueApp;
    export default {
        name: "BlogHome",
        data() {
            return {
                navData: {},
                mdDirectoryEditor: [],
                defaultProps: {
                    children: 'children',
                    label: 'label'
                },
                blogAssay: "",
                blogId: "",
                blog: {
                    content: this.blogAssay,
                },
                blogCategory: {},
                categoryName1: "",
                categoryName2: "",
                categoryName3: "",
                categoryName4: "",
                isChangAssay: false,
            }
        },
        methods: {
            async getAssay(id) {
                // 切换文章时获取文章id
                let ids = id.split("-");
                id = ids[ids.length - 1];
                this.blogId = id;
                // 是否切换了文章
                if (id == this.lastAssayId) return;
                this.isChangAssay = true;
                let {data: res} = await this.$http.get("blogAssay/selectOne?id=" + id);
                editor.setMarkdown(res.content, false);


                // this.isSameId = 1;
                // this.lastAssayId = id;
            },
            updateBlog: function () {
                if (this.isChangAssay) {  // 切换了文章
                    this.isChangAssay = false;
                } else {  // 没有切换文章
                    this.blog.id = this.blogId;
                    console.log('updateId' + this.blogId)
                    this.blog.content = this.blogAssay;
                    this.$http.post("blogAssay/update", this.blog)
                }
            },
            async addCategory(pid, categoryName, event) {
                if (categoryName == '') return this.$message.error("别搞事情，不能添加为空的分类！")
                let newVar = await this.$messageBox.confirm('确认添加此分类？')
                    .catch(() => {
                        this.resetCategoryName();
                        return
                    })
                if (newVar !== 'confirm') {
                    this.resetCategoryName();
                    return
                }
                this.blogCategory.uid = sessionStorage.getItem('uid');
                this.blogCategory.pid = pid;
                this.blogCategory.name = categoryName;
                let {data: res} = await this.$http.post("blogCategory/insertCategory", this.blogCategory)
                console.log(res)

                this.$message.success("添加成功")
                await this.findAllCategoryAndAssayTitle();
                this.resetCategoryName();
            },
            resetCategoryName() {
                this.categoryName1 = "";
                this.categoryName2 = "";
                this.categoryName3 = "";
                this.categoryName4 = "";
            },
            async findAllCategoryAndAssayTitle() {
                let promise = this.$http.get("blogCategory/findAllCategoryAndAssayTitle?uid=" + sessionStorage.getItem("uid"));
                promise.then(res => {
                    this.navData = res.data;
                })
            },
            async addAssay(pid, categoryName, event) {
                if (categoryName == '') return this.$message.error("不能新增标题为空的文章！")
                let newVar = await this.$messageBox.confirm('确认添加此文章？')
                    .catch(() => {
                        this.resetCategoryName();
                        return
                    })
                if (newVar !== 'confirm') {
                    this.resetCategoryName();
                    return
                }
                this.blogCategory.cid = pid;
                this.blogCategory.title = categoryName;
                let {data: res} = await this.$http.post("blogAssay/insertAssay", this.blogCategory)
                console.log(res)

                this.$message.success("添加成功")
                await this.findAllCategoryAndAssayTitle();
                this.resetCategoryName();

            },
            async deleteCategory(id) {
                let newVar = await this.$messageBox.confirm('确认删除该目录，这将删除该目录及其下所有文章！！！')
                    .catch(() => {
                        return
                    })
                if (newVar !== 'confirm') {
                    return
                }
                let newVar1 = await this.$messageBox.confirm('再次确认：删除该目录，将删除该目录及其下所有文章！！！')
                    .catch(() => {
                        return
                    })
                if (newVar1 !== 'confirm') {
                    return
                }
                let {data: res} = await this.$http.get("blogCategory/deleteCategory?id=" + id)
                this.$message.success("删除成功")
                await this.findAllCategoryAndAssayTitle();
                this.resetCategoryName();
            }
        },
        watch: {
            blogAssay: function (newA, oldA) {
                this.debouncedGetBlogAssay()
            }
        },
        created: function () {
            this.debouncedGetBlogAssay = _.debounce(this.updateBlog, 500)
        },
        mounted() {
            vueApp = this;
            editor = new this.$editor({
                el: document.querySelector('#editor'),
                height: '100%',
                initialEditType: 'markdown',
                previewStyle: 'vertical',
                initialValue: '',
                plugins: [codeSyntaxHighlight],
                events: {
                    change() {
                        let mdVal = editor.getMarkdown();
                        vueApp.blogAssay = mdVal;
                    },
                }
            });
            this.findAllCategoryAndAssayTitle();
        },
    }

</script>

<style lang="less" scoped>
    .aside-nav {
        width: 250px;
        height: 100%;
        background-color: #707b86;
    }

    .el-container {
        height: 100%;
        /*background-color: skyblue;*/
    }

    .el-aside {
    }

    .el-main {
        padding: 0;
        height: 100%;
    }

    .el-main > div {
        height: 100%;
    }

    .el-menu {
        border-right: none !important;
    }

    .el-tree {
        background-color: #3ab57e;
        color: #575757;
    }

    .el-icon-circle-plus {
        width: 100%;
        color: #dddddd !important;
    }

    .categoryName {
        box-sizing: border-box;
        width: 100%;
        height: 30px;
        background-color: #707b86;
        outline: none;
        border: 0;
        color: #dddddd;
    }

    .categoryName::-webkit-input-placeholder {
        /* WebKit browsers */
        color: #ccc;
    }

    .deleteCategory {
        position: relative;
        display: inline-block;
        width: 10px;
        height: 10px;
        border-radius: 50%;
        float: right;
        right: 25px;
        top: 22px;
    }

    .deleteAssay.deleteCategory {
        right: 0;
    }

</style>