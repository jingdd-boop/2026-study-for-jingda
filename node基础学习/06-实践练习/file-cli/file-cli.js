/**
 * 实践练习：文件 CLI 工具
 *
 * 用法：
 *   node file-cli.js read <文件路径>
 *   node file-cli.js write <文件路径> <内容>
 *   node file-cli.js list <目录路径>
 */

const fs = require('fs/promises');
const path = require('path');

const [, , command, ...args] = process.argv;

function usage() {
  console.log(`
文件 CLI 工具

用法:
  node file-cli.js read  <文件>
  node file-cli.js write <文件> <内容>
  node file-cli.js list  <目录>

示例:
  node file-cli.js read sample.txt
  node file-cli.js write output.txt "Hello"
  node file-cli.js list .
`);
}

async function cmdRead(filePath) {
  const abs = path.resolve(filePath);
  const content = await fs.readFile(abs, 'utf-8');
  console.log(`--- ${abs} ---`);
  console.log(content);
}

async function cmdWrite(filePath, content) {
  const abs = path.resolve(filePath);
  await fs.writeFile(abs, content, 'utf-8');
  console.log(`✅ 已写入 ${abs} (${content.length} 字符)`);
}

async function cmdList(dirPath) {
  const abs = path.resolve(dirPath || '.');
  const entries = await fs.readdir(abs, { withFileTypes: true });

  console.log(`目录: ${abs}\n`);
  for (const entry of entries) {
    const type = entry.isDirectory() ? '[DIR]' : '[FILE]';
    const stat = await fs.stat(path.join(abs, entry.name));
    console.log(`${type}  ${entry.name.padEnd(20)} ${stat.size} bytes`);
  }
}

async function main() {
  if (!command) {
    usage();
    process.exit(1);
  }

  try {
    switch (command) {
      case 'read':
        if (!args[0]) throw new Error('缺少文件路径');
        await cmdRead(args[0]);
        break;
      case 'write':
        if (!args[0]) throw new Error('缺少文件路径');
        await cmdWrite(args[0], args.slice(1).join(' ') || '');
        break;
      case 'list':
        await cmdList(args[0]);
        break;
      default:
        console.error(`未知命令: ${command}`);
        usage();
        process.exit(1);
    }
  } catch (err) {
    console.error('❌ 错误:', err.message);
    process.exit(1);
  }
}

main();
