<template>
    <div class="mindMap">
        <div class="menuDiv">
            <el-menu
                    default-active="2"
                    class="el-menu-vertical-demo"
                    @open="handleOpen"
                    @close="handleClose"
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
    </div>
</template>

<script>
    let mind = null;
    export default {
        name: "mindMapHome",
        data() {
            return {
                mindData: mind.getAllData(),
                mindDetail: [
                    {
                        id: '222-1',
                        parent: '111-1',
                        content: 'joiafwejoifhoaiwjefoijw'
                    },
                    {
                        id: '222-2',
                        parent: '111-2',
                        content: '噶玩儿法我饿阿尕王二狗'
                    },
                ]
            };
        },
        watch: {},
        computed: {},
        methods: {},
        mounted() {
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
                nodeMenu: true, // default true
                keypress: true, // default true
            })
            mind.init()

            // get a node
            //this.$mindMapE('node-id')

            let allData = mind.getAllData();
            console.log(allData)


            setInterval(function () {
                console.log(mind.getAllDataString());
            }, 10000)

            let grpList = document.querySelectorAll('grp');
            addEventListener('mouseover', function (e) {

            })


        }
    }
</script>

<style scoped lang="less">
    .mindMap {
        width: 100%;
        height: 100%;

        .menuDiv {
            float: left;
            width: 15%;
            height: 100%;

            .el-menu {
                width: 100%;
                height: 100%;
            }
        }

        .mapDiv {
            float: left;
            width: 85%;
            height: 100%;

            #map {
                width: 100%;
                height: 100%;
            }
        }
    }
</style>