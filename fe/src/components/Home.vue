<template>
    <div>
        <el-container>
            <!--页面头部导航-->
            <el-header class="main-header">
                <el-menu :default-active="activeIndex2"
                         class="el-menu-demo header-menu"
                         mode="horizontal"
                         @select="handleSelect"
                         background-color="#545c64"
                         text-color="#fff"
                         active-text-color="#67C23A">
                    <el-menu-item index="1" class="header-logo">
                        <img src="../assets/logo.png" alt="">
                    </el-menu-item>
                    <el-menu-item index="2" @click="blogHome">BLOG</el-menu-item>
                    <el-menu-item index="3" @click="todoHome">TIME</el-menu-item>
                    <el-menu-item index="5" @click="mindMapHome">MINDMAP</el-menu-item>
                    <el-menu-item index="4" @click="updImg">UPLOADIMAGE</el-menu-item>
                    <el-menu-item index="14" class="logout"><a href="javascript:void(0)" @click="logout">LOGOUT</a>
                    </el-menu-item>
                </el-menu>
            </el-header>
            <!--页面主体-->
            <el-main>
                <router-view></router-view>
            </el-main>
            <div class="updImg" v-bind:style="{display: showUpdImg}">
                <el-upload
                        :on-change="updImgToServer"
                        class="upload-demo"
                        drag
                        action=""
                >
                    <i class="el-icon-upload"></i>
                    <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                    <div class="el-upload__tip" slot="tip">
                        <div class="copyImgLink" @click="copyImgLink()" @mouseover="resetImgLinkInfo">
                            <el-tooltip class="item" effect="dark" :content="tooltipMsg" placement="top">
                                <span id="showUpdInfo">{{showUpdInfo}}</span>
                            </el-tooltip>
                        </div>
                        <i @click.stop.prevent="concle()" class="el-icon-close concelUpdImg"></i>
                    </div>
                </el-upload>
            </div>
        </el-container>
    </div>
</template>

<script>
    let vueApp;
    export default {
        name: "Home",
        data() {
            return {
                tooltipMsg: '点击复制图片链接',
                showUpdImg: 'none',
                showUpdInfo: '只能上传jpg/png文件，且不超过500kb',
                activeIndex: '1',
                activeIndex2: '2',
            }
        },
        mounted() {
            vueApp = this;
        },
        methods: {
            resetImgLinkInfo(){
                this.tooltipMsg = '点击复制图片链接'
            },
            copyImgLink(){
                var copyText = document.getElementById("showUpdInfo");
                var textArea = document.createElement("textarea");
                textArea.style.display = "none";
                textArea.value = copyText.textContent;
                document.body.appendChild(textArea);
                textArea.select();
                document.execCommand("Copy");
                this.tooltipMsg = '复制成功'
            },
            updImgToServer(file) {
                let OSS = require('ali-oss');
                let client = new OSS({
                    region: 'oss-cn-beijing',
                    //云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用STS方式来进行API访问
                    accessKeyId: 'LTAI4GHhJZGgDPZ1PbB6ZQoG',
                    accessKeySecret: 'UH2vEgtuYQFiB42hVnd9cCpzUGHynh',
                    bucket: 'jylife'
                });

                async function putObject() {
                    try {
                        // object-key可以自定义为文件名（例如file.txt）或目录（例如abc/test/file.txt）的形式，实现将文件上传至当前Bucket或Bucket下的指定目录。
                        let result = await client.put(new Date().valueOf() + file.name, file.raw);
                        vueApp.showUpdInfo = result.url;
                    } catch (e) {
                        console.log(e);
                    }
                }

                putObject();

                this.showUpdInfo = file.name
            },
            concle() {
                this.showUpdImg = 'none'
            },
            handleSelect(key, keyPath) {
            },
            logout() {
                sessionStorage.removeItem('token');
                this.$router.push('/login')
            },
            blogHome() {
                this.$router.push('/blogHome');
            },
            todoHome() {
                this.$router.push('/todoHome');
            },
            updImg() {
                this.showUpdImg = 'block';
            },
            mindMapHome() {
                this.$router.push('/mindMapHome');
            }

        }
    }
</script>

<style lang="less" scoped>
    .el-upload__tip {
        margin-top: -5px;
        height: 20px;
        background-color: #fff;
        cursor: pointer;
    }

    .concelUpdImg {
        position: absolute;
        left: 330px;
        top: 10px;
        width: 30px;
        height: 30px;
        cursor: pointer;
        font-size: 20px;
    }

    .updImg {
        position: fixed;
        left: 300px;
        top: 40px;
        z-index: 999;
    }

    .el-header {
        padding: 0;
        height: 40px !important;
    }

    .el-menu {
        height: 40px;
    }

    .main-header .el-menu li {
        height: 40px;
        line-height: 40px;
    }

    .header-logo {
        height: 40px;

        img {
            height: 40px;
            border-radius: 50%;
        }
    }

    .logout {
        float: right !important;
    }

    .el-main {
        padding: 0;
        height: 100%;
    }

    .el-container {
        height: 100%;
    }

    #app > div,
    .el-main > div {
        height: 100%;
    }
</style>