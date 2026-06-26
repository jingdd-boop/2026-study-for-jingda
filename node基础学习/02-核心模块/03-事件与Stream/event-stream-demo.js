/**
 * Demo：EventEmitter 与 Stream
 * 运行：node event-stream-demo.js
 */

const EventEmitter = require('events');
const fs = require('fs');
const path = require('path');
const { pipeline } = require('stream/promises');

const OUTPUT_DIR = path.join(__dirname, 'stream-output');

// ========== Part 1: EventEmitter ==========

console.log('===== EventEmitter =====');

class TodoList extends EventEmitter {
  constructor() {
    super();
    this.items = [];
  }

  add(item) {
    this.items.push(item);
    this.emit('added', item);
    this.emit('change', this.items);
  }

  remove(item) {
    this.items = this.items.filter((i) => i !== item);
    this.emit('removed', item);
    this.emit('change', this.items);
  }
}

const todos = new TodoList();

todos.on('added', (item) => {
  console.log(`[事件] 新增: ${item}`);
});

todos.on('change', (list) => {
  console.log(`[事件] 当前列表: [${list.join(', ')}]`);
});

todos.once('added', () => {
  console.log('[事件] once：这条只会在第一次 add 时打印');
});

todos.add('学 fs');
todos.add('学 Express');
todos.remove('学 fs');

// ========== Part 2: Stream 复制文件 ==========

async function streamDemo() {
  console.log('\n===== Stream 文件复制 =====');

  fs.mkdirSync(OUTPUT_DIR, { recursive: true });

  const sourcePath = path.join(OUTPUT_DIR, 'source.txt');
  const destPath = path.join(OUTPUT_DIR, 'copy.txt');

  // 创建一个大文本（模拟大文件场景）
  const lines = Array.from({ length: 1000 }, (_, i) => `Line ${i + 1}: Node Stream Demo`);
  fs.writeFileSync(sourcePath, lines.join('\n'), 'utf-8');

  const readStream = fs.createReadStream(sourcePath, { encoding: 'utf-8' });
  const writeStream = fs.createWriteStream(destPath);

  let chunkCount = 0;
  readStream.on('data', () => {
    chunkCount++;
  });

  // pipeline 自动处理错误和关闭流
  await pipeline(readStream, writeStream);

  const stat = fs.statSync(destPath);
  console.log(`复制完成: ${sourcePath}`);
  console.log(`       → ${destPath}`);
  console.log(`文件大小: ${stat.size} 字节，分 ${chunkCount} 块读取`);

  console.log('\n===== 为什么用 Stream？=====');
  console.log('- 大文件不会一次性占满内存');
  console.log('- HTTP 上传/下载底层就是 Stream');
  console.log('- 可以管道连接：读 → 压缩 → 写');
}

streamDemo().catch(console.error);
