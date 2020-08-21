<template>
    <div class="mindMap">
        <div class="menuDiv">
            <el-menu
                    default-active="2"
                    class="el-menu-vertical-demo"
                    background-color="#545c64"
                    text-color="#fff"
                    active-text-color="#ffd04b">
                <el-submenu index="1">
                    <template slot="title">
                        <i class="el-icon-location"></i>
                        <span>导航一</span>
                    </template>
                    <el-menu-item-group>
                        <template slot="title">分组一</template>
                        <el-menu-item index="1-1">选项1</el-menu-item>
                        <el-menu-item index="1-2">选项2</el-menu-item>
                    </el-menu-item-group>

                </el-submenu>
            </el-menu>
        </div>
        <div class="mapDiv">
            <div id="map"></div>
        </div>
        <div class="detailDiv"></div>

        <div id="editor1"></div>
    </div>
</template>

<script>
    import codeSyntaxHighlight from "@toast-ui/editor-plugin-code-syntax-highlight";

    let mind = null;
    let vueApp = null;
    let editor = null;
    export default {
        name: "mindMapHome",
        data() {
            return {
                //mindData: mind.getAllData(),
                mindDetail: {
                    '111-1': {
                        id: '222-1',
                        parent: '111-1',
                        content: '饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n饭哦按维持哦噶位置你爱我NB芝麻窝陈\n'
                    },
                    '111-2': {
                        id: '222-2',
                        parent: '111-2',
                        content: '噶玩儿法我饿阿尕王二狗'
                    },
                },
                drawer: true
            };
        },
        watch: {},
        computed: {},
        methods: {},
        mounted() {
            vueApp = this;
            mind = new this.$mindMap({
                el: '#map',
                direction: this.$mindMap.LEFT,
                // create new map data
                //data: this.$mindMap.new('new topic'),
                // or set as data that is return from `.getAllData()`
                data: {
                    nodeData: {
                        topic: '飞娃儿',
                        id: 'bd1c24420cd2c2f5',
                        root: true,
                        children: [
                            {
                                topic: '奇偶尘埃万恶戳',
                                id: '111-1',
                            },
                            {
                                topic: '奇偶尘埃万恶戳',
                                id: '111-2',
                            }
                        ]
                    },
                    linkData: {}
                },
                draggable: true, // default true
                contextMenu: true, // default true
                toolBar: true, // default true
                nodeMenu: true, // default truetop
                keypress: true, // default true
            })
            mind.init()

            document.querySelector('.map-canvas').addEventListener('mouseover', function (e) {
                // 调整详细信息的位置
                let target = e.target;
                let boundingClientRect = target.getBoundingClientRect();
                let detailDiv = document.querySelector('.detailDiv');
                detailDiv.style.left = boundingClientRect.right + 'px';
                detailDiv.style.top = boundingClientRect.top + 'px';

                if (target == this) return

                //调整信息内容
                let id = String(target.dataset.nodeid).substr(2);
                let content = ""
                if (vueApp.mindDetail[id] != undefined) {
                    content = vueApp.mindDetail[id].content;
                    editor.setMarkdown(content, false);
                    // detailDiv.innerText = content;
                    // detailDiv.style.backgroundColor = 'pink';
                } else {
                    detailDiv.style.left = '-99999px';
                    detailDiv.style.top = '-99999px';
                }


            })

            // 详细信息内容编辑器
            editor = new this.$editor({
                el: document.querySelector('.detailDiv'),
                height: '100%',
                initialEditType: 'markdown',
                previewStyle: 'vertical',
                initialValue: '',
                plugins: [codeSyntaxHighlight],
                events: {
                    change() {
                        let mdVal = editor.getMarkdown();
                    },
                }
            });

        }
    }
</script>

<style scoped lang="less">
    .mindMap {
        width: 100%;
        height: 100%;

        .menuDiv {
            float: left;
            width: 13%;
            height: 100%;

            .el-menu {
                width: 100%;
                height: 100%;
            }
        }

        .mapDiv {
            float: left;
            width: 87%;
            height: 100%;

            #map {
                width: 100%;
                height: 100%;
            }
        }

        .detailDiv {
            position: fixed;
            left: -9999px;
            border-radius: 5px;
            padding: 5px;
            width: 1000px;
        }

    }
</style>