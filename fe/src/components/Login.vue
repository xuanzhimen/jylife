<template>
    <div class="login-container">
        <div class="login-box">
            <div class="avatar-box">
                <img src="../assets/logo.png" alt="头像">
            </div>
            <div class="login-form">
                <el-form ref="loginFormRef" :model="loginForm" :rules="loginFormRules">
                    <el-form-item prop="username">
                        <el-input
                                placeholder="请输入用户名"
                                prefix-icon="el-icon-user-solid"
                                v-model="loginForm.username">
                        </el-input>
                    </el-form-item>
                    <el-form-item prop="password">
                        <el-input
                                placeholder="请输入密码"
                                prefix-icon="el-icon-lock"
                                v-model="loginForm.password"
                                type="password">
                        </el-input>
                    </el-form-item>
                    <el-form-item class="login-btn">
                        <el-button type="primary" @click="login">登录</el-button>
                        <el-button @click="resetLoginForm">重置</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Login",
        data() {
            return {
                loginForm: {
                    username: 'admin',
                    password: '123456'
                },
                loginFormRules: {
                    username: [
                        { required: true, message: '请输入用户名', trigger: 'blur' },
                        { min: 5, max: 18, message: '用户名长度为 5 到 18 个字符', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: '请输入密码', trigger: 'blur' },
                        { min: 6, max: 18, message: '密码长度在 6 到 18 个字符', trigger: 'blur' }
                    ]
                }
            }
        },
        methods: {
            login() {
                //表单验证
                this.$refs.loginFormRef.validate(async valid => {
                    if (!valid) return this.$message({message: '用户名或密码填写有误，请核实！', type: 'error'});
                    //发送axios请求返回数据  1.promise 用async修饰父函数用await修饰promis函数  2.可直接解析数据 const {data: res}
                    //let promiseData = this.$http.post('login', this.loginForm);
                    let {data: res} = await this.$http.post("user/login", this.loginForm);
                    if (res.code === 20040) return this.$message({message: '用户名或密码错误，请核实！', type: 'error'});
                    sessionStorage.setItem('uid', res.data.id);
                    sessionStorage.setItem('username', res.data.username);
                    this.$message({message: '登录成功', type: 'success'});
                    this.$router.push('/home');
                })
            },
            resetLoginForm() {
                this.$refs.loginFormRef.resetFields();
            }
        }
    }
</script>

<style lang="less" scoped>
    .login-container {
        background-color: #409EFF;
        height: 100%;
    }

    .login-box {
        width: 450px;
        height: 300px;
        background-color: #fff;
        border-radius: 3px;
        position: absolute;
        left: 50%;
        top: 50%;
        transform: translate(-50%, -50%);

        .avatar-box {
            position: absolute;
            left: 50%;
            transform: translate(-50%, -50%);
            height: 130px;
            width: 130px;
            border: 1px solid #eee;
            border-radius: 50%;
            padding: 10px;
            box-shadow: 0 0 10px #ddd;
            background-color: #fff;

            img {
                height: 100%;
                width: 100%;
                border-radius: 50%;
                background-color: #eee;
            }
        }
    }

    .login-form {
        padding: 0 20px;
        margin-top: 100px;

        .login-btn {
            float: right;
        }
    }
</style>