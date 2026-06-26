const app = require('./app');

const PORT = process.env.PORT || 3007;

app.listen(PORT, () => {
  console.log(`✅ 测试安全 Demo: http://localhost:${PORT}`);
  console.log('   运行测试: npm test');
});
