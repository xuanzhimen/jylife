<template>
    <div>
        <div id="editor"></div>
    </div>
</template>

<script>
    let editor;
    var mdDirectory = []
    export default {
        name: "BlogEditor",
        data() {
            return {
                mdVal: '123',
                mdDirectory: mdDirectory
            }
        },
        mounted() {
            const { codeSyntaxHighlight } = this.$editor.plugin;
            editor = new this.$editor({
                el: document.querySelector('#editor'),
                height: '100%',
                initialEditType: 'wysiwyg',
                previewStyle: 'vertical',
                initialValue: '',
                plugins: [codeSyntaxHighlight],
                events: {
                    change() {
                        let mdVal = editor.getMarkdown();
                        let editorEle;
                        if (editor.currentMode !== 'wysiwyg') editorEle = document.querySelector('.te-preview');
                        else editorEle = document.querySelector('.te-editor>.tui-editor-contents');
                        let hEle = editorEle.querySelectorAll('h1, h2, h3, h4, h5, h6');
                        let hn = [-1, -1, -1, -1, -1, -1]
                        let hNum = 0;
                        let lastHNum = 0;
                        mdDirectory = [];
                        for (let i = 0; i < hEle.length; i++) {
                            if ('H1' === hEle[i].nodeName) {
                                mdDirectory.push([hEle[i].innerText, []]);
                                hNum = 1;
                                hn[0]++;
                            }
                            if ('H2' === hEle[i].nodeName) {
                                mdDirectory[hn[0]][1].push([hEle[i].innerText, []]);
                                hNum = 2;
                                hn[1]++;
                            }
                            if ('H3' === hEle[i].nodeName) {
                                mdDirectory[hn[0]][1][hn[1]][1].push([hEle[i].innerText, []]);
                                hNum = 3;
                                hn[2]++;
                            }
                            if ('H4' === hEle[i].nodeName) {
                                mdDirectory[hn[0]][1][hn[1]][1][hn[2]][1].push([hEle[i].innerText, []]);
                                hNum = 4;
                                hn[3]++;
                            }
                            if ('H5' === hEle[i].nodeName) {
                                mdDirectory[hn[0]][1][hn[1]][1][hn[2]][1][hn[3]][1].push([hEle[i].innerText, []]);
                                hNum = 5;
                                hn[4]++;
                            }
                            if ('H6' === hEle[i].nodeName) {
                                mdDirectory[hn[0]][1][hn[1]][1][hn[2]][1][hn[3]][1][hn[4]][1].push([hEle[i].innerText, []]);
                                hNum = 6;
                                hn[5]++;
                            }
                            if (lastHNum > hNum) {
                                for (let j = hNum; j < 7; j++) {
                                    hn[j] = -1;
                                }
                            }
                            if ('H1' === hEle[i].nodeName) lastHNum = 1;
                            else if ('H2' === hEle[i].nodeName) lastHNum = 2;
                            else if ('H3' === hEle[i].nodeName) lastHNum = 3;
                            else if ('H4' === hEle[i].nodeName) lastHNum = 4;
                            else if ('H5' === hEle[i].nodeName) lastHNum = 5;
                            else if ('H6' === hEle[i].nodeName) lastHNum = 6;
                        }
                    },
                }
            });
            this.mkMdDirectory();
            this.mdDirectory= mdDirectory;
        },
        methods: {
            mkMdDirectory() {
                let mdVal = editor.getMarkdown();
                let editorEle;
                if (editor.currentMode !== 'wysiwyg') editorEle = document.querySelector('.te-preview');
                else editorEle = document.querySelector('.te-editor>.tui-editor-contents');
                let hEle = editorEle.querySelectorAll('h1, h2, h3, h4, h5, h6');
                let hn = [-1, -1, -1, -1, -1, -1]
                let hNum = 0;
                let lastHNum = 0;
                mdDirectory = [];
                for (let i = 0; i < hEle.length; i++) {
                    if ('H1' === hEle[i].nodeName) {
                        mdDirectory.push({label: hEle[i].innerText, children: []});
                        hNum = 1;
                        hn[0]++;
                    }
                    if ('H2' === hEle[i].nodeName) {
                        mdDirectory[hn[0]].children.push({label: hEle[i].innerText, children: []});
                        hNum = 2;
                        hn[1]++;
                    }
                    if ('H3' === hEle[i].nodeName) {
                        mdDirectory[hn[0]].children[hn[1]].children.push({label: hEle[i].innerText, children: []});
                        hNum = 3;
                        hn[2]++;
                    }
                    if ('H4' === hEle[i].nodeName) {
                        mdDirectory[hn[0]].children[hn[1]].children[hn[2]].children.push({label: hEle[i].innerText, children: []});
                        hNum = 4;
                        hn[3]++;
                    }
                    if ('H5' === hEle[i].nodeName) {
                        mdDirectory[hn[0]].children[hn[1]].children[hn[2]].children[hn[3]].children.push({label: hEle[i].innerText, children: []});
                        hNum = 5;
                        hn[4]++;
                    }
                    if ('H6' === hEle[i].nodeName) {
                        mdDirectory[hn[0]].children[hn[1]].children[hn[2]].children[hn[3]].children[hn[4]].children.push({label: hEle[i].innerText, children: []});
                        hNum = 6;
                        hn[5]++;
                    }
                    if (lastHNum > hNum) {
                        for (let j = hNum; j < 7; j++) {
                            hn[j] = -1;
                        }
                    }
                    if ('H1' === hEle[i].nodeName) lastHNum = 1;
                    else if ('H2' === hEle[i].nodeName) lastHNum = 2;
                    else if ('H3' === hEle[i].nodeName) lastHNum = 3;
                    else if ('H4' === hEle[i].nodeName) lastHNum = 4;
                    else if ('H5' === hEle[i].nodeName) lastHNum = 5;
                    else if ('H6' === hEle[i].nodeName) lastHNum = 6;
                }
                this.mdDirectory = mdDirectory;
            }
        }

    }

</script>


<style lang="less" scoped>
    .el-main > div {
        height: 100%;
    }

</style>