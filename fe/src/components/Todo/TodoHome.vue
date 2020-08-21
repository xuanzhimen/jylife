<template>
    <div class="vueApp">
        <el-row class="el-row-center" :gutter="20">
            <el-col :span="6">
                <div class="grid-content bg-purple">
                    <div class="actionTitle">
                        <el-tag @click="getAllUnfinishTodoTask()" size="medium" type="">活动清单</el-tag>&nbsp;&nbsp;
                        <el-button size="mini" @click="getAllTodoTask()">查看所有清单</el-button>
                    </div>
                    <div class="block actionList">
                        <el-timeline>
                            <el-timeline-item
                                    v-for="(item,index) in todoTaskList"
                                    :color="item.actualTime ? '#0bbd87' : '#F56C6C'"
                                    :icon="item.actualTime ? 'el-icon-check' : ((getDay(item.expectedTime, new Date().getTime())  >= 0) ? 'el-icon-warning-outline' : 'el-icon-caret-right')"
                                    :timestamp="getTime(item.makeTime)"
                                    placement="top">
                                <el-card shadow="hover" :body-style="{ padding: '15px' }">
                                    <div class="todoItem">
                                        <!--文字说明区-->
                                        <div>
                                            <h4>{{item.name}}</h4>
                                            <!--完成任务-->
                                            <div v-if="item.actualTime">
                                                <!--延期完成-->
                                                <div v-if="getDay(item.expectedTime, item.actualTime) >= 0">
                                                    <p class="afterTimeFinish">延期 {{getDay(item.expectedTime,
                                                        item.actualTime) + 1}} 天完成</p>
                                                </div>
                                                <!--按时或提前完成-->
                                                <div v-else>
                                                    <p class="onTimeFinish"
                                                       v-if="getDay(item.expectedTime, item.actualTime) >= -1">按时完成</p>
                                                    <p class="beforeTimeFinish" v-else>
                                                        提前：{{Math.abs(getDay(item.expectedTime, item.actualTime)) -
                                                        1}}天完成</p>
                                                </div>
                                            </div>
                                            <!--任务正在进行-->
                                            <div v-else>
                                                <div v-if="getDay(item.expectedTime, new Date().getTime())  >= 0">
                                                    <p class="planLaterTimeFinish">已延期 {{getDay(item.expectedTime,
                                                        new Date().getTime()) + 1}} 天!</p>
                                                </div>
                                                <div v-else>
                                                    <p class="planTimeFinish">计划完成日期：{{getDate(item.expectedTime)}}</p>
                                                </div>
                                            </div>
                                        </div>
                                        <!--操作图标区-->
                                        <div class="optBtns">
                                            <!--添加活动到今日待办-->
                                            <el-button type="warning" icon="el-icon-plus" circle
                                                       size="mini" @click="addTaskToTodo(index, item.id)"></el-button>
                                            <!--已完成活动-->
                                            <el-button type="success" icon="el-icon-check" circle
                                                       size="mini" @click="finishTask(index)"></el-button>
                                            <!--删除活动-->
                                            <el-button type="danger" icon="el-icon-delete" circle
                                                       size="mini" @click="deleteTask(index)"></el-button>
                                        </div>
                                    </div>
                                </el-card>
                            </el-timeline-item>
                            <el-timeline-item timestamp="" placement="top">
                                <el-card shadow="hover">
                                    <input class="addItemInput" placeholder="添加活动名称" v-model="addTodoTask.name"/>
                                    <div class="chooseDate">
                                        <el-date-picker
                                                type="date"
                                                v-model="addTodoTask.expectedTime"
                                                placeholder="完成日期">
                                        </el-date-picker>
                                        <el-button @click="addTodoTaskBtn()" type="info" size="mini" round>添加
                                        </el-button>
                                    </div>
                                </el-card>
                            </el-timeline-item>
                        </el-timeline>
                    </div>
                </div>
            </el-col>
            <el-col :span="6">
                <div class="grid-content bg-purple">
                    <div class="actionTitle">
                        <el-tag size="medium" type="warning">今日待办</el-tag>
                    </div>
                    <div class="block actionList">
                        <el-timeline>
                            <el-timeline-item
                                    v-for="(item,index) in todoTodayList"
                                    :color="item.beginTime == 0 ? '#409EFF' : (item.endTime == 0 ? '#F56C6C': '#67C23A')"
                                    :icon="item.beginTime == 0 ? 'el-icon-s-unfold' : (item.endTime == 0 ?'el-icon-loading': 'el-icon-check')"
                                    :timestamp="getTime(item.makeTime)"
                                    placement="top">
                                <el-card shadow="hover" :body-style="{ padding: '15px' }">
                                    <div class="todoItem">
                                        <!--文字说明区-->
                                        <div>
                                            <h4>{{item.name}}</h4>
                                            <p class="planTimeFinish">计划用时：{{getTodoPlanTime(item.expectedTime)}}</p>&nbsp;&nbsp;
                                            <p v-if="item.beginTime == 0" class="planTimeFinish"></p>
                                            <p v-else-if="item.endTime == 0" class="planTimeFinish">
                                                {{todoTodayListItemLastTimeStr}}
                                            </p>
                                            <p v-else-if="item.endTime != 0" class="planTimeFinish">
                                                实际用时：{{getTodoPlanSecondTime(item.actualTime)}}</p>
                                        </div>
                                        <!--操作图标区-->
                                        <div class="optBtns">
                                            <!--启动-->
                                            <el-button :type="!item.isRunning ? start.type : stop.type"
                                                       :icon="!item.isRunning? start.icon:stop.icon" circle
                                                       :disabled="(item.beginTime == 0 ? false : true)"
                                                       size="mini" @click="beginTodo(index)"></el-button>
                                            <!--完成-->
                                            <el-button type="success" icon="el-icon-check" circle
                                                       :disabled="(item.endTime == 0 ? false : true)"
                                                       size="mini" @click="finishTodo(index)"></el-button>
                                        </div>
                                    </div>
                                </el-card>
                            </el-timeline-item>
                            <el-timeline-item timestamp="" placement="top">
                                <el-card shadow="hover">
                                    <input class="addItemInput" placeholder="添加计划外的临时待办" v-model="addTodoToday.name"/>
                                    <div class="chooseDate">
                                        <el-input v-model="addTodoToday.expectedTime"
                                                  placeholder="输入所需时间：分钟"></el-input>
                                        <el-button @click="addOutPlanTodo()" type="info" size="mini" round>添加
                                        </el-button>
                                    </div>
                                </el-card>
                            </el-timeline-item>
                        </el-timeline>
                    </div>
                </div>
            </el-col>
            <el-col :span="6">
                <div class="grid-content bg-purple">
                    <div class="actionTitle">
                        <el-tag size="medium" type="success">今日完成清单</el-tag>
                    </div>
                    <div class="block actionList">
                        <el-timeline>
                            <el-timeline-item
                                    v-for="(item,index) in finishTodayTodo"
                                    :color="item.beginTime == 0 ? '#409EFF' : (item.endTime == 0 ? '#F56C6C': '#67C23A')"
                                    :icon="item.beginTime == 0 ? 'el-icon-s-unfold' : (item.endTime == 0 ?'el-icon-loading': 'el-icon-check')"
                                    :timestamp="getTime(item.makeTime)"
                                    placement="top">
                                <el-card shadow="hover" :body-style="{ padding: '15px' }">
                                    <div class="todoItem">
                                        <!--文字说明区-->
                                        <div>
                                            <h4>{{item.name}}</h4>
                                            <p class="planTimeFinish">计划用时：{{getTodoPlanTime(item.expectedTime)}}</p>&nbsp;&nbsp;
                                            <p v-if="item.beginTime == 0" class="planTimeFinish"></p>
                                            <p v-else-if="item.endTime == 0" class="planTimeFinish">
                                                {{todoTodayListItemLastTimeStr}}
                                            </p>
                                            <p v-else-if="item.endTime != 0" class="planTimeFinish">
                                                实际用时：{{getTodoPlanSecondTime(item.actualTime)}}</p>
                                        </div>
                                        <!--操作图标区-->
                                        <div class="optBtns">
                                            <!--启动-->
                                            <el-button :type="!item.isRunning ? start.type : stop.type"
                                                       :icon="!item.isRunning? start.icon:stop.icon" circle
                                                       :disabled="(item.beginTime == 0 ? false : true)"
                                                       size="mini" @click="beginTodo(index)"></el-button>
                                            <!--完成-->
                                            <el-button type="success" icon="el-icon-check" circle
                                                       :disabled="(item.endTime == 0 ? false : true)"
                                                       size="mini" @click="finishTodo(index)"></el-button>
                                        </div>
                                    </div>
                                </el-card>
                            </el-timeline-item>
                        </el-timeline>
                    </div>
                </div>
            </el-col>
            <el-col :span="6">
                <div class="grid-content bg-purple">
                    <div class="actionTitle">
                        <el-tag size="medium" type="danger">反思</el-tag>
                    </div>
                    <div class="block">
                        <el-timeline class="thinking">
                            <el-timeline-item timestamp="" placement="top">
                                <el-card shadow="hover">
                                    <textarea class="writeThink" placeholder="请记录你的小思考" v-model="addThinkingBean.content"/>
                                    <el-button @click="addThinking" type="info" size="mini" round>添加</el-button>
                                </el-card>
                            </el-timeline-item>
                            <el-timeline-item v-for="item in thinkList" :timestamp="getTime(item.makeTime)" placement="top">
                                <el-card shadow="hover">
                                    <p>{{item.content}}</p>
                                </el-card>
                            </el-timeline-item>
                        </el-timeline>
                    </div>
                </div>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    let vueApp;
    let startTodoInterval;
    export default {
        name: "TodoHome",
        data() {
            return {
                stop: {
                    type: 'danger',
                    icon: 'el-icon-video-pause'
                },
                start: {
                    type: 'warning',
                    icon: 'el-icon-video-play'
                },
                todoTaskList: [
                    /*
                                        {
                                            id: '1',
                                            name: "活动名称1",
                                            makeTime: 1593974684557,
                                            expectedTime: 1593984412295,
                                            actualTime: 0,
                                        }
                    */
                ],
                addTodoTask: {
                    name: "",
                    makeTime: 0,
                    expectedTime: '',
                    actualTime: 0,
                    uid: 0
                },

                todoTodayList: [
                    /*
                                        {
                                            id: '1',
                                            name: "活动名称1",
                                            makeTime: 1593974684557,
                                            expectedTime: 8240000,
                                            isOutPlan: 0,
                                            beginTime: 0,
                                            endTime: 0,
                                            isRunning: 0,
                                            actualTime: 0,
                                        },
                    */
                ],
                addTodoToday: {
                    uid: 0,
                    name: '',
                    makeTime: 0,
                    expectedTime: '',
                    isOutPlan: 1,
                    beginTime: 0,
                    endTime: 0,
                    isRunning: 0,
                    actualTime: 0,
                },
                finishTodayTodo: [],
                taskFinish: {
                    color: '#0bbd87',
                },
                todoTodayListItemLastTimeStr: '',
                thinkList: [
                    {
                        id: 0,
                        uid: 0,
                        makeTime: '',
                        content: ''
                    }
                ],
                addThinkingBean: {
                    uid: 0,
                    makeTime: '',
                    content: '',
                }
            };
        },
        computed: {
        },
        methods: {
            addOutPlanTodo() {
                this.addTodoToday.uid = sessionStorage.getItem('uid')
                this.addTodoToday.makeTime = new Date().getTime();
                this.addTodoToday.expectedTime = this.addTodoToday.expectedTime * 60 * 1000;
                this.$http.post('todoListToday/insert', this.addTodoToday)
                    .then(function (response) {
                        console.log(response)
                        vueApp.getAllUnfinishTodayTodo();
                        vueApp.addTodoToday.uid = 0
                        vueApp.addTodoToday.name = ''
                        vueApp.addTodoToday.makeTime = 0
                        vueApp.addTodoToday.expectedTime = ''
                    })
                    .catch(function (err) {
                        console.log(err)
                    })
            },
            addTodoTaskBtn() {
                this.addTodoTask.makeTime = new Date().getTime();
                this.addTodoTask.expectedTime = this.addTodoTask.expectedTime.getTime();
                this.addTodoTask.uid = sessionStorage.getItem("uid");
                console.log(this.addTodoTask)
                this.$http.post('/todoTask/insert', this.addTodoTask)
                    .then(function (res) {
                        vueApp.addTodoTask.name = '';
                        vueApp.addTodoTask.makeTime = '';
                        vueApp.addTodoTask.expectedTime = '';
                        vueApp.getAllUnfinishTodoTask();
                    })
                    .catch(function (err) {
                        console.log(err)
                    });
            },
            async deleteTask(index) {
                if (!await this.$messageBox.confirm('确认删除该任务？')) return
                //数据库中删除任务项
                await this.$http.get('/todoTask/delete?id=' + this.todoTaskList[index].id)
                    .then(function (response) {
                        //页面中删除任务项
                        vueApp.todoTaskList.splice(index, 1);
                    })
            },
            addTaskToTodo(index, id) {
                this.$messageBox.prompt('请输入需要的时间单位：分钟', '', {
                    confirmButtonText: '添加到Todo',
                    cancelButtonText: '取消',
                    inputPattern: /[\d]{1,3}/,
                    inputErrorMessage: '时间格式不正确'
                }).then(({value}) => {
                    //页面今日待办显示
                    let todo = {
                        uid: sessionStorage.getItem('uid'),
                        name: vueApp.todoTaskList[index].name,
                        makeTime: new Date().getTime(),
                        expectedTime: value * 60000,
                        isOutPlan: 0,
                        beginTime: 0,
                        endTime: 0,
                        isRunning: 0,
                        actualTime: 0,
                    }
                    vueApp.todoTodayList.push(todo)
                    //数据库中存储今日待办
                    vueApp.$http.post('todoListToday/insert', todo)
                    this.$message({
                        type: 'success',
                        message: '"' + vueApp.todoTaskList[index].name + '" 活动已添加到今日待办中'
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '取消输入'
                    });
                });
            },
            getTodoPlanSecondTime(intTime) {
                let hour = Math.floor(intTime / 1000 / 60 / 60 % 24)
                let minite = Math.floor(intTime / 1000 / 60 % 60)
                let second = Math.floor(intTime / 1000 % 60)
                return hour + "时" + minite + "分" + second + "秒";
            },
            getTodoPlanTime(intTime) {
                let hour = Math.floor(intTime / 1000 / 60 / 60 % 24)
                let minite = Math.floor(intTime / 1000 / 60 % 60)
                let second = Math.floor(intTime / 1000 % 60)
                return hour + "时" + minite + "分";
            },
            getTime(intTime) {
                let date = new Date(intTime);
                let result = date.getFullYear() + '/' + (date.getMonth() + 1) + '/' + date.getDate() + ' ' + date.getHours() + ':' + date.getMinutes() + ':' + date.getSeconds();
                return result;
            },
            getDay(startTime, endTime) {
                return Math.floor((endTime - startTime) / 86400000);
            },
            getDate(intTime) {
                let date = new Date(intTime);
                let result = date.getFullYear() + '/' + (date.getMonth() + 1) + '/' + date.getDate();
                return result;
            },
            finishTask(index) {
                // 更新页面数据
                this.todoTaskList[index].actualTime = new Date().getTime();
                // 更新数据库数据
                this.$http.post('/todoTask/finish', this.todoTaskList[index])
                    .then(function (response) {
                        vueApp.getAllUnfinishTodoTask();
                    })
            },
            beginTodo(index) {
                let initTime = 1;
                vueApp.todoTodayList[index].isRunning = 1;
                vueApp.todoTodayList[index].beginTime = new Date().getTime();
                //更新数据库 beginTime, isRunning字段
                this.$http.post('/todoListToday/beginTodo', this.todoTodayList[index])
                    .then(function (response) {
                        startTodoInterval = setInterval(function () {
                            vueApp.todoTodayList[index].actualTime = initTime;
                            vueApp.todoTodayListItemLastTimeStr = '实际用时：' + vueApp.getTodoPlanSecondTime(initTime * 1000)
                            initTime++;
                        }, 1000);
                    })
            },
            finishTodo(index) {
                clearInterval(startTodoInterval);
                this.todoTodayList[index].isRunning = 0;
                this.todoTodayList[index].endTime = new Date().getTime();
                this.todoTodayList[index].actualTime = this.todoTodayList[index].endTime - this.todoTodayList[index].beginTime;
                //更新数据库 endTime, isRunning, actualTime字段
                this.$http.post('/todoListToday/finishTodo', this.todoTodayList[index])
                    .then(function (response) {
                    })
                //将已完成数据转移到今日完成清单中
                this.finishTodayTodo.push(this.todoTodayList[index])
                //在今日待办中移除完成项
                this.todoTodayList.splice(index, 1);
            },
            getAllUnfinishTodoTask() {
                this.$http.get('/todoTask/selectUnfinishTask?uid=' + sessionStorage.getItem('uid'))
                    .then(function (resp) {
                        vueApp.todoTaskList = resp.data;
                    })
                    .catch(function (err) {
                        console.log(err)
                    })
            },
            getAllTodoTask() {
                this.$http.get('/todoTask/getAllTodoTask?uid=' + sessionStorage.getItem('uid'))
                    .then(function (resp) {
                        vueApp.todoTaskList = resp.data;
                    })
                    .catch(function (err) {
                        console.log(err)
                    })
            },
            getAllUnfinishTodayTodo() {
                this.$http.get('/todoListToday/selectUnfinishTask?uid=' + sessionStorage.getItem('uid'))
                    .then(function (resp) {
                        vueApp.todoTodayList = resp.data;
                    })
                    .catch(function (err) {
                        console.log(err)
                    })
            },
            getAllFinishTodayTodo() {
                this.$http.get('/todoListToday/selectFinishTodo?uid=' + sessionStorage.getItem('uid'))
                    .then(function (resp) {
                        vueApp.finishTodayTodo = resp.data;
                    })
                    .catch(function (err) {
                        console.log(err)
                    })
            },
            getAllTodoThink() {
                this.$http.get('/todoThink/selectAll?uid=' + sessionStorage.getItem('uid'))
                    .then(function (resp) {
                        vueApp.thinkList = resp.data;
                    })
                    .catch(function (err) {
                        console.log(err)
                    })
            },
            addThinking() {
                this.addThinkingBean.uid = sessionStorage.getItem("uid");
                this.addThinkingBean.makeTime = new Date().getTime();
                console.log(this.addThinkingBean)
                this.$http.post('/todoThink/insert', this.addThinkingBean)
                    .then(function (response) {
                        vueApp.getAllTodoThink();
                        vueApp.addThinkingBean.content = ''
                    })
                    .catch(function (error) {
                        console.log(error)
                    })
            }
        },
        mounted() {
            vueApp = this;
            this.getAllUnfinishTodoTask();
            this.getAllUnfinishTodayTodo();
            this.getAllFinishTodayTodo();
            this.getAllTodoThink();
        }
    }
