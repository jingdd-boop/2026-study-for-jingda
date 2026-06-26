/**
 * Demo：环境变量
 * 运行：npm install && node env-demo.js
 */

require('dotenv').config();

console.log('===== process.env =====');
console.log('NODE_ENV:', process.env.NODE_ENV || '(未设置，默认 development)');
console.log('PORT:', process.env.PORT || '3000（使用默认值）');
console.log('APP_NAME:', process.env.APP_NAME || '(未设置)');

console.log('\n===== 实际应用场景 =====');
const port = Number(process.env.PORT) || 3000;
const isDev = process.env.NODE_ENV !== 'production';

console.log(`服务将在端口 ${port} 启动`);
console.log(`当前模式: ${isDev ? '开发' : '生产'}`);

if (isDev) {
  console.log('开发模式：开启详细日志、允许 CORS *');
} else {
  console.log('生产模式：精简日志、严格 CORS');
}

console.log('\n===== 前端类比 =====');
console.log('process.env.PORT     ≈  import.meta.env.VITE_PORT');
console.log('dotenv + .env        ≈  Vite .env.development');
console.log('NODE_ENV=production  ≈  vite build 时的 production 模式');

console.log('\n💡 试试: PORT=5000 NODE_ENV=production node env-demo.js');
