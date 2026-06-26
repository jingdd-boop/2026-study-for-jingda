/**
 * Demo：文件系统 fs
 * 运行：node fs-demo.js
 */

const fs = require('fs');
const fsp = require('fs/promises');
const path = require('path');

const OUTPUT_DIR = path.join(__dirname, 'demo-output');

async function main() {
  console.log('===== 1. 创建目录 =====');
  await fsp.mkdir(OUTPUT_DIR, { recursive: true });
  console.log('已创建:', OUTPUT_DIR);

  console.log('\n===== 2. 写入文本文件 =====');
  const textPath = path.join(OUTPUT_DIR, 'hello.txt');
  await fsp.writeFile(textPath, 'Hello from Node fs!\n第二行内容', 'utf-8');
  console.log('已写入:', textPath);

  console.log('\n===== 3. 读取文本文件 =====');
  const content = await fsp.readFile(textPath, 'utf-8');
  console.log('文件内容:\n', content);

  console.log('\n===== 4. 读写 JSON =====');
  const jsonPath = path.join(OUTPUT_DIR, 'config.json');
  const config = { name: 'Node Demo', version: 1, features: ['fs', 'path'] };
  await fsp.writeFile(jsonPath, JSON.stringify(config, null, 2), 'utf-8');

  const loaded = JSON.parse(await fsp.readFile(jsonPath, 'utf-8'));
  console.log('读取 JSON:', loaded);

  console.log('\n===== 5. 列出目录内容 =====');
  const files = await fsp.readdir(OUTPUT_DIR);
  console.log('demo-output 下的文件:', files);

  console.log('\n===== 6. 文件信息 stat =====');
  const stat = await fsp.stat(textPath);
  console.log('是否文件:', stat.isFile());
  console.log('大小(字节):', stat.size);
  console.log('修改时间:', stat.mtime.toLocaleString());

  console.log('\n===== 7. 回调风格（了解即可）=====');
  fs.readFile(textPath, 'utf-8', (err, data) => {
    if (err) {
      console.error('读取失败:', err.message);
      return;
    }
    console.log('回调读取成功，长度:', data.length);
  });

  // 等待回调执行完毕
  await new Promise((resolve) => setTimeout(resolve, 100));

  console.log('\n✅ Demo 完成，查看 demo-output/ 目录');
}

main().catch(console.error);
