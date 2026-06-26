/**
 * Demo 01：Hello World
 * 运行：node hello-world.js
 */

console.log('Hello, Node.js!');

// 查看 Node 版本
console.log('Node 版本:', process.version);

// 当前工作目录（你在终端 cd 进来的路径）
console.log('工作目录:', process.cwd());

// 命令行参数：node hello-world.js foo bar
// process.argv[0] = node 可执行文件路径
// process.argv[1] = 当前脚本路径
// process.argv[2] 起 = 你传入的参数
console.log('命令行参数:', process.argv.slice(2));