</script>

<style lang="less" scoped>

    .thinking {
        .writeThink {
            width: 100%;
            height: 60px;
            border: 0;
            resize: none;
            outline: none;
        }
    }

    .chooseDate {
        margin-top: 10px;

        .el-input {
            width: 160px !important;
        }

        .el-button {
            display: none;
            margin-top: 5px;
            float: right;
        }
    }

    .el-card:hover {
        .el-button {
            display: inline;
        }
    }

    .el-timeline-item:hover {
        .optBtns button {
            display: inline;
        }
    }

    .optBtns button {
        display: none;
        margin-top: 10px;
    }

    .onTimeFinish, .beforeTimeFinish, .afterTimeFinish, .planTimeFinish {
        padding-top: 5px;
        opacity: 50%;
    }

    .onTimeFinish {
        color: #67c23a;
    }

    .beforeTimeFinish {
        color: #fcc14c;
    }

    .afterTimeFinish {
        color: #ff335f;
    }

    .todoItem .planLaterTimeFinish {
        color: #ff0000;
    }

    .todoItem {
        box-sizing: border-box;
        display: flex;
        justify-content: space-between;
    }

    .addItemInput {
        outline: 0;
        border: 0;
    }

    .el-tag {
        margin-bottom: 10px;
        cursor: pointer;
    }

    .el-timeline-item {
        margin: 0 0 0 20px;
    }

    .vueApp {
        position: relative;
    }

    .el-row-center {
        box-sizing: border-box;
        position: absolute;
        display: flex;
        width: 95%;
        height: 100%;
        justify-content: space-between;
        transform: translate(-50%);
        left: 50%;
    }

    .el-row {
        margin-bottom: 20px;
        padding-top: 30px;

        &:last-child {
            margin-bottom: 0;
        }

        li {
            list-style: none;
        }
    }

    .el-col {
        height: 100%;
        border-radius: 4px;
        overflow: auto;
    }

    .bg-purple-dark {
        background: #99a9bf;
    }

    .bg-purple {
        /*background: rgba(211, 220, 230, 0.2);*/
    }

    .bg-purple-light {
        background: #e5e9f2;
    }

    .grid-content {
        border-radius: 4px;
        height: 100%;
        min-height: 36px;
    }

    .row-bg {
        padding: 10px 0;
        background-color: #f9fafc;
    }
</style>