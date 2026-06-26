/**
 * Demo：Event Loop 执行顺序
 * 运行：node event-loop-demo.js
 *
 * 建议：先自己猜测输出顺序，再运行对照
 */

console.log('1 - 同步');

setTimeout(() => {
  console.log('2 - setTimeout（宏任务）');
}, 0);

Promise.resolve().then(() => {
  console.log('3 - Promise.then（微任务）');
});

queueMicrotask(() => {
  console.log('4 - queueMicrotask（微任务）');
});

console.log('5 - 同步');

// --- 第二轮：微任务里再嵌套 ---
Promise.resolve().then(() => {
  console.log('6 - 微任务 A');
  Promise.resolve().then(() => {
    console.log('7 - 微任务 A 里的微任务 B');
  });
});

setTimeout(() => {
  console.log('8 - 第二个 setTimeout');
  Promise.resolve().then(() => {
    console.log('9 - setTimeout 回调里的微任务');
  });
}, 0);

console.log('\n--- 预期顺序 ---');
console.log('1 → 5 → 3 → 4 → 6 → 7 → 2 → 8 → 9');
console.log('（同步 → 微任务（含嵌套）→ 宏任务 → 宏任务里的微任务）\n');

// Node 特有：setImmediate vs setTimeout(0)
setTimeout(() => console.log('10 - setTimeout 0'), 0);
setImmediate(() => console.log('11 - setImmediate（I/O 阶段）'));

console.log('12 - 最后一行同步');
